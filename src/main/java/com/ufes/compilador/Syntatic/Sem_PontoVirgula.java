package com.ufes.compilador.Syntatic;

import com.ufes.compilador.JFlex.Yyerror;
import com.ufes.compilador.Model.tokenCollection;
import com.ufes.compilador.Model.tokenModel;
import java.util.ArrayList;
import java.util.List;

public class Sem_PontoVirgula {
    public tokenCollection tokenList;

    /*
     variáveis auxiliares
    */
    public List<Boolean> temPontoEVirgula; // se no índex (linha) possui ponto e vírgula
    public int numLinhas;
    
    public Sem_PontoVirgula(tokenCollection tokenList) {
        this.tokenList = tokenList;
        this.temPontoEVirgula = new ArrayList<Boolean>();
        this.numLinhas = this.tokenList.getLinhas();
        this.initialize_temPontoEVirgula();
        this.verify();
    }
    
    /*
     sobre a lista pra saber se tem ponto e vírgula
    */
    public void initialize_temPontoEVirgula() {
        for (int i = 0; i <= numLinhas; i++) {
            if (this.tokenList.getLinhaPossuiToken(i) == false) {
                this.temPontoEVirgula.add(true);
            } else {
                this.temPontoEVirgula.add(false);
            }
        }
    }
    
    public void show_temPontoEVirgula() {
        for (int i = 0; i <= numLinhas; i++) {
            System.out.println("Linha: " + i + ", tem ponto e vírgula ? " + this.temPontoEVirgula.get(i));
        }
    }
    
    /*
     verifica se todas as linhas terminam com ponto e vírgula, com exceção das:
     nomeiam programa, variáveis/constantes, declaram procedimento, iniciam ou terminam bloco
    */
    public void verify () {
        for(tokenModel tk : tokenList.tokensReverse) {
            if (tk.token.equals("TKN_pontoEvirgula") || tk.token.equals("TKN_nomeiaPrograma") || tk.token.equals("TKN_declaraProcedimento") || tk.token.equals("TKN_declarafuncao") || tk.token.equals("TKN_iniciaBloco") || tk.token.equals("TKN_terminaBloco") || tk.token.equals("TKN_iniciaVariavel") || tk.token.equals("TKN_iniciaConstante")) {
                this.temPontoEVirgula.set(tk.line, true);
            }
        }
        for (int i = 0; i <= numLinhas; i++) {
            if (this.temPontoEVirgula.get(i) == false) {
                new Yyerror(i, "Ponto e vírgula não encontrado");
            }
        }
    }
}
