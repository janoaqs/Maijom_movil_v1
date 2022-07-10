package com.example.maijomv100.recursos;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.maijomv100.R;
import com.example.maijomv100.modelos.Subsidio;
import com.example.maijomv100.vistas.DetalleNoticia;
import com.example.maijomv100.vistas.DetalleSubsidio;

import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;


public class AdaptadorSubsidios extends RecyclerView.Adapter<AdaptadorSubsidios.ViewHolder> {

    LayoutInflater miInflador;
    ArrayList<Subsidio> misSubsidios;
    String rut_postulante;
    String id_ficha;
    int edad;

    public AdaptadorSubsidios(Context contexto, ArrayList<Subsidio> misSubsidios,String rut_postulante,String id_ficha,int edad) {
        this.miInflador = LayoutInflater.from(contexto);
        this.misSubsidios = misSubsidios;
        this.rut_postulante=rut_postulante;
        this.id_ficha=id_ficha;
        this.edad=edad;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View vista=miInflador.inflate(R.layout.vista_subsidios,parent,false);
        Log.d("jano","Aca");
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        String nombre=misSubsidios.get(position).getNombre_subsidio();
        String tipo=misSubsidios.get(position).getTipo_subsidio();


        Log.d("jano",nombre);
        holder.nombreTV.setText("Subsidio: "+nombre);
        holder.tipoTV.setText("Tipo: "+tipo);
    }

    @Override
    public int getItemCount() {
        return misSubsidios.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nombreTV,tipoTV;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent miIntent=new Intent(itemView.getContext(), DetalleSubsidio.class);
                    miIntent.putExtra("nombre_subsidio",misSubsidios.get(getAdapterPosition()).getNombre_subsidio());
                    miIntent.putExtra("tipo_subsidio",misSubsidios.get(getAdapterPosition()).getTipo_subsidio());
                    miIntent.putExtra("descripcion_subsidio",misSubsidios.get(getAdapterPosition()).getDescripcion_subsidio());
                    miIntent.putExtra("id_subsidio",misSubsidios.get(getAdapterPosition()).getId_subsidio());

                    miIntent.putExtra("rut_postulante",rut_postulante);
                    miIntent.putExtra("id_ficha",id_ficha);
                    miIntent.putExtra("edad",edad);
                    view.getContext().startActivity(miIntent);
                }
            });

            nombreTV=itemView.findViewById(R.id.nombreSub_TV);
            tipoTV=itemView.findViewById(R.id.tipoSub_TV);
        }
    }
}
