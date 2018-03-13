<%-- 
    Document   : aperturaDeCuenta
    Created on : 20-feb-2018, 18:11:47
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
        <link href="assets/bootstrap/css/bootstrap.css" rel="stylesheet">
        <script src="assets/bootstrap/js/bootstrap.js"></script>
        <script src="assets/js/mostrardropdown.js" ></script>
        <script src="assets/js/mostrarSegundoTitular.js" ></script>
        <script src="assets/js/envioAjax.js" ></script>
        <link href="assets/css/estilos.css" rel="stylesheet">
    </head>
    <body class="body-abrir">
        <div class="container">
            <form action="aperturaCuenta" method="post"  name="formTitular" id="formTitular">
                <div class="row">
                    <div class="form-group col-5">
                        <div class="btn btn-danger flotado-izquierda offset-3 segundoTitutlar" for="segundoTitular">
                            <label for="segundoTitular">Segundo titular</label>
                            <input type="checkbox" id="segundoTitular">
                        </div>
                        <div id="segundoTitulardiv">
                            <input type="text" name="dni" placeholder="Introduzca su DNI" id="dni2" class="input-segundo-titular col-10 offset-1">
                            <input type="text" name="cuenta" placeholder="Introduzca el nombre del segundo titular" class="input-segundo-titular col-10 offset-1" id="nom2">
                            <input type="email" name="email" placeholder="Introduzca el coreo" id="email2" class="input-segundo-titular col-10 offset-1">
                            <input type="text" name="direccion" placeholder="Introduzca su direccion" id="direccion2" class="input-segundo-titular col-10 offset-1">
                            <input type="text" name="telefono" placeholder="Introduzca el numero de telefono" id="telefono2" class="input-segundo-titular col-10 offset-1">
                            <input type="date" name="fechaNacimiento" id="fechaNacimiento2" class="input-segundo-titular col-10 offset-1"> 
                            <input type="date" name="fechaIngreso" id="fechaIngreso2" class="input-segundo-titular col-10 offset-1">
                        </div>
                    </div>
                    <div  class="col-6 offset-1">
                        <div class="fondo-blanco col-12 input-solo">
                            <input type="text" name="dni" placeholder="Introduzca su DNI" id="dni1" class="col-12 input-segundo-titular">
                        </div>
                    </div>
                </div>
                <div class="form-group div-datos-cuenta-cliente">
                    <span class="close">
                        &times;
                    </span>
                    <div class="contenido-modal-cliente-cuenta col-7 offset-4">
                        <div class="row">
                            <div id="datos" class="modal-clientes col-5">
                                <span class="h5">Datos del cliente</span>
                                <input type="text" name="nombre" placeholder="Introduzca el nombre el cliente" id="nom1" class="input-segundo-titular">
                                <input type="email" name="email" placeholder="Introduzca el coreo" id="email1" class="input-segundo-titular">
                                <input type="text" name="direccion" placeholder="Introduzca su direccion" id="direccion" class="input-segundo-titular">
                                <input type="text" name="telefono" placeholder="Introduzca el numero de telefono" id="telefono" class="input-segundo-titular">
                                <input type="date" name="fechaNacimiento" id="fechaNacimiento" class="input-segundo-titular"> 
                                <input type="date" name="fechaIngreso" id="fechaIngreso" class="input-segundo-titular">
                            </div>
                            <div id="datosCuenta" class="modal-cuenta col-5 offset-1">
                                <span class="h5">Datos de la cuenta</span>
                                <input type="text" name="cuenta" placeholder="Introduzca el nombre el cliente" id="numCuenta" class="input-segundo-titular">
                                <input type="email" name="saldo" placeholder="Introduzca el saldo" id="saldo" class="input-segundo-titular">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <input type="button" value="Registrar" name="acc" id="registro"  class="btn btn-danger col-12">
                </div>
            </form>
        </div>
        <div id="respuesta"></div>
    </body>
</html>
