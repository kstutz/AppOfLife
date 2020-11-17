package com.example.gameoflife;

import java.util.*;
import android.graphics.*;
import android.content.*;
import android.util.Log;
import android.view.*;

public class Board {

    public ViewCell[][] grid;
    private GameView gameView;
    private int boardWidth;
    private int boardHeight;
    private int cellWidth = 25;
    private int cellHeight = 25;

    public Board(GameView gameView, int boardWidth, int boardHeight) {
        this.grid = new ViewCell[boardWidth][boardHeight];
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.gameView = gameView;
    }

    public void draw(Canvas canvas) {
        for(int i = 0; i < boardWidth; i++) {
            for(int j = 0; j < boardHeight; j++) {
                this.grid[i][j].onDraw(canvas);
            }
        }
    }

    public void reset() {
        for(int i = 0; i < boardWidth; i++) {
            for(int j = 0; j < boardHeight; j++) {
                this.grid[i][j] = new ViewCell(i, j, cellWidth, cellHeight);
            }
        }
        setPositions();
    }

    public void randomFill() {
        for(int i = 0; i < boardWidth; i++) {
            for(int j = 0; j < boardHeight; j++) {
                this.grid[i][j] = new ViewCell(i, j, cellWidth, cellHeight);
                int rand = new Random().nextInt(2);
                System.out.println(rand);
                if (rand == 0) {
                    this.grid[i][j].setAlive(false);
                } else {
                    this.grid[i][j].setAlive(true);
                }
            }
        }
        setPositions();
    }

    private ViewCell[][] getEmptyGrid() {
        ViewCell[][] emptyGrid = new ViewCell[boardWidth][boardHeight];
        for(int i = 0; i < boardWidth; i++) {
            for(int j = 0; j < boardHeight; j++) {
                emptyGrid[i][j] = new ViewCell(i, j, cellWidth, cellHeight);
            }
        }
        return emptyGrid;
    }

    public void setPositions() {
        int horizontalOffset = (320 - (this.boardWidth * 25)) / 2;
        for(int i = 0; i < boardWidth; i++) {
            for(int j = 0; j < boardHeight; j++) {
                this.grid[i][j].setX(horizontalOffset + i * 25);
                this.grid[i][j].setY(90 + j * 25);
            }
        }
    }

    public void nextGeneration() {
        ViewCell[][] nextGrid = getEmptyGrid();
        int neighbours;
        for  (int row = 0; row < boardHeight; row++) {
            for (int column = 0; column < boardWidth; column++  ) {
                neighbours = countNeighbours(column, row);

//               Any live cell with two or three live neighbours survives.
                if (grid[column][row].isAlive() && (neighbours == 2 || neighbours == 3)) {
                    nextGrid[column][row].setAlive(true);
                }
//               Any dead cell with three live neighbours becomes a live cell.
                if (!grid[column][row].isAlive() &&  neighbours == 3) {
                    nextGrid[column][row].setAlive(true);
                }
//               All other live cells die in the next generation. Similarly, all other dead cells stay dead.
                ; //map initialised to false
            }
        }
        grid = nextGrid;
        setPositions();
    }

    /**
     * Counts number of neighbouring organisms of one cell
     * @param row
     * @param column
     * @return number of neighbours
     */
    private int countNeighbours(int column, int row) {
        int count = 0;
        int startRow, startColumn;
        int endRow, endColumn;
        if (row == 0) {
            startRow = 0;
            endRow = 1;
        } else if (row == boardHeight-1) {
            startRow = row-1;
            endRow = row;
        } else {
            startRow = row-1;
            endRow = row+1;
        }
        if (column == 0) {
            startColumn = 0;
            endColumn = 1;
        } else if (column == boardWidth-1) {
            startColumn = column-1;
            endColumn = column;
        } else {
            startColumn = column-1;
            endColumn = column+1;
        }

        for (int currentRow = startRow; currentRow<= endRow; currentRow++) {
            for (int currentColumn = startColumn; currentColumn <= endColumn; currentColumn++  ) {
                if (!((currentRow==row) && (currentColumn==column))) {
                    if (grid[currentColumn][currentRow].isAlive()) {
                        count++;
                    }
                }
            }

        }
        return count;
    }
}