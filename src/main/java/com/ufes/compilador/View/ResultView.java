package com.ufes.compilador.View;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class ResultView extends javax.swing.JFrame {

    private DefaultTableModel tokensTableModel = new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "Linha", "Token", "Texto"
        }
    );
    private DefaultTableModel errorsTableModel = new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "Linha", "Erro"
        }
    );
    
    public ResultView() {
        initComponents();
        this.mudaEstiloTabelaTokens();        
        this.mudaEstiloTabelaErrors();
    }
    
    private void mudaEstiloTabelaTokens() {
        // cor do cabeçalho
        JTableHeader header = this.getTabelaTokens().getTableHeader();
        header.setBackground(Color.black);
        header.setForeground(Color.white);
        
        // alinhamento do cabeçalho
        ((DefaultTableCellRenderer)this.getTabelaTokens().getTableHeader()
                .getDefaultRenderer()).setHorizontalAlignment(JLabel.LEFT);
        
        // alinhando as células
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(JLabel.LEFT);
        this.getTabelaTokens().getColumnModel().getColumn(0).setCellRenderer(leftRenderer);
        this.getTabelaTokens().getColumnModel().getColumn(1).setCellRenderer(leftRenderer);
        this.getTabelaTokens().getColumnModel().getColumn(2).setCellRenderer(leftRenderer);
    }
    
    private void mudaEstiloTabelaErrors() {
        // cor do cabeçalho
        JTableHeader header = this.getTabelaErrors().getTableHeader();
        header.setBackground(Color.black);
        header.setForeground(Color.white);
        
        // alinhamento do cabeçalho
        ((DefaultTableCellRenderer)this.getTabelaTokens().getTableHeader()
                .getDefaultRenderer()).setHorizontalAlignment(JLabel.LEFT);
        
        // alinhando as células
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(JLabel.LEFT);
        this.getTabelaErrors().getColumnModel().getColumn(0).setCellRenderer(leftRenderer);
        this.getTabelaErrors().getColumnModel().getColumn(1).setCellRenderer(leftRenderer);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableResultado = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableLexica = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(789, 557));
        setMinimumSize(new java.awt.Dimension(789, 557));
        setPreferredSize(new java.awt.Dimension(789, 557));
        setSize(new java.awt.Dimension(789, 557));

        jTableResultado.setModel(this.errorsTableModel);
        jScrollPane1.setViewportView(jTableResultado);

        jLabel1.setText("Análise léxica");

        jLabel2.setText("Erros");

        jTableLexica.setModel(this.tokensTableModel);
        jScrollPane2.setViewportView(jTableLexica);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 782, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {}
        });
    }

    public DefaultTableModel getTokensModel() {
        return tokensTableModel;
    }
    
    public DefaultTableModel getErrorsModel() {
        return errorsTableModel;
    }
    
    public JTable getTabelaTokens() {
        return this.jTableLexica;
    }
    
    public JTable getTabelaErrors() {
        return this.jTableResultado;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableLexica;
    private javax.swing.JTable jTableResultado;
    // End of variables declaration//GEN-END:variables
}
