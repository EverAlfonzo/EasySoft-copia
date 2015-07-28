/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FrmLogon.java
 *
 * Created on 11/08/2012, 08:39:57 AM
 */
package frames;


import clases.Archivo;
import clases.DBConnect;
import clases.JOptionPaneConTimeOut;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;


/**
 *
 * @author Edgar
 */
public class FrmLogon extends javax.swing.JFrame {
    DBConnect db;
     Image imagen = getToolkit().getImage(getClass().getResource("/imagenes/global2.png"));
    Archivo config;
     /** Creates new form FrmLogon */
    public FrmLogon() {
        initComponents();
        setTitle("Identificacion");
        db = new DBConnect();
        setIconImage(imagen);
        setLocationRelativeTo(null);
        jtfusuario.requestFocus();
//        String sep= System.getProperty("file.separator");
//        String home = System.getProperty("user.home");
//        config = new Archivo(home+sep+"ConfigEasySoft1.dat");
//          if(config.existe()){
//            try {
//                db = config.cargar();
//                
//            } catch (FileNotFoundException ex) {
//                Logger.getLogger(FrmLogon.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (IOException ex) {
//                Logger.getLogger(FrmLogon.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (ClassNotFoundException ex) {
//                Logger.getLogger(FrmLogon.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }else{
//            String user,pass;
//            user = jtfusuario.getText();
//            
//            pass = jpfcontraseña.getText();
//            
//            db = new DBConnect();
//            db.conectado(user, pass);
//            try {
//                config.guardar(db);
//            } catch (FileNotFoundException ex) {
//                Logger.getLogger(FrmLogon.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (IOException ex) {
//                Logger.getLogger(FrmLogon.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
        
        
        this.setEnabled(true);
        
    }
    public void autorizar(){
        try{
            if(db.conectado(this.jtfusuario.getText(),
                this.jpfcontraseña.getText()))
                {
                JOptionPaneConTimeOut a = new JOptionPaneConTimeOut();
                a.visualizaDialogo(this, "Ingresando al sistema", "Espere",1000);
                FramePrincipal f = new FramePrincipal(db);
                f.setExtendedState(FramePrincipal.MAXIMIZED_BOTH);
                f.setBackground(Color.red);
                f.setVisible(true);
                dispose();
            }
        }catch (Exception e){}
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtfusuario = new javax.swing.JTextField();
        jpfcontraseña = new javax.swing.JPasswordField();
        jPanel1 = new javax.swing.JPanel();
        jBAceptar = new javax.swing.JButton();
        jBAvanzado = new javax.swing.JButton();
        jBCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/prog_login_img.png"))); // NOI18N

        jLabel2.setText("Usuario:");

        jLabel3.setText("Password:");

        jtfusuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtfusuarioKeyPressed(evt);
            }
        });

        jpfcontraseña.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jpfcontraseñaKeyPressed(evt);
            }
        });

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        jBAceptar.setText("Aceptar");
        jBAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAceptarActionPerformed(evt);
            }
        });
        jBAceptar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jBAceptarKeyReleased(evt);
            }
        });
        jPanel1.add(jBAceptar);

        jBAvanzado.setText("Avanzado");
        jBAvanzado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAvanzadoActionPerformed(evt);
            }
        });
        jPanel1.add(jBAvanzado);

        jBCancelar.setText("Cancelar");
        jBCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(jBCancelar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpfcontraseña))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfusuario, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(32, 32, 32))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfusuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jpfcontraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleParent(this);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAceptarActionPerformed
        // TODO add your handling code here:
        autorizar();

    }//GEN-LAST:event_jBAceptarActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_formWindowClosed

    private void jBAceptarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBAceptarKeyReleased
        if (evt.getKeyCode() == evt.VK_ENTER){
            autorizar();
        }
    }//GEN-LAST:event_jBAceptarKeyReleased

    private void jBAvanzadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAvanzadoActionPerformed
        new FrmAvanzado().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jBAvanzadoActionPerformed

    private void jpfcontraseñaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jpfcontraseñaKeyPressed
        // TODO add your handling code here:
          if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            jBAceptar.requestFocus();
            autorizar();
            
        }
    }//GEN-LAST:event_jpfcontraseñaKeyPressed

    private void jtfusuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfusuarioKeyPressed
         if (evt.getKeyCode() == KeyEvent.VK_ENTER){
              jBAceptar.requestFocus();
            autorizar();
            
        }
    }//GEN-LAST:event_jtfusuarioKeyPressed

    private void jBCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jBCancelarActionPerformed

    /** 
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmLogon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmLogon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmLogon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmLogon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            
            public void run() {
                
                new FrmLogon().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBAceptar;
    private javax.swing.JButton jBAvanzado;
    private javax.swing.JButton jBCancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jpfcontraseña;
    private javax.swing.JTextField jtfusuario;
    // End of variables declaration//GEN-END:variables
}