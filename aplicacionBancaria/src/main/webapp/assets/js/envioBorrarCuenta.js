/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function borrarCuenta() {
    var cuenta = {
        "cu_ncu": $("#numeroCuenta").val(),
    };

    var datos = {
        "cuenta": JSON.stringify(cuenta),
        "acc": $("#borrar").val()
    };

    $.ajax({
        data: datos,
        url: "borrarcuenta",
        type: "post",
        success: function (response) {
            console.log(response);
        }
    });
}
$(document).ready(function () {
    $("#borrar").click(borrarCuenta);
});

