<%-- 
    Document   : listarMovimientos
    Created on : 06-mar-2018, 19:36:09
    Author     : Sergio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="assets/js/jquery-3.2.1.js"></script>
        <script src="assets/js/listadoMovimientosAjax.js"></script>
    </head>
    <body>
        <form action="">
            <input type="text" placeholder="DNI" id="dniCliente">
            <input type="text" placeholder="Numero de Cuenta" id="numCuenta">
            <input type="date" id="fecha1">
            <input type="date" id="fecha2">
            <input type="button" name="op" value="Enviar" id="leerMovimientos">
        </form>
        <table id="listarMovimientos">
            <tr>
                <td>Numero de cuenta</td>
                <td>Descripcion de la operacion</td>
                <td>Hor</td>
                <td>Importe</td>
                <td>Fecha</td>
            </tr>
    </body>
</html>
