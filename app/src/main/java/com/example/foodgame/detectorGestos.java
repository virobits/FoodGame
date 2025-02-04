package com.example.foodgame;

import android.view.GestureDetector;
import android.view.MotionEvent;

public class detectorGestos extends
        GestureDetector.SimpleOnGestureListener {

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        return true;
    }
}
