/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import config.Configuration;
import java.util.HashMap;
import java.util.Map;
import java.lang.*;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Usuario;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import utils.Constantes;
import utils.PasswordHash;

public class UsuariosDAO {

    public UsuariosDAO() {

    }

    

    public boolean comprobarUser(String nombre,String password) {
        JdbcTemplate jdbcSelect = new JdbcTemplate(
                DBConnection.getInstance().getDataSource());
        String sql = Constantes.SELECT_ONE_USER;

	Usuario testUser = (Usuario)jdbcSelect.queryForObject(
			sql, new BeanPropertyRowMapper(Usuario.class), nombre);

            if(password.equals(testUser.getPassword())){
                return true;
            }
        
        return false;
    }
    /*public Usuario comprobarUser(String nombre,String password) {
        JdbcTemplate jdbcSelect = new JdbcTemplate(
                DBConnection.getInstance().getDataSource());
        String sql = Constantes.SELECT_ONE_USER;

	Usuario testUser = (Usuario)jdbcSelect.queryForObject(
			sql, new BeanPropertyRowMapper(Usuario.class), nombre);

        
        return testUser;
    }*/
    
    public int userExiste(Usuario user){
        JdbcTemplate jdbcSelect = new JdbcTemplate(
                DBConnection.getInstance().getDataSource());
        String sql = Constantes.SELECT_COUNT_USERS;

        int count = jdbcSelect.queryForObject(sql,Integer.class,user.getNombre());
        return count;
    }
    
    
}
