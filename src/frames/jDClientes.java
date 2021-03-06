/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * jDClientes.java
 *
 * Created on 11/08/2012, 08:10:00 AM
 */
package frames;

import clases.DBConnect;
import clases.Usuario;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.util.HashMap;
import javax.swing.JOptionPane;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;


/**
 *
 * @author Edgar
 */
public class jDClientes extends javax.swing.JDialog {
    Image imagen = getToolkit().getImage(getClass().getResource("/imagenes/global2.png"));
    private String Usuario;
    private String Password;
    private String Host;
    private String Puerto;
    private String DB;
    DefaultTableModel m;
    ResultSet r;
    DBConnect db;
    int i=0;
    String cod_clien;
    Usuario user;
    /** Creates new form jDClientes */
    public jDClientes(java.awt.Frame parent, boolean modal,DBConnect con, Usuario user) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        setIconImage(imagen);
        setTitle("Clientes");
        this.Usuario = con.getUser();
        this.Password = con.getPass();
        this.user = user;
        db=con;
        preparartabla();
        
    }
    
     private void preparartabla() {
         r=null;
         r=db.Listar("*", "cliente", "");
            //String titulos[]={"Cod. Cliente", "Nombre", "Telefono", "Direccion"};
            m =(new javax.swing.table.DefaultTableModel(
                            new Object [][] {

                            },
                            new String [] {
                                "Cod. Cliente", "Nombre", "Telefono", "Direccion"
                            }
                        ) {
                            boolean[] canEdit = new boolean [] {
                                false, false, false, false
                            };

           
                            public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit [columnIndex];
                            }
                        });
            jTableClientes.setModel(m);
            String fila[] = new String[4];
            try {
            while(r.next()){
                fila[0]=r.getString("Id_cliente");
                fila[1]=r.getString("Nom_cliente");
                fila[2]=r.getString("Tel_cliente");
                fila[3]=r.getString("Direc_cliente");
                 m.addRow(fila);
            }
           
        } catch (Exception ex) {
           JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        Vercuentas.setEnabled(false);
        ModificarCliente.setEnabled(false);
        Eliminar_Cliente.setEnabled(false);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jPmenucliente = new javax.swing.JPopupMenu();
        Vercuentas = new javax.swing.JMenuItem();
        ModificarCliente = new javax.swing.JMenuItem();
        Eliminar_Cliente = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jBAddCliente = new javax.swing.JButton();
        JbCancelar = new javax.swing.JButton();
        jBImprimir = new javax.swing.JButton();
        jBModificar = new javax.swing.JButton();
        jTbuscar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableClientes = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLCuentas = new javax.swing.JLabel();

        jPmenucliente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        Vercuentas.setText("Ver Cuentas");
        Vercuentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                VercuentasMouseClicked(evt);
            }
        });
        Vercuentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VercuentasActionPerformed(evt);
            }
        });
        jPmenucliente.add(Vercuentas);

        ModificarCliente.setText("Modificar Cliente");
        ModificarCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ModificarClienteMouseClicked(evt);
            }
        });
        ModificarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarClienteActionPerformed(evt);
            }
        });
        jPmenucliente.add(ModificarCliente);

        Eliminar_Cliente.setText("Eliminar Cliente");
        Eliminar_Cliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Eliminar_ClienteMouseClicked(evt);
            }
        });
        Eliminar_Cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Eliminar_ClienteActionPerformed(evt);
            }
        });
        jPmenucliente.add(Eliminar_Cliente);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(new java.awt.GridLayout(2, 2));

        jBAddCliente.setText("Agregar Cliente");
        jBAddCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAddClienteActionPerformed(evt);
            }
        });
        jPanel1.add(jBAddCliente);

        JbCancelar.setText("Salir");
        JbCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(JbCancelar);

        jBImprimir.setText("Imprimir Listado");
        jBImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBImprimirActionPerformed(evt);
            }
        });
        jPanel1.add(jBImprimir);

        jBModificar.setText("Modificar Cliente");
        jBModificar.setToolTipText("Modificar datos del Cliente");
        jBModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBModificarActionPerformed(evt);
            }
        });
        jPanel1.add(jBModificar);

        jTbuscar.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTbuscarCaretUpdate(evt);
            }
        });
        jTbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTbuscarKeyTyped(evt);
            }
        });

        jLabel1.setText("Buscar:");

        jTableClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod. Cliente", "Nombre", "Telefono", "Direccion"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jPmenucliente, org.jdesktop.beansbinding.ObjectProperty.create(), jTableClientes, org.jdesktop.beansbinding.BeanProperty.create("componentPopupMenu"));
        bindingGroup.addBinding(binding);

        jTableClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableClientesMouseClicked(evt);
            }
        });
        jTableClientes.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTableClientesFocusLost(evt);
            }
        });
        jScrollPane1.setViewportView(jTableClientes);

        jLabel3.setText("Nro. de Facturas a Pagar:");

        jLCuentas.setText("pendientes a cobrar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jLCuentas, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(jLabel1)
                    .addComponent(jTbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLCuentas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBAddClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAddClienteActionPerformed

            new jDnuevocliente(null, true,db,0).setVisible(true);
            jTbuscarCaretUpdate(null);
    }//GEN-LAST:event_jBAddClienteActionPerformed

    private void jTbuscarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTbuscarCaretUpdate
        // TODO add your handling code here:
        int fil, col=1;
        jTableClientes.clearSelection();
        String titulos[]={"Cod. Cliente", "Nombre", "Telefono", "Direccion"};
        m = (new javax.swing.table.DefaultTableModel(
    new Object [][] {

    },
    new String [] {
        "Cod. Cliente", "Nombre", "Telefono", "Direccion"
    }
) {
    boolean[] canEdit = new boolean [] {
        false, false, false, false
    };

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
    }
});
        jTableClientes.setModel(m); r=null;
        r=db.Listar("*", "cliente", " where Id_cliente like \"%"+ jTbuscar.getText()+"%\" or Nom_cliente like \"%"+ jTbuscar.getText()+"%\" ");
        String fila[] = new String[4];
        try {
            while(r.next()){
                fila[0]=r.getString("Id_cliente");
                fila[1]=r.getString("Nom_cliente");
                fila[2]=r.getString("Tel_cliente");
                fila[3]=r.getString("Direc_cliente");
                 m.addRow(fila);
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        Vercuentas.setEnabled(false);
        ModificarCliente.setEnabled(false);
        Eliminar_Cliente.setEnabled(false);
}//GEN-LAST:event_jTbuscarCaretUpdate

    private void jTbuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTbuscarKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTbuscarKeyTyped

    private void JbCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_JbCancelarActionPerformed

    private void jBImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBImprimirActionPerformed
         try{
           r=null;
           r=db.Listar("*", "cliente", "");
          

           
        } catch (Exception ex) {
             //Logger.getLogger(ciudad.class.getName()).log(Level.SEVERE, null, ex);
        }

        JRResultSetDataSource jrRS = new JRResultSetDataSource(r);
        HashMap parameters = new HashMap();

        try{
            URL urlMaestro = getClass().getClassLoader().getResource("reportes/clientes.jasper");
            // Cargamos el reporte
            JasperReport masterReport = null;
            masterReport = (JasperReport) JRLoader.loadObject(urlMaestro);
            JasperPrint masterPrint = null;
            masterPrint = JasperFillManager.fillReport(masterReport,parameters,jrRS);

             JasperViewer ventana = new JasperViewer(masterPrint,false);
            ventana.setTitle("Vista Previa");
            ventana.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
            ventana.setVisible(true);

        }catch(JRException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocurrio un error "+e.toString(),"ATENCION ", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jBImprimirActionPerformed

    private void jTableClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableClientesMouseClicked
//        if(evt.getButton()==MouseEvent.BUTTON1){
//            System.out.println(" HOLO ");
//            Vercuentas.setEnabled(true);
//            ModificarCliente.setEnabled(true);
//            Eliminar_Cliente.setEnabled(true);
//            
//            ResultSet rs  = null;
//            
//            rs = db.Listar("count(Id_cuenta)", "cuenta_cliente", "where Cliente_Id_cliente ='"+jTableClientes.getValueAt(jTableClientes.getSelectedRow(), 0)+"' and Estado_Cuenta= 'no pagado'");
//            try {
//                if(rs.next()){
//                    jLCuentas.setText(rs.getString("count(Id_cuenta)"));
//                }else{
//                    jLCuentas.setText("Sin Cuentas Pendientes");
//                }
//            } catch (SQLException ex) {
//                Logger.getLogger(jDClientes.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
    }//GEN-LAST:event_jTableClientesMouseClicked

    private void VercuentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VercuentasMouseClicked
        
    }//GEN-LAST:event_VercuentasMouseClicked

    private void ModificarClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ModificarClienteMouseClicked
        
    }//GEN-LAST:event_ModificarClienteMouseClicked

    private void Eliminar_ClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Eliminar_ClienteMouseClicked
        
    }//GEN-LAST:event_Eliminar_ClienteMouseClicked

    private void VercuentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VercuentasActionPerformed
            new JDCuenta(null, true, db, String.valueOf(jTableClientes.getValueAt(jTableClientes.getSelectedRow(), 0))).setVisible(true);
       
    }//GEN-LAST:event_VercuentasActionPerformed

    private void jBModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBModificarActionPerformed
        if(jTableClientes.getSelectedRow()>(-1)){
            String cod= String.valueOf(jTableClientes.getValueAt(jTableClientes.getSelectedRow(), 0));
            new jDModcliente(null, true, db, cod).setVisible(true);
            jTbuscarCaretUpdate(null);
        }else{
            JOptionPane.showMessageDialog(null, "Por favor seleccione un personal", "Atencion", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jBModificarActionPerformed

    private void ModificarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarClienteActionPerformed
        if(jTableClientes.getSelectedRow()>(-1)){
            String cod= String.valueOf(jTableClientes.getValueAt(jTableClientes.getSelectedRow(), 0));
            System.out.println(cod);
            new jDModcliente(null, true,db, cod).setVisible(true);
            jTbuscarCaretUpdate(null);
        }else{
            JOptionPane.showMessageDialog(null, "Por favor seleccione un personal", "Atencion", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_ModificarClienteActionPerformed

    private void Eliminar_ClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Eliminar_ClienteActionPerformed
        if(jTableClientes.getSelectedRow()>(-1)){
            int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro de que desea eliminar al cliente?");
            if(resp==JOptionPane.YES_OPTION){
                String cod= String.valueOf(jTableClientes.getValueAt(jTableClientes.getSelectedRow(), 0));
                if(db.Bajas("cliente", "where Id_cliente="+cod)){
                    JOptionPane.showMessageDialog(null, "Cliente "+cod+" eliminado", "Exito", JOptionPane.INFORMATION_MESSAGE);
                }
                preparartabla();
            }
        }else{
            JOptionPane.showMessageDialog(null, "Por favor seleccione un personal", "Atencion", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_Eliminar_ClienteActionPerformed

    private void jTableClientesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTableClientesFocusLost
        if(jTableClientes.getSelectedRowCount()==0){
            jLCuentas.setText(" pendientes a cobrar");
        }
    }//GEN-LAST:event_jTableClientesFocusLost
    
    private void autorizar(){
        String Nivel=user.getNivelUsuario();
        if(Nivel.equals("Administrador")){
            
            
        }else if(Nivel.equals("Depósito")){
            jBAddCliente.setVisible(false);
            jBModificar.setVisible(false);
            
        }else if(Nivel.equals("Ventas")){
            jBAddCliente.setVisible(false);
            jBModificar.setVisible(false);
            
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                jDClientes dialog = new jDClientes(new javax.swing.JFrame(), true,null,null);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Eliminar_Cliente;
    private javax.swing.JButton JbCancelar;
    private javax.swing.JMenuItem ModificarCliente;
    private javax.swing.JMenuItem Vercuentas;
    private javax.swing.JButton jBAddCliente;
    private javax.swing.JButton jBImprimir;
    private javax.swing.JButton jBModificar;
    private javax.swing.JLabel jLCuentas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPmenucliente;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableClientes;
    private javax.swing.JTextField jTbuscar;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
