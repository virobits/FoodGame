package com.example.foodgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class youWin extends AppCompatActivity {

    TextView tv_youWin;
    Button btContinuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_win);

        tv_youWin =findViewById(R.id.tv_youWin);
        btContinuar = findViewById(R.id.btContinuar);

        tv_youWin.setText("YOU WIN");


        /** Inicia Listener boton PLAY **/
        btContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // LLAMAMOS A LA ACTIVITY LISTADOFRUTAS
                startActivity(new Intent(youWin.this, Menu.class));

                Juego.nivel=1;
                Juego.puntuacion=0;
                Juego.play=0;

            }
        });
    }
}