package br.univali.lexide.gals;

import br.univali.lexide.visao.LexIDE;
import br.univali.lexide.exception.BusinessException;
import br.univali.lexide.exception.InfoException;
import br.univali.lexide.modelo.Tupla;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Semantico implements Constants {

    public static List<Tupla> tabela;
    private Stack<String> pilha;
    private Tupla temp;
    private int contIF;
    private int contELSE;
    private int contWHILE;
    private int contDO;

    public Semantico() {
        tabela = new ArrayList();
        pilha = new Stack();
        temp = new Tupla();
        contIF = 0;
        contELSE = 0;
        contWHILE = 0;
        contDO = 0;
        pilha.push("Global");
    }

    public void executeAction(int action, Token token) throws SemanticError, BusinessException, InfoException {

        switch (action) {
            case 1: // name
                temp.setNome(token.getLexeme());
                System.out.println("Ação nome #" + action + ", Token: " + token.getLexeme());
                break;
            case 2: // type
                temp.setTipo(token.getLexeme());
                System.out.println("Ação tipo #" + action + ", Token: " + token.getLexeme());
                break;
            case 3: // inicialized
                temp.setInicializado(true);
                System.out.println("Ação inicializado #" + action + ", Token: " + token.getLexeme());
                break;
            case 4: // used
                temp.setUsado(true);
                System.out.println("Ação usado #" + action + ", Token: " + token.getLexeme());
                break;
            case 5: // scope
                inserePilha(token);
                System.out.println("Ação escopo #" + action + ", Token: " + token.getLexeme());
                break;
            case 6: // param
                temp.setParametro(true);
                System.out.println("Ação parametro #" + action + ", Token: " + token.getLexeme());
                break;
            case 7: // position
                temp.setPos(token.getPosition());
                System.out.println("Ação posição #" + action + ", Token: " + token.getLexeme());
                break;
            case 8: // vector
                temp.setVetor(true);
                System.out.println("Ação vetor #" + action + ", Token: " + token.getLexeme());
                break;
            case 9: // matrix
                temp.setMatriz(true);
                System.out.println("Ação matrix #" + action + ", Token: " + token.getLexeme());
                break;
            case 10: // ref
                temp.setRef(true);
                System.out.println("Ação referencia #" + action + ", Token: " + token.getLexeme());
                break;
            case 11: // func
                temp.setFuncao(true);
                Tupla retorno = buscaTabela(temp.getNome());
                if (temp.getTipo() != null && temp.isFuncao()) {
                    insereTabela();
                } else if (retorno != null) {
                    temp.setTipo(retorno.getTipo());
                    temp.setUsado(true);
                    retorno.setUsado(true);
                } else {
                    throw new BusinessException("Função não declarada: " + temp.getNome());
                }
                temp = new Tupla();
                System.out.println("Ação função #" + action + ", Token: " + token.getLexeme());
                break;
            case 12: // final line
                insereTabela();
                LexIDE.gerador.novaLinha(temp);
                temp = new Tupla();
                System.out.println("Ação ; #" + action + ", Token: " + token.getLexeme());
                break;
            case 13: // final scope
                System.out.println("Ação ; #" + action + ", Token: " + token.getLexeme());
                System.out.println("Removido: " + pilha.pop());
                break;
            case 14: // final code
                System.out.println("Codigo chegou ao fim.");
                checarVariaveis();
                break;
            case 15: // assignment
                temp.addOperacao(token.getLexeme()); // adiciona uma operação de atribuiçao para ser checada Ex; a = b + c;
                System.out.println("Atribuição.");
                break;
            case 16: // value
                temp.setValor(token.getLexeme());
                System.out.println("Ação ; #" + action + ", Token: " + token.getLexeme());
                break;
            case 17: // index vector
                temp.setIndexVet(token.getLexeme());
                break;
            case 18: // vector value
                temp.addValorVer(token.getLexeme());
                break;
            case 19:
                temp.setIo("read");
                verificaDeclaracao(temp.getNome());
                LexIDE.gerador.novaLinha(temp);
                break;
            case 20:
                temp.setIo("write");
                verificaDeclaracao(temp.getNome());
                LexIDE.gerador.novaLinha(temp);
                break;
        }
    }

    public void insereTabela() throws BusinessException {

        boolean podeInserir = true;

        temp.setEscopo(pilha.peek());
        if (temp.isFuncao()) {
            pilha.push(temp.getNome());
        }
        //if (temp.getTipo() == null) {
        if (!temp.getOperacoes().isEmpty()) {
            Tupla aux;
            if (!temp.getOperacoes().isEmpty()) {
                for (String operacao : temp.getOperacoes()) {
                    if (!isDigit(operacao)) {
                        aux = verificaDeclaracao(operacao);
                        aux.setUsado(true);
                    }
                }
            }
        }
        if (tabela.size() != 0 && temp != null) {
            for (Tupla t : tabela) {
                // 2. declaracao no mesmo escopo
                if (t.getNome().equals(temp.getNome()) && t.getEscopo().equals(temp.getEscopo())) {
                    if (!temp.isFuncao()) {
                        if (temp.getTipo() != null) {
                            podeInserir = false;
                            throw new BusinessException("Nome de variavel já usado: " + t.getNome());
                        } else {
                            t.setInicializado(temp.isInicializado());
                            t.setUsado(temp.isUsado());
                            t.setParametro(temp.isParametro());
                            t.setPos(temp.getPos());
                            t.setRef(temp.isRef());
                            podeInserir = false;
                        }
                    } else {
                        throw new BusinessException("Nome de função já usado: " + t.getNome());
                    }
                }
            }
            if (podeInserir) {
                tabela.add(temp);
                System.out.println("INSERIU");
            }
        } else {
            tabela.add(temp);
            System.out.println("INSERIU");
        }

        imprimeTabela();
    }

    public void imprimeTabela() {
        System.out.println("-------------------------------------------------------------------");
        System.out.println("NOME\tTIPO\tVALOR\tINIC\tUSADO\tESCOPO\tPARAM\tPOS\tVET\tMATRIZ\tREF\tFUNC");
        for (Tupla t : tabela) {
            System.out.print(t.getNome() + "\t");
            System.out.print(t.getTipo() + "\t");
            System.out.print(t.getValor() + "\t");
            System.out.print(t.isInicializado() + "\t");
            System.out.print(t.isUsado() + "\t");
            System.out.print(t.getEscopo() + "\t");
            System.out.print(t.isParametro() + "\t");
            System.out.print(t.getPos() + "\t");
            System.out.print(t.isVetor() + "\t");
            System.out.print(t.isMatriz() + "\t");
            System.out.print(t.isRef() + "\t");
            System.out.print(t.isFuncao() + "\n");
        }
    }

    public boolean isDigit(String s) { // checa se é numero ou operadores
        boolean retorno = false;
        retorno = s.matches("[0-9]*");
        if(s.equals("+") || s.equals("-")){
            retorno = true;
        }
        return retorno;
    }

    private Tupla buscaTabela(String nome) {
        for (Tupla t : tabela) {
            if (t.getNome().equals(nome) && t.isFuncao()) {
                return t;
            }
        }
        return null;
    }

    private void inserePilha(Token token) {
        switch (token.getLexeme()) {
            case "if":
                pilha.push("if-" + contIF);
                contIF++;
                break;
            case "else":
                pilha.push("else-" + contELSE);
                contELSE++;
                break;
            case "while":
                pilha.push("while-" + contWHILE);
                contWHILE++;
                break;
            case "do":
                pilha.push("do-" + contDO);
                contDO++;
                break;
            default:
                pilha.push(token.getLexeme());
                break;
        }
    }

    private void checarVariaveis() throws InfoException {
        String variaveis = "\n";
        for (Tupla t : tabela) {
            if (!t.isUsado()) {
                variaveis += t.getNome() + "\n";
            }
        }
        if (!variaveis.equals("\n")) {
            throw new InfoException("identificadores nunca usados:" + variaveis);
        }
    }

    private Tupla verificaDeclaracao(String nome) throws BusinessException {
        for (int i = (tabela.size() - 1); i >= 0; i--) {
            if (nome.equals(tabela.get(i).getNome())) {
                for (int j = (pilha.size() - 1); j >= 0; j--) {
                    if (tabela.get(i).getEscopo().equals(pilha.get(j))) {
                        if (temp.getIndexVet() != null && temp.getValor() != null) { // Verificar se é uma atribuição de vetor vet[0] = 2;
                            if (tabela.get(i).isVetor()) {
                                return tabela.get(i);
                            } else {
                                throw new BusinessException("identificador '" + nome + "' não foi declarado.");
                            }
                        } else {
                            return tabela.get(i);
                        }
                    }
                }
            }
        }
        throw new BusinessException("identificador '" + nome + "' não foi declarado.");
    }
}
