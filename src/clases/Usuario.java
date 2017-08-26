package clases;

import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import util.Conecta;

/**
 * @author Pablo Hurtado
 * @version 1.0
 * @created 25-ago-2017 10:44:57 PM
 */
public class Usuario {

    private int idusuario;
    private String apellido;
    private String nombre;
    private String password;
    private String usuario;
    Conecta cnx = new Conecta();
    PreparedStatement ps;
    ResultSet rs;
    
    public Usuario(){

    }

    public Usuario(int idusuario, String apellido, String nombre, String password, String usuario) {
        this.idusuario = idusuario;
        this.apellido = apellido;
        this.nombre = nombre;
        this.password = password;
        this.usuario = usuario;  
    }

    public int getIdUsuario() {
        return idusuario;
    }

    public void setIdUsuario(int idusuario) {
        this.idusuario = idusuario;
    }
        
    public String getApellido(){
            return apellido;
    }

    public String getNombre(){
            return nombre;
    }

    public String getPassword(){
            return password;
    }

    public String getUsuario(){
            return usuario;
    }

    public void setApellido(String apellid){
            apellido = apellid;
    }

    public void setNombre(String nombr){
            nombre = nombr;
    }

    public void setPassword(String pwd){
            password = pwd;
    }

    public void setUsuario(String usuari){
            usuario = usuari;
    }

    public void actualizarUsuario(){
        cnx.Conecta();
        try{
            String SQL ="update usuario set nombre=?, apellido=?,"
                    + " usuario=?, contrasenia=? where idusuario=?";
            
            ps = cnx.conn.prepareStatement(SQL);
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, usuario);
            ps.setString(4, password);
            ps.setInt(5, idusuario);
            int n = ps.executeUpdate();
            if(n>0){
                JOptionPane.showMessageDialog(null, "Datos del Usuario actualizados correctamente");                                
            }
            
            ps.close();
        }catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        } finally {                
            cnx.Desconecta();
        }
    }
    
    public void eliminarUsuario(){
        cnx.Conecta();
        try {
            String SQL = "delete from usuario where idusuario= ?";
            
            ps = cnx.conn.prepareStatement(SQL); 
            ps.setInt(1, idusuario);
            int n = ps.executeUpdate();
            if(n>0){                
                JOptionPane.showMessageDialog(null, "Datos del Usuario eliminados correctamente");
            }
            
            ps.close();
        } catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error Eliminar: " + e.getMessage());
        } finally {                
            cnx.Desconecta();
        }
    }
    
    public void guardarUsuario(){
        cnx.Conecta();
        try {
            String SQL = "insert into usuario(nombre, apellido, usuario, contrasenia) "
            + "values(?,?,?,?)";
            
            ps = cnx.conn.prepareStatement(SQL);
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, usuario);
            ps.setString(4, password);
            int n = ps.executeUpdate();
            if (n>0){
                JOptionPane.showMessageDialog(null, "Datos del Usuario guardados correctamente");                
            }
            
            ps.close();
        } catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        } finally {
            cnx.Desconecta();
        }
    }

    public int consultaIdUsuario(String Usuario){
        int id = 0;
        cnx.Conecta();
        try{
            String SQL = "Select idusuario from usuario where nombre = "+ Usuario;
            
            ps = cnx.conn.prepareStatement(SQL);
            rs = ps.executeQuery();            
            while(rs.next()){
                id = rs.getInt("idusuario");
            }
            
            ps.close();
        } catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error consulta ID Usuario: " + e.getMessage());
        }
        cnx.Desconecta();       
        return id;
    }
    
//    public String consultaUsuario(int id){
//        String fila= "";
//        cnx.Conecta();
//        try{
//            String SQL = "Select usuario from usuario where idusuario="+id;
//            
//            ps = cnx.conn.prepareStatement(SQL);
//            rs = ps.executeQuery();
//            while(rs.next()){
//                fila = rs.getString("usuario");                
//            }
//            
//            ps.close();
//        } catch(SQLException | HeadlessException e){
//            JOptionPane.showMessageDialog(null, "Error consulta Nombre Usuario: " + e.getMessage());
//        }
//        cnx.Desconecta();       
//        return fila;
//    }
    
}