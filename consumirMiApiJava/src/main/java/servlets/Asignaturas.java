/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.EmptyContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.UrlEncodedContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.GenericData;
import config.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servicios.AsignaturasServicios;
import model.Asignatura;
import utils.Constantes;

/**
 *
 * @author Sergio
 */
@WebServlet(name = "Asignaturas", urlPatterns = {"/asignaturas"})
public class Asignaturas extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HashMap root = new HashMap();
            AsignaturasServicios asignaturasServices = new AsignaturasServicios();

            HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
            final JsonFactory JSON_FACTORY = new JacksonFactory();
            HttpRequestFactory requestFactory
                    = HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
                        @Override
                        public void initialize(HttpRequest request) {
                            request.setParser(new JsonObjectParser(JSON_FACTORY));

                        }
                    });

            GenericUrl url = new GenericUrl("http://localhost:8080/crearApi/rest/asignaturas");
            String op = request.getParameter("op");
            int codigo = 0;
            String mensaje = "";
            switch (op) {
                case Constantes.OPERACION_LEER:
                    HttpRequest requestGoogle = requestFactory.buildGetRequest(url);
                    requestGoogle.getHeaders().set(Constantes.ATRIBUTO_APIKEY, "2deee83e549c4a6e9709871d0fd58a0a");
                    Asignatura asingatura = requestGoogle.execute().parseAs(Asignatura.class);
                    root.put(Constantes.ATRIBUTO_ASIGNATURA, asingatura);
                    break;
                case Constantes.OPERACION_INSERTAR:
                    GenericJson asignatura = new GenericJson();
                    asignatura.set(Constantes.NOMBRE, request.getParameter(Constantes.NOMBRE));
                    asignatura.set(Constantes.CURSO, request.getParameter(Constantes.CURSO));
                    asignatura.set(Constantes.CICLO, request.getParameter(Constantes.CICLO));
                    ObjectMapper mapper = new ObjectMapper();
                    url.set(Constantes.ATRIBUTO_ASIGNATURA, mapper.writeValueAsString(asignatura));

                    HttpRequest requestGoogleInsert = requestFactory.buildPutRequest(url, new EmptyContent());
                    requestGoogleInsert.getHeaders().set(Constantes.ATRIBUTO_APIKEY, "2deee83e549c4a6e9709871d0fd58a0a");
                    mensaje = requestGoogleInsert.execute().parseAs(String.class);
                    HttpServletResponse res = (HttpServletResponse) response;
                    codigo = res.getStatus();
                    root.put(Constantes.CODIGO, codigo);
                    root.put(Constantes.MENSAJE, mensaje);
                    break;
                case Constantes.OPERACION_ACTUALIZAR:
                    GenericJson asignaturaUpdate = new GenericJson();
                    asignaturaUpdate.set(Constantes.ID, request.getParameter(Constantes.ID));
                    asignaturaUpdate.set(Constantes.NOMBRE, request.getParameter(Constantes.NOMBRE));
                    asignaturaUpdate.set(Constantes.CURSO, request.getParameter(Constantes.CURSO));
                    asignaturaUpdate.set(Constantes.CICLO, request.getParameter(Constantes.CICLO));

                    GenericData data = new GenericData();
                    ObjectMapper mapperUpdate = new ObjectMapper();
                    data.put(Constantes.ATRIBUTO_ASIGNATURA, mapperUpdate.writeValueAsString(asignaturaUpdate));
                    HttpRequest requestGoogleUpdate = requestFactory.buildPostRequest(url, new UrlEncodedContent(data));
                    requestGoogleUpdate.getHeaders().set(Constantes.ATRIBUTO_APIKEY, "2deee83e549c4a6e9709871d0fd58a0a");
                    mensaje = requestGoogleUpdate.execute().parseAs(String.class);
                    HttpServletResponse resAc = (HttpServletResponse) response;
                    codigo = resAc.getStatus();
                    root.put(Constantes.CODIGO, codigo);
                    root.put(Constantes.MENSAJE, mensaje);
                    break;
                case Constantes.OPERACION_BORRAR:
                    GenericJson asignaturaDelete = new GenericJson();
                    asignaturaDelete.set(Constantes.ID, request.getParameter(Constantes.ID));
                    root.put("idAsignaturaDelete", asignaturaDelete.get(Constantes.ID));
                    ObjectMapper mapperDelete = new ObjectMapper();
                    url.set(Constantes.ATRIBUTO_ASIGNATURA, mapperDelete.writeValueAsString(asignaturaDelete));

                    try {
                        HttpRequest requestGoogleDelete = requestFactory.buildDeleteRequest(url);
                        requestGoogleDelete.getHeaders().set(Constantes.ATRIBUTO_APIKEY, "2deee83e549c4a6e9709871d0fd58a0a");
                        mensaje = requestGoogleDelete.execute().parseAs(String.class);
                        HttpServletResponse resDe = (HttpServletResponse) response;
                        codigo = resDe.getStatus();
                        root.put(Constantes.CODIGO, codigo);
                        root.put(Constantes.MENSAJE, mensaje);
                    } catch (HttpResponseException ex) {
                        root.put("error", ex.getStatusCode());
                    }
                    break;
                case Constantes.OPERACION_BORRADOTOTAL:
                    GenericJson asignaturaDeleteTotal = new GenericJson();
                    asignaturaDeleteTotal.set(Constantes.ID, request.getParameter(Constantes.ID));
                    ObjectMapper mapperDeleteTotal = new ObjectMapper();
                    url.set(Constantes.ATRIBUTO_ASIGNATURA, mapperDeleteTotal.writeValueAsString(asignaturaDeleteTotal));
                    url.set("deletesiosi", "ok");
                    HttpRequest requestGoogleDeleteTotal = requestFactory.buildDeleteRequest(url);
                    requestGoogleDeleteTotal.getHeaders().set(Constantes.ATRIBUTO_APIKEY, "2deee83e549c4a6e9709871d0fd58a0a");
                    mensaje = requestGoogleDeleteTotal.execute().parseAs(String.class);
                    HttpServletResponse resDeT = (HttpServletResponse) response;
                    codigo = resDeT.getStatus();
                    root.put(Constantes.CODIGO, codigo);
                    root.put(Constantes.MENSAJE, mensaje);
                    break;
            }
            Template temp = Configuration.getInstance().getFreeMarker().getTemplate(Constantes.REDIRECCION_SERVLET_ASIGNATURAS);
            temp.process(root, response.getWriter());
        } catch (TemplateException ex) {
            Logger.getLogger(Eleccion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
