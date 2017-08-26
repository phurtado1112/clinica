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

    private int idestudiante;
    private String apellidos;
    private String carne;
    private String celular;
    private String email;
    private String nombre;
    private int idasignatura;
    Conecta cnx = new Conecta();
    PreparedStatement ps;
    ResultSet rs;

    public Paciente(){

    }

    public Paciente(String apellidos, String carne, String celular, String email, String nombre, int idestudiante, int idasignatura) {
        this.apellidos = apellidos;
        this.carne = carne;
        this.celular = celular;
        this.email = email;
        this.nombre = nombre;
        this.idestudiante = idestudiante;
        this.idasignatura = idasignatura;
    }

    public int getIdEstudiante() {
        return idestudiante;
    }

    public void setIdEstudiante(int idestudiante) {
        this.idestudiante = idestudiante;
    }
        
    public String getApellidos(){
            return apellidos;
    }

    public String getCarnet(){
            return carne;
    }

    public String getCelular(){
            return celular;
    }

    public String getEmail(){
            return email;
    }

    public String getNombre(){
            return nombre;
    }

    public void setApellidos(String apellid){
            apellidos = apellid;
    }

    public void setCarnet(String carn){
            carne = carn;
    }

    public void setCelular(String celula){
            celular = celula;
    }

    public void setEmail(String correo){
            email = correo;
    }

    public void setNombre(String nombr){
            nombre = nombr;
    }

    public int getIdAsignatura() {
        return idasignatura;
    }

    public void setIdAsignatura(int idasignatura) {
        this.idasignatura = idasignatura;
    }

    @Override
    public String toString(){
        return this.nombre+" "+this.apellidos;
    }

    public void actualizarEstudiante(){
        cnx.Conecta();
            try{
                String SQL ="update estudiante set nombreE=?,apellidoE=?,"
                        + "carnet=?,celular=?, email=?,"
                        + " idasignatura=? where idestudiante=?";

                ps = cnx.conn.prepareStatement(SQL);
                ps.setString(1, nombre);
                ps.setString(2, apellidos);
                ps.setString(3, carne);
                ps.setString(4, celular);
                ps.setString(5, email);
                ps.setInt(6, idasignatura);
                ps.setInt(7, idestudiante);
                int n = ps.executeUpdate();
                if(n>0){
                    JOptionPane.showMessageDialog(null, "Datos actualizados correctamente");                
                }
                
                ps.close();
            }catch(SQLException | HeadlessException e){
                JOptionPane.showMessageDialog(null, "Error Actualizar: " + e.getMessage());
            } finally {                
                cnx.Desconecta();
            }
    }
    
    public void eliminarEstudiante(){
        cnx.Conecta();
            try {
                String SQL = "delete from estudiante where idestudiante= ?";
                
                ps = cnx.conn.prepareStatement(SQL);
                ps.setInt(1, idestudiante);
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
    
    public void guardarEstudiante(){
        cnx.Conecta();
            try {
                String SQL = "insert into estudiante(nombreE,apellidoE,carnet,celular,email,idasignatura) "
                + "values(?,?,?,?,?,?)";
                
                ps = cnx.conn.prepareStatement(SQL);
                ps.setString(1, nombre);
                ps.setString(2, apellidos);
                ps.setString(3, carne);
                ps.setString(4, celular);
                ps.setString(5, email);
                ps.setInt(6, idasignatura);                       
                int n = ps.executeUpdate();
                if (n>0){
                    JOptionPane.showMessageDialog(null, "Datos guardados correctamente");                
                }
                
                ps.close();
            } catch(SQLException | HeadlessException e){
                JOptionPane.showMessageDialog(null, "Error Guardar: " + e.getMessage());
            } finally {
                cnx.Desconecta();
            }
    }
    
    public int consultaId(String Nombr, String Apell){
        int id = 0;
        cnx.Conecta();
        try{
            String SQL = "Select idestudiante from estudiante where nombreE = "+"\""+Nombr+"\""
                    + "and apellidoE="+"\""+Apell+"\"";
            
            ps = cnx.conn.prepareStatement(SQL);
            rs = ps.executeQuery();            
            while(rs.next()){
                id = rs.getInt("idestudiante");
            }
            
            ps.close();
        } catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error consulta ID Estudiante: " + e.getMessage());
        } finally {
        cnx.Desconecta();       
        }
        return id;
    }

    public String[] consultaEstudiante(int id){
        String [] fila = new String[2];
        cnx.Conecta();
        try{
            String SQL = "Select nombreE, apellidoE from estudiante where idestudiante="+id;
            
            ps = cnx.conn.prepareStatement(SQL);
            rs = ps.executeQuery();
            while(rs.next()){
                fila[0] = rs.getString("nombreE");
                fila[1] = rs.getString("apellidoE");
            }
            
            ps.close();
        } catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error consulta Nombre Estidiante: " + e.getMessage());
        } finally {
        cnx.Desconecta();
        }
        return fila;
    }

    public ArrayList<String> listaEstudiante(){
        cnx.Conecta();
        ArrayList<String> ls = new ArrayList<>();
        try{
            String SQL = "Select nombreE, apellidoE from estudiante";
            
            ps = cnx.conn.prepareStatement(SQL);
            rs = ps.executeQuery();            
            while(rs.next()){
                ls.add(rs.getString("nombreE"));
                ls.add(rs.getString("apellidoE"));
            }
            
            ps.close();
        } catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error consultaUniversidad: " + e.getMessage());
        } finally {
        cnx.Desconecta();
        }
        return ls;                                  
    } 
}