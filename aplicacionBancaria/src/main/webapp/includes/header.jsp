<%-- 
    Document   : header
    Created on : 11-mar-2018, 16:06:00
    Author     : Sergio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <body>
        <header class="header">
            <div class="row">
                <a href="eleccion.jsp" class="offset-mio-1">Aplicacion Bancaria</a>
                <div class="dropdown offset-8 dropdown-eleccion">
                    <button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown">
                        Operaciones
                    </button>
                    <div class="dropdown-menu">
                        <a href="aperturaDeCuenta.jsp" class="dropdown-item">Apertura de Cuenta</a>
                        <a href="IngresoReintegros.jsp" class="dropdown-item">Ingresos y reintegros</a>
                        <a href="borrarCuenta.jsp" class="dropdown-item">Borrar Cuenta</a>
                        <a href="listarMovimientos.jsp" class="dropdown-item">Listado de movimientos</a>
                        <a href="logout" class="dropdown-item" id="cerrarSesion">Cerrar Sesion</a>
                    </div>
                </div> 
            </div>
        </header>
    </body>
</html>
