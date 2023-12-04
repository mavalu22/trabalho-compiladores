package com.ufes.compilador.Semantic;

import com.ufes.compilador.Model.tokenCollection;

public class RunSemantic {
    
    public RunSemantic() {
        tokenCollection tokenList = new tokenCollection();
        new Escopo(tokenList);
        new ErroDeTipo(tokenList);
        new Break_Continue(tokenList);
        new Indice_Vetor(tokenList);
    }
}
