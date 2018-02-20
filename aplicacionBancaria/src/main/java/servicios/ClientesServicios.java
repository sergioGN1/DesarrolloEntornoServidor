/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;
import dao.ClientesDAO;
import model.Cliente;
/**
 *
 * @author Sergio
 */
public class ClientesServicios {
    public boolean comprobarUsuario(Cliente cliente){
       ClientesDAO clDAO = new ClientesDAO();
       if(clDAO.comprobarCliente(cliente) >= 1){
           return true;
       }else{
           return false;
       }
    }
}
