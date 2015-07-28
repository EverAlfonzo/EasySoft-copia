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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.text.BadLocationException;
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
public class jDnventa extends javax.swing.JDialog {
Image imagen = getToolkit().getImage(getClass().getResource("/imagenes/global2.png"));
DefaultTableModel m;
Connection con;
private  Numero_a_Letra NL = new  Numero_a_Letra();
private jDProductos prod;
DBConnect db;
ResultSet r;
int i=0;
Date a;
Integer total=0;
Integer existencia=0;
int nro =1;
int nro2=1;
private boolean ex= true;// controla si la existencia del producto es suficiente
private String Nivel;
/** Creates new form jDnventa */
    public jDnventa(java.awt.Frame parent, boolean modal,DBConnect con,String Nivel) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        db = con;
        
        jBRegistrarventa.setEnabled(false);
        this.Nivel=Nivel;
        jcNombre.setEditable(true);
        AutoCompleteDecorator.decorate(jcNombre);
        setIconImage(imagen);
        this.setTitle("Agregar Venta");
        
        jTVendedor.setText(con.getUser());
        jTVendedor.setEditable(false);
        jBRegProducto.setEnabled(false);
        actualizarCombo();
        actualizarCampoUsados();
        a = new Date();
        jTFecha.setText(String.valueOf(a.getDate())+"/"+String.valueOf(a.getMonth()+1)+"/"+String.valueOf(a.getYear()+1900));
        jTFecha.setEditable(false);
        jtnumvendedor.setEditable(false);
        String titulos[] = {"Cod. Producto","Cantidad","Descripcion","Sub-Total"};
        m= (DefaultTableModel) jTProducto.getModel();
        m.setColumnIdentifiers(titulos);
        r=db.Listar("Id_venta", "venta", " order by Id_venta ASC");
        try {
            
            if(r.last()) nro = Integer.valueOf(r.getString(1))+1;
            
            if(nro>0 && nro <=9){
                jTNumFac.setText("000000"+nro);
            }
            if(nro>=10 && nro<=99){
                jTNumFac.setText("00000"+nro);
            }
            if(nro>=100 && nro<=999){
                jTNumFac.setText("0000"+nro);
            }
            if(nro>=1000 && nro<=9999){
                jTNumFac.setText("000"+nro);
            }
            if(nro>=10000 && nro<=99999){
                jTNumFac.setText("00"+nro);
            }
            if(nro>=100000 && nro<=999999){
                jTNumFac.setText("0"+nro);
            }
            if(nro>=1000000 && nro<=9999999){
                jTNumFac.setText(""+nro);
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
            Logger.getLogger(jDnventa.class.getName()).log(Level.SEVERE, null, ex);
        }
        jBImprimirFactura.setEnabled(false);
        prod = new jDProductos(null, true, db,true,Nivel);
    }
   
    public jDnventa(java.awt.Frame parent, boolean modal,DBConnect con,String presupuesto,String Nivel) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        db = con;
        
        jBRegistrarventa.setEnabled(false);
        this.Nivel=Nivel;
        jcNombre.setEditable(true);
        AutoCompleteDecorator.decorate(jcNombre);
        setIconImage(imagen);
        this.setTitle("Agregar Venta");
      
