/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Nota;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

/**
 *
 * @author DAW
 */
public class NotasDAO {
    public List<Nota> getAllNotasDBUtils() {
        List<Nota> lista = null;
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();

            QueryRunner qr = new QueryRunner();
            ResultSetHandler<List<Nota>> handler
                    = new BeanListHandler<Nota>(Nota.class);
            lista = qr.query(con, "select * FROM NOTAS",handler);

        } catch (Exception ex) {
            Logger.getLogger(NotasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.getInstance().cerrarConexion(con);
        }
        return lista;
    }

    public int getAllNotasSelectDBUtils(Nota selectNota) {
        int notaAlumno = 0;
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();

          
            QueryRunner qr = new QueryRunner();
            ScalarHandler<Integer> i = new ScalarHandler<Integer>();
            notaAlumno = qr.query(con, "SELECT NOTA FROM NOTAS WHERE ID_ALUMNO = ? AND ID_ASIGNATURA = ?",
                 i ,selectNota.getId_alumno(),selectNota.getId_asignatura());
            
            selectNota.setNota(notaAlumno);
            
        } catch (Exception ex) {
            Logger.getLogger(NotasDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        } finally {
            DBConnection.getInstance().cerrarConexion(con);
        }
        return selectNota.getNota();
    }
    
    public int insertNotaDBUtils(Nota insertNota) {
        Connection con = null;

        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            QueryRunner qr = new QueryRunner();

            BigInteger id = qr.insert(con,
                    "INSERT INTO NOTAS (ID_ALUMNO,ID_ASIGNATURA,NOTA) VALUES(?,?,?)",
                    new ScalarHandler<BigInteger>(),
                    insertNota.getId_alumno(), insertNota.getId_asignatura(), insertNota.getNota());

            con.commit();

        } catch (Exception ex) {
            Logger.getLogger(NotasDAO.class.getName()).log(Level.SEVERE, null, ex);
            if(ex.toString().contains("Duplicate entry")){
                insertNota = null;
                return 0;
            }
            
            return 0;
        } finally {
            DBConnection.getInstance().cerrarConexion(con);
        }
        return 1;

    }

    public int updateNotaDBUtils(Nota nota) {
        Connection con = null;
        int actualizadas = 0;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            QueryRunner qr = new QueryRunner();

            actualizadas = qr.update(con,
                    "UPDATE NOTAS SET NOTA=? WHERE ID_ALUMNO = ? AND ID_ASIGNATURA = ?",
                    nota.getNota(), nota.getId_alumno(),nota.getId_asignatura());

            con.commit();

        } catch (Exception ex) {
            Logger.getLogger(NotasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.getInstance().cerrarConexion(con);
        }
        return actualizadas;
    }

    public int deleteNotaDBUtils(Nota nota) {
        Connection con = null;
        int borradas = 0;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            QueryRunner qr = new QueryRunner();

            borradas = qr.update(con,
                    "DELETE FROM NOTAS WHERE ID_ALUMNO=? AND ID_ASIGNATURA = ?",
                    nota.getId_alumno(),nota.getId_asignatura());

            con.commit();

        } catch (Exception ex) {
            Logger.getLogger(NotasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.getInstance().cerrarConexion(con);
        }
        return borradas;
    }
}
