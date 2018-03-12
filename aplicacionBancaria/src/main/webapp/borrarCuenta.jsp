<%-- 
    Document   : borrarCuenta
    Created on : 08-mar-2018, 16:56:48
    Author     : Sergio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="includes/header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="assets/js/jquery-3.2.1.js" ></script>
        <script src="assets/js/comprobaciones.js"></script>
        <script src="assets/js/envioBorrarCuenta.js"></script>
        <link href="assets/bootstrap/css/bootstrap.css" rel="stylesheet">
        <script src="assets/bootstrap/js/bootstrap.js"></script>
        <!--<link href="../assets/css/general.css" rel="stylesheet">-->
        <link href="assets/css/estilos.css" rel="stylesheet">
    </head>
    <body class="body-borrar">
        <div class="container">
            <div clas="row">
                <div class="form-group offset-4">
                    <form action="" class="fondo-blanco col-5">
                        <input type="text" placeholder="Numero de cuenta" id="numeroCuenta" class="input-escribir">
                        <input type="hidden" value="visualizarDatos" id="accion" class="input-escribir">
                        <input type="button" value="Consultar" id="borrar" class="btn btn-danger">
                    </form>
                </div>
            </div>
            <div class="row">
                <div class="div-modal">
                    <div class="contenido-modal">
                        <table id="tablaResultado" class="table table-striped fondo-blanco">
                            <tr>
                                <td>Numero de cuenta</td>
                                <td>Dni 1</td>
                                <td>Dni 2</td>
                                <td>Saldo</td>
                            </tr>
                        </table>  
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
