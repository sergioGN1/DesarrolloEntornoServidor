/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Datos;
import model.Movimiento;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.Constantes;

/**
 *
 * @author Sergio
 */
public class MovimientosDAO {
    public List<Movimiento> getMovimientos(Datos datos){
        JdbcTemplate jdbcSelect = new JdbcTemplate(
                DBConnection.getInstance().getDataSource());
        String sql = Constantes.SELECT_MOVIMIENTOS;

        List<Movimiento> listadoMovimientos = (List<Movimiento>) jdbcSelect.query(
                sql, new BeanPropertyRowMapper(Movimiento.class), datos.getNumeroCuenta(), datos.getFecha1(), datos.getFecha2());
        return listadoMovimientos;
    }
}
