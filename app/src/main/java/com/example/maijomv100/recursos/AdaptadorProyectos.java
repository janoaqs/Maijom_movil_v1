package com.example.maijomv100.recursos;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maijomv100.FiltroProyectos.FiltroProyectos;
import com.example.maijomv100.R;
import com.example.maijomv100.modelos.Noticia;
import com.example.maijomv100.modelos.Proyecto;
import com.example.maijomv100.vistas.DetalleNoticia;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AdaptadorProyectos extends RecyclerView.Adapter<AdaptadorProyectos.ViewHolder> implements Filterable {
    LayoutInflater miInflador;
    public ArrayList<Proyecto> misProyectos,listaFiltrada;
    FiltroProyectos filtro;

    public AdaptadorProyectos(Context contexto, ArrayList<Proyecto> misProyectos) {
        this.miInflador = LayoutInflater.from(contexto);
        this.misProyectos = misProyectos;
        this.listaFiltrada=misProyectos;
    }
    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View vista=miInflador.inflate(R.layout.vista_proyectos,parent,false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        String nombre=misProyectos.get(position).getNombre_proyecto();
        String direccion=misProyectos.get(position).getDireccion_proyecto();
        String telefono=misProyectos.get(position).getTelefono_proyecto();
        int casa=misProyectos.get(position).getCasas_disponibles();
        String inmobiliaria=misProyectos.get(position).getNombre_inmobiliaria();
        String comuna=misProyectos.get(position).getNombre_comuna();
        String subsidio=misProyectos.get(position).getNombre_subsidio();

        Log.d("jano",nombre);
        holder.nombreTV.setText("Proyecto: "+nombre);
        holder.direccionTV.setText("Dirección: "+direccion);
        holder.telefonoTV.setText("Telefono: "+telefono);
        holder.casaTV.setText("Casas disponibles: "+casa);
        holder.inmobiliariaTV.setText("Inmobiliaria: "+inmobiliaria);
        holder.comunaTV.setText("Comuna: "+comuna);
        holder.subsidioTV.setText("Subsidio asociado: "+subsidio);

    }

    @Override
    public int getItemCount() {
        return misProyectos.size();
    }

    @Override
    public Filter getFilter() {
        if (filtro==null){
            filtro=new FiltroProyectos(listaFiltrada,this);
        }
        return filtro;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nombreTV,direccionTV,telefonoTV,casaTV,inmobiliariaTV,comunaTV,subsidioTV;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            nombreTV=itemView.findViewById(R.id.nombre_TV);
            direccionTV=itemView.findViewById(R.id.direccion_TV);
            telefonoTV=itemView.findViewById(R.id.telefono_TV);
            casaTV=itemView.findViewById(R.id.casas_TV);
            inmobiliariaTV=itemView.findViewById(R.id.inmobiliaria_TV);
            comunaTV=itemView.findViewById(R.id.comuna_TV);
            subsidioTV=itemView.findViewById(R.id.subsidio_TV);
        }
    }


}
