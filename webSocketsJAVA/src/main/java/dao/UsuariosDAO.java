/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.google.api.client.util.DateTime;
import com.mycompany.websocketsjava.MyEndpoint;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Mensaje;
import model.MensajeBaseDatos;
import model.MensajeFechas;
import model.Usuario;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import utils.Constantes;

public class UsuariosDAO {

    public UsuariosDAO() {

    }

    public int existeUser(String user) {
        JdbcTemplate jdbcSelect = new JdbcTemplate(
                DBConnection.getInstance().getDataSource());
        String sql = Constantes.SELECT_COUNT_USERS;

        int count = jdbcSelect.queryForObject(sql, Integer.class, user);
        return count;
    }

    public int addUsersDAO(String user, String password) {
        TransactionDefinition txDef = new DefaultTransactionDefinition();
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(DBConnection.getInstance().getDataSource());
        TransactionStatus txStatus = transactionManager.getTransaction(txDef);
        int gg = 0;
        int canalGeneral = 5;
        try {
            SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(DBConnection.getInstance().getDataSource()).withTableName(Constantes.NOMBRE_TABLA_LOGIN).usingGeneratedKeyColumns(Constantes.ID);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put(Constantes.NOMBRE, user);
            parameters.put(Constantes.PASSWORD, password);
            gg = jdbcInsert.executeAndReturnKey(parameters).intValue();

            SimpleJdbcInsert jdbcInsertGeneral = new SimpleJdbcInsert(DBConnection.getInstance().getDataSource()).withTableName(Constantes.NOMBRE_TABLA_SUSCRIPCIONES).usingGeneratedKeyColumns(Constantes.ID);
            Map<String, Object> parametersGeneral = new HashMap<>();
            parametersGeneral.put(Constantes.ID_CANAL, canalGeneral);
            parametersGeneral.put(Constantes.NOMBRE, user);
            jdbcInsertGeneral.execute(parametersGeneral);
            transactionManager.commit(txStatus);
        } catch (Exception e) {
            transactionManager.rollback(txStatus);

            throw e;
        }
        return gg;
    }

    public boolean addMensajeDAO(Mensaje mensaje) {
        try {
            SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(DBConnection.getInstance().getDataSource()).withTableName(Constantes.NOMBRE_TABLA_MENSAJES).usingGeneratedKeyColumns(Constantes.ID);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put(Constantes.CONTENIDO_MENSAJES, mensaje.getContenido());
            parameters.put(Constantes.FECHA_MENSAJES, mensaje.getFecha());
            parameters.put(Constantes.ID_CANAL_MENSAJES, mensaje.getDestino());
            parameters.put(Constantes.USER, mensaje.getUsuario());
            jdbcInsert.executeAndReturnKey(parameters);
        } catch (Exception ex) {
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public Usuario comprobarUser(String nombre, String password) {
        JdbcTemplate jdbcSelect = new JdbcTemplate(
                DBConnection.getInstance().getDataSource());
        String sql = Constantes.SELECT_ONE_USER;
        Usuario testUser = null;
        try {
            testUser = (Usuario) jdbcSelect.queryForObject(
                    sql, new BeanPropertyRowMapper(Usuario.class), nombre);

        } catch (Exception ex) {
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
            return testUser;
        }
        return testUser;
    }

    /*public Usuario comprobarUser(String nombre,String password) {
        JdbcTemplate jdbcSelect = new JdbcTemplate(
                DBConnection.getInstance().getDataSource());
        String sql = Constantes.SELECT_ONE_USER;

	Usuario testUser = (Usuario)jdbcSelect.queryForObject(
			sql, new BeanPropertyRowMapper(Usuario.class), nombre);

        
        return testUser;
    }*/
    public int userExiste(Usuario user) {
        JdbcTemplate jdbcSelect = new JdbcTemplate(
                DBConnection.getInstance().getDataSource());
        String sql = Constantes.SELECT_COUNT_USERS;

        int count = jdbcSelect.queryForObject(sql, Integer.class, user.getNombre());
        return count;
    }

    public List<String> usuariosSuscritos(Mensaje usuario) {
        List<String> usuarios = null;
        try {
            JdbcTemplate jdbcSelect = new JdbcTemplate(
                    DBConnection.getInstance().getDataSource());
            String sql = Constantes.SELECT_LIST_USER_SUSCRITOS;

            usuarios = (List<String>) jdbcSelect.query(sql, new BeanPropertyRowMapper(String.class), usuario.getDestino());
        } catch (Exception ex) {
            return usuarios;
        }
        return usuarios;
    }

    public ArrayList<MensajeBaseDatos> getMessages(MensajeFechas mensaje) {
        ArrayList<MensajeBaseDatos> canal = null;
        try {
            JdbcTemplate jdbcSelect = new JdbcTemplate(
                    DBConnection.getInstance().getDataSource());
            String sql = Constantes.SELECT_MENSAJES;

            canal = (ArrayList<MensajeBaseDatos>) jdbcSelect.query(sql, new BeanPropertyRowMapper(MensajeBaseDatos.class), mensaje.getNombreUser(), mensaje.getFecha1(), mensaje.getFecha2());
        } catch (Exception ex) {
            return canal;
        }
        return canal;
    }
}
