<%-- 
    Document   : eleccion
    Created on : 09-mar-2018, 19:58:55
    Author     : Sergio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="includes/header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Eleccion de operacion</title>
        <script src="assets/js/jquery-3.2.1.js"></script>
        <link href="assets/bootstrap/css/bootstrap.css" rel="stylesheet">
        <script src="assets/bootstrap/js/bootstrap.js"></script>
        <!--<link href="../assets/css/general.css" rel="stylesheet">-->
        <link href="assets/css/estilos.css" rel="stylesheet">
    </head>
    <body class="body-eleccion">
        <div class="container">
            <div class="offset-3 col-5 contenedor-botones-eleccion">
                <div class="row">
                    <a href="aperturaDeCuenta.jsp" class="btn btn-primary col-12 boton-gordo">Apertura de Cuenta</a>
                </div>
                <div class="row">
                    <a href="IngresoReintegros.jsp" class="btn btn-danger col-12 boton-gordo">Ingresos y reintegros</a>
                </div>
                <div class="row">
                    <a href="borrarCuenta.jsp" class="btn btn-success col-12 boton-gordo">Borrar Cuenta</a>
                </div>
                <div class="row">
                    <a href="listarMovimientos.jsp" class="btn btn-warning col-12 boton-gordo">Listado de movimientos</a>
                </div>
            </div>
        </div>
    </body>
</html>
