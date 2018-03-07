/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import model.Cliente;
import model.Cuenta;
import model.Datos;
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
public class ClientesDAO {

    public int comprobarCliente(Cliente cliente) {
        int count = 0;
        try {
            JdbcTemplate jdbcSelect = new JdbcTemplate(
                    DBConnection.getInstance().getDataSource());
            String sql = Constantes.SELECT_COUNT_CUENTA;

            count = jdbcSelect.queryForObject(sql, Integer.class, cliente.getCl_dni());
        } catch (Exception ex) {
            return count;
        }
        return count;
    }

    public Cliente seleccionarCliente(Cliente cliente) {
        JdbcTemplate jdbcSelect = new JdbcTemplate(
                DBConnection.getInstance().getDataSource());
        String sql = Constantes.SELECT_ONE_CLIENTE;

        Cliente clienteCogido = (Cliente) jdbcSelect.queryForObject(
                sql, new BeanPropertyRowMapper(Cliente.class), cliente.getCl_dni());
        return clienteCogido;
    }

    public int updateNumCuentas() {
        return 1;
    }

    public int nuevoCliente(Cliente cliente, Cuenta cuenta) {
        TransactionDefinition txDef = new DefaultTransactionDefinition();
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(DBConnection.getInstance().getDataSource());
        TransactionStatus txStatus = transactionManager.getTransaction(txDef);
        int gg = 0, hh = 0;
        try {
            SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(DBConnection.getInstance().getDataSource()).withTableName(Constantes.TABLA_DE_CLIENTES);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put(Constantes.CL_DNI, cliente.getCl_dni());
            parameters.put(Constantes.CL_NOM, cliente.getCl_nom());
            parameters.put(Constantes.CL_DIR, cliente.getCl_dir());
            parameters.put(Constantes.CL_TEL, cliente.getCl_tel());
            parameters.put(Constantes.CL_EMA, cliente.getCl_ema());
            parameters.put(Constantes.CL_FNA, cliente.getCl_fna());
            parameters.put(Constantes.CL_FCL, cliente.getCl_fcl());
            parameters.put(Constantes.CL_NCU, 1);
            parameters.put(Constantes.CL_SAL, cuenta.getCu_sal());
            gg = jdbcInsert.execute(parameters);

            SimpleJdbcInsert jdbcInsertCuenta = new SimpleJdbcInsert(DBConnection.getInstance().getDataSource()).withTableName(Constantes.TABLA_DE_CUENTAS);
            Map<String, Object> parametersCuenta = new HashMap<>();
            parametersCuenta.put(Constantes.CU_NCU, cuenta.getCu_ncu());
            parametersCuenta.put(Constantes.CU_DNI1, cliente.getCl_dni());
            parametersCuenta.put(Constantes.CU_DNI2, cliente.getCl_dni());
            parametersCuenta.put(Constantes.CU_SAL, cuenta.getCu_sal());
            hh = jdbcInsertCuenta.execute(parametersCuenta);

            SimpleJdbcInsert jdbcInsertMovimiento = new SimpleJdbcInsert(DBConnection.getInstance().getDataSource()).withTableName(Constantes.TABLA_DE_MOVIMIENTOS);
            Map<String, Object> parametersMovimiento = new HashMap<>();
            parametersMovimiento.put(Constantes.MO_NCU, cuenta.getCu_ncu());
            parametersMovimiento.put(Constantes.MO_FEC, new Date());
            parametersMovimiento.put(Constantes.MO_HOR, 10000);
            parametersMovimiento.put(Constantes.MO_DES, "Apertura Cuenta");
            parametersMovimiento.put(Constantes.MO_IMP, cuenta.getCu_sal());
            hh = jdbcInsertMovimiento.execute(parametersMovimiento);

            transactionManager.commit(txStatus);
        } catch (Exception ex) {
            transactionManager.rollback(txStatus);
            if (ex.toString().contains("Duplicate")) {
                return 2;
            }
            return 0;
        }
        return gg;
    }

    public int actualizarCliente(Cliente cliente, Cuenta cuenta) {
        TransactionDefinition txDef = new DefaultTransactionDefinition();
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(DBConnection.getInstance().getDataSource());
        TransactionStatus txStatus = transactionManager.getTransaction(txDef);
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(DBConnection.getInstance().getDataSource());
            int numeroDeCuentas = Integer.parseInt(jdbcTemplate.queryForObject(Constantes.QUERY_SELECCIONAR_CLIENTE, String.class, cliente.getCl_dni()));
            
            JdbcTemplate jtm = new JdbcTemplate(DBConnection.getInstance().getDataSource());
            jtm.update(Constantes.QUERY_ACTUALIZAR, numeroDeCuentas+1,cliente.getCl_dni());
            
            SimpleJdbcInsert jdbcInsertCuenta = new SimpleJdbcInsert(DBConnection.getInstance().getDataSource()).withTableName(Constantes.TABLA_DE_CUENTAS);
            Map<String, Object> parametersCuenta = new HashMap<>();
            parametersCuenta.put(Constantes.CU_NCU, cuenta.getCu_ncu());
            parametersCuenta.put(Constantes.CU_DNI1, cliente.getCl_dni());
            parametersCuenta.put(Constantes.CU_DNI2, cliente.getCl_dni());
            parametersCuenta.put(Constantes.CU_SAL, cuenta.getCu_sal());
            jdbcInsertCuenta.execute(parametersCuenta);

            SimpleJdbcInsert jdbcInsertMovimiento = new SimpleJdbcInsert(DBConnection.getInstance().getDataSource()).withTableName(Constantes.TABLA_DE_MOVIMIENTOS);
            Map<String, Object> parametersMovimiento = new HashMap<>();
            parametersMovimiento.put(Constantes.MO_NCU, cuenta.getCu_ncu());
            parametersMovimiento.put(Constantes.MO_FEC, new Date());
            parametersMovimiento.put(Constantes.MO_HOR, 10000);
            parametersMovimiento.put(Constantes.MO_DES, "Apertura Cuenta");
            parametersMovimiento.put(Constantes.MO_IMP, cuenta.getCu_sal());
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
    public int comprobarCuentaDni(Datos datos){
        JdbcTemplate jdbcSelect = new JdbcTemplate(
                DBConnection.getInstance().getDataSource());
        String sql = Constantes.COUNT_CLIENTE_CUENTA;

        int count = jdbcSelect.queryForObject(sql, Integer.class, datos.getDni());
        return count;
    }
}
