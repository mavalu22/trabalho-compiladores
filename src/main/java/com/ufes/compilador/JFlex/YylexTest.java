package com.ufes.compilador.JFlex;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class YylexTest {
    
    public YylexTest() {
        try {
            this.output();
        } catch(Exception e) {
            System.out.println("Erro ao ler arquivo para gerar análise léxica: " + e);
        }
    }
    
    /*
     pega o codigo salvo no arquivo e joga para analise lexica
    */
    public void output() throws Exception {
      File inputFile = openFile("src/test/data/input.txt");

      String[] argv = new String[] {inputFile.getPath()};

      Yylex.main(argv);
    }

    /*
     funcao auxiliar para ler o arquivo
    */
    private static File openFile(String pathName) throws IOException {
      String path = pathName;
      File file = new File(path);
      if (!file.isFile()) {
        throw new FileNotFoundException(path);
      }
      return file;
    }
}
