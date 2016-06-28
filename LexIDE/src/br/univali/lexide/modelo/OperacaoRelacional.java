package br.univali.lexide.modelo;

public class OperacaoRelacional {
    private String operando1;
    private String operando2;
    private String operacao;
    private String escopo;
    private boolean Else;
    private String finalEscopo;
    
    public boolean isElse() {
        return Else;
    }

    public void setElse(boolean Else) {
        this.Else = Else;
    }
    
    public String getFinalEscopo() {
        return finalEscopo;
    }

    public void setFinalEscopo(String finalEscopo) {
        this.finalEscopo = finalEscopo;
    }

    public String getOperando1() {
        return operando1;
    }

    public void setOperando1(String operando1) {
        this.operando1 = operando1;
    }

    public String getOperando2() {
        return operando2;
    }

    public void setOperando2(String operando2) {
        this.operando2 = operando2;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public String getEscopo() {
        return escopo;
    }

    public void setEscopo(String escopo) {
        this.escopo = escopo;
    }
}
