<?php

$listaMovimientos = $listadoDeMovimientos->listarMovimientos($movimientosObj);
?>

<html>
    <head>
        <meta charset="UTF-8">
        <title>CRUD Alumnos</title>

        <!--BoostStrap-->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>


        <!--JavaScript-->
        <script src="../assets/js/modalAdd.js"></script>
        <script src="../assets/js/modalUpdate.js"></script>
        <script src="../assets/js/general.js"></script>


        <!--CSS-->
        <link rel="stylesheet" href="../assets/css/modales.css">
        <link rel="stylesheet" href="../assets/css/checkboxes.css">
        <link rel="stylesheet" href="../assets/css/general.css">
    </head>
    <body>
        <table class="table table-striped">
            <tr class="bg-primary">
                <td>Numero de cuenta</td>
                <td>Descripcion de la operacion</td>
                <td>Hor</td>
                <td>Importe</td>
                <td>Fecha</td>
            </tr>
            <?php foreach ($listaMovimientos as $movimientos) { ?>
                <tr>
                    <td><?php echo $listaMovimientos->mo_ncu;?></td>
                    <td><?php echo $listaMovimientos->mo_des;?></td>
                    <td><?php echo $listaMovimientos->mo_hor;?></td>
                    <td><?php echo $listaMovimientos->mo_imp;?></td>
                    <td><?php echo $listaMovimientos->mo_fec;?></td>
                </tr>
             <?php } ?>
        </table>
    </body>
</html>