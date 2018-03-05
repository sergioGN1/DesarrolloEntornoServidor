/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import dao.ClientesDAO;
import model.Cliente;
import model.Cuenta;

/**
 *
 * @author Sergio
 */
public class ClientesServicios {

    public boolean comprobarUsuario(Cliente cliente) {
        ClientesDAO clDAO = new ClientesDAO();
        if (clDAO.comprobarCliente(cliente) >= 1) {
            return true;
        } else {
            return false;
        }
    }

    public int insertarNuevoCliente(Cliente cliente, Cuenta cuenta) {
        ClientesDAO clDAO = new ClientesDAO();
        return clDAO.nuevoCliente(cliente, cuenta);

    }

    public int actualizarCliente(Cliente cliente, Cuenta cuenta) {
        ClientesDAO clDAO = new ClientesDAO();
        return clDAO.actualizarCliente(cliente, cuenta);

    }
    public Cliente getUserByDni(Cliente objetoCliente) {
        ClientesDAO clDAO = new ClientesDAO();
        return clDAO.seleccionarCliente(objetoCliente);
    }

    
}
