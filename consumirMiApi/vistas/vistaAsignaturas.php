
<?php
require '../vendor/autoload.php';

use controller\Asignaturas;

$asignatura = new Asignaturas();

$asignaturaLista = $asignatura->mostrarAsignaturas();
?>
<html>
    <head>
        <meta charset="UTF-8">
        <title>CRUD Asignaturas</title>
        
        <!--BoostStrap-->
        <link rel="stylesheet" href="../assets/bootstrap/css/bootstrap.css">
        <script src="../assets/bootstrap/js/bootstrap.js"></script>
        
        <!--jQuery-->
        <script src="../assets/js/jquery-3.2.1.js"></script>
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
                <td>Nombre</td>

                <td>Curso</td>

                <td>Ciclo</td>

                <td>Operaciones</td>
            </tr>
            <?php foreach ($asignaturaLista as $asignaturas) { ?>
                <tr>
                    <td>
                        <?php echo $asignaturas->nombre; ?>
                    </td>
                    <td>
                        <?php echo $asignaturas->curso; ?>
                    </td>
                    <td>
                       <?php echo $asignaturas->ciclo; ?>
                    </td>
                    <td>
                        <div class="row">
                            <div class="edit" data-edit="<?php echo $asignaturas->id;?>">&#9998;</div>
                            <div class="delete" data-delete="<?php echo $asignaturas->id;?>">&#9746;
                                <form action="../frontController.php" id="formulario<?php echo $asignaturas->id;?>">
                                    <input type="hidden" name="op" value="delete">
                                    <input type="hidden" name="tipo" value="asignatura">
                                    <input type="hidden" name="id" value="<?php echo $asignaturas->id;?>">
                                </form>
                            </div>
                        </div>
                    </td>
                </tr>
            <?php } ?>

            <tr>
                <td colspan="4"><button class="col-12 btn btn-secondary" id="add">Añadir Asignatura</button></td>
            </tr>
        </table>    
        <div id="addA" class="modal">

            
            <div class="modal-content">
                <span class="close">&times;</span>
                <form action="../frontController.php" name="addAlumno">
                    <input type="hidden" name="tipo" value="asignatura">
                    <input type="text" name="nombre" placeholder="Nombre de la asignatura" class="col-12">
                    <input type="text" name="curso" placeholder="Nombre del curso" class="col-12">
                    <input type="text" name="ciclo" placeholder="Nombre del ciclo" class="col-12">
                    <button name="op" value="insertar" class="col-12 btn btn-secondary">Enviar</button>
                </form>
            </div>

        </div>
        <div id="updateA" class="modal">

            
            <div class="modal-content">
                <span class="close">&times;</span>
                <form action="../frontController.php" id="updateAlumno">
                    <input type="hidden" name="tipo" value="asignatura">
                    <input type="hidden" name="op" value="update">
                    <input type="hidden" name="id" value="" id="idUpdate">
                    <input type="text" name="nombre" placeholder="Nombre de la asignatura" class="col-12">
                    <input type="text" name="curso" placeholder="Nombre del curso" class="col-12">
                    <input type="text" name="ciclo" placeholder="Nombre del ciclo" class="col-12">
                    <button name="op" value="actualizar" id="enviar" class="col-12 btn btn-secondary">Enviar</button>
                </form>
            </div>

        </div>
        <a href="vistaAlumno.php">Alumnos</a>
        <a href="vistaNotas.php">Notas</a>
    </body>
</html>
