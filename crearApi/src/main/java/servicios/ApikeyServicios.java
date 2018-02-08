/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import dao.ApikeyDAO;

/**
 *
 * @author Sergio
 */
public class ApikeyServicios {
    public boolean comprobarApikey(String apikey){
        ApikeyDAO dao = new ApikeyDAO();
        
        if(dao.comprobarApikeyDAO(apikey) == 1){
            return true;
        } else {
            return false;
        }
    }
}
