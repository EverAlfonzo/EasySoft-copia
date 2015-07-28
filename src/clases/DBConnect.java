/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import com.mysql.jdbc.PreparedStatement;
import java.io.FileInputStream;
import java.io.Serializable;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Edgar
 */
public class DBConnect implements Serializable{
    private String bd="dbeasysoft-act";
    private String host="localhost";
    private String puerto="3306";
    private String user="";
    private String pass="";
    private Connection conectar = null;
    public DBConnect(){
      
   }

    public String getPass() {
        return pass;
    }

    public String getUser() {
        return user;
    }
    
  
    
    public   boolean conectado(String host,String Usuario, String Pass,String puerto,String database){
        this.host = host; 
        this.puerto = puerto;
        this.bd = database;
        this.user = Usuario;
        this.pass = Pass;
        
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            conectar = DriverManager.getConnection("jdbc:mysql://"+host+":"+puerto+"/"+database,Usuario,Pass);
            
            return true;
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Error Al Conectar 1",JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
    } 
    public boolean conectado(String Usuario, String Pass){
        

        this.user = Usuario;
        this.pass = Pass;
        
        try{
             Class.forName("com.mysql.jdbc.Driver");
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            conectar = DriverManager.getConnection("jdbc:mysql://"+this.host+":"+this.puerto+"/"+this.bd,Usuario,Pass);
            
            return true;
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Error Al Conectar",JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
    }
    /**
     * Retorna el resulset con el resultado de la consulta
     * @param campos
     * @param tabla
     * @param condicion
     * @return 
     */
    public ResultSet Listar(String campos,String tabla,String condicion){
        ResultSet rs=null;
        try {
            Statement s= conectar.createStatement();
            System.out.println("select "+campos+" from "+tabla+" "+ condicion);
            rs = s.executeQuery("select "+campos+" from "+tabla+" "+ condicion);
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, ex.getMessage(), "Error2", JOptionPane.ERROR_MESSAGE);
        }
        return rs;
    }

