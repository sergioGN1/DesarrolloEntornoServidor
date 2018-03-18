<%-- 
    Document   : IngresoReintegros
    Created on : 07-mar-2018, 20:17:04
    Author     : Sergio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="includes/header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ingresos y Reintegros</title>
        <script src="assets/js/jquery-3.2.1.js"></script>
        <script src="assets/js/comprobaciones.js"></script>
        <link href="assets/bootstrap/css/bootstrap.css" rel="stylesheet">
        <script src="assets/bootstrap/js/bootstrap.js"></script>
        <script src="assets/js/mostrardropdown.js" ></script>
        <!--<link href="../assets/css/general.css" rel="stylesheet">-->
        <link href="assets/css/estilos.css" rel="stylesheet">
        <script src="assets/js/envioIngresoReintegro.js"></script>
        <script src="assets/js/estilosIngresosReintegros.js"></script>
    </head>
    <body class="body-ingrei">
        <div class="container">
            <div class="row">
                <a class="retirar btn btn-danger col-6" id="botonMostrarSacar">Retirar</a>
                <a class="ingresar btn btn-danger col-6" id="botonMostrarMeter">Ingresar</a>
            </div>
            <div class="form-group contenedor-meter-dinero col-7 offset-2" id="divMeter">
                <div class="col-12 h4">
                    Introduzca los datos pertinentes para ingresar dinero
                </div><hr>
                <div  class="input-group col-10 offset-1">

                    <form action="" id="ingresar">
                        <input type="text" placeholder="dni" class="dni input-escribir col-12">
                        <input type="text" placeholder="Numero de cuenta" class="ncuenta input-escribir col-12">
                        <input type="text" placeholder="Descripcion de la operacion" class="des input-escribir col-12">
                        <input type="text" placeholder="Importe" class="imp input-escribir col-12">
                        <input type="text" placeholder="Hor" class="hor input-escribir col-12">
                        <input type="button" name="a" class="insertar btn btn-danger col-12" value="insertar">
                    </form>
                </div>
            </div>
            <div class="form-group contenedor-sacar-dinero col-7 offset-2" id="divSacar">
                <div class="col-12 h4">
                    Introduzca los datos pertinentes para retirar dinero
                </div><hr>
                <div  class="input-group col-10 offset-1">

                    <form action="" id="sacarDinero">
                        <input type="text" placeholder="dni" class="dniSacar col-12 input-escribir">
                        <input type="text" placeholder="Numero de cuenta" class="ncuentaSacar input-escribir col-12">
                        <input type="text" placeholder="Descripcion de la operacion" class="desSacar col-12 input-escribir">
                        <input type="text" placeholder="Importe" class="impSacar col-12 input-escribir">
                        <input type="text" placeholder="Hor" class="horSacar col-12 input-escribir">
                        <input type="button" name="a" class="sacar col-12 btn btn-danger" value="sacar">
                    </form>
                </div>
            </div>
        </div>

    </body>
</html>
