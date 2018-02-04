/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Asignatura;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

/**
 *
 * @author DAW
 */
public class AsignaturasDAO {

    public List<Asignatura> getAllAsignaturasDBUtils() {
        List<Asignatura> lista = null;
        
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();

            QueryRunner qr = new QueryRunner();
            ResultSetHandler<List<Asignatura>> handler
                    = new BeanListHandler<Asignatura>(Asignatura.class);
            lista = qr.query(con, "select * FROM ASIGNATURAS", handler);

        } catch (Exception ex) {
            Logger.getLogger(AsignaturasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.getInstance().cerrarConexion(con);
        }
        return lista;
    }

    public int insertAsignaturaDBUtils(Asignatura insertAsignatura) {
        
        Connection con = null;
        BigInteger id = null;
        try {
            con = DBConnection.getInstance().getConnection();
            QueryRunner qr = new QueryRunner();

            id = qr.insert(con,
                    "INSERT INTO ASIGNATURAS (NOMBRE,CURSO,CICLO) VALUES(?,?,?)",
                    new ScalarHandler<BigInteger>(),
                    insertAsignatura.getNombre(), insertAsignatura.getCurso(), insertAsignatura.getCiclo());

            insertAsignatura.setId(id.longValue());
            con.commit();

        } catch (Exception ex) {
            Logger.getLogger(AsignaturasDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            DBConnection.getInstance().cerrarConexion(con);
        }
        return 1;

    }

    public int updateAsignaturaDBUtils(Asignatura asignatura) {
        
        Connection con = null;
        int actualizadas = 0;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            QueryRunner qr = new QueryRunner();

            actualizadas = qr.update(con,
                    "UPDATE ASIGNATURAS SET NOMBRE=?, CURSO=?,CICLO=? WHERE ID = ?",
                    asignatura.getNombre(), asignatura.getCurso(), asignatura.getCiclo(), asignatura.getId());

            con.commit();

        } catch (Exception ex) {
            Logger.getLogger(AsignaturasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.getInstance().cerrarConexion(con);
        }
        return actualizadas;
    }

    public int deleteAsignaturaDBUtils(Asignatura asignatura) {
        
        Connection con = null;
        int borradas = 0;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            QueryRunner qr = new QueryRunner();

            borradas = qr.update(con,
                    "DELETE FROM ASIGNATURAS WHERE id=?",
                    asignatura.getId());

            con.commit();

        } catch (Exception ex) {
            Logger.getLogger(AsignaturasDAO.class.getName()).log(Level.SEVERE, null, ex);
            if(ex.toString().contains("foreign key")){
                borradas = -1;
            }
        } finally {
            DBConnection.getInstance().cerrarConexion(con);
        }
        return borradas;
    }
    public int completeDeleteAsignatura(Asignatura a) throws SQLException {
        
        Connection con = null;
        int filas = 0, filasA = 0;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            QueryRunner qr = new QueryRunner();
            filas = qr.update(con,"DELETE FROM NOTAS WHERE ID_ASIGNATURA=?",a.getId());
            filasA = qr.update(con,"DELETE FROM ASIGNATURAS WHERE ID=?",a.getId());
            
            if(filas > 0 && filasA > 0){
                con.commit();
            } else {
                con.rollback();
            }
        } catch (Exception ex) {
            if (con != null){
                con.rollback();
            }
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            DBConnection.getInstance().cerrarConexion(con);
        }

        return filasA;
    }
}
