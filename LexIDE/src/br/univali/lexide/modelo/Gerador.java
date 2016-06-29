//  BIP só considera inteiro
package br.univali.lexide.modelo;

import java.util.ArrayList;
import java.util.List;

public class Gerador {

    private List<String> data;
    private List<String> text;
    private List<String> temp;
    private int index;
    private String escopoRel;

    public Gerador() {
        data = new ArrayList();
        text = new ArrayList();
        temp = new ArrayList<>();
        this.index = 0;
    }

    public String montarCodigo() {
        String codigo = ".data\n";
        for (Object d : data) {
            codigo += d + "\n";
        }
        codigo += "\n.text\n";
        for (Object t : text) {
            codigo += t + "\n";
        }
        codigo += "HLT 0";
        zerarListas();
        return codigo;
    }

    private void zerarListas() {
        text.clear();
        temp.clear();
    }

    private void verificaOperacaoDesvio(OperacaoRelacional opRel, boolean isEnd) {
        if (opRel.getOperacao() != null) {
            switch (opRel.getOperacao()) {
                case "==":
                    if (isEnd) {
                        temp.add("BNE " + opRel.getEscopo().toUpperCase());
                    } else {
                        temp.add("BNE END_" + opRel.getEscopo().toUpperCase());
                    }
                    break;
                case "!=":
                    if (isEnd) {
                        temp.add("BEQ " + opRel.getEscopo().toUpperCase());
                    } else {
                        temp.add("BEQ END_" + opRel.getEscopo().toUpperCase());
                    }
                    break;
                case "<=":
                    if (isEnd) {
                        temp.add("BGT " + opRel.getEscopo().toUpperCase());
                    } else {
                        temp.add("BGT END_" + opRel.getEscopo().toUpperCase());
                    }
                    break;
                case "<":
                    if (isEnd) {
                        temp.add("BGE " + opRel.getEscopo().toUpperCase());
                    } else {
                        temp.add("BGE END_" + opRel.getEscopo().toUpperCase());
                    }
                    break;
                case ">=":
                    if (isEnd) {
                        temp.add("BLT " + opRel.getEscopo().toUpperCase());
                    } else {
                        temp.add("BLT END_" + opRel.getEscopo().toUpperCase());
                    }
                    break;
                case ">":
                    if (isEnd) {
                        temp.add("BLE " + opRel.getEscopo().toUpperCase());
                    } else {
                        temp.add("BLE END_" + opRel.getEscopo().toUpperCase());
                    }
                    break;
            }
        }
    }

    private void montaInicioDesvio(Tupla t) {
        if (isDigit(t.getOpRel().getOperando1())) {
            temp.add("LDI " + t.getOpRel().getOperando1());
        } else {
            temp.add("LD " + t.getOpRel().getOperando1());
        }
        temp.add("STO 1000");
        if (isDigit(t.getOpRel().getOperando2())) {
            temp.add("LDI " + t.getOpRel().getOperando2());
        } else {
            temp.add("LD " + t.getOpRel().getOperando2());
        }
        temp.add("STO 1001");
        temp.add("LD 1000");
        temp.add("SUB 1001");
    }

