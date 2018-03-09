<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
        <script src="assets/js/jquery-3.2.1.js"></script>
        <script>
            $(document).ready(function () {
                $("#meterDinero").click(function () {
                    $("#formularioMeterDinero").submit();
                });
                $("#sacarDinero").click(function () {
                    $("#formularioSacarDinero").submit();
                });
            });
        </script>
    </head>
    <body>

        <a href="vistas/SacarDineroVista.php">Pagar</a>
        <form action="frontController.php" id="formularioSacarDinero">
            <input type="text" name="dniSacar" placeholder="dni" class="dniSacar">
            <input type="text" name="ncuentaSacar" placeholder="Numero de cuenta" class="ncuentaSacar">
            <input type="text" name="desSacar" placeholder="Descripcion de la operacion" class="desSacar">
            <input type="text" name="impSacar" placeholder="Importe" class="impSacar">
            <input type="text" name="horSacar" placeholder="Hor" class="horSacar">
            <input type="hidden" name="fechaSacar" id="fechaSacar">
            <input type="hidden" name="tipo" value="Sacar">
            <input type="button" name="tipo" id="sacarDinero" value="Operar">
        </form>

    </body>
</html>
