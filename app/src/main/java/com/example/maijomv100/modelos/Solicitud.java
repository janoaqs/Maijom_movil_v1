package com.example.maijomv100.modelos;

import java.util.Date;

public class Solicitud {

    private int id_solicitud;
    private Date fecha_solicitud;
    private String id_estado;
    private String nombre_estado;
    private int id_subsidio;
    private String id_ficha;
    private String id_serviu;
    private String rut_postulante;

    public Solicitud() {
    }

    public Solicitud(int id_solicitud, Date fecha_solicitud, String id_estado, int id_subsidio, String id_ficha, String id_serviu, String rut_postulante) {
        this.id_solicitud = id_solicitud;
        this.fecha_solicitud = fecha_solicitud;
        this.id_estado = id_estado;
        this.id_subsidio = id_subsidio;
        this.id_ficha = id_ficha;
        this.id_serviu = id_serviu;
        this.rut_postulante = rut_postulante;
    }

    public Solicitud(int id_solicitud, Date fecha_solicitud, String nombre_estado) {
        this.id_solicitud = id_solicitud;
        this.fecha_solicitud = fecha_solicitud;
        this.nombre_estado = nombre_estado;
    }

    public int getId_solicitud() {
        return id_solicitud;
    }

    public void setId_solicitud(int id_solicitud) {
        this.id_solicitud = id_solicitud;
    }

    public Date getFecha_solicitud() {
        return fecha_solicitud;
    }

    public void setFecha_solicitud(Date fecha_solicitud) {
        this.fecha_solicitud = fecha_solicitud;
    }

    public String getId_estado() {
        return id_estado;
    }

    public void setId_estado(String id_estado) {
        this.id_estado = id_estado;
    }

    public String getNombre_estado() {
        return nombre_estado;
    }

    public void setNombre_estado(String nombre_estado) {
        this.nombre_estado = nombre_estado;
    }

    public int getId_subsidio() {
        return id_subsidio;
    }

    public void setId_subsidio(int id_subsidio) {
        this.id_subsidio = id_subsidio;
    }

    public String getId_ficha() {
        return id_ficha;
    }

    public void setId_ficha(String id_ficha) {
        this.id_ficha = id_ficha;
    }

    public String getId_serviu() {
        return id_serviu;
    }

    public void setId_serviu(String id_serviu) {
        this.id_serviu = id_serviu;
    }

    public String getRut_postulante() {
        return rut_postulante;
    }

    public void setRut_postulante(String rut_postulante) {
        this.rut_postulante = rut_postulante;
    }
}
