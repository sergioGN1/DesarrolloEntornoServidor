/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function enviarDatosListadoMovimientos() {
    var datos = {
        "dni": $("#dniCliente").val(),
        "numeroCuenta": $("#numCuenta").val(),
        "fecha1": $("#fecha1").val(),
        "fecha2": $("#fecha2").val()
    };

    var listar = {
        "datos": JSON.stringify(datos)
    };
    $.ajax({
        data: listar,
        url: "listadomovientos",
        type: "post",
        success: function (response) {
            var contenidoResponse = JSON.parse(response);
            if (contenidoResponse.otro == 1) {
                var stringContenido = JSON.parse(contenidoResponse.contenido);
                var listaContenido = JSON.parse(stringContenido);
                var tabla = document.getElementById("listarMovimientos");
                var i = 0;
                //tabla.css("display", "block");
                console.log("Longitud: " + listaContenido.length + "  Contenido: " + listaContenido);
                while (i < listaContenido.length) {
                    var fila = document.createElement("tr");
                    var columnaDescripcion = document.createElement("td");
                    var columnaNumeroCuenta = document.createElement("td");
                    var columnaFecha = document.createElement("td");
                    var columnaHor = document.createElement("td");
                    var columnaImporte = document.createElement("td");
                    columnaDescripcion.appendChild(document.createTextNode(listaContenido[i].mo_des));
                    columnaNumeroCuenta.appendChild(document.createTextNode(listaContenido[i].mo_ncu));
                    columnaFecha.appendChild(document.createTextNode(listaContenido[i].mo_fec));
                    columnaHor.appendChild(document.createTextNode(listaContenido[i].mo_hor));
                    columnaImporte.appendChild(document.createTextNode(listaContenido[i].mo_imp));
                    fila.appendChild(columnaNumeroCuenta);
                    fila.appendChild(columnaDescripcion);
                    fila.appendChild(columnaHor);
                    fila.appendChild(columnaImporte);
                    fila.appendChild(columnaFecha);
                    tabla.appendChild(fila);
                    i++;
                }
                $(".div-modal").css("display","block");
            } else {
                alert(contenidoResponse.contenido);
            }
        }
    });
}

$(document).ready(function () {
    $("#leerMovimientos").click(function(){
        if(comprobarDni($("#dniCliente").val())){
            if(comprobarNumeroCuenta($("#numCuenta").val())){
                enviarDatosListadoMovimientos();
            }else{
                alert("Numero de cuenta está mal fomado");
            }
        }else{
            alert("DNI mal formado");
        }
    });
    $(".close").click(function(){
        $(".div-modal").css("display","none");
    });
}
);
