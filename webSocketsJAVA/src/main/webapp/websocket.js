/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://glassfish.dev.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

var wsUri = "ws://localhost:8080/webSocketsJAVA/websocket";
console.log("Connecting to " + wsUri);
var websocket;
var output = document.getElementById("output");
function conectar() {
    websocket = new WebSocket(wsUri + "/" + user.value + "/" + pass.value, []);
    websocket.onopen = function (evt) {
        onOpen(evt);
    };
    websocket.onmessage = function (evt) {
        onMessage(evt);
    };
    websocket.onerror = function (evt) {
        onError(evt);
    };
    websocket.onclose = function (evt) {
        onClose(evt);
    };
}
function addCanales() {
    var object = {
        "destino": $("#canalesSuscrito").val(),
        "tipo": "addCanales",
        "contenido": canalNuevo.value + ";" + passCanal.value,
        "fecha": new Date(),
        "guardar": false,
        "usuario": user.value
    };
    websocket.send(JSON.stringify(object));
}
function getCanalesSuscrito(){
    var object = {
        "destino": $("#canalesSuscrito").val(),
        "tipo": "canalesSuscrito",
        "contenido": "x",
        "fecha": new Date(),
        "guardar": false,
        "usuario": user.value
    };
    websocket.send(JSON.stringify(object));
}
function suscribirse() {
    var object = {
        "destino": $("#canalesSuscrito").val(),
        "tipo": "suscripcionCanal",
        "contenido": listaCanales.value,
        "fecha": new Date(),
        "guardar": false,
        "usuario": user.value
    };
    websocket.send(JSON.stringify(object));
}
function suscripcionRechazada(objeto) {
    objeto.tipo = "suscripcionRechazada";
    websocket.send(JSON.stringify(objeto));
}
function suscripcionAceptada(objeto) {
    objeto.tipo = "suscripcionAceptada";
    websocket.send(JSON.stringify(objeto));
}
function getCanales() {

    var object = {
        "destino": $("#canalesSuscrito").val(),
        "tipo": "canales",
        "contenido": "x",
        "fecha": new Date(),
        "guardar": false,
        "usuario": user.value
    };
    websocket.send(JSON.stringify(object));
}
function getMensajes() {
    var objetoFechas = {};
    var fecha11 = new Date(fecha1.value);
    var fecha21 = new Date(fecha2.value);
    if (fecha11.getTime() < fecha21.getTime()) {
        fechas = {
            "fecha1": fecha11,
            "fecha2": fecha21
        }
    } else {
        fechas = {
            "fecha1": fecha21,
            "fecha2": fecha11
        }
    }
    var object = {
        "destino": $("#canalesSuscrito").val(),
        "tipo": "mensajes",
        "contenido": JSON.stringify(fechas),
        "fecha": new Date(),
        "guardar": false,
        "usuario": user.value
    };
    websocket.send(JSON.stringify(object));
}
function sayHello() {
    console.log("sayHello: " + myField.value);
    var object = new Object();
    if (idToken == "") {
        object.contenido = myField.value;
    } else {
        object.contenido = myField.value + ";" + idToken;
        idToken = "";
    }
    object.tipo = "texto";
    object.destino = $("#canalesSuscrito").val();
    object.fecha = new Date();
    object.guardar = guardarMensaje.checked;
    if (user.value != "") {
        object.usuario = user.value;
    } else {
        object.usuario = usuarioGoogle;
    }
    websocket.send(JSON.stringify(object));
    writeToScreen(myField.value,"enviado");
}

function echoBinary() {
    var buffer = new ArrayBuffer(myField2.value.length);
    var bytes = new Uint8Array(buffer);
    for (var i = 0; i < bytes.length; i++) {
        bytes[i] = i;
    }

    websocket.send(buffer);
    writeToScreen("SENT (binary): " + buffer.byteLength + " bytes");
}

function onOpen() {
    $("#todo").css("display","block");
    $("#registro").css("display","none");
    getCanalesSuscrito();
    getCanales();
}
function onClose() {
    $("#todo").css("display","none");
    $("#registro").css("display","block");
    writeToScreen("Server close conection");
}

function onMessage(evt) {

    if (typeof evt.data == "string") {
        var mensaje = JSON.parse(evt.data);
        switch (mensaje.tipo) {
            case "texto":
                writeToScreen(mensaje.usuario + ": " + mensaje.contenido,"recibido");
                break;
            case "canales":
                var canales = JSON.parse(mensaje.contenido);
                for (var i = 0; i < canales.length; i++) {
                    $("#listaCanales").append(new Option(canales[i].nombre, canales[i].id));
                }
                break;
            case "addCanales":
                var canal = JSON.parse(mensaje.contenido);
                
                writeToScreen(mensaje.usuario + " ha creado un nuevo canal llamado: <strong>" + canal.nombre + "</strong> <div style='color:red'>!! SUSCRIBETE YA !!<div>","recibido");
                $("#listaCanales").append(new Option(canal.nombre, canal.id));
                break;
            case "suscripcionCanal":
                if(mensaje.contenido != "no"){
                    if (confirm("El usuario" + mensaje.usuario + " quiere suscribirse a tu canal")) {
                        suscripcionAceptada(mensaje);
                    } else {
                        suscripcionRechazada(mensaje);
                    }
                }else{
                    alert("El administrador no esta conectado");
                }
                
                break;
            case "suscripcionAceptada":
                mensaje.contenido = "Tu suscripcion ha sido aceptada";
                alert(mensaje.contenido);
                getCanalesSuscrito();
                break;
            case "suscripcionRechazada":
                mensaje.contenido = "Tu suscripcion ha sido rechazada";
                alert(mensaje.contenido);
                break;
            case "mensajes":
                var mensajesContenido = JSON.parse(mensaje.contenido);
                for (var i = 0; i < mensajesContenido.length; i++) {
                    writeToScreen(mensajesContenido[i].fecha + "-" + mensajesContenido[i].nombre + ": " + mensajesContenido[i].contenido,"recibido");
                }
                break;
            case "canalesSuscrito":
                var canalesSuscrito = JSON.parse(mensaje.contenido);
                for (var i = 0; i < canalesSuscrito.length; i++) {
                    $("#canalesSuscrito").append(new Option(canalesSuscrito[i].nombre, canalesSuscrito[i].id));
                }
                break;
        }

    } else {
        writeToScreen("RECEIVED (binary): " + evt.data);
    }
}

function onError(evt) {
    writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
}

function writeToScreen(message,tipo) {
    var pre = document.createElement("p");
    pre.style.wordWrap = "break-word";
    pre.innerHTML = message;
    if(tipo=="recibido"){
        $("#output").css("text-align","left");
        pre.setAttribute("color","red");
    } else {
        $("#output").css("text-align","right");
        pre.setAttribute("color","green");
    }
    output.appendChild(pre);
}

