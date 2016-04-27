package br.univali.lexide.principal;

import br.univali.lexide.importador.Tupla;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Semantico implements Constants {
    List<Tupla> tabela = new ArrayList();
    Stack<String> pilha = new Stack();
    Tupla temp = new Tupla();
    
    public void executeAction(int action, Token token)	throws SemanticError {
        switch (action) {
            case 1: // name
                temp.setNome(token.getLexeme());
                insereTabela();
                System.out.println("Ação #"+action+", Token: "+token);
                break;
            case 2: // type
                temp.setTipo(token.getLexeme());
                System.out.println("Ação #"+action+", Token: "+token);
                break;
            case 3: // inicialized
                temp.setInicializado(true);
                insereTabela();
                System.out.println("Ação #"+action+", Token: "+token);
                break;
            case 4: // used
                temp.setUsado(true);
                insereTabela();
                System.out.println("Ação #"+action+", Token: "+token);
                break;
            case 5: // scope
                pilha.push(token.getLexeme());
                System.out.println("Ação #"+action+", Token: "+token);
                break;
            case 6: // param
                temp.setParametro(true);
                System.out.println("Ação #"+action+", Token: "+token);
                break;
            case 7: // position
                temp.setPos(token.getPosition());
                System.out.println("Ação #"+action+", Token: "+token);
                break;
            case 8: // vector
                temp.setVetor(true);
                insereTabela();
                System.out.println("Ação #"+action+", Token: "+token);
                break;
            case 9: // matrix
                temp.setMatriz(true);
                System.out.println("Ação #"+action+", Token: "+token);
                break;
            case 10: // ref
                temp.setRef(true);
                insereTabela();
                System.out.println("Ação #"+action+", Token: "+token);
                break;
            case 11: // func
                temp.setFuncao(true);
                insereTabela();
                System.out.println("Ação #"+action+", Token: "+token);
                break;
        }
    }
    
    public void insereTabela() {
        temp.setEscopo(pilha.firstElement());
        
        if (tabela.size() != 0) {
            for (Tupla t : tabela) {
                if (temp.getNome().equals(t.getNome()) && temp.getEscopo().equals(t.getEscopo())) {
                    System.out.println(t.getNome() + " Ja existe neste escopo!");
                } else {
                    tabela.add(temp);
                    System.out.println("INSERIU");
                }
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
            System.out.print(t.getTipo()+ "\t");
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
