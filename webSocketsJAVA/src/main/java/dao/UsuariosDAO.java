/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.google.api.client.util.DateTime;
import com.mycompany.websocketsjava.MyEndpoint;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Mensaje;
import model.Usuario;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import utils.Constantes;

public class UsuariosDAO {

    public UsuariosDAO() {

    }
    public int existeUser(String user){
        JdbcTemplate jdbcSelect = new JdbcTemplate(
                DBConnection.getInstance().getDataSource());
        String sql = Constantes.SELECT_COUNT_USERS;

        int count = jdbcSelect.queryForObject(sql,Integer.class,user);
        return count;
    }
    public int addUsersDAO(String user, String password){
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(DBConnection.getInstance().getDataSource()).withTableName(Constantes.NOMBRE_TABLA_LOGIN).usingGeneratedKeyColumns(Constantes.ID);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(Constantes.NOMBRE, user);
        parameters.put(Constantes.PASSWORD, password);
        int gg = jdbcInsert.executeAndReturnKey(parameters).intValue();
        return gg;
    }
    
    public boolean addMensajeDAO(Mensaje mensaje){
        try{
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(DBConnection.getInstance().getDataSource()).withTableName(Constantes.NOMBRE_TABLA_MENSAJES).usingGeneratedKeyColumns(Constantes.ID);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("contenido", mensaje.getContenido());
        parameters.put("FECHA", mensaje.getFecha());
        parameters.put("ID_CANAL", mensaje.getDestino());
        parameters.put("USER", mensaje.getUsuario());
        jdbcInsert.executeAndReturnKey(parameters);
        }catch(Exception ex){
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public boolean comprobarUser(String nombre,String password) {
        JdbcTemplate jdbcSelect = new JdbcTemplate(
                DBConnection.getInstance().getDataSource());
        String sql = Constantes.SELECT_ONE_USER;
        try{
            Usuario testUser = (Usuario)jdbcSelect.queryForObject(
			sql, new BeanPropertyRowMapper(Usuario.class), nombre);

            if(password.equals(testUser.getPassword())){
                return true;
            }
        }catch(Exception ex){
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
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
