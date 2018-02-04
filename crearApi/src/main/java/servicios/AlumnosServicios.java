package servicios;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import dao.AlumnosDAO;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Alumno;

/**
 *
 * @author DAW
 */


public class AlumnosServicios {
    
    public List<Alumno> getAllAlumnos()
    {
        AlumnosDAO dao = new AlumnosDAO();
        
        return dao.getAllAlumnosJDBC();
    }
    
   /* public Alumno getAlumnoById(int id){
        AlumnosDAO dao = new AlumnosDAO();
        
        return dao.getUserById(id);
        
    }*/
    public int completeDeleteAlumno(Alumno borradoCompleto) throws SQLException{
        AlumnosDAO dao = new AlumnosDAO();
        
        return dao.completeDeleteAlumno(borradoCompleto);
    }
    public boolean addAlumno(Alumno alumnoNuevo)
    {
        AlumnosDAO dao = new AlumnosDAO();
        
        if(dao.insertAlumnoJDBC(alumnoNuevo) == 1){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean updateAlumno(Alumno alumnoNuevo)
    {
        AlumnosDAO dao = new AlumnosDAO();
        
        if(dao.updateAlumnoJDBC(alumnoNuevo) == 1){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean deleteAlumno(Alumno alumnoNuevo)
    {
        AlumnosDAO dao = new AlumnosDAO();
        if(dao.deleteAlumnoJDBC(alumnoNuevo) == 1){
            return true;
        }else{
            return false;
        }
    }
    
}
