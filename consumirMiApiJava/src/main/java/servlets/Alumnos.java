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
import com.google.gson.reflect.TypeToken;
import config.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Alumno;
import servicios.AlumnosServicios;
import utils.Constantes;

/**
 *
 * @author Sergio
 */
@WebServlet(name = "Alumnos", urlPatterns = {"/alumnos"})
public class Alumnos extends HttpServlet {

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
            AlumnosServicios alumnosServicios = new AlumnosServicios();

            HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
            final JsonFactory JSON_FACTORY = new JacksonFactory();
            HttpRequestFactory requestFactory
                    = HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
                        @Override
                        public void initialize(HttpRequest request) {
                            request.setParser(new JsonObjectParser(JSON_FACTORY));

                        }
                    });

            GenericUrl url = new GenericUrl("http://localhost:8080/crearApi/rest/alumnos");

            String op = request.getParameter("op");
            switch (op) {
                case Constantes.OPERACION_LEER:
                    HttpRequest requestGoogle = requestFactory.buildGetRequest(url);
                    requestGoogle.getHeaders().set(Constantes.ATRIBUTO_APIKEY, "2deee83e549c4a6e9709871d0fd58a0a");
                    Type type = new TypeToken<List<GenericJson>>() {
                    }.getType();

                    List<GenericJson> alumnos = (List) requestGoogle.execute().parseAs(type);

                    root.put("alumnos", alumnos);
                    break;
                case Constantes.OPERACION_INSERTAR:

                    GenericJson alumno = new GenericJson();
                    alumno.set(Constantes.NOMBRE, request.getParameter(Constantes.NOMBRE));
                    alumno.set(Constantes.FECHA_DE_NACIMIENTO, alumnosServicios.parseoFecha(request.getParameter(Constantes.FECHA_DE_NACIMIENTO)));
                    if ("on".equals(request.getParameter(Constantes.MAYOR_EDAD))) {
                        alumno.set(Constantes.MAYOR_EDAD, true);
                    } else {
                        alumno.set(Constantes.MAYOR_EDAD, false);
                    }
                    ObjectMapper mapper = new ObjectMapper();

                    url.set(Constantes.ATRIBUTO_ALUMNO, mapper.writeValueAsString(alumno));

                    HttpRequest requestGoogleInsert = requestFactory.buildPutRequest(url, new EmptyContent());
                    requestGoogleInsert.getHeaders().set(Constantes.ATRIBUTO_APIKEY, "2deee83e549c4a6e9709871d0fd58a0a");
                    String mensajeInsert = requestGoogleInsert.execute().parseAs(String.class);
                    root.put(Constantes.MENSAJE, mensajeInsert);
                    break;
                case Constantes.OPERACION_ACTUALIZAR:
                    GenericJson alumnoUpdate = new GenericJson();
                    alumnoUpdate.set(Constantes.ID, request.getParameter(Constantes.ID));
                    alumnoUpdate.set(Constantes.NOMBRE, request.getParameter(Constantes.NOMBRE));
                    alumnoUpdate.set(Constantes.FECHA_DE_NACIMIENTO, alumnosServicios.parseoFecha(request.getParameter(Constantes.FECHA_DE_NACIMIENTO)));
                    if ("on".equals(request.getParameter(Constantes.MAYOR_EDAD))) {
                        alumnoUpdate.set(Constantes.MAYOR_EDAD, true);
                    } else {
                        alumnoUpdate.set(Constantes.MAYOR_EDAD, false);
                    }
                    GenericData data = new GenericData();
                    ObjectMapper mapperUpdate = new ObjectMapper();
                    data.put(Constantes.ATRIBUTO_ALUMNO, mapperUpdate.writeValueAsString(alumnoUpdate));
                    HttpRequest requestGoogleUpdate = requestFactory.buildPostRequest(url, new UrlEncodedContent(data));
                    requestGoogleUpdate.getHeaders().set(Constantes.ATRIBUTO_APIKEY, "2deee83e549c4a6e9709871d0fd58a0a");
                    String mensajeUpdate = requestGoogleUpdate.execute().parseAs(String.class);
                    root.put(Constantes.MENSAJE, mensajeUpdate);
                    break;
                case Constantes.OPERACION_BORRAR:
                    GenericJson alumnoDelete = new GenericJson();
                    alumnoDelete.set(Constantes.ID, request.getParameter(Constantes.ID));
                    root.put("idAlumnoBorrar", alumnoDelete.get(Constantes.ID));
                    ObjectMapper mapperDelete = new ObjectMapper();
                    Alumno alumnoBorrar = new Alumno();
                    url.set(Constantes.ATRIBUTO_ALUMNO, mapperDelete.writeValueAsString(alumnoDelete));
                    try {
                        HttpRequest requestGoogleDelete = requestFactory.buildDeleteRequest(url);
                        requestGoogleDelete.getHeaders().set(Constantes.ATRIBUTO_APIKEY, "2deee83e549c4a6e9709871d0fd58a0a");
                        String mensaje = requestGoogleDelete.execute().parseAs(String.class);
                        root.put(Constantes.MENSAJE, mensaje);
                    } catch (HttpResponseException ex) {
                        root.put("error", ex.getStatusCode());
                    }
                    break;
                case Constantes.OPERACION_BORRADOTOTAL:
                    GenericJson alumnoDeleteTotal = new GenericJson();
                    alumnoDeleteTotal.set(Constantes.ID, request.getParameter(Constantes.ID));
                    ObjectMapper mapperDeleteTotal = new ObjectMapper();
                    url.set(Constantes.ATRIBUTO_ALUMNO, mapperDeleteTotal.writeValueAsString(alumnoDeleteTotal));
                    url.set("deletesiosi", "ok");
                    HttpRequest requestGoogleDeleteTotal = requestFactory.buildDeleteRequest(url);
                    requestGoogleDeleteTotal.getHeaders().set(Constantes.ATRIBUTO_APIKEY, "2deee83e549c4a6e9709871d0fd58a0a");
                    String mensaje = requestGoogleDeleteTotal.execute().parseAs(String.class);
                    root.put(Constantes.MENSAJE, mensaje);
                    break;
            }
            Template temp = Configuration.getInstance().getFreeMarker().getTemplate(Constantes.REDIRECCION_SERVLET_ALUMNOS);
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
