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
import com.mysql.jdbc.Blob;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileView;

/**
 *
 * @author Edgar
 */
public class jDModPers extends javax.swing.JDialog {
    private String Host;
    private String Puerto;
    private String Database;
    private String Usuario;
    private String Password;
    private String FieldContra;
    DBConnect connect;
    int i;
    String cod;
    ResultSet r;
    private String ruta="";
    private double zoom;
    /** Creates new form jDNuevoPers */
    public jDModPers(java.awt.Frame parent, boolean modal,DBConnect con,String id_personal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Modificar Personal");
        
        this.cod=id_personal;
        jLNuevo.setText("Modificar Personal");
        connect = con;
        
        llenar();
        jtcedula.setEditable(false);
    }
    
     public void llenar(){
        
            r=null;
            r = connect.Listar("*","usuario","where Id_usu= '"+cod+"'");
        try {
            r.next();
            jtcedula.setText(r.getString(1));
            jTNombre.setText(r.getString(2));
            jtapellido.setText(r.getString(3));
            jtTelefono.setText(r.getString(4));
            jTDireccion.setText(r.getString(5));
        } catch (SQLException ex) {
            Logger.getLogger(jDModPers.class.getName()).log(Level.SEVERE, null, ex);
        }
        mostrarImagen();
        
     }
     
