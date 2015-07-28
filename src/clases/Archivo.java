/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Ever
 */
public class Archivo {
    String ruta;
    String nombreArchivo;
    DBConnect Gdato;
    File f;
    public Archivo(String ruta){
        this.ruta = ruta;
        f= new File(ruta);
    }
    
    /**
     *
     * @param Ldato
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void guardar(DBConnect Ldato) throws FileNotFoundException, IOException{
        FileOutputStream fos = new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(Ldato);
        
        fos.close();
        oos.close();
    }
    
    public DBConnect cargar() throws FileNotFoundException, IOException, ClassNotFoundException{
        if(f.exists()){
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Gdato = (DBConnect) ois.readObject();
            
            fis.close();
            ois.close();
            
            return Gdato;
        }else{
            f.createNewFile();
            return null;
        }
    }
    
    public boolean existe(){
        return f.exists();
    }
}
