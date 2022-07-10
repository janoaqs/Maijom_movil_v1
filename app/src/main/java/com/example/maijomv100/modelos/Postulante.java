package com.example.maijomv100.modelos;

import android.content.Intent;
import android.util.Log;

import java.util.Date;

public class Postulante {

    private String rut_postulante;
    private String pass_postulante;
    private String nombre_postulante;
    private String apellido_postulante;
    private Date fecha_nacimiento;
    private String sexo_postulante;
    private String correo_postulante;
    private String telefono_postulante;
    private String direccion_postulante	;
    private String 	id_comuna;

    public Postulante() {
    }

    public Postulante(String rut_postulante, String pass_postulante, String nombre_postulante, String apellido_postulante, Date fecha_nacimiento, String sexo_postulante, String correo_postulante, String telefono_postulante, String direccion_postulante, String id_comuna) {
        this.rut_postulante = rut_postulante;
        this.pass_postulante = pass_postulante;
        this.nombre_postulante = nombre_postulante;
        this.apellido_postulante = apellido_postulante;
        this.fecha_nacimiento = fecha_nacimiento;
        this.sexo_postulante = sexo_postulante;
        this.correo_postulante = correo_postulante;
        this.telefono_postulante = telefono_postulante;
        this.direccion_postulante = direccion_postulante;
        this.id_comuna = id_comuna;
    }

    public String getRut_postulante() {
        return rut_postulante;
    }

    public void setRut_postulante(String rut_postulante) {
        this.rut_postulante = rut_postulante;
    }

    public String getPass_postulante() {
        return pass_postulante;
    }

    public void setPass_postulante(String pass_postulante) {
        this.pass_postulante = pass_postulante;
    }

    public String getNombre_postulante() {
        return nombre_postulante;
    }

    public void setNombre_postulante(String nombre_postulante) {
        this.nombre_postulante = nombre_postulante;
    }

    public String getApellido_postulante() {
        return apellido_postulante;
    }

    public void setApellido_postulante(String apellido_postulante) {
        this.apellido_postulante = apellido_postulante;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getSexo_postulante() {
        return sexo_postulante;
    }

    public void setSexo_postulante(String sexo_postulante) {
        this.sexo_postulante = sexo_postulante;
    }

    public String getCorreo_postulante() {
        return correo_postulante;
    }

    public void setCorreo_postulante(String correo_postulante) {
        this.correo_postulante = correo_postulante;
    }

    public String getTelefono_postulante() {
        return telefono_postulante;
    }

    public void setTelefono_postulante(String telefono_postulante) {
        this.telefono_postulante = telefono_postulante;
    }

    public String getDireccion_postulante() {
        return direccion_postulante;
    }

    public void setDireccion_postulante(String direccion_postulante) {
        this.direccion_postulante = direccion_postulante;
    }

    public String getId_comuna() {
        return id_comuna;
    }

    public void setId_comuna(String id_comuna) {
        this.id_comuna = id_comuna;
    }


    public boolean validacion() {
        Boolean resultado=false; //Resultado de si el el rut es válido: Si(true) o No(false)
        String caracter; //Caracter del rut que se esta analizando
        String digitoVerificador="";
        int caracterAnum=0; //Caracter del rut pasado a Entero
        int largoRut=0; //Cantidad de caracteres que tiene el rut ingresado
        int constante=2; //Constante predefinida para calcular el rut en Chile
        int sumaDigitos=0; //La suma acumulativa de cada dígito del rut por la constante
        int digitoCalculado=0; //El dígito verificador resultado de aplicar la fórmula
        int digitoEscrito=0; //El digito verificador ingresado por el usuario
        int i;

        largoRut=this.rut_postulante.length();

        //Se descartan los rut que no cumplan con el largo de caracteres (8-9)
        if (largoRut<8 || largoRut>9) {
            Log.d("Errorlg","Largo incorrecto");
            resultado=false;
        }
        //Si el rut coincide con el largo de caracteres
        else {
            for (i=largoRut-1;i>0;i--) {
                caracter=this.rut_postulante.substring(i-1,i); //Pasar a un String el caracter del rut seleccionado
                //Se intenta convertir Entero para saber si es un número
                try {
                    caracterAnum=Integer.parseInt(caracter);
                    //Si la conversion es correcta se va acumulando la suma
                    Log.d("errorlg","No Hay una letra loca");
                    sumaDigitos=sumaDigitos+Integer.parseInt(caracter)*constante;
                    Log.d("errorlg","Indice="+i+" caracter="+caracter+" suma="+sumaDigitos);
                    constante=constante+1;
                    if (constante==8) {
                        constante=2;
                    }
                }
                catch (Exception e) {
                    //Si la conversion no es correcta se da valor 0 a ese caracter
                    Log.d("errorlg","Hay una letra loca");
                    caracterAnum=0;
                    i=1;
                }
            }
            //Pasar el digito verificador a Entero si es que es un numero, sino atrapar la Exception
            try {
                digitoEscrito=Integer.parseInt(this.rut_postulante.substring(largoRut-1,largoRut));
                Log.d("errorlg","Es guion un numero");
            }
            catch (Exception e) {
                Log.d("errorlg","Es guion K");
                digitoVerificador=this.rut_postulante.substring(largoRut-1,largoRut);
            }
            digitoCalculado=11-(sumaDigitos%11);
            Log.d("errorlg","Digito calculado="+digitoCalculado+" escrito="+digitoVerificador);

            if (digitoCalculado==10 && digitoVerificador.toUpperCase().equals("K")) {
                resultado=true;
            }
            else if (digitoCalculado==11 && digitoEscrito==0) {
                resultado=true;
            }
            else if (digitoCalculado==digitoEscrito) {
                resultado=true;
            }
        }
        return resultado;
    }


}
