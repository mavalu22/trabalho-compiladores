package com.ufes.compilador.Syntatic;

import com.ufes.compilador.JFlex.Yyerror;
import com.ufes.compilador.Model.tokenCollection;
import com.ufes.compilador.Model.tokenModel;

public class Nomeia_Programa {
    public tokenCollection tokenList;

    public Nomeia_Programa(tokenCollection tokenList) {
        this.tokenList = tokenList;
        this.verify();
    }
    
    /*
     verifica se o primeiro token nomeia o programa e se
     o segundo é um identificador
    */
    public void verify() {
        tokenModel firstToken = tokenList.getFirstToken();
        tokenModel secondToken = tokenList.getSecondToken();
        if (!(firstToken.token.equals("TKN_nomeiaPrograma") && secondToken.token.equals("TKN_identificador"))) {
            new Yyerror(firstToken.line, "Erro na nomeação do programa");
        }
    }
}
