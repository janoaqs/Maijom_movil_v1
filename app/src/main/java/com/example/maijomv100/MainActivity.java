package com.example.maijomv100;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maijomv100.interfaces.RetrofitConexion;
import com.example.maijomv100.modelos.Postulante;
import com.example.maijomv100.vistas.Inicio;
import com.example.maijomv100.vistas.Registrar;

import java.io.Console;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    //Creador Oscar Quintana

    EditText rutET;
    EditText passET;
    private final String url="http://172.20.10.3/API/movil/solicitudes/postulante/"; //Base de datos local
    //private final String url="https://maijombdd.000webhostapp.com/maijom/api/solicitudes/postulante/"; //Base de datos Pruebas
    //private final String url="https://maijom-web.000webhostapp.com/API/movil/solicitudes/postulante/"; //Base de datos compartida

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Paso de vista de textpo del diseño a la variable
        rutET=findViewById(R.id.rut_ET);
        passET=findViewById(R.id.pass_ET);

    }

    //Click en el boton Iniciar Sesion
    public void ClickValidar(View vista){
        //Validacion campos vacions
        rutET.setError(null);
        passET.setError(null);
        String rutVal=rutET.getText().toString();
        String passVal=passET.getText().toString();

        if (rutVal.equals("") && passVal.equals("")) {
            rutET.setError("Intruduce un rut");
            passET.setError("Introduce la contraseña");
            //Mensaje personalizado ppara que ingrese todos los campos
            LayoutInflater infladorToast=getLayoutInflater();
            View vista_error=infladorToast.inflate(R.layout.toast_personalizado_error,(ViewGroup) findViewById(R.id.ll_toast_error));

            TextView mensajeTV=vista_error.findViewById(R.id.Toast_error_texto);
            mensajeTV.setText("Ingrese todos los campos");

            Toast mensajeError=new Toast(getApplicationContext());
            mensajeError.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER, 0, 200);
            mensajeError.setView(vista_error);
            mensajeError.show();
            return;
        }
        else if (rutVal.equals("")) {
            rutET.setError("Intruduce un rut");
            //Mensaje personalizado ppara que ingrese todos los campos
            LayoutInflater infladorToast=getLayoutInflater();
            View vista_error=infladorToast.inflate(R.layout.toast_personalizado_error,(ViewGroup) findViewById(R.id.ll_toast_error));

            TextView mensajeTV=vista_error.findViewById(R.id.Toast_error_texto);
            mensajeTV.setText("Ingrese todos los campos");

            Toast mensajeError=new Toast(getApplicationContext());
            mensajeError.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER, 0, 200);
            mensajeError.setView(vista_error);
            mensajeError.show();
            return;
        }
        else if (passVal.equals("")) {
            passET.setError("Introduce la contraseña");
            //Mensaje personalizado ppara que ingrese todos los campos
            LayoutInflater infladorToast=getLayoutInflater();
            View vista_error=infladorToast.inflate(R.layout.toast_personalizado_error,(ViewGroup) findViewById(R.id.ll_toast_error));

            TextView mensajeTV=vista_error.findViewById(R.id.Toast_error_texto);
            mensajeTV.setText("Ingrese todos los campos");

            Toast mensajeError=new Toast(getApplicationContext());
            mensajeError.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER, 0, 200);
            mensajeError.setView(vista_error);
            mensajeError.show();
            return;
        }
        else {
            Postulante postobj=new Postulante();
            postobj.setRut_postulante(rutVal);
            Boolean prueba;
            prueba=postobj.validacion();
            if (prueba){
                Log.d("a","Bueno "+prueba);
                //Llamada al método para validar postulante
                ValidarUsuario();
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

                //Toast mensaje=Toast.makeText(getApplicationContext(),"Rut inválido", Toast.LENGTH_SHORT);
                //mensaje.show();
            }

        }
    }




    //Validar usuario en la BDD
    private void ValidarUsuario(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitConexion miConexion=retrofit.create(RetrofitConexion.class);
        Call<List<Postulante>> call=miConexion.getPostulante(rutET.getText().toString(),passET.getText().toString());
        call.enqueue(new Callback<List<Postulante>>() {
            @Override
            public void onResponse(Call<List<Postulante>> call, retrofit2.Response<List<Postulante>> response) {
                if (!response.isSuccessful()){
                }

                List<Postulante> misPostulante=response.body();
                String rutPostulante=misPostulante.get(0).getRut_postulante().toString();
                String passPostulante=misPostulante.get(0).getPass_postulante().toString();
                String nombrePostulante=misPostulante.get(0).getNombre_postulante().toString();
                String apellidoPostulante=misPostulante.get(0).getApellido_postulante().toString();
                Date fechaPostulante=misPostulante.get(0).getFecha_nacimiento();
                String sexoPostulante=misPostulante.get(0).getSexo_postulante().toString();
                String correoPostulante=misPostulante.get(0).getCorreo_postulante().toString();
                String telefonoPostulante=misPostulante.get(0).getTelefono_postulante().toString();
                String direccionPostulante=misPostulante.get(0).getDireccion_postulante().toString();
                String comunaPostulante=misPostulante.get(0).getId_comuna().toString();

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String fecha = formatter.format(Date.parse(fechaPostulante.toString()));


                //Mensaje personalizado cuando las credenciales son correctas
                LayoutInflater infladorToast=getLayoutInflater();
                View vistaOk=infladorToast.inflate(R.layout.toast_personalizado,(ViewGroup) findViewById(R.id.ll_toast_ok));

                TextView mensajeTV=vistaOk.findViewById(R.id.Toast_ok_texto);
                mensajeTV.setText("Bienvenido "+nombrePostulante);

                Toast mensajeOk=new Toast(getApplicationContext());
                mensajeOk.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER, 0, 200);
                mensajeOk.setView(vistaOk);
                mensajeOk.show();
                //Toast mensaje=Toast.makeText(getApplicationContext(),"Bienvenido "+nombrePostulante, Toast.LENGTH_SHORT);
                //mensaje.show();

                Intent miIntent=new Intent(getApplicationContext(),Inicio.class);

                miIntent.putExtra("rut_postulante",rutPostulante);
                miIntent.putExtra("pass_postulante",passPostulante);
                miIntent.putExtra("nombre_postulante",nombrePostulante);
                miIntent.putExtra("apellido_postulante",apellidoPostulante);
                miIntent.putExtra("fecha_nacimiento",fecha);
                miIntent.putExtra("sexo_postulante",sexoPostulante);
                miIntent.putExtra("correo_postulante",correoPostulante);
                miIntent.putExtra("telefono_postulante",telefonoPostulante);
                miIntent.putExtra("direccion_postulante",direccionPostulante);
                miIntent.putExtra("id_comuna",comunaPostulante);
                startActivity(miIntent);
            }
            @Override
            public void onFailure(Call<List<Postulante>> call, Throwable t) {
                //Mensaje personalizado para credenciales incorrectas
                LayoutInflater infladorToast=getLayoutInflater();
                View vista_error=infladorToast.inflate(R.layout.toast_personalizado_error,(ViewGroup) findViewById(R.id.ll_toast_error));

                TextView mensajeTV=vista_error.findViewById(R.id.Toast_error_texto);
                mensajeTV.setText("Credenciales incorrectas");

                Toast mensajeError=new Toast(getApplicationContext());
                mensajeError.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER, 0, 200);
                mensajeError.setView(vista_error);
                mensajeError.show();



                //Toast mensaje=Toast.makeText(getApplicationContext(),"Credenciales incorrectas", Toast.LENGTH_SHORT);
                Log.d("falla",""+t);
                //mensaje.show();
            }
        });
    }

    //Ir al activity de registrar
    public void IraRegistrar(View vista){
        Intent miIntent=new Intent(getApplicationContext(), Registrar.class);
        startActivity(miIntent);
    }

}