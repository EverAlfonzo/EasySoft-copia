/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * jDnventa.java
 *
 * Created on 11/08/2012, 03:36:02 PM
 */
package frames;

import clases.DBConnect;
import clases.Numero_a_Letra;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import java.sql.ResultSet;
import java.util.HashMap;
import javax.swing.JOptionPane;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
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
public class jDnPresupuesto extends javax.swing.JDialog {
Image imagen = getToolkit().getImage(getClass().getResource("/imagenes/global2.png"));
DefaultTableModel m;
Connection con;
private  Numero_a_Letra NL = new  Numero_a_Letra();
private  String vendedor;
private  String password;
private String Host;
private String Puerto;
private String Database;
private DBConnect db;
private ResultSet r;
private int i=0;
private Date a;
private Integer total=0;
private Integer existencia=0;
private int nro =1;
private boolean ex= true;// controla si la existencia del producto es suficiente
private String Nivel;
/** Creates new form jDnventa */
    public jDnPresupuesto(java.awt.Frame parent, boolean modal,DBConnect con,String Nivel) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        db = con;
        this.Nivel=Nivel;
        jcNombre.setEditable(true);
        AutoCompleteDecorator.decorate(jcNombre);
        setIconImage(imagen);
        this.setTitle("Agregar Venta");
        this.vendedor=con.getUser();
        this.password=con.getPass();
        jTVendedor.setText(vendedor);
        jTVendedor.setEditable(false);
        actualizarCombo();
        actualizarCampoUsados();
        a = new Date();
        jTFecha.setText(String.valueOf(a.getDate())+"/"+String.valueOf(a.getMonth()+1)+"/"+String.valueOf(a.getYear()+1900));
        jTFecha.setEditable(false);
        jTNumPresup.setEditable(false);
        jtnumvendedor.setEditable(false);
        String titulos[] = {"Cod. Producto","Cantidad","Descripcion","Precio"};
        m= (DefaultTableModel) jTProducto.getModel();
        m.setColumnIdentifiers(titulos);
        r=db.Listar("Id_presupuesto", "presupuesto", " order by Id_presupuesto ASC");
        try {
            
            if(r.last())
            nro = Integer.valueOf(r.getString(1))+1;
            if(nro>0 && nro <=9){
                jTNumPresup.setText("0000000"+nro);
            }
            if(nro>=10 && nro<=99){
                jTNumPresup.setText("000000"+nro);
            }
            if(nro>=100 && nro<=999){
                jTNumPresup.setText("00000"+nro);
            }
            if(nro>=1000 && nro<=9999){
                jTNumPresup.setText("0000"+nro);
            }
            if(nro>=10000 && nro<=99999){
                jTNumPresup.setText("000"+nro);
            }
            if(nro>=100000 && nro<=999999){
                jTNumPresup.setText("00"+nro);
            }
            if(nro>=1000000 && nro<=9999999){
                jTNumPresup.setText("0"+nro);
            }
            if(nro>=10000000 && nro<=99999999){
                jTNumPresup.setText(""+nro);
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        ResultSet nrovendr = db.Listar("*", "usuario", "where Alias_usu='"+jTVendedor.getText()+"'");
        try {
            if(nrovendr.next()){
                jtnumvendedor.setText(nrovendr.getString(1));
                jTVendedor.setText(nrovendr.getString(2)+" "+nrovendr.getString(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(jDnPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
        }
        jBImprimirPresup.setEnabled(false);
    }
   
    
    public void actualizarCombo(){
        try {
            r=null;
            r= db.Listar("*", "cliente", "");
            while(r.next()){
                jcNombre.addItem(r.getString("Nom_cliente")+" ,"+r.getString("Id_cliente"));
                System.out.println(r.getString("Nom_cliente")+" , "+r.getString("Id_cliente"));
            }
            AutoCompleteDecorator.decorate(jcNombre);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jmborrar = new javax.swing.JMenuItem();
        jPCliente = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jBaddCliente = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLidCliente = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jcNombre = new javax.swing.JComboBox();
        jPfactura = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTNumPresup = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTFecha = new javax.swing.JTextField();
        jTVendedor = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jtnumvendedor = new javax.swing.JTextField();
        jtdias = new javax.swing.JFormattedTextField();
        jLabel18 = new javax.swing.JLabel();
        jPcantidad = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jTPrecTotal = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jBImprimirPresup = new javax.swing.JButton();
        jBGuardPresup = new javax.swing.JButton();
        jBCancelar = new javax.swing.JButton();
        jPbotones = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTProducto = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jPProducto = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTPrecio = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTCantidad = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jBRegProducto = new javax.swing.JButton();
        jXTxtfUsados = new org.jdesktop.swingx.JXTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();

        jmborrar.setText("Eliminar");
        jmborrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmborrarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jmborrar);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPCliente.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Nombre del Cliente:");

        jBaddCliente.setText("Agregar Cliente");
        jBaddCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBaddClienteActionPerformed(evt);
            }
        });

        jLabel16.setText("ID. Cliente:");

        jLidCliente.setText("Nro");

        jcNombre.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcNombreItemStateChanged(evt);
            }
        });
        jcNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcNombreActionPerformed(evt);
            }
        });
        jScrollPane2.setViewportView(jcNombre);

        javax.swing.GroupLayout jPClienteLayout = new javax.swing.GroupLayout(jPCliente);
        jPCliente.setLayout(jPClienteLayout);
        jPClienteLayout.setHorizontalGroup(
            jPClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLidCliente))
                .addGap(18, 18, 18)
                .addComponent(jBaddCliente)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPClienteLayout.setVerticalGroup(
            jPClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPClienteLayout.createSequentialGroup()
                .addGroup(jPClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPClienteLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jBaddCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPClienteLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLidCliente))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPfactura.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("Presupuesto Nro:");

        jLabel3.setText("Fecha:");

        jLabel4.setText("Vendedor:");

        jLabel5.setText("Valido por:");

        jLabel17.setText("Codigo Vendedor:");

        jtdias.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jtdias.setText("15");
        jtdias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtdiasActionPerformed(evt);
            }
        });

        jLabel18.setText("Días");

        javax.swing.GroupLayout jPfacturaLayout = new javax.swing.GroupLayout(jPfactura);
        jPfactura.setLayout(jPfacturaLayout);
        jPfacturaLayout.setHorizontalGroup(
            jPfacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPfacturaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPfacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPfacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPfacturaLayout.createSequentialGroup()
                        .addComponent(jtdias, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18)
                        .addGap(154, 154, 154))
                    .addGroup(jPfacturaLayout.createSequentialGroup()
                        .addComponent(jTVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPfacturaLayout.createSequentialGroup()
                        .addComponent(jTNumPresup, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(22, 22, 22)))
                .addGroup(jPfacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtnumvendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );
        jPfacturaLayout.setVerticalGroup(
            jPfacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPfacturaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPfacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTNumPresup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPfacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel17)
                    .addComponent(jtnumvendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPfacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jtdias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPcantidad.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel14.setText("Precio Total:");

        jTPrecTotal.setText("0");

        jLabel11.setText("Gs.");

        jBImprimirPresup.setText("Imprimir Presupuesto");
        jBImprimirPresup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBImprimirPresupActionPerformed(evt);
            }
        });

        jBGuardPresup.setText("Guardar Presupuesto");
        jBGuardPresup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBGuardPresupActionPerformed(evt);
            }
        });

        jBCancelar.setText("Cancelar");
        jBCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPcantidadLayout = new javax.swing.GroupLayout(jPcantidad);
        jPcantidad.setLayout(jPcantidadLayout);
        jPcantidadLayout.setHorizontalGroup(
            jPcantidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPcantidadLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBImprimirPresup, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jBGuardPresup, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTPrecTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addContainerGap())
        );
        jPcantidadLayout.setVerticalGroup(
            jPcantidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPcantidadLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPcantidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTPrecTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel11)
                    .addComponent(jBImprimirPresup, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBGuardPresup, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPbotones.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPbotones.setLayout(new java.awt.GridLayout(1, 0, 20, 0));

        jTProducto.setModel(new javax.swing.table.DefaultTableModel(
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
        jTProducto.setComponentPopupMenu(jPopupMenu1);
        jTProducto.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jTProductoAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
                jTProductoAncestorRemoved(evt);
            }
        });
        jScrollPane4.setViewportView(jTProducto);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/search_icon.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPProducto.setBorder(javax.swing.BorderFactory.createTitledBorder("Insertar Repuestos Usados"));

        jLabel7.setText("Nombre y Detalle del Producto:");

        jLabel10.setText("Precio/unid:");

        jTPrecio.setText("0");
        jTPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTPrecioKeyReleased(evt);
            }
        });

        jLabel15.setText("Gs.");

        jTCantidad.setText("0");
        jTCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTCantidadKeyReleased(evt);
            }
        });

        jLabel12.setText("Cantidad:");

        jLabel13.setText("unidades");

        jBRegProducto.setText("Registrar Producto");
        jBRegProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBRegProductoActionPerformed(evt);
            }
        });
        jBRegProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jBRegProductoKeyReleased(evt);
            }
        });

        jXTxtfUsados.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jXTxtfUsadosKeyReleased(evt);
            }
        });

        jLabel6.setText("[?]");
        jLabel6.setToolTipText("Presione F3 para acceder al campo");

        javax.swing.GroupLayout jPProductoLayout = new javax.swing.GroupLayout(jPProducto);
        jPProducto.setLayout(jPProductoLayout);
        jPProductoLayout.setHorizontalGroup(
            jPProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPProductoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPProductoLayout.createSequentialGroup()
                        .addComponent(jBRegProducto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel15))
                    .addGroup(jPProductoLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jXTxtfUsados, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)))
                .addGap(17, 17, 17))
        );
        jPProductoLayout.setVerticalGroup(
            jPProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPProductoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(jLabel12)
                        .addComponent(jTCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(jXTxtfUsados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPProductoLayout.createSequentialGroup()
                        .addGroup(jPProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(jLabel10))
                        .addGap(24, 24, 24))
                    .addComponent(jBRegProducto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel19.setText("Para agregar productos registrados en base de datos al presupuesto, presionar F4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPcantidad, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPfactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)
                                .addComponent(jPbotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1)
                            .addComponent(jLabel19))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(108, 108, 108)
                                .addComponent(jPbotones, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPfactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBaddClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBaddClienteActionPerformed
            new jDnuevocliente(null, true,db,1).setVisible(true);
            actualizarCombo();

    }//GEN-LAST:event_jBaddClienteActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_formWindowClosed

    private void jcNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcNombreActionPerformed

    private void jcNombreItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcNombreItemStateChanged
        String h = String.valueOf(jcNombre.getSelectedItem());
        jLidCliente.setText(h.substring(h.indexOf(",")+1));
        System.out.println(h.substring(h.indexOf(",")+1));
    }//GEN-LAST:event_jcNombreItemStateChanged

    private void jBCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jBCancelarActionPerformed

    private void jBImprimirPresupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBImprimirPresupActionPerformed
try{
           r=null;
           r=db.Listar("*", "vistapresupuesto", "where Id_presupuesto='"+jTNumPresup.getText()+"'");
          

           
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }

      JasperReport reporte;
     JasperPrint reporte_view;
     JRResultSetDataSource jrRS = new JRResultSetDataSource(r);
     con = db.getConnection();
     //Se crea un objeto HashMap
          HashMap parametros = new HashMap();
          parametros.clear();
          //parametros de entrada
          String Total= NL.Convertir(jTPrecTotal.getText(), true);
          String NumFactura = jTNumPresup.getText();
          Integer SumaTotal = Integer.valueOf(jTPrecTotal.getText());
                    
          parametros.put( "SUMATOTAL", SumaTotal );
     try{
         //direccion del archivo JASPER
          URL  in = this.getClass().getClassLoader().getResource("reportes/presupuesto.jasper");
          reporte = (JasperReport) JRLoader.loadObject( in );
          
          
          //-----------------------------------
          reporte_view = JasperFillManager.fillReport(reporte, parametros, jrRS);
          JasperViewer ventana = new JasperViewer(reporte_view,false);
          ventana.setTitle("Vista Previa");
          ventana.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
          ventana.setVisible(true);
          
          
	  }catch (JRException E){
              JOptionPane.showMessageDialog(null, E.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
          }
    }//GEN-LAST:event_jBImprimirPresupActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
            jDProductos prod = new jDProductos(null, true, db,2,Nivel);
        prod.setVisible(true);
        //-------------------------------------------------//
        boolean bandera=false;
        int indice=0;
        for(int h=0;h<prod.f;h++){
          bandera = false;
            Integer precio=0;
            Integer cantidad=0;
            String codigo1="";
            String codigo2="";
             indice = 0;
            
            
            r=null;
            r = db.Listar("*", "producto", "where Cod_inter_producto='"+String.valueOf(prod.Lista[h][0]+"'"));
            String vec[]= new String[4];
            
            int hasta= jTProducto.getRowCount();
            
            System.out.println(String.valueOf(hasta));
            
                for (int j=0;j<hasta;j++){
                    bandera=false;
                    codigo1 = String.valueOf(jTProducto.getValueAt(j, 0));
                    codigo2 = String.valueOf(prod.Lista[h][0]);
                    if(codigo1.equals(codigo2)){
                        bandera=true;
                        indice = j;
                        System.out.println("Hooolaa");
                        break;
                    }
                    System.out.println(jTProducto.getValueAt(j, 0));
                    
                }
            
            
            if(bandera==false){
                try {
                    
                        r.next();
                        vec[0]= r.getString("Cod_inter_producto");
                        System.out.println("puto"+ prod.Lista[h][1]);
                        vec[1]= prod.Lista[h][1];
                        vec[2]= r.getString("Nom_producto")+" "+r.getString("Marca_producto");
                        precio = Integer.valueOf(prod.Lista[h][2])*Integer.valueOf(prod.Lista[h][1]);
                        vec[3]= String.valueOf(precio);
                        for(int i = 0; i<4 ; i++)
                        System.out.println(vec[i]);
                        m.addRow(vec);
                        total = total + precio;
                        jTPrecTotal.setText(String.valueOf(total));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                
            }else{
                     try {
                            r.next();

                            String canactual=String.valueOf(jTProducto.getValueAt(indice, 1));
                            cantidad=Integer.valueOf(prod.Lista[h][1])+Integer.valueOf(canactual);
                            precio = Integer.valueOf(prod.Lista[h][2])*Integer.valueOf(prod.Lista[h][1]);
                            total=total+precio;
                            jTPrecTotal.setText(String.valueOf(total));
                            jTProducto.setValueAt((Integer.valueOf(prod.Lista[h][2])*cantidad),indice, 3);
                            jTProducto.setValueAt(cantidad, indice, 1);

                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
            }
        }
        
           System.out.println(bandera+" "+indice);
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jmborrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmborrarActionPerformed
        int total = Integer.valueOf(String.valueOf(jTProducto.getValueAt(jTProducto.getSelectedRow(), 3)));
        int totalto = Integer.valueOf(jTPrecTotal.getText());
        jTPrecTotal.setText(String.valueOf(totalto-total));
        this.total = Integer.valueOf(jTPrecTotal.getText()); 
        m.removeRow(jTProducto.getSelectedRow());
}//GEN-LAST:event_jmborrarActionPerformed

    private void jBGuardPresupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBGuardPresupActionPerformed
        if(jTProducto.getRowCount()>0){
            boolean bandera= true;//controla que la existencia del producto sea mayor o igual a cero
            int ctrl_false = 0;//controla si uno de los booleanos dio false
            int filas = jTProducto.getRowCount();//obtengo las filas de la tabla jtproducto

            if(bandera==true){
                if(db.Altas("presupuesto","", "('"+jTNumPresup.getText()+"','"+jLidCliente.getText()+"','"+jtnumvendedor.getText()+"','"+jLidCliente.getText()+"','"+(a.getYear()+1900)+"/"+(a.getMonth()+1)+"/"+a.getDate()+"','"+jtdias.getText()+"',NULL,'En Espera')")){
                
                    for(int j=0; j<filas; j++){
                        if(!esUsado(jTProducto.getValueAt(j, 0).toString()))
                            db.Altas("detalle_presupuesto", "", "('"+jTNumPresup.getText()+"','"+jTProducto.getValueAt(j, 0) +"','"+jTProducto.getValueAt(j, 1)+"',"+jTProducto.getValueAt(j, 3)+")");
                        else
                            db.Altas("detalle_presupuesto_usado", "", "('"+jTNumPresup.getText()+"','"+jTProducto.getValueAt(j, 0) +"','"+jTProducto.getValueAt(j, 1)+"',"+jTProducto.getValueAt(j, 3)+")");

                    }
                    jBImprimirPresup.setEnabled(true);
                    JOptionPane.showMessageDialog(null, "El presupuesto se guardó exitosamente ", "Exito", JOptionPane.INFORMATION_MESSAGE);
                    int respuest = JOptionPane.showConfirmDialog(null, "¿Desea ralizar otro presupuesto?","Confirmar",JOptionPane.YES_NO_CANCEL_OPTION);
                    if(respuest==JOptionPane.YES_OPTION){
                        this.dispose();
                            new jDnPresupuesto(null, true, db,Nivel).setVisible(true);
                    }else if (respuest==JOptionPane.NO_OPTION){
                        this.dispose();
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Ha ocurrido  un error en el momento de registrar el presupuesto", "Atencion", JOptionPane.INFORMATION_MESSAGE);
                }

            }else{
                jBImprimirPresup.setEnabled(false);                
            }
        }else{
            JOptionPane.showMessageDialog(rootPane, "Seleccione por lo menos un producto.", "Atención", JOptionPane.WARNING_MESSAGE);
        }
}//GEN-LAST:event_jBGuardPresupActionPerformed

    private void jTProductoAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jTProductoAncestorAdded
    }//GEN-LAST:event_jTProductoAncestorAdded

    private void jTProductoAncestorRemoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jTProductoAncestorRemoved
    }//GEN-LAST:event_jTProductoAncestorRemoved

    private void jTPrecioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTPrecioKeyReleased
        switch(evt.getKeyCode()){
            case KeyEvent.VK_ESCAPE:
            this.dispose();
            break;
            case KeyEvent.VK_F2:
            this.jcNombre.requestFocus();
            break;
            case KeyEvent.VK_F3:
            this.jXTxtfUsados.requestFocus();
            break;
            case KeyEvent.VK_F4:
            jButton1ActionPerformed(null);
            break;
            case KeyEvent.VK_ENTER:
            jBRegProductoActionPerformed(null);
            break;
        }
    }//GEN-LAST:event_jTPrecioKeyReleased

    private void jTCantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTCantidadKeyReleased
        if(evt.getKeyCode()== evt.VK_MINUS){
            jTCantidad.setText("0");
        }
        switch(evt.getKeyCode()){
            case KeyEvent.VK_ESCAPE:
            this.dispose();
            break;
            case KeyEvent.VK_F2:
            this.jcNombre.requestFocus();
            break;
            case KeyEvent.VK_F3:
            this.jXTxtfUsados.requestFocus();
            break;
            case KeyEvent.VK_F4:
            jButton1ActionPerformed(null);
            break;
            case KeyEvent.VK_ENTER:
            jTPrecio.requestFocus();
            jTPrecio.selectAll();
        }
    }//GEN-LAST:event_jTCantidadKeyReleased

    public boolean esUsado(String id){
        if(id.substring(0, 1).equals("F")){
            return false;
        }else{
            return true;
        }
    }
    private void jBRegProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBRegProductoActionPerformed
        boolean bandera = false;
        Integer precio=0;
        Integer cantidad=0;
        String codigo1="";
        String codigo2="";
        int indice = 0;
        if(!jTCantidad.getText().equals("0")){
            jBGuardPresup.setEnabled(true);
            r=null;
            r = db.Listar("*", "usados", "where Nombre_Usados='"+jXTxtfUsados.getText()+"'");
            String vec[]= new String[4];

            //se obtiene la cantidad de filas con que cuenta la tabla
            int hasta= jTProducto.getRowCount();

            System.out.println(String.valueOf(hasta));
            // se recorre la tabla en búsqueda de algun producto que se repita con
            // el que se quiere agregar
            for (int j=0;j<hasta;j++){
                bandera=false;
                codigo1 = String.valueOf(jTProducto.getValueAt(j, 2));
                codigo2 = jXTxtfUsados.getText();
                if(codigo1.equals(codigo2)){
                    bandera=true;
                    indice = j;
                    System.out.println("Hooolaa");
                    break;
                }
                System.out.println(jTProducto.getValueAt(j, 0));

            }

            //si el producto no se ha registrado aún en la venta actual
            if(bandera==false){
                try {
                    //se verifica si este usado ya se ha guardado en la base de datos
                    if(r.next()){
                        //si ya se había guardado antes, entonces sólo se agrega
                        //el usado a la venta actual
                        vec[0]= r.getString("Id_Usados");
                        System.out.println("puto"+ jTCantidad.getText());
                        vec[1]= jTCantidad.getText();
                        vec[2]= r.getString("Nombre_Usados");
                        //se calcula el subtotal
                        precio = Integer.valueOf(jTPrecio.getText())*Integer.valueOf(jTCantidad.getText());
                        vec[3]= String.valueOf(precio);
                        for(int i = 0; i<4 ; i++)
                        System.out.println(vec[i]);
                        m.addRow(vec);
                        //se calcula el costo total de la venta
                        total = total + precio;
                        jTPrecTotal.setText(String.valueOf(total));
                    }else{
                        //si no se había guardado antes, entonces se guardarán los datos ahora
                        db.Altas("usados", "(Nombre_Usados,Precio_Usado)", "('"+jXTxtfUsados.getText()+"',"+jTPrecio.getText()+")");
                        ResultSet usado = db.Listar("Id_Usados","usados"," where Nombre_Usados= '"+jXTxtfUsados.getText()+"'");
                        //se desea saber con que código se guardó el usado
                        usado.next();
                        vec[0] = usado.getString("Id_Usados");
                        vec[1] = jTCantidad.getText();
                        vec[2] = jXTxtfUsados.getText();
                        precio = Integer.valueOf(jTPrecio.getText())*Integer.valueOf(jTCantidad.getText());
                        vec[3] = String.valueOf(precio);

                        m.addRow(vec);

                        total = total + precio;
                        jTPrecTotal.setText(String.valueOf(total));
                        actualizarCampoUsados();
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error1");
                }

            }else{
                String canactual=String.valueOf(jTProducto.getValueAt(indice, 1));
                cantidad=Integer.valueOf(jTCantidad.getText())+Integer.valueOf(canactual);
                precio = Integer.valueOf(jTPrecio.getText())*Integer.valueOf(jTCantidad.getText());
                total=total+precio;
                jTPrecTotal.setText(String.valueOf(total));
                jTProducto.setValueAt((Integer.valueOf(jTPrecio.getText())*cantidad),indice, 3);
                jTProducto.setValueAt(cantidad, indice, 1);
            }
        }else{
            JOptionPane.showMessageDialog(null, "La cantidad debe ser mayor a cero", "Error", JOptionPane.ERROR_MESSAGE);
        }

        System.out.println(bandera+" "+indice);
    }//GEN-LAST:event_jBRegProductoActionPerformed
    public void actualizarCampoUsados(){
     
        List<String> lista = new ArrayList<String>();
        try {
           ResultSet rs=null;
            rs= db.Listar("Nombre_Usados", "usados", "");
            while(rs.next()){
                lista.add(rs.getString(1));
                System.out.println(rs.getString(1));
            }
            AutoCompleteDecorator.decorate(jXTxtfUsados,lista,false);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Error 212",JOptionPane.ERROR_MESSAGE);
        }
        
    }
    private void jBRegProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBRegProductoKeyReleased
        switch(evt.getKeyCode()){
            case KeyEvent.VK_ESCAPE:
            this.dispose();
            break;
            case KeyEvent.VK_F2:
            this.jcNombre.requestFocus();
            break;
            case KeyEvent.VK_F3:
            this.jXTxtfUsados.requestFocus();
            break;
            case KeyEvent.VK_F4:
            jButton1ActionPerformed(null);
            break;
        }
    }//GEN-LAST:event_jBRegProductoKeyReleased

    private void jXTxtfUsadosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jXTxtfUsadosKeyReleased
        switch(evt.getKeyCode()){
            case KeyEvent.VK_ESCAPE:
            this.dispose();
            break;
            case KeyEvent.VK_F2:
            this.jcNombre.requestFocus();
            break;
            case KeyEvent.VK_F3:
            this.jXTxtfUsados.requestFocus();
            break;
            case KeyEvent.VK_F4:
            jButton1ActionPerformed(null);
            break;
            case KeyEvent.VK_ENTER:
            ResultSet usado = db.Listar("Precio_Usado", "usados", "where Nombre_Usados= '"+jXTxtfUsados.getText()+"'");
            try {
                if(usado.next()){
                    jTPrecio.setText(usado.getString(1));
                }
            } catch (SQLException ex) {
                Logger.getLogger(jDnventa.class.getName()).log(Level.SEVERE, null, ex);
            }
            jTCantidad.requestFocus();
            jTCantidad.selectAll();
        }

    }//GEN-LAST:event_jXTxtfUsadosKeyReleased

    private void jtdiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtdiasActionPerformed
        this.jtdias.selectAll();
    }//GEN-LAST:event_jtdiasActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                jDnPresupuesto dialog = new jDnPresupuesto(new javax.swing.JFrame(), true,null,"");
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
    private javax.swing.JButton jBCancelar;
    private javax.swing.JButton jBGuardPresup;
    private javax.swing.JButton jBImprimirPresup;
    private javax.swing.JButton jBRegProducto;
    private javax.swing.JButton jBaddCliente;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLidCliente;
    private javax.swing.JPanel jPCliente;
    private javax.swing.JPanel jPProducto;
    private javax.swing.JPanel jPbotones;
    private javax.swing.JPanel jPcantidad;
    private javax.swing.JPanel jPfactura;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField jTCantidad;
    private javax.swing.JTextField jTFecha;
    private javax.swing.JTextField jTNumPresup;
    private javax.swing.JTextField jTPrecTotal;
    private javax.swing.JTextField jTPrecio;
    private javax.swing.JTable jTProducto;
    private javax.swing.JTextField jTVendedor;
    private org.jdesktop.swingx.JXTextField jXTxtfUsados;
    private javax.swing.JComboBox jcNombre;
    private javax.swing.JMenuItem jmborrar;
    private javax.swing.JFormattedTextField jtdias;
    private javax.swing.JTextField jtnumvendedor;
    // End of variables declaration//GEN-END:variables
}
