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
import config.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.PrintWriter;
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
            switch (op) {
                case "leer":
                    HttpRequest requestGoogle = requestFactory.buildGetRequest(url);
                    Asignatura asingatura = requestGoogle.execute().parseAs(Asignatura.class);
                    root.put("asignatura", asingatura);
                    break;
                case "insertar":
                    GenericJson asignatura = new GenericJson();
                    asignatura.set("nombre", request.getParameter("nombre"));
                    asignatura.set("curso",request.getParameter("curso"));
                    asignatura.set("ciclo", request.getParameter("ciclo"));
                    ObjectMapper mapper = new ObjectMapper();
                    url.set("asignatura",  mapper.writeValueAsString(asignatura));
                    
                    HttpRequest requestGoogleInsert = requestFactory.buildPutRequest(url,new EmptyContent());
                    requestGoogleInsert.execute();
                    break;
                case "actualizar":
                    Asignatura asignaturaUpdate = asignaturasServices.recogerParametros(request.getParameter("nombre"), request.getParameter("curso"), request.getParameter("ciclo"));
                    asignaturaUpdate.setId(asignaturasServices.parseoId(request.getParameter("id")));
                    GenericData dataUpdate = new GenericData();
                    dataUpdate.put("asignatura", asignaturaUpdate);
                    HttpRequest requestGoogleUpdate = requestFactory.buildPutRequest(url, new UrlEncodedContent(dataUpdate));
                    requestGoogleUpdate.execute();
                    break;
                case "delete":
                    Asignatura asignaturaDelete = null;
                    asignaturaDelete.setId(asignaturasServices.parseoId(request.getParameter("id")));
                    GenericData dataDelete = new GenericData();
                    dataDelete.put("asignatura", asignaturaDelete);
                    HttpRequest requestGoogleDelete = requestFactory.buildPutRequest(url, new UrlEncodedContent(dataDelete));
                    requestGoogleDelete.execute();
                    break;
            }
            Template temp = Configuration.getInstance().getFreeMarker().getTemplate("asignaturas.ftl");
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
