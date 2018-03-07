function ingresoDatos() {

    var movimientos = {
        "mo_ncu": $("#ncuenta").val(),
        "mo_des": $("#des").val(),
        "mo_imp": $("#imp").val(),
        "mo_hor": $("#hor").val(),
        "mo_fec": new Date(),

    };

    var cliente = {
        "cl_dni": $("#dni").val(),
    };
    var datos = {
        "movimientos": JSON.stringify(movimientos),
        "cliente": JSON.stringify(cliente),
        "a": "insertar"
    };

    $.ajax({
        data: datos,
        url: "ingresoreintegro",
        type: "post",
        success: function (response) {
            console.log(response);
        }
    });
}
function sacarDatos() {

    var movimientos = {
        "mo_ncu": $("#ncuenta").val(),
        "mo_des": $("#des").val(),
        "mo_imp": $("#imp").val(),
        "mo_hor": $("#hor").val(),
        "mo_fec": new Date(),

    };

    var cliente = {
        "cl_dni": $("#dni").val(),
    };
    var datos = {
        "movimientos": JSON.stringify(movimientos),
        "cliente": JSON.stringify(cliente),
        "a": "quitar"
    };

    $.ajax({
        data: datos,
        url: "ingresoreintegro",
        type: "post",
        success: function (response) {
            console.log(response);
        }
    });
}
$(document).ready(function () {
    $("#insertar").click(ingresoDatos);
    $("#sacar").click(sacarDatos);
});





