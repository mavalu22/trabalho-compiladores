package com.ufes.compilador.Model;

import static com.ufes.compilador.Semantic.Escopo.token_funcao;
import static com.ufes.compilador.Semantic.Escopo.token_inicio;
import static com.ufes.compilador.Semantic.Escopo.token_procedimento;

import java.util.ArrayList;
import java.util.List;

public class vetorCollection {
    public tokenCollection tokenList;
    public List<vetorModel> vetores;
    
    public vetorCollection(tokenCollection tokenList) {
        this.tokenList = tokenList;
        this.vetores = new ArrayList<vetorModel>();
        this.fillVetores();
    }
    
    public void printaVetores() {
        for (vetorModel vm : vetores) {
            System.out.println(vm.name + "["+vm.startSize+".."+vm.maxSize+"]\n");
        }
    }
    
    /**
     * retorna se o index acessado no vetor - seja uma variável ou constante -
     * é válido
     */
    public boolean verifyIndex(String nome, int indice) {
        boolean verify = false;
        for (vetorModel vetor : vetores) {
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
    
    /**
     * preenche os vetores com os declarados em VARIAVEL ou CONSTANTE
     */
    public void fillVetores() {
        
        /*
         variáveis auxiliares verificar se a sequencia de tokens representa um vetor
        */
        boolean isDeclaringVariables = false;
        String name = "";
        boolean temDoisPontos = false;
        boolean declarouVetor = false;
        boolean abriuColchetes = false;
        int startSize = -1;
        int maxSize = -1;
        boolean hasMaxSize = false;
        
        for(tokenModel tk : tokenList.tokensReverse) {
            if (tk.token.equals("TKN_iniciaVariavel") || tk.token.equals("TKN_iniciaConstante")) {
                isDeclaringVariables = true;
            }
            if (tk.token.equals(token_inicio) || tk.token.equals(token_procedimento) || tk.token.equals(token_funcao)) {
                isDeclaringVariables = false;
            }
            if (isDeclaringVariables == true ) {
                if (tk.token.equals("TKN_identificador")) {
                    name = tk.text;
                }
                if (name.length() > 0 && tk.token.equals("TKN_doisPontos")) {
                    temDoisPontos = true;
                }
                if (temDoisPontos = true && tk.token.equals("TKN_declaraVetor")) {
                    declarouVetor = true;
                }
                if (declarouVetor = true && tk.token.equals("TKN_abreColchetes")) {
                    abriuColchetes = true;
                }
                if (abriuColchetes = true && tk.token.equals("TKN_tipoInteiro")) {
                    startSize = Integer.parseInt(tk.text);
                }
                if (!hasMaxSize && tk.token.equals("TKN_tipoInteiro")) {
                    maxSize = Integer.parseInt(tk.text);
                    hasMaxSize = true;
                }
                if (maxSize > -1 && tk.token.equals("TKN_fechaColchetes")) {
                    vetores.add(new vetorModel(name,maxSize,startSize));
                    name = "";
                    temDoisPontos = false;
                    declarouVetor = false;
                    abriuColchetes = false;
                    startSize = -1;
                    maxSize = -1;
                    hasMaxSize = false;
                }
            }
        }
    }
}
