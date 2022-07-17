package com.example.maijomv100.vistas;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maijomv100.R;
import com.squareup.picasso.Picasso;

public class DetalleNoticia extends AppCompatActivity {

    TextView tituloTV,detalleTV;
    ImageView imagenIV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_noticia);

        tituloTV=findViewById(R.id.tituloDetalle_TV);
        detalleTV=findViewById(R.id.detalle_TV);
        imagenIV=findViewById(R.id.imagenGrande_IV);
        Intent miIntent=getIntent();
        String titulo=miIntent.getStringExtra("titulo_noticia");
        String detalle=miIntent.getStringExtra("descripcion_noticia");

        String imagen="http://172.20.10.3/imagenes/"+miIntent.getStringExtra("imagen_noticia");

        Picasso.get()
                .load(imagen)
                .into(imagenIV);

        tituloTV.setText(titulo);
        detalleTV.setText(detalle);
    }
}