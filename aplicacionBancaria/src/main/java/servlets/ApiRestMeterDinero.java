/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;
import model.Datos;
import model.Mensaje;
import model.Movimiento;
import servicios.ClientesServicios;
import servicios.CuentasServicios;
import utils.Constantes;

/**
 *
 * @author DAW
 */
@WebServlet(name = "ApiRestMeterDinero", urlPatterns = {"/apirestmeterdinero"})
public class ApiRestMeterDinero extends HttpServlet {

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
        ClientesServicios clientServices = new ClientesServicios();
        CuentasServicios cuentasServicios = new CuentasServicios();
        Movimiento movimiento = (Movimiento) request.getAttribute("movimiento");
        Cliente cliente = (Cliente) request.getAttribute("cliente");
        ObjectMapper mapper = new ObjectMapper();
        movimiento.setMo_fec(new Date());
        Mensaje mensaje = new Mensaje();
        Datos datos = new Datos();
        datos.setDni(cliente.getCl_dni());
        datos.setNumeroCuenta(movimiento.getMo_ncu());
        if (clientServices.comprobarCuentaDni(datos)) {
            if (cuentasServicios.ingresoDinero(movimiento, cliente)) {
                mensaje.setContenido("Ingreso realizado con éxito");
            } else {
                mensaje.setContenido("Hubo un problema al realizar el ingreso");
            }
        }else{
            mensaje.setContenido(Constantes.DNI_CUENTA);
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
