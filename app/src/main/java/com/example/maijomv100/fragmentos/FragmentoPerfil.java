package com.example.maijomv100.fragmentos;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentoPerfil#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentoPerfil extends Fragment {

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
    Button guardarEdicionBTN;
    private int dia,mes,anio;
    View perfilFrag;
    private final String url="http://172.20.10.3/API/movil/solicitudes/postulante/"; //Base de datos local
    //private final String url="https://maijombdd.000webhostapp.com/maijom/api/solicitudes/postulante/"; //Base de datos Pruebas
    //private final String url="https://maijom-web.000webhostapp.com/API/movil/solicitudes/postulante/"; //Base de datos compartida

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentoPerfil() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentoPerfil.
     */
    // TODO: Rename and change types and number odf parameters
    public static FragmentoPerfil newInstance(String param1, String param2) {
        FragmentoPerfil fragment = new FragmentoPerfil();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        perfilFrag= inflater.inflate(R.layout.fragment_fragmento_perfil, container, false);

        //Paso de la vista a variables
        rut_postulanteET=perfilFrag.findViewById(R.id.rut_ET);
        pass_postulanteET=perfilFrag.findViewById(R.id.pass_ET);
        nombre_postulanteET=perfilFrag.findViewById(R.id.nombre_ET);
        apellido_postulanteET=perfilFrag.findViewById(R.id.apellido_ET);
        fecha_nacimientoET=perfilFrag.findViewById(R.id.fNacimiento_ET);
        sexoSP=perfilFrag.findViewById(R.id.sexo_SP);
        sexoSP.setPrompt("Sexo");
        correo_postulanteET=perfilFrag.findViewById(R.id.correo_ET);
        telefono_postulanteET=perfilFrag.findViewById(R.id.telefono_ET);
        direccion_postulanteET=perfilFrag.findViewById(R.id.direccion_ET);
        comunaSP=perfilFrag.findViewById(R.id.comunas_SP);
        comunaSP.setPrompt("Comuna");
        guardarEdicionBTN=perfilFrag.findViewById(R.id.guardar_BTN);
        fecha_nacimientoBTN=perfilFrag.findViewById(R.id.fNacimiento_BTN);
        //llenado de combobox sexo
        String[] datosSexo = new String[] {"Masculino","Femenino","Otro"};
        ArrayAdapter<String> adapterSexo = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, datosSexo);
        sexoSP.setAdapter(adapterSexo);
        //llenado de combobox comunas
        String[] datosComunas = new String[] {"Concepcion","Chiguayante","Coronel",
                "Florida","Hualpen", "Hualqui","Lota","Penco","San pedro de la Paz",
                "Santa Juana","Talcahuano", "Tome"};
        ArrayAdapter<String> adapterComunas = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, datosComunas);
        comunaSP.setAdapter(adapterComunas);

        Bundle bundle = getActivity().getIntent().getExtras();
        if (bundle != null) {
            rut_postulanteET.setText(bundle.getString("rut_postulante"));
            pass_postulanteET.setText(bundle.getString("pass_postulante"));
            nombre_postulanteET.setText(bundle.getString("nombre_postulante"));
            apellido_postulanteET.setText(bundle.getString("apellido_postulante"));
            fecha_nacimientoET.setText(bundle.getSerializable("fecha_nacimiento").toString());
            correo_postulanteET.setText(bundle.getString("correo_postulante"));
            telefono_postulanteET.setText(bundle.getString("telefono_postulante"));
            direccion_postulanteET.setText(bundle.getString("direccion_postulante"));
        }

        //Setear combobox de la comuna
        if(bundle.getString("id_comuna").equals("CM1")){
            comunaSP.setSelection(0);
        }
        if(bundle.getString("id_comuna").equals("CM2")){
            comunaSP.setSelection(1);
        }
        if(bundle.getString("id_comuna").equals("CM3")){
            comunaSP.setSelection(2);
        }
        if(bundle.getString("id_comuna").equals("CM4")){
            comunaSP.setSelection(3);
        }
        if(bundle.getString("id_comuna").equals("CM5")){
            comunaSP.setSelection(4);
        }
        if(bundle.getString("id_comuna").equals("CM6")){
            comunaSP.setSelection(5);
        }
        if(bundle.getString("id_comuna").equals("CM7")){
            comunaSP.setSelection(6);
        }
        if(bundle.getString("id_comuna").equals("CM8")){
            comunaSP.setSelection(7);
        }
        if(bundle.getString("id_comuna").equals("CM9")){
            comunaSP.setSelection(8);
        }
        if(bundle.getString("id_comuna").equals("CM10")){
            comunaSP.setSelection(9);
        }
        if(bundle.getString("id_comuna").equals("CM11")){
            comunaSP.setSelection(10);
        }
        if(bundle.getString("id_comuna").equals("CM12")){
            comunaSP.setSelection(11);
        }
        //Setear combobox del sexo
        if(bundle.getString("sexo_postulante").equals("Masculino")){
            sexoSP.setSelection(0);
        }
        if(bundle.getString("sexo_postulante").equals("Femenino")){
            sexoSP.setSelection(1);
        }
        if(bundle.getString("sexo_postulante").equals("Otro")){
            sexoSP.setSelection(2);
        }

        guardarEdicionBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


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

                if (rut_postulante.equals("") ||
                        pass_postulante.equals("") ||
                        nombre_postulante.equals("") ||
                        apellido_postulante.equals("") ||
                        fecha_nacimiento.equals("") ||
                        correo_postulante.equals("") ||
                        telefono_postulante.equals("") ||
                        direccion_postulante.equals("")) {
                    guardarEdicionBTN.setError("Ingrese todos los campos");
                    Toast mensaje=Toast.makeText(getActivity(),"Ingrese todos los campos", Toast.LENGTH_SHORT);
                    mensaje.show();
                }
                else {
                    Gson fechaFormato = new GsonBuilder()
                            .setDateFormat("yyyy-MM-dd")
                            .create();

                    Retrofit retrofit=new Retrofit.Builder()
                            .baseUrl(url)
                            .addConverterFactory(GsonConverterFactory.create(fechaFormato))
                            .build();

                    RetrofitConexion miConexion=retrofit.create(RetrofitConexion.class);
                    Call<Boolean> call=miConexion.editPostulante(
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
                            if (!response.isSuccessful()){
                            }
                            Boolean respuesta=response.body();
                            if (respuesta) {
                                Toast mensaje=Toast.makeText(getActivity(),"Edicion correcto", Toast.LENGTH_SHORT);
                                mensaje.show();
                            } else {
                                Toast mensaje=Toast.makeText(getActivity(),"Edicion fallida", Toast.LENGTH_SHORT);
                                mensaje.show();
                            }
                        }
                        @Override
                        public void onFailure(Call<Boolean> call, Throwable t) {
                            Toast mensaje=Toast.makeText(getActivity(),"Error de conexion", Toast.LENGTH_SHORT);
                            mensaje.show();
                        }
                    });
                }
            }
        });
        fecha_nacimientoBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v==fecha_nacimientoBTN){
                    final Calendar calendario=Calendar.getInstance();
                    dia=calendario.get(Calendar.DAY_OF_MONTH);
                    mes=calendario.get(Calendar.MONTH);
                    anio=calendario.get(Calendar.YEAR);
                    DatePickerDialog miDatePD=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            fecha_nacimientoET.setText(year+"-"+(month+1)+"-"+dayOfMonth);
                        }
                    },dia,mes,anio);
                    miDatePD.show();
                }
            }
        });

        return perfilFrag;
    }




}