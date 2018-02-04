package dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import model.Alumno;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.lang.*;
/*
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
*/
/**
 *
 * @author oscar
 */
public class AlumnosDAO {

 
    // Select JDBC
    public List<Alumno> getAllAlumnosJDBC() {
        List<Alumno> lista = new ArrayList<>();
        Alumno nuevo = null;
       
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getInstance().getConnection();
            stmt = con.createStatement();
            String sql;
            sql = "SELECT * FROM ALUMNOS";
            rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                Date fn = rs.getDate("fecha_nacimiento");
                Boolean mayor = rs.getBoolean("mayor_edad");
                nuevo = new Alumno();
                nuevo.setFecha_nacimiento(fn);
                nuevo.setId(id);
                nuevo.setMayor_edad(mayor);
                nuevo.setNombre(nombre);
                lista.add(nuevo);
            }

        } catch (Exception ex) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            DBConnection.getInstance().cerrarConexion(con);
        }
        return lista;

    }

    //inser JDBC
    public int insertAlumnoJDBC(Alumno a) {
        
        Connection con = null;
        int filas = 0;
        try {
            con = DBConnection.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement("INSERT INTO ALUMNOS (NOMBRE,FECHA_NACIMIENTO,MAYOR_EDAD) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, a.getNombre());
            stmt.setDate(2, new java.sql.Date(a.getFecha_nacimiento().getTime()));
            stmt.setBoolean(3, a.getMayor_edad());
            filas = stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                a.setId(rs.getInt(1));
            }

        } catch (Exception ex) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.getInstance().cerrarConexion(con);
        }

        return filas;
    }

    public int updateAlumnoJDBC(Alumno a) {
        
        Connection con = null;
        int filas = 0;
        try {
            con = DBConnection.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement("UPDATE ALUMNOS SET NOMBRE=?, fecha_nacimiento=?, MAYOR_EDAD=? where id=?", Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, a.getNombre());
            stmt.setDate(2, new java.sql.Date(a.getFecha_nacimiento().getTime()));
            stmt.setBoolean(3, a.getMayor_edad());
            stmt.setLong(4, a.getId());
            
            filas = stmt.executeUpdate();
        }catch (Exception ex) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.getInstance().cerrarConexion(con);
        }
        return filas;
    }

    public int deleteAlumnoJDBC(Alumno a){
        
        Connection con = null;
        int filas = 0;
        try {
            
            con = DBConnection.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement("DELETE FROM ALUMNOS WHERE id=?", Statement.RETURN_GENERATED_KEYS);

            stmt.setLong(1, a.getId());
            filas = stmt.executeUpdate();
            
        }catch (SQLIntegrityConstraintViolationException fk){
            
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, fk);
            filas = -1;
            
        } catch (Exception ex) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
            if(ex.toString().contains("foreign key")){
                filas = -1;
            }
        }finally {
            
           DBConnection.getInstance().cerrarConexion(con);
            
        }

        return filas;
    }
    public int completeDeleteAlumno(Alumno a) throws SQLException {
      
        Connection con = null;
        int filas = 0, filasA = 0;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            PreparedStatement stmt = con.prepareStatement("DELETE FROM NOTAS WHERE ID_ALUMNO=?", Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, a.getId());
            
            PreparedStatement stmtA = con.prepareStatement("DELETE FROM ALUMNOS WHERE ID=?", Statement.RETURN_GENERATED_KEYS);
            stmtA.setLong(1, a.getId());
            
            filas = stmt.executeUpdate();            
            filasA = stmtA.executeUpdate();
            con.commit();
        } catch (Exception ex) {
            con.rollback();
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            DBConnection.getInstance().cerrarConexion(con);
        }

        return filasA;
    }
 
}
