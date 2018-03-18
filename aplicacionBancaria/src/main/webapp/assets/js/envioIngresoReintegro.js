function ingresoDatos() {

    var movimiento = {
        "mo_ncu": $(".ncuenta").val(),
        "mo_des": $(".des").val(),
        "mo_imp": $(".imp").val(),
        "mo_hor": $(".hor").val(),
        "mo_fec": new Date(),

    };

    var cliente = {
        "cl_dni": $(".dni").val(),
    };
    var datos = {
        "movimiento": JSON.stringify(movimiento),
        "cliente": JSON.stringify(cliente),
        "a": "insertar"
    };

    $.ajax({
        data: datos,
        url: "ingresoreintegro",
        type: "post",
        success: function (response) {
            var mensaje = JSON.parse(response);
            alert(mensaje.contenido);
            console.log(mensaje.contenido);
        }
    });
}
function sacarDatos() {

    var movimiento = {
        "mo_ncu": $(".ncuentaSacar").val(),
        "mo_des": $(".desSacar").val(),
        "mo_imp": $(".impSacar").val(),
        "mo_hor": $(".horSacar").val(),
        "mo_fec": new Date(),

    };

    var cliente = {
        "cl_dni": $(".dniSacar").val(),
    };
    var datos = {
        "movimiento": JSON.stringify(movimiento),
        "cliente": JSON.stringify(cliente),
        "a": "quitar"
    };

    $.ajax({
        data: datos,
        url: "ingresoreintegro",
        type: "post",
        success: function (response) {
            var mensaje = JSON.parse(response);
            alert(mensaje.contenido);
        }
    });
}
$(document).ready(function () {
    $(".insertar").click(function(){
        if(comprobarDni($(".dni").val())){
            if(comprobarNumeroCuenta($(".ncuenta").val())){
                ingresoDatos();
            }else{
                alert("Numero de cuenta está mal fomado");
            }
        }else{
            alert("DNI mal formado");
        }
    });
    $(".sacar").click(function(){
        if(comprobarDni($(".dniSacar").val())){
            if(comprobarNumeroCuenta($(".ncuentaSacar").val())){
                sacarDatos();
            }else{
                alert("Numero de cuenta está mal fomado");
            }
        }else{
            alert("DNI mal formado");
        }
    });
});





