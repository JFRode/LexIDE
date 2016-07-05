package br.univali.lexide.modelo;

import java.util.List;

public class Metodo {
    private String identificador;
    private String conteudo;

    public Metodo(String identificador) {
        this.identificador = identificador;
    }
    
    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
}
