/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;
import java.awt.Color;
import java.awt.Component;
import java.sql.ResultSet;
import models.Tarea;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import models.Proyecto;
import view_dialogs.EditingTask;
/**
 *
 * @author cesargustavo
 */
public class ViewTareas extends javax.swing.JPanel {
        private Tarea tareas;
        private Proyecto proj;
        private DefaultTableModel modelo;
        private ResultSet results;
        private EditingTask edt;
    /**
     * Creates new form ViewTareas
     */
    public ViewTareas(AdminView _frame) {
        initComponents();
        proj = new Proyecto();
        tareas = new Tarea();
        
        edt = new EditingTask(_frame,true);
        edt.setViewTareas(this);
        
        modelo = new DefaultTableModel(){
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
        modelo.addColumn("ID");
        modelo.addColumn("Titulo");
        modelo.addColumn("Descripcion");
        modelo.addColumn("Responsable");
        modelo.addColumn("Email");
        modelo.addColumn("Status");
        modelo.addColumn("Proyecto");
        
        this.jTable1.setModel(modelo);
        
        // COLORES DE LAS ROWS
        this.jTable1.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus, int row, int col) {

                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
                        
                String status = (String)table.getModel().getValueAt(row, 5);
                if (isSelected) {
                    c.setBackground(Color.blue.darker());
                    c.setForeground(Color.white);
                }else{
                    if (col == 5) {
                        c.setForeground(Color.white);
                        switch(status){
                            case "Pendiente":
                                c.setBackground(Color.orange.darker());
                            break;
                            case "Cancelada":
                                c.setBackground(Color.red.darker());
                            break;
                            case "Terminada":
                                c.setBackground(Color.green.darker());
                            break;
                        }
                    }else{
                        c.setBackground(null);
                        c.setForeground(Color.black);
                    }
                }
                return this;
            }   
        });
        
        this.jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    
    public void actualizarbox(){
        this.jComboBox1.removeAllItems();
        results = proj.getAllPending();
        try{
            while(results.next()){
                this.jComboBox1.addItem(results.getString("titulo"));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        if (this.jComboBox1.getItemCount() > 0) {
            this.agregaTarea.setEnabled(true);
            this.txttitulo.setEnabled(true);
            this.txtdescripcion.setEnabled(true);
            this.jComboBox1.setEnabled(true);
        }else{
            this.agregaTarea.setEnabled(false);
            this.txttitulo.setEnabled(false);
            this.txtdescripcion.setEnabled(false);
            this.jComboBox1.setEnabled(false);
        }
    }
    
    public void actualizarTabla(){
        modelo.setRowCount(0);
        results=tareas.getAll();
        try{
            while(results.next()){
                modelo.addRow(new Object[]{
                    results.getString("id"),
                    results.getString("titulo"),
                    results.getString("descripcion"),
                    ( results.getString("u_nombre") != null 
                            && 
                            !results.getString("u_nombre").isEmpty()
                            &&
                            results.getString("u_apellidos") != null
                            &&
                            !results.getString("u_apellidos").isEmpty()
                    ) ? results.getString("u_nombre")+" "+results.getString("u_apellidos") : "No asignado.",
                    (results.getString("u_email") != null && !results.getString("u_email").isEmpty()) ? results.getString("u_email") : " - ",
                    (results.getInt("status") == 1 ? "Pendiente" : (results.getInt("status") == 2) ? "Cancelada" : "Terminada" ),
                    results.getString("p_titulo")
                });
            }
            this.jTable1.setModel(modelo);
        }catch(SQLException e){
            System.out.println(e.getMessage());
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

        jPanel1 = new javax.swing.JPanel();
        PanelAgregarProyecto = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtdescripcion = new javax.swing.JTextField();
        txttitulo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        agregaTarea = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        editarTarea = new javax.swing.JButton();
        detallesTarea = new javax.swing.JButton();

        PanelAgregarProyecto.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel1.setText("Tareas");

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel3.setText("Titulo:");

        txtdescripcion.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        txtdescripcion.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        txttitulo.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        txttitulo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txttitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttituloActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel4.setText("Descripcion:");

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel5.setText("Proyecto:");

        agregaTarea.setBackground(new java.awt.Color(0, 42, 73));
        agregaTarea.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        agregaTarea.setForeground(new java.awt.Color(255, 255, 255));
        agregaTarea.setText("Agregar");
        agregaTarea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregaTareaActionPerformed(evt);
            }
        });

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        editarTarea.setBackground(new java.awt.Color(0, 42, 73));
        editarTarea.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        editarTarea.setForeground(new java.awt.Color(255, 255, 255));
        editarTarea.setText("Editar");
        editarTarea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarTareaActionPerformed(evt);
            }
        });

        detallesTarea.setBackground(new java.awt.Color(0, 42, 73));
        detallesTarea.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        detallesTarea.setForeground(new java.awt.Color(255, 255, 255));
        detallesTarea.setText("Detalles");
        detallesTarea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detallesTareaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelAgregarProyectoLayout = new javax.swing.GroupLayout(PanelAgregarProyecto);
        PanelAgregarProyecto.setLayout(PanelAgregarProyectoLayout);
        PanelAgregarProyectoLayout.setHorizontalGroup(
            PanelAgregarProyectoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelAgregarProyectoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelAgregarProyectoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelAgregarProyectoLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(PanelAgregarProyectoLayout.createSequentialGroup()
                        .addGroup(PanelAgregarProyectoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(PanelAgregarProyectoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(agregaTarea, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelAgregarProyectoLayout.createSequentialGroup()
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtdescripcion, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txttitulo, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 597, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelAgregarProyectoLayout.createSequentialGroup()
                        .addComponent(detallesTarea, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editarTarea, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        PanelAgregarProyectoLayout.setVerticalGroup(
            PanelAgregarProyectoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelAgregarProyectoLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelAgregarProyectoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelAgregarProyectoLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txttitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtdescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PanelAgregarProyectoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(agregaTarea, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelAgregarProyectoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editarTarea, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(detallesTarea, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelAgregarProyecto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(PanelAgregarProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txttituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttituloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttituloActionPerformed

    private void agregaTareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregaTareaActionPerformed
        // TODO add your handling code here:
        if (!"".equals(this.txttitulo.getText()) && !"".equals(this.txtdescripcion.getText())) {
            results = proj.findByTitle(String.valueOf(this.jComboBox1.getSelectedItem()));
            try{
                if (results.first()) {
                    tareas.insert(this.txttitulo.getText(), this.txtdescripcion.getText(),1,results.getInt("id"));
                    JOptionPane.showMessageDialog(this, "Tarea registrada con exito!");
                    this.txttitulo.setText("");
                    this.txtdescripcion.setText("");
                    this.actualizarTabla();
                }
            }catch(SQLException e){
                JOptionPane.showMessageDialog(this, "Error!");
            }
        }else{
            JOptionPane.showMessageDialog(this, "Llenar los campos!");
        }
    }//GEN-LAST:event_agregaTareaActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void editarTareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarTareaActionPerformed
        // TODO add your handling code here:
        if (this.jTable1.getSelectedRowCount() > 0) {
            this.edt.setTaskData(Integer.valueOf( String.valueOf( jTable1.getValueAt(jTable1.getSelectedRow(), 0) ) ));
            this.edt.setVisible(true);
        }else
            JOptionPane.showMessageDialog(this, "Seleccione una tarea!");
    }//GEN-LAST:event_editarTareaActionPerformed

    private void detallesTareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detallesTareaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_detallesTareaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelAgregarProyecto;
    private javax.swing.JButton agregaTarea;
    private javax.swing.JButton detallesTarea;
    private javax.swing.JButton editarTarea;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtdescripcion;
    private javax.swing.JTextField txttitulo;
    // End of variables declaration//GEN-END:variables
}
