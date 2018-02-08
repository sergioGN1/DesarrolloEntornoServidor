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
        require '../vendor/autoload.php';

        use controller\Asignaturas;
        use controller\Alumnos;


        $asignatura = new Asignaturas();
        $alumno = new Alumnos();

        $asignaturaLista = $asignatura->mostrarAsignaturas();
        $alumnosLista = $alumno->mostrarAlumnos();
        ?>
        <form action="../frontController.php" method="get">
            <input type="hidden" name="tipo" value="notas">
            <select name="id_alumno">
                <?php
                    foreach($alumnosLista as $listarAlumnos){
                        ?>
                            <option value="<?php echo $listarAlumnos->id;?>"><?php echo $listarAlumnos->nombre;?> </option>
                        <?php
                    }
                ?>
            </select>
            <select name="id_asignatura">
                <?php
                    foreach($asignaturaLista as $listarAsignaturas){
                        ?>
                            <option value="<?php echo $listarAsignaturas->id;?>"><?php echo $listarAsignaturas->nombre;?> </option>
                        <?php
                    }
                ?>
            </select>
            <input type="text" name="valorNota" placeholder="Introduzca el valor de la nota">
            <button name="op" value="insertar">Insertar</button>
            <button name="op" value="borrar">Borrar</button>
            <button name="op" value="actualizar">Actualizar</button>
        </form>
    </body>
</html>
