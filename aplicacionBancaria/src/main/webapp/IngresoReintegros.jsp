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
    </head>
    <body>
        <form action="" id="ingresar">
            <input type="text" placeholder="dni" id="dni">
            <input type="text" placeholder="Numero de cuenta" id="ncuenta">
            <input type="text" placeholder="Descripcion de la operacion" id="des">
            <input type="text" placeholder="Importe" id="imp">
            <input type="text" placeholder="Hor" id="hor">
            <input type="button" name="a" id="insertar" value="insertar">
        </form>
        <form action="" id="sacar">
            <input type="text" placeholder="dni" id="dni">
            <input type="text" placeholder="Numero de cuenta" id="ncuenta">
            <input type="text" placeholder="Descripcion de la operacion" id="des">
            <input type="text" placeholder="Importe" id="imp">
            <input type="button" name="a" id="sacar" value="sacar">
        </form>
    </body>
</html>
