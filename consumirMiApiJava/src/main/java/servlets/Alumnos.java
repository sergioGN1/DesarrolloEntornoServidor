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
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
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
                    Type type = new TypeToken<List<GenericJson>>() {}.getType();
                    
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
                    requestGoogleInsert.execute();
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
                    ObjectMapper mapperUpdate = new ObjectMapper();

                    url.set("alumno", mapperUpdate.writeValueAsString(alumnoUpdate));

                    HttpRequest requestGoogleUpdate = requestFactory.buildPutRequest(url, new EmptyContent());
                    requestGoogleUpdate.execute();
                    break;
                case "delete":
                    GenericJson alumnoDelete = new GenericJson();
                    alumnoDelete.set("id", request.getParameter("id"));

                    ObjectMapper mapperDelete = new ObjectMapper();

                    url.set("alumno", mapperDelete.writeValueAsString(alumnoDelete));

                    HttpRequest requestGoogleDelete = requestFactory.buildPutRequest(url, new EmptyContent());
                    requestGoogleDelete.execute();
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
