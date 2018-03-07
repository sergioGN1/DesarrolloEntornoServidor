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
import model.Datos;
import model.Mensaje;
import servicios.ClientesServicios;
import servicios.CuentasServicios;
import servicios.MovimientosServicios;
import utils.Constantes;

/**
 *
 * @author Sergio
 */
@WebServlet(name = "ListadoMovimientos", urlPatterns = {"/listadomovientos"})
public class ListadoMovimientos extends HttpServlet {

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
        MovimientosServicios movimientosServicios = new MovimientosServicios();
        String datos = request.getParameter("datos");
        ObjectMapper mapper = new ObjectMapper();
        Datos objetoDatos = mapper.readValue(datos, new TypeReference<Datos>() {
        });
        Mensaje mensaje = new Mensaje();
        if (clientServices.comprobarDNI(objetoDatos.getDni())) {
            if (clientServices.comprobarCuentaDni(objetoDatos)) {
                if (cuentasServicios.comprobarCuenta(objetoDatos.getNumeroCuenta())) {
                    String datosString = movimientosServicios.getMovimientos(objetoDatos);
                    if(!"[]".equals(datosString) || datosString != null){
                        mensaje.setContenido(mapper.writeValueAsString(datosString));
                        mensaje.setOtro("1");
                    }else{
                        mensaje.setContenido("No se han encontrado resultados");
                        mensaje.setOtro("0");
                    }
                } else {
                    mensaje.setContenido(Constantes.MENSAJE_NUMERO_DE_CUENTA_ERRONEO);
                }
            }else{
                mensaje.setContenido(Constantes.MENSAJE_NO_EXISTE_ASOCIACION);
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
