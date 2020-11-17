package com.example.gameoflife;

import android.graphics.*;
import android.util.Log;

public class GameLoopThread extends Thread {

    private GameView gameView;
    private boolean isRunning;

    public GameLoopThread(GameView gameView) {
        this.gameView = gameView;
        this.isRunning = false;
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    @Override
    public void run() {
        while(this.isRunning) {
            Canvas canvas = null;
            try {
                canvas = this.gameView.getHolder().lockCanvas();
                synchronized(this.gameView.getHolder()) {
                    if(canvas != null) {
                        this.gameView.tryDrawing(canvas);
                    }
                }
            } finally {
                if (canvas != null) {
                    this.gameView.getHolder().unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}