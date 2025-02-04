package com.example.foodgame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;


import java.util.Random;

// Extensión de una View. Totalmente necesario para dibujar


public class Juego extends View {
    public int ancho,alto;
    public float escala;
    public int posX,posY,radio,posFrutaX,posFrutaY;
    private GestureDetector gestos;
    private RectF rectCesta;
    private RectF rectFruta;
    private Random random = new Random();
    /** Random tipoFruto **/
    private Random randomTipo = new Random();
    private Random randomTipoBuena = new Random();
    private Random randomTipoMala = new Random();
    public int tipoFruta;
    public int frutaBuena;
    public int frutaMala;

    public int valorTipoFruta;
    public int valorTipoBuena;
    public int valorTipoMala;

    public int varPuntuacion;
    static int puntuacion=5;
    public String puntuacionStr;

    static int nivel=5;
    static String nivelStr;
    static int play;
    static String nombreStr;
    public String GOnombreStr = nombreStr;
    static int gameOver;



    /** variable contador **/
    static int contador=0;

    /** Creo variable bitmapCesta **/
    Bitmap bitmapCesta = BitmapFactory.decodeResource(getResources (), R.drawable.cesta);
    /** Creo variable bitmapFresa **/
    Bitmap bitmapFresa = BitmapFactory.decodeResource(getResources (), R.drawable.fresa);
    /** Creo variable bitmapManzana **/
    Bitmap bitmapManazana = BitmapFactory.decodeResource(getResources (), R.drawable.manzana);
    /** Creo variable bitmapDonut **/
    Bitmap bitmapDonut = BitmapFactory.decodeResource(getResources (), R.drawable.donut);
    /** Creo variable bitmapCesta **/
    Bitmap bitmapBanana = BitmapFactory.decodeResource(getResources (), R.drawable.banana);
    /** Creo variable bitmapFresa **/
    Bitmap bitmapBurger = BitmapFactory.decodeResource(getResources (), R.drawable.burger);
    /** Creo variable bitmapManzana **/
    Bitmap bitmapCarrot = BitmapFactory.decodeResource(getResources (), R.drawable.carrrot);
    /** Creo variable bitmapDonut **/
    Bitmap bitmapHotdog = BitmapFactory.decodeResource(getResources (), R.drawable.hotdog);

    public Juego(Context context) {


        super(context);

    }
    public Juego(Context context, AttributeSet attrs) {
        super(context, attrs);

        valorTipoFruta= rndFruta();
        valorTipoBuena = rndFrutaBuena();
        valorTipoMala = rndFrutaMala();

    }
    //Sección que capta los eventos del usuario


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // you may need the x/y location
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_MOVE:

                radio=50;

                /** Si la cesta passa de mitad pantalla le resta el radio**/

                if(event.getX()>getWidth()/2){
                    posX=(int)event.getX()-radio;

                }
                /** Sino le suma el radio **/
                else{
                    posX=(int)event.getX()+radio;

                }

                this.invalidate();
        }
        return true;
    }

    public Juego(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }



    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        //Definimos los objetos a pintar
        //Paint fondo = new Paint();
        Paint cesta = new Paint();
        Paint fruta = new Paint();

        //Definimos los colores de los objetos a pintar
        //fondo.setColor(Color.BLACK);
        //fondo.setStyle(Paint.Style.FILL_AND_STROKE);
        cesta.setColor(Color.RED);
        fruta.setColor(Color.RED);
        cesta.setStyle(Paint.Style.STROKE);
        fruta.setStyle(Paint.Style.STROKE);
        //Pinto rectángulo con un ancho y alto de 1000 o de menos si la pantalla es menor.
        //canvas.drawRect(new Rect(0,0,(ancho),(alto)),fondo);
        // Pinto la pelota. La Y la implementa el timer y la X la pongo aleatoreamente en cuanto llega al final

        /** dibuja cesta **/
        rectCesta= new RectF((posX-radio),(posY-radio),(posX+radio),(posY+radio));
        //canvas.drawOval(rectCesta,cesta);
        canvas.drawBitmap(bitmapCesta, null, rectCesta, cesta);

        /** dibuja Fruta **/
        rectFruta = new RectF((posFrutaX-radio),(posFrutaY-radio),(posFrutaX+radio),(posFrutaY+radio));

        /** Corrector posicion X fruta **/
        if(posFrutaX>ancho-radio){
            posFrutaX=posFrutaX-radio;
        }
        if(posFrutaX<0+radio){
            posFrutaX=posFrutaX+radio;
        }

        if(valorTipoFruta>5){
            /** Fruta Mala - Asigno número al azar entre 0 y 10**/

            if(valorTipoMala>=0 && valorTipoMala<3) {
                canvas.drawBitmap(bitmapDonut, null, rectFruta, fruta);

            }
            if(valorTipoMala>=3 && valorTipoMala<7) {
                canvas.drawBitmap(bitmapBurger, null, rectFruta, fruta);

            }
            if(valorTipoMala>=7) {
                canvas.drawBitmap(bitmapHotdog, null, rectFruta, fruta);

            }

        }

        /** Fruta Buena - Asigno número al azar entre 0 y 10**/
        else{

            if(valorTipoBuena>=0 && valorTipoBuena<3) {
                canvas.drawBitmap(bitmapManazana, null, rectFruta, fruta);
                varPuntuacion=1;
            }
            if(valorTipoBuena>3 && valorTipoBuena<6) {
                canvas.drawBitmap(bitmapBanana, null, rectFruta, fruta);
                varPuntuacion=1;
            }
            if(valorTipoBuena>=6 && valorTipoBuena<8) {
                canvas.drawBitmap(bitmapCarrot, null, rectFruta, fruta);
                varPuntuacion=1;
            }
            if(valorTipoBuena>=8) {
                canvas.drawBitmap(bitmapFresa, null, rectFruta, fruta);
                varPuntuacion=2;
            }
        }

        /** Si fruta pasa de pantalla **/

        if (posFrutaY<posY) {

            /** Si la fruta que pasa pantalla es buena **/
            if(valorTipoFruta<=5){
                /** Modifica puntuación **/
                puntuacion -= 1;
            }

            contador++;
            posFrutaY=getHeight()-radio;
            posFrutaX= random.nextInt(ancho-radio);


            /** Corrector posicion X fruta **/
            if(posFrutaX>ancho-(radio*2)){
                posFrutaX=posFrutaX-(radio*2);
            }
            if(posFrutaX<0+(radio*2)){
                posFrutaX=posFrutaX+(radio*2);
            }

            /** Seleccion nueva fruta **/
            valorTipoFruta= rndFruta();
            if(valorTipoFruta<5){
                valorTipoBuena = rndFrutaBuena();
            }else{
                valorTipoMala = rndFrutaMala();
            }
        }


        puntuacionStr= Integer.toString(puntuacion);

        nivelStr= Integer.toString(nivel);

        /** COLISIONES **/
        if(RectF.intersects(rectCesta,rectFruta)){
            contador++;

            /** Ejecuta sonido **/
            if(valorTipoFruta>5){
                /** Modifica puntuación **/
                puntuacion -= 1;
                MainActivity.mp2.start();

            }else{
                /** Modifica puntuación **/

                    puntuacion += varPuntuacion;

                MainActivity.mp.start();
            }

            posFrutaY=getHeight()-radio;
            /** Modifica posición**/
            posFrutaX=random.nextInt(ancho);

            /** Corrector posicion X fruta **/
            if(posFrutaX>ancho-(radio*2)){
                posFrutaX=posFrutaX-(radio*2);
            }
            if(posFrutaX<0+(radio*2)){
                posFrutaX=posFrutaX+(radio*2);
            }

            /** Seleccion nueva fruta **/
            valorTipoFruta= rndFruta();
            if(valorTipoFruta<5){
                valorTipoBuena = rndFrutaBuena();
            }else{
                valorTipoMala = rndFrutaMala();
            }

            }
        }

        public int rndFruta(){
            tipoFruta =randomTipo.nextInt(10)+1;
        return tipoFruta;
        }

        public int rndFrutaBuena(){
            frutaBuena =randomTipoBuena.nextInt(10)+1;
        return frutaBuena;
        }

        public int rndFrutaMala(){
            frutaMala =randomTipoMala.nextInt(10)+1;

        return frutaMala;

        }
}




