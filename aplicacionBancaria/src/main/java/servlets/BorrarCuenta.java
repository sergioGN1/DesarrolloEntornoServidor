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
import model.Cuenta;
import model.Mensaje;
import servicios.CuentasServicios;

/**
 *
 * @author Sergio
 */
@WebServlet(name = "BorrarCuenta", urlPatterns = {"/borrarcuenta"})
public class BorrarCuenta extends HttpServlet {

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
        //ClientesServicios clientServices = new ClientesServicios();
        CuentasServicios cuentasServicios = new CuentasServicios();
        //MovimientosServicios movimientosServicios = new MovimientosServicios();
        String cuenta = request.getParameter("cuenta");
        String accion = request.getParameter("a");
        ObjectMapper mapper = new ObjectMapper();
        Cuenta objetoCuenta = mapper.readValue(cuenta, new TypeReference<Cuenta>() {
        });
        Mensaje mensaje = new Mensaje();
        switch (accion) {
            case "visualizarDatos":
                if (cuentasServicios.comprobarCuenta(objetoCuenta.getCu_ncu())) {
                    if (cuentasServicios.comprobarExistenciaCuenta(objetoCuenta.getCu_ncu())) {
                        mensaje.setContenido(mapper.writeValueAsString(cuentasServicios.getCuentas(objetoCuenta)));
                        mensaje.setOtro("2");
                    } else {
                        mensaje.setContenido("No existe la cuenta");
                    }
                } else {
                    mensaje.setContenido("El numero de cuenta esta mal formado");
                }
                response.getWriter().println(mapper.writeValueAsString(mensaje));
                break;
            case "borrarTotal":
                if (cuentasServicios.comprobarCuenta(objetoCuenta.getCu_ncu())) {
                    if (cuentasServicios.comprobarExistenciaCuenta(objetoCuenta.getCu_ncu())) {
                        if (cuentasServicios.saldoDeLaCuenta(objetoCuenta)) {
                            if (cuentasServicios.borrarCuentaTotal(objetoCuenta) == 1) {
                                mensaje.setContenido("La cuenta se borró correctamente");
                            }  else {
                                mensaje.setContenido("Se produjo un error al borrar la cuenta");
                                mensaje.setOtro("4");
                            }
                        } else {
                            mensaje.setContenido("El saldo de esta no esta a 0");
                        }
                    } else {
                        mensaje.setContenido("No existe la cuenta");
                    }
                } else {
                    mensaje.setContenido("El numero de cuenta esta mal formado");
                }
                response.getWriter().println(mapper.writeValueAsString(mensaje));
                break;
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
