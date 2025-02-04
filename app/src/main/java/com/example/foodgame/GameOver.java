package com.example.foodgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class GameOver extends AppCompatActivity {
    Button btContinuar;
    static TextView tv_GameOver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        tv_GameOver =findViewById(R.id.tvGameOver);
        btContinuar = findViewById(R.id.btContinuar);

            tv_GameOver.setText("GAME OVER");


        /** Inicia Listener boton PLAY **/
        btContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // LLAMAMOS A LA ACTIVITY LISTADOFRUTAS
                startActivity(new Intent(GameOver.this, Menu.class));

                Juego.nivel=1;
                Juego.puntuacion=0;
                Juego.play=0;

            }
        });


    }
}