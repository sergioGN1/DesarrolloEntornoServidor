package utils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author oscar
 */
public class Constantes {
    //VARIABLES SUELTAS
    //ACCIONES
    public static final String OPERACION_REGISTRARSE = "Registrar";
    public static final String OPERACION_ACTIVAR = "activar";
    public static final String OPERACION_LOGIN = "Login";
    public static final String ACCION = "accion";
    //COLUMNAS TABLAS
    public static final String ID_CANAL = "id_canal";
    public static final String ID_CANAL_MENSAJES = "ID_CANAL";
    public static final String FECHA_MENSAJES = "FECHA";
    public static final String USER = "USER";
    public static final String CONTENIDO_MENSAJES = "contenido";
    public static final String USER_ADMIN_BIEN = "user_admin";
    public static final String NOMBRE_CANAL = "nombre";
    public static final String NOMBRE = "user";
    public static final String PASSWORD = "password";
    public static final String NOMBRE_TABLA_LOGIN = "login";
    public static final String NOMBRE_TABLA_MENSAJES = "mensajes";
    public static final String NOMBRE_TABLA_SUSCRIPCIONES = "canal_usuarios";
    public static final String ID = "ID";
    public static final String USER_ADMIN = "NOMBRE_USUARIO";
    public static final String NOMBRE_TABLA_CANALES = "canal";
    public static final String CLAVE = "clave";
    //CONSTANTES SERVLET
    public static final String USUARIO = "usuario";
    public static final String CORREO = "correo";
    //SELECT
    public static final String SELECT_CANALES_SUSCRITO = "select canal.id, canal.nombre from canal_usuarios right join canal on canal_usuarios.id_canal = canal.id where canal_usuarios.user = ?";
    public static final String SELECT_MENSAJES = "SELECT mensajes.contenido,canal.nombre,mensajes.USER,mensajes.FECHA FROM mensajes LEFT JOIN canal ON mensajes.ID_CANAL = canal.id where mensajes.USER = ? AND mensajes.FECHA between ? and ?";
    public static final String SELECT_ONE_CANAL = "SELECT user_admin FROM canal WHERE id = ?";
    public static final String SELECT_COUNT_USERS = "SELECT count(*) FROM login WHERE user = ?";
    public static final String SELECT_ONE_USER = "SELECT * FROM login WHERE user = ?";
    public static final String SELECT_ALL_CANALES = "SELECT * FROM canal";
    public static final String SELECT_LIST_USER_SUSCRITOS = "SELECT user FROM canal_usuarios WHERE id_canal = ?";
    //JSP
    public static final String LOGIN_HECHO = "loginHechoWS";
    public static final String VARIABLE_ERROR = "error";
    public static final String USUARIOS = "usuarios";
    public static final String JSP_LISTAR = "lista.jsp";
    public static final String MENSAJE_ERROR_LOGIN = "Debe iniciar sesion en el WebSocket para poder acceder aqui";
    public static final String ACTIVADO_JSP = "activado.jsp";
    public static final String LOGIN_CORRECTO_JSP = "loginCorrecto.jsp";
    public static final String REGISTRADO_JSP = "registrado.jsp";
    //UPDATES
    public static final String ACTIVAR_USER = "UPDATE USERS SET ACTIVO = ? WHERE CODIGO_ACTIVACION = ?";
}