/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;
import model.Cuenta;
import model.Mensaje;
import servicios.ClientesServicios;
import servicios.CuentasServicios;
import utils.Constantes;

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
        CuentasServicios cuentaServices = new CuentasServicios();
        String cliente = request.getParameter(Constantes.RECOGER_PRIMER_TITULAR);
        String acc = request.getParameter("acc");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Cliente objetoCliente = mapper.readValue(cliente, new TypeReference<Cliente>() {
        });
        Mensaje mensaje = new Mensaje();
        Pattern pat = Pattern.compile(Constantes.PATRON_EXP_REG);
        Matcher mat = pat.matcher(objetoCliente.getCl_dni());
        if (mat.matches()) {
            if (clientServices.comprobarUsuario(objetoCliente)) {

                Cliente clienteCogido = clientServices.getUserByDni(objetoCliente);
                mensaje.setStatus("200");
                mensaje.setContenido(mapper.writeValueAsString(clienteCogido));
                mensaje.setOtro("1");
            } else if ("insertar".equals(acc)) {
                mensaje.setOtro("0");
                String cuenta = request.getParameter("cuenta");
                Cuenta objetoCuenta = mapper.readValue(cuenta, new TypeReference<Cuenta>() {
                });

                if (cuentaServices.comprobarCuenta(objetoCuenta.getCu_ncu())) {
                    if (clientServices.insertarNuevoCliente(objetoCliente, objetoCuenta) == 1) {
                        mensaje.setContenido(Constantes.MENSAJE_CLIENTE_CREADO_OK);
                    } else if (clientServices.insertarNuevoCliente(objetoCliente, objetoCuenta) == 2) {
                        mensaje.setContenido(Constantes.MENSAJE_DUPLICADO_CUENTA);
                    } else {
                        mensaje.setContenido(Constantes.MENSAJE_CLIENTE_CREADO_ERROR);
                    }
                } else {
                    mensaje.setContenido(Constantes.MENSAJE_NUMERO_DE_CUENTA_ERRONEO);
                }
            } else if ("actualizar".equals(acc)) {
                String cuenta = request.getParameter("cuenta");
                Cuenta objetoCuenta = mapper.readValue(cuenta, new TypeReference<Cuenta>() {
                });
                if (clientServices.actualizarCliente(objetoCliente, objetoCuenta) == 1) {
                        mensaje.setContenido(Constantes.MENSAJE_CLIENTE_CREADO_OK);
                    } else if (clientServices.insertarNuevoCliente(objetoCliente, objetoCuenta) == 2) {
                        mensaje.setContenido(Constantes.MENSAJE_DUPLICADO_CUENTA);
                    } else {
                        mensaje.setContenido(Constantes.MENSAJE_CLIENTE_CREADO_ERROR);
                    }
            } else {
                mensaje.setContenido("desplegar");
            }
        } else {
            mensaje.setContenido(Constantes.MENSAJE_DNI_MAL_FORMADO);
        }
        response.getWriter().println(mapper.writeValueAsString(mensaje));
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
