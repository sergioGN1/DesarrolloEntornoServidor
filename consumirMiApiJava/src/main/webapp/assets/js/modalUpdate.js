$(document).ready(function () {
        $('.edit').click(function () {
            $("#updateA").css("display", "block");
        });
        $('.editAsig').click(function () {
            $("#updateAsig").css("display", "block");
        });
        $('.editNota').click(function () {
            $("#updateNota").css("display", "block");
        });
        $(".close").click(function () {
            $("#updateA").css("display", "none");
            $("#updateAsig").css("display", "none");
            $("#updateNota").css("display", "none");
        });
        $("#enviar").click(function () {
            $("#updateAlumno").submit();
        });
});