        jTVendedor.setText(con.getUser());
        jTVendedor.setEditable(false);
        actualizarCombo();
        actualizarCampoUsados();
        a = new Date();
        jTFecha.setText(String.valueOf(a.getDate())+"/"+String.valueOf(a.getMonth()+1)+"/"+String.valueOf(a.getYear()+1900));
        jTFecha.setEditable(false);
        jTNumFac.setEditable(false);
        jtnumvendedor.setEditable(false);
        String titulos[] = {"Cod. Producto","Cantidad","Descripcion","Sub-Total"};
        m= (DefaultTableModel) jTProducto.getModel();
        m.setColumnIdentifiers(titulos);
        r=db.Listar("Id_venta", "venta", " order by Id_venta ASC");
        try {
            
            if(r.last())
            nro = Integer.valueOf(r.getString(1))+1;
            if(nro>0 && nro <=9){
                jTNumFac.setText("000000"+nro);
            }
            if(nro>=10 && nro<=99){
                jTNumFac.setText("00000"+nro);
            }
            if(nro>=100 && nro<=999){
                jTNumFac.setText("0000"+nro);
            }
            if(nro>=1000 && nro<=9999){
                jTNumFac.setText("000"+nro);
            }
            if(nro>=10000 && nro<=99999){
                jTNumFac.setText("00"+nro);
            }
            if(nro>=100000 && nro<=999999){
                jTNumFac.setText("0"+nro);
            }
            if(nro>=1000000 && nro<=9999999){
                jTNumFac.setText(""+nro);
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
            Logger.getLogger(jDnventa.class.getName()).log(Level.SEVERE, null, ex);
        }
        jBImprimirFactura.setEnabled(false);
        
        prod = new jDProductos(null, true, db,true,Nivel);
        jTNumFac.requestFocus();
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
    
    public void calcularNumeroFactura(){
         r=db.Listar("Id_venta", "venta", " order by Id_venta ASC");
         String fact1,fact2,fact3;
         
         try {
            
            if(r.last()){
               nro = Integer.valueOf(r.getString(1))+1;
               fact1= String.valueOf(nro).substring(0, 1);
               fact2= String.valueOf(nro).substring(1, 3);
               fact3= String.valueOf(nro+1).substring(4, String.valueOf(nro).length());
            }
            
            if(nro>0 && nro <=9){
                jTNumFac.setText("000000"+nro);
            }
            if(nro>=10 && nro<=99){
                jTNumFac.setText("00000"+nro);
            }
            if(nro>=100 && nro<=999){
                jTNumFac.setText("0000"+nro);
            }
            if(nro>=1000 && nro<=9999){
                jTNumFac.setText("000"+nro);
            }
            if(nro>=10000 && nro<=99999){
                jTNumFac.setText("00"+nro);
            }
            if(nro>=100000 && nro<=999999){
                jTNumFac.setText("0"+nro);
            }
            if(nro>=1000000 && nro<=9999999){
                jTNumFac.setText(""+nro);
            }
          
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
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
        jLabel8 = new javax.swing.JLabel();
        jPfactura = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTFecha = new javax.swing.JTextField();
        jTVendedor = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jtnumvendedor = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jTNumFac = new javax.swing.JFormattedTextField();
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
        jPcantidad = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jTPrecTotal = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jBImprimirFactura = new javax.swing.JButton();
        jBRegistrarventa = new javax.swing.JButton();
        jBCancelar = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTProducto = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

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

        jPCliente.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de Cliente"));
        jPCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jPClienteKeyReleased(evt);
            }
        });

        jLabel1.setText("Nombre del Cliente:");

