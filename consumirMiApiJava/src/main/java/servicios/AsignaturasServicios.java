/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import model.Asignatura;

/**
 *
 * @author Sergio
 */
public class AsignaturasServicios {
    public Asignatura recogerParametros(String nombre, String curso, String ciclo) {
        Asignatura asignatura = new Asignatura();
        if (nombre != null) {
            asignatura.setNombre(nombre);
        }
        if (curso != null) {
            asignatura.setCurso(curso);
        }
        if (ciclo != null) {
            asignatura.setCiclo(ciclo);
        } 
        return asignatura;
    }

    public long parseoId(String id) {
        long idParseado = 0;
        try {
            idParseado = Long.parseLong(id);
        } catch (NumberFormatException nfe) {
            System.out.println("NumberFormatException: " + nfe.getMessage());
        }
        return idParseado;
    }
}
