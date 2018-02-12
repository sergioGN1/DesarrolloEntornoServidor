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
        <h1>Este <?php echo $_SESSION["tipo"];?> tienes notas asociadas, ¿Desea eliminarlas?</h1>
        <form action="../frontController.php">
            <input type="hidden" name="id" value="<?php echo $_SESSION["id"];?>">
            <input type="hidden" name="tipo" value="<?php echo $_SESSION["tipo"];?>">
            
            <button name="op" value="deleteTotal">Enviar</button>
        </form>
    </body>
</html>
