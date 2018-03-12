/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function comprobarDni(dni){
    var expReg = /[0-9]{8}[A-Z]{1}/gi;
    return expReg.test(dni);
}

function comprobarNumeroCuenta(numCuenta){
    var numero = numCuenta;
    var primeraParte = parseInt(numero.substring(0, numero.length-1));
    var segundaParte = parseInt(numero.substring(numero.length-1));
    var aux = primeraParte;
    var total = 0;
    while(parseInt(primeraParte) != 0){
        total = total + (primeraParte%10);
        primeraParte = primeraParte / 10;
    }
    if(total%9 == segundaParte){
        return true;
    }else{
        return false;
    }
}