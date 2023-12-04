package com.ufes.compilador.Semantic;

import com.ufes.compilador.JFlex.Yyerror;
import com.ufes.compilador.Model.tokenCollection;
import com.ufes.compilador.Model.tokenModel;
import com.ufes.compilador.Model.variavelCollection;
import com.ufes.compilador.Model.variavelModel;
import static com.ufes.compilador.Semantic.Escopo.token_funcao;
import static com.ufes.compilador.Semantic.Escopo.token_inicio;
import static com.ufes.compilador.Semantic.Escopo.token_procedimento;
import java.util.ArrayList;
import java.util.List;

public class ErroDeTipo {
    public tokenCollection tokenList;
    public variavelCollection variaveis;
    
    /*
     variáveis auxiliáres para saber se, ao percorrer os tokens, estamos
     em um procedimento/função ou no bloco principal
    */
    public boolean estaEmProcedimento = false;
    public boolean estaEmBlocoDeProcedimento = false;
    
    /*
     variaveis auxiliares para detectar o uso
     de variaveis e constantes e verificar seu tipo associado
     no bloco principal
    */
    public boolean estaEmBloco = false;
    public String nome = "";
    public boolean passouDoIgual = false;

    
    /*
     variaveis auxiliares para detectar o uso
     de variaveis e constantes e verificar seu tipo associado
     em uma função/procedimento
    */
    List<variavelModel> p_variaveis;
    public String p_nome = "";
    public boolean p_passouDoIgual = false;
    
    public ErroDeTipo(tokenCollection tokenList) {
        this.tokenList = tokenList;
        this.variaveis = new variavelCollection(tokenList);
        this.p_fillVariaveis();
        this.verify();
    }
    
    /*
     retorna se a variável/constante está contida na lista de variáveis
     passadas por parâmetro em uma função/procedimento
    */
    public boolean p_possuiVariavel(String v) {
        boolean possui = false;
        for (variavelModel vm : p_variaveis) {
            if (vm.name.equals(v)) {
                possui = true;
            }
        }
        return possui;
    }
    
    /*
     retorna a compatibilidade da variável/constante nos parâmetros de uma procedimento
     com seu tipo atribuido
    */
    public boolean p_tipoCompativel(String nome, String tipo) {
        boolean compativel = false;
        for (variavelModel vm : p_variaveis) {
            if (vm.name.equals(nome) && vm.type.equals(tipo) || tipo.equals("caracter") && vm.type.equals("caracteres")) {
                compativel = true;
            }
        }
        return compativel;
    }
    
