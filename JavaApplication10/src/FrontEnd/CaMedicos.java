/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package FrontEnd;

import BackEnd.Conexao;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class CaMedicos extends javax.swing.JDialog {
    
    private Conexao c;

   
    public CaMedicos() {
        initComponents();
        this.setModal(true);
        this.c = new Conexao();
        this.GetListarMedicos();
    }
    
        private void GetListarMedicos() {
        DefaultTableModel d = (DefaultTableModel) grdMedicos.getModel();
        
        //Limpar linhas
        while(d.getRowCount() > 0)
            d.removeRow(0);
        
        //Definir SQL
        this.c.setResultSet("SELECT * FROM Medico");
        
        //Mostrar resultado
        try {
            if (this.c.getResultSet().first()) {
                do {
                    d.addRow(
                       new Object[] {
                           this.c.getResultSet().getString("CodMedico") ,
                           this.c.getResultSet().getString("Nome")
                       }
                    );                 
                } while(this.c.getResultSet().next());
            }
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollMedicos = new javax.swing.JScrollPane();
        grdMedicos = new javax.swing.JTable();
        btnCadastrar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Medicos");

        grdMedicos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Código", "Medico"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollMedicos.setViewportView(grdMedicos);

        btnCadastrar.setText("Novo");
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollMedicos, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollMedicos, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCadastrar)
                    .addComponent(btnExcluir)
                    .addComponent(btnEditar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        // TODO add your handling code here:
        new CaMedicosEntrada(-1).setVisible(true);
        this.GetListarMedicos();
    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
        if (grdMedicos.getRowCount() > 0) {
            if (grdMedicos.getSelectedRowCount() > 0) {
                String CodMedico = String.valueOf(grdMedicos.getValueAt(grdMedicos.getSelectedRow(), 0));
                new CaMedicosEntrada(Integer.valueOf(CodMedico)).setVisible(true);
                this.GetListarMedicos();
            }
            else
            JOptionPane.showMessageDialog(this, "Selecionar um medico");
        }
        else
        JOptionPane.showMessageDialog(this, "Não existem medicos cadastrados");

    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        // TODO add your handling code here:
        if (grdMedicos.getRowCount() > 0) {
            if (grdMedicos.getSelectedRowCount() > 0) {
                if (JOptionPane.showConfirmDialog(this,
                    "Confirmar exclusão do medico?",
                    "Excluir",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                String CodMedico = String.valueOf(grdMedicos.getValueAt(grdMedicos.getSelectedRow(), 0));
                this.c.SQLExecute("DELETE FROM Medico WHERE CodMedico = " + CodMedico);
                this.GetListarMedicos();
            }
        }
        else
        JOptionPane.showMessageDialog(this, "Selecionar um medico");
        }
        else
        JOptionPane.showMessageDialog(this, "Não existem medicos cadastrados");
    }//GEN-LAST:event_btnExcluirActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JTable grdMedicos;
    private javax.swing.JScrollPane jScrollMedicos;
    // End of variables declaration//GEN-END:variables
}
