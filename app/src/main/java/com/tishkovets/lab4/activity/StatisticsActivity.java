package com.tishkovets.lab4.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tishkovets.lab4.R;

public class StatisticsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        Button buttonStatisticLayer = findViewById(R.id.buttonStatisticBack);

        buttonStatisticLayer.setOnClickListener(v -> finish());

    }
}
