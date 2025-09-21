package com.dazconsultingds.increasegamerm121224pl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GameActivity extends AppCompatActivity {

    TextView title, number;
    Button buttonGame;
    String playerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        title = findViewById(R.id.title);
        number = findViewById(R.id.number);
        buttonGame = findViewById(R.id.buttonGame);

        // odbierz imię
        playerName = getIntent().getStringExtra("UserName");
        title.setText("Good luck " + playerName);
        number.setText("0");

        buttonGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOne();
            }
        });
    }

    public void addOne() {
        int scoreVal = Integer.parseInt(number.getText().toString());

        if (scoreVal == 22) {
            Toast.makeText(this, "Sorry, cannot count higher", Toast.LENGTH_SHORT).show();
            return;
        }

        scoreVal += 1;
        number.setText(String.valueOf(scoreVal));

        if (scoreVal > 22) {
            Intent winner = new Intent(GameActivity.this, WinnerActivity.class);
            winner.putExtra("UserName", playerName);
            startActivity(winner);
        }
    }
}
