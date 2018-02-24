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
import model.Canal;
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
        JdbcTemplate jdbcSelect = new JdbcTemplate(
                DBConnection.getInstance().getDataSource());
        String sql = Constantes.SELECT_ALL_CANALES;
        List<Canal> canales = jdbcSelect.query(sql, new BeanPropertyRowMapper<>(Canal.class));
        return canales;
    }
    public boolean addCanal(Canal canal){
       try{
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(DBConnection.getInstance().getDataSource()).withTableName(Constantes.NOMBRE_TABLA_CANALES).usingGeneratedKeyColumns(Constantes.ID);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(Constantes.NOMBRE,canal.getNombre());
        parameters.put(Constantes.USER_ADMIN,canal.getNombre_usuario());
        parameters.put(Constantes.CLAVE,canal.getClave());
        int gg = jdbcInsert.executeAndReturnKey(parameters).intValue();
        } catch (Exception ex){
            return false;
        }        
        return true;
    }
}
