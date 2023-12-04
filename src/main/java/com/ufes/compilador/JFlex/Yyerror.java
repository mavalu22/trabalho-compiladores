package com.ufes.compilador.JFlex;

import com.ufes.compilador.DAO.errorDAO;

public class Yyerror {
    public Yyerror(int line, String description) {
      new errorDAO(line, description);
    }
  
    /*
     adiciona as linhas em branco na 
    */
    public String processLine() {
        return "";
    }

    public void printError(int line, String description) {
      System.out.println("\n-- PRINT ERRO --\n");
      System.out.println("Linha: " + line + "\n");   
      System.out.println("Erro: " + description + "\n");
      System.out.println("-- FIM PRINT ERRO --\n");
    }
}
