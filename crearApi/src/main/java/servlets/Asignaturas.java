/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Asignatura;
import model.Completado;
import servicios.AsignaturasServicios;
import utils.Constantes;

/**
 *
 * @author DAW
 */
@WebServlet(name = "Asignaturas", urlPatterns = {"/rest/asignaturas"})
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
        AsignaturasServicios as = new AsignaturasServicios();
        List<Asignatura> asignaturas = as.getAllAsignaturas();
        request.setAttribute("json", asignaturas);
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
        AsignaturasServicios as = new AsignaturasServicios();
        Asignatura asignatura = (Asignatura) request.getAttribute(Constantes.REQUEST_ATTRIBUTE_ASIGNATURA);
        if (as.updateAsignatura(asignatura)) {
            request.setAttribute("json", Constantes.MENSAJE_ACTUALIZADO_CORRECTO);
        } else {
            request.setAttribute("json", Constantes.MENSAJE_ACTUALIZADO_INCORRECTO);
        }
        
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AsignaturasServicios as = new AsignaturasServicios();
        
        Asignatura asignatura = (Asignatura) req.getAttribute(Constantes.REQUEST_ATTRIBUTE_ASIGNATURA);
        
        if (!("ok").equals(req.getParameter("deletesiosi"))){
            if (as.deleteAsignatura(asignatura)) {
                resp.setStatus(200);
                req.setAttribute("json", Constantes.MENSAJE_BORRADO_CORRECTO);
            } else {
                resp.setStatus(500);
                req.setAttribute("json", Constantes.MENSAJE_BORRADO_INCORRECTO);
            }
        }else{
            try {
                if (as.completeDeleteAlumno(asignatura) == 1) {
                    resp.setStatus(200);
                    req.setAttribute("json", Constantes.MENSAJE_BORRADO_CORRECTO);
                } else {
                    resp.setStatus(500);
                    req.setAttribute("json", Constantes.MENSAJE_BORRADO_INCORRECTO);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Alumnos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AsignaturasServicios as = new AsignaturasServicios();
        Asignatura asignatura = (Asignatura) req.getAttribute(Constantes.REQUEST_ATTRIBUTE_ASIGNATURA);
        if (as.addAsignatura(asignatura)) {
            req.setAttribute("json", Constantes.MENSAJE_INSERTADO_CORRECTO);
        } else {
            req.setAttribute("json", Constantes.MENSAJE_INSERTADO_INCORRECTO);
        }
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
