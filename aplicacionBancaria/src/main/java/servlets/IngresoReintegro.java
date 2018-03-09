/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
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
 * @author Sergio
 */
@WebServlet(name = "IngresoReintegro", urlPatterns = {"/ingresoreintegro"})
public class IngresoReintegro extends HttpServlet {

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
        CuentasServicios cuentasServicios = new CuentasServicios();
        String movimiento = request.getParameter("movimiento");
        String cliente = request.getParameter("cliente");
        String accion = request.getParameter("a");
        ObjectMapper mapper = new ObjectMapper();
        Movimiento objetoMovimiento = mapper.readValue(movimiento, new TypeReference<Movimiento>() {
        });
        Cliente objetoCliente = mapper.readValue(cliente, new TypeReference<Cliente>() {
        });
        Datos datos = new Datos();
        datos.setDni(objetoCliente.getCl_dni());
        datos.setNumeroCuenta(objetoMovimiento.getMo_ncu());
        Mensaje mensaje = new Mensaje();
        if (clientServices.comprobarDNI(objetoCliente.getCl_dni())) {
            if (clientServices.comprobarUsuario(objetoCliente)) {
                if (cuentasServicios.comprobarCuenta(objetoMovimiento.getMo_ncu())) {
                    if (cuentasServicios.comprobarExistenciaCuenta(objetoMovimiento.getMo_ncu())) {
                        if (clientServices.comprobarCuentaDni(datos)) {
                            if (Constantes.INSERTAR.equals(accion)) {
                                if (cuentasServicios.ingresoDinero(objetoMovimiento, objetoCliente)) {
                                    mensaje.setContenido(Constantes.INGRESO_OK);
                                } else {
                                    mensaje.setContenido(Constantes.ERROR_INGRESO);
                                }
                            } else if (Constantes.QUITAR.equals(accion)) {
                                if (cuentasServicios.saldoCuenta(objetoMovimiento.getMo_ncu()) > Integer.parseInt(objetoMovimiento.getMo_imp())) {
                                    if (cuentasServicios.reintegroDinero(objetoMovimiento, objetoCliente)) {
                                        mensaje.setContenido(Constantes.REINTEGRO_OK);
                                    } else {
                                        mensaje.setContenido(Constantes.ERROR_REINTEGRO);
                                    }
                                } else {
                                    mensaje.setContenido(Constantes.NO_SUFUCIENTE_SALDO);
                                }
                            } else {
                                mensaje.setContenido("Tiene que elegir una operacion");
                            }
                        }

                    }
                }
            }
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
