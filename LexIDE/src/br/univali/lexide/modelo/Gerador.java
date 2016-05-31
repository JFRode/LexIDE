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
        return codigo;
    }
    
    public void novaLinha(Tupla t){
        if (t.isInicializado()) {   //  Declaracao variavel
            data.add(t.getNome() + " : " + t.getValor());
        }
        if (t.isVetor()){   //  Declaracao vetor não inicializado
            String instancia = "0";
            int comp = Integer.parseInt(t.getValor());
            for (int i=1;i < comp; i++) instancia += ",0";
            data.add(t.getNome() + " : " + instancia);
        }
    }

    public List<String> getData() {
        return data;
    }

    public List<String> getText() {
        return text;
    }
}