    /*
     percorre os tokens e quando encontra um identificador associado a um "="
     verifica se: 1) o identificador é uma variável contida em this.variaveis
     2) se o texto após o "=" é compatível com o tipo dessa variável
     3) caso haja uma operação com a variável, se ela é compatível com seu tipo
    */
    public void p_verifyBloco(tokenModel tk) {
        if (tk.token.equals("TKN_identificador") && this.p_possuiVariavel(tk.text)) {
            this.p_nome = tk.text;
            this.p_passouDoIgual = false;
        }
        if (this.p_nome.length() > 0 && tk.token.equals("TKN_igual")) {
            this.p_passouDoIgual = true;
        }
        if (tk.token.equals("TKN_pontoEvirgula")) {
            this.p_nome = "";
            this.p_passouDoIgual = false;
        }
        if (this.p_passouDoIgual == true && this.pegaTipo(tk).length() > 0 && !tk.token.equals("TKN_identificador") && !tk.token.equals("TKN_pontoEvirgula") && !tk.token.equals("TKN_igual") && !tk.token.equals("TKN_doisPontos")) {
            if(!this.p_tipoCompativel(this.p_nome, this.pegaTipo(tk))) {
                new Yyerror(tk.line, "Tipo " + this.pegaTipo(tk) + " do valor " + tk.text + " incompatível associado a variável/constante " + this.nome);
            }
        }
    }

    
    /*
     preenche o vetor com as variaveis e constantes passadas por parâmetros nas
     funções/procedimentos
    */
    public void p_fillVariaveis() {
        this.p_variaveis = new ArrayList<variavelModel>();
        boolean isDeclaringVariables = false;
        String name = "";
        boolean temDoisPontos = false;
        for(tokenModel tk : tokenList.tokensReverse) {
            if (tk.token.equals(token_procedimento) || tk.token.equals(token_funcao)) {
                isDeclaringVariables = true;
            }
            if (tk.token.equals(token_inicio)) {
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
                    p_variaveis.add(new variavelModel(name,tk.text));
                    name = "";
                    temDoisPontos = false;
                }
            }
        }
    }
    
    /*
     retorna o tipo a que a string está se referindo
    */
    public String pegaTipo(tokenModel tk) {
        String type = "";
        if (tk.token.equals("TKN_tipoInteiro")) {
            type = "inteiro";
        }
        if (tk.token.equals("TKN_tipoReal")) {
            type = "real";
        } 
        if (tk.token.equals("TKN_verdadeiro") || tk.token.equals("TKN_falso")) {
            type = "booleano";
        }
        if (tk.token.equals("TKN_identificador")) {
            if (tk.text.length() > 1) {
                type = "caracteres";
            } else if (tk.text.length() == 1){
                type = "caracter";
            }
        }
        return type;
    }
    
    /*
     percorre os tokens e quando encontra um identificador associado a um "="
     verifica se: 1) o identificador é uma variável contida em this.variaveis
     2) se o texto após o "=" é compatível com o tipo dessa variável
     3) caso haja uma operação com a variável, se ela é compatível com seu tipo
    */
    public void verifyBloco(tokenModel tk) {
        if (tk.token.equals("TKN_identificador") && this.variaveis.possuiVariavel(tk.text)) {
            this.nome = tk.text;
        }
        if (this.nome.length() > 0 && tk.token.equals("TKN_igual")) {
            this.passouDoIgual = true;
        }
        if (tk.token.equals("TKN_pontoEvirgula")) {
            this.nome = "";
            this.passouDoIgual = false;
        }
        if (this.passouDoIgual == true && this.pegaTipo(tk).length() > 0 && !tk.token.equals("TKN_identificador") &&  !tk.token.equals("TKN_pontoEvirgula") && !tk.token.equals("TKN_igual") && !tk.token.equals("TKN_doisPontos")) {
            if(!this.variaveis.tipoCompativel(this.nome, this.pegaTipo(tk))) {
                new Yyerror(tk.line, "Tipo " + this.pegaTipo(tk) + " do valor " + tk.text + " incompatível associado a variável/constante " + this.nome);
            }
        }
    }
    
    /*
     percorre os tokens e decide se as verificações serão feitas no bloco principal
     ou numa função/procedimento
    */
    public void verify() {
        for(tokenModel tk : tokenList.tokensReverse) {
            if (tk.token.equals("TKN_declarafuncao") || tk.token.equals("TKN_declaraProcedimento")) {
                this.estaEmProcedimento = true;
            }
            if (tk.token.equals("TKN_iniciaBloco")) {
                if (this.estaEmProcedimento == true) {
                    this.estaEmBlocoDeProcedimento = true;
                    this.estaEmBloco = false;
                }  else {
                    this.estaEmBlocoDeProcedimento = false;
                    this.estaEmBloco = true;
                }
            }
            if (tk.token.equals("TKN_terminaBloco")) {
                this.estaEmBlocoDeProcedimento = false;
                this.estaEmProcedimento = false;
                this.estaEmBloco = false;
            }
            if (this.estaEmBlocoDeProcedimento == true) {
                this.p_verifyBloco(tk);
            }
            if (this.estaEmBloco == true) {
                this.verifyBloco(tk);
            }
        }
    }
}
