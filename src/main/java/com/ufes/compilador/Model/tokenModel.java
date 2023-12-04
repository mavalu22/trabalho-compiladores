package com.ufes.compilador.Model;

public class tokenModel {
    public String text;
    public String token;
    public int line;
    
    public tokenModel(String text, String token, int line) {
        this.text = text;
        this.token = token;
        this.line = line;
    }
    
    public void printToken() {
        System.out.println("\n-- TOKEN --\n");
        System.out.println("Linha: " + line);
        System.out.println("Texto: " + text);
        System.out.println("Token: " + token);
        System.out.println("-- FIM TOKEN --\n");
    }
}
