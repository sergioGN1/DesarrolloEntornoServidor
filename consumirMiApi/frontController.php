<?php

require 'vendor/autoload.php';

use controller\Alumnos;
use controller\Asignaturas;
use controller\Notas;

$alumnoOb = new Alumnos();
$asignaturasOb = new Asignaturas();
$notasOb = new Notas();

$op = $_REQUEST["op"];
$tipo = $_REQUEST["tipo"];

if ($tipo == "alumno") {


    if ($op == "insertar") {
        if (!isset($_REQUEST["id"])) {
            $_REQUEST["id"] = 0;
        }
        if (!isset($_REQUEST["mayor_edad"])) {
            $alumnoRecogido = $alumnoOb->recogerParametros($_REQUEST["id"], $_REQUEST["nombre"], $_REQUEST["fecha_nacimiento"], false);
        } else if ($_REQUEST["mayor_edad"] == "on") {
            $alumnoRecogido = $alumnoOb->recogerParametros($_REQUEST["id"], $_REQUEST["nombre"], $_REQUEST["fecha_nacimiento"], true);
        }
        $alumnoOb->insertarAlumnos($alumnoRecogido);

        
    
    } else if ($op == "delete") {

        $alumnoRecogido = $alumnoOb->recogerParametros($_REQUEST["id"], "", "", "");
        $alumnoBorrado = $alumnoOb->borrarAlumnos($alumnoRecogido);

        var_dump($alumnoBorrado);
  
    } else if ($op == "actualizar") {
        
        if (!isset($_REQUEST["mayor_edad"])) {
            $alumnoRecogido = $alumnoOb->recogerParametros($_REQUEST["id"], $_REQUEST["nombre"], $_REQUEST["fecha_nacimiento"], false);
        } else if ($_REQUEST["mayor_edad"] == "on") {
            $alumnoRecogido = $alumnoOb->recogerParametros($_REQUEST["id"], $_REQUEST["nombre"], $_REQUEST["fecha_nacimiento"], true);
        }
        

        $alumnoOb->updateAlumno($alumnoRecogido);
    }


} else if ($tipo == "asignatura") {
    if (!isset($_REQUEST["id"])) {
        $_REQUEST["id"] = 0;
    }
    

    if ($op == "insertar") {
        $asignaturaRecogido = $asignaturasOb->recogerParametros($_REQUEST["id"], $_REQUEST["nombre"], $_REQUEST["curso"], $_REQUEST["ciclo"]);
        $asignaturasOb->insertarAsignaturas($asignaturaRecogido);

    } else if ($op == "delete") {
        $asignaturaRecogido = $asignaturasOb->recogerParametros($_REQUEST["id"], "", "","");
        
        $asignaturasOb->borrarAsignaturas($asignaturaRecogido);
    } else if ($op == "actualizar") {

        $asignaturaRecogido = $asignaturasOb->recogerParametros($_REQUEST["id"], $_REQUEST["nombre"], $_REQUEST["curso"],$_REQUEST["ciclo"]);

        $asignaturasOb->updateAsignaturas($asignaturaRecogido);
    }

} else if ($tipo == "notas") {
    

    if ($op == "insertar") {
        $notaRecogida = $notasOb->recogerParametros($_REQUEST["id_alumno"], $_REQUEST["id_asignatura"], $_REQUEST["valorNota"]);
        $notasOb->insertarNotas($notaRecogida);
    } else if ($op == "borrar") {
        $notaRecogida = $notasOb->recogerParametros($_REQUEST["id_alumno"], $_REQUEST["id_asignatura"],"");
        $notasOb->borrarNotas($notaRecogida);
    } else if ($op == "actualizar") {
        $notaRecogida = $notasOb->recogerParametros($_REQUEST["id_alumno"], $_REQUEST["id_asignatura"], $_REQUEST["valorNota"]);
        $notasOb->updateNotas($notaRecogida);
    } else if($op == "leer"){
        $notaRecogida = $notasOb->recogerParametros($_REQUEST["id_alumno"], $_REQUEST["id_asignatura"],"");
        $notaTraida = $notasOb->mostrarNotas($notaRecogida);
        echo $notaTraida->nota;
    }
    
}
?>
