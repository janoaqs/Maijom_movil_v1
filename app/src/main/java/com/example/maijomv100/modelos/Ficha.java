package com.example.maijomv100.modelos;

public class Ficha {

    private String id_ficha;
    private String nombre_integrante;
    private String parentezco_integrante;
    private int edad_integrante;
    private int calificacion_se;

    public Ficha() {
    }

    public Ficha(String id_ficha, String nombre_integrante, String parentezco_integrante, int edad_integrante, int calificacion_se) {
        this.id_ficha = id_ficha;
        this.nombre_integrante = nombre_integrante;
        this.parentezco_integrante = parentezco_integrante;
        this.edad_integrante = edad_integrante;
        this.calificacion_se = calificacion_se;
    }

    public Ficha(String id_ficha, int edad_integrante) {
        this.id_ficha = id_ficha;
        this.edad_integrante = edad_integrante;
    }

    public String getNombre_integrante() {
        return nombre_integrante;
    }

    public void setNombre_integrante(String nombre_integrante) {
        this.nombre_integrante = nombre_integrante;
    }

    public String getParentezco_integrante() {
        return parentezco_integrante;
    }

    public void setParentezco_integrante(String parentezco_integrante) {
        this.parentezco_integrante = parentezco_integrante;
    }

    public int getEdad_integrante() {
        return edad_integrante;
    }

    public void setEdad_integrante(int edad_integrante) {
        this.edad_integrante = edad_integrante;
    }

    public int getCalificacion_se() {
        return calificacion_se;
    }

    public void setCalificacion_se(int calificacion_se) {
        this.calificacion_se = calificacion_se;
    }

    public String getId_ficha() {
        return id_ficha;
    }

    public void setId_ficha(String id_ficha) {
        this.id_ficha = id_ficha;
    }
}
