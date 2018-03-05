/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

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
        if((total % 9) == segundaParteInt){
            return true;
        }else{
            return false;
        }
    }
}
