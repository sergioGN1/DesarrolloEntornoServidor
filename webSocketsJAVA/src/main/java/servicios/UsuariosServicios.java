/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import com.google.api.client.util.DateTime;
import dao.UsuariosDAO;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Mensaje;
import model.MensajeBaseDatos;
import model.MensajeFechas;
import model.Usuario;
import utils.PasswordHash;
import utils.Utils;

/**
 *
 * @author DAW
 */
public class UsuariosServicios {

    public UsuariosServicios() {

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
    public boolean existirUser(String user) {
        UsuariosDAO usuario = new UsuariosDAO();

        if (usuario.existeUser(user) == 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean comprobarLogin(String nombre, String password) {
        UsuariosDAO usuario = new UsuariosDAO();
        Usuario usuarioBD = usuario.comprobarUser(nombre, password);
        if(usuarioBD != null){
            try {
                return PasswordHash.getInstance().validatePassword(password, usuarioBD.getPassword());
            } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
                Logger.getLogger(UsuariosServicios.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean addUsers(String user, String pass) {
        UsuariosDAO usuario = new UsuariosDAO();
        String passwordHasheada = "";
        try {
            passwordHasheada = PasswordHash.getInstance().createHash(pass);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuariosServicios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(UsuariosServicios.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (usuario.addUsersDAO(user, passwordHasheada) == 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean guardarMensaje(Mensaje mensaje) {
        UsuariosDAO usuario = new UsuariosDAO();
        return usuario.addMensajeDAO(mensaje);

    }

    public Date parseoFecha(String fecha) {
        Date convertido = null;
        try {
            DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            convertido = fechaHora.parse(fecha);

        } catch (ParseException ex) {
            Logger.getLogger(UsuariosServicios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return convertido;
    }

    public ArrayList<MensajeBaseDatos> getMensajes(MensajeFechas mensaje) {
        UsuariosDAO usuario = new UsuariosDAO();
        return usuario.getMessages(mensaje);

    }
}
