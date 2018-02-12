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
$_SESSION["tipo"]=$tipo;
$objetoABorrar = "";
$mensaje = "";
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
        try{
            $mensaje = $alumnoOb->insertarAlumnos($alumnoRecogido);
        } catch (Exception $ex) {
            if($ex->getCode() == 500){
                echo $mensaje;
            }
        }
        include("./vistas/mensajeAccion.php");
        
    
    } else if ($op == "delete") {
        $_SESSION["id"]=$_REQUEST["id"];
        $alumnoRecogido = $alumnoOb->recogerParametros($_REQUEST["id"], "", "", "");
        try{
            $objetoABorrar = $alumnoOb->borrarAlumnos($alumnoRecogido,"");
        }catch(Exception $exception){
            if($exception->getCode() == 500){
                include("vistas/borrarSioSi.php");
            }
        } 
        if($objetoABorrar != 500){
            $mensaje = $objetoABorrar;
            include("./vistas/mensajeAccion.php");
        }
    } else if ($op == "actualizar") {
        
        if (!isset($_REQUEST["mayor_edad"])) {
            $alumnoRecogido = $alumnoOb->recogerParametros($_REQUEST["id"], $_REQUEST["nombre"], $_REQUEST["fecha_nacimiento"], false);
        } else if ($_REQUEST["mayor_edad"] == "on") {
            $alumnoRecogido = $alumnoOb->recogerParametros($_REQUEST["id"], $_REQUEST["nombre"], $_REQUEST["fecha_nacimiento"], true);
        }
        try{
            $mensaje = $alumnoOb->updateAlumno($alumnoRecogido);
        } catch (Exception $ex) {
            if($ex->getCode() == 500){
                echo $mensaje;
            }
        }
        
        include("./vistas/mensajeAccion.php");
    } else if($op == "deleteTotal"){
        $alumnoRecogido = $alumnoOb->recogerParametros($_REQUEST["id"], "", "", "");
        try{
            $mensaje = $alumnoOb->borrarAlumnos($alumnoRecogido,"deleteTotal");
        } catch (Exception $ex) {
            if($ex->getCode() == 500){
                echo $mensaje;
            }
        }
        
        include("./vistas/mensajeAccion.php");
    }


} else if ($tipo == "asignatura") {
    if (!isset($_REQUEST["id"])) {
        $_REQUEST["id"] = 0;
    }
    
    if ($op == "insertar") {
        $asignaturaRecogido = $asignaturasOb->recogerParametros($_REQUEST["id"], $_REQUEST["nombre"], $_REQUEST["curso"], $_REQUEST["ciclo"]);
        try{
            $mensaje = $asignaturasOb->insertarAsignaturas($asignaturaRecogido);
        } catch (Exception $ex) {
            if($ex->getCode() == 500){
                echo $mensaje;
            }
        }
        include("./vistas/mensajeAccion.php");

    } else if ($op == "delete") {
        $_SESSION["id"]=$_REQUEST["id"];
        $asignaturaRecogido = $asignaturasOb->recogerParametros($_REQUEST["id"], "", "","");
        try{
            $objetoABorrar = $asignaturasOb->borrarAsignaturas($asignaturaRecogido,"");
        }catch(Exception $exception){
            if($exception->getCode() == 500){
                include("vistas/borrarSioSi.php");
            }
        }
        if($objetoABorrar != 500){
            $mensaje = $objetoABorrar;
            include("./vistas/mensajeAccion.php");
        }
    } else if ($op == "actualizar") {

        $asignaturaRecogido = $asignaturasOb->recogerParametros($_REQUEST["id"], $_REQUEST["nombre"], $_REQUEST["curso"],$_REQUEST["ciclo"]);
        try{
            $mensaje = $asignaturasOb->updateAsignaturas($asignaturaRecogido);
        } catch (Exception $ex) {
            if($ex->getCode() == 500){
                echo $mensaje;
            }
        }
        
        include("./vistas/mensajeAccion.php");
    } else if($op == "deleteTotal"){
        $asignaturaRecogido = $asignaturasOb->recogerParametros($_REQUEST["id"], "", "", "");
        try{
            $mensaje =  $asignaturasOb->borrarAsignaturas($asignaturaRecogido,"deleteTotal");
        } catch (Exception $ex) {
            if($ex->getCode() == 500){
                echo $mensaje;
            }
        }
        
        include("./vistas/mensajeAccion.php");
    }

} else if ($tipo == "notas") {
    

    if ($op == "insertar") {
        $notaRecogida = $notasOb->recogerParametros($_REQUEST["id_alumno"], $_REQUEST["id_asignatura"], $_REQUEST["valorNota"]);
        try{
            $mensaje = $notasOb->insertarNotas($notaRecogida);
        } catch (Exception $ex) {
            if($ex->getCode() == 500){
                echo $mensaje;
            }
        }
        include("./vistas/mensajeAccion.php");
    } else if ($op == "borrar") {
        $notaRecogida = $notasOb->recogerParametros($_REQUEST["id_alumno"], $_REQUEST["id_asignatura"],"");
        try{
            $mensaje  = $notasOb->borrarNotas($notaRecogida);
        } catch (Exception $ex) {
            if($ex->getCode() == 500){
                echo $mensaje;
            }
        }
        include("./vistas/mensajeAccion.php");
        
    } else if ($op == "actualizar") {
        $notaRecogida = $notasOb->recogerParametros($_REQUEST["id_alumno"], $_REQUEST["id_asignatura"], $_REQUEST["valorNota"]);
        try{
            $mensaje = $notasOb->updateNotas($notaRecogida);
        } catch (Exception $ex) {
            if($ex->getCode() == 500){
                echo $mensaje;
            }
        }
        include("./vistas/mensajeAccion.php");
    } else if($op == "leer"){
        $notaRecogida = $notasOb->recogerParametros($_REQUEST["id_alumno"], $_REQUEST["id_asignatura"],"");
        $notaTraida = $notasOb->mostrarNotas($notaRecogida);
        include("./vistas/mostrarNota.php");
    }
    
}
?>
