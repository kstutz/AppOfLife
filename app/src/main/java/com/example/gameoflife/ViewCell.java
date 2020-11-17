package com.example.gameoflife;
import android.graphics.*;

public class ViewCell {

    private int x;
    private int y;
    private int width;
    private int height;
    private boolean isAlive;

    public ViewCell(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.isAlive = false;
    }

    public void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        if (isAlive) {
            paint.setColor(Color.BLACK);
        } else {
            paint.setColor(Color.WHITE);
        }
        canvas.drawRect(x, y, x+width-1, y+height-1, paint);
    }

    public boolean hasCollided(float otherX, float otherY) {
        return this.x < otherX && this.y < otherY && this.x + this.width > otherX && this.y + this.height > otherY;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void toggle() {
        isAlive = !isAlive;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public boolean isAlive() {
        return isAlive;
    }
    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

}