        jBaddCliente.setText("Agregar Cliente");
        jBaddCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBaddClienteActionPerformed(evt);
            }
        });
        jBaddCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jBaddClienteKeyReleased(evt);
            }
        });

        jLabel16.setText("ID. Cliente:");

        jLidCliente.setText("Nro");

        jScrollPane2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jScrollPane2KeyReleased(evt);
            }
        });

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
        jcNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jcNombreKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jcNombre);

        jLabel8.setText("[?]");
        jLabel8.setToolTipText("Presione F2 para acceder al campo");

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
                    .addGroup(jPClienteLayout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addGap(18, 18, 18)
                        .addComponent(jBaddCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addGap(89, 89, 89))
                    .addGroup(jPClienteLayout.createSequentialGroup()
                        .addComponent(jLidCliente)
                        .addGap(178, 178, 178))))
        );
        jPClienteLayout.setVerticalGroup(
            jPClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jBaddCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                        .addComponent(jLabel8))
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLidCliente))
                .addContainerGap())
        );

        jPfactura.setBorder(javax.swing.BorderFactory.createTitledBorder("Cabecera de Factura"));

        jLabel2.setText("Factura N°:");

        jLabel3.setText("Fecha:");

        jTFecha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFechaKeyReleased(evt);
            }
        });

        jTVendedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTVendedorKeyReleased(evt);
            }
        });

        jLabel4.setText("Vendedor:");

        jLabel5.setText("Cond. de Venta:");

        jLabel17.setText("Codigo Vendedor:");

        jtnumvendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtnumvendedorActionPerformed(evt);
            }
        });
        jtnumvendedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtnumvendedorKeyReleased(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Contado", "Crédito" }));
        jComboBox1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jComboBox1KeyReleased(evt);
            }
        });

        try {
            jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField1.setText("001-002");
        jFormattedTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField1ActionPerformed(evt);
            }
        });

        try {
            jTNumFac.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#######")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

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
                .addGroup(jPfacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPfacturaLayout.createSequentialGroup()
                        .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTNumFac))
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(jPfacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPfacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTFecha, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                    .addComponent(jtnumvendedor))
                .addContainerGap())
        );
        jPfacturaLayout.setVerticalGroup(
            jPfacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPfacturaLayout.createSequentialGroup()
                .addGroup(jPfacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPfacturaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPfacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel2)
                            .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTNumFac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPfacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel4)
                            .addComponent(jTVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPfacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPfacturaLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jTFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPfacturaLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPfacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPfacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPfacturaLayout.createSequentialGroup()
                                    .addGap(27, 27, 27)
                                    .addComponent(jLabel17))
                                .addComponent(jLabel3))
                            .addComponent(jtnumvendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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

        jXTxtfUsados.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jXTxtfUsadosCaretUpdate(evt);
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
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

        jPcantidad.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel14.setText("Precio Total:");

        jTPrecTotal.setText("0");

        jLabel11.setText("Gs.");

        jBImprimirFactura.setText("Imprimir Factura");
        jBImprimirFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBImprimirFacturaActionPerformed(evt);
            }
        });

        jBRegistrarventa.setMnemonic('R');
        jBRegistrarventa.setText("Registrar Venta");
        jBRegistrarventa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBRegistrarventaActionPerformed(evt);
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
                .addComponent(jBImprimirFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jBRegistrarventa, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 383, Short.MAX_VALUE)
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
                .addGroup(jPcantidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jBRegistrarventa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPcantidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTPrecTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14)
                        .addComponent(jLabel11)
                        .addComponent(jBImprimirFactura, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                        .addComponent(jBCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
        jTProducto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTProductoFocusLost(evt);
            }
        });
        jScrollPane4.setViewportView(jTProducto);

        jLabel18.setText("Para agregar productos registrados a la venta, presionar F4");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/search_icon.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jButton1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPcantidad, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPfactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel18)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane4)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jLabel18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jPfactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(jPCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)))
                .addComponent(jPcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBaddClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBaddClienteActionPerformed
            new jDnuevocliente(null, true,db,1).setVisible(true);

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

    private void jBRegProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBRegProductoActionPerformed
        boolean bandera = false;
        Integer precio=0;
        Integer cantidad=0;
        String codigo1="";
        String codigo2="";
        int indice = 0;
        if((!jTCantidad.getText().equals("0")) && (!jXTxtfUsados.getText().equals(""))){
            jBRegistrarventa.setEnabled(true);
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
                        String marcaUsado = JOptionPane.showInputDialog(rootPane, "Introduzca la Marca del Usado: ","Marca de Usado", JOptionPane.QUESTION_MESSAGE);
                        db.Altas("usados", "(Nombre_Usados,Precio_Usado,Marca_Usado,detalle_usado)", "('"+jXTxtfUsados.getText()+"',"+jTPrecio.getText()+",'"+marcaUsado+"','Usado')");
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

    private void jBRegistrarventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBRegistrarventaActionPerformed
       boolean bandera= false;//controla que la existencia del producto sea mayor o igual a cero
       int ctrl_false = 0;//controla si uno de los booleanos dio false
        int filas = jTProducto.getRowCount();//obtengo las filas de la tabla jtproducto
               String nFact="" ;
//              nFact = jFormattedTextField1.getText().substring(2, 3)+jFormattedTextField1.getText().substring(5,6);
        if(db.Altas("venta","", "('"+nFact+jTNumFac.getText()+"','"+(a.getYear()+1900)+"/"+(a.getMonth()+1)+"/"+a.getDate()+"','"+jLidCliente.getText()+"','"+jtnumvendedor.getText()+"','"+jComboBox1.getSelectedItem().toString()+"','',0,'Emitido')")){
             for(int j=0; j<filas; j++){
                 int subtotal = Integer.valueOf(String.valueOf(jTProducto.getValueAt(j, 3)));
                 int cantidad = Integer.valueOf(String.valueOf(jTProducto.getValueAt(j, 1)));
                 double preVenta = subtotal/cantidad;
                 if(!esUsado((String)jTProducto.getValueAt(j, 0))){

                     db.Altas("detalle_venta", "", "('"+nFact+jTNumFac.getText()+"','"+jTProducto.getValueAt(j, 0)+"','"+jTProducto.getValueAt(j, 1)+"','"+String.valueOf(preVenta)+"')");
                     ResultSet rs = db.Listar("Cant_producto", "producto", " where Cod_inter_producto = '"+jTProducto.getValueAt(j, 0).toString()+"'");
                     int cant=0;
                     try {
                         rs.next();
                         cant = rs.getInt("Cant_producto");
                     } catch (SQLException ex) {
                         Logger.getLogger(jDnventa.class.getName()).log(Level.SEVERE, null, ex);
                     }

                     db.actualizarRegistro("producto", "Cant_producto = "+String.valueOf(cant - Integer.valueOf((String) jTProducto.getValueAt(j, 1)))," where Cod_Inter_producto = '"+jTProducto.getValueAt(j, 0)+"'");
                 }
                 else
                     db.Altas("detalle_venta_usados", "", "('"+nFact+jTNumFac.getText()+"','"+jTProducto.getValueAt(j, 0)+"','"+jTProducto.getValueAt(j, 1)+"','"+String.valueOf(preVenta)+"')");
             }
             jBImprimirFactura.setEnabled(true);
             JOptionPane.showMessageDialog(null, "venta registrada con exito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
             int print = JOptionPane.showConfirmDialog(null, "¿Desea expedir la factura?","Confirmar",JOptionPane.YES_NO_OPTION);

             if(JOptionPane.showConfirmDialog(null, "¿Desea guardar algun comentario u observación sobre la venta?","Confirmar",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                 String com = JOptionPane.showInputDialog(rootPane,"Introduzca la Observación", "Observación",JOptionPane.QUESTION_MESSAGE);
                 db.actualizarRegistro("venta", "observacion='"+com+"' ", "where Id_venta= "+nFact+jTNumFac.getText());
             }

             if(print==JOptionPane.YES_OPTION){
                 jBImprimirFacturaActionPerformed(evt);
             }

             int respuest = JOptionPane.showConfirmDialog(null, "¿Desea ralizar otra venta?","Confirmar",JOptionPane.YES_NO_OPTION);
             if(respuest==JOptionPane.YES_OPTION){
                 this.dispose();
                     new jDnventa(null, true, db,Nivel).setVisible(true);
             }else{
                 this.dispose();
             }



        }else{
             JOptionPane.showMessageDialog(null, "Ha ocurrido  un error en el momento de registrar la venta", "Atencion", JOptionPane.INFORMATION_MESSAGE);
         }
     jBImprimirFactura.setEnabled(false);
    }//GEN-LAST:event_jBRegistrarventaActionPerformed

    public boolean esUsado(String id){
        if(id.substring(0, 1).equals("F")){
            return false;
        }else{
            return true;
        }
    }
    private void jBCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jBCancelarActionPerformed

    private void jBImprimirFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBImprimirFacturaActionPerformed
        String tipoVenta=new String();
        String nFact="" ;
//        nFact = jFormattedTextField1.getText().substring(2, 3)+jFormattedTextField1.getText().substring(5,6);
        try{
           r=null;
           r=db.Listar("*", "vistafactura where Id_venta='"+nFact+jTNumFac.getText()+"' union select * from vistafacturaused", "where Id_venta='"+jTNumFac.getText()+"'");
           ResultSet rs = db.Listar("Tipo_venta", "vistafactura", "where Id_venta='"+nFact+jTNumFac.getText()+"'");
           if(rs.next())
           tipoVenta = rs.getString(1);
           
        } catch (SQLException ex) {
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
          String NumFactura = jTNumFac.getText();
          Integer SumaTotal = Integer.valueOf(jTPrecTotal.getText());
          parametros.put( "NumFactura",NumFactura );
          parametros.put( "NumLiteral", Total );          
          parametros.put( "SumaTotal", SumaTotal );
     try{
         //direccion del arfchivo JASPER
         if(tipoVenta.equals("Contado")){
          URL  in = this.getClass().getClassLoader().getResource("reportes/Factura1.jasper");
          reporte = (JasperReport) JRLoader.loadObject( in );
         }else{
             URL  in = this.getClass().getClassLoader().getResource("reportes/Factura2.jasper");
            reporte = (JasperReport) JRLoader.loadObject( in );
         }
          
          
          //-----------------------------------
          reporte_view = JasperFillManager.fillReport(reporte, parametros, jrRS);
          JasperViewer ventana = new JasperViewer(reporte_view,false);
          ventana.setTitle("Vista Previa");
          ventana.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
          ventana.setVisible(true);
          
          
	  }catch (JRException E){
              JOptionPane.showMessageDialog(null, E.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
          }
    }//GEN-LAST:event_jBImprimirFacturaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        prod.Lista= new String[50][3];
        prod.c=prod.f=0;
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
            
            
            jBRegistrarventa.setEnabled(true);
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
                        System.out.println("puto"+ jTCantidad.getText());
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
                            JOptionPane.showMessageDialog(null, "Error2");
                        }
            }
        }
        
           System.out.println(bandera+" "+indice);
            
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jmborrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmborrarActionPerformed
        int total1 = Integer.valueOf(String.valueOf(jTProducto.getValueAt(jTProducto.getSelectedRow(), 3)));
        int totalto = Integer.valueOf(jTPrecTotal.getText());
        
//        jTPrecTotal.setText(String.valueOf(totalto-total1));
//        total = Integer.valueOf(jTPrecTotal.getText());
//        int cantdisp = Integer.valueOf(jTcantdisponible.getText());
//        int canttabla = Integer.valueOf(String.valueOf(jTProducto.getValueAt(jTProducto.getSelectedRow(), 1)));
//        jTcantdisponible.setText(String.valueOf(cantdisp+canttabla));
//        m.removeRow(jTProducto.getSelectedRow());
        
}//GEN-LAST:event_jmborrarActionPerformed

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

    private void jTProductoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTProductoFocusLost
        System.out.println(jTProducto.getRowCount());
        if(jTProducto.getRowCount()==-1){
            jBRegistrarventa.setEnabled(false);
        }
    }//GEN-LAST:event_jTProductoFocusLost

    private void jTVendedorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTVendedorKeyReleased
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

    }//GEN-LAST:event_jTVendedorKeyReleased

    private void jComboBox1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox1KeyReleased
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

    }//GEN-LAST:event_jComboBox1KeyReleased

    private void jTFechaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFechaKeyReleased
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

    }//GEN-LAST:event_jTFechaKeyReleased

    private void jtnumvendedorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtnumvendedorKeyReleased
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

    }//GEN-LAST:event_jtnumvendedorKeyReleased

    private void jcNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jcNombreKeyReleased
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

    }//GEN-LAST:event_jcNombreKeyReleased

    private void jBaddClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBaddClienteKeyReleased
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

    }//GEN-LAST:event_jBaddClienteKeyReleased

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

    private void jButton1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyReleased
                switch(evt.getKeyCode()){
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

    }//GEN-LAST:event_jButton1KeyReleased

    private void jScrollPane2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jScrollPane2KeyReleased
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

    }//GEN-LAST:event_jScrollPane2KeyReleased

    private void jPClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPClienteKeyReleased
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

    }//GEN-LAST:event_jPClienteKeyReleased

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){
                this.setVisible(false);
        }

    }//GEN-LAST:event_jButton1KeyPressed

    private void jXTxtfUsadosCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jXTxtfUsadosCaretUpdate
        if(jXTxtfUsados.getText().equals("")){
            jBRegProducto.setEnabled(false);
        }else{
            jBRegProducto.setEnabled(true);
        }
    }//GEN-LAST:event_jXTxtfUsadosCaretUpdate

    private void jtnumvendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtnumvendedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtnumvendedorActionPerformed

    private void jFormattedTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                jDnventa dialog = new jDnventa(new javax.swing.JFrame(), true,null,"");
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
    private javax.swing.JButton jBImprimirFactura;
    private javax.swing.JButton jBRegProducto;
    private javax.swing.JButton jBRegistrarventa;
    private javax.swing.JButton jBaddCliente;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JFormattedTextField jFormattedTextField1;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLidCliente;
    private javax.swing.JPanel jPCliente;
    private javax.swing.JPanel jPProducto;
    private javax.swing.JPanel jPcantidad;
    private javax.swing.JPanel jPfactura;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField jTCantidad;
    private javax.swing.JTextField jTFecha;
    private javax.swing.JFormattedTextField jTNumFac;
    private javax.swing.JTextField jTPrecTotal;
    private javax.swing.JTextField jTPrecio;
    private javax.swing.JTable jTProducto;
    private javax.swing.JTextField jTVendedor;
    private org.jdesktop.swingx.JXTextField jXTxtfUsados;
    private javax.swing.JComboBox jcNombre;
    private javax.swing.JMenuItem jmborrar;
    private javax.swing.JTextField jtnumvendedor;
    // End of variables declaration//GEN-END:variables
}
