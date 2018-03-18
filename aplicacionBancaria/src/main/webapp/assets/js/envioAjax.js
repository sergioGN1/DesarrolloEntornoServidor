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
        "cl_nom": $("#nom2").val(),
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
                $(".div-datos-cuenta-cliente").css("display", "block");
            }
            if (contenido.otro == "1") {
                if (confirm("Quiere abrir otra cuenta")) {
                    $("#datos").css("display", "block");
                    $("#datosCuenta").css("display", "block");
                    $("#registro").val("actualizar");
                    $(".div-datos-cuenta-cliente").css("display", "block");
                }
            } else {
                if (contenido.contenido != "desplegar") {
                    alert(contenido.contenido);
                }
            }
            if (contenido.otro == "5") {
                $(".div-datos-cuenta-cliente").css("display", "block");
            }
        }
    });
}
$(document).ready(function () {
    $("#registro").click(function () {
        if ($("#registro").val() == "Registrar") {
            if ($("#dni2").val() != "") {
                if (!comprobarDni($("#dni2").val())) {
                    alert("El DNI del segundo titular está mal formado");
                    $("#segundoTitulardiv").css("display", "block");
                    $("#dni2").focus();
                }
                if ($("#dni2").val() != $("#dni1").val()) {
                    alert("El DNI del segundo titular no puede ser igual al de primer titular");
                } else {
                    if (comprobarDni($("#dni1").val())) {
                        envioDatos();
                    } else {
                        alert("El DNI del primero titular esta mal formado");
                    }
                }
            } else {
                if (comprobarDni($("#dni1").val())) {
                    envioDatos();
                } else {
                    alert("El DNI del primero titular esta mal formado");
                }
            }
        } else if ($("#registro").val() == "actualizar") {
            if ($("#dni2").val() != "") {
                if (!comprobarDni($("#dni2").val())) {
                    alert("El DNI del segundo titular está mal formado");
                    $("#segundoTitulardiv").css("display", "block");
                    $("#dni2").focus();
                }
                if ($("#dni2").val() != $("#dni1").val()) {
                    alert("El DNI del segundo titular no puede ser igual al de primer titular");
                } else {
                    if (comprobarDni($("#dni1").val())) {
                        if (comprobarNumeroCuenta($("#numCuenta").val())) {
                            envioDatos();
                        } else {
                            alert("El numero de cuenta esta mal formado");
                            $(".div-datos-cuenta-cliente").css("display", "block");
                            $("#numCuenta").focus();
                        }
                    } else {
                        alert("El DNI del primero titular esta mal formado");
                    }
                }
            } else {
                if (comprobarDni($("#dni1").val())) {
                    if (comprobarNumeroCuenta($("#numCuenta").val())) {
                        if (comprobarNumeroCuenta($("#numCuenta").val())) {
                            envioDatos();
                        } else {
                            alert("El numero de cuenta esta mal formado");
                        }
                    } else {
                        alert("El numero de cuenta esta mal formado");
                        $(".div-datos-cuenta-cliente").css("display", "block");
                        $("#numCuenta").focus();
                    }
                } else {
                    alert("El DNI del primero titular esta mal formado");
                }
            }
        } else if ($("#registro").val() == "insertar") {
            if ($("#dni2").val() != "") {
                if (!comprobarDni($("#dni2").val())) {
                    alert("El DNI del segundo titular está mal formado");
                    $("#segundoTitulardiv").css("display", "block");
                    $("#dni2").focus();
                }
                if ($("#dni2").val() != $("#dni1").val()) {
                    alert("El DNI del segundo titular no puede ser igual al de primer titular");
                } else {
                    if (comprobarDni($("#dni1").val())) {
                        if (comprobarNumeroCuenta($("#numCuenta").val())) {
                            envioDatos();
                        } else {
                            alert("El numero de cuenta esta mal formado");
                        }
                    } else {
                        alert("El DNI del primero titular esta mal formado");
                    }
                }
            } else {
                if (comprobarDni($("#dni1").val())) {
                    if (comprobarNumeroCuenta($("#numCuenta").val())) {
                        envioDatos();
                    } else {
                        alert("El numero de cuenta esta mal formado");
                    }
                } else {
                    alert("El DNI del primero titular esta mal formado");
                }
            }
        }
    });
    $(".close").click(function () {
        $(".div-datos-cuenta-cliente").css("display", "none");
    });
});





