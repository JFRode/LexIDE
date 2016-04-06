package br.univali.lexide.principal;



public class Semantico implements Constants
{
    public void executeAction(int action, Token token)	throws SemanticError
    {
        System.out.println("Ao #"+action+", Token: "+token);
    }	
}
