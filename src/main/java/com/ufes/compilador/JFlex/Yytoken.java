package com.ufes.compilador.JFlex;

import com.ufes.compilador.DAO.tokenDAO;

public class Yytoken {
  public Yytoken(String text, int line, String token) {
    if (token == "TKN_identificador") {
        this.verifyID(text, line, token);
    } else {
        new tokenDAO(text, token, line);
    }
  }
  
  /*
   verifica se o identificador é grande demais ou se tem caracteres especiais
  */
  public void verifyID(String text, int line, String token) {
      if (text.length() > 10) {
          new Yyerror(line, "Identificador não pode ter mais do que 10 caracteres");
      } else {
        if (text.contains("$") || text.contains("%") || text.contains("@") || text.contains("#") || text.contains("!") || text.contains("?")) {
            new Yyerror(line, "Identificador não pode conter caracteres especiais");
        } else {
            new tokenDAO(text, token, line);
        }
      }
  }
  
  public void printToken(String text, String token, int line) {
    System.out.println("\n-- PRINT TOKEN --\n");
    System.out.println("Texto: " + text + "\n");
    System.out.println("Token: " + token + "\n");
    System.out.println("Linha: " + line + "\n");
    System.out.println("-- FIM PRINT TOKEN --\n");
  }
}
