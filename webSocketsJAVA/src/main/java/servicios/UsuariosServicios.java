/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import dao.UsuariosDAO;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Usuario;
import utils.PasswordHash;
import utils.Utils;

/**
 *
 * @author DAW
 */
public class UsuariosServicios {
    
    public UsuariosServicios(){
        
    }
    public Usuario recogidaParametros(String nombre,String password,String correo){
        Usuario user = new Usuario();
        
            if (!"".equals(nombre)) {

                user.setNombre(nombre);
            }
            if (!"".equals(password)) {
                String hash = "";
                
                
            try {
                
                hash = PasswordHash.getInstance().createHash(password);
                
            } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
                
                Logger.getLogger(UsuariosServicios.class.getName()).log(Level.SEVERE, null, ex);
            }
            
                user.setPassword(hash);


            if (!"".equals(correo)){
                user.setEmail(correo);
            }
            LocalDate fecha_activacion = LocalDate.now();
            Date fechaActivacion = Date.from(fecha_activacion.atStartOfDay(ZoneId.systemDefault()).toInstant());
            user.setFecha_activacion(fechaActivacion);
            String codigo = Utils.randomAlphaNumeric(10);
            user.setCodigo_activacion(codigo);
            user.setActivo(Boolean.FALSE);
            
        }
        return user;
    }
    /*public boolean comprobarPassword(String nombre,String password){
    UsuariosDAO userDAO = comprobarUser(nombre, password);
        try {
            if(userDAO.getActivo() && PasswordHash.getInstance().validatePassword(password, userDAO.getPassword())){
                return true;
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    return false;
    }*/
    public boolean existirUser(String user){
        UsuariosDAO usuario = new UsuariosDAO();
        
        if(usuario.existeUser(user) == 1){
            return true;
        }else {
            return false;
        }
    }
    public boolean comprobarLogin(String nombre,String password){
        UsuariosDAO usuario = new UsuariosDAO();
        
        return usuario.comprobarUser(nombre,password);
    }
    public boolean addUsers(String user, String pass){
        UsuariosDAO usuario = new UsuariosDAO();
        String passwordHasheada="";
        try {
            passwordHasheada = PasswordHash.getInstance().createHash(pass);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuariosServicios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(UsuariosServicios.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(usuario.addUsersDAO(user,passwordHasheada) == 1){
            return true;
        }else {
            return false;
        }
    }
}
