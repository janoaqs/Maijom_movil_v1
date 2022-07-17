package com.example.maijomv100.vistas;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maijomv100.MainActivity;
import com.example.maijomv100.R;
import com.example.maijomv100.interfaces.RetrofitConexion;
import com.example.maijomv100.modelos.Postulante;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Registrar extends AppCompatActivity {

    EditText rut_postulanteET;
    EditText pass_postulanteET;
    EditText nombre_postulanteET;
    EditText apellido_postulanteET;
    EditText fecha_nacimientoET;
    Button fecha_nacimientoBTN;
    Spinner sexoSP;
    EditText correo_postulanteET;
    EditText telefono_postulanteET;
    EditText direccion_postulanteET;
    Spinner comunaSP;
    Button registrarBTN;
    private int dia,mes,anio;
    private final String url="http://172.20.10.3/API/movil/solicitudes/postulante/"; //Base de datos local
    //private final String url="https://maijombdd.000webhostapp.com/maijom/api/solicitudes/postulante/"; //Base de datos Pruebas
    //private final String url="https://maijom-web.000webhostapp.com/API/movil/solicitudes/postulante/"; //Base de datos compartida

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        //Paso de la vista a variables
        rut_postulanteET=findViewById(R.id.rut_ET);
        pass_postulanteET=findViewById(R.id.pass_ET);
        nombre_postulanteET=findViewById(R.id.nombre_ET);
        apellido_postulanteET=findViewById(R.id.apellido_ET);
        fecha_nacimientoET=findViewById(R.id.fNacimiento_ET);
        fecha_nacimientoBTN=findViewById(R.id.fNacimiento_BTN);
        sexoSP=findViewById(R.id.sexo_SP);
        sexoSP.setPrompt("Sexo");
        correo_postulanteET=findViewById(R.id.correo_ET);
        telefono_postulanteET=findViewById(R.id.telefono_ET);
        direccion_postulanteET=findViewById(R.id.direccion_ET);
        comunaSP=findViewById(R.id.comunas_SP);
        comunaSP.setPrompt("Comuna");
        registrarBTN=findViewById(R.id.registar_BTN);
        //llenado de combobox sexo
        String[] datosSexo = new String[] {"Masculino","Femenino","Otro"};
        ArrayAdapter<String> adapterSexo = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datosSexo);
        sexoSP.setAdapter(adapterSexo);
        //llenado de combobox comunas
        String[] datosComunas = new String[] {"Concepcion","Chiguayante","Coronel",
                "Florida","Hualpen", "Hualqui","Lota","Penco","San pedro de la Paz",
                "Santa Juana","Talcahuano", "Tome"};
        ArrayAdapter<String> adapterComunas = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datosComunas);
        comunaSP.setAdapter(adapterComunas);
    }

    public void Registrar(View view) {

        //Asignar ID de comuna según seleccion de combo box
        String nombre_comuna = comunaSP.getSelectedItem().toString();
        String id_comuna = new String();
        if (nombre_comuna.equals("Concepcion")) {
            id_comuna = "CM1";
        }
        if (nombre_comuna.equals("Chiguayante")) {
            id_comuna = "CM2";
        }
        if (nombre_comuna.equals("Coronel")) {
            id_comuna = "CM3";
        }
        if (nombre_comuna.equals("Florida")) {
            id_comuna = "CM4";
        }
        if (nombre_comuna.equals("Hualpen")) {
            id_comuna = "CM5";
        }
        if (nombre_comuna.equals("Hualqui")) {
            id_comuna = "CM6";
        }
        if (nombre_comuna.equals("Lota")) {
            id_comuna = "CM7";
        }
        if (nombre_comuna.equals("Penco")) {
            id_comuna = "CM8";
        }
        if (nombre_comuna.equals("San pedro de la Paz")) {
            id_comuna = "CM9";
        }
        if (nombre_comuna.equals("Santa Juana")) {
            id_comuna = "CM10";
        }
        if (nombre_comuna.equals("Talcahuano")) {
            id_comuna = "CM11";
        }
        if (nombre_comuna.equals("Tome")) {
            id_comuna = "CM12";
        }

        String rut_postulante = rut_postulanteET.getText().toString();
        String pass_postulante = pass_postulanteET.getText().toString();
        String nombre_postulante = nombre_postulanteET.getText().toString();
        String apellido_postulante = apellido_postulanteET.getText().toString();
        String fecha_nacimiento = fecha_nacimientoET.getText().toString();
        String sexo_postulante = sexoSP.getSelectedItem().toString();
        String correo_postulante = correo_postulanteET.getText().toString();
        String telefono_postulante = telefono_postulanteET.getText().toString();
        String direccion_postulante = direccion_postulanteET.getText().toString();

        //Validación
        if (rut_postulante.equals("") ||
                pass_postulante.equals("") ||
                nombre_postulante.equals("") ||
                apellido_postulante.equals("") ||
                fecha_nacimiento.equals("") ||
                correo_postulante.equals("") ||
                telefono_postulante.equals("") ||
                direccion_postulante.equals("")) {
            //Mensaje personalizado ppara que ingrese todos los campos
            LayoutInflater infladorToast=getLayoutInflater();
            View vista_error=infladorToast.inflate(R.layout.toast_personalizado_error,(ViewGroup) findViewById(R.id.ll_toast_error));

            TextView mensajeTV=vista_error.findViewById(R.id.Toast_error_texto);
            mensajeTV.setText("Ingrese todos los campos");

            Toast mensajeError=new Toast(getApplicationContext());
            mensajeError.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER, 0, 200);
            mensajeError.setView(vista_error);
            mensajeError.show();


            //Toast mensaje=Toast.makeText(getApplicationContext(),"Ingrese todos los campos", Toast.LENGTH_SHORT);
            //mensaje.show();
            registrarBTN.setError("");
        }
        else {
            Postulante postobj=new Postulante();
            postobj.setRut_postulante(rut_postulante);
            Boolean prueba;
            prueba=postobj.validacion();
            if (prueba){
                //Buider de Retrofit
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                //Conexión para registrar en la Base de datos
                RetrofitConexion miConexion = retrofit.create(RetrofitConexion.class);
                Call<Boolean> call = miConexion.setPostulante(
                        rut_postulante,
                        pass_postulante,
                        nombre_postulante,
                        apellido_postulante,
                        fecha_nacimiento,
                        sexo_postulante,
                        correo_postulante,
                        telefono_postulante,
                        direccion_postulante,
                        id_comuna);
                call.enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, retrofit2.Response<Boolean> response) {
                        if (!response.isSuccessful()) {

                        }
                        Boolean respuesta = response.body();
                        if (respuesta) {
                            //Mensaje personalizado para edición correcta
                            LayoutInflater infladorToast=getLayoutInflater();
                            View vistaOk=infladorToast.inflate(R.layout.toast_personalizado,(ViewGroup) findViewById(R.id.ll_toast_ok));

                            TextView mensajeTV=vistaOk.findViewById(R.id.Toast_ok_texto);
                            mensajeTV.setText("Registro correcto");

                            Toast mensajeOk=new Toast(getApplicationContext());
                            mensajeOk.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER, 0, 200);
                            mensajeOk.setView(vistaOk);
                            mensajeOk.show();


                            //Toast mensaje=Toast.makeText(getApplicationContext(),"Registro correcto", Toast.LENGTH_SHORT);
                            //mensaje.show();
                            Intent miIntent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(miIntent);
                        } else {
                            //Mensaje personalizado para registro sin éxito
                            LayoutInflater infladorToast=getLayoutInflater();
                            View vista_error=infladorToast.inflate(R.layout.toast_personalizado_error,(ViewGroup) findViewById(R.id.ll_toast_error));

                            TextView mensajeTV=vista_error.findViewById(R.id.Toast_error_texto);
                            mensajeTV.setText("Registro sin éxito");

                            Toast mensajeError=new Toast(getApplicationContext());
                            mensajeError.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER, 0, 200);
                            mensajeError.setView(vista_error);
                            mensajeError.show();
                            //Toast mensaje=Toast.makeText(getApplicationContext(),"Registro fallido", Toast.LENGTH_SHORT);
                            //mensaje.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                        Toast mensaje=Toast.makeText(getApplicationContext(),"Error de conexion", Toast.LENGTH_SHORT);
                        mensaje.show();
                    }
                });
            }
            else {
                //Mensaje personalizado para rut inválido
                LayoutInflater infladorToast=getLayoutInflater();
                View vista_error=infladorToast.inflate(R.layout.toast_personalizado_error,(ViewGroup) findViewById(R.id.ll_toast_error));

                TextView mensajeTV=vista_error.findViewById(R.id.Toast_error_texto);
                mensajeTV.setText("Rut inválido");

                Toast mensajeError=new Toast(getApplicationContext());
                mensajeError.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER, 0, 200);
                mensajeError.setView(vista_error);
                mensajeError.show();


                //Toast mensaje=Toast.makeText(getApplicationContext(),"Rut invalido", Toast.LENGTH_SHORT);
                //mensaje.show();
            }
        }//fin else
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void ClickFecha(View view){
        if(view==fecha_nacimientoBTN){
            final Calendar calendario=Calendar.getInstance();
            dia=calendario.get(Calendar.DAY_OF_MONTH);
            mes=calendario.get(Calendar.MONTH);
            anio=calendario.get(Calendar.YEAR);

            DatePickerDialog miDatePD=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    fecha_nacimientoET.setText(year+"-"+(month+1)+"-"+dayOfMonth);
                }
            }
            ,dia,mes,anio);
            miDatePD.show();
        }
    }





}