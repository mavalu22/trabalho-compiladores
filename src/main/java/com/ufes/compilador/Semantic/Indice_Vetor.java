package com.ufes.compilador.Semantic;

import com.ufes.compilador.JFlex.Yyerror;
import com.ufes.compilador.Model.tokenCollection;
import com.ufes.compilador.Model.tokenModel;
import com.ufes.compilador.Model.vetorCollection;
import com.ufes.compilador.Model.vetorModel;
import static com.ufes.compilador.Semantic.Escopo.token_inicio;
import java.util.ArrayList;
import java.util.List;

public class Indice_Vetor {
    public tokenCollection tokenList;
    public vetorCollection vetores;
    
    /*
      variaveis auxiliares para verificações no bloco
    */
    public boolean estaEmBloco = false;
    public boolean estaEmProcedimento = false;
    public String nome = "";
    public boolean abriuColchetes = false;
    public boolean achouIndex = false;
    public int index = -1;
    
    /*
     variaveis auxiliaries para preencher os vetores que são parâmetros procedimentos/funções
    */
    List<vetorModel> vetoresProcedimento;
    public boolean isBlocoStarted = false;
    public String name = "";
    public boolean temDoisPontos = false;
    public boolean declarouVetor = false;
    public boolean abriuColchetesProcedimento = false;
    public int startSize = -1;
    public int maxSize = -1;
    public boolean hasMaxSize = false;
    
    /*
      variaveis auxiliares para verificações nos procedimentos/funções
    */
    public boolean p_estaEmBloco = false;
    public boolean p_estaEmProcedimento = false;
    public String p_nome = "";
    public boolean p_abriuColchetes = false;
    public boolean p_achouIndex = false;
    public int p_index = -1;
    
    public Indice_Vetor(tokenCollection tokenList) {
        this.tokenList = tokenList;
        this.vetores = new vetorCollection(tokenList);
        this.vetoresProcedimento = new ArrayList<vetorModel>();
        this.verify();
    }
    
    /*
     verifica se, no bloco principal, acessou um index invalido de um vetor
    */
    public void verificaBloco(tokenModel tk) {
        if (tk.token.equals("TKN_identificador")) {
            this.nome = tk.text;
        }
        if (this.nome.length() > 0 && tk.token.equals("TKN_abreColchetes")) {
            this.abriuColchetes = true;
        }
        if (this.abriuColchetes = true && tk.token.equals("TKN_tipoInteiro")) {
            this.index = Integer.parseInt(tk.text);
        }
        if (this.index > -1 && tk.token.equals("TKN_fechaColchetes")) {
            if (this.vetores.verifyIndex(this.nome, this.index) == false) {
                new Yyerror(tk.line, "Acessando um índice inexistente no vetor");
            }
            this.nome = "";
            this.abriuColchetes = false;
            this.achouIndex = false;
            this.index = -1;
        }
    }
    
    /*
     retorna se o index de um vetor usado no procedimento é válido ou não
     tem uma função similar para esse retorno, porém verificação do bloco principal
     em vetorCollection.java
    */
    public boolean p_verifyIndex(String nome, int indice) {
        boolean verify = false;
        for (vetorModel vetor : vetoresProcedimento) {
            if (vetor.name.equals(nome)) {
                if (indice > vetor.maxSize || indice < vetor.startSize) {
                    verify = false;
                } else if (indice <= vetor.maxSize && indice >= vetor.startSize) {
                    verify = true;
                }
            }
        }
        return verify;
    }
    
    /*
     faz preenche a lista dos vetores usados como parâmetros em procedimento
     e verifica se foram usados com index válidos
    */
    public void verificaProcedimento(tokenModel tk) {  
        if (tk.token.equals(token_inicio)) {
            this.isBlocoStarted = true;
        }
        if (!this.isBlocoStarted) {
            if (tk.token.equals("TKN_identificador")) {
                this.name = tk.text;
            }
            if (this.name.length() > 0 && tk.token.equals("TKN_doisPontos")) {
                this.temDoisPontos = true;
            }
            if (this.temDoisPontos = true && tk.token.equals("TKN_declaraVetor")) {
                this.declarouVetor = true;
            }
            if (this.declarouVetor = true && tk.token.equals("TKN_abreColchetes")) {
                this.abriuColchetesProcedimento = true;
            }
            if (this.abriuColchetesProcedimento = true && tk.token.equals("TKN_tipoInteiro")) {
                this.startSize = Integer.parseInt(tk.text);
            }
            if (!this.hasMaxSize && tk.token.equals("TKN_tipoInteiro")) {
                this.maxSize = Integer.parseInt(tk.text);
                this.hasMaxSize = true;
            }
            if (this.maxSize > -1 && tk.token.equals("TKN_fechaColchetes")) {
                this.vetoresProcedimento.add(new vetorModel(name,maxSize,startSize));
                this.name = "";
                this.temDoisPontos = false;
                this.declarouVetor = false;
                this.abriuColchetesProcedimento = false;
                this.startSize = -1;
                this.maxSize = -1;
                this.hasMaxSize = false;
            }
        } else {
            if (tk.token.equals("TKN_identificador")) {
                this.p_nome = tk.text;
            }
            if (this.p_nome.length() > 0 && tk.token.equals("TKN_abreColchetes")) {
                this.p_abriuColchetes = true;
            }
            if (this.p_abriuColchetes = true && tk.token.equals("TKN_tipoInteiro")) {
                this.p_index = Integer.parseInt(tk.text);
            }
            if (this.p_index > -1 && tk.token.equals("TKN_fechaColchetes")) {
                if (this.p_verifyIndex(this.p_nome, this.p_index) == false) {
                    new Yyerror(tk.line, "Acessando um índice inexistente no vetor");
                }
                this.p_nome = "";
                this.p_abriuColchetes = false;
                this.p_achouIndex = false;
                this.p_index = -1;
            }
        }
    }
    
    /*
     percorre os tokens e, conforme estamos no bloco principal ou em um procediment
     faz as chamadas necessárias para verificar se os index usados nos vetores são válidos
    */
    public void verify() {                
        for(tokenModel tk : tokenList.tokensReverse) {
            if (tk.token.equals("TKN_declarafuncao") || tk.token.equals("TKN_declaraProcedimento")) {
                this.estaEmProcedimento = true;
            }
            if (!estaEmProcedimento && tk.token.equals("TKN_iniciaBloco")) {
                this.estaEmBloco = true;
            }
            if (tk.token.equals("TKN_terminaBloco")) {
                if (this.estaEmBloco == true) {
                    this.estaEmBloco = false;
                } else {
                    this.estaEmProcedimento = false;
                    this.isBlocoStarted = false;
                    this.vetoresProcedimento = new ArrayList<vetorModel>();
                }
            }
            if (this.estaEmBloco == true) {
                this.verificaBloco(tk);
            } else {
                if (this.estaEmProcedimento == true) {
                    this.verificaProcedimento(tk);
                } else {
                    this.nome = "";
                    this.abriuColchetes = false;
                    this.achouIndex = false;
                    this.index = -1;
                }
            }
        }
    }
}
