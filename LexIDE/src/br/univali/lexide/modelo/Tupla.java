package br.univali.lexide.modelo;

public class Tupla {
    private String nome;
    private String tipo;
    private String valor;
    private String io = null;
    private boolean ldi = false;
    private boolean inicializado = false;
    private boolean usado = false;
    private String escopo;
    private boolean parametro = false;
    private int pos = 0;
    private boolean vetor = false;
    private boolean matriz = false;
    private boolean ref = false;
    private boolean funcao = false;

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

    public boolean isLdi() {
        return ldi;
    }

    public void setLdi(boolean ldi) {
        this.ldi = ldi;
    }
    
}
