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
   
    
    public static final String PATRON_EXP_REG = "[0-9]{8}[A-Z]{1}";
    public static final String VARIABLE_ERROR = "error";
    public static final String MENSAJE_DNI_MAL_FORMADO = "El DNI esta mal formado";
    public static final String RECOGER_PRIMER_TITULAR = "primerTitular";
    public static final String RECOGER_SEGUNDO_TITULAR = "segundoTitular";
    public static final String MENSAJE_CLIENTE_NO_ENCONTRADO = "No se ha encontrado el usuario";
    public static final String MENSAJE_USUARO_YA_EXISTE = "Este DNI ya está registrado";
    public static final String MENSAJE_CLIENTE_CREADO_OK="El cliente se ha creado correctamente";
    public static final String MENSAJE_CLIENTE_CREADO_ERROR = "Se ha producido un error al crear el cliente";
    public static final String MENSAJE_NUMERO_DE_CUENTA_ERRONEO = "El formato del numero de cuenta no es correcto";
    public static final String MENSAJE_DUPLICADO_CUENTA = "El numero de cuenta ya se encuentra registrado";
    public static final String MENSAJE_NO_EXISTE_ASOCIACION = "Esta cuenta no está asociada al DNI ";
    public static final String DNI_CUENTA = "El dni o la cuenta no coinciden";
    public static final String ERROR_INGRESO = "Hubo un problema al realizar el ingreso";
    public static final String ERROR_REINTEGRO = "Hubo un problema al realizar el reintegro";
    public static final String INGRESO_OK = "Ingreso realizado con éxito";
    public static final String REINTEGRO_OK = "Reintegro realizado con éxito";
    public static final String NO_SUFUCIENTE_SALDO = "No hay suficiente saldo en su cuenta";
    
    public static final String INSERTAR = "insertar";
    public static final String QUITAR = "quitar";
    public static final String CL_DNI = "cl_dni";
    public static final String CL_NOM = "cl_nom";
    public static final String CL_DIR = "cl_dir";
    public static final String CL_TEL = "cl_tel";
    public static final String CL_EMA = "cl_ema";
    public static final String CL_FNA = "cl_fna";
    public static final String CL_FCL = "cl_fcl";
    public static final String CL_NCU = "cl_ncu";
    public static final String CL_SAL = "cl_sal";
    public static final String CU_NCU = "cu_ncu";
    public static final String CU_DNI1 = "cu_dn1";
    public static final String CU_DNI2 = "cu_dn2";
    public static final String CU_SAL = "cu_sal";
    public static final String MO_NCU = "mo_ncu";
    public static final String MO_FEC = "mo_fec";
    public static final String MO_HOR = "mo_hor";
    public static final String MO_DES = "mo_des";
    public static final String MO_IMP = "mo_imp";
    public static final String METER_DINERO_API = "Meter";
    public static final String SACAR_DINERO_API = "Sacar";
    //NOMBRE DE TABLAS
    public static final String TABLA_DE_CLIENTES = "clientes";
    public static final String TABLA_DE_CUENTAS = "cuentas";
    public static final String TABLA_DE_MOVIMIENTOS = "movimientos";
    
    
    public static final String BORRAR_CUENTA = "DELETE FROM cuentas where cu_ncu = ?";
    public static final String QUERY_ACTUALIZAR = "UPDATE clientes SET cl_ncu = ?  clientes where cl_dni = ?";
    public static final String QUERY_SELECCIONAR_CLIENTE = "SELECT cl_ncu FROM clientes where cl_dni = ?";
    public static final String SELECT_COUNT_CLIENTE = "SELECT count(*) FROM clientes WHERE cl_dni = ?";
    public static final String SELECT_COUNT_CUENTA = "SELECT count(*) FROM cuentas WHERE cu_ncu = ?";
    public static final String SELECT_ONE_CLIENTE = "SELECT * FROM clientes WHERE cl_dni = ?";
    public static final String COUNT_CLIENTE_CUENTA = "SELECT COUNT(*) FROM cuentas where cu_dn1 = ?";
    public static final String SELECT_MOVIMIENTOS = "SELECT * FROM movimientos where mo_ncu = ? AND mo_fec between ? and ? ";
    public static final String SALDO_EN_LA_CUENTA = "SELECT cu_sal FROM cuentas where cu_dn1 = ?";
    public static final String SALDO_EN_CUENTA = "SELECT cu_sal FROM cuenta where cu_ncu = ?";
    public static final String SALDO_EN_EL_CLIENTE = "SELECT cl_sal FROM clientes where cl_dni = ?";
    public static final String ACTUALIZAR_SALDO_CLIENTE = "UPDATE clientes SET cl_sal = ? where cl_dni = ?";
    public static final String ACTUALIZAR_SALDO_CUENTA = "UPDATE cuentas SET cu_sal = ? where cu_dn1 = ? AND cu_ncu = ?";
    public static final String SELECT_DNI1_DELACUENTA = "SELECT cu_dn1 FROM cuentas where cu_ncu = ?";
    public static final String SELECT_DNI2_DELACUENTA = "SELECT cu_dn2 FROM cuentas where cu_ncu = ?";
    public static final String DELETE_CLIENTE = "DELETE FROM clientes WHERE cl_dni = ?";
    public static final String COUNT_CLIENTE_BY_DNI = "SELECT COUNT(*) FROM clientes where cu_dni = ?";
    public static final String SELECT_ONE_CUENTA = "SELECT * FROM cuentas WHERE cu_ncu = ?";
}