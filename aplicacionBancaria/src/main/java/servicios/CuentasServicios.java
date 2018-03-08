/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import dao.CuentasDAO;
import model.Cliente;
import model.Cuenta;
import model.Movimiento;

/**
 *
 * @author Sergio
 */
public class CuentasServicios {

    public boolean comprobarCuenta(String numeroCuenta) {
        String numeroString = numeroCuenta;
        String primeraParte = numeroString.substring(0, numeroString.length() - 1);
        String segundaParte = numeroString.substring(primeraParte.length());
        int primeraParteInt = Integer.parseInt(primeraParte);
        int segundaParteInt = Integer.parseInt(segundaParte);
        int aux = primeraParteInt;
        int total = 0;
        while (primeraParteInt != 0) {
            total = total + (primeraParteInt % 10);
            primeraParteInt = primeraParteInt / 10;
        }
        if ((total % 9) == segundaParteInt) {
            return true;
        } else {
            return false;
        }
    }
    public boolean comprobarExistenciaCuenta(String cuenta){
        CuentasDAO cuentaDAO = new CuentasDAO();
        if(cuentaDAO.existenciaCuenta(cuenta) == 1){
            return true;
        }else{
            return false;
        }
    }
    public boolean saldoDeLaCuenta(Cuenta cuenta){
        CuentasDAO cuentaDAO = new CuentasDAO();
        if(cuentaDAO.saldoDeLaCuenta(cuenta) > 0){
            return false;
        }else{
            return true;
        }
    }
    public int borrarCuenta(Cuenta cuenta){
        CuentasDAO cuentaDAO = new CuentasDAO();
        return cuentaDAO.borrarCuenta(cuenta);
        
    }
    public int saldoCuenta(String cuentaNum){
        CuentasDAO cuentaDAO = new CuentasDAO();
        Cuenta cuenta = new Cuenta();
        cuenta.setCu_ncu(cuentaNum);
        return cuentaDAO.saldoDeLaCuenta(cuenta);
    }
    public boolean ingresoDinero(Movimiento movimiento, Cliente cliente) {
        CuentasDAO cuenta = new CuentasDAO();
        if (cuenta.ingresoDinero(movimiento, cliente) == 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean reintegroDinero(Movimiento movimiento, Cliente cliente) {
        CuentasDAO cuenta = new CuentasDAO();
        if (cuenta.reintegroDinero(movimiento, cliente) == 1) {
            return true;
        } else {
            return false;
        }
    }
}
