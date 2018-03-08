<%-- 
    Document   : IngresoReintegros
    Created on : 07-mar-2018, 20:17:04
    Author     : Sergio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="assets/js/jquery-3.2.1.js" ></script>
        <script src="assets/js/envioIngresoReintegro.js"></script>
    </head>
    <body>
        <form action="" id="ingresar">
            <input type="text" placeholder="dni" class="dni">
            <input type="text" placeholder="Numero de cuenta" class="ncuenta">
            <input type="text" placeholder="Descripcion de la operacion" class="des">
            <input type="text" placeholder="Importe" class="imp">
            <input type="text" placeholder="Hor" class="hor">
            <input type="button" name="a" class="insertar" value="insertar">
        </form>
        <form action="" id="sacarDinero">
            <input type="text" placeholder="dni" class="dniSacar">
            <input type="text" placeholder="Numero de cuenta" class="ncuentaSacar">
            <input type="text" placeholder="Descripcion de la operacion" class="desSacar">
            <input type="text" placeholder="Importe" class="impSacar">
            <input type="text" placeholder="Hor" class="horSacar">
            <input type="button" name="a" class="sacar" value="sacar">
        </form>
    </body>
</html>
