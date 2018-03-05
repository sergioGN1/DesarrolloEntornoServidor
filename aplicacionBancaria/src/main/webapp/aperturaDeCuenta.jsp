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
        <script src="assets/js/jquery-3.2.1.js" ></script>
        <script src="assets/js/mostrarSegundoTitular.js" ></script>
        <script src="assets/js/envioAjax.js" ></script>
        <link href="assets/css/general.css" rel="stylesheet">
    </head>
    <body>
        <form action="aperturaCuenta" method="get"  name="formTitular" id="formTitular">
            <input type="text" name="dni" placeholder="Introduzca su DNI" id="dni1">
            <div id="datos">
                Datos del cliente
                <input type="text" name="nombre" placeholder="Introduzca el nombre el cliente" id="nom1">
                <input type="email" name="email" placeholder="Introduzca el coreo" id="email1">
                <input type="text" name="direccion" placeholder="Introduzca su direccion" id="direccion">
                <input type="text" name="telefono" placeholder="Introduzca el numero de telefono" id="telefono">
                <input type="date" name="fechaNacimiento" id="fechaNacimiento"> 
                <input type="date" name="fechaIngreso" id="fechaIngreso">
            </div>
            <div id="datosCuenta">
                Datos de la cuenta
                <input type="text" name="cuenta" placeholder="Introduzca el nombre el cliente" id="numCuenta">
                <input type="email" name="saldo" placeholder="Introduzca el saldo" id="saldo">
            </div>
            <label for="segundoTitular">Segundo titular</label>
            <input type="checkbox" id="segundoTitular">
            <div id="segundoTitulardiv">
                <input type="text" name="dni" placeholder="Introduzca su DNI" id="dni2">
                <input type="text" name="cuenta" placeholder="Introduzca el nombre del segundo titular" id="nom2">
                <input type="email" name="email" placeholder="Introduzca el coreo" id="email2">
                <input type="text" name="direccion" placeholder="Introduzca su direccion" id="direccion2">
                <input type="text" name="telefono" placeholder="Introduzca el numero de telefono" id="telefono2">
                <input type="date" name="fechaNacimiento" id="fechaNacimiento2"> 
                <input type="date" name="fechaIngreso" id="fechaIngreso2">
            </div>
            <input type="button" value="Registrar" name="acc" id="registro">
        </form>
        <div id="respuesta"></div>
    </body>
</html>
