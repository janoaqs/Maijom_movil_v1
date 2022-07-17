package com.example.maijomv100.vistas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maijomv100.MainActivity;
import com.example.maijomv100.R;
import com.example.maijomv100.interfaces.RetrofitConexion;
import com.example.maijomv100.modelos.Ficha;
import com.example.maijomv100.modelos.Solicitud;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetalleSubsidio extends AppCompatActivity {

    TextView nombreTV,tipoTV,detalleTV;
    TextView solicitudTV,edadTV,fichaTV;
    String nombre,tipo,detalle,rut,id_ficha;
    Button postularBTN;
    int edad,id_subsidio;
    Boolean solicitudAnterior=false;

    private final String url="http://172.20.10.3/API/movil/solicitudes/ficha/"; //Base de datos local
    //private final String url="http://maijombdd.000webhostapp.com/maijom/api/solicitudes/ficha/"; //Base de datos Pruebas
    //private final String url="https://maijom-web.000webhostapp.com/API/movil/solicitudes/ficha/"; //Base de datos compartida
    RetrofitConexion miConexion;

    private final String urlInsert="http://172.20.10.3/API/movil/solicitudes/solicitud/"; //Base de datos local
    //private final String urlInsert="http://maijombdd.000webhostapp.com/maijom/api/solicitudes/solicitud/"; //Base de datos Pruebas
    //private final String urlInsert="https://maijom-web.000webhostapp.com/API/movil/solicitudes/solicitud/"; //Base de datos compartida
    RetrofitConexion miConexionInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_subsidio);

        nombreTV=findViewById(R.id.nombreSub_TV);
        tipoTV=findViewById(R.id.tipoSub_TV);
        detalleTV=findViewById(R.id.detalleSub_TV);
        postularBTN=findViewById(R.id.postular_BTN);
        postularBTN.setEnabled(true);
        solicitudTV=findViewById(R.id.solicitudPendiente_TV);
        edadTV=findViewById(R.id.edad_TV);
        fichaTV=findViewById(R.id.ficha_TV);

        Intent miIntent=getIntent();
        nombre=miIntent.getStringExtra("nombre_subsidio");
        tipo=miIntent.getStringExtra("tipo_subsidio");
        detalle=miIntent.getStringExtra("descripcion_subsidio");
        rut=miIntent.getStringExtra("rut_postulante");
        id_ficha=miIntent.getStringExtra("id_ficha");
        id_subsidio=miIntent.getIntExtra("id_subsidio",0);
        edad=miIntent.getIntExtra("edad",0);

        nombreTV.setText(nombre);
        tipoTV.setText(tipo);
        detalleTV.setText(detalle);

        Retrofit miRetrofit=new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create()).build();
        miConexion=miRetrofit.create(RetrofitConexion.class);

        Retrofit miRetrofitInsert=new Retrofit.Builder()
                .baseUrl(urlInsert)
                .addConverterFactory(GsonConverterFactory.create()).build();
        miConexionInsert=miRetrofitInsert.create(RetrofitConexion.class);


        edadTV.setText("Usted cumple con la edad requerida("+edad+")");
        if (edad<18){
            postularBTN.setEnabled(false);
            edadTV.setText("Usted NO cumple con la edad requerida o NO tiene ficha");
        }

        fichaTV.setText("Usted SI ESTÁ asociado a una ficha");
        Log.d("jano","la id ficha es "+id_ficha);
        if (id_ficha.equals("NO")) {
            postularBTN.setEnabled(false);
            fichaTV.setText("Usted NO ESTÁ asociado a una ficha");
        }
        mostrarSubsidioBdd();
    }

    private void mostrarSubsidioBdd(){
        Call<List<Solicitud>> call=miConexion.getSolicitudes(id_ficha);
        call.enqueue(new Callback<List<Solicitud>>() {
            @Override
            public void onResponse(Call<List<Solicitud>> call, Response<List<Solicitud>> response) {
                if(!response.isSuccessful()) {
                }
                List<Solicitud> misSolicitudes=response.body();
                for (int i=0; i<misSolicitudes.size();i++) {
                    if (misSolicitudes.get(i).getNombre_estado().toString().equals("Aprobada") ||
                        misSolicitudes.get(i).getNombre_estado().toString().equals("En trámite")) {
                        solicitudAnterior=true;
                    }
                }
                if (solicitudAnterior==true) {
                    postularBTN.setEnabled(false);
                    solicitudTV.setText("Ustedes TIENE solicitud EN TRÁMITE o APROBADA");
                }
                else if(solicitudAnterior==false) {
                    solicitudTV.setText("Ustedes NO TIENE solicitud EN TRÁMITE o APROBADA");

                }
            }

            @Override
            public void onFailure(Call<List<Solicitud>> call, Throwable t) {
                    solicitudTV.setText("Ustedes NO TIENE solicitud EN TRÁMITE o APROBADA");

            }
        });
    }

    public void Click(View view){
        String fecha = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        String id_estado="E01";
        String id_serviu="S01";

        Call<Boolean> call=miConexionInsert.setSolicitud(
                fecha,id_estado,
                id_subsidio,
                id_ficha,
                id_serviu,
                rut);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean>response) {
                if(!response.isSuccessful()) {

                }else {
                    Boolean respuesta=response.body();
                    if (respuesta){
                        //Mensaje personalizado para postulación correcta
                        LayoutInflater infladorToast=getLayoutInflater();
                        View vistaOk=infladorToast.inflate(R.layout.toast_personalizado,(ViewGroup) findViewById(R.id.ll_toast_ok));

                        TextView mensajeTV=vistaOk.findViewById(R.id.Toast_ok_texto);
                        mensajeTV.setText("Postulación exitosa");

                        Toast mensajeOk=new Toast(getApplicationContext());
                        mensajeOk.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER, 0, 200);
                        mensajeOk.setView(vistaOk);
                        mensajeOk.show();
                    }
                    else {
                        //Mensaje personalizado para error en postulación
                        LayoutInflater infladorToast=getLayoutInflater();
                        View vista_error=infladorToast.inflate(R.layout.toast_personalizado_error,(ViewGroup) findViewById(R.id.ll_toast_error));

                        TextView mensajeTV=vista_error.findViewById(R.id.Toast_error_texto);
                        mensajeTV.setText("No se pudo postular");

                        Toast mensajeError=new Toast(getApplicationContext());
                        mensajeError.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER, 0, 200);
                        mensajeError.setView(vista_error);
                        mensajeError.show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast mensaje=Toast.makeText(getApplicationContext(),"Error de conexion", Toast.LENGTH_SHORT);
                mensaje.show();


                solicitudTV.setText("Ustedes NO TIENE solicitud pendiente o aprobada");

            }
        });
    }

}