/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import com.fasterxml.jackson.core.JsonProcessingException;
import model.Datos;
import dao.MovimientosDAO;
import java.util.List;
import model.Movimiento;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Sergio
 */
public class MovimientosServicios {

    public MovimientosServicios() {
    }
    
    

    public String getMovimientos(Datos objetoDatos) {
        try {
            MovimientosDAO dao = new MovimientosDAO();
            ObjectMapper mapper = new ObjectMapper();
            List<Movimiento> movimientos = dao.getMovimientos(objetoDatos);
            return mapper.writeValueAsString(movimientos);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(MovimientosServicios.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getMessage();
        }
    }
}