     public void mostrarImagen(){
        String codigo;
        codigo = jtcedula.getText();
        ResultSet rs = connect.Listar("*", "usuario", "where Id_usu = '"+codigo+"'");
        
        try{
            rs.next();
            Blob blob = (Blob) rs.getBlob("Imagen_usu");
            byte [] data = blob.getBytes(1,(int) blob.length());
            BufferedImage img = null;
            img = ImageIO.read(new ByteArrayInputStream(data));
            jXImageView1.setImage(img);
            
            
        } catch ( Exception ex){
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
     
     public File obtenerArchivo(){
       int resultado = 0;
       JFileChooser elegir = new JFileChooser();
       
       FileView fv = new FileView() {};
       
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
        jlCedula = new javax.swing.JLabel();
        jtcedula = new javax.swing.JTextField();
        jlTelefono = new javax.swing.JLabel();
        jtTelefono = new javax.swing.JTextField();
        jlDireccion = new javax.swing.JLabel();
        jTDireccion = new javax.swing.JTextField();
        jLIcon = new javax.swing.JLabel();
        jXImageView1 = new org.jdesktop.swingx.JXImageView();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLNuevo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLNuevo.setText("Modificar Personal");

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

        jPanel1.setLayout(new java.awt.GridLayout(5, 2, 0, 10));

        jLNombre.setText("Nombre:");
        jPanel1.add(jLNombre);
        jPanel1.add(jTNombre);

        jlapellido.setText("Apellido:");
        jPanel1.add(jlapellido);
        jPanel1.add(jtapellido);

        jlCedula.setText("Cédula Nro:");
        jPanel1.add(jlCedula);
        jPanel1.add(jtcedula);

        jlTelefono.setText("Teléfono:");
        jPanel1.add(jlTelefono);
        jPanel1.add(jtTelefono);

        jlDireccion.setText("Dirección:");
        jPanel1.add(jlDireccion);
        jPanel1.add(jTDireccion);

        jLIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Vendedor_32x32.png"))); // NOI18N

        jXImageView1.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                jXImageView1MouseWheelMoved(evt);
            }
        });
        jXImageView1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jXImageView1MouseClicked(evt);
            }
        });
        jXImageView1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jXImageView1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jXImageView1Layout = new javax.swing.GroupLayout(jXImageView1);
        jXImageView1.setLayout(jXImageView1Layout);
        jXImageView1Layout.setHorizontalGroup(
            jXImageView1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 258, Short.MAX_VALUE)
        );
        jXImageView1Layout.setVerticalGroup(
            jXImageView1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jXImageView1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(94, 94, 94)
                                .addComponent(jLIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLNuevo))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPBotones, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLNuevo))
                            .addComponent(jLIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 44, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jXImageView1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPBotones, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAceptarActionPerformed
            boolean exito = false;
              if(!jtcedula.getText().equals("")){
                if(!jTNombre.getText().equals("")){
                   exito =  connect.actualizarRegistro("usuario","Nom_usu='"+jTNombre.getText()+"',Apellido_usu='"+jtapellido.getText()+"', Tel_usu='"+jtTelefono.getText()+"',Direc_usu='"+jTDireccion.getText()+"'","where Id_usu='"+cod+"'");
                    exito= exito && connect.actualizarRegistro("usuario", " zoom_image="+zoom, "where Id_usu= '"+jtcedula.getText()+"'");
                    
                    
                    
                    if(ruta!=""){
                        ResultSet res= null;
                        res = connect.Listar("*", "usuario", "where Id_usu="+jtcedula.getText());
                        String mods[] = new String[2];
                        
                        try {
                            res.next();
                            mods[0] = res.getString("Notas_usu");
                            mods[1] = res.getString("Alias_usu");
                        } catch (SQLException ex) {
                            Logger.getLogger(jDModPers.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        

                        FileInputStream foto = null,foto1 = null;
                        try {
                            foto = new FileInputStream(ruta);
                            foto1 = new FileInputStream(ruta);
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(FramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        if((connect.actualizarRegistro(mods,foto,"usuario"))){
                            JOptionPane.showMessageDialog(rootPane, "Los Cambios han sido guardados correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);

                        }else{
                            JOptionPane.showMessageDialog(rootPane, "Ha ocurrido un Error al momento de guardar los Cambios", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    
                    if(exito)
                        JOptionPane.showMessageDialog(rootPane, "Los Cambios han sido guardados correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "El campo Nombre no debe estar vacio","Atencion",JOptionPane.ERROR_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null, "El campo Cedula no debe estar vacio","Atencion",JOptionPane.ERROR_MESSAGE);
            }
            
        
            
        
}//GEN-LAST:event_jBAceptarActionPerformed

    private void jBCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCancelarActionPerformed
        this.dispose();
       
    }//GEN-LAST:event_jBCancelarActionPerformed

    private void jXImageView1MouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_jXImageView1MouseWheelMoved
        if(evt.getPreciseWheelRotation()>0){
            zoom-=0.1;
            jXImageView1.setScale(zoom);
        }else{
            zoom+=0.1;
            jXImageView1.setScale(zoom);
        }
    }//GEN-LAST:event_jXImageView1MouseWheelMoved

    private void jXImageView1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jXImageView1MouseClicked
        if(evt.getClickCount()==2){
            File archive = obtenerArchivo();

            if (archive.exists() && archive != null)
            ruta = archive.getPath();

            if(ruta!=""){
                Image imagen = getToolkit().getImage(ruta);
                System.out.println();
                File file = new File(ruta);
                try {
                    jXImageView1.setImage(file);

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_jXImageView1MouseClicked

    private void jXImageView1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jXImageView1KeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_PLUS){
            zoom+=0.1;
            jXImageView1.setScale(zoom);
        }
        if(evt.getKeyCode()==KeyEvent.VK_MINUS){
            zoom-=0.1;
            jXImageView1.setScale(zoom);
        }
    }//GEN-LAST:event_jXImageView1KeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                jDModPers dialog = new jDModPers(new javax.swing.JFrame(), true,null,"1");
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
    private javax.swing.JButton jBAceptar;
    private javax.swing.JButton jBCancelar;
    private javax.swing.JLabel jLIcon;
    private javax.swing.JLabel jLNombre;
    private javax.swing.JLabel jLNuevo;
    private javax.swing.JPanel jPBotones;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTDireccion;
    private javax.swing.JTextField jTNombre;
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
