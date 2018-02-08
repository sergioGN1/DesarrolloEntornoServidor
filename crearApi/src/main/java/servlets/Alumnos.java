/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Alumno;
import model.Completado;
import model.ErrorHttp;
import servicios.AlumnosServicios;

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
        Completado completado = new Completado();
        Alumno alumnoI = (Alumno) req.getAttribute("alumno");
        if (as.deleteAlumno(alumnoI)) {
            completado.setMensaje("Borrado");
            
        } else {
            resp.setStatus(500);
            completado.setMensaje("Se produjo un error al borrar");
        }
        req.setAttribute("json", completado);
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
        Completado completado = new Completado();
        Alumno alumnoI = (Alumno) req.getAttribute("alumno");
        if (as.addAlumno(alumnoI)) {
            completado.setMensaje("Insertado");
        } else {
            completado.setMensaje("Se produjo un error al insertar");
        }
        req.setAttribute("json", completado);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AlumnosServicios as = new AlumnosServicios();
        Completado completado = new Completado();
        Alumno alumnoI = (Alumno) request.getAttribute("alumno");
        if (as.updateAlumno(alumnoI)) {
            completado.setMensaje("Actualizado");
        } else {
            completado.setMensaje("Se produjo un error al actualizar");
        }
        request.setAttribute("json", completado);
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
