<?php

require 'vendor/autoload.php';

use controller\ListarMovimientos;
use controller\SacarDinero;

$listadoDeMovimientos = new ListarMovimientos();
$operacionSacarDinero = new SacarDinero();
$tipo = $_REQUEST["tipo"];

switch($tipo){
    case "Ver":
        $movimientosObj = $listadoDeMovimientos->montarObjeto($_REQUEST["dniCliente"], $_REQUEST["numCuenta"], $_REQUEST["fecha1"],$_REQUEST["fecha2"]);
        include './vistas/ListadoMovimientos.php';
        break;
    case "Sacar":
        $clienteSacarDineroObj = $operacionSacarDinero->formarCliente($_REQUEST["dniSacar"]);
        $movimientoSacarDineroObj = $operacionSacarDinero->formarMovimiento($_REQUEST["ncuentaSacar"], $_REQUEST["desSacar"], $_REQUEST["impSacar"], $_REQUEST["horSacar"], $_REQUEST["fechaSacar"]);
        include './vistas/SacarDineroVista.php';
        break;
}

