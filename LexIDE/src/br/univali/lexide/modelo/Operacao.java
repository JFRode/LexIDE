/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univali.lexide.modelo;

/**
 *
 * @author 5108250
 */
public class Operacao {
    private String operacao;
    private String indexVet;

    public Operacao(String operacao, String indexVet) {
        this.operacao = operacao;
        this.indexVet = indexVet;
    }

    public String getIndexVet() {
        return indexVet;
    }

    public void setIndexVet(String indexVet) {
        this.indexVet = indexVet;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }
}
