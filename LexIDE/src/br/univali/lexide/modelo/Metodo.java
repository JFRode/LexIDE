package br.univali.lexide.modelo;

import java.util.ArrayList;
import java.util.List;

public class Metodo {
    private String identificador;
    private List<String> conteudo;

    public Metodo(String identificador) {
        this.identificador = identificador;
        this.conteudo = new ArrayList();
    }
    
    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public List<String> getConteudo() {
        return conteudo;
    }

    public void setConteudo(List<String> conteudo) {
        this.conteudo = conteudo;
    }
}
