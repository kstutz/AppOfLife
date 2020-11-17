package com.example.gameoflife;

import android.view.*;
import android.widget.*;
import android.graphics.*;

public class Game {

    private GameView gameView;
    public Board gameBoard;
    private int boardWidth;
    private int boardHeight;


    public Game(GameView gameView, int boardWidth, int boardHeight) {
        this.gameView = gameView;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.gameBoard = new Board(gameView, boardWidth, boardHeight);
    }

    public void start() {
        this.gameBoard.reset();
    }

    public void registerTouch(MotionEvent event) {

        for (int i = 0; i < this.boardWidth; i++) {
            for (int j = 0; j < this.boardHeight; j++) {
                if (this.gameBoard.grid[i][j].hasCollided(event.getX(), event.getY())) {
                    this.gameBoard.grid[i][j].toggle();
                }
            }
        }
    }

}