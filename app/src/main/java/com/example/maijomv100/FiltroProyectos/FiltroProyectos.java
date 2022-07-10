package com.example.maijomv100.FiltroProyectos;
import android.widget.Filter;
import com.example.maijomv100.modelos.Proyecto;
import com.example.maijomv100.recursos.AdaptadorProyectos;

import java.util.ArrayList;

public class FiltroProyectos extends Filter {
    ArrayList<Proyecto> listaFiltrada;
    AdaptadorProyectos adaptadorProyectos;

    public FiltroProyectos(ArrayList<Proyecto> listaFiltrada, AdaptadorProyectos adaptadorProyectos) {
        this.listaFiltrada = listaFiltrada;
        this.adaptadorProyectos = adaptadorProyectos;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults misResultados=new FilterResults();
        if (constraint!=null && constraint.length()>0){
            constraint=constraint.toString().toUpperCase();
            ArrayList<Proyecto> proyectosFiltrado=new ArrayList();
            for (int i=0;i<listaFiltrada.size();i++) {
                if(listaFiltrada.get(i).getNombre_comuna().toUpperCase().contains(constraint)) {
                    proyectosFiltrado.add(listaFiltrada.get(i));
                }
            }
            misResultados.count=proyectosFiltrado.size();
            misResultados.values=proyectosFiltrado;
        }
        else {
            misResultados.count=listaFiltrada.size();
            misResultados.values=listaFiltrada;
        }
        return misResultados;
    }


    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adaptadorProyectos.misProyectos=(ArrayList<Proyecto>)results.values;
        adaptadorProyectos.notifyDataSetChanged();
    }
}
