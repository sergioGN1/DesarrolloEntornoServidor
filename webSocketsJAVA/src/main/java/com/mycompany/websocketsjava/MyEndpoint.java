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
package com.mycompany.websocketsjava;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import model.Canal;
import model.CanalSuscrito;
import model.Mensaje;
import model.MensajeBaseDatos;
import model.MensajeFechas;
import model.Suscripcion;
import servicios.CanalesServicios;
import servicios.UsuariosServicios;

/**
 * @author Arun Gupta
 */
@ServerEndpoint(value = "/websocket/{user}/{pass}", configurator = ServletAwareConfig.class)
public class MyEndpoint {

    @OnOpen
    public void onOpen(Session session, EndpointConfig config,
            @PathParam("user") String user,
            @PathParam("pass") String pass) {
        UsuariosServicios userServices = new UsuariosServicios();
        HttpSession httpSession = (HttpSession) config.getUserProperties().get("httpsession");
        if (userServices.comprobarLogin(user, pass)) {
            session.getUserProperties().put("user", user);
            session.getUserProperties().put("pass", pass);
            session.getUserProperties().put("login", "OK");
            httpSession.setAttribute("ws", "loginHechoWS");
        } else if (userServices.existirUser(user) && pass.equals("google")) {

            session.getUserProperties().put("login", "FALSE");
        } else {
            if (!userServices.existirUser(user)) {
                userServices.addUsers(user, pass);
            } else {
                try {
                    session.close();
                } catch (IOException ex) {
                    Logger.getLogger(MyEndpoint.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        ArrayList<String> users = new ArrayList<>();
        for (Session sessioGuardar : session.getOpenSessions()) {
            users.add((String) sessioGuardar.getUserProperties().get("user"));
        }
        httpSession.setAttribute("usuarios", users);
    }

    @OnClose
    public void onClose(EndpointConfig config, Session session) {
        HttpSession httpSession = (HttpSession) config.getUserProperties().get("httpsession");
        ArrayList<String> users = new ArrayList<>();
        users = (ArrayList<String>) httpSession.getAttribute("usuarios");
        for (Session s : session.getOpenSessions()) {
            try {
                System.out.println(s.getUserProperties().get("user"));
                s.getBasicRemote().sendText("SALIDO " + session.getUserProperties().get("user").toString());
                users.remove(s.getUserProperties().get("user"));
            } catch (IOException ex) {
                Logger.getLogger(MyEndpoint.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        httpSession.setAttribute("usuarios", users);
    }

    @OnMessage
    public void echoText(String mensaje, Session sessionQueManda) {
        try {
            UsuariosServicios userServices = new UsuariosServicios();
            CanalesServicios canalServices = new CanalesServicios();
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Mensaje objetoMensaje = mapper.readValue(mensaje, new TypeReference<Mensaje>() {
            });
            if (!objetoMensaje.getContenido().equals("")){
            if (sessionQueManda.getUserProperties().get("login").equals("FALSE")) {

                if (!sessionQueManda.getUserProperties().get("login").equals("OK")) {

                    try {
                        // comprobar login
                        String contenido = (String) objetoMensaje.getContenido();
                        String[] contenidoDivido = contenido.split(";");
                        String idToken = contenidoDivido[1];
                        GoogleIdToken.Payload payLoad = IdTokenVerifierAndParser.getPayload(idToken);
                        String name = (String) payLoad.get("name");
                        sessionQueManda.getUserProperties().put("user", name);
                        System.out.println(payLoad.getJwtId());
                        sessionQueManda.getUserProperties().put("login", "OK");
                    } catch (Exception ex) {
                        try {
                            sessionQueManda.close();
                        } catch (IOException ex1) {
                            Logger.getLogger(MyEndpoint.class.getName()).log(Level.SEVERE, null, ex1);
                        }
                        Logger.getLogger(MyEndpoint.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            } else {
                switch (objetoMensaje.getTipo()) {
                    case "texto":
                        for (Session sesionesMandar : sessionQueManda.getOpenSessions()) {
                            try {
                                if (objetoMensaje.isGuardar()) {
                                    userServices.guardarMensaje(objetoMensaje);
                                }
                                if (!objetoMensaje.getDestino().equals("0")) {
                                    List<String> usuariosSuscritos = userServices.usuariosSuscritosAlDestino(objetoMensaje);
                                    for (int i = 0; i < usuariosSuscritos.size(); i++) {

                                        if (!sesionesMandar.getUserProperties().get("user").equals(usuariosSuscritos.get(i))) {
                                            sesionesMandar.getBasicRemote().sendText(mapper.writeValueAsString(objetoMensaje));
                                        }
                                    }
                                } else {
                                    if (!sesionesMandar.equals(sessionQueManda)) {
                                        sesionesMandar.getBasicRemote().sendText(mapper.writeValueAsString(objetoMensaje));
                                    }
                                }
                            } catch (IOException ex) {
                                Logger.getLogger(MyEndpoint.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;
                    case "canales":
                        List<Canal> canales = canalServices.getCanales();
                        objetoMensaje.setContenido(mapper.writeValueAsString(canales));
                        sessionQueManda.getBasicRemote().sendText(mapper.writeValueAsString(objetoMensaje));
                        break;
                    case "addCanales":
                        Canal canal = new Canal();
                        canal.setNombre(objetoMensaje.getContenido());
                        canal.setNombre_usuario(objetoMensaje.getUsuario());
                        String[] arrayContenido = objetoMensaje.getContenido().split(";");
                        String pass = arrayContenido[1];
                        String contenido = arrayContenido[0];
                        canal.setNombre(contenido);
                        canal.setClave(pass);
                        canal = canalServices.crearCanal(canal);
                        if (canal != null) {
                            objetoMensaje.setContenido(mapper.writeValueAsString(canal));
                            for (Session sesionesMandar : sessionQueManda.getOpenSessions()) {
                                if (!sesionesMandar.equals(sessionQueManda)) {
                                    sesionesMandar.getBasicRemote().sendText(mapper.writeValueAsString(objetoMensaje));
                                }
                            }
                        }
                        break;
                    case "suscripcionCanal":
                        String nombreAdmin = canalServices.getCanal(objetoMensaje.getContenido());
                        boolean encontrado = false;
                        for (Session sesionesMandar : sessionQueManda.getOpenSessions()) {
                            try {
                                if (sesionesMandar.getUserProperties().get("user").equals(nombreAdmin)) {
                                    sesionesMandar.getBasicRemote().sendText(mapper.writeValueAsString(objetoMensaje));
                                    encontrado = true;
                                }
                            } catch (IOException ex) {
                                Logger.getLogger(MyEndpoint.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        if (!encontrado) {
                            objetoMensaje.setContenido("no");
                            sessionQueManda.getBasicRemote().sendText(mapper.writeValueAsString(objetoMensaje));
                        }
                        break;
                    case "suscripcionAceptada":
                        Suscripcion suscripcion = new Suscripcion();
                        suscripcion.setCanal(objetoMensaje.getContenido());
                        suscripcion.setUser(objetoMensaje.getUsuario());
                        if (canalServices.suscribirse(suscripcion)) {
                            for (Session sesionesMandar : sessionQueManda.getOpenSessions()) {
                                try {
                                    if (sesionesMandar.getUserProperties().get("user").equals(suscripcion.getUser())) {
                                        sesionesMandar.getBasicRemote().sendText(mapper.writeValueAsString(objetoMensaje));
                                    }
                                } catch (IOException ex) {
                                    Logger.getLogger(MyEndpoint.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }

                        }
                        break;
                    case "suscripcionRechazada":
                        for (Session sesionesMandar : sessionQueManda.getOpenSessions()) {
                            try {
                                if (sesionesMandar.getUserProperties().get("user").equals(objetoMensaje.getUsuario())) {
                                    sessionQueManda.getBasicRemote().sendText(mapper.writeValueAsString(objetoMensaje));
                                }
                            } catch (IOException ex) {
                                Logger.getLogger(MyEndpoint.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;
                    case "mensajes":

                        //mapperFechas.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        MensajeFechas mensajeFechas = mapper.readValue(objetoMensaje.getContenido(), new TypeReference<MensajeFechas>() {
                        });

                        mensajeFechas.setNombreUser(objetoMensaje.getUsuario());
                        ArrayList<MensajeBaseDatos> listaMensajes = userServices.getMensajes(mensajeFechas);
                        if (listaMensajes.size() == 0) {
                            objetoMensaje.setContenido("No tienes mensajes entre las fechas seleccionadas");
                        } else {
                            objetoMensaje.setContenido(mapper.writeValueAsString(listaMensajes));
                        }
                        sessionQueManda.getBasicRemote().sendText(mapper.writeValueAsString(objetoMensaje));
                        break;
                    case "canalesSuscrito":
                        List<CanalSuscrito> canalesSuscrito = canalServices.getCanalSuscrito(objetoMensaje);
                        objetoMensaje.setContenido(mapper.writeValueAsString(canalesSuscrito));
                        sessionQueManda.getBasicRemote().sendText(mapper.writeValueAsString(objetoMensaje));
                        break;
                }
            }
            }
        } catch (IOException ex) {
            Logger.getLogger(MyEndpoint.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @OnMessage
    public void echoBinary(byte[] data, Session session) throws IOException {
        System.out.println("echoBinary: " + data);
        StringBuilder builder = new StringBuilder();
        for (byte b : data) {
            builder.append(b);
        }
        System.out.println(builder);

        for (Session s : session.getOpenSessions()) {
            System.out.println(s.getUserProperties().get("nombre"));
            s.getBasicRemote().sendBinary(ByteBuffer.wrap(data));
        }

    }

//    @WebSocketMessage
//    public void echoBinary(ByteBuffer data, Session session) throws IOException {
//        System.out.println("echoBinary: " + data);
//        StringBuilder builder = new StringBuilder();
//        for (byte b : data.array()) {
//            builder.append(b);
//        }
//        System.out.println(builder);
//        session.getRemote().sendBytes(data);
//    }
}
