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

        <title>Servlet OAuth example</title>
    </head>
    <body>
        <h2>Servlet OAuth example</h2>
        <br>
        <div class="g-signin2" data-onsuccess="onSignIn"></div>

        <div style="text-align: center;">
            <form action=""> 
                <h2>Text Data</h2>
                <input id="user" type="text"><br>
                <input id="pass" type="password">
                <input onclick="conectar();" value="conectar" type="button"> 
                <h2>Text Data</h2>
                <input type="button" id="getAllCanales" onclick="getCanales()" value="Mostrar">
                <select id="listaCanales" name="canales"></select>
                <input type="button" value="Suscribirse" onclick="suscribirse();">
                <input onclick="sayHello();" value="Say Hello" type="button"> 
                <input id="myField" value="WebSocket" type="text"><br>
                <input type="checkbox" id="guardarMensaje">Guardar Mensaje<br>
                <input type="hidden" id="destino" value="1">
                <input type="text" name="canal" id="canalNuevo" placeholder="Introduzca el nombre del nuevo canal">
                <input type="text" name="passCanal" id="passCanal"  placeholder="Introduzca la contraseña del nuevo canal">
                <input type="button" id="enviarCanal" name="enviarCanal" value="Añadir Canal" onclick="addCanales();">
                <input type="date" id="fecha1">
                <input type="date" id="fecha2">
                <input type="button" onclick="getMensajes()">
            </form>
            
        </div>
        <div id="output"></div>
        <script language="javascript" type="text/javascript" src="websocket.js">
        </script>
        <script>
            var idToken="";
            var usuarioGoogle="";
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