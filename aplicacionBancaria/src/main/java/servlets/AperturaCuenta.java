/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;
import servicios.ClientesServicios;
/**
 *
 * @author Sergio
 */
@WebServlet(name = "AperturaCuenta", urlPatterns = {"/aperturaCuenta"})
public class AperturaCuenta extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        
        ClientesServicios clientServices = new ClientesServicios();
        String dniCliente = request.getParameter("dni");
        String numeroCuenta = request.getParameter("cuenta");
        Cliente cliente = new Cliente();
        cliente.setCl_dni(dniCliente);
        String primerosNueve = numeroCuenta.substring(0, numeroCuenta.length() - 1);
        String ultimo = numeroCuenta.substring(numeroCuenta.length() - 1,numeroCuenta.length());
        Pattern pat = Pattern.compile("[0-9]{8}[A-Z]{1}");
        Matcher mat = pat.matcher(dniCliente);
        if(mat.matches()){
            if(clientServices.comprobarUsuario(cliente)){
                
            }else{
                
            }
        }else {
            request.setAttribute("error","El DNI esta mal formado");
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
