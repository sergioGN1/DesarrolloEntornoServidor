/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Completado;
import model.Nota;
import servicios.NotasServicios;

/**
 *
 * @author DAW
 */
@WebServlet(name = "Notas", urlPatterns = {"/rest/notas"})
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
        NotasServicios ns = new NotasServicios();
        Nota notaCogida = new Nota();

        Nota nota = (Nota) request.getAttribute("nota");

        notaCogida.setNota(ns.getAllNotasSelect(nota));
        request.setAttribute("json", nota);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NotasServicios ns = new NotasServicios();
        Nota nota = (Nota) req.getAttribute("nota");
        if (ns.deleteNota(nota)) {
            resp.setStatus(200);
            req.setAttribute("json", "Borrado correctamente");
        } else {
            resp.setStatus(500);
            req.setAttribute("json", "Se produjo un error al borrar");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NotasServicios ns = new NotasServicios();
        Nota nota = (Nota) req.getAttribute("nota");
        if (ns.addNota(nota)) {
            resp.setStatus(200);
            req.setAttribute("json", "Insertado Correctamente");
        } else {
            resp.setStatus(500);
            req.setAttribute("json", "Se produjo un error al insertar");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        NotasServicios ns = new NotasServicios();
        Nota nota = (Nota) request.getAttribute("nota");
        if (ns.updateNota(nota)) {
            response.setStatus(200);
            request.setAttribute("json", "Actualizado correctamente");
        } else {
            response.setStatus(500);
            request.setAttribute("json", "Se produjo un error al actualizar");
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
