<%-- 
    Document   : aperturaDeCuenta
    Created on : 20-feb-2018, 18:11:47
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
        <form action="aperturaCuenta" method="get">
            <input type="text" name="dni" placeholder="Introduzca su DNI">
            <input type="text" name="cuenta" placeholder="Introduzca el numero de la cuenta">
            <input type="submit" value="Registrar"> 
        </form>
    </body>
</html>
