function envioDatos() {

    var primerTitular = {
        "cl_dni": $("#dni1").val(),
        "cl_nom": $("#nom1").val(),
        "cl_dir": $("#direccion").val(),
        "cl_tel": $("#telefono").val(),
        "cl_ema": $("#email1").val(),
        "cl_fna": $("#fechaNacimiento").val(),
        "cl_fcl": $("#fechaIngreso").val()

    };
    var cuenta = {
        "cu_ncu": $("#numCuenta").val(),
        "cu_sal": $("#saldo").val()
    };
    var segundoTitular = {
        "cl_dni": $("#dni2").val(),
        "cl_nom": $("nom2").val(),
        "cl_dir": $("#direccion2").val(),
        "cl_tel": $("#telefono2").val(),
        "cl_ema": $("#email2").val(),
        "cl_fna": $("#fechaNacimiento2").val(),
        "cl_fcl": $("#fechaIngreso2").val()
    };
    var datos = {
        "primerTitular": JSON.stringify(primerTitular),
        "segundoTitular": JSON.stringify(segundoTitular),
        "cuenta": JSON.stringify(cuenta),
        "acc": $("#registro").val()
    };

    $.ajax({
        data: datos,
        url: "aperturaCuenta",
        type: "post",
        success: function (response) {
            var contenido = JSON.parse(response);
            console.log(contenido.contenido);
            if (contenido.contenido == "desplegar") {
                $("#datos").css("display", "block");
                $("#datosCuenta").css("display", "block");
                $("#registro").val("insertar");
            }
            if (contenido.otro == "1") {
                if (confirm("Quiere abrir otra cuenta")) {
                    $("#datos").css("display", "block");
                    $("#datosCuenta").css("display", "block");
                    $("#registro").val("actualizar");
                }
            }
        }
    });
}
$(document).ready(function () {
    $("#registro").click(envioDatos);
});





