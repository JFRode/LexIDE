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
        codigo += "HLT";
        return codigo;
    }

    public void novaLinha(Tupla t) {
        if (t.getIo() != null) {    // IO
            if (t.getIo().equals("read")) {
                text.add("LD $in_port");
                text.add("STO " + t.getNome());
            } else {
                text.add("LD " + t.getNome());
                text.add("STO $out_port");
            }
        } else if (t.isVetor()) {   //  Declaracao vetor não inicializado
            String instancia = "";
            if (t.getValores().size() == 1) {
                instancia = "0";
                int comp = Integer.parseInt(t.getValores().get(0));
                for (int i = 1; i < comp; i++) {
                    instancia += ",0";
                }
            }else{
                instancia = t.getValores().get(0);
                for (int i = 1; i < t.getValores().size(); i++) {
                    instancia += "," + t.getValores().get(i);
                }
            }

            data.add(t.getNome() + " : " + instancia);
        } else if (t.isInicializado()) {   //  Declaracao variavel
            data.add(t.getNome() + " : " + t.getValores().get(0));
        } else {
            data.add(t.getNome() + " : " + "0");
        }
    }

    public List<String> getData() {
        return data;
    }

    public List<String> getText() {
        return text;
    }
}
