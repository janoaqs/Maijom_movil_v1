package com.example.maijomv100.modelos;

public class Proyecto {

    private int id_proyecto;
    private String nombre_proyecto;
    private String direccion_proyecto;
    private String telefono_proyecto;
    private int casas_disponibles;
    private String nombre_inmobiliaria;
    private String nombre_comuna;
    private String nombre_subsidio;

    public Proyecto() {
    }

    public Proyecto(int id_proyecto, String nombre_proyecto, String direccion_proyecto, String telefono_proyecto, int casas_disponibles, String nombre_inmobiliaria, String nombre_comuna, String nombre_subsidio) {
        this.id_proyecto = id_proyecto;
        this.nombre_proyecto = nombre_proyecto;
        this.direccion_proyecto = direccion_proyecto;
        this.telefono_proyecto = telefono_proyecto;
        this.casas_disponibles = casas_disponibles;
        this.nombre_inmobiliaria = nombre_inmobiliaria;
        this.nombre_comuna = nombre_comuna;
        this.nombre_subsidio = nombre_subsidio;
    }

    public int getId_proyecto() {
        return id_proyecto;
    }

    public void setId_proyecto(int id_proyecto) {
        this.id_proyecto = id_proyecto;
    }

    public String getNombre_proyecto() {
        return nombre_proyecto;
    }

    public void setNombre_proyecto(String nombre_proyecto) {
        this.nombre_proyecto = nombre_proyecto;
    }

    public String getDireccion_proyecto() {
        return direccion_proyecto;
    }

    public void setDireccion_proyecto(String direccion_proyecto) {
        this.direccion_proyecto = direccion_proyecto;
    }

    public String getTelefono_proyecto() {
        return telefono_proyecto;
    }

    public void setTelefono_proyecto(String telefono_proyecto) {
        this.telefono_proyecto = telefono_proyecto;
    }

    public int getCasas_disponibles() {
        return casas_disponibles;
    }

    public void setCasas_disponibles(int casas_disponibles) {
        this.casas_disponibles = casas_disponibles;
    }

    public String getNombre_inmobiliaria() {
        return nombre_inmobiliaria;
    }

    public void setNombre_inmobiliaria(String nombre_inmobiliaria) {
        this.nombre_inmobiliaria = nombre_inmobiliaria;
    }

    public String getNombre_comuna() {
        return nombre_comuna;
    }

    public void setNombre_comuna(String nombre_comuna) {
        this.nombre_comuna = nombre_comuna;
    }

    public String getNombre_subsidio() {
        return nombre_subsidio;
    }

    public void setNombre_subsidio(String nombre_subsidio) {
        this.nombre_subsidio = nombre_subsidio;
    }
}
