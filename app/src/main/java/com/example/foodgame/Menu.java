package com.example.foodgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class Menu extends AppCompatActivity {

    Button btPlay;
    Spinner spNivel;
    TextView pt_nombre;
    public int posNivel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Juego.gameOver=0;

        pt_nombre=findViewById(R.id.pt_nombre);

        /** INCIALIZA SPINER NIVEL **/
        spNivel = findViewById(R.id.sp_nivel);

        /** Declara array tipo Niveles con nombre niveles **/
        ArrayList <Niveles> niveles = new ArrayList<Niveles>();

        /** Añade datos al array **/
        niveles.add(new Niveles("1"));
        niveles.add(new Niveles("2"));
        niveles.add(new Niveles("3"));
        niveles.add(new Niveles("4"));
        niveles.add(new Niveles("5"));

        /** Declara adaptador tipo classe Niveles **/
        ArrayAdapter <Niveles> adapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,niveles);

        /** infla el espiner con los datos devueltos por adaptador adapter **/
        spNivel.setAdapter(adapter);

        /** Guarda la posiciónde  nivelSeleccionado en un int **/
        posNivel = spNivel.getSelectedItemPosition();


        /** INCIALIZA BOTON PLAY **/
        btPlay= findViewById(R.id.bt_play);
        /** Inicia Listener boton PLAY **/
        btPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /** Activa el juego **/
                Juego.play=1;

                /** Recoge el valor de posicion seleccionado espiner, multiplica x2 i passa a Int nivel Juego**/
                Juego.nivel=spNivel.getSelectedItemPosition()+1;

                /** Modifica el nombreStr de juego con el nombre introducido **/

                Juego.nombreStr = pt_nombre.getText().toString();

                // LLAMAMOS A LA ACTIVITY LISTADOFRUTAS

                startActivity(new Intent(Menu.this, MainActivity.class));

                Juego.play=1;

                MainActivity.mp3.start();

            }
        });

    }
}