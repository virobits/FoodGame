package com.example.foodgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

// Extensión de una View. Totalmente necesario para dibujar
public class Pantalla extends View {
    public int anchoMundo,altoMundo,ancho,alto;
    public float escala;
    public int posX,posY,radio;
    private GestureDetector gestos;

    public Pantalla(Context context) {
        super(context);
    }
    public Pantalla(Context context, AttributeSet attrs) {
        super(context, attrs);
        // Defino límite de mi mundo
        anchoMundo = 1000;
        altoMundo = 1000;
        // La posición inicial de la pelota está sobre la mitad
        posX = anchoMundo/2;
        posY=altoMundo/2;
        // El ancho de la pelota
        radio=50;

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // you may need the x/y location
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                posY=(int)event.getY();
                posX=(int)event.getX();
                radio=25;
                // invalidate llama al onDraw y vuelve a pintar la bola
                this.invalidate();
        }
        return true;
    }
    public Pantalla(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //Definimos los objetos a pintar
        Paint p1 = new Paint();
        Paint p2 = new Paint();
        //Definimos los colores de los objetos a pintar
        p1.setColor(Color.BLACK);
        p1.setStyle(Paint.Style.FILL_AND_STROKE);
        p2.setColor(Color.YELLOW);
        p2.setStyle(Paint.Style.FILL_AND_STROKE);
        //Pinto rectángulo con un ancho y alto de 1000 o de menos si la pantalla es menor.
        canvas.drawRect(new Rect(0,0,world2screen(anchoMundo),world2screen(altoMundo)),p1);
        // Pinto la pelota
        canvas.drawOval(new RectF(world2screen(posX-radio),world2screen(posY-radio),
                world2screen(posX+radio),
                world2screen(posY+radio)),p2);

    }

    public void setEscala() {
        float escalaX,escalaY;
        escalaX=(float)ancho/(float)anchoMundo;
        escalaY=(float)alto/(float)altoMundo;
        escala = Math.min(escalaX,escalaY);
    }
    public int world2screen(int worldCoord){
        return (int) (worldCoord*escala);
    }
    public int screen2world(int screenCoord){
        return (int) (screenCoord*escala);
    }


}
