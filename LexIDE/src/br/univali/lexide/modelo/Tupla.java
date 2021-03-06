package br.univali.lexide.modelo;

import java.util.ArrayList;
import java.util.List;

public class Tupla {
    private String nome;
    private String tipo;
    private String valor;
    private List<String> valoresVet = new ArrayList<>();
    private List<Operacao> operacoes = new ArrayList<>();
    private String indexVet = null;
    private String io = null;
    private boolean inicializado = false;
    private boolean usado = false;
    private String escopo;
    private boolean parametro = false;
    private int pos = 0;
    private boolean vetor = false;
    private boolean matriz = false;
    private boolean ref = false;
    private boolean funcao = false;
    private OperacaoRelacional opRel = new OperacaoRelacional();
    private String incrementType;

    public OperacaoRelacional getOpRel() {
        return opRel;
    }

    public void setOpRel(OperacaoRelacional opRel) {
        this.opRel = opRel;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
    public boolean isInicializado() {
        return inicializado;
    }

    public void setInicializado(boolean inicializado) {
        this.inicializado = inicializado;
    }

    public boolean isUsado() {
        return usado;
    }

    public void setUsado(boolean usado) {
        this.usado = usado;
    }

    public String getEscopo() {
        return escopo;
    }

    public void setEscopo(String escopo) {
        this.escopo = escopo;
    }

    public boolean isParametro() {
        return parametro;
    }

    public void setParametro(boolean parametro) {
        this.parametro = parametro;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public boolean isVetor() {
        return vetor;
    }

    public void setVetor(boolean vetor) {
        this.vetor = vetor;
    }

    public boolean isMatriz() {
        return matriz;
    }

    public void setMatriz(boolean matriz) {
        this.matriz = matriz;
    }

    public boolean isRef() {
        return ref;
    }

    public void setRef(boolean ref) {
        this.ref = ref;
    }

    public boolean isFuncao() {
        return funcao;
    }

    public void setFuncao(boolean funcao) {
        this.funcao = funcao;
    }

    public String getIo() {
        return io;
    }

    public void setIo(String io) {
        this.io = io;
    }

    public List<String> getValoresVet() {
        return valoresVet;
    }

    public void setValoresVet(List<String> valores) {
        this.valoresVet = valores;
    }
    
    public void addValorVer(String valor){
        this.valoresVet.add(valor);
    }
    
    public List<Operacao> getOperacoes() {
        return operacoes;
    }

    public void setOperacoes(List<Operacao> operacoes) {
        this.operacoes = operacoes;
    }
    
    public void addOperacao(String operacao, String indexVet){
        this.operacoes.add(new Operacao(operacao, indexVet));
    }
    public String getIndexVet() {
        return indexVet;
    }

    public void setIndexVet(String indexVet) {
        this.indexVet = indexVet;
    }

    public String getIncrementType() {
        return incrementType;
    }

    public void setIncrementType(String incrementType) {
        this.incrementType = incrementType;
    }
}
