/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Canal;
import model.Suscripcion;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
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

    public boolean addCanal(Canal canal) {
        try {
            SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(DBConnection.getInstance().getDataSource()).withTableName(Constantes.NOMBRE_TABLA_CANALES).usingGeneratedKeyColumns(Constantes.ID);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put(Constantes.NOMBRE_CANAL, canal.getNombre());
            parameters.put(Constantes.USER_ADMIN_BIEN, canal.getNombre_usuario());
            parameters.put(Constantes.CLAVE, canal.getClave());
            int gg = jdbcInsert.executeAndReturnKey(parameters).intValue();
        } catch (Exception ex) {
            return false;
        }
        return true;
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
}
