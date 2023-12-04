package com.ufes.compilador.Syntatic;

import com.ufes.compilador.JFlex.Yyerror;
import com.ufes.compilador.Model.tokenCollection;
import com.ufes.compilador.Model.tokenModel;
import java.util.ArrayList;
import java.util.List;

public class VerificaProcedimento {
    public tokenCollection tokenList;

    /*
     variáveis auxiliares
    */
    public List<Boolean> linhasProcedimentos; // lista para saber as linhas que estão os procedimentos
    public int numLinhas;
    public boolean temProcedimento = false;
    public boolean temFuncao = false;
    public boolean temIdentificador = false;
    public boolean abriuParenteses = false;
    public boolean fechouParenteses = false;


    public VerificaProcedimento(tokenCollection tokenList) {
        this.tokenList = tokenList;
        this.linhasProcedimentos = new ArrayList<Boolean>();
        this.numLinhas = this.tokenList.getLinhas();
        this.initialize_linhasProcedimentos();
        this.mostra_linhasProcedimentos();
        this.verify();
    }
    
    /*
     sobre a lista pra saber se está na linha que possui procedimentos
    */
    public void initialize_linhasProcedimentos() {
        for (int i = 0; i <= numLinhas; i++) {
            this.linhasProcedimentos.add(false);
        }
        
        for (tokenModel tk : tokenList.tokensReverse) {
            if (tk.token.equals("TKN_declarafuncao") || tk.token.equals("TKN_declaraProcedimento")) {
                this.linhasProcedimentos.set(tk.line, true); 
            }
        }
    }
    
    public void mostra_linhasProcedimentos() {
        int line = 0;
        for (boolean b : linhasProcedimentos) {
            System.out.println("linha : " + line + " | procedimento: " + b);
            line += 1;
        }
    }
    
    /*
     função que verifica a linha de procedimento
    */
    public void processaLinhaProcedimento(tokenModel tk) {
        if (tk.token.equals("TKN_declaraProcedimento")) {
            this.temProcedimento = true;
        }
        if (tk.token.equals("TKN_declarafuncao")) {
            this.temFuncao = true;
        }
        if (tk.token.equals("TKN_identificador")) {
            this.temIdentificador = true;
        }
        if (tk.token.equals("TKN_abreParenteses")) {
            this.abriuParenteses = true;
        }
        if (tk.token.equals("TKN_fechaParenteses")) {
            this.fechouParenteses = true;
        }
    }
    
    /*
     mostra os erros conforme o processamento do vetor LinhaProcedimento
    */
    public void mostraErros(int linha) {
        if(this.temProcedimento == true || this.temFuncao == true) {
            if (!this.temIdentificador) {
               new Yyerror(linha, "Identificador não encontrado");
            }
            if (!this.abriuParenteses) {
                new Yyerror(linha, "Não abriu parênteses");
            }
            if (!this.fechouParenteses) {
                new Yyerror(linha, "Não fechou parênteses");
            }
        }
    }
    
    public void verify() {
        for (tokenModel k : tokenList.tokensReverse) {
            if (this.linhasProcedimentos.get(k.line) == true) {
                if (k.line < numLinhas) {
                    System.out.println("Caiu 1");
                    this.processaLinhaProcedimento(k);
                    System.out.println("Saiu 1");
                } else {
                    System.out.println("Caiu 2");
                    this.processaLinhaProcedimento(k);
                    System.out.println("Saiu 2");
                }
            } else {
                this.mostraErros(k.line - 1);
                this.temProcedimento = false;
                this.temFuncao = false;
                this.temIdentificador = false;
                this.abriuParenteses = false;
                this.fechouParenteses = false;
                System.out.println("Saiu 3");
            }
        }
    }
}
