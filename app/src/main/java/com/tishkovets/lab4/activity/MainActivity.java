package com.tishkovets.lab4.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.tishkovets.lab4.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonStatisticLayer = findViewById(R.id.buttonGetStatistics);

        buttonStatisticLayer.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, StatisticsActivity.class);
            startActivity(intent);
        });

        Button buttonNewProfile = findViewById(R.id.buttonAddNewProfile);

        buttonNewProfile.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ProfileEditActivity.class);
            startActivity(intent);
        });

    }
}