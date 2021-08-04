package com.example.firstandroidgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.core.content.ContextCompat;

/**
 * Game manages all objects in the game and all rendering/updating
 */

class Game extends SurfaceView implements SurfaceHolder.Callback {
    private GameLoop gameLoop;
    private Context context;

    public Game(Context context) {
        super(context);
        this.context = context;

        // Get surface holder and add call back
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        gameLoop = new GameLoop(this, surfaceHolder);

        setFocusable(true);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void draw(Canvas canvas) {
        // All rendering goes here
        super.draw(canvas);

        drawFPS(canvas);
    }

    public void drawFPS(Canvas canvas){
        String averageFPS = Double.toString(gameLoop.getAverageFPS());

        Paint paint = new Paint();
        int colour = ContextCompat.getColor(context, R.color.navy);
        paint.setColor(colour);
        paint.setTextSize(50);

        canvas.drawText("FPS: " + averageFPS, 100, 50, paint);

    }

    public void update() {
        
    }
}
