$(document).ready(function () {
        $('.edit').click(function () {
            $("#updateA").css("display", "block");
        });
        $('.editAsig').click(function () {
            $("#updateAsig").css("display", "block");
        });
        $(".close").click(function () {
            $("#updateA").css("display", "none");
            $("#updateAsig").css("display", "none");
        });
        $("#enviar").click(function () {
            $("#updateAlumno").submit();
        });
});


