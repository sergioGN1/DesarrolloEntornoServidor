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
    public String getCanal(String nombreCanal){
        CanalesDAO canales = new CanalesDAO();
        return canales.getCanall(nombreCanal);
    }
}
