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
 * @created 27-mar-2013 10:45:00 PM
 */
public class Paciente {

    private int idpaciente;
    private String nombre;
    private String apellido;
    private int edad;
    private String genero;
    Conecta cnx = new Conecta();
    PreparedStatement ps;
    ResultSet rs;

    public Paciente(){

    }

    public Paciente(int idpaciente, String nombre, String apellido, int edad, String genero) {
        this.idpaciente = idpaciente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
    }

    public int getIdpaciente() {
        return idpaciente;
    }

    public void setIdpaciente(int idpaciente) {
        this.idpaciente = idpaciente;
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    public void guardarPaciente(){
        cnx.Conecta();
            try {
                String SQL = "insert into paciente(nombre,apellido,edad,genero) "
                + "values(?,?,?,?)";
                
                ps = cnx.conn.prepareStatement(SQL);
                ps.setString(1, nombre);
                ps.setString(2, apellido);
                ps.setInt(3, edad);
                ps.setString(4, genero);                   
                int n = ps.executeUpdate();
                if (n>0){
                    JOptionPane.showMessageDialog(null, "Datos de Paciente guardados correctamente");                
                }
                
                ps.close();
            } catch(SQLException | HeadlessException e){
                JOptionPane.showMessageDialog(null, "Error al Guardar los datos del Paciente: " + e.getMessage());
            } finally {
                cnx.Desconecta();
            }
    }

    public void actualizarPaciente(){
        cnx.Conecta();
            try{
                String SQL ="update paciente set nombre=?,apellido=?,"
                        + "edad=?,genero=? where idpaciente=?";

                ps = cnx.conn.prepareStatement(SQL);
                ps.setString(1, nombre);
                ps.setString(2, apellido);
                ps.setInt(3, edad);
                ps.setString(4, genero);
                ps.setInt(5, idpaciente);
                int n = ps.executeUpdate();
                if(n>0){
                    JOptionPane.showMessageDialog(null, "Datos de Paciente actualizados correctamente");                
                }
                
                ps.close();
            }catch(SQLException | HeadlessException e){
                JOptionPane.showMessageDialog(null, "Error al Actualizar los datos del paciente: " + e.getMessage());
            } finally {                
                cnx.Desconecta();
            }
    }
    
    public void eliminarPaciente(){
        cnx.Conecta();
            try {
                String SQL = "delete from paciente where idpaciente= ?";
                
                ps = cnx.conn.prepareStatement(SQL);
                ps.setInt(1, idpaciente);
                int n = ps.executeUpdate();
                if(n>0){                
                    JOptionPane.showMessageDialog(null, "Datos de Paciente eliminados correctamente");
                }
                
                ps.close();
            } catch(SQLException | HeadlessException e){
                JOptionPane.showMessageDialog(null, "Error al Eliminar datos de Paciente: " + e.getMessage());
            } finally {                
                cnx.Desconecta();
            }
    }
    
    public int consultaIdPaciente(String nombre_completo){
        int id = 0;
        cnx.Conecta();
        try{
            String SQL = "Select idpaciente from paciente where nombre_completo = " 
                    + "\"" + nombre_completo + "\"";
            
            ps = cnx.conn.prepareStatement(SQL);
            rs = ps.executeQuery();            
            while(rs.next()){
                id = rs.getInt("idpaciente");
            }
            
            ps.close();
        } catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error consulta el ID del Paciente: " + e.getMessage());
        } finally {
        cnx.Desconecta();       
        }
        return id;
    }

    public String[] consultaPaciente(int idpaciente){
        String [] fila = new String[1];
        cnx.Conecta();
        try{
            String SQL = "Select nombre_completo from paciente where idpaciente= " + "\"" + idpaciente + "\"";
            
            ps = cnx.conn.prepareStatement(SQL);
            rs = ps.executeQuery();
            while(rs.next()){
                fila[0] = rs.getString("nombre_completo");
            }
            
            ps.close();
        } catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error al consultar el nombre del Paciente: " + e.getMessage());
        } finally {
        cnx.Desconecta();
        }
        return fila;
    }

//    public ArrayList<String> listaEstudiante(){
//        cnx.Conecta();
//        ArrayList<String> ls = new ArrayList<>();
//        try{
//            String SQL = "Select nombreE, apellidoE from estudiante";
//            
//            ps = cnx.conn.prepareStatement(SQL);
//            rs = ps.executeQuery();            
//            while(rs.next()){
//                ls.add(rs.getString("nombreE"));
//                ls.add(rs.getString("apellidoE"));
//            }
//            
//            ps.close();
//        } catch(SQLException | HeadlessException e){
//            JOptionPane.showMessageDialog(null, "Error consultaUniversidad: " + e.getMessage());
//        } finally {
//        cnx.Desconecta();
//        }
//        return ls;                                  
//    } 
}