package com.ufes.compilador.Model;

public class errorModel {
    public int line;
    public String description;
    
    public errorModel(int line, String description) {
        this.line = line;
        this.description = description;
    }
}
