package com.example.maijomv100.fragmentos;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;
import com.example.maijomv100.R;
import com.example.maijomv100.interfaces.RetrofitConexion;
import com.example.maijomv100.modelos.Ficha;
import com.example.maijomv100.modelos.Solicitud;
import com.example.maijomv100.recursos.TablaIntegrantes;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentoFicha#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentoFicha extends Fragment {

    Boolean solicitudAnterior=false;

    String rut_postulante;
    String id_ficha;
    TextView porcentajeTV;
    ProgressBar barraPB;
    TableLayout ficha_TL;
    TableLayout solicitudes_TL;
    String[]header={"Nombre","Parentezco","Edad"};
    String[]headerSolicitudes={"ID","Fecha","Estado"};
    ArrayList<String[]> filas=new ArrayList<>();
    ArrayList<String[]> filasSol=new ArrayList<>();
    View perfilFrag;
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

    public FragmentoFicha() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentoFicha.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentoFicha newInstance(String param1, String param2) {
        FragmentoFicha fragment = new FragmentoFicha();
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
        perfilFrag=inflater.inflate(R.layout.fragment_fragmento_ficha, container, false);
        barraPB=perfilFrag.findViewById(R.id.porcentaje_PB);
        porcentajeTV=perfilFrag.findViewById(R.id.porcentaje_TV);
        ficha_TL=perfilFrag.findViewById(R.id.ficha_TL);
        solicitudes_TL=perfilFrag.findViewById(R.id.solicitudes_TL);

        Bundle bundle = getActivity().getIntent().getExtras();
        if (bundle != null) {
            rut_postulante=bundle.getString("rut_postulante");
        }
        Retrofit miRetrofit=new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create()).build();
        miConexion=miRetrofit.create(RetrofitConexion.class);
        mostrarFichaBdd();
        barraPB.setProgress(0);

        return perfilFrag;
    }

    private void mostrarFichaBdd() {
        Call<List<Ficha>> call=miConexion.getFicha(rut_postulante);
        call.enqueue(new Callback<List<Ficha>>() {
            @Override
            public void onResponse(Call<List<Ficha>> call, Response<List<Ficha>> response) {
                if(!response.isSuccessful()) {
                }
                List<Ficha> miFicha=response.body();
                id_ficha=miFicha.get(0).getId_ficha();
                mostrarFicha(miFicha);
                mostrarSolicitudesBdd(id_ficha);
            }

            @Override
            public void onFailure(Call<List<Ficha>> call, Throwable t) {
                    porcentajeTV.setText("Usted no esta asociado a una ficha");
            }
        });
    }


    private void mostrarFicha(List<Ficha> miFicha) {
        int i;
        porcentajeTV.setText("Vulnerabilidad: "+miFicha.get(0).getCalificacion_se()+"%");
        barraPB.setProgress(miFicha.get(0).getCalificacion_se());

        TablaIntegrantes tablaficha=new TablaIntegrantes(ficha_TL,getActivity());
        tablaficha.AgregarHeader(header);

        for (i=0; i<miFicha.size();i++) {
            filas.add(new String[]{miFicha.get(i).getNombre_integrante(),miFicha.get(i).getParentezco_integrante(),""+miFicha.get(i).getEdad_integrante()});
        }
        tablaficha.AgregarDatos(filas,miFicha.size());
    }

    private void mostrarSolicitudesBdd(String id_ficha) {
        Call<List<Solicitud>> call=miConexion.getSolicitudes(id_ficha);
        call.enqueue(new Callback<List<Solicitud>>() {
            @Override
            public void onResponse(Call<List<Solicitud>> call, Response<List<Solicitud>> response) {
                if(!response.isSuccessful()) {
                }
                List<Solicitud> misSolicitudes=response.body();
                mostrarSolicitudes(misSolicitudes);
            }
            @Override
            public void onFailure(Call<List<Solicitud>> call, Throwable t) {

            }
        });
    }

    private void mostrarSolicitudes(List<Solicitud> misSolicitudes) {
        int i;
        TablaIntegrantes tablasolicitudes=new TablaIntegrantes(solicitudes_TL,getActivity());
        tablasolicitudes.AgregarHeader(headerSolicitudes);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        filasSol.clear();
        for (i=0; i<misSolicitudes.size();i++) {
            String fecha = formatter.format(Date.parse(misSolicitudes.get(i).getFecha_solicitud().toString()));
            filasSol.add(new String[]{""+misSolicitudes.get(i).getId_solicitud(),fecha,misSolicitudes.get(i).getNombre_estado()});
            if (misSolicitudes.get(i).getNombre_estado().toString().equals("Aprobada") ||
                misSolicitudes.get(i).getNombre_estado().toString().equals("En trámite")) {
                solicitudAnterior=true;
            }
        }
        tablasolicitudes.AgregarDatos(filasSol,misSolicitudes.size());

    }

}