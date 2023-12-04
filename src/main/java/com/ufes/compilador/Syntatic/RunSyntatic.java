package com.ufes.compilador.Syntatic;

import com.ufes.compilador.Model.tokenCollection;

public class RunSyntatic {
    
    public RunSyntatic() {
        tokenCollection tokenList = new tokenCollection();
        new Nomeia_Programa(tokenList);
        new Sem_PontoVirgula(tokenList);
        new BlocoSemFim(tokenList);
        new VerificaProcedimento(tokenList);
    }
}
