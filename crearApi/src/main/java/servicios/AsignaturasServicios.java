/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import dao.AsignaturasDAO;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Asignatura;

/**
 *
 * @author DAW
 */
public class AsignaturasServicios {
    
    
    public List<Asignatura> getAllAsignaturas()
    {
        AsignaturasDAO dao = new AsignaturasDAO();
        
        return dao.getAllAsignaturasDBUtils();
    }
    
   /* public Asignatura getAsignaturaById(int id){
        AsignaturasDAO dao = new AsignaturasDAO();
        
        return dao.getUserById(id);
        
    }*/
    
    public int completeDeleteAlumno(Asignatura borradoCompleto) throws SQLException{
        AsignaturasDAO dao = new AsignaturasDAO();
        
        return dao.completeDeleteAsignatura(borradoCompleto);
    }
    public boolean addAsignatura(Asignatura asignaturaNuevo)
    {
        AsignaturasDAO dao = new AsignaturasDAO();
        if( dao.insertAsignaturaDBUtils(asignaturaNuevo) == 1){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean updateAsignatura(Asignatura asignaturaNuevo)
    {
        AsignaturasDAO dao = new AsignaturasDAO();
        if(dao.updateAsignaturaDBUtils(asignaturaNuevo) == 1){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean deleteAsignatura(Asignatura asignaturaNuevo){
        AsignaturasDAO dao = new AsignaturasDAO();
        if(dao.deleteAsignaturaDBUtils(asignaturaNuevo) == 1){
            return true;
        }else{
            return false;
        }
    }
}
