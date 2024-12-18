/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.fei.projetocliente.projetocliente;

import DAO.Controller;
import entity.Transacoes;
import java.util.List;
import javax.swing.DefaultListModel;

/**
 *
 * @author ValterNetto
 */
public class Extrato extends javax.swing.JFrame {

    /**
     * Creates new form Extrato
     */
    public Extrato() {
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        atualizarButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listExtrato = new javax.swing.JList<>();
        textCPF = new javax.swing.JTextField();
        voltarButton1 = new javax.swing.JButton();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DC BANK - EXTRATO");
        setBackground(new java.awt.Color(255, 255, 255));

        atualizarButton.setBackground(new java.awt.Color(102, 0, 255));
        atualizarButton.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        atualizarButton.setForeground(new java.awt.Color(255, 255, 255));
        atualizarButton.setText("ATUALIZAR");
        atualizarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atualizarButtonActionPerformed(evt);
            }
        });

        listExtrato.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "NOME: FULANO ", " ", "CPF: 3165462132", " ", "**** CONTA CORRENTE ****", " ", "DATA: 26/10/2023 17:10 + 600.00 TARIFA: 0.00 SALDO 600.00", "DATA: 26/10/2023 17:10  - 50.00   TARIFA: 0.50 SALDO 549.50", "DATA: 26/10/2023 17:10 + 100.00 TARIFA: 0.00 SALDO 649.50", " " };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(listExtrato);

        textCPF.setToolTipText("");
        textCPF.setBorder(javax.swing.BorderFactory.createTitledBorder("CPF"));

        voltarButton1.setBackground(new java.awt.Color(102, 0, 255));
        voltarButton1.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        voltarButton1.setForeground(new java.awt.Color(255, 255, 255));
        voltarButton1.setText("VOLTAR");
        voltarButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
                    .addComponent(atualizarButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textCPF)
                    .addComponent(voltarButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(textCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(atualizarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(voltarButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt) {                                    
        // TODO add your handling code here:
         
    }
    private void atualizarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atualizarButtonActionPerformed
        String cpf = textCPF.getText();
        DefaultListModel<String> listModel = new DefaultListModel<>();
        List<Transacoes> transacoes = Controller.pesquisaExtrato(cpf);
        for(Transacoes transacao : transacoes){
            String item = "CPF: " + transacao.getMovimento() + "  Tipo de Valor : " + transacao.getValor() + "  Tarifa: " + transacao.getTarifa() + " Saldo: " + transacao.getSaldo() + " Data: " + transacao.getData() + " Hora: " + transacao.getHora();
            listModel.addElement(item);
        }
        listExtrato.setModel(listModel);
    }//GEN-LAST:event_atualizarButtonActionPerformed

    private void voltarButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarButton1ActionPerformed
        TelaPrincipal telaPrincipal = new TelaPrincipal();
        telaPrincipal.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_voltarButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Extrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Extrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Extrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Extrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Extrato().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton atualizarButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JList<String> listExtrato;
    private javax.swing.JTextField textCPF;
    private javax.swing.JButton voltarButton1;
    // End of variables declaration//GEN-END:variables
}
