$(document).ready(function () {
        $('.deleteAlumno').click(function () {
            $("#modalDeleteAlumno").css("display", "block");
        });
        $('.deleteAsig').click(function () {
            $("#modalDeleteAsignatura").css("display", "block");
        });
        $(".close").click(function () {
            $("#modalDeleteAlumno").css("display", "none");
            $("#modalDeleteAsignatura").css("display", "none");
        });
});