    public void novaLinha(Tupla t) {
        if (t.getOpRel().isFor() && t.getOpRel().getOperando1() != null) {
            System.err.println("qualquer coisa" + t.getOpRel().getEscopo());
            if (t.getOpRel().getFinalEscopo() == null) {
                for (String text1 : text) {                                     // Copia o que reconheceu antes do while para o temp
                    temp.add(text1);
                }
                escopoRel = t.getOpRel().getEscopo();
                text = new ArrayList<>();
                temp.add("LDI " + t.getOperacoes().get(0).getOperacao());
                temp.add("STO " + t.getNome());
                temp.add("LDI " + t.getValor());
                temp.add("STO 1000");
                temp.add("LDI 1");
                temp.add("STO 1001");
                temp.add("LD " + t.getNome());
                temp.add(escopoRel.toUpperCase() + ":");
                temp.add("SUB 1000");
                verificaOperacaoDesvio(t.getOpRel(), false);
                System.out.println("");
            }else{
                for (int i = temp.size() - 1; i >= 0; i--) {
                    text.add(0, temp.get(i));
                }
                text.add("LD " + t.getNome());
                text.add("ADD 1001");
                text.add("STO " + t.getNome());
                text.add("JMP " + escopoRel.toUpperCase());
                text.add("END_" + escopoRel.toUpperCase()+":");
                temp.clear();
            }
        }else if (t.getOpRel().isIsWhile()) {
            if (t.getOpRel().getFinalEscopo() == null) {                        // Inicio do While
                for (String text1 : text) {                                     // Copia o que reconheceu antes do while para o temp
                    temp.add(text1);
                }
                escopoRel = t.getOpRel().getEscopo();
                text = new ArrayList<>();
                temp.add("INI_" + t.getOpRel().getEscopo().toUpperCase() + ":");
                montaInicioDesvio(t);
                verificaOperacaoDesvio(t.getOpRel(), false);
                text.clear();
            } else {                                                            // Fim do while
                for (int i = temp.size() - 1; i >= 0; i--) {
                    text.add(0, temp.get(i));
                }
                text.add("JMP INI_" + t.getOpRel().getEscopo().toUpperCase());
                text.add("END_" + escopoRel.toUpperCase() + ":");
                temp.clear();
            }

        } else if (t.getOpRel().isElse()) {                                     // Else
            if (t.getOpRel().getFinalEscopo() == null) {
                verificaOperacaoDesvio(t.getOpRel(), true);
                for (String text1 : text) {
                    temp.add(text1);
                }
                temp.add("JMP END" + escopoRel.toUpperCase());
                temp.add(t.getOpRel().getEscopo().toUpperCase() + ":");
                text.clear();
            } else {
                for (int i = temp.size() - 1; i >= 0; i--) {
                    text.add(0, temp.get(i));
                }
                text.add("END_" + escopoRel.toUpperCase() + ":");
                temp.clear();
            }
        } else if (t.getOpRel().getFinalEscopo() != null && t.getOpRel().getFinalEscopo().equals("}")) {        // termina de montar o codigo if
            verificaOperacaoDesvio(t.getOpRel(), false);
            for (int i = temp.size() - 1; i >= 0; i--) {
                text.add(0, temp.get(i));
            }
            temp.clear();
            text.add("END_" + t.getOpRel().getEscopo().toUpperCase() + ":");
            System.out.println("");

        } else if (t.getOpRel().getOperando1() != null) {                       // Monta o inicio do codigo if
            escopoRel = t.getOpRel().getEscopo();
            text.stream().forEach((text1) -> {
                temp.add(text1);
            });
            text = new ArrayList<>();
            montaInicioDesvio(t);
        } else if (t.getIo() != null) {                                                // IO
            if (t.getIo().equals("read")) {                                     // Read
                if (t.isVetor()) {
                    text.add("LDI " + t.getValor());
                    text.add("STO $indr");
                    text.add("LD $in_port");
                    text.add("STOV " + t.getNome());
                } else {
                    text.add("LD $in_port");
                    text.add("STO " + t.getNome());
                }
            } else if (t.getIo().equals("write")) {                             // Write
                if (t.isVetor()) {
                    text.add("LDI " + t.getValor());
                    text.add("STO $indr");
                    text.add("LDV " + t.getNome());
                } else if (t.getValor() == null) {
                    text.add("LD " + t.getNome());
                } else {
                    text.add("LDI " + t.getValor());
                }
                text.add("STO $out_port");
            }
        } else if (t.isVetor()) {
            if (t.getTipo() != null) {
                String instancia = "";
                if (t.getValoresVet().isEmpty()) {                              //  Declaracao vetor não inicializado
                    instancia = "0";
                    int comp = Integer.parseInt(t.getValor());
                    for (int i = 1; i < comp; i++) {
                        instancia += ",0";
                    }
                } else {                                                        //Declaração de vetor incializado
                    instancia = t.getValoresVet().get(0);
                    for (int i = 1; i < t.getValoresVet().size(); i++) {
                        instancia += "," + t.getValoresVet().get(i);
                    }
                }
                data.add(t.getNome() + " : " + instancia);
            } else {                                                            // Atribuindo um vetor em uma variavel ex: a = vet[5];
                montaOperacao(t);
                text.add("STO " + t.getNome());
            }
        } //else if (t.getIndexVet() != null && t.getValor() != null) {
        else if (t.getIndexVet() != null) {                                     // Atribuição em vetor
            text.add("LDI " + t.getIndexVet());
            text.add("STO 1000");
            montaOperacao(t);
            text.add("STO 1001");
            text.add("LD 1000");
            text.add("STO $indr");
            text.add("LD 1001");
            text.add("STOV " + t.getNome());
        } else if (t.getOperacoes().size() <= 1 && t.getTipo() != null) {       // se nao for uma atribuição vai estar vazio
            if (t.isInicializado()) {                                           //  Declaracao variavel inicializada
                data.add(t.getNome() + " : " + t.getValor());
            } else {                                                            //  Declaracao variavel não inicializada;
                data.add(t.getNome() + " : " + "0");
            }
        } else if (t.getTipo() == null) {                                       // Atribuição de variavel
            montaOperacao(t);
            text.add("STO " + t.getNome());
        }
    }

