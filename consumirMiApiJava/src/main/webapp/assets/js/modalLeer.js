$(document).ready(function () {
        $('.leerAlumnos').click(function () {
            $("#leerAlumnos").css("display", "block");
        });
        $('.leerAsig').click(function () {
            $("#leerAsignatura").css("display", "block");
        });
        $(".close").click(function () {
            $("#leerAlumnos").css("display", "none");
            $("#leerAsignatura").css("display", "none");
        });
});





