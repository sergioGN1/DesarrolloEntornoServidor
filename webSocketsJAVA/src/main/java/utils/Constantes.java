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
    public static final String FILAS_INSERTADAS = "filas_insertadas";
    public static final String FILAS_ACTUALIZADAS = "filas_actualizadas";
    public static final String LOGIN = "LOGIN";
    public static final String LOGIN_OK = "ok";
    //ACCIONES
    public static final String OPERACION_REGISTRARSE = "Registrar";
    public static final String OPERACION_ACTIVAR = "activar";
    public static final String OPERACION_LOGIN = "Login";
    public static final String ACCION = "accion";
    //COLUMNAS TABLAS
    public static final String NOMBRE = "nombre";
    public static final String PASSWORD = "password";
    public static final String ACTIVO = "activo";
    public static final String CODIGO_ACTIVACION = "codigo_activacion";
    public static final String FECHA_ACTIVACION = "fecha_activacion";
    public static final String EMAIL = "email";
    public static final String NOMBRE_TABLA_USUARIOS = "USERS";
    public static final String NOMBRE_TABLA_MENSAJES = "MENSAJES";
    public static final String ID = "ID";
    //CONSTANTES SERVLET
    public static final String USUARIO = "usuario";
    public static final String CORREO = "correo";
    //SELECT
    public static final String SELECT_COUNT_USERS = "SELECT count(*) FROM USERS WHERE NOMBRE = ?";
    public static final String SELECT_ONE_USER = "SELECT * FROM USERS WHERE NOMBRE = ?";
    public static final String SELECT_FECHA_ACTIVACION = "SELECT FECHA_ACTIVACION FROM USERS WHERE CODIGO_ACTIVACION = ?";
    //JSP
    public static final String ACTIVADO_JSP = "activado.jsp";
    public static final String LOGIN_CORRECTO_JSP = "loginCorrecto.jsp";
    public static final String REGISTRADO_JSP = "registrado.jsp";
    //UPDATES
    public static final String ACTIVAR_USER = "UPDATE USERS SET ACTIVO = ? WHERE CODIGO_ACTIVACION = ?";
}