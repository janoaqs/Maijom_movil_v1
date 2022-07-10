package com.example.maijomv100.fragmentos;

import android.app.ActionBar;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.maijomv100.R;
import com.example.maijomv100.interfaces.RetrofitConexion;
import com.example.maijomv100.modelos.Noticia;
import com.example.maijomv100.modelos.Proyecto;
import com.example.maijomv100.recursos.Adaptador;
import com.example.maijomv100.recursos.AdaptadorProyectos;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentoProyecto#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentoProyecto extends Fragment {

    RecyclerView proyectosRV;
    AdaptadorProyectos adaptador;
    View perfilFrag;
    ArrayList<Proyecto> proyectosLista=new ArrayList<>();
    private final String url="http://172.20.10.3/API/movil/solicitudes/proyecto/"; //Base de datos local
    //private final String url=("https://maijombdd.000webhostapp.com/maijom/api/solicitudes/proyecto/");//Base de datos Pruebas
    //private final String url="https://maijom-web.000webhostapp.com/API/movil/solicitudes/proyecto/"; //Base de datos compartida
    RetrofitConexion miConexion;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentoProyecto() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentoProyecto.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentoProyecto newInstance(String param1, String param2) {
        FragmentoProyecto fragment = new FragmentoProyecto();
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
        perfilFrag=inflater.inflate(R.layout.fragment_fragmento_proyecto, container, false);
        proyectosRV=perfilFrag.findViewById(R.id.proyectos_RV);
        Retrofit miRetrofit=new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create()).build();
        miConexion=miRetrofit.create(RetrofitConexion.class);
        mostrarProyectosBdd();
        setHasOptionsMenu(true);
        ActionBar actionBar = getActivity().getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false); // remove the left caret
        }

        return perfilFrag;
    }

    private void mostrarProyectosBdd() {
        Call<List<Proyecto>> call=miConexion.getProyectos();
        call.enqueue(new Callback<List<Proyecto>>() {
            @Override
            public void onResponse(Call<List<Proyecto>> call, Response<List<Proyecto>> response) {
                if(!response.isSuccessful()) {
                }
                proyectosLista.clear();
                List<Proyecto> proyectos=response.body();
                for (Proyecto proyecto: proyectos){
                    proyectosLista.add(proyecto);
                }
                mostrarProyectos();
            }
            @Override
            public void onFailure(Call<List<Proyecto>> call, Throwable t) {

            }
        });
    }

    private void mostrarProyectos() {
        proyectosRV.setLayoutManager(new LinearLayoutManager(getActivity()));
        adaptador=new AdaptadorProyectos(getActivity(), proyectosLista);
        proyectosRV.setAdapter(adaptador);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull @NotNull Menu menu, @NonNull @NotNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_filtro_proyectos,menu);

        MenuItem item=menu.findItem(R.id.buscarProyecto);
        SearchView searchView=(SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adaptador.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adaptador.getFilter().filter(newText);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }
}