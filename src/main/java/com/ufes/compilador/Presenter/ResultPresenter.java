package com.ufes.compilador.Presenter;

import com.ufes.compilador.Model.errorCollection;
import com.ufes.compilador.Model.errorModel;
import com.ufes.compilador.Model.tokenCollection;
import com.ufes.compilador.Model.tokenModel;
import com.ufes.compilador.View.ResultView;
import javax.swing.table.DefaultTableModel;

public class ResultPresenter {
    private ResultView view; 

    public ResultPresenter() {
        this.view = new ResultView();
        this.atualizaTabelaTokens();
        this.atualizaTabelaErrors();
        this.view.setVisible(true);
    }
    
    private void atualizaTabelaTokens() {
        try {
            DefaultTableModel modelo = (DefaultTableModel) this.view.getTokensModel();
            modelo.setNumRows(0);

            
            for(tokenModel token: new tokenCollection().tokensReverse) {
                modelo.addRow(new Object[]{
                    token.line,
                    token.token,
                    token.text,
                });
            }
        } catch(RuntimeException e) {
            System.out.println("Erro ao atualizar tabela: " + e);
        }
    }
    
    private void atualizaTabelaErrors() {
        try {
            DefaultTableModel modelo = (DefaultTableModel) this.view.getErrorsModel();
            modelo.setNumRows(0);

            for(errorModel error: new errorCollection().errorsReverse) {
                modelo.addRow(new Object[]{
                    error.line,
                    error.description,
                });
            }
        } catch(RuntimeException e) {
            System.out.println("Erro ao atualizar tabela: " + e);
        }
    } 
}
