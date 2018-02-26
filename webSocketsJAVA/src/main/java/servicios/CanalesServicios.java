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
    public boolean crearCanal(Canal canal){
        CanalesDAO canales = new CanalesDAO();
        return canales.addCanal(canal);
    }
    public String getCanal(String idCanal){
        CanalesDAO canales = new CanalesDAO();
        return canales.getCanall(parseoString(idCanal));
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
