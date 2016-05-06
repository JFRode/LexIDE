package br.univali.lexide.principal;

import br.univali.lexide.exception.BusinessException;
import br.univali.lexide.importador.Tupla;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Semantico implements Constants {

    List<Tupla> tabela;
    Stack<String> pilha;
    Tupla temp;
    int contIF;
    int contELSE;

    public Semantico() {
        tabela = new ArrayList();
        pilha = new Stack();
        temp = new Tupla();
        contIF = 1;
        contELSE = 1;
        pilha.push("Global");
    }

    public void executeAction(int action, Token token) throws SemanticError, BusinessException {

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
                    insereTabela();
                } else {
                    throw new BusinessException("Função não declarada: " + temp.getNome());
                }
                temp = new Tupla();
                System.out.println("Ação função #" + action + ", Token: " + token.getLexeme());
                break;
            case 12: // final
                insereTabela();
                temp = new Tupla();
                System.out.println("Ação ; #" + action + ", Token: " + token.getLexeme());
                imprimeTabela();
                break;

//            case 13: // final escope
//                System.out.println("Ação ; #" + action + ", Token: " + token.getLexeme());
//                System.out.println("Removido: " + pilha.pop());
//                break;
        }
    }

    public void insereTabela() throws BusinessException {
        boolean podeInserir = true;
        temp.setEscopo(pilha.peek());
        if (temp.isFuncao()) {
            pilha.push(temp.getNome());
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
        System.out.println("NOME\tTIPO\tINIC\tUSADO\tESCOPO\tPARAM\tPOS\tVET\tMATRIZ\tREF\tFUNC");
        for (Tupla t : tabela) {
            System.out.print(t.getNome() + "\t");
            System.out.print(t.getTipo() + "\t");
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

    private Tupla buscaTabela(String nome) {
        for (Tupla t : tabela) {
            if (t.getNome().equals(nome) && t.isFuncao()) {
                return t;
            }
        }
        return null;
    }

    private void inserePilha(Token token) {
        boolean adicionaUmIF = false;
        boolean adicionaUmELSE = false;
        
        for (String p : pilha) {
            if(token.getLexeme().equals("if") && (token.getLexeme().equals(p) || (token.getLexeme()+contIF).equals(p))){
                adicionaUmIF = true;
            }else if(token.getLexeme().equals("else") && (token.getLexeme().equals(p) || (token.getLexeme()+contELSE).equals(p))){
                adicionaUmELSE = true;
            }
        }

        if (adicionaUmIF) {
            contIF++;
            pilha.push(token.getLexeme() + contIF);
        }else if(adicionaUmELSE){
            contELSE++;
            pilha.push(token.getLexeme() + contELSE);
        }else{
            pilha.push(token.getLexeme());
        }
    }
}
