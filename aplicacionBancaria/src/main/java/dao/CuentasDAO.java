/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Cliente;
import model.Cuenta;
import model.Datos;
import model.Movimiento;
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
public class CuentasDAO {
    public int ingresoDinero(Movimiento movimiento, Cliente cliente){
        TransactionDefinition txDef = new DefaultTransactionDefinition();
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(DBConnection.getInstance().getDataSource());
        TransactionStatus txStatus = transactionManager.getTransaction(txDef);
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(DBConnection.getInstance().getDataSource());
            int saldoDelCliente = Integer.parseInt(jdbcTemplate.queryForObject(Constantes.SALDO_EN_EL_CLIENTE, String.class, cliente.getCl_dni()));
            
            int saldoDeLaCuenta = Integer.parseInt(jdbcTemplate.queryForObject(Constantes.SALDO_EN_LA_CUENTA, String.class, cliente.getCl_dni()));
            
            
            JdbcTemplate actualizarCliente = new JdbcTemplate(DBConnection.getInstance().getDataSource());
            actualizarCliente.update(Constantes.ACTUALIZAR_SALDO_CLIENTE, saldoDelCliente+Integer.parseInt(movimiento.getMo_imp()),cliente.getCl_dni());
            
            JdbcTemplate actualizarCuenta = new JdbcTemplate(DBConnection.getInstance().getDataSource());
            actualizarCuenta.update(Constantes.ACTUALIZAR_SALDO_CUENTA, saldoDeLaCuenta+Integer.parseInt(movimiento.getMo_imp()),cliente.getCl_dni());

            SimpleJdbcInsert jdbcInsertMovimiento = new SimpleJdbcInsert(DBConnection.getInstance().getDataSource()).withTableName(Constantes.TABLA_DE_MOVIMIENTOS);
            Map<String, Object> parametersMovimiento = new HashMap<>();
            parametersMovimiento.put(Constantes.MO_NCU, movimiento.getMo_ncu());
            parametersMovimiento.put(Constantes.MO_FEC, new Date());
            parametersMovimiento.put(Constantes.MO_HOR, 10000);
            parametersMovimiento.put(Constantes.MO_DES, movimiento.getMo_des());
            parametersMovimiento.put(Constantes.MO_IMP, movimiento.getMo_imp());
            jdbcInsertMovimiento.execute(parametersMovimiento);

            transactionManager.commit(txStatus);
        } catch (Exception ex) {
            transactionManager.rollback(txStatus);
            if (ex.toString().contains("Duplicate")) {
                return 2;
            }
            return 0;
        }
        return 1;
    }
    public int reintegroDinero(Movimiento movimiento, Cliente cliente){
        TransactionDefinition txDef = new DefaultTransactionDefinition();
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(DBConnection.getInstance().getDataSource());
        TransactionStatus txStatus = transactionManager.getTransaction(txDef);
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(DBConnection.getInstance().getDataSource());
            int saldoDelCliente = Integer.parseInt(jdbcTemplate.queryForObject(Constantes.SALDO_EN_EL_CLIENTE, String.class, cliente.getCl_dni()));
            
            int saldoDeLaCuenta = Integer.parseInt(jdbcTemplate.queryForObject(Constantes.SALDO_EN_LA_CUENTA, String.class, cliente.getCl_dni()));
            
            
            JdbcTemplate actualizarCliente = new JdbcTemplate(DBConnection.getInstance().getDataSource());
            actualizarCliente.update(Constantes.ACTUALIZAR_SALDO_CLIENTE, saldoDelCliente-Integer.parseInt(movimiento.getMo_imp()),cliente.getCl_dni());
            
            JdbcTemplate actualizarCuenta = new JdbcTemplate(DBConnection.getInstance().getDataSource());
            actualizarCuenta.update(Constantes.ACTUALIZAR_SALDO_CUENTA, saldoDeLaCuenta - Integer.parseInt(movimiento.getMo_imp()),cliente.getCl_dni());

            SimpleJdbcInsert jdbcInsertMovimiento = new SimpleJdbcInsert(DBConnection.getInstance().getDataSource()).withTableName(Constantes.TABLA_DE_MOVIMIENTOS);
            Map<String, Object> parametersMovimiento = new HashMap<>();
            parametersMovimiento.put(Constantes.MO_NCU, movimiento.getMo_ncu());
            parametersMovimiento.put(Constantes.MO_FEC, new Date());
            parametersMovimiento.put(Constantes.MO_HOR, 10000);
            parametersMovimiento.put(Constantes.MO_DES, movimiento.getMo_des());
            parametersMovimiento.put(Constantes.MO_IMP, movimiento.getMo_imp());
            jdbcInsertMovimiento.execute(parametersMovimiento);

            transactionManager.commit(txStatus);
        } catch (Exception ex) {
            transactionManager.rollback(txStatus);
            if (ex.toString().contains("Duplicate")) {
                return 2;
            }
            return 0;
        }
        return 1;
    }

    public int existenciaCuenta(String cuenta) {
        int count = 0;
        try {
            JdbcTemplate jdbcSelect = new JdbcTemplate(
                    DBConnection.getInstance().getDataSource());
            String sql = Constantes.SELECT_COUNT_CLIENTE;

            count = jdbcSelect.queryForObject(sql, Integer.class, cuenta);
        } catch (Exception ex) {
            return 0;
        }
        return count;
    }
    
}
