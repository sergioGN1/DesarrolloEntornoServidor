<?php




$mensajeRecibido = $operacionSacarDinero->sacarDinero($clienteSacarDineroObj, $movimientoSacarDineroObj);
$mensajeRecibidoDecoded = json_decode($mensajeRecibido);
echo $mensajeRecibidoDecoded->contenido;
?>
<a href="../index.php">Atrás</a>