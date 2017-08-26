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
 * @created 27-mar-2013 10:45:03 PM
 */
public class Medico {
    private int idmedico;
    private String nombre;
    private String apellido;
    Especialidad es = new Especialidad();
    private int idespecialidad = es.getIdespecialidad();
    Conecta cnx = new Conecta();
    PreparedStatement ps;
    ResultSet rs;

    public Medico(){

    }

    public Medico(int idmedico, String nombre, String apellido, int idespecialidad) {
        this.idmedico = idmedico;
        this.nombre = nombre;
        this.apellido = apellido;
        this.idespecialidad = idespecialidad;
    }
    
    

    public int getIdmedico() {
        return idmedico;
    }

    public void setIdmedico(int idmedico) {
        this.idmedico = idmedico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getIdespecialidad() {
        return idespecialidad;
    }

    public void setIdespecialidad(int idespecialidad) {
        this.idespecialidad = idespecialidad;
    }

    

    public void ActualizarMedico(){
    cnx.Conecta();
        try{
            String SQL ="update medico set nombre=?, apellido=?, idespecialidad=?"
            + "where idmedico=?";
            
            ps = cnx.conn.prepareStatement(SQL);
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setInt(3, idespecialidad);
            ps.setInt(4, idmedico);
            int n = ps.executeUpdate();
            if(n>0){
                JOptionPane.showMessageDialog(null, "Datos del Médico actualizados correctamente");                
            }
            ps.close();
        }catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error al actualizar los datos del Médico: " + e.getMessage());
        } finally {
            cnx.Desconecta();
        }
    }
    
    public void EliminarMedico(){
        cnx.Conecta();
        try {
            String SQL = "delete from medico where idmedico= ?";

            ps = cnx.conn.prepareStatement(SQL);
            ps.setInt(1, idmedico);
            int n = ps.executeUpdate();
            if(n>0){                
                JOptionPane.showMessageDialog(null, "Datos del Médico eliminados correctamente");
            }            
            ps.close();
        } catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error al eliminar los datos del Médico: " + e.getMessage());            
        } finally {
            cnx.Desconecta();
        }
    }
    
    public void GuardarMedico(){
        cnx.Conecta();
        try {
            String SQL = "insert into medico(nombre,apellido,idespecialidad) values(?,?,?)";

            ps = cnx.conn.prepareStatement(SQL);
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setInt(3, idespecialidad);
            int n = ps.executeUpdate();
            if (n>0){
                JOptionPane.showMessageDialog(null, "Datos del Médico guardados correctamente");                
            }            
            ps.close();
        } catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error Guardar los datos del Médico: " + e.getMessage());
        } finally {
            cnx.Desconecta();
        }
    }

//    public int consultaId(String Facult){
//        int id = 0;
//        cnx.Conecta();
//        try{
//            String SQL = "Select idfacultad from facultad where nombreF = "+"\""+Facult+"\"";
//            
//            ps = cnx.conn.prepareStatement(SQL);
//            rs = ps.executeQuery();            
//            while(rs.next()){
//                id = rs.getInt("idfacultad");
//            }
//            
//            ps.close();
//        } catch(SQLException | HeadlessException e){
//            JOptionPane.showMessageDialog(null, "Error consulta ID Facultad: " + e.getMessage());
//        }
//        cnx.Desconecta();       
//        return id;
//    }
    
//    public String consultaFacultad(int id){
//        String fila= "";
//        cnx.Conecta();
//        try{
//            String SQL = "Select nombreF from facultad where idfacultad="+id;
//            ps = cnx.conn.prepareStatement(SQL);
//            rs = ps.executeQuery();
//            while(rs.next()){
//                fila = rs.getString("nombreF");
//            }            
//            ps.close();
//        } catch(SQLException | HeadlessException e){
//            JOptionPane.showMessageDialog(null, "Error consulta Nombre Facultad: " + e.getMessage());
//        }
//        cnx.Desconecta();       
//        return fila;
//    }
    
//    public ArrayList<String> consultaFacultad(){
//        cnx.Conecta();
//        ArrayList<String> ls = new ArrayList<>();
//        try{
//            String SQL = "Select nombreF from facultad";
//            ps = cnx.conn.prepareStatement(SQL);
//            rs = ps.executeQuery();            
//            while(rs.next()){
//                ls.add(rs.getString("nombreF"));
//            }            
//            ps.close();
//        } catch(SQLException | HeadlessException e){
//            JOptionPane.showMessageDialog(null, "Error consultaFacultad: " + e.getMessage());
//        }
//        cnx.Desconecta();
//        return ls;                                  
//    }
}