    private void montaOperacao(Tupla t) {
        for (int i = 0; i < t.getOperacoes().size(); i++) {
            if (t.getOperacoes().get(i).getIndexVet() != null) {
                text.add("LDI " + t.getOperacoes().get(i).getIndexVet());
                text.add("STO $indr");
                text.add("LDV " + t.getOperacoes().get(i).getOperacao());
            } else if (t.getOperacoes().get(i).getOperacao().equals("+")) {
                if (isDigit(t.getOperacoes().get(i + 1).getOperacao())) {
                    text.add("ADDI " + t.getOperacoes().get(i + 1).getOperacao());
                } else if (t.getOperacoes().get(i + 1).getIndexVet() != null) {
                    text.add("STO 1000");
                    text.add("LDI " + t.getOperacoes().get(i + 1).getIndexVet());
                    text.add("STO $indr");
                    text.add("LDV " + t.getOperacoes().get(i + 1).getOperacao());
                    text.add("STO 1001");
                    text.add("LD 1000");
                    text.add("ADD 1001");
                } else {
                    text.add("ADD " + t.getOperacoes().get(i + 1).getOperacao());
                }
                i++;
            } else if (t.getOperacoes().get(i).getOperacao().equals("-")) {
                if (isDigit(t.getOperacoes().get(i + 1).getOperacao())) {
                    text.add("SUBI " + t.getOperacoes().get(i + 1).getOperacao());
                } else {
                    text.add("SUB " + t.getOperacoes().get(i + 1).getOperacao());
                }
                i++;
            } else if (t.getOperacoes().get(i).getOperacao().equals("<<")) {
                text.add("SLL " + t.getOperacoes().get(i + 1).getOperacao());
                i++;
            } else if (t.getOperacoes().get(i).getOperacao().equals(">>")) {
                text.add("SRL " + t.getOperacoes().get(i + 1).getOperacao());
                i++;
            } else if (isDigit(t.getOperacoes().get(i).getOperacao())) {
                text.add("LDI " + t.getOperacoes().get(i).getOperacao());
            } else {
                text.add("LD " + t.getOperacoes().get(i).getOperacao());
            }
        }
    }

    public List<String> getData() {
        return data;
    }

    public List<String> getText() {
        return text;
    }

    public boolean isDigit(String s) {                                          // checa se é numero ou operadores
        return s.matches("[0-9]*");
    }

}
