$(document).ready(function () {
        $('.edit').click(function () {
            $("#updateA").css("display", "block");
        });
        $(".close").click(function () {
            $("#updateA").css("display", "none");
        });
        $("#enviar").click(function () {
            $("#updateAlumno").submit();
        });
});


