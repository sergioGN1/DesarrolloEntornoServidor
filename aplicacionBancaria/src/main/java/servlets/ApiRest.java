/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;
import model.Datos;
import model.Mensaje;
import model.Movimiento;
import servicios.CuentasServicios;
import servicios.MovimientosServicios;
import utils.Constantes;

/**
 *
 * @author Sergio
 */
@WebServlet(name = "ApiRest", urlPatterns = {"/apirest"})
public class ApiRest extends HttpServlet {

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
        MovimientosServicios movimientosServicios = new MovimientosServicios();
        Datos datos = (Datos)request.getAttribute("datos");
        ObjectMapper mapper = new ObjectMapper();
        
        mapper.writeValue(response.getOutputStream(), movimientosServicios.getMovimientos(datos));
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
        CuentasServicios cuentasServicios = new CuentasServicios();
        Movimiento movimiento = (Movimiento) request.getAttribute("movimiento");
        Cliente cliente = (Cliente) request.getAttribute("cliente");
        ObjectMapper mapper = new ObjectMapper();
        
        Mensaje mensaje = new Mensaje();

        if (cuentasServicios.reintegroDinero(movimiento, cliente)) {
            mensaje.setContenido("Reintegro realizado con éxito");
        } else {
            mensaje.setContenido("Hubo un problema al realizar el reintegro");
        }

        mapper.writeValue(response.getOutputStream(), mapper.writeValueAsString(mensaje));
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
