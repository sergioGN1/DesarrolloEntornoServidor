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
    var aux = numCuenta;
    var total = 0;
    while(numCuenta != 0){
        total = total + (numCuenta%10);
        numCuenta = numCuenta / 10;
    }
    if(total%9 == aux){
        return true;
    }else{
        return false;
    }
}