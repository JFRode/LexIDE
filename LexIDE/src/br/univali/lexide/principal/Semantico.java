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

    public Semantico() {
        tabela = new ArrayList();
        pilha = new Stack();
        temp = new Tupla();
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
                pilha.push(token.getLexeme());
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
                insereTabela();
                //temp = new Tupla();
                System.out.println("Ação função #" + action + ", Token: " + token.getLexeme());
                break;
            case 12: // ;
                insereTabela();
                temp = new Tupla();
                System.out.println("Ação ; #" + action + ", Token: " + token.getLexeme());
                //imprimeTabela();
                break;
        }
    }

    public void insereTabela() throws BusinessException{
        boolean podeInserir = true;
        temp.setEscopo(pilha.peek());
        if (tabela.size() != 0 && temp != null) {
            for (Tupla t : tabela) {
                // 2. declaracao no mesmo escopo
                if (t.getNome().equals(temp.getNome()) && t.getEscopo().equals(temp.getEscopo())) {
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
}
