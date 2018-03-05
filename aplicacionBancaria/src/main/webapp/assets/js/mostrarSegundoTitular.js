/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
    $("#segundoTitular").change(function(){
        if($("#segundoTitular").is(":checked")){
            $("#segundoTitulardiv").css("display","block");
        }else{
            $("#segundoTitulardiv").css("display","none");
        }
    });
});
