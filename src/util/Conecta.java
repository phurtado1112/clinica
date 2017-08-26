package util;

import java.awt.HeadlessException;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Pablo Hurtado
 */
public class Conecta {
    public Connection conn;
    public String ruta;
    
    public Conecta(){
        ruta="cnae.sqlite"; //especificamos la ruta de la base
    }
    
    public void Conecta(){
        try{
           Class.forName("com.mysql.jdbc.Driver"); //driver a utilizar                       
           conn=DriverManager.getConnection("jdbc:mysql://localhost/clinica1", "root", "Alpaelanis5!"); //conexion con la base 
        }catch(ClassNotFoundException | SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, e);//hubo un error
  } 

    }
    
    public void Desconecta() {
       if (conn != null) {
            try {
                    conn.close();                    
            } catch (SQLException ex) {				
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), 
                    "Error de Desconexión", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
