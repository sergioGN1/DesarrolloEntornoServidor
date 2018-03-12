<%-- 
    Document   : listarMovimientos
    Created on : 06-mar-2018, 19:36:09
    Author     : Sergio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="includes/header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="assets/js/jquery-3.2.1.js"></script>
        <script src="assets/js/comprobaciones.js"></script>
        <script src="assets/js/listadoMovimientosAjax.js"></script>
        <link href="assets/bootstrap/css/bootstrap.css" rel="stylesheet">
        <script src="assets/bootstrap/js/bootstrap.js"></script>
        <link href="assets/css/estilos.css" rel="stylesheet">
    </head>
    <body class="body-listar">
        <div class="container">
            <div class="form-group offset-1">
                <div class="input-group fondo-blanco col-10">
                    <form action="">
                        <input type="text" placeholder="DNI" id="dniCliente" class="input-escribir">
                        <input type="text" placeholder="Numero de Cuenta" id="numCuenta" class="input-escribir">
                        <input type="date" id="fecha1" class="input-escribir">
                        <input type="date" id="fecha2" class="input-escribir">
                        <input type="button" name="op" value="Enviar" id="leerMovimientos" class="btn btn-danger">
                    </form>
                </div>
            </div>
            <div class="div-modal">
                <span class="close">&times;</span>
                <div class="contenido-modal">
                    <table id="listarMovimientos" class="table table-striped">
                        <tr>
                            <td>Numero de cuenta</td>
                            <td>Descripcion de la operacion</td>
                            <td>Hor</td>
                            <td>Importe</td>
                            <td>Fecha</td>
                        </tr>
                    </table> 
                </div>
            </div>
        </div>
    </body>
</html>
