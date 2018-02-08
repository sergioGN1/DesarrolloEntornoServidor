$(document).ready(function () {
    $('.add').click(function () {
        $("#addA").css("display","block");
    });
    $('.addAsig').click(function () {
        $("#addAsig").css("display","block");
    });
    $('.addNota').click(function () {
        $("#insertarNota").css("display","block");
    });
    $(".close").click(function(){
        $("#addA").css("display","none");
        $("#addAsig").css("display","none");
        $("#insertarNota").css("display","none");
    }); 
});


