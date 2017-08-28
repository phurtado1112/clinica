package clases;

import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import util.Conecta;

/**
 * @author Pablo Hurtado
 * @version 1.0
 * @created 25-ago-2017 10:45:09 PM
 */
public class Especialidad {

    private int idespecialidad;
    private String descripcion_especialidad;
    Conecta cnx = new Conecta();
    PreparedStatement ps;
    ResultSet rs;

    public Especialidad(){

    }

    public Especialidad(int idespecialidad, String descripcion_especialida) {
        this.idespecialidad = idespecialidad;
        this.descripcion_especialidad = descripcion_especialida;
    }

    public int getIdespecialidad() {
        return idespecialidad;
    }

    public void setIdespecialidad(int idespecialidad) {
        this.idespecialidad = idespecialidad;
    }

    public String getDescripcion_especialidad() {
        return descripcion_especialidad;
    }

    public void setDescripcion_especialidad(String descripcion_especialidad) {
        this.descripcion_especialidad = descripcion_especialidad;
    }
        
    public void actualizarEspecialidad(){
        cnx.Conecta();
        try{
            String SQL ="update especialidad set descripcion_especialidad=?"
                    + " where idespecialidad=?";
            
            ps = cnx.conn.prepareStatement(SQL);
            
            ps.setString(1, descripcion_especialidad);
            ps.setInt(2, idespecialidad);            

            int n = ps.executeUpdate();
            if(n>0){
                    JOptionPane.showMessageDialog(null, "Datos de Especialidad actualizados correctamente");               
                }
            ps.close();
            }catch(SQLException | HeadlessException e){
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            } finally {                
                cnx.Desconecta();
            }
    }
    
    public void eliminarEspecialidad(){
        cnx.Conecta();
            try {
                String SQL = "delete from especialidad where idespecialidad= ?";         
                ps = cnx.conn.prepareStatement(SQL);
                ps.setInt(1, idespecialidad);                
                int n = ps.executeUpdate();
                if(n>0){                
                    JOptionPane.showMessageDialog(null, "Datos eliminados correctamente");
                }
                ps.close();
            } catch(SQLException | HeadlessException e){
                JOptionPane.showMessageDialog(null, "Error Eliminar: " + e.getMessage());
            } finally {                
                cnx.Desconecta();
            }
    }
    
    public void guardarEspecialidad(){
        cnx.Conecta();
        try{
            String SQL = "insert into especialidad (descripcion_especialidad) values(?)";
            ps = cnx.conn.prepareStatement(SQL);            
            ps.setString(1, descripcion_especialidad);     
           
            int n = ps.executeUpdate();
            if (n>0){
                    JOptionPane.showMessageDialog(null, "Datos de Especialidad guardados correctamente");                
            }
            ps.close();
        } catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error al guardar Especialidad: " + e.getMessage());
        } finally {                
            cnx.Desconecta();
        }
    }
        
    public int consultaIdespecialidad(String Especialidad){
        int id = 0;
        cnx.Conecta();
        try{
            String SQL = "Select idespecialidad from especialidad where descripcion_especialidad = " + "\"" + Especialidad + "\"";            
            ps = cnx.conn.prepareStatement(SQL);
            rs = ps.executeQuery();            
            while(rs.next()){
                id = rs.getInt("idespecialidad");
            }
            ps.close();
        } catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error al consultar ID Especialidad: " + e.getMessage());
        }
        cnx.Desconecta();       
        return id;
    }
    
    public String consultaEspecialidad(int idespecialidad){
        String fila= "";
        cnx.Conecta();
        try{
            String SQL = "Select descripcion_especialidad from especialidad where idespecialidad= " + "\"" + idespecialidad + "\"";
            ps = cnx.conn.prepareStatement(SQL);
            rs = ps.executeQuery();
            while(rs.next()){
                fila = rs.getString("descripcion_especialidad");
            }
            ps.close();
        } catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error consulta Nombre Especialidad: " + e.getMessage());
        }
        cnx.Desconecta();       
        return fila;
    }  
}