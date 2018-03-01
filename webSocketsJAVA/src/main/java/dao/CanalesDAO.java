/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Canal;
import model.CanalSuscrito;
import model.Mensaje;
import model.Suscripcion;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import utils.Constantes;

/**
 *
 * @author Sergio
 */
public class CanalesDAO {

    public List<Canal> getAllCanales() {
        List<Canal> canales = null;
        try {
            JdbcTemplate jdbcSelect = new JdbcTemplate(
                    DBConnection.getInstance().getDataSource());
            String sql = Constantes.SELECT_ALL_CANALES;
            canales = (List<Canal>) jdbcSelect.query(sql, new BeanPropertyRowMapper(Canal.class));
        } catch (Exception ex) {
            Logger.getLogger(CanalesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return canales;
    }

    public Canal addCanal(Canal canal) {
        TransactionDefinition txDef = new DefaultTransactionDefinition();
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(DBConnection.getInstance().getDataSource());
        TransactionStatus txStatus = transactionManager.getTransaction(txDef);
        int idCanal = 0;
        try {
            SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(DBConnection.getInstance().getDataSource()).withTableName(Constantes.NOMBRE_TABLA_CANALES).usingGeneratedKeyColumns(Constantes.ID);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put(Constantes.NOMBRE_CANAL, canal.getNombre());
            parameters.put(Constantes.USER_ADMIN_BIEN, canal.getNombre_usuario());
            parameters.put(Constantes.CLAVE, canal.getClave());
            idCanal = jdbcInsert.executeAndReturnKey(parameters).intValue();
            canal.setId(idCanal);
            SimpleJdbcInsert jdbcInsertSuscripcion = new SimpleJdbcInsert(DBConnection.getInstance().getDataSource()).withTableName(Constantes.NOMBRE_TABLA_SUSCRIPCIONES).usingGeneratedKeyColumns(Constantes.ID);
            Map<String, Object> parametersSuscripcion = new HashMap<>();
            parametersSuscripcion.put(Constantes.ID_CANAL, idCanal);
            parametersSuscripcion.put(Constantes.USER, canal.getNombre_usuario());
            jdbcInsertSuscripcion.execute(parametersSuscripcion);
            transactionManager.commit(txStatus);
        } catch (Exception e) {
            transactionManager.rollback(txStatus);

            return canal;
        }
        return canal;
    }

    public String getCanall(int idCanal) {
        String canal = "";
        try {
            JdbcTemplate jdbcSelect = new JdbcTemplate(
                    DBConnection.getInstance().getDataSource());
            String sql = Constantes.SELECT_ONE_CANAL;

            canal = jdbcSelect.queryForObject(sql, String.class, idCanal);
        } catch (Exception ex) {
            return canal;
        }
        return canal;
    }

    public boolean addSuscripcion(Suscripcion suscripcion) {
        try {
            SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(DBConnection.getInstance().getDataSource()).withTableName(Constantes.NOMBRE_TABLA_SUSCRIPCIONES);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put(Constantes.ID_CANAL, suscripcion.getCanal());
            parameters.put(Constantes.USER, suscripcion.getUser());
            jdbcInsert.execute(parameters);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public List<CanalSuscrito> getCanalSuscritoDAO(Mensaje canalSuscrito) {
        List<CanalSuscrito> canales = null;
        try {
            JdbcTemplate jdbcSelect = new JdbcTemplate(
                    DBConnection.getInstance().getDataSource());
            String sql = Constantes.SELECT_CANALES_SUSCRITO;

            canales = (List<CanalSuscrito>) jdbcSelect.query(sql, new BeanPropertyRowMapper(CanalSuscrito.class), canalSuscrito.getUsuario());
        } catch (Exception ex) {
            return canales;
        }
        return canales;
    }
}
