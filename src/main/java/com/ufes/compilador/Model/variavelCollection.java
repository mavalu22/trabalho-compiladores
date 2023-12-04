package com.ufes.compilador.Model;

import static com.ufes.compilador.Semantic.Escopo.token_funcao;
import static com.ufes.compilador.Semantic.Escopo.token_inicio;
import static com.ufes.compilador.Semantic.Escopo.token_procedimento;
import java.util.ArrayList;
import java.util.List;

public class variavelCollection {
    public tokenCollection tokenList;
    public List<variavelModel> variaveis;
    
    public variavelCollection(tokenCollection tokenList) {
        this.tokenList = tokenList;
        this.variaveis = new ArrayList<variavelModel>();
        this.fillVariaveis();
    }
    
    public void printaVariaveis() {
        for (variavelModel v : variaveis) {
            System.out.println(v.name + " : " + v.type);
        }
    }
    
    /*
     retorna o número de variáveis
    */
    public int getNumVariaveis() {
        return this.variaveis.size();
    }
    
    /*
     retorna o tipo de uma variável cujo nome
     foi passado como parâmetro
    */
    public String getTipoVariavel(String s) {
        String tipo = "";
        for (variavelModel vm : variaveis) {
            if (vm.name.equals(s)) {
                tipo = vm.type;
            }
        }
        return tipo;
    }
    
    /*
     retorna se a variável/constante está contida na lista de variáveis
    */
    public boolean possuiVariavel(String v) {
        boolean possui = false;
        for (variavelModel vm : variaveis) {
            if (vm.name.equals(v)) {
                possui = true;
            }
        }
        return possui;
    }
    
    /*
     retorna se o tipo da variável/constante é compatível com o que está
     no parâmetro    
    */
    public boolean tipoCompativel(String nome, String tipo) {
        boolean compativel = false;
        for (variavelModel vm : variaveis) {
            if (vm.name.equals(nome) && vm.type.equals(tipo) || tipo.equals("caracter") && vm.type.equals("caracteres")) {
                compativel = true;
            }
        }
        return compativel;
    }
    
    /*
     preenche o vetor com as variaveis e constantes
    */
    public void fillVariaveis() {
        
        /*
         variáveis auxiliares verificar se a sequencia de tokens representa uma
         variável ou constante
        */
        boolean isDeclaringVariables = false;
        String name = "";
        boolean temDoisPontos = false;
        
        for(tokenModel tk : tokenList.tokensReverse) {
            if (tk.token.equals("TKN_iniciaVariavel") || tk.token.equals("TKN_iniciaConstante")) {
                isDeclaringVariables = true;
            }
            if (tk.token.equals(token_inicio) || tk.token.equals(token_procedimento) || tk.token.equals(token_funcao)) {
                isDeclaringVariables = false;
            }
            if (isDeclaringVariables == true) {
                if (tk.token.equals("TKN_identificador")) {
                    name = tk.text;
                }
                if (name.length() > 0 && tk.token.equals("TKN_doisPontos")) {
                    temDoisPontos = true;
                }
                if (temDoisPontos = true && tk.token.equals("TKN_tipoVariavel")) {
                    variaveis.add(new variavelModel(name,tk.text));
                    name = "";
                    temDoisPontos = false;
                }
            }
        }
    }
}
