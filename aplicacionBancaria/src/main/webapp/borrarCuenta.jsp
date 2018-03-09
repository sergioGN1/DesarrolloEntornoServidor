<%-- 
    Document   : borrarCuenta
    Created on : 08-mar-2018, 16:56:48
    Author     : Sergio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="assets/js/jquery-3.2.1.js" ></script>
        <script src="assets/js/envioBorrarCuenta.js"></script>
    </head>
    <body>
        <form action="">
            <input type="text" placeholder="Numero de cuenta" id="numeroCuenta">
            <input type="hidden" value="visualizarDatos" id="accion">
            <input type="button" value="Consultar" id="borrar">
        </form>
    </body>
</html>
