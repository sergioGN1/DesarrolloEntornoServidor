/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import dao.ClientesDAO;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Cliente;
import model.Cuenta;
import model.Datos;
import utils.Constantes;

/**
 *
 * @author Sergio
 */
public class ClientesServicios {

    public boolean comprobarDNI(String dni) {
        Pattern pat = Pattern.compile(Constantes.PATRON_EXP_REG);
        Matcher mat = pat.matcher(dni);
        return mat.matches();

    }

    public boolean comprobarUsuario(Cliente cliente) {
        ClientesDAO clDAO = new ClientesDAO();
        if (clDAO.comprobarCliente(cliente) >= 1) {
            return true;
        } else {
            return false;
        }
    }

    public int insertarNuevoCliente(Cliente cliente, Cuenta cuenta,Cliente cliente2) {
        ClientesDAO clDAO = new ClientesDAO();
        return clDAO.nuevoCliente(cliente, cuenta, cliente2);

    }

    public int actualizarCliente(Cliente cliente, Cuenta cuenta) {
        ClientesDAO clDAO = new ClientesDAO();
        return clDAO.actualizarCliente(cliente, cuenta);

    }

    public Cliente getUserByDni(Cliente objetoCliente) {
        ClientesDAO clDAO = new ClientesDAO();
        return clDAO.seleccionarCliente(objetoCliente);
    }
    
    public boolean comprobarCuentaDni(Datos datos){
        ClientesDAO clDAO = new ClientesDAO();
        if(clDAO.comprobarCuentaDni(datos) == 1){
            return true;
        }else{
            return false;
        }
    }
}
