/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * jDncompra.java
 *
 * Created on 11/08/2012, 03:36:21 PM
 */
package frames;

import clases.DBConnect;
import java.awt.Image;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Edgar
 */
public class jDncompra extends javax.swing.JDialog {
    private String receptor;
    private String password;
    private String Host;
    private String Puerto;
    private String Database;
    private DefaultTableModel m;
    private DBConnect db=null;
    private int i=0;
    private ResultSet r;
    private Date a;
    private Integer total=0;
    private Integer nro ;
    private Image imagen = getToolkit().getImage(getClass().getResource("/imagenes/global2.png"));
    private String Nivel;
    jDProductos prod;
    /** Creates new form jDncompra */
    public jDncompra(java.awt.Frame parent, boolean modal,DBConnect con,String Nivel) {
        super(parent, modal);
        initComponents();
        
        this.setIconImage(imagen);
        this.Nivel=Nivel;
        receptor=con.getUser();
        password=con.getPass();
        db = con;
        setLocationRelativeTo(null);
        setTitle("Nueva Compra");
        String titulos[] = {"Cod. Producto","Número de Fábrica","Código Original","Cantidad","Descripcion","Precio"};
        m= (DefaultTableModel) jTProducto.getModel();
        m.setColumnIdentifiers(titulos);
        this.jTcantdisponible.setEditable(false);
        this.jtPreciocompra.setEditable(false);
        this.jTNomProducto.setEditable(false);
        this.jTreceptor.setEditable(false);
        this.jtnumreceptor.setEditable(false);
        this.jBRegCompra.setEnabled(false);
        ResultSet nrovent=db.Listar("Id_compra", "compra", "order by Id_compra ASC");
        try {
            if(nrovent.last()){
                nro = Integer.valueOf(nrovent.getString(1));
                jTNumCompra.setText(String.valueOf(nro+1));
            }else{
                nro=1;
                jTNumCompra.setText(String.valueOf(nro));
            }
            
        }catch(SQLException e){
            Logger.getLogger(jDnventa.class.getName()).log(Level.SEVERE, null, e);
        }
        a = new Date();
        jXFecha.setDate(a);
        jTreceptor.setText(this.receptor);
        ResultSet nrovendr = db.Listar("*", "usuario", "where Alias_usu='"+jTreceptor.getText()+"'");
        try {
            if(nrovendr.next()){
                jtnumreceptor.setText(nrovendr.getString(1));
                jTreceptor.setText(nrovendr.getString(2)+" "+nrovendr.getString(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(jDnventa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        actualizarCombopro();
        calcularexistencia();
        actualizarCombo();
        jBImprimirFactura.setEnabled(false);
    }
   /* public jDncompra(java.awt.Frame parent, boolean modal,String host,String Receptor,String pass,String puerto,String database) {
        super(parent, modal);
        initComponents();
        receptor=Receptor;
        password=pass;
        this.Host=host;
        this.Puerto=puerto;
        this.Database=database;
        db = new DBConnect();
        this.setIconImage(imagen);
        db.conectado(Host, receptor, password, Puerto, Database);
        setLocationRelativeTo(null);
        setTitle("Nueva Compra");
        String titulos[] = {"Cod. Producto","Cantidad","Descripcion","Precio"};
        m= (DefaultTableModel) jTProducto.getModel();
        m.setColumnIdentifiers(titulos);
        this.jTcantdisponible.setEditable(false);
        this.jtPreciocompra.setEditable(false);
        this.jTNomProducto.setEditable(false);
        this.jTFecha.setEditable(false);
        this.jTreceptor.setEditable(false);
        this.jtnumreceptor.setEditable(false);
        this.jTNumCompra.setEditable(false);
        ResultSet nrovent=db.Listar("Id_compra", "compra", "order by Id_compra ASC");
        try {
            if(nrovent.last()){
                nro = Integer.valueOf(nrovent.getString(1)+1);
                jTNumCompra.setText(String.valueOf(nro));
            }else{
                nro=1;
                jTNumCompra.setText(String.valueOf(nro));
            }
            
        }catch(SQLException e){
            Logger.getLogger(jDnventa.class.getName()).log(Level.SEVERE, null, e);
        }
        this.i=1;
        a = new Date();
        jTFecha.setText(String.valueOf(a.getDate())+"/"+String.valueOf(a.getMonth()+1)+"/"+String.valueOf(a.getYear()+1900));
        jTreceptor.setText(this.receptor);
        ResultSet nrovendr = db.Listar("*", "receptor", "where Alias_vendedor='"+jTreceptor.getText()+"'");
        try {
            if(nrovendr.next()){
                jtnumreceptor.setText(nrovendr.getString(1));
                jTreceptor.setText(nrovendr.getString(2)+" "+nrovendr.getString(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(jDnventa.class.getName()).log(Level.SEVERE, null, ex);
        }
        jBImprimirFactura.setEnabled(false);
        actualizarCombopro();
        calcularexistencia();
    }*/
   
    public void actualizarCombopro(){
        jCIdProducto.removeAllItems();
        
        try {
           ResultSet rs=null;
            rs= db.Listar("*", "producto", "");
            while(rs.next()){
                jCIdProducto.addItem(rs.getString("Cod_inter_producto"));
                System.out.println(rs.getString("Cod_inter_producto"));
            }
            AutoCompleteDecorator.decorate(jCIdProducto);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
     public void actualizarCombo(){
        try {
            
            
            ResultSet r=null;
            r= db.Listar("*", "proveedor", "");
            while(r.next()){
                jCProveedor.addItem(r.getString("Nom_proveedor")+" ,"+r.getString("Id_proveedor"));
                System.out.println(r.getString("Nom_proveedor")+" , "+r.getString("Id_proveedor"));
            }
            AutoCompleteDecorator.decorate(jCProveedor);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Error 106",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void actualizarexistencia(){
        r=null;
        r=db.Listar("Cant_producto", "producto", " where Cod_inter_producto='"+jCIdProducto.getSelectedItem()+"'");
        try {
            r.next();
            jTcantdisponible.setText(r.getString(1));
        } catch (SQLException ex) {
            Logger.getLogger(jDnventa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void calcularexistencia(){
            actualizarexistencia();
            
            Integer exist=0;
            
          
            int hasta= jTProducto.getRowCount();
            System.out.println(String.valueOf(hasta));
            int indice;
            if(hasta>0){
                for (int j=0;j<hasta;j++){
                    String codigo1 = String.valueOf(jTProducto.getValueAt(j, 0)); /* Obtengo el codigo del producto en la tabla */
                    String codigo2 = String.valueOf(jCIdProducto.getSelectedItem()); /* Obtengo el codigo del producto seleccionado*/
                    if(codigo1.equals(codigo2)){ /* si ambos coinciden */
                       exist = Integer.valueOf(String.valueOf(jTProducto.getValueAt(j,3))); /* se almacena la cantidad registrada del producto */
                       String ex_total=String.valueOf(Integer.valueOf(jTcantdisponible.getText())+exist); /* se calcula la existencia total sumando la cantidad disponible con la cantidad comprada*/
                       jTcantdisponible.setText(String.valueOf(Integer.valueOf(jTcantdisponible.getText())+exist));
                    }    
                             
                }
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

        jPMProductos = new javax.swing.JPopupMenu();
        jMBorrar = new javax.swing.JMenuItem();
        jPcompra = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTNumCompra = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTreceptor = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jtnumreceptor = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jXFecha = new org.jdesktop.swingx.JXDatePicker();
        jLabel1 = new javax.swing.JLabel();
        jCProveedor = new org.jdesktop.swingx.JXComboBox();
        jLabel10 = new javax.swing.JLabel();
        jFTFactura = new javax.swing.JFormattedTextField();
        jLabel16 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jBNProveedor = new javax.swing.JButton();
        jPProducto = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTNomProducto = new javax.swing.JTextField();
        jTcantdisponible = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jCIdProducto = new javax.swing.JComboBox();
        jBAgregarProducto = new javax.swing.JButton();
        jBBuscarProducto = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jtPreciocompra = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTCantidad = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTProducto = new javax.swing.JTable();
        jBRegProducto1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPbotones = new javax.swing.JPanel();
        jBRegCompra = new javax.swing.JButton();
        jBImprimirFactura = new javax.swing.JButton();
        jPcantidad = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jTPrecTotal = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jlcondesc = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();

        jMBorrar.setText("Borrar Item");
        jMBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMBorrarActionPerformed(evt);
            }
        });
        jPMProductos.add(jMBorrar);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPcompra.setBorder(javax.swing.BorderFactory.createTitledBorder("Cabecera de Compra"));

        jLabel2.setText("Compra N°:");

        jLabel3.setText("Fecha:");

        jLabel4.setText("Receptor:");

        jLabel17.setText("Codigo Receptor:");

        jButton1.setText("Modificar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jXFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jXFechaActionPerformed(evt);
            }
        });

        jLabel1.setText("Proveedor:");

        jLabel10.setText("Factura de Compra: ");

        try {
            jFTFactura.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###-### #######")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel16.setText("Tipo de Factura:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Contado", "Crédito" }));

        jBNProveedor.setText("+");
        jBNProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBNProveedorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPcompraLayout = new javax.swing.GroupLayout(jPcompra);
        jPcompra.setLayout(jPcompraLayout);
        jPcompraLayout.setHorizontalGroup(
            jPcompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPcompraLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPcompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPcompraLayout.createSequentialGroup()
                        .addGroup(jPcompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2))
                        .addGap(61, 61, 61)
                        .addGroup(jPcompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPcompraLayout.createSequentialGroup()
                                .addComponent(jTreceptor, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1))
                            .addComponent(jTNumCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPcompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel17)
                            .addComponent(jLabel16))
                        .addGap(18, 18, 18)
                        .addGroup(jPcompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jtnumreceptor)
                            .addComponent(jXFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPcompraLayout.createSequentialGroup()
                        .addGroup(jPcompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPcompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPcompraLayout.createSequentialGroup()
                                .addComponent(jFTFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPcompraLayout.createSequentialGroup()
                                .addComponent(jCProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jBNProveedor)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPcompraLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jCProveedor, jTNumCompra, jTreceptor});

        jPcompraLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jComboBox1, jXFecha, jtnumreceptor});

        jPcompraLayout.setVerticalGroup(
            jPcompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPcompraLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPcompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jTNumCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jXFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPcompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jButton1)
                    .addComponent(jTreceptor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel17)
                    .addComponent(jtnumreceptor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPcompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jFTFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPcompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jCProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBNProveedor))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPProducto.setBorder(javax.swing.BorderFactory.createTitledBorder("Información de Producto"));

        jLabel6.setText("ID. Producto");

        jLabel7.setText("Producto");

        jLabel8.setText("Cantidad disponible:");

        jTcantdisponible.setText("0");

        jLabel9.setText("unidades");

        jCIdProducto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCIdProductoItemStateChanged(evt);
            }
        });
        jCIdProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCIdProductoActionPerformed(evt);
            }
        });
        jScrollPane3.setViewportView(jCIdProducto);

