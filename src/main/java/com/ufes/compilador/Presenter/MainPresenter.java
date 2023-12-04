package com.ufes.compilador.Presenter;

import com.ufes.compilador.DAO.errorDAO;
import com.ufes.compilador.DAO.tokenDAO;
import com.ufes.compilador.JFlex.YylexTest;
import com.ufes.compilador.Semantic.RunSemantic;
import com.ufes.compilador.Syntatic.RunSyntatic;
import com.ufes.compilador.View.MainView;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class MainPresenter {
    private MainView view; // tela principal

    public MainPresenter() {
        this.view = new MainView(); // instanciando a tela principal
        
        this.view.getCompileButton().addActionListener((e) -> {
            new errorDAO().resetaArquivo();
            new tokenDAO().resetaArquivo();
            this.setArquivoParaCompilar();
            new YylexTest();
            new RunSyntatic();
            new RunSemantic();
            new ResultPresenter();
            this.view.makeErrorLinesRead();
        });

        this.view.getImportButton().addActionListener((e) -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File(System.getProperty("user.dir") + "/test files"));
            chooser.showOpenDialog(null);
            File f = chooser.getSelectedFile();
            String filename = f.getAbsolutePath();
            try {
                FileReader reader = new FileReader(filename);
                BufferedReader br = new BufferedReader(reader);
                this.view.getJTextArea1().read(br, null);
                br.close();
                this.view.getJTextArea1().requestFocus();
                this.view.updateLineNumbers();
                this.view.removeRedLines();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        });
        
        this.view.setVisible(true);
    }
    
    /*
     joga todo o código no arquivo que será lido para a análise léxica
    */
    public void setArquivoParaCompilar() {
        try{
            File file = new File(System.getProperty("user.dir") + "/src/test/data/input.txt");
            PrintWriter writer = new PrintWriter(file);
            writer.print(this.view.getTextToCompile());
            writer.close();
        }
        catch(Exception e){
            System.out.println("Erro ao salvar código no arquivo para compilar: " + e);
        }
    }
}
