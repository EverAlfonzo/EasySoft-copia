/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * jDPersonal.java
 *
 * Created on 11/08/2012, 08:10:47 AM
 */
package frames;

import clases.DBConnect;
import com.mysql.jdbc.Blob;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.omg.CORBA.CODESET_INCOMPATIBLE;
import java.sql.ResultSet;
import java.util.HashMap;
import javax.swing.JOptionPane;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JTable;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.jdesktop.swingx.JXImageView;


/**
 *
 * @author Edgar
 */
public class jDPersonal extends javax.swing.JDialog {
    Image imagen = getToolkit().getImage(getClass().getResource("/imagenes/global2.png"));
    ResultSet r;
    DBConnect bd;
    DefaultTableModel m;
    private String Host;
    private String Puerto;
    private String Database;
    private String Usuario;
    private String Password;
    int i=0;
    /** Creates new form jDPersonal */
    public jDPersonal(java.awt.Frame parent, boolean modal,DBConnect con) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        setIconImage(imagen);
        setTitle("Personal");
        bd = con;
        PrepararTabla();
        PrepararTabla();
        this.Usuario=con.getUser();
        this.Password=con.getPass();
        ModificarVend.setEnabled(false);
        Eliminar_Vend.setEnabled(false);
    }
    
     private void PrepararTabla() {
          try {
                r= null;
                r = bd.Listar("*", "usuario", "");
                String titulos[]={"Cod. Usuario","Nombre","Telefono","Direccion"};
                    m = (new javax.swing.table.DefaultTableModel(
                        new Object [][] {

                        },
                        new String [] {
                            "Cod. Usuario","Nombre","Telefono","Direccion"
                        }
                    ) {
                        boolean[] canEdit = new boolean [] {
                            false, false, false, false
                        };

                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                            return canEdit [columnIndex];
                        }
                    });
                    jTVentas.setModel(m);
                    
                String Fila[]= new String[4];
           
                while(r.next()){
                    Fila[0]= r.getString("Id_usu");
                    Fila[1]= r.getString("Nom_usu")+" "+r.getString("Apellido_usu");
                    Fila[2]= r.getString("Tel_usu");
                    Fila[3]= r.getString("Direc_usu");
                    m.addRow(Fila);

                }
            } catch (Exception ex) {
               JOptionPane.showMessageDialog(null, ex.getMessage(),"Error 106",JOptionPane.ERROR_MESSAGE);
               
            }
        }
     
     
     public void mostrarImagen(JXImageView visor, JTable tabla){ 
        String codigo;
        codigo = (String) tabla.getValueAt(tabla.getSelectedRow(), 0);
        ResultSet rs = bd.Listar("*", "usuario", "where Id_usu= '"+codigo+"'");
        
        try{
            rs.next();
            Blob blob = (Blob) rs.getBlob("Imagen_usu");
            byte [] data = blob.getBytes(1,(int) blob.length());
            BufferedImage img = null;
            img = ImageIO.read(new ByteArrayInputStream(data));
            visor.setImage(img);
            visor.setScale(Double.valueOf(rs.getString("zoom_image")));
            
        } catch ( SQLException ex){
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error 127", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex){
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error 129", JOptionPane.ERROR_MESSAGE);
        }
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPmenuVenta = new javax.swing.JPopupMenu();
        ModificarVend = new javax.swing.JMenuItem();
        Eliminar_Vend = new javax.swing.JMenuItem();
        jTPersonal = new javax.swing.JTabbedPane();
        jPVentas = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTVentas = new javax.swing.JTable();
        jPBotones = new javax.swing.JPanel();
        jBNuevoVEnd = new javax.swing.JButton();
        jBSalir = new javax.swing.JButton();
        jBModVenta = new javax.swing.JButton();
        jBImprVenta = new javax.swing.JButton();
        jXVentaImage = new org.jdesktop.swingx.JXImageView();

        ModificarVend.setText("Modificar Vendedor");
        ModificarVend.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ModificarVendMouseClicked(evt);
            }
        });
        ModificarVend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarVendActionPerformed(evt);
            }
        });
        jPmenuVenta.add(ModificarVend);

        Eliminar_Vend.setText("Eliminar Vendedor");
        Eliminar_Vend.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Eliminar_VendMouseClicked(evt);
            }
        });
        Eliminar_Vend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Eliminar_VendActionPerformed(evt);
            }
        });
        jPmenuVenta.add(Eliminar_Vend);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(null);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jTVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTVentas.setComponentPopupMenu(jPmenuVenta);
        jTVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTVentasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTVentas);

        jPBotones.setLayout(new java.awt.GridLayout(2, 3));

        jBNuevoVEnd.setText("Nuevo Personal");
        jBNuevoVEnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBNuevoVEndActionPerformed(evt);
            }
        });
        jPBotones.add(jBNuevoVEnd);

        jBSalir.setText("Salir");
        jBSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSalirActionPerformed(evt);
            }
        });
        jPBotones.add(jBSalir);

        jBModVenta.setText("Modificar Datos");
        jBModVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBModVentaActionPerformed(evt);
            }
        });
        jPBotones.add(jBModVenta);

        jBImprVenta.setText("Imprimir el registro");
        jBImprVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBImprVentaActionPerformed(evt);
            }
        });
        jPBotones.add(jBImprVenta);

        javax.swing.GroupLayout jXVentaImageLayout = new javax.swing.GroupLayout(jXVentaImage);
        jXVentaImage.setLayout(jXVentaImageLayout);
        jXVentaImageLayout.setHorizontalGroup(
            jXVentaImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 275, Short.MAX_VALUE)
        );
        jXVentaImageLayout.setVerticalGroup(
            jXVentaImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPVentasLayout = new javax.swing.GroupLayout(jPVentas);
        jPVentas.setLayout(jPVentasLayout);
        jPVentasLayout.setHorizontalGroup(
            jPVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPVentasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPVentasLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jXVentaImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPVentasLayout.createSequentialGroup()
                        .addComponent(jPBotones, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPVentasLayout.setVerticalGroup(
            jPVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPVentasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXVentaImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPBotones, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTPersonal.addTab("Usuarios", jPVentas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTPersonal)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTPersonal)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBNuevoVEndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBNuevoVEndActionPerformed
            new jDNuevoPers(null, true,"",bd,"",0).setVisible(true);
    }//GEN-LAST:event_jBNuevoVEndActionPerformed

    private void jBSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_jBSalirActionPerformed

    private void jBModVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBModVentaActionPerformed
        if(jTVentas.getSelectedRow()>(-1)){
            String cod= String.valueOf(jTVentas.getValueAt(jTVentas.getSelectedRow(), 0));
            new jDModPers(null, true, bd, cod).setVisible(true);
            PrepararTabla();
            
        }else{
            JOptionPane.showMessageDialog(null, "Por favor seleccione un personal", "Atencion", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jBModVentaActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
       
    }//GEN-LAST:event_formWindowActivated

    private void jBImprVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBImprVentaActionPerformed
        try{
           r=null;
           r=bd.Listar("*", "vendedor", "");
          

           
        } catch (Exception ex) {
             //Logger.getLogger(ciudad.class.getName()).log(Level.SEVERE, null, ex);
        }

        JRResultSetDataSource jrRS = new JRResultSetDataSource(r);
        HashMap parameters = new HashMap();

        try{
            URL urlMaestro = getClass().getClassLoader().getResource("reportes/Vendedores.jasper");
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
    }//GEN-LAST:event_jBImprVentaActionPerformed

    private void ModificarVendMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ModificarVendMouseClicked
        
}//GEN-LAST:event_ModificarVendMouseClicked

    private void ModificarVendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarVendActionPerformed
        if(jTVentas.getSelectedRow()>(-1)){
        String cod= String.valueOf(jTVentas.getValueAt(jTVentas.getSelectedRow(), 0));
        this.dispose();
        if(i==0){
            new jDModPers(null, true,bd, cod).setVisible(true);
        }
         }else{
            JOptionPane.showMessageDialog(null, "Por favor seleccione un personal", "Atencion", JOptionPane.INFORMATION_MESSAGE);
        }
}//GEN-LAST:event_ModificarVendActionPerformed

    private void Eliminar_VendMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Eliminar_VendMouseClicked
        
}//GEN-LAST:event_Eliminar_VendMouseClicked

    private void Eliminar_VendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Eliminar_VendActionPerformed
        if(jTVentas.getSelectedRow()>(-1)){
            int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro de que desea eliminar al vendedor?");
            if(resp==JOptionPane.YES_OPTION){
                String cod= String.valueOf(jTVentas.getValueAt(jTVentas.getSelectedRow(), 0));
                ResultSet alias= bd.Listar("*", "usuario", "where Id_usu='"+cod+"'");
                if(bd.Bajas("usuario", "where Id_usu="+cod)){
                    
                     ResultSet comp = bd.Listar("*", "usuario", "where Id_usu='"+cod+"'");
                    try {
                        if(alias.next()){
                             if(!comp.next()){//verifica si existe otro personal con el mismo id, en cuyo caso no borra al usuario de la base de datos
                                String as = alias.getString("Alias_usu");
                                bd.borrarUsuario(as);
                             }
                         }
                    } catch (SQLException ex) {
                        Logger.getLogger(jDPersonal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    JOptionPane.showMessageDialog(null, "Usuario "+cod+" eliminado", "Exito", JOptionPane.INFORMATION_MESSAGE);
                    PrepararTabla();
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Por favor seleccione un personal", "Atencion", JOptionPane.INFORMATION_MESSAGE);
        }
}//GEN-LAST:event_Eliminar_VendActionPerformed

    private void jTVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTVentasMouseClicked
        if(evt.getButton()==evt.BUTTON1){
            ModificarVend.setEnabled(true);
            Eliminar_Vend.setEnabled(true);
        }
        
        mostrarImagen(jXVentaImage,jTVentas);
    }//GEN-LAST:event_jTVentasMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                jDPersonal dialog = new jDPersonal(new javax.swing.JFrame(), true,null);
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
    private javax.swing.JMenuItem Eliminar_Vend;
    private javax.swing.JMenuItem ModificarVend;
    private javax.swing.JButton jBImprVenta;
    private javax.swing.JButton jBModVenta;
    private javax.swing.JButton jBNuevoVEnd;
    private javax.swing.JButton jBSalir;
    private javax.swing.JPanel jPBotones;
    private javax.swing.JPanel jPVentas;
    private javax.swing.JPopupMenu jPmenuVenta;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTPersonal;
    private javax.swing.JTable jTVentas;
    private org.jdesktop.swingx.JXImageView jXVentaImage;
    // End of variables declaration//GEN-END:variables
}
