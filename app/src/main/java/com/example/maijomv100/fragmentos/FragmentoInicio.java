package com.example.maijomv100.fragmentos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.maijomv100.R;
import com.example.maijomv100.interfaces.RetrofitConexion;
import com.example.maijomv100.modelos.Noticia;
import com.example.maijomv100.recursos.Adaptador;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentoInicio#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentoInicio extends Fragment {

    RecyclerView noticiasRV;
    Adaptador adaptador;
    View perfilFrag;
    ArrayList<Noticia> noticiasLista=new ArrayList<>();
    private final String url="http://172.20.10.3/API/movil/solicitudes/noticias/";
    //private final String url="https://maijombdd.000webhostapp.com/maijom/api/solicitudes/noticias/";
    //private final String url="https://maijom-web.000webhostapp.com/API/movil/solicitudes/noticias/";
    RetrofitConexion miConexion;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentoInicio() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentoInicio.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentoInicio newInstance(String param1, String param2) {
        FragmentoInicio fragment = new FragmentoInicio();
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
        perfilFrag=inflater.inflate(R.layout.fragment_fragmento_inicio, container, false);
        noticiasRV=perfilFrag.findViewById(R.id.noticias_RV);
        Retrofit miRetrofit=new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create()).build();
        miConexion=miRetrofit.create(RetrofitConexion.class);
        mostrarNoticiasBdd();

        return perfilFrag;
    }

    private void mostrarNoticiasBdd() {
        Call<List<Noticia>> call=miConexion.getNoticias();
        call.enqueue(new Callback<List<Noticia>>() {
            @Override
            public void onResponse(Call<List<Noticia>> call, Response<List<Noticia>> response) {
                if(!response.isSuccessful()) {
                }
                noticiasLista.clear();
                List<Noticia> noticias=response.body();
                for (Noticia noticia: noticias){
                    noticiasLista.add(noticia);
                }
                mostrarNoticias();
            }
            @Override
            public void onFailure(Call<List<Noticia>> call, Throwable t) {
                Toast mensaje=Toast.makeText(getActivity(),"Error de conexion", Toast.LENGTH_SHORT);
                mensaje.show();
            }
        });
    }

    private void mostrarNoticias() {
        noticiasRV.setLayoutManager(new LinearLayoutManager(getActivity()));
        adaptador=new Adaptador(getActivity(), noticiasLista);
        noticiasRV.setAdapter(adaptador);
    }

}