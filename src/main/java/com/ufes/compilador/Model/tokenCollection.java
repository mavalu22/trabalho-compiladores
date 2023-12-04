package com.ufes.compilador.Model;

import com.ufes.compilador.DAO.tokenDAO;
import java.util.List;
import java.util.Collections;

public class tokenCollection {
    public List<tokenModel> tokens; // tokens começando do ultimo para o primeiro
    public List<tokenModel> tokensReverse; // tokens começando do primeiro para o ultimo
    
    public tokenCollection() {
        this.tokens = new tokenDAO().getTokens();        
        this.tokensReverse = new tokenDAO().getTokens();
        Collections.reverse(tokensReverse);
    }
    
    public boolean getLinhaPossuiToken (int linha) {
        boolean possui = false;
        for (tokenModel i : tokensReverse) {
            if (i.line == linha) {
                possui = true;
            }
        }
        return possui;
    }
    
    public int getLinhas() {
        int lines = 0;
        for (tokenModel i : this.tokensReverse) {
            lines = i.line;
        }
        return lines;
    }
    
    public tokenModel getFirstToken() {
        return tokensReverse.get(0);
    }
    
    public tokenModel getSecondToken() {
        return tokensReverse.get(1);
    }
    
    public void printTokens() {
        for(tokenModel token: tokens) {
            token.printToken();
        }
    }
}