    public void consulta ( String sql){
        try {
            Statement s= conectar.createStatement();
            System.out.println(sql);
            s.executeUpdate(sql);
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, ex.getMessage(), "Error2", JOptionPane.ERROR_MESSAGE);
        }

    }
    public void desconectar(){
        try {
            conectar.close();
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Usted no ha iniciado sesion", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public boolean ConsActualizacion(String sql){
        Statement stmt;
        boolean resultado=false;
        try{
            stmt = conectar.createStatement();
            stmt.executeUpdate(sql);
            resultado=true;
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            resultado=false;
        }
        return resultado;
    }
    
    public boolean Altas(String tabla,String campos,String valores){
        Statement stmt;
        boolean resultado=false;
        try {
            stmt = conectar.createStatement();
            System.out.println("INSERT INTO "+tabla+" "+campos+" values "+valores+";");
            stmt.executeUpdate("INSERT INTO "+tabla+" "+campos+" values "+valores+";");
            resultado=true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            resultado = false;
        }
        return resultado;
    }
    public boolean Altas(String [] campos, FileInputStream foto){
        PreparedStatement ps;
        boolean resultado=false;
        for(int i=0; i<campos.length; i++){
            System.out.println(campos[i]+" ");
        }
        
        try {
            ps = (PreparedStatement) conectar.prepareStatement("INSERT INTO producto VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, campos[0]);
            ps.setString(2, campos[1]);
            ps.setString(3, campos[2]);
            ps.setString(4, campos[3]);
            ps.setString(5, campos[4]);
            ps.setString(6, campos[5]);
            ps.setString(7, campos[6]);
            ps.setString(8, campos[7]);
            ps.setString(9, campos[8]);
            ps.setString(10, campos[9]);
            ps.setString(11, campos[10]);
            ps.setString(12, campos[11]);
            ps.setString(13, campos[12]);
            ps.setString(15, campos[13]);
            if(foto!=null){
                ps.setBinaryStream(14, foto);
            }else{
                ps.setString(14, "");
            }
            ps.executeUpdate();
            resultado = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            resultado = false;
        }
        
        
        return resultado;
    }
    
    public boolean Altas(String tabla,String [] campos, FileInputStream foto){
        PreparedStatement ps;
        boolean resultado=false;
        for(int i=0; i<campos.length; i++){
            System.out.println(campos[i]+" ");
        }
        
        try {
            ps = (PreparedStatement) conectar.prepareStatement("INSERT INTO "+tabla+" VALUES(?,?,?,?,?,?,?)");
            ps.setString(1, campos[0]);
            ps.setString(2, campos[1]);
            ps.setString(3, campos[2]);
            ps.setString(4, campos[3]);
            ps.setString(5, campos[4]);
            ps.setString(6, campos[5]);
           
            if(foto!=null){
                ps.setBinaryStream(7, foto);
             
            }
            ps.executeUpdate();
            resultado = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            resultado = false;
        }
        
        
        return resultado;
    }
    

    public boolean Bajas(String tabla,String condicion){
        boolean result=false;
        Statement s;
        try{
            s = conectar.createStatement();
            s.executeUpdate("Delete From "+tabla+" "+condicion);
            result=true;
        }catch(Exception e){
            result=false;
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return result;
    }
    public boolean crearUsuario( String Usuario, String pass,String frame){
            boolean resultado=false;
            try{
                java.sql.Statement stmt = conectar.createStatement();
                stmt.executeUpdate("Create user '"+Usuario+"'@'localhost' identified by '"+pass+"'");
                stmt.executeUpdate("flush privileges");
                stmt.executeUpdate("Create user '"+Usuario+"'@'%' identified by '"+pass+"'");
                stmt.executeUpdate("Flush Privileges");
                stmt.executeUpdate("grant all privileges on *.* to '"+Usuario+"'@'localhost' identified by '"+pass+"' with grant option;");
                stmt.executeUpdate("flush privileges");
                stmt.executeUpdate("grant all privileges on *.* to '"+Usuario+"'@'%' identified by '"+pass+"' with grant option;");
                stmt.executeUpdate("flush privileges");
                JOptionPane.showMessageDialog(null, frame+" Creado Correctamente","Exito", JOptionPane.INFORMATION_MESSAGE);
                resultado = true;
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Error: "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                resultado = false;

            } 
            return resultado;
    }
    public boolean borrarUsuario(String Usuario){
     boolean resultado= false;
        try {
            Statement stmt = conectar.createStatement();
            System.out.println("drop user '"+Usuario+"'@'localhost';");
            stmt.executeUpdate("drop user '"+Usuario+"'@'localhost';");
            stmt.executeUpdate("flush privileges");
            stmt.executeUpdate("drop user '"+Usuario+"'@'%'");
            stmt.executeUpdate("flush privileges");
            JOptionPane.showMessageDialog(null, "El Usuario fue eliminado correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return resultado;

    }
     public boolean actualizarRegistro(String tabla, String valores, String condicion){
        int resultado;
        try{
            Statement stmt = conectar.createStatement();
            System.out.println("update " + tabla + " set " + valores 
                    + " " + condicion);
            resultado = stmt.executeUpdate("update " + tabla + " set " + valores 
                    + " " + condicion);
            
            return true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", 
                    JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
    }
     
    public boolean actualizarRegistro(String [] campos, FileInputStream foto){
        PreparedStatement ps;
        boolean resultado=false;
        for(int i=0; i<campos.length; i++){
            System.out.println(campos[i]+" ");
        }
        
        try {
            ps = (PreparedStatement) conectar.prepareStatement("UPDATE producto set Marca_Fabrica=? , Cod_Original = ?,"
                    + "Nom_producto = ?, Marca_producto = ?, detalle_producto = ? , ubica_producto=? , cant_producto = ?,"
                    + "cant_min= ? ,pre_venta= ?, pre_compra = ?, Proveedor_Id_proveedor = ?, nombre_imagen= ?, imagen = ?, ruta_imagen= ? where Cod_inter_producto= ?  ");
            ps.setString(1, campos[0]);
            ps.setString(2, campos[1]);
            ps.setString(3, campos[2]);
            ps.setString(4, campos[3]);
            ps.setString(5, campos[4]);
            ps.setString(6, campos[5]);
            ps.setString(7, campos[6]);
            ps.setString(8, campos[7]);
            ps.setString(9, campos[8]);
            ps.setString(10, campos[9]);
            ps.setString(11, campos[10]);
            ps.setString(12, campos[11]);
            if(foto!=null){
                ps.setBinaryStream(13, foto);
            }
            
            ps.setString(14, campos[12]);
            ps.setString(15, campos[13]);
            ps.executeUpdate();
            resultado = true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            resultado = false;
        }
        
        
        return resultado;
    }
    
    public boolean actualizarRegistro(String [] campos, FileInputStream foto,String tipo){
        PreparedStatement ps;
        boolean resultado   =false;
        for(int i=0; i<campos.length; i++){
            System.out.println(campos[i]+" ");
        }
        try {
            ps = (PreparedStatement) conectar.prepareStatement("UPDATE "+tipo+" set  Notas_usu= ?,Imagen_usu = ?"
                    + " where Alias_usu='"+campos[1]+"'");
            ps.setString(1, campos[0]);

            if(foto!=null){
                ps.setBinaryStream(2, foto);
            }

            ps.executeUpdate();
            resultado = true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
            resultado = false;
        }
        
        return resultado;
    }
      public Connection getConnection(){
           return conectar;
    }

}