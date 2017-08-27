/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.awt.HeadlessException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import util.Conecta;

/**
 *
 * @author Pablo Hurtado - PHD Systems
 */
public class HistorialClinico {
    private int idhistorial_clinico;
    private Date fecha;
    private String observacion;
    Paciente pa = new Paciente();
    private int idpaciente = pa.getIdpaciente();
    Medico me = new Medico();
    private int idmedico = me.getIdmedico();
    Conecta cnx = new Conecta();
    PreparedStatement ps;
    ResultSet rs;

    public HistorialClinico() {
        
    }

    public HistorialClinico(int idhistorial_clinico, Date fecha, String observacion, int idpaciente, int idmedico) {
        this.idhistorial_clinico = idhistorial_clinico;
        this.fecha = fecha;
        this.observacion = observacion;
        this.idpaciente = idpaciente;
        this.idmedico = idmedico;
    }

    public int getIdhistorial_clinico() {
        return idhistorial_clinico;
    }

    public void setIdhistorial_clinico(int idhistorial_clinico) {
        this.idhistorial_clinico = idhistorial_clinico;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getIdpaciente() {
        return idpaciente;
    }

    public void setIdpaciente(int idpaciente) {
        this.idpaciente = idpaciente;
    }

    public int getIdmedico() {
        return idmedico;
    }

    public void setIdmedico(int idmedico) {
        this.idmedico = idmedico;
    }
    
    public void guardarHistorialClinico() {
        cnx.Conecta();
            try {
                String SQL = "insert into historial_clinico(idpaciente,idmedico,fecha,observacion) "
                + "values(?,?,?,?)";
                
                ps = cnx.conn.prepareStatement(SQL);
                ps.setInt(1, idpaciente);
                ps.setInt(2, idmedico);
                ps.setDate(3, fecha);
                ps.setString(4, observacion);                   
                int n = ps.executeUpdate();
                if (n>0){
                    JOptionPane.showMessageDialog(null, "Datos de Historial Clinico guardados correctamente");                
                }
                
                ps.close();
            } catch(SQLException | HeadlessException e){
                JOptionPane.showMessageDialog(null, "Error al Guardar los datos del Historial Clinico: " + e.getMessage());
            } finally {
                cnx.Desconecta();
            }
    }
    
    public void actualizarHistorialClinico() {
        cnx.Conecta();
            try{
                String SQL ="update historial_clinico set idpaciente=?,idmedico=?,"
                        + "fecha=?,observacion=? where idhistorial_clinico=?";

                ps = cnx.conn.prepareStatement(SQL);
                ps.setInt(1, idpaciente);
                ps.setInt(2, idmedico);
                ps.setDate(3, fecha);
                ps.setString(4, observacion);
                ps.setInt(5, idhistorial_clinico);
                int n = ps.executeUpdate();
                if(n>0){
                    JOptionPane.showMessageDialog(null, "Datos de Historial Clinico actualizados correctamente");                
                }
                
                ps.close();
            }catch(SQLException | HeadlessException e){
                JOptionPane.showMessageDialog(null, "Error al Actualizar los datos del Historial Clinico: " + e.getMessage());
            } finally {                
                cnx.Desconecta();
            }
    }
    
    public void eliminarHistorialClinico() {
        cnx.Conecta();
            try {
                String SQL = "delete from historial_clinico where idhistorial_clinico= ?";
                
                ps = cnx.conn.prepareStatement(SQL);
                ps.setInt(1, idhistorial_clinico);
                int n = ps.executeUpdate();
                if(n>0){                
                    JOptionPane.showMessageDialog(null, "Datos de Historial Clinico eliminados correctamente");
                }
                
                ps.close();
            } catch(SQLException | HeadlessException e){
                JOptionPane.showMessageDialog(null, "Error al Eliminar datos del Historial Clinico: " + e.getMessage());
            } finally {                
                cnx.Desconecta();
            }
    }
}
    
