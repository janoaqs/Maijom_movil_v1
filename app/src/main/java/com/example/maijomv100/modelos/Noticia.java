package com.example.maijomv100.modelos;

import java.util.Date;

public class Noticia {

    private int id_noticia;
    private String 	titulo_noticia;
    private Date fecha_noticia;
    private String descripcion_noticia;
    private String imagen_noticia;
    private int id_cliente;

    public Noticia() {
    }

    public Noticia(int id_noticia, String titulo_noticia, Date fecha_noticia, String descripcion_noticia, String imagen_noticia, int id_cliente) {
        this.id_noticia = id_noticia;
        this.titulo_noticia = titulo_noticia;
        this.fecha_noticia = fecha_noticia;
        this.descripcion_noticia = descripcion_noticia;
        this.imagen_noticia = imagen_noticia;
        this.id_cliente = id_cliente;
    }

    public int getId_noticia() {
        return id_noticia;
    }

    public void setId_noticia(int id_noticia) {
        this.id_noticia = id_noticia;
    }

    public String getTitulo_noticia() {
        return titulo_noticia;
    }

    public void setTitulo_noticia(String titulo_noticia) {
        this.titulo_noticia = titulo_noticia;
    }

    public Date getFecha_noticia() {
        return fecha_noticia;
    }

    public void setFecha_noticia(Date fecha_noticia) {
        this.fecha_noticia = fecha_noticia;
    }

    public String getDescripcion_noticia() {
        return descripcion_noticia;
    }

    public void setDescripcion_noticia(String descripcion_noticia) {
        this.descripcion_noticia = descripcion_noticia;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getImagen_noticia() {
        return imagen_noticia;
    }

    public void setImagen_noticia(String imagen_noticia) {
        this.imagen_noticia = imagen_noticia;
    }
}
