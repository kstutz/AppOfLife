package com.example.gameoflife;

import android.content.*;
import android.util.AttributeSet;
import android.view.*;
import android.graphics.*;

public class GameView extends SurfaceView implements SurfaceHolder.Callback{

    private SurfaceHolder holder;
    private GameLoopThread gameLoopThread;
    public Context context;
    private Game game;
    private long lastClick;
    private static final int WIDTH = 8;
    private static final int HEIGHT = 10;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.context = context;
        gameLoopThread = new GameLoopThread(this);
        game = new Game(this, WIDTH, HEIGHT);
        game.start();
        holder = getHolder();
        holder.addCallback(this);
    }

    public void doOneStep() {
        game.gameBoard.nextGeneration();
    }

    public void reset() {
        game.gameBoard.reset();
    }

    public void randomFill() {
        game.gameBoard.randomFill();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = false;
        gameLoopThread.setRunning(false);
        while(retry) {
            try {
                gameLoopThread.join();
                retry = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        gameLoopThread.setRunning(true);
        gameLoopThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) { }


    public void tryDrawing(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        this.game.gameBoard.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(System.currentTimeMillis() - lastClick > 500) {
            lastClick = System.currentTimeMillis();
            synchronized (getHolder()) {
                this.game.registerTouch(event);
            }
        }
        return true;
    }

}