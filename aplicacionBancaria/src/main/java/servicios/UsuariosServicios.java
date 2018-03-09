/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import dao.UsuariosDAO;
import utils.PasswordHash;

/**
 *
 * @author Sergio
 */
public class UsuariosServicios {
    public boolean comprobarUser(User user){
        UsuariosDAO usuario = new UsuariosDAO();
            User usuarioBD = usuario.comprobarUser(user);
            if (usuarioBD != null) {
                try {
                    return PasswordHash.getInstance().validatePassword(user.getPassword(), usuarioBD.getPassword());
                } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
                    Logger.getLogger(UsuariosServicios.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            } else {
                return false;
            }
    }
    public User recogerParametros(String nombre, String password){
        User usuario = new User();
        if(nombre != null && password != null){
            usuario.setNombre(nombre);
            usuario.setPassword(password);
        }
        return usuario;
    }
}
