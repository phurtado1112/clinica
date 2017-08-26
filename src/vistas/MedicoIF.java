package vistas;

import clases.Medico;
import clases.Especialidad;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import util.Conecta;
import util.Valida;

/**
 *
 * @author Pablo Hurtado
 */
public class MedicoIF extends javax.swing.JInternalFrame {
    DefaultTableModel model;
    DefaultComboBoxModel<String> modeloCombo;
    Conecta cnx = new Conecta();
    Valida va = new Valida();
    Statement stm;
    ResultSet rs;
    PreparedStatement ps;    
    Especialidad es = new Especialidad();
    Medico m = new Medico();

    /**
     * Creates new form MedicoIF
     */
    public MedicoIF() {
        initComponents();
        this.llenarCB();
        cnx.Conecta();
        Deshabilitar();
        LlenarTabla();
        BotonesInicio();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    public void limpiar(){
        txtNombre.setText("");
        txtApellido.setText("");
        cbxEspecialidad.removeAllItems();
    }
    
    private void Deshabilitar() {
        txtNombre.setEnabled(false);
        txtApellido.setEnabled(false);
        cbxEspecialidad.removeAllItems();
        cbxEspecialidad.setEnabled(false);
    }
    
    public void Habilitar(){
        txtNombre.setEnabled(true);
        va.SoloLetras(txtNombre);
        va.SeleccionarTodo(txtNombre);
        txtNombre.requestFocus();
        txtApellido.setEnabled(true);
        va.SoloLetras(txtApellido);
        va.SeleccionarTodo(txtApellido);
        cbxEspecialidad.setEnabled(true);
    }
    
    private void BotonesInicio(){
        btnNuevo.setEnabled(true);
        btnActualizar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnGuardar.setEnabled(false);
        btnCancelar.setEnabled(false);
    }
    
    private void BotonesNuevo(){
        btnNuevo.setEnabled(false);
        btnActualizar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnGuardar.setEnabled(true);
        btnCancelar.setEnabled(true);
    }
    
    private void BotonesClick(){
        btnNuevo.setEnabled(false);
        btnGuardar.setEnabled(false);
        btnActualizar.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnEliminar.setEnabled(true);
    }
    
    public final void llenarCB() {
        cnx.Conecta();
        try {            
            modeloCombo = new DefaultComboBoxModel<String>();            
            String SQL = "select descripcion_especialidad from especialidad";
            stm = cnx.conn.createStatement();            
            rs = stm.executeQuery(SQL);
            while (rs.next()) {
                modeloCombo.addElement(rs.getString("descripcion_especialidad"));
            }
            rs.close();
            cbxEspecialidad.setModel(modeloCombo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al Llenar el CB de Especialidad: " + ex.getMessage());
        } finally {
            cnx.Desconecta();
        }
    }
    //Llena con datos el JTable con un consulta
    private void LlenarTabla() {
        int[] anchos = {30, 200, 200}; 
        cnx.Conecta();
        try{
            String [] titulos ={"ID","Médico","Especialidad"};
            String SQL = "Select idmedico, concat(nombre,' ',apellido) as medico, especialidad from medico_view";
            model = new DefaultTableModel(null, titulos);
            stm = cnx.conn.createStatement();
            rs = stm.executeQuery(SQL);
            String [] fila = new String[3];
            while(rs.next()){
                fila[0] = rs.getString("idmedico");
                fila[1] = rs.getString("medico");
                fila[2] = rs.getString("especialidad");
                model.addRow(fila);
            }
            
            //Dimensiona el ancho de las columnas de la tabla
            tblMedico.setModel(model);
            for(int i = 0; i < tblMedico.getColumnCount(); i++) {
                tblMedico.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            }
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al Llenar la tabla de Médicos: " + e.getMessage());
        } finally {
            cnx.Desconecta();
        }
    }
    
    private boolean validar(){
        boolean val=false;
        if(txtNombre.getText().trim().length()==0){ //Valida campo Nombre
            JOptionPane.showMessageDialog(this, "El campo de Nombre no puede estar vacío");
            val = false;
        } else if(txtApellido.getText().trim().length()==0) { //Valida campo Apellido
            JOptionPane.showMessageDialog(this, "El campo de Apellido no puede estar vacío");
        } else {
            val=true;
        }       
        return val;
    }
    
    /*
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cbxEspecialidad = new javax.swing.JComboBox<String>();
        jLabel3 = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMedico = new javax.swing.JTable();
        btnNuevo = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setTitle("Médico");
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        setVisible(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Médico"));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Nombre");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Especialidad");

        cbxEspecialidad.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Apellido");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
                    .addComponent(cbxEspecialidad, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtApellido))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(9, 9, 9))
        );

        tblMedico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        tblMedico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMedicoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblMedico);
        if (tblMedico.getColumnModel().getColumnCount() > 0) {
            tblMedico.getColumnModel().getColumn(0).setMinWidth(50);
            tblMedico.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblMedico.getColumnModel().getColumn(0).setMaxWidth(55);
        }

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnActualizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar)
                    .addComponent(btnSalir)
                    .addComponent(btnActualizar)
                    .addComponent(btnNuevo)
                    .addComponent(btnEliminar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        Habilitar();
        limpiar();
        llenarCB();
        BotonesNuevo();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        if (validar()==true){            
        int i = JOptionPane.showConfirmDialog(null, "Desea Actualizar?","Confirmar",
            JOptionPane.OK_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE);
            if(i==JOptionPane.OK_OPTION){
                int fila = tblMedico.getSelectedRow();
                m.setNombre(this.txtNombre.getText().trim());
                m.setApellido(this.txtApellido.getText().trim());
                m.setIdespecialidad(es.consultaIdespecialidad(this.cbxEspecialidad.getSelectedItem().toString().trim()));
                m.setIdmedico(Integer.parseInt(this.tblMedico.getValueAt(fila, 0).toString()));
                m.ActualizarMedico();
            }
            LlenarTabla();
            limpiar();
            Deshabilitar();
            BotonesInicio();
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int i = JOptionPane.showConfirmDialog(null, "Desea Eliminar?","Confirmar",
            JOptionPane.OK_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE);
        if(i==JOptionPane.OK_OPTION){
            int fila = tblMedico.getSelectedRow();
            m.setIdmedico(Integer.parseInt(tblMedico.getValueAt(fila, 0).toString()));
            m.EliminarMedico();
        limpiar();
        Deshabilitar();
        LlenarTabla();
        BotonesInicio();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (validar()==true){        
        int i = JOptionPane.showConfirmDialog(null, "Desea Guardar?","Confirmar",
            JOptionPane.OK_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE);
        if(i==JOptionPane.OK_OPTION){
            m.setNombre(txtNombre.getText().trim());
            m.setApellido(this.txtApellido.getText().trim());
            m.setIdespecialidad(es.consultaIdespecialidad(this.cbxEspecialidad.getSelectedItem().toString().trim()));            
            m.GuardarMedico();
        }
        LlenarTabla();
        limpiar();
        Deshabilitar();
        BotonesInicio();
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpiar();
        Deshabilitar();
        LlenarTabla();
        BotonesInicio();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        int i = JOptionPane.showConfirmDialog(null, "Desea Salir?","Confirmar",
            JOptionPane.OK_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE);
        if(i==JOptionPane.OK_OPTION){
            this.doDefaultCloseAction();
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void tblMedicoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMedicoMouseClicked
        if (evt.getButton()==1){
            int fila = tblMedico.getSelectedRow();
            Habilitar();
            llenarCB();
            BotonesClick();
            cnx.Conecta();
            try{                                               
                String SQL = "Select * from medico where idmedico = " + tblMedico.getValueAt(fila, 0);
                stm = cnx.conn.createStatement();
                rs = stm.executeQuery(SQL);
                
                rs.next();
                txtNombre.setText(rs.getString("nombre"));                
                cbxEspecialidad.setSelectedItem(es.consultaEspecialidad(rs.getInt("iduniversidad")));
            } catch(SQLException | HeadlessException e){
                JOptionPane.showMessageDialog(null, "Error Facultad Mouse Cliked: " + e.getMessage());
            } finally {
                cnx.Desconecta();
            }
        }
    }//GEN-LAST:event_tblMedicoMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cbxEspecialidad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblMedico;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
