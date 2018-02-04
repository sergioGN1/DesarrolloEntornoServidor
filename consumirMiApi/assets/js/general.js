$(document).ready(function(){
    $(".delete").click(function(){

        var idForm = $(this).attr("data-delete");
        $("#formulario"+idForm).submit();
    });$(".edit").click(function(){
        var idForm = $(this).attr("data-edit");
        console.log(idForm);
        $("#idUpdate").attr("value", idForm);

        $(".formulario").submit();

    });
});


