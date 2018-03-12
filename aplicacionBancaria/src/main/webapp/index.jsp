<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="includes/header.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="assets/js/jquery-3.2.1.js"></script>
        <link href="assets/bootstrap/css/bootstrap.css" rel="stylesheet">
        <script src="assets/bootstrap/js/bootstrap.js"></script>
        <script src="assets/js/login.js"></script>
        <!--<link href="../assets/css/general.css" rel="stylesheet">-->
        <link href="assets/css/estilos.css" rel="stylesheet">
        <title>Login</title>
    </head>
    <body class="body-login">
        <div class="container">

            <div class="row">
                <div class="contenedor-formulario col-5 input-group offset-3">
                    <form action="login" method="post" class="offset-1 offset-mio-alto col-10">
                        <input type="text" name="nombre" class="offset-mio-1 col-11 input-escribir" placeholder="Introduzca el nombre">
                        <input type="password" name="pass"  class="offset-mio-1 col-11 input-escribir" placeholder="Introduzca la contraseña">
                        <input type="submit" name="Enviar" value="Enviar"  class="offset-mio-1 col-11 btn btn-danger">
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
