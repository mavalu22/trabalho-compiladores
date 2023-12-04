package com.ufes.compilador.Model;

public class vetorModel {
    public int startSize;
    public int maxSize;
    public String name;
    
    public vetorModel(String name, int startSize, int maxSize) {
        this.name = name;
        this.startSize = startSize;
        this.maxSize = maxSize;
    }
}