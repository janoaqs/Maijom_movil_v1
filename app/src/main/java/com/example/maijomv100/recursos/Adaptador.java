package com.example.maijomv100.recursos;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.maijomv100.MainActivity;
import com.example.maijomv100.R;
import com.example.maijomv100.modelos.Noticia;
import com.example.maijomv100.vistas.DetalleNoticia;
import com.example.maijomv100.vistas.Inicio;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder> {
    LayoutInflater miInflador;
    ArrayList<Noticia> misNoticias;



    public Adaptador(Context contexto, ArrayList<Noticia> misNoticias) {
        this.miInflador = LayoutInflater.from(contexto);
        this.misNoticias = misNoticias;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View vista=miInflador.inflate(R.layout.vista_personalizada,parent,false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        String titulo=misNoticias.get(position).getTitulo_noticia();
        String fecha=misNoticias.get(position).getFecha_noticia().toString();
        String imagen="http://172.20.10.3/imagenes/"+misNoticias.get(position).getImagen_noticia();
        //String imagen="https://maijombdd.000webhostapp.com/maijom/imagenes/noticia_ejemplo.jpg";
        //String imagen="https://maijom-web.000webhostapp.com/imagenes/"+misNoticias.get(position).getImagen_noticia();

        Picasso.get()
                .load(imagen)
                .resize(100,100)
                .into(holder.imagenIV);
        holder.tituloTV.setText(titulo);
        holder.fechaTV.setText(fecha);

    }

    @Override
    public int getItemCount() {
        return misNoticias.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tituloTV, fechaTV;
        ImageView imagenIV;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent miIntent=new Intent(itemView.getContext(), DetalleNoticia.class);
                    miIntent.putExtra("titulo_noticia",misNoticias.get(getAdapterPosition()).getTitulo_noticia());
                    miIntent.putExtra("descripcion_noticia",misNoticias.get(getAdapterPosition()).getDescripcion_noticia());
                    miIntent.putExtra("imagen_noticia",misNoticias.get(getAdapterPosition()).getImagen_noticia());
                    view.getContext().startActivity(miIntent);
                }
            });
            tituloTV=itemView.findViewById(R.id.titulo_TV);
            fechaTV=itemView.findViewById(R.id.fecha_TV);
            imagenIV=itemView.findViewById(R.id.noticia_IMG);
        }
    }




}
