package com.example.foodgame;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;

import javax.security.auth.Destroyable;


public class MainActivity extends AppCompatActivity {

    public Juego juego;

    static String comentFinal;

    private Handler handler = new Handler();

    /** variables sonido **/
    static MediaPlayer mp;
    static MediaPlayer mp2;
    static MediaPlayer mp3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** si variable play es 0 ejecuta el menu **/
        if(Juego.play==0) {
            startActivity(new Intent(MainActivity.this, Menu.class));
        }

        setContentView(R.layout.activity_main);


        /** Establece los TextView **/


        TextView tvPuntos = findViewById(R.id.tvPuntuacion);

        TextView tvNivel = findViewById(R.id.tvNivel);

        TextView tvNombre = findViewById(R.id.tvNombre);

        juego = (Juego) findViewById(R.id.Pantalla);

        /** Rellena los TextView **/

        tvNombre.setText(Juego.nombreStr);

        tvPuntos.setText(juego.puntuacionStr);

        tvNivel.setText(juego.nivelStr);

        /** Definimos variables audio **/
        mp = MediaPlayer.create(MainActivity.this, R.raw.ha);
        mp2 = MediaPlayer.create(MainActivity.this, R.raw.burb);
        mp3 = MediaPlayer.create(MainActivity.this, R.raw.cartoon);

        ViewTreeObserver obs = juego.getViewTreeObserver();

        obs.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {

                // Sólo se puede averiguar el ancho y alto una vez ya se ha pintado el layout. Por eso se calcula en este listener
                juego.ancho = juego.getWidth();
                juego.alto = juego.getHeight();
                juego.posX = juego.ancho / 2;
                /** Defino posY de juego para colocar textos **/
                juego.posY = juego.alto - juego.alto + 225;
                juego.radio = 50;
                juego.posFrutaY = juego.getHeight();

            }
        });

        //RENDERIZACION Ejecutamos cada 20 milisegundos
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

                           @Override
                           public void run() {

                               if(Juego.contador>=5){
                                   Juego.nivel=Juego.nivel+1;
                                   Juego.contador=0;
                               }

                               if(Juego.gameOver==0) {
                                   /** Incia listener para que la musica se vaya repitiendo **/
                                   mp3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                       @Override
                                       public void onCompletion(MediaPlayer mp3) {
                                           //observador.stop();
                                           mp3.seekTo(0);

                                           // TODO Auto-generated method stub
                                           mp3.start();
                                       }
                                   });
                               }

                               handler.post(new Runnable() {

                                   public void run() {
                                   //Cada x segundos movemos la moneda 10dp

                                       juego.posFrutaY -= juego.nivel*5;

                                       //actualiza el los puntos de partida

                                       tvPuntos.setText(juego.puntuacionStr);
                                       tvNivel.setText(juego.nivelStr);


                                       //refreca la pantalla y llama al draw
                                       juego.invalidate();

                                       /** Si la puntación es inferior a 0 ejecuta game over**/

                                           if(juego.puntuacion<=0){

                                               comentFinal="GAME OVER";

                                               Juego.gameOver=1;
                                               mp3.stop();
                                               mp3.release();
                                               timer.cancel();

                                               startActivity(new Intent(MainActivity.this, GameOver.class));

                                           }

                                       /** Si la puntación es inferior a 0 ejecuta You Win**/

                                           if(juego.nivel>=10){

                                               comentFinal="YOU WIN!";

                                               Juego.gameOver=1;
                                               mp3.stop();
                                               mp3.release();
                                               timer.cancel();

                                               startActivity(new Intent(MainActivity.this, youWin.class));

                                           }

                                   }

                               });

                           }

                       }, 0, 20
        );


    }
}