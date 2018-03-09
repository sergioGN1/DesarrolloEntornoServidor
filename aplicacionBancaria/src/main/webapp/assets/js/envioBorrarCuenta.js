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
        "a": $("#accion").val()
    };

    $.ajax({
        data: datos,
        url: "borrarcuenta",
        type: "post",
        success: function (response) {
            console.log(response);
            var responseBien = JSON.parse(response);
            
            var i = 0;
            if(responseBien.otro == "2"){
                var responseContenido = JSON.parse(responseBien.contenido);
                $("#borrar").val("Borrar");
                $("#accion").val("borrarTotal");
                var tabla = document.createElement("table");
                //while (i < responseContenido.length) {
                    var fila = document.createElement("tr");
                    var columnaNumeroCuenta = document.createElement("td");
                    var columnaDni1 = document.createElement("td");
                    var columnaDni2 = document.createElement("td");
                    var columnaSalario = document.createElement("td");
                    columnaNumeroCuenta.appendChild(document.createTextNode(responseContenido.cu_ncu));
                    columnaDni1.appendChild(document.createTextNode(responseContenido.cu_dn1));
                    columnaDni2.appendChild(document.createTextNode(responseContenido.cu_dn2));
                    columnaSalario.appendChild(document.createTextNode(responseContenido.cu_sal));
                    fila.appendChild(columnaNumeroCuenta);
                    fila.appendChild(columnaDni1);
                    fila.appendChild(columnaDni2);
                    fila.appendChild(columnaSalario);
                    tabla.appendChild(fila);
                    i++;
                //}
                document.body.appendChild(tabla);
            }else{
                alert(responseBien.contenido);
            }
        },
        error: function(request, status, error){
            console.log(error);
        }
    });
}
$(document).ready(function () {
    $("#borrar").click(borrarCuenta);
});

