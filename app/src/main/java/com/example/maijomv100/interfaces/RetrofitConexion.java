package com.example.maijomv100.interfaces;

import com.example.maijomv100.modelos.Ficha;
import com.example.maijomv100.modelos.Noticia;
import com.example.maijomv100.modelos.Postulante;
import com.example.maijomv100.modelos.Proyecto;
import com.example.maijomv100.modelos.Solicitud;
import com.example.maijomv100.modelos.Subsidio;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitConexion {

    @FormUrlEncoded
    @POST("validarPostulante.php")
    Call<List<Postulante>> getPostulante(@Field("rut") String rut, @Field("pass") String pass);

    @FormUrlEncoded
    @POST("insertarPostulante.php")
    Call<Boolean> setPostulante(@Field("rut") String rut,
                                @Field("pass") String pass,
                                @Field("nombre") String nombre,
                                @Field("apellido") String apellido,
                                @Field("fechaNacimiento") String fechaNacimiento,
                                @Field("sexo") String sexo,
                                @Field("correo") String correo,
                                @Field("telefono") String telefono,
                                @Field("direccion") String direccion,
                                @Field("comuna") String comuna);

    @FormUrlEncoded
    @POST("editarPerfil.php")
    Call<Boolean> editPostulante(@Field("rut") String rut,
                                @Field("pass") String pass,
                                @Field("nombre") String nombre,
                                @Field("apellido") String apellido,
                                @Field("fechaNacimiento") String fechaNacimiento,
                                @Field("sexo") String sexo,
                                @Field("correo") String correo,
                                @Field("telefono") String telefono,
                                @Field("direccion") String direccion,
                                @Field("comuna") String comuna);

    @POST("mostrar.php")
    Call<List<Noticia>> getNoticias();

    @POST("mostrar.php")
    Call<List<Proyecto>> getProyectos();

    @FormUrlEncoded
    @POST("mostrar.php")
    Call<List<Ficha>> getFicha(@Field("rut") String rut);

    @FormUrlEncoded
    @POST("mostrarEdad.php")
    Call<List<Ficha>> getEdad(@Field("rut") String rut);

    @FormUrlEncoded
    @POST("mostrarSolicitudes.php")
    Call<List<Solicitud>> getSolicitudes(@Field("id_ficha") String id_ficha);

    @POST("mostrar.php")
    Call<List<Subsidio>> getSubsidios();

    @FormUrlEncoded
    @POST("insertarSolicitud.php")
    Call<Boolean> setSolicitud(@Field("fecha_solicitud") String fecha_solicitud,
                                @Field("id_estado") String id_estado,
                                @Field("id_subsidio") int id_subsidio,
                                @Field("id_ficha") String id_ficha,
                                @Field("id_serviu") String id_serviu,
                                @Field("rut_postulante") String rut_postulante);


}
