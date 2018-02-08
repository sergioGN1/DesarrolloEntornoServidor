/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Alumno;
import model.Asignatura;
import model.Completado;
import servicios.AlumnosServicios;
import servicios.AsignaturasServicios;

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
        Completado completado = new Completado();
        Asignatura asignatura = (Asignatura) request.getAttribute("asignatura");
        if (as.updateAsignatura(asignatura)) {
            completado.setMensaje("Actualizado");
        } else {
            completado.setMensaje("Se produjo un error al actualizar");
        }
        request.setAttribute("json", completado);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AsignaturasServicios as = new AsignaturasServicios();
        Completado completado = new Completado();
        Asignatura asignatura = (Asignatura) req.getAttribute("asignatura");
        if (as.deleteAsignatura(asignatura)) {
            completado.setMensaje("Borrado");
            
        } else {
            resp.setStatus(500);
            completado.setMensaje("Se produjo un error al borrar");
        }
        req.setAttribute("json", completado); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AsignaturasServicios as = new AsignaturasServicios();
        Completado completado = new Completado();
        Asignatura asignatura = (Asignatura) req.getAttribute("asignatura");
        if (as.addAsignatura(asignatura)) {
            completado.setMensaje("Insertado");
        } else {
            completado.setMensaje("Se produjo un error al insertar");
        }
        req.setAttribute("json", completado);
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
