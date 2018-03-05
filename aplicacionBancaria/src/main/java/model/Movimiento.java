/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author Sergio
 */
public class Movimiento {
    private String mo_ncu;
    private String mo_des;
    private String mo_imp;
    private String mo_hor;
    private Date mo_fecha;

    public Movimiento() {
    }

    public String getMo_ncu() {
        return mo_ncu;
    }

    public void setMo_ncu(String mo_ncu) {
        this.mo_ncu = mo_ncu;
    }

    public String getMo_des() {
        return mo_des;
    }

    public void setMo_des(String mo_des) {
        this.mo_des = mo_des;
    }

    public String getMo_imp() {
        return mo_imp;
    }

    public void setMo_imp(String mo_imp) {
        this.mo_imp = mo_imp;
    }

    public String getMo_hor() {
        return mo_hor;
    }

    public void setMo_hor(String mo_hor) {
        this.mo_hor = mo_hor;
    }

    public Date getMo_fecha() {
        return mo_fecha;
    }

    public void setMo_fecha(Date mo_fecha) {
        this.mo_fecha = mo_fecha;
    }
    
    
}
