package com.example.foodgame;

public class Niveles {
    public  String nivelDificultad;

    public Niveles(String nivelDificultad) {
        this.nivelDificultad = nivelDificultad;
    }

    public String getNivelDificultad() {
        return nivelDificultad;
    }

    public void setNivelDificultad(String nivelDificultad) {
        this.nivelDificultad = nivelDificultad;
    }

    @Override
    public String toString() {
        return "Nivel " + nivelDificultad;
    }
}




