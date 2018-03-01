/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import dao.UsuariosDAO;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Mensaje;
import model.MensajeBaseDatos;
import model.MensajeFechas;
import model.Usuario;
import utils.PasswordHash;
import java.util.List;

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
        if (!password.equals("google")) {
            Usuario usuarioBD = usuario.comprobarUser(nombre, password);
            if (usuarioBD != null) {
                try {
                    return PasswordHash.getInstance().validatePassword(password, usuarioBD.getPassword());
                } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
                    Logger.getLogger(UsuariosServicios.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean addUsers(String user, String pass) {
        UsuariosDAO usuario = new UsuariosDAO();
        String passwordHasheada = "";
        int g = 0, h = 0;
        if (!pass.equals("google")) {
            try {
                passwordHasheada = PasswordHash.getInstance().createHash(pass);
                g = usuario.addUsersDAO(user, passwordHasheada);
            } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
                Logger.getLogger(UsuariosServicios.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            h = usuario.addUsersDAO(user, pass);
        }
        if (h == 1 || g == 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean guardarMensaje(Mensaje mensaje) {
        UsuariosDAO usuario = new UsuariosDAO();
        if (mensaje.getDestino().equals("0")) {
            mensaje.setDestino("5");
        }
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

    public List<String> usuariosSuscritosAlDestino(Mensaje mensajeCanal) {
        UsuariosDAO usuario = new UsuariosDAO();
        return usuario.usuariosSuscritos(mensajeCanal);
    }

    public ArrayList<MensajeBaseDatos> getMensajes(MensajeFechas mensaje) {
        UsuariosDAO usuario = new UsuariosDAO();
        return usuario.getMessages(mensaje);

    }
}
