package com.ufes.compilador.DAO;

import com.ufes.compilador.Model.tokenModel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.ArrayUtils;

public class tokenDAO {
    Path caminho;
    
    /*
     salvando um token direto ao instanciar a classe
    */
    public tokenDAO(String text, String token, int line) {
        this.caminho = Paths.get(System.getProperty("user.dir") + "/src/main/java/com/ufes/compilador/DAO/tokens.txt");
        this.salvarToken(text, token, line);
    }

    /*
     instanciando a classe
    */
    public tokenDAO() {
        this.caminho = Paths.get(System.getProperty("user.dir") + "/src/main/java/com/ufes/compilador/DAO/tokens.txt");
    }
    
    public void salvarToken(String text, String token, int line) {
        try{
            String textoEscrita = "\n-- TOKEN --\n" + "Texto: " + text + "\n" + "Token: " + token + "\n" + "Linha: " + line + "\n" + "." + "-- FIM TOKEN --\n";
            byte[] textoLeitura = Files.readAllBytes(caminho);
            byte[] textoEmByte = textoEscrita.getBytes();
            byte[] textoCompleto = ArrayUtils.addAll(textoEmByte, textoLeitura);
            Files.write(caminho, textoCompleto);
        }
        catch(Exception e){
            System.out.println("Erro ao salvar funcionário no arquivo: " + e);
        }
    }
    
    public void resetaArquivo() {
        try {
            File file = new File(System.getProperty("user.dir") + "/src/main/java/com/ufes/compilador/DAO/tokens.txt");
            PrintWriter writer = new PrintWriter(file);
            writer.print("");
            writer.close();
        } catch(Exception e) {
            System.out.println("Erro ao ler arquivo: " + e);
        }
    }
    
    
    // pega a lista de tokens
    public List<tokenModel> getTokens() {
        try {
            if (!Files.isReadable(caminho)) {
                throw new RuntimeException("Não foi possível ler o arquivo");
            }
            
            List<tokenModel> tokens = new ArrayList<tokenModel>();
            String[] splitTokens = new String(Files.readAllBytes(caminho)).split("-- FIM TOKEN --");
            
            if (splitTokens.length > 0) {
                for (int i = 0; i < splitTokens.length; i++) {
                    try {
                        tokens.add(leituraToToken(splitTokens[i]));
                    } catch(Exception e) {
                        System.out.println("Não foi possível converter o token em uma instância: " + e);
                    }
                }
                return tokens;
            } 
            
        } catch(Exception e) {
            System.out.println("Erro ao ler tokens do arquivo: " + e);
        }
        return new ArrayList<tokenModel>();
    }
    
    // transforma a string de um token em uma instância
    public tokenModel leituraToToken(String s) {
        s = s.split("-- TOKEN --\n")[1];
        
        String texto = s.substring(s.indexOf("Texto: "), s.indexOf("\n" + "Token: ")).split("Texto: ")[1];
        String token = s.substring(s.indexOf("Token: "), s.indexOf("\n" + "Linha: ")).split("Token: ")[1];
        int linha = Integer.parseInt(s.substring(s.indexOf("Linha: "), s.indexOf("\n" + ".")).split("Linha: ")[1]);

        return new tokenModel(texto, token, linha);
    }
}
