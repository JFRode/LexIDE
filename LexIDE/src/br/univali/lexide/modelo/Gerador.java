//  BIP só considera inteiro
package br.univali.lexide.modelo;

import java.util.ArrayList;
import java.util.List;

public class Gerador {

    private List<String> data;
    private List<String> text;

    public Gerador() {
        data = new ArrayList();
        text = new ArrayList();
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
        return codigo;
    }

    public void novaLinha(Tupla t) {
        if (t.getIo() != null) {    // IO
            if (t.getIo().equals("read")) {
                text.add("LD $in_port");
                text.add("STO " + t.getNome());
            } else {
                if (t.getValor() == null) {
                    text.add("LD " + t.getNome());
                } else {
                    text.add("LDI " + t.getValor());
                }
                text.add("STO $out_port");
            }
        } else if (t.isVetor()) {   
            String instancia = "";
            if (t.getValoresVet().isEmpty()) {                                  //  Declaracao vetor não inicializado
                    instancia = "0";
                    int comp = Integer.parseInt(t.getValor());
                    for (int i = 1; i < comp; i++) {
                        instancia += ",0";
                    }
            } else {                                                            //Declaração de vetor incializado
                instancia = t.getValoresVet().get(0);
                for (int i = 1; i < t.getValoresVet().size(); i++) {
                    instancia += "," + t.getValoresVet().get(i);
                }
            }
            data.add(t.getNome() + " : " + instancia);
        } else if (t.getIndexVet() != null && t.getValor() != null) { 
            for (String operacoe : t.getOperacoes()) {
                System.out.println("operacoes " + operacoe);
            }
            text.add("LDI " + t.getIndexVet());
            text.add("STO 1000");
            text.add("LDI " + t.getValor());
            text.add("STO 1001");
            text.add("LD 1000");
            text.add("STO $indr");
            text.add("LD 1001");
            text.add("STOV " + t.getNome());
            //text.add(t.getNome() + "[" + t.getIndexVet() + "] = " + t.getValor());
        } else if (t.getOperacoes().size() <= 1) {                              // se nao for uma atribuição vai estar vazio
            if (t.isInicializado()) {                                           //  Declaracao variavel inicializada
                data.add(t.getNome() + " : " + t.getValor());
            } else {                                                            //  Declaracao variavel não inicializada;
                data.add(t.getNome() + " : " + "0");
            }
        }
    }

    public List<String> getData() {
        return data;
    }

    public List<String> getText() {
        return text;
    }
}
