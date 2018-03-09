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
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public Cuenta getCuenta(Cuenta cuenta) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(DBConnection.getInstance().getDataSource());
        String sql = Constantes.SELECT_ONE_CUENTA;

        Cuenta cuentaBd = (Cuenta) jdbcTemplate.queryForObject(
                sql, new BeanPropertyRowMapper(Cuenta.class), cuenta.getCu_ncu());
        return cuentaBd;
    }

    public int saldoDeLaCuenta(Cuenta cuenta) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(DBConnection.getInstance().getDataSource());
        return Integer.parseInt(jdbcTemplate.queryForObject(Constantes.SALDO_EN_CUENTA, String.class, cuenta.getCu_ncu()));
    }

    public int borrarCuenta(Cuenta cuenta) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(DBConnection.getInstance().getDataSource());
            return jdbcTemplate.update(Constantes.BORRAR_CUENTA, String.class, cuenta.getCu_ncu());
        } catch (Exception ex) {
            if (ex.toString().contains("foreign key")) {
                return 2;
            }
            return 0;
        }
    }

    public int ingresoDinero(Movimiento movimiento, Cliente cliente) {
        TransactionDefinition txDef = new DefaultTransactionDefinition();
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(DBConnection.getInstance().getDataSource());
        TransactionStatus txStatus = transactionManager.getTransaction(txDef);
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(DBConnection.getInstance().getDataSource());
            int saldoDelCliente = Integer.parseInt(jdbcTemplate.queryForObject(Constantes.SALDO_EN_EL_CLIENTE, String.class, cliente.getCl_dni()));

            int saldoDeLaCuenta = Integer.parseInt(jdbcTemplate.queryForObject(Constantes.SALDO_EN_LA_CUENTA, String.class, cliente.getCl_dni()));

            JdbcTemplate actualizarCliente = new JdbcTemplate(DBConnection.getInstance().getDataSource());
            actualizarCliente.update(Constantes.ACTUALIZAR_SALDO_CLIENTE, saldoDelCliente + Integer.parseInt(movimiento.getMo_imp()), cliente.getCl_dni());

            JdbcTemplate actualizarCuenta = new JdbcTemplate(DBConnection.getInstance().getDataSource());
            actualizarCuenta.update(Constantes.ACTUALIZAR_SALDO_CUENTA, saldoDeLaCuenta + Integer.parseInt(movimiento.getMo_imp()), cliente.getCl_dni(), movimiento.getMo_ncu());

            SimpleJdbcInsert jdbcInsertMovimiento = new SimpleJdbcInsert(DBConnection.getInstance().getDataSource()).withTableName(Constantes.TABLA_DE_MOVIMIENTOS);
            Map<String, Object> parametersMovimiento = new HashMap<>();
            parametersMovimiento.put(Constantes.MO_NCU, movimiento.getMo_ncu());
            parametersMovimiento.put(Constantes.MO_FEC, new Date());
            parametersMovimiento.put(Constantes.MO_HOR, movimiento.getMo_hor());
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

    public int reintegroDinero(Movimiento movimiento, Cliente cliente) {
        TransactionDefinition txDef = new DefaultTransactionDefinition();
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(DBConnection.getInstance().getDataSource());
        TransactionStatus txStatus = transactionManager.getTransaction(txDef);
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(DBConnection.getInstance().getDataSource());
            int saldoDelCliente = Integer.parseInt(jdbcTemplate.queryForObject(Constantes.SALDO_EN_EL_CLIENTE, String.class, cliente.getCl_dni()));

            int saldoDeLaCuenta = Integer.parseInt(jdbcTemplate.queryForObject(Constantes.SALDO_EN_LA_CUENTA, String.class, cliente.getCl_dni()));

            JdbcTemplate actualizarCliente = new JdbcTemplate(DBConnection.getInstance().getDataSource());
            actualizarCliente.update(Constantes.ACTUALIZAR_SALDO_CLIENTE, saldoDelCliente - Integer.parseInt(movimiento.getMo_imp()), cliente.getCl_dni());

            JdbcTemplate actualizarCuenta = new JdbcTemplate(DBConnection.getInstance().getDataSource());
            actualizarCuenta.update(Constantes.ACTUALIZAR_SALDO_CUENTA, saldoDeLaCuenta + Integer.parseInt(movimiento.getMo_imp()), cliente.getCl_dni(), movimiento.getMo_ncu());

            SimpleJdbcInsert jdbcInsertMovimiento = new SimpleJdbcInsert(DBConnection.getInstance().getDataSource()).withTableName(Constantes.TABLA_DE_MOVIMIENTOS);
            Map<String, Object> parametersMovimiento = new HashMap<>();
            parametersMovimiento.put(Constantes.MO_NCU, movimiento.getMo_ncu());
            parametersMovimiento.put(Constantes.MO_FEC, new Date());
            parametersMovimiento.put(Constantes.MO_HOR, movimiento.getMo_hor());
            parametersMovimiento.put(Constantes.MO_DES, movimiento.getMo_des());
            parametersMovimiento.put(Constantes.MO_IMP, movimiento.getMo_imp());
            jdbcInsertMovimiento.execute(parametersMovimiento);

            transactionManager.commit(txStatus);
        } catch (Exception ex) {
            transactionManager.rollback(txStatus);
            Logger.getLogger(CuentasDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            String sql = Constantes.SELECT_COUNT_CUENTA;

            count = jdbcSelect.queryForObject(sql, Integer.class, cuenta);
        } catch (Exception ex) {
            return 0;
        }
        return count;
    }

    public int borrarCuentaTotal(Cuenta objetoCuenta) {
        TransactionDefinition txDef = new DefaultTransactionDefinition();
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(DBConnection.getInstance().getDataSource());
        TransactionStatus txStatus = transactionManager.getTransaction(txDef);
        int count = 0;
        try {
            JdbcTemplate jdbcSelect = new JdbcTemplate(
                    DBConnection.getInstance().getDataSource());
            String dni1 = this.dniAsociadoAlaCuenta(objetoCuenta);
            String dni2 = this.dni2AsociadoAlaCuenta(objetoCuenta);
            int numeroCuentas = this.numeroDeCuentas(dni1);
            int numeroCuentasTitular2 = 0;
            if (!"".equals(dni2)) {
                numeroCuentasTitular2 = this.numeroDeCuentas(dni2);
                if (numeroCuentasTitular2 < 2) {
                    jdbcSelect.update(Constantes.DELETE_CLIENTE, dni2);
                } else {
                    jdbcSelect.update(Constantes.QUERY_ACTUALIZAR, numeroCuentasTitular2 - 1, dni2);
                }
            }
            if (numeroCuentas < 2) {
                jdbcSelect.update(Constantes.DELETE_CLIENTE, dni1);
            } else {
                jdbcSelect.update(Constantes.QUERY_ACTUALIZAR, numeroCuentas - 1, dni1);
            }

            jdbcSelect.update(Constantes.BORRAR_CUENTA, objetoCuenta.getCu_ncu());
            jdbcSelect.update(Constantes.BORRA_MOVIMIENTOS_CUENTAS);
            transactionManager.commit(txStatus);
        } catch (Exception ex) {
            transactionManager.rollback(txStatus);
            return 0;
        }
        return 1;
    }

    public String dniAsociadoAlaCuenta(Cuenta cuenta) {
        JdbcTemplate jdbcSelect = new JdbcTemplate(
                DBConnection.getInstance().getDataSource());
        return jdbcSelect.queryForObject(Constantes.SELECT_DNI1_DELACUENTA, String.class, cuenta.getCu_ncu());
    }

    public String dni2AsociadoAlaCuenta(Cuenta cuenta) {
        JdbcTemplate jdbcSelect = new JdbcTemplate(
                DBConnection.getInstance().getDataSource());
        return jdbcSelect.queryForObject(Constantes.SELECT_DNI2_DELACUENTA, String.class, cuenta.getCu_ncu());
    }

    public int numeroDeCuentas(String dni) {
        JdbcTemplate jdbcSelect = new JdbcTemplate(
                DBConnection.getInstance().getDataSource());
        return Integer.parseInt(jdbcSelect.queryForObject(Constantes.QUERY_SELECCIONAR_CLIENTE, String.class, dni));
    }

}
