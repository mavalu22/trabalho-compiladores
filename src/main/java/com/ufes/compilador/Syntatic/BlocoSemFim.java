package com.ufes.compilador.Syntatic;

import com.ufes.compilador.JFlex.Yyerror;
import com.ufes.compilador.Model.tokenCollection;
import com.ufes.compilador.Model.tokenModel;

public class BlocoSemFim {
    public tokenCollection tokenList;

    public BlocoSemFim(tokenCollection tokenList) {
        this.tokenList = tokenList;
        this.verify();
    }
    
    /*
     verifica se o bloco foi iniciado e não foi finalizado
    */
    public void verify() {
        tokenModel bloco = new tokenModel("", "", -1);
        for (tokenModel i : tokenList.tokensReverse) {
            if (i.token.equals("TKN_iniciaBloco")) {
                if (bloco.line != -1) {
                    new Yyerror(bloco.line, "Bloco inicializado, mas não finalizado");
                }
                bloco = i;
            }
            if (i.token.equals("TKN_terminaBloco")) {
                bloco = new tokenModel("", "", -1);
            }
        }
        if (bloco.line != -1) {
            new Yyerror(bloco.line, "Bloco inicializado, mas não finalizado");
            bloco = new tokenModel("", "", -1);
        }
    }
}
