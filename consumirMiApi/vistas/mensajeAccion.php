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
    </head>
    <body>
        <?php
            echo $mensaje;
            
            if($tipo == "alumno"){
                echo "<a href='vistas/vistaAlumno.php'>Volver</a>";
            } else if ($tipo == "asignatura"){
                echo "<a href='vistas/vistaAsignaturas.php'>Volver</a>";
            } else if ($tipo == "notas"){
                echo "<a href='vistas/vistaNotas.php'>Volver</a>";
            }
        ?>
    </body>
</html>
