package com.example.gameoflife;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private TextView message;
    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("huhu", "haha");
        setContentView(R.layout.activity_main);

        message = findViewById(R.id.statusTextField);
        gameView = findViewById(R.id.gameView);
    }

    public void startGame(View view) {
        gameView.doOneStep();
        message.setText("Started");
    }

    public void pauseGame(View view) {
        message.setText("Stopped");
    }

    public void fillRandomly(View view) {
        message.setText("Random");
        gameView.randomFill();
    }

    public void clearBoard(View view)  {
        gameView.reset();
    }

}