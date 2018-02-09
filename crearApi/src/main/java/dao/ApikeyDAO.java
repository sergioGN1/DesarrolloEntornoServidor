/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Asignatura;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

/**
 *
 * @author Sergio
 */
public class ApikeyDAO {

    public ApikeyDAO() {
    }

    public int comprobarApikeyDAO(String apikey) {
        long count = 0;
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();

            ScalarHandler<Long> scalarHandler = new ScalarHandler<>();

            QueryRunner runner = new QueryRunner();
            String query = "SELECT COUNT(*) FROM APIKEY where APIKEY=?";
            
                   count = runner.query(con, query,scalarHandler, apikey);

        } catch (Exception ex) {
            Logger.getLogger(AsignaturasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.getInstance().cerrarConexion(con);
        }
        
        return (int)count;
    }
}
