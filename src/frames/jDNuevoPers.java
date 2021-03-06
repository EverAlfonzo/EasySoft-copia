/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * jDNuevoPers.java
 *
 * Created on 08/09/2012, 11:44:04 AM
 */
package frames;

import clases.DBConnect;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileView;

/**
 *
 * @author Edgar
 */
public class jDNuevoPers extends javax.swing.JDialog {
    private String Host;
    private String Puerto;
    private String Database;
    private String Usuario;
    private String Password;
    private String FieldContra;
    private String Alias;
    DBConnect connect;
    int frame;
    int ban=0;
    String tabla;
    int i=0;
    double zoom = 1.0; 
    Image imagen = getToolkit().getImage(getClass().getResource("/imagenes/global2.png"));
    /** Creates new form jDNuevoPers */
    
     public jDNuevoPers(java.awt.Frame parent, boolean modal,String nUsuario,DBConnect con,String FieldContra,int control) {
        super(parent, modal);
        initComponents();
        this.setIconImage(imagen);
        setLocationRelativeTo(null);
        setTitle("Nuevo Personal");
        this.FieldContra=FieldContra;
        this.Alias=con.getUser();
        
        jLNuevo.setText("Nuevo Personal");
        this.connect=con;
       if(control==1){
           this.jPContraseña.setEnabled(false);
           this.jPContraseña2.setEnabled(false);
           
       }
        
        this.ban=control;
        
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLNuevo = new javax.swing.JLabel();
        jPBotones = new javax.swing.JPanel();
        jBAceptar = new javax.swing.JButton();
        jBCancelar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLNombre = new javax.swing.JLabel();
        jTNombre = new javax.swing.JTextField();
        jlapellido = new javax.swing.JLabel();
        jtapellido = new javax.swing.JTextField();
        jLContraseña = new javax.swing.JLabel();
        jPContraseña = new javax.swing.JPasswordField();
        jLContraseña2 = new javax.swing.JLabel();
        jPContraseña2 = new javax.swing.JPasswordField();
        jlCedula = new javax.swing.JLabel();
        jtcedula = new javax.swing.JTextField();
        jlTelefono = new javax.swing.JLabel();
        jtTelefono = new javax.swing.JTextField();
        jlDireccion = new javax.swing.JLabel();
        jTDireccion = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLNivUsu = new javax.swing.JLabel();
        jXComboBox1 = new org.jdesktop.swingx.JXComboBox();
        jLIcon = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jXImageView1 = new org.jdesktop.swingx.JXImageView();
        JTRuta = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLNuevo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLNuevo.setText("Nuevo Usuario");

        jPBotones.setLayout(new java.awt.GridLayout(1, 0, 50, 0));

        jBAceptar.setText("Aceptar");
        jBAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAceptarActionPerformed(evt);
            }
        });
        jPBotones.add(jBAceptar);

        jBCancelar.setText("Cancelar");
        jBCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCancelarActionPerformed(evt);
            }
        });
        jPBotones.add(jBCancelar);

        jLNombre.setText("Nombre:");

        jlapellido.setText("Apellido:");

        jLContraseña.setText("Contraseña:");

        jLContraseña2.setText("Conf. Contraseña");

        jlCedula.setText("Cédula Nro:");

        jlTelefono.setText("Teléfono:");

        jlDireccion.setText("Dirección:");

        jLabel1.setText("Imagen:");

        jLNivUsu.setText("Nivel de Usuario:");

        jXComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Administrador", "Depósito", "Ventas" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlCedula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLContraseña2, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                    .addComponent(jLContraseña, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlapellido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtapellido, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPContraseña2, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtcedula, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLNivUsu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jXComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlapellido, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtapellido, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLContraseña2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPContraseña2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtcedula, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLNivUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Vendedor_32x32.png"))); // NOI18N

        jButton1.setText("Examinar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jXImageView1.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                jXImageView1MouseWheelMoved(evt);
            }
        });

        javax.swing.GroupLayout jXImageView1Layout = new javax.swing.GroupLayout(jXImageView1);
        jXImageView1.setLayout(jXImageView1Layout);
        jXImageView1Layout.setHorizontalGroup(
            jXImageView1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 261, Short.MAX_VALUE)
        );
        jXImageView1Layout.setVerticalGroup(
            jXImageView1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel2.setText("Ruta:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(94, 94, 94)
                            .addComponent(jLIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLNuevo))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jPBotones, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel2)
                            .addGap(74, 74, 74)
                            .addComponent(JTRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton1)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jXImageView1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLNuevo))
                    .addComponent(jLIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(JTRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addComponent(jXImageView1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jPBotones, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAceptarActionPerformed
        if(!jPContraseña.isEnabled()){
            if(!jtcedula.getText().equals("")){
                if(!jTNombre.getText().equals("")){
                   if(JTRuta.getText()==""){
                        connect.Altas("usuario", "", "("+jtcedula.getText()+",'"+jTNombre.getText()+"','"+jtapellido.getText()+"','"+jtTelefono.getText()+"','"+jTDireccion.getText()+"','"+Alias+"','','','','"+jXComboBox1.getSelectedItem()+"');");
                   }else{
                       FileInputStream foto=null;
                       try {
                            foto = new FileInputStream(JTRuta.getText());
               
                      } catch (FileNotFoundException ex) {
                            JOptionPane.showMessageDialog(rootPane,ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                       
                       
                       String vector[] = new String[2];
                       vector[0] = "";
                       vector[1] = jtcedula.getText();
                       connect.actualizarRegistro(vector,foto, "usuario");
                   }
                    
                }else{
                    JOptionPane.showMessageDialog(null, "El campo Nombre no debe estar vacio","Atencion",JOptionPane.ERROR_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null, "El campo Cedula no debe estar vacio","Atencion",JOptionPane.ERROR_MESSAGE);
            }
        }else{
            if(!jtcedula.getText().equals("")){
                if(!jTNombre.getText().equals("")){
                    
                    
                        if(jPContraseña.getText().equals(jPContraseña2.getText())){
                            String alias = JOptionPane.showInputDialog("Introduzca Un Alias");
                            while(!connect.crearUsuario(alias, jPContraseña.getText(), "Usuario")){
                                JOptionPane.showMessageDialog(null, "El alias ya existe, vuelva a introducir", "Atencion", JOptionPane.INFORMATION_MESSAGE);
                                alias=JOptionPane.showInputDialog("Introduzca un Alias");
                            }
                            connect.Altas("usuario", "", "("+jtcedula.getText()+",'"+jTNombre.getText()+"','"+jtapellido.getText()+"','"+jtTelefono.getText()+"','"+jTDireccion.getText()+"','"+alias+"',"+zoom+",'','','"+jXComboBox1.getSelectedItem()+"');");
                            
                            if(!JTRuta.getText().equals("")){
                                String mods[] = new String[2];

                                mods[0] = " ";
                                mods[1] = jtcedula.getText();

                                FileInputStream foto = null,foto1 = null;
                                try {

                                    foto = new FileInputStream(JTRuta.getText());
                                    foto1 = new FileInputStream(JTRuta.getText());
                                } catch (FileNotFoundException ex) {
                                    Logger.getLogger(FramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
                                }

                                if((connect.actualizarRegistro(mods,foto,"usuario"))){
                                    JOptionPane.showMessageDialog(rootPane, "Los Cambios han sido guardados correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);

                                }else{
                                    JOptionPane.showMessageDialog(rootPane, "Ha ocurrido un Error al momento de guardar los Cambios", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }else{
                                JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
                         }
                            
                    
                }else{
                    JOptionPane.showMessageDialog(null, "El campo Nombre no debe estar vacio","Atencion",JOptionPane.ERROR_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null, "El campo Cedula no debe estar vacio","Atencion",JOptionPane.ERROR_MESSAGE);
            }
        }
        
            
        
}//GEN-LAST:event_jBAceptarActionPerformed

    private void jBCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCancelarActionPerformed
        this.dispose();
        
    }//GEN-LAST:event_jBCancelarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        int resultado = JOptionPane.showConfirmDialog(null, "¿Desea realmente salir?");
        if (resultado==JOptionPane.YES_OPTION){
            this.dispose();
        }
    }//GEN-LAST:event_formWindowClosing

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        File archive = obtenerArchivo();
        
        if (archive.exists() && archive != null)
         JTRuta.setText(archive.getPath());
        
        if(JTRuta.getText()!=""){
            Image imagen = getToolkit().getImage(JTRuta.getText());
                System.out.println();
                File file = new File(JTRuta.getText());
                try {
                    jXImageView1.setImage(file);

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jXImageView1MouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_jXImageView1MouseWheelMoved
         if(evt.getPreciseWheelRotation()>0){
            zoom-=0.1;
            jXImageView1.setScale(zoom);
        }else{
            zoom+=0.1;
            jXImageView1.setScale(zoom);
        }
    }//GEN-LAST:event_jXImageView1MouseWheelMoved

    public File obtenerArchivo(){
       int resultado = 0;
       JFileChooser elegir = new JFileChooser();
       
       FileView fv = new FileView() {
};
       
       elegir.setFileView(fv);
       
       elegir.setFileSelectionMode(JFileChooser.FILES_ONLY);
       resultado = elegir.showOpenDialog(this);
       if(resultado == JFileChooser.CANCEL_OPTION){
           return null;
       }
       File archivo = elegir.getSelectedFile();
       if(archivo == null){
           JOptionPane.showMessageDialog(null, "Error al cargar archivo",
                        "Error", JOptionPane.ERROR_MESSAGE);
       }
       return archivo;
   }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                jDNuevoPers dialog = new jDNuevoPers(new javax.swing.JFrame(), true,"",null,"",0);
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
    private javax.swing.JTextField JTRuta;
    private javax.swing.JButton jBAceptar;
    private javax.swing.JButton jBCancelar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLContraseña;
    private javax.swing.JLabel jLContraseña2;
    private javax.swing.JLabel jLIcon;
    private javax.swing.JLabel jLNivUsu;
    private javax.swing.JLabel jLNombre;
    private javax.swing.JLabel jLNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPBotones;
    private javax.swing.JPasswordField jPContraseña;
    private javax.swing.JPasswordField jPContraseña2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTDireccion;
    private javax.swing.JTextField jTNombre;
    private javax.swing.JTextField jTextField1;
    private org.jdesktop.swingx.JXComboBox jXComboBox1;
    private org.jdesktop.swingx.JXImageView jXImageView1;
    private javax.swing.JLabel jlCedula;
    private javax.swing.JLabel jlDireccion;
    private javax.swing.JLabel jlTelefono;
    private javax.swing.JLabel jlapellido;
    private javax.swing.JTextField jtTelefono;
    private javax.swing.JTextField jtapellido;
    private javax.swing.JTextField jtcedula;
    // End of variables declaration//GEN-END:variables
}
