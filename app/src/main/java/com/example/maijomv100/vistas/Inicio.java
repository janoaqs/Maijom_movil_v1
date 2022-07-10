package com.example.maijomv100.vistas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import com.example.maijomv100.R;
import com.example.maijomv100.fragmentos.FragmentoFicha;
import com.example.maijomv100.fragmentos.FragmentoInicio;
import com.example.maijomv100.fragmentos.FragmentoPerfil;
import com.example.maijomv100.fragmentos.FragmentoPostula;
import com.example.maijomv100.fragmentos.FragmentoProyecto;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import org.jetbrains.annotations.NotNull;

public class Inicio extends AppCompatActivity {

    FragmentoInicio miFragInicio=new FragmentoInicio();
    FragmentoProyecto miFragProyecto=new FragmentoProyecto();
    FragmentoFicha miFragFicha=new FragmentoFicha();
    FragmentoPostula miFragPostula=new FragmentoPostula();
    FragmentoPerfil miFragPerfil=new FragmentoPerfil();
    TextView usuarioTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        //Asignar la vista de menu navegación a una variable
        BottomNavigationView navegacion=findViewById(R.id.navegacion_BNV);
        navegacion.setOnNavigationItemSelectedListener(listener);
        //Asigar la caja de texto del nombre a una variable
        usuarioTV=findViewById(R.id.usuario_TV);
        //Iniciar con el primer fragmento
        CargarFragmento(miFragInicio);
        //Guardar el rut que viene como 'Extra'
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String usuario;
            usuario=""+bundle.getString("nombre_postulante")+" "+bundle.getString("apellido_postulante");
            usuarioTV.setText(usuario);
        }
    }

    //Listener para cambiar entre fragmentos
    private final BottomNavigationView.OnNavigationItemSelectedListener listener=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.fragmentoInicio_FRG:
                    CargarFragmento(miFragInicio);
                    return true;
                case R.id.fragmentoProyectos_FRG:
                    CargarFragmento(miFragProyecto);
                    return true;
                case R.id.fragmentoPerfil_FRG:
                    CargarFragmento(miFragPerfil);
                    return true;
                case R.id.fragmentoFicha_FRG:
                    CargarFragmento(miFragFicha);
                    return true;
                case R.id.fragmentoPostula_FRG:
                    CargarFragmento(miFragPostula);
                    return true;
            }
            return false;
        }
    };

    public void CargarFragmento(Fragment fragmento){
        FragmentTransaction transaccion=getSupportFragmentManager().beginTransaction();
        transaccion.replace(R.id.contenedor_FL,fragmento);
        transaccion.commit();
    }




}