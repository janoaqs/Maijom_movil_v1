package com.example.maijomv100.fragmentos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.maijomv100.R;
import com.example.maijomv100.interfaces.RetrofitConexion;
import com.example.maijomv100.modelos.Ficha;
import com.example.maijomv100.modelos.Proyecto;
import com.example.maijomv100.modelos.Subsidio;
import com.example.maijomv100.recursos.AdaptadorProyectos;
import com.example.maijomv100.recursos.AdaptadorSubsidios;
import com.example.maijomv100.recursos.TablaIntegrantes;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentoPostula#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentoPostula extends Fragment {

    RecyclerView subsidiosRV;
    AdaptadorSubsidios adaptadorSub;
    ArrayList<Subsidio> subsidiosLista=new ArrayList<>();
    private final String urlSub="http://172.20.10.3/API/movil/solicitudes/subsidio/"; //Base de datos local
    //private final String urlSub="http://maijombdd.000webhostapp.com/maijom/api/solicitudes/subsidio/"; //Base de datos Pruebas
    //private final String urlSub="https://maijom-web.000webhostapp.com/API/movil/solicitudes/subsidio/";  //Base de datos compartida
    RetrofitConexion miConexionSub;

    String rut_postulante;
    String id_ficha="NO";
    int edad;

    Boolean requisitos=true;
    View perfilFrag;

    TextView poseeFichaTV,mayorDeEdadTV;
    ArrayList<Ficha> ficha=new ArrayList<>();
    private final String url="http://172.20.10.3/API/movil/solicitudes/ficha/"; //Base de datos local
    //private final String url="http://maijombdd.000webhostapp.com/maijom/api/solicitudes/ficha/"; //Base de datos Pruebas
    //private final String url="https://maijom-web.000webhostapp.com/API/movil/solicitudes/ficha/"; //Base de datos compartida
    RetrofitConexion miConexion;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentoPostula() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentoPostula.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentoPostula newInstance(String param1, String param2) {
        FragmentoPostula fragment = new FragmentoPostula();
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
        perfilFrag=inflater.inflate(R.layout.fragment_fragmento_postula, container, false);
        poseeFichaTV=perfilFrag.findViewById(R.id.poseeFicha_TV);
        mayorDeEdadTV=perfilFrag.findViewById(R.id.esMayor_TV);
        subsidiosRV=perfilFrag.findViewById(R.id.subsidios_RV);

        Bundle bundle = getActivity().getIntent().getExtras();
        if (bundle != null) {
            rut_postulante=bundle.getString("rut_postulante");
        }

        Retrofit miRetrofit=new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create()).build();
        miConexion=miRetrofit.create(RetrofitConexion.class);
        mostrarFichaBdd();
        Retrofit miRetrofitSub=new Retrofit.Builder()
                .baseUrl(urlSub)
                .addConverterFactory(GsonConverterFactory.create()).build();
        miConexionSub=miRetrofitSub.create(RetrofitConexion.class);
        //mostrarSubsidioBdd();

        return perfilFrag;
    }

    private void mostrarFichaBdd() {
        Call<List<Ficha>> call=miConexion.getEdad(rut_postulante);
        call.enqueue(new Callback<List<Ficha>>() {
            @Override
            public void onResponse(Call<List<Ficha>> call, Response<List<Ficha>> response) {
                if(!response.isSuccessful()) {
                }
                ficha.clear();
                List<Ficha> miFicha=response.body();
                poseeFichaTV.setText("Ficha: ASOCIADO");

                edad=miFicha.get(0).getEdad_integrante();
                id_ficha=miFicha.get(0).getId_ficha();
                Log.d("FICHA",id_ficha);
                if (edad>17) {
                    mayorDeEdadTV.setText("Mayor de edad: SI ("+edad+")");
                }else {
                    mayorDeEdadTV.setText("Mayor de edad: NO ("+edad+")");
                }
                mostrarSubsidioBdd();
            }

            @Override
            public void onFailure(Call<List<Ficha>> call, Throwable t) {
                poseeFichaTV.setText("Ficha: NO ASOCIADO A FICHA");
                mayorDeEdadTV.setText("Mayor de edad: NO ASOCIADO A FICHA");
                mostrarSubsidioBdd();
            }
        });
    }

    private void mostrarSubsidioBdd(){

        Call<List<Subsidio>> call=miConexionSub.getSubsidios();
        call.enqueue(new Callback<List<Subsidio>>() {
            @Override
            public void onResponse(Call<List<Subsidio>> call, Response<List<Subsidio>> response) {
                if(!response.isSuccessful()) {
                }
                subsidiosLista.clear();
                List<Subsidio> subsidios=response.body();
                for (Subsidio subsidio: subsidios){
                    subsidiosLista.add(subsidio);
                }
                mostrarSubsidios();
            }

            @Override
            public void onFailure(Call<List<Subsidio>> call, Throwable t) {

            }
        });

    }

    private void mostrarSubsidios(){
        subsidiosRV.setLayoutManager(new LinearLayoutManager(getActivity()));
        Log.d("FICHA2",id_ficha);
        adaptadorSub=new AdaptadorSubsidios(getActivity(), subsidiosLista,rut_postulante,id_ficha,edad);
        subsidiosRV.setAdapter(adaptadorSub);
    }

}