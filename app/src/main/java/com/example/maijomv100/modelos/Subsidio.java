package com.example.maijomv100.modelos;

public class Subsidio {

    private int id_subsidio;
    private String nombre_subsidio;
    private String descripcion_subsidio;
    private String tipo_subsidio;

    public Subsidio() {
    }

    public Subsidio(int id_subsidio, String nombre_subsidio, String descripcion_subsidio, String tipo_subsidio) {
        this.id_subsidio = id_subsidio;
        this.nombre_subsidio = nombre_subsidio;
        this.descripcion_subsidio = descripcion_subsidio;
        this.tipo_subsidio = tipo_subsidio;
    }

    public int getId_subsidio() {
        return id_subsidio;
    }

    public void setId_subsidio(int id_subsidio) {
        this.id_subsidio = id_subsidio;
    }

    public String getNombre_subsidio() {
        return nombre_subsidio;
    }

    public void setNombre_subsidio(String nombre_subsidio) {
        this.nombre_subsidio = nombre_subsidio;
    }

    public String getDescripcion_subsidio() {
        return descripcion_subsidio;
    }

    public void setDescripcion_subsidio(String descripcion_subsidio) {
        this.descripcion_subsidio = descripcion_subsidio;
    }

    public String getTipo_subsidio() {
        return tipo_subsidio;
    }

    public void setTipo_subsidio(String tipo_subsidio) {
        this.tipo_subsidio = tipo_subsidio;
    }
}
