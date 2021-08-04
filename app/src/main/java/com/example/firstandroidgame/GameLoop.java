package com.example.firstandroidgame;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

import java.security.MessageDigest;

class GameLoop extends Thread {

    private boolean isRunning = false;
    private Game game;
    private SurfaceHolder surfaceHolder;
    private double averageFPS;

    public GameLoop(Game game, SurfaceHolder surfaceHolder){
        this.game = game;
        this.surfaceHolder = surfaceHolder;
    }

    public Double getAverageFPS() {
        return averageFPS;
    }

    public void startLoop() {
        isRunning = true;
        start(); // extended from thread class, everything is run in run()
    }

    @Override
    public void run() {
        super.run();

        // Frame count and time
        int frameCount = 0;
        //int ups

        long startTime;
        long elapsedTime;
        long sleepTime;

        startTime = System.currentTimeMillis();

        //Canvas canvas;
        Canvas canvas = null;
        while(isRunning){

            // Update and render game
            try{
                canvas = surfaceHolder.lockCanvas();

                synchronized (surfaceHolder){
                    game.update();
                    game.draw(canvas);
                }
                try{
                    surfaceHolder.unlockCanvasAndPost(canvas);
                    frameCount++;
                }catch(Exception e){
                    e.printStackTrace();
                }
            }catch (IllegalArgumentException e){
                e.printStackTrace();
            } finally {
                if(canvas != null){
                    try{
                        surfaceHolder.unlockCanvasAndPost(canvas);
                        frameCount++;
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }

            elapsedTime = System.currentTimeMillis() - startTime;
            if(elapsedTime >= 1000){
                averageFPS = frameCount / (1E-3 * elapsedTime);
                frameCount = 0;
                startTime = System.currentTimeMillis();
            }
        }


//        while(isRunning){
//            try {
//                canvas = surfaceHolder.lockCanvas();
//                game.update();
//                game.draw(canvas);
//                surfaceHolder.unlockCanvasAndPost(canvas);
//            }catch (IllegalArgumentException e){
//                e.printStackTrace();
//            }
//            frameCount++;
//
//            elapsedTime = System.currentTimeMillis() - startTime;
//            if(elapsedTime >= 1000){
//                averageFPS = frameCount / (1E-3 * elapsedTime);
//                frameCount = 0;
//                startTime = System.currentTimeMillis();
//            }
//        }


    }
}
