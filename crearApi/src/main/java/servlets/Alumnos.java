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
import model.Alumno;
import servicios.AlumnosServicios;
import utils.Constantes;
/**
 *
 * @author DAW
 */
@WebServlet(name = "Alumnos", urlPatterns = {"/rest/alumnos"})
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
        AlumnosServicios as = new AlumnosServicios();
        List<Alumno> alumnos = as.getAllAlumnos();
        request.setAttribute("json", alumnos);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        AlumnosServicios as = new AlumnosServicios();

        Alumno alumnoI = (Alumno) req.getAttribute(Constantes.REQUEST_ATTRIBUTE_ALUMNO);
        if (!("ok").equals(req.getParameter("deletesiosi"))) {
            if (as.deleteAlumno(alumnoI)) {

                req.setAttribute("json", Constantes.MENSAJE_BORRADO_CORRECTO);
            } else {
                resp.setStatus(500);
            }
        } else {
            try {
                if (as.completeDeleteAlumno(alumnoI) == 1) {
                    resp.setStatus(200);
                    req.setAttribute("json", Constantes.MENSAJE_BORRADO_CORRECTO);
                } else {
                    resp.setStatus(500);
                    req.setAttribute("json", Constantes.MENSAJE_BORRADO_INCORRECTO);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Alumnos.class.getName()).log(Level.SEVERE, null, ex);
                resp.setStatus(500);
            }
        }
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
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AlumnosServicios as = new AlumnosServicios();
        Alumno alumnoI = (Alumno) req.getAttribute(Constantes.REQUEST_ATTRIBUTE_ALUMNO);
        if (as.addAlumno(alumnoI)) {
            resp.setStatus(200);
            req.setAttribute("json", Constantes.MENSAJE_INSERTADO_CORRECTO);
        } else {
            resp.setStatus(500);
            req.setAttribute("json", Constantes.MENSAJE_INSERTADO_INCORRECTO);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AlumnosServicios as = new AlumnosServicios();
        Alumno alumnoI = (Alumno) request.getAttribute(Constantes.REQUEST_ATTRIBUTE_ALUMNO);
        if (as.updateAlumno(alumnoI)) {
            response.setStatus(200);
            request.setAttribute("json", Constantes.MENSAJE_ACTUALIZADO_CORRECTO);
        } else {
            response.setStatus(500);
            request.setAttribute("json", Constantes.MENSAJE_ACTUALIZADO_INCORRECTO);
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
