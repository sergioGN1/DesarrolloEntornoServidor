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
import model.Nota;
import servicios.AsignaturasServicios;

/**
 *
 * @author Sergio
 */
@WebServlet(name = "Notas", urlPatterns = {"/notas"})
public class Notas extends HttpServlet {

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

            GenericUrl url = new GenericUrl("http://localhost:8080/crearApi/rest/notas");
            String op = request.getParameter("op");
            int codigo = 0;
            String mensaje = "";
            switch (op) {
                case "leer":
                    Nota notaCogida = new Nota();
                    GenericJson notaVer = new GenericJson();
                    notaVer.set("id_alumno",request.getParameter("id_alumno"));
                    notaVer.set("id_asignatura", request.getParameter("id_asignatura"));
                    ObjectMapper mapperVer = new ObjectMapper();
                    url.set("nota",  mapperVer.writeValueAsString(notaVer));
                    HttpRequest requestGoogle = requestFactory.buildGetRequest(url);
                    requestGoogle.getHeaders().set("apikey", "2deee83e549c4a6e9709871d0fd58a0a");
                    try{
                        notaCogida = requestGoogle.execute().parseAs(Nota.class);
                    } catch(HttpResponseException excepcion){
                        root.put("error", excepcion.getStatusCode());
                    }
                    root.put("nota", notaCogida.getNota());
                    break;
                case "insertar":
                    GenericJson nota = new GenericJson();
                    nota.set("nota", request.getParameter("valorNota"));
                    nota.set("id_alumno",request.getParameter("id_alumno"));
                    nota.set("id_asignatura", request.getParameter("id_asignatura"));
                    ObjectMapper mapper = new ObjectMapper();
                    url.set("nota",  mapper.writeValueAsString(nota));
                    
                    HttpRequest requestGoogleInsert = requestFactory.buildPutRequest(url,new EmptyContent());
                    requestGoogleInsert.getHeaders().set("apikey", "2deee83e549c4a6e9709871d0fd58a0a");
                    mensaje = requestGoogleInsert.execute().parseAs(String.class);
                    HttpServletResponse res = (HttpServletResponse) response;
                    codigo = res.getStatus();
                    root.put("codigo", codigo);
                    root.put("mensaje", mensaje);
                    break;
                case "actualizar":
                    GenericJson asignaturaUpdate = new GenericJson();
                    asignaturaUpdate.set("nota", request.getParameter("valorNota"));
                    asignaturaUpdate.set("id_alumno", request.getParameter("id_alumno"));
                    asignaturaUpdate.set("id_asignatura", request.getParameter("id_asignatura"));
                    
                    GenericData data = new GenericData();
                    ObjectMapper mapperUpdate = new ObjectMapper();
                    data.put("nota", mapperUpdate.writeValueAsString(asignaturaUpdate));
                    HttpRequest requestGoogleUpdate = requestFactory.buildPostRequest(url, new UrlEncodedContent(data));
                    requestGoogleUpdate.getHeaders().set("apikey", "2deee83e549c4a6e9709871d0fd58a0a");
                    mensaje = requestGoogleUpdate.execute().parseAs(String.class);
                    HttpServletResponse resAc = (HttpServletResponse) response;
                    codigo = resAc.getStatus();
                    root.put("codigo", codigo);
                    root.put("mensaje", mensaje);
                    break;
                case "delete":
                    GenericJson notaDelete = new GenericJson();
                    notaDelete.set("id_alumno", request.getParameter("id_alumno"));
                    notaDelete.set("id_asignatura", request.getParameter("id_asignatura"));

                    ObjectMapper mapperDelete = new ObjectMapper();

                    url.set("nota", mapperDelete.writeValueAsString(notaDelete));

                    HttpRequest requestGoogleDelete = requestFactory.buildDeleteRequest(url);
                    requestGoogleDelete.getHeaders().set("apikey", "2deee83e549c4a6e9709871d0fd58a0a");
                    mensaje = requestGoogleDelete.execute().parseAs(String.class);
                        HttpServletResponse resDe = (HttpServletResponse) response;
                        codigo = resDe.getStatus();
                        root.put("codigo", codigo);
                        root.put("mensaje", mensaje);
                    break;
            }
            Template temp = Configuration.getInstance().getFreeMarker().getTemplate("notas.ftl");
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
