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
import model.Completado;
import servicios.AlumnosServicios;

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
                case "leer":
                    HttpRequest requestGoogle = requestFactory.buildGetRequest(url);
                    requestGoogle.getHeaders().set("apikey", "2deee83e549c4a6e9709871d0fd58a0a");
                    Type type = new TypeToken<List<GenericJson>>() {
                    }.getType();

                    List<GenericJson> alumnos = (List) requestGoogle.execute().parseAs(type);

                    root.put("alumnos", alumnos);
                    break;
                case "insertar":

                    GenericJson alumno = new GenericJson();
                    alumno.set("nombre", request.getParameter("nombre"));
                    alumno.set("fecha_nacimiento", alumnosServicios.parseoFecha(request.getParameter("fecha_nacimiento")));
                    if ("on".equals(request.getParameter("mayor_edad"))) {
                        alumno.set("mayor_edad", true);
                    } else {
                        alumno.set("mayor_edad", false);
                    }
                    ObjectMapper mapper = new ObjectMapper();

                    url.set("alumno", mapper.writeValueAsString(alumno));

                    HttpRequest requestGoogleInsert = requestFactory.buildPutRequest(url, new EmptyContent());
                    requestGoogleInsert.getHeaders().set("apikey", "2deee83e549c4a6e9709871d0fd58a0a");
                    requestGoogleInsert.execute();
                    root.put("insertadoOk", request.getAttribute("json"));
                    break;
                case "actualizar":
                    GenericJson alumnoUpdate = new GenericJson();
                    alumnoUpdate.set("id", request.getParameter("id"));
                    alumnoUpdate.set("nombre", request.getParameter("nombre"));
                    alumnoUpdate.set("fecha_nacimiento", alumnosServicios.parseoFecha(request.getParameter("fecha_nacimiento")));
                    if ("on".equals(request.getParameter("mayor_edad"))) {
                        alumnoUpdate.set("mayor_edad", true);
                    } else {
                        alumnoUpdate.set("mayor_edad", false);
                    }
                    GenericData data = new GenericData();
                    ObjectMapper mapperUpdate = new ObjectMapper();
                    data.put("alumno", mapperUpdate.writeValueAsString(alumnoUpdate));
                    HttpRequest requestGoogleUpdate = requestFactory.buildPostRequest(url, new UrlEncodedContent(data));
                    requestGoogleUpdate.getHeaders().set("apikey", "2deee83e549c4a6e9709871d0fd58a0a");
                    requestGoogleUpdate.execute();
                    root.put("actualizadoOk", request.getAttribute("json"));
                    break;
                case "delete":
                    GenericJson alumnoDelete = new GenericJson();
                    alumnoDelete.set("id", request.getParameter("id"));
                    root.put("idAlumnoBorrar", alumnoDelete.get("id"));
                    ObjectMapper mapperDelete = new ObjectMapper();
                    Alumno alumnoBorrar = new Alumno();
                    url.set("alumno", mapperDelete.writeValueAsString(alumnoDelete));
                    try {
                        HttpRequest requestGoogleDelete = requestFactory.buildDeleteRequest(url);
                        requestGoogleDelete.getHeaders().set("apikey", "2deee83e549c4a6e9709871d0fd58a0a");
                        alumnoBorrar = requestGoogleDelete.execute().parseAs(Alumno.class);
                        root.put("alumnoParaBorrar", alumnoBorrar.getId());
                    } catch (HttpResponseException ex) {
                        root.put("error", ex.getStatusCode());
                    }
                    break;
                case "deleteTotal":
                    GenericJson alumnoDeleteTotal = new GenericJson();
                    alumnoDeleteTotal.set("id", request.getParameter("id"));
                    ObjectMapper mapperDeleteTotal = new ObjectMapper();
                    Alumno alumnoBorrarTotal = new Alumno();
                    url.set("alumno", mapperDeleteTotal.writeValueAsString(mapperDeleteTotal));
                    url.set("deletesiosi", "ok");
                    HttpRequest requestGoogleDeleteTotal = requestFactory.buildDeleteRequest(url);
                    requestGoogleDeleteTotal.getHeaders().set("apikey", "2deee83e549c4a6e9709871d0fd58a0a");
                    alumnoBorrarTotal = requestGoogleDeleteTotal.execute().parseAs(Alumno.class);
                    break;
            }
            Template temp = Configuration.getInstance().getFreeMarker().getTemplate("alumnos.ftl");
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
