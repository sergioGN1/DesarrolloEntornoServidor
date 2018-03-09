<?php

require 'vendor/autoload.php';

use controller\SacarDinero;

$operacionSacarDinero = new SacarDinero();
$tipo = $_REQUEST["tipo"];


$clienteSacarDineroObj = $operacionSacarDinero->formarCliente($_REQUEST["dniSacar"]);
$movimientoSacarDineroObj = $operacionSacarDinero->formarMovimiento($_REQUEST["ncuentaSacar"], $_REQUEST["desSacar"], $_REQUEST["horSacar"], $_REQUEST["impSacar"], $_REQUEST["fechaSacar"]);
include './vistas/SacarDineroVista.php';


