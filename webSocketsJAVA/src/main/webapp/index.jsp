<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <script src="https://apis.google.com/js/platform.js" async defer></script>
        <meta name="google-signin-scope" content="profile email">
        <meta name="google-signin-client_id"
              content="122912927113-kjk38bdf4h3njamc8nkq4cjpq0tc49av.apps.googleusercontent.com">
        <link href="assets/foundation-6.4.2-essential/css/foundation.min.css" rel="stylesheet">
        <link href="assets/css/general.css" rel="stylesheet">
        <title>Servlet OAuth example</title>
    </head>
    <body>
        <h2>Servlet OAuth example</h2>
        <br>
        <div id="registro" class="grid-x">
            <form action="" class="cell medium-7">
                <h4>Registrate o logeate</h4>
                <input id="user" type="text" class="medium-6" placeholder="Introduzca su nombre de usuario">
                <input id="pass" type="password" class="medium-6" placeholder="Introduza su contraseña">
                <div class="input-group row">
                    <input onclick="conectar();" class="input-group-button button" value="conectar" type="button">
                    <div class="g-signin2" data-onsuccess="onSignIn" class="input-group-button button"></div>
                </div>
            </form>
        </div>

        <div id="todo" style="text-align: center;display:none">
            <form action=""> 
                <div class="grid-x">
                         <div class="input-group">
                            <div class="grid-x large-offset-3">
                                <div class="large-12 ">
                                    <h5 class="small-9">Canales a suscribirse</h5>
                                    <div class="">
                                        <div class="input-group">
                                            <select id="listaCanales" name="canales" class="small-4"></select>
                                            <input type="button" id="getAllCanales" onclick="getCanales()" value="Mostrar" class="button">
                                        </div>
                                        <div class="grid-x">
                                            <div class="large-12">
                                                <input type="button" value="Suscribirse" onclick="suscribirse();" class="button">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="columns">
                                <div class="grid-x">
                                    <div class="large-12">
                                        <h5>Añade aqui tus canales</h5>
                                        <input type="text" name="canal" id="canalNuevo" placeholder="Introduzca el nombre del nuevo canal">
                                        <input type="text" name="passCanal" id="passCanal"  placeholder="Introduzca la contraseña del nuevo canal">
                                        <input type="button" id="enviarCanal" name="enviarCanal" value="Añadir Canal" onclick="addCanales();" class="button">
                                    </div>
                                </div>
                            </div>
                            <div class="grid-x">
                                <div class="large-12 small-offset-12">
                                    <h5>Mensajes guardados</h5>
                                    <input type="date" id="fecha1">
                                    <input type="date" id="fecha2">
                                    <input type="button" onclick="getMensajes()" value="Buscar" class="button">
                                </div>
                            </div>
                        </div>
                </div>
                <div class="grid-x">
                    <div id="output" style="border:1px solid black;height:375px;" class="small-8 small-offset-2"></div>
                </div>
                <div class="grid-x large-10">
                    <div class="grid-x large-12 large-offset-2">
                        <div class="input-group">
                            <select name="canalesSuscritos" id="canalesSuscrito" class="large-4">
                                <option value="0">General</option>
                            </select>
                            <input id="myField" value="WebSocket" placeholder="Escribe tu mensaje" type="text" class="large-3">
                            <input onclick="sayHello();" value="Enviar" type="button" class="button"> 
                        </div>
                    </div>
                    <div class="grid-x large-12 large-offset-4">
                        <input type="checkbox" id="guardarMensaje">Guardar Mensaje
                    </div>
                </div>
        </div>
    </form>

</div>
<script language="javascript" type="text/javascript" src="websocket.js">
</script>
<script>
    var idToken = "";
    var usuarioGoogle = "";
    function onSignIn(googleUser) {
        var correo = "";
        var profile = googleUser.getBasicProfile();

        websocket = new WebSocket(wsUri + "/" + googleUser.getBasicProfile().getEmail() + "/google", []);

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
        //do not post above info to the server because that is not safe.
        //just send the id_token
        idToken = googleUser.getAuthResponse().id_token;
        usuarioGoogle = profile.getEmail();
        //using jquery to post data dynamically

    }

</script>
<a href="#" onclick="signOut();">Sign out</a>
<script>
    function signOut() {
        onClose();
        var auth2 = gapi.auth2.getAuthInstance();
        auth2.signOut().then(function () {
            console.log('User signed out.');
        });
    }
</script>
</body>
</html>