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
import model.Alumno;

/**
 *
 * @author Sergio
 */
public class AlumnosServicios {

    public Alumno recogerParametros(String nombre, String fecha, String mayor) {
        Alumno alumno = new Alumno();
        if (nombre != null) {
            alumno.setNombre(nombre);
        }
        if (fecha != null) {
            alumno.setFecha_nacimiento(parseoFecha(fecha));
        }
        if (mayor != null) {
            alumno.setMayor_edad(true);
        } else {
            alumno.setMayor_edad(false);
        }
        return alumno;
    }

    public Date parseoFecha(String fecha) {
        Date fecha_nacimiento = null;
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            fecha_nacimiento = formato.parse(fecha);
        } catch (ParseException ex) {
        }
        return fecha_nacimiento;
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
