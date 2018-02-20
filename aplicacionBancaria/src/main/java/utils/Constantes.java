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
    public static final String ID = "ID";
    //CONSTANTES SERVLET
    public static final String USUARIO = "usuario";
    public static final String CORREO = "correo";
    
    //REQUEST
    public static final String REQUEST_ATTRIBUTE_ALUMNO = "alumno";
    public static final String REQUEST_ATTRIBUTE_ASIGNATURA = "asignatura";
    public static final String REQUEST_ATTRIBUTE_NOTA = "nota";
    //MENSAJES DE OPERACIONES
    public static final String MENSAJE_INSERTADO_CORRECTO = "Inserción correcta";
    public static final String MENSAJE_INSERTADO_INCORRECTO = "Se ha producido un error al insertar";
    public static final String MENSAJE_BORRADO_CORRECTO = "Borrado correctamente";
    public static final String MENSAJE_BORRADO_INCORRECTO = "Se ha producido un error al borrar";
    public static final String MENSAJE_ACTUALIZADO_CORRECTO = "Actualizacion correcta";
    public static final String MENSAJE_ACTUALIZADO_INCORRECTO = "Se ha producido un error al actualizar";
    //SELECT
    public static final String SELECT_COUNT_USERS = "";
    public static final String SELECT_ONE_USER = "SELECT * FROM USERS WHERE NOMBRE = ?";
    public static final String SELECT_FECHA_ACTIVACION = "SELECT FECHA_ACTIVACION FROM USERS WHERE CODIGO_ACTIVACION = ?";
    //JSP
    public static final String ACTIVADO_JSP = "activado.jsp";
    public static final String LOGIN_CORRECTO_JSP = "loginCorrecto.jsp";
    public static final String REGISTRADO_JSP = "registrado.jsp";
    //UPDATES
    public static final String ACTIVAR_USER = "UPDATE USERS SET ACTIVO = ? WHERE CODIGO_ACTIVACION = ?";
    public static String SELECT_COUNT_CLIENTE = "SELECT count(*) FROM CLIENTES WHERE cl_dni = ?";
    public static String SELECT_ONE_CLIENTE = "SELECT * FROM CLIENTES WHERE cl_dni = ?";
}