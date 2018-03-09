/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Cuenta;
import model.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.Constantes;

/**
 *
 * @author Sergio
 */
public class UsuariosDAO {

    public User comprobarUser(User user) {
        User cuentaBd = null;
        try{
        JdbcTemplate jdbcTemplate = new JdbcTemplate(DBConnection.getInstance().getDataSource());
        String sql = Constantes.SELECT_USER_TRABAJOR;

        cuentaBd = (User) jdbcTemplate.queryForObject(
                sql, new BeanPropertyRowMapper(User.class), user.getNombre());
        }catch(Exception ex){
            return cuentaBd;
        }
        return cuentaBd;
    }
    
}
