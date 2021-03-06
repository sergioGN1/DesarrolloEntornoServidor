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
        Cliente objetoCliente2 = null;
        String cliente = request.getParameter(Constantes.RECOGER_PRIMER_TITULAR);
        String cliente2 = request.getParameter("segundoTitular");
        String acc = request.getParameter("acc");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        if(cliente2 != null){
            objetoCliente2 = mapper.readValue(cliente2, new TypeReference<Cliente>() {
        });
        }
        Cliente objetoCliente = mapper.readValue(cliente, new TypeReference<Cliente>() {
        });
        Mensaje mensaje = new Mensaje();
        
        if (clientServices.comprobarDNI(objetoCliente.getCl_dni())) {
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
                    if (clientServices.insertarNuevoCliente(objetoCliente, objetoCuenta,objetoCliente2) == 1) {
                        mensaje.setContenido(Constantes.MENSAJE_CLIENTE_CREADO_OK);
                        mensaje.setOtro("0");
                    } else if (clientServices.insertarNuevoCliente(objetoCliente, objetoCuenta,objetoCliente2) == 2) {
                        mensaje.setContenido(Constantes.MENSAJE_DUPLICADO_CUENTA);
                        mensaje.setOtro("3");
                    } else {
                        mensaje.setContenido(Constantes.MENSAJE_CLIENTE_CREADO_ERROR);
                        mensaje.setOtro("2");
                    }
                } else {
                    mensaje.setContenido(Constantes.MENSAJE_NUMERO_DE_CUENTA_ERRONEO);
                    mensaje.setOtro("5");
                }
            } else if ("actualizar".equals(acc)) {
                String cuenta = request.getParameter("cuenta");
                Cuenta objetoCuenta = mapper.readValue(cuenta, new TypeReference<Cuenta>() {
                });
                if (clientServices.actualizarCliente(objetoCliente, objetoCuenta) == 1) {
                        mensaje.setContenido(Constantes.MENSAJE_CLIENTE_CREADO_OK);
                        mensaje.setOtro("0");
                    } else if (clientServices.actualizarCliente(objetoCliente, objetoCuenta) == 2) {
                        mensaje.setContenido(Constantes.MENSAJE_DUPLICADO_CUENTA);
                        mensaje.setOtro("3");
                    } else {
                        mensaje.setContenido(Constantes.MENSAJE_CLIENTE_CREADO_ERROR);
                        mensaje.setOtro("2");
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
