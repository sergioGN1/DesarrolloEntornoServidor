/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
    $("#botonMostrarSacar").click(function(){
        $("#divMeter").css("display","none");
        $("#divSacar").css("display","block");
    });
    $("#botonMostrarMeter").click(function(){
        $("#divMeter").css("display","block");
        $("#divSacar").css("display","none");
    });
});
