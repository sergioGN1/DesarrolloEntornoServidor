/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.HashMap;
import java.util.Map;
import model.Cliente;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import utils.Constantes;

/**
 *
 * @author Sergio
 */
public class ClientesDAO {
    public int comprobarCliente(Cliente cliente){
        JdbcTemplate jdbcSelect = new JdbcTemplate(
                DBConnection.getInstance().getDataSource());
        String sql = Constantes.SELECT_COUNT_CLIENTE;

        int count = jdbcSelect.queryForObject(sql,Integer.class,cliente.getCl_dni());
        return count;
    }
    public Cliente seleccionarCliente(Cliente cliente){
        JdbcTemplate jdbcSelect = new JdbcTemplate(
                DBConnection.getInstance().getDataSource());
        String sql = Constantes.SELECT_ONE_CLIENTE;

	Cliente clienteCogido = (Cliente)jdbcSelect.queryForObject(
			sql, new BeanPropertyRowMapper(Cliente.class), cliente.getCl_dni());
        return clienteCogido;
    }
}