        jBAgregarProducto.setText("Agregar Producto");
        jBAgregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAgregarProductoActionPerformed(evt);
            }
        });

        jBBuscarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/search_icon.png"))); // NOI18N
        jBBuscarProducto.setToolTipText("Buscar Producto");
        jBBuscarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBBuscarProductoActionPerformed(evt);
            }
        });

        jLabel15.setText("Precio Compra:");

        jLabel13.setText("unidades");

        jTCantidad.setText("0");

        jLabel12.setText("Cantidad:");

        javax.swing.GroupLayout jPProductoLayout = new javax.swing.GroupLayout(jPProducto);
        jPProducto.setLayout(jPProductoLayout);
        jPProductoLayout.setHorizontalGroup(
            jPProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPProductoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPProductoLayout.createSequentialGroup()
                        .addGroup(jPProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3)
                            .addComponent(jTNomProducto))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPProductoLayout.createSequentialGroup()
                                .addComponent(jBBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBAgregarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPProductoLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(jPProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPProductoLayout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jtPreciocompra, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPProductoLayout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTcantdisponible, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel9)))))
                        .addGap(67, 67, 67))
                    .addGroup(jPProductoLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(jTCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13))))
        );
        jPProductoLayout.setVerticalGroup(
            jPProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPProductoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPProductoLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, 0, 0, Short.MAX_VALUE)
                    .addComponent(jBBuscarProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBAgregarProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPProductoLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jTcantdisponible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)))
                    .addGroup(jPProductoLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTNomProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))))
                .addGroup(jPProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPProductoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jTCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)))
                    .addGroup(jPProductoLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jtPreciocompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
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
        jTProducto.setColumnSelectionAllowed(true);
        jTProducto.setComponentPopupMenu(jPMProductos);
        jScrollPane4.setViewportView(jTProducto);

        jBRegProducto1.setText("Registrar Producto");
        jBRegProducto1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBRegProducto1ActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        jPbotones.setLayout(new java.awt.GridLayout(1, 0, 20, 0));

        jBRegCompra.setText("Registrar Compra");
        jBRegCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBRegCompraActionPerformed(evt);
            }
        });
        jPbotones.add(jBRegCompra);

        jBImprimirFactura.setText("Visualizar Compra");
        jBImprimirFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBImprimirFacturaActionPerformed(evt);
            }
        });
        jPbotones.add(jBImprimirFactura);

        jPanel1.add(jPbotones);

        jLabel14.setText("Precio Total:");

        jTPrecTotal.setText("0");

        jLabel11.setText("Gs.");

        javax.swing.GroupLayout jPcantidadLayout = new javax.swing.GroupLayout(jPcantidad);
        jPcantidad.setLayout(jPcantidadLayout);
        jPcantidadLayout.setHorizontalGroup(
            jPcantidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPcantidadLayout.createSequentialGroup()
                .addContainerGap(352, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTPrecTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addGap(16, 16, 16))
        );
        jPcantidadLayout.setVerticalGroup(
            jPcantidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPcantidadLayout.createSequentialGroup()
                .addGroup(jPcantidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jTPrecTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPcantidad);

        jlcondesc.setText("100");

        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextArea1CaretUpdate(evt);
            }
        });
        jTextArea1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextArea1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTextArea1);

        jLabel5.setText("Obs.:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jlcondesc))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jPProducto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jBRegProducto1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPcompra, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane4)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPcompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBRegProducto1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jlcondesc, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(jScrollPane4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCIdProductoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCIdProductoItemStateChanged
        if(!"".equals(String.valueOf(jCIdProducto.getSelectedItem()))){
            try{
                 ResultSet resulset;
                resulset=null;
                Integer indice= Integer.valueOf(String.valueOf(jCIdProducto.getSelectedItem()));
                resulset=db.Listar("*", "producto", "where Cod_inter_producto = '"+String.valueOf(indice)+"'");
                resulset.next();
                jTNomProducto.setText(resulset.getString("Nom_producto"));
                jTcantdisponible.setText(resulset.getString(4));
                jtPreciocompra.setText(resulset.getString(6));

            }catch(Exception e){

             }
            calcularexistencia();
        }
    }//GEN-LAST:event_jCIdProductoItemStateChanged

    private void jBBuscarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBuscarProductoActionPerformed
        prod = new jDProductos(null, true, db,1,Nivel);
        prod.setVisible(true);
        //-------------------------------------------------//
                boolean bandera = false;
        Integer precio=0;
        Integer cantidad=0;
        String codigo1="";
        String codigo2="";
        int indice = 0;
        for(int h=0;h<prod.f;h++){
            jBRegCompra.setEnabled(true);
            r=null;
            r = db.Listar("*", "producto", "where Cod_inter_producto='"+prod.Lista[h][0]+"'");
            String vec[]= new String[6];
            int hasta= jTProducto.getRowCount();
            System.out.println(String.valueOf(hasta));
            
            for (int j=0;j<hasta;j++){
                bandera=false;
                codigo1 = String.valueOf(jTProducto.getValueAt(j, 0));
                codigo2 = String.valueOf(prod.Lista[h][0]);
                if(codigo1.equals(codigo2)){
                    bandera=true;
                    indice = j;
                    break;
                }
                System.out.println(prod.Lista[h][0]);
                
            }
            
            
            if(bandera==false){
                try {
                    r.next();
                    vec[0]= r.getString("Cod_inter_producto");
                    vec[1] = r.getString("Marca_Fabrica");
                    vec[2] = r.getString("Cod_Original");
                    vec[3]= prod.Lista[h][1];
                    vec[4]= r.getString("Nom_producto")+" "+r.getString("Marca_producto");
                    precio = Integer.valueOf(prod.Lista[h][2])*Integer.valueOf(prod.Lista[h][1]);
                    vec[5]= String.valueOf(precio);
                    m.addRow(vec);
                    total = total + precio;
                    jTPrecTotal.setText(String.valueOf(total));
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }else{
                try {
                    r.next();
                    
                    String canactual = String.valueOf(jTProducto.getValueAt(indice,3));
                    cantidad = Integer.valueOf(prod.Lista[h][1]) + Integer.valueOf (canactual);
                    precio = Integer.valueOf(r.getString(8)) * Integer.valueOf(prod.Lista[h][1]);
                    total=total+precio;
                    jTPrecTotal.setText(String.valueOf(total));
                    jTProducto.setValueAt((Integer.valueOf(r.getString(8))*cantidad),indice, 5);
                    jTProducto.setValueAt(cantidad, indice, 3);
                    
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        
        calcularexistencia();
        System.out.println(bandera+" "+indice);
    }//GEN-LAST:event_jBBuscarProductoActionPerformed

    private void jTextArea1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextArea1CaretUpdate
         String desc = jTextArea1.getText();
        if(Integer.valueOf(jlcondesc.getText())>0){
            jlcondesc.setText(String.valueOf(100-desc.length()));
        }
        if (Integer.valueOf(jlcondesc.getText())<=0){
            jTextArea1.setEditable(false);
        }
        jlcondesc.setText(String.valueOf(100-desc.length()));
    }//GEN-LAST:event_jTextArea1CaretUpdate

    private void jBRegProducto1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBRegProducto1ActionPerformed
        boolean bandera = false;
        Integer precio=0;
        Integer cantidad=0;
        String codigo1="";
        String codigo2="";
        int indice = 0;
        
        if(!jTCantidad.getText().equals("0")){
            jBRegCompra.setEnabled(true);
            r=null;
            r = db.Listar("*", "producto", "where Cod_inter_producto='"+String.valueOf(jCIdProducto.getSelectedItem())+"'");
            String vec[]= new String[6];
            int hasta= jTProducto.getRowCount();
            System.out.println(String.valueOf(hasta));
            
            for (int j=0;j<hasta;j++){
                bandera=false;
                codigo1 = String.valueOf(jTProducto.getValueAt(j, 0));
                codigo2 = String.valueOf(jCIdProducto.getSelectedItem());
                if(codigo1.equals(codigo2)){
                    bandera=true;
                    indice = j;
                    break;
                }
                System.out.println(jTProducto.getValueAt(j, 0));
                
            }
            
            
            if(bandera==false){
                try {
                    r.next();
                    vec[0]= r.getString(1);
                    vec[1] = r.getString(2);
                    vec[2] = r.getString(3);
                    vec[3]= jTCantidad.getText();
                    vec[4]= r.getString(4)+" "+r.getString(5);
                    precio = Integer.valueOf(r.getString(8))*Integer.valueOf(jTCantidad.getText());
                    vec[5]= String.valueOf(precio);
                    m.addRow(vec);
                    total = total + precio;
                    jTPrecTotal.setText(String.valueOf(total));
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error1");
                }
            }else{
                try {
                    r.next();
                    
                    String canactual = String.valueOf(jTProducto.getValueAt(indice,3));
                    cantidad = Integer.valueOf(jTCantidad.getText()) + Integer.valueOf (canactual);
                    precio = Integer.valueOf(r.getString(8)) * Integer.valueOf(jTCantidad.getText());
                    total=total+precio;
                    jTPrecTotal.setText(String.valueOf(total));
                    jTProducto.setValueAt((Integer.valueOf(r.getString(8))*cantidad),indice, 5);
                    jTProducto.setValueAt(cantidad, indice, 3);
                    
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error2");
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "La cantidad debe ser mayor a cero", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        calcularexistencia();
        System.out.println(bandera+" "+indice);
}//GEN-LAST:event_jBRegProducto1ActionPerformed

    private void jCIdProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCIdProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCIdProductoActionPerformed

    private void jTextArea1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea1KeyPressed
        if (evt.getKeyCode() == evt.VK_BACK_SPACE){

                    jTextArea1.setEditable(true);
                    String desc = jTextArea1.getText();
                    if(desc.length()==100){
                        jlcondesc.setText(String.valueOf(101-desc.length()));
                    }else{
                        jlcondesc.setText(String.valueOf(100-desc.length()));
                    }
            }
        if(evt.getKeyCode()== evt.VK_DELETE){
                    String desc = jTextArea1.getText();
                    jlcondesc.setText(String.valueOf(100-desc.length()));
                    jTextArea1.setEditable(true);
                   
          }
                    String desc = jTextArea1.getText();
                    jlcondesc.setText(String.valueOf(100-desc.length()));
    }//GEN-LAST:event_jTextArea1KeyPressed

    private void jBAgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAgregarProductoActionPerformed

        if (i==0) {
            jCIdProducto.removeAllItems();
            new jDnProducto(null, true,db,true).setVisible(true);
            actualizarCombopro();
        } else {
//            new jDnProducto(null, true, Host, receptor, password, Puerto, Database).setVisible(true);
        }
    }//GEN-LAST:event_jBAgregarProductoActionPerformed

    private void jBRegCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBRegCompraActionPerformed
         boolean bandera= false;//controla que la existencia del producto sea mayor o igual a cero
       int ctrl_false = 0;//controla si uno de los booleanos dio false
        int filas = jTProducto.getRowCount();//obtengo las filas de la tabla jtproducto
        for(int j=0; j<filas; j++){
            ResultSet resultado = null;
            String id_producto = String.valueOf(jTProducto.getValueAt(j, 0));
            resultado = db.Listar("Cant_producto,Nom_producto", "producto", "where Cod_inter_producto='"+id_producto+"'");
            
            Integer cant_producto=0;
            try {
                Integer cant= Integer.valueOf(String.valueOf(jTProducto.getValueAt(j, 3)));
                resultado.next();
                cant_producto = Integer.valueOf(resultado.getString("Cant_producto"));
                cant_producto= (cant_producto)+(cant);
                db.actualizarRegistro("producto","Cant_producto="+cant_producto,"where Cod_inter_producto='"+id_producto+"';");                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this,ex.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
                bandera=false;
            }
            
        }       
                int ind =String.valueOf( jCProveedor.getSelectedItem()).indexOf(",");
                String provee=String.valueOf(jCProveedor.getSelectedItem()).substring(ind+1);
                boolean exito = false;
                if(db.Altas("compra","", "('"+jTNumCompra.getText()+"','"+jtnumreceptor.getText()+"','"+provee+"','"+(a.getYear()+1900)+"/"+(a.getMonth()+1)+"/"+a.getDate()+"','"+jFTFactura.getText()+"','"+(String) jComboBox1.getSelectedItem()+"','"+jTextArea1.getText()+"')")){
                    for(int j=0; j<filas; j++){
                        if(db.Altas("detalle_compra", "", "('"+jTNumCompra.getText()+"','"+jTProducto.getValueAt(j, 0)+"','"+ jTProducto.getValueAt(j, 3)+"','"+jTProducto.getValueAt(j, 5) +"')")){
                            exito= true;
                        }else{
                            exito = false;
                            db.Bajas("compra", "where Id_compra='"+jTNumCompra.getText()+"'");
                            db.Bajas("detalle_compra", "where Id_compra='"+jTNumCompra.getText()+"'");
                            break;
                        }
                        
                    }
                    if(exito){
                        JOptionPane.showMessageDialog(null, "compra registrada con exito", "Exito", JOptionPane.INFORMATION_MESSAGE);
                        int respuest = JOptionPane.showConfirmDialog(null, "¿Desea ralizar otra compra?");
                        if(respuest==JOptionPane.YES_OPTION){
                            this.dispose();
                            new jDncompra(null, true,db,Nivel).setVisible(true);
                        }else{
                            jBImprimirFactura.setEnabled(true);
                        }
                    }
               }else{
                    JOptionPane.showMessageDialog(null, "Ha ocurrido  un error en el momento de registrar la venta", "Atencion", JOptionPane.INFORMATION_MESSAGE);
                }  
                
    }//GEN-LAST:event_jBRegCompraActionPerformed

    private void jBImprimirFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBImprimirFacturaActionPerformed
                try{
            r=null;
            r=db.Listar("*", "vistacompra3", "where Id_compra='"+jTNumCompra.getText()+"'");
        } catch (Exception ex) {
            //Logger.getLogger(ciudad.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         JasperReport reporte;
     JasperPrint reporte_view;
     JRResultSetDataSource jrRS = new JRResultSetDataSource(r);
     
     //Se crea un objeto HashMap
          HashMap parametros = new HashMap();
          parametros.clear();
          //parametros de entrada
         
          Integer SumaTotal = Integer.valueOf(jTPrecTotal.getText());
          String NumCompra = jTNumCompra.getText();
          parametros.put( "SumTotal", SumaTotal+" Gs" );
          parametros.put("NumCompra",NumCompra);
     try{
         //direccion del archivo JASPER
          URL  in = this.getClass().getClassLoader().getResource("reportes/DetalleCompra.jasper");
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
    }//GEN-LAST:event_jBImprimirFacturaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jTreceptor.setEditable(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jXFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jXFechaActionPerformed
        this.a = jXFecha.getDate();
        
    }//GEN-LAST:event_jXFechaActionPerformed

    private void jMBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMBorrarActionPerformed
        if(JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea remover éste item?", "Borrar Producto", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
            m.removeRow(jTProducto.getSelectedRow());
        }
    }//GEN-LAST:event_jMBorrarActionPerformed

    private void jBNProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBNProveedorActionPerformed
        new jDnProveedor(null, true, db, 0).setVisible(true);
        actualizarCombo();
    }//GEN-LAST:event_jBNProveedorActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                jDncompra dialog = new jDncompra(new javax.swing.JFrame(), true,null,"");
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
    private javax.swing.JButton jBAgregarProducto;
    private javax.swing.JButton jBBuscarProducto;
    private javax.swing.JButton jBImprimirFactura;
    private javax.swing.JButton jBNProveedor;
    private javax.swing.JButton jBRegCompra;
    private javax.swing.JButton jBRegProducto1;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jCIdProducto;
    private org.jdesktop.swingx.JXComboBox jCProveedor;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JFormattedTextField jFTFactura;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMBorrar;
    private javax.swing.JPopupMenu jPMProductos;
    private javax.swing.JPanel jPProducto;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPbotones;
    private javax.swing.JPanel jPcantidad;
    private javax.swing.JPanel jPcompra;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField jTCantidad;
    private javax.swing.JTextField jTNomProducto;
    private javax.swing.JTextField jTNumCompra;
    private javax.swing.JTextField jTPrecTotal;
    private javax.swing.JTable jTProducto;
    private javax.swing.JTextField jTcantdisponible;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTreceptor;
    private org.jdesktop.swingx.JXDatePicker jXFecha;
    private javax.swing.JLabel jlcondesc;
    private javax.swing.JTextField jtPreciocompra;
    private javax.swing.JTextField jtnumreceptor;
    // End of variables declaration//GEN-END:variables
}
