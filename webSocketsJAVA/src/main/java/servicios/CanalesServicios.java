/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import java.util.List;
import model.Canal;
import dao.CanalesDAO;
import java.util.ArrayList;
import model.CanalSuscrito;
import model.Mensaje;
import model.Suscripcion;
/**
 *
 * @author Sergio
 */
public class CanalesServicios {
    public List<Canal> getCanales(){
        CanalesDAO canales = new CanalesDAO();
        return canales.getAllCanales();
    }
    public Canal crearCanal(Canal canal){
        CanalesDAO canales = new CanalesDAO();
        return canales.addCanal(canal);
    }
    public String getCanal(String idCanal){
        CanalesDAO canales = new CanalesDAO();
        return canales.getCanall(parseoString(idCanal));
    }
    public List<CanalSuscrito> getCanalSuscrito(Mensaje canalSuscrito){
        CanalesDAO canales = new CanalesDAO();
        return canales.getCanalSuscritoDAO(canalSuscrito);
    }
    public boolean suscribirse(Suscripcion suscripcion){
        CanalesDAO canales = new CanalesDAO();
        return canales.addSuscripcion(suscripcion);
    }
    public int parseoString (String cadena){
        int id = 0;
        id = Integer.parseInt(cadena);
        return id;
    }
}
