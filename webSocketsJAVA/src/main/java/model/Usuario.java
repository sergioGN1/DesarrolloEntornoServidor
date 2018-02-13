/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author DAW
 */
public class Usuario {
    private Long id;
    private String nombre;
    private String password;
    private String email;
    private Boolean activo;
    private Date fecha_activacion;
    private String codigo_activacion;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Date getFecha_activacion() {
        return fecha_activacion;
    }

    public void setFecha_activacion(Date fecha_activacion) {
        this.fecha_activacion = fecha_activacion;
    }

    public String getCodigo_activacion() {
        return codigo_activacion;
    }

    public void setCodigo_activacion(String codigo_activacion) {
        this.codigo_activacion = codigo_activacion;
    }
}
