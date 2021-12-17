package com.tishkovets.lab4.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tishkovets.lab4.R;
import com.tishkovets.lab4.repository.ProfileRepository;
import com.tishkovets.lab4.repository.ProfileRepositoryImpl;
import com.tishkovets.lab4.service.StatisticService;
import com.tishkovets.lab4.service.StatisticServiceImpl;

public class MainActivity extends AppCompatActivity {

    private final StatisticService statisticService = new StatisticServiceImpl();
    private final ProfileRepository profileRepository = ProfileRepositoryImpl.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initButtonHandlers();
        updateProfileCOuntStatistic();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        updateProfileCOuntStatistic();
    }

    private void updateProfileCOuntStatistic() {
        TextView allProfiles = findViewById(R.id.textViewNumbersOfProfiles);
        allProfiles.setText(generateAllProfilesText());
    }

    private String generateAllProfilesText() {
        return String.format(getResources().getString(R.string.allProfilesTextPattern), String.valueOf(statisticService.getProfileCount(profileRepository.getAll())));
    }

    private void initButtonHandlers() {
        Button buttonNewProfile = findViewById(R.id.buttonAddNewProfile);
        buttonNewProfile.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ProfileEditActivity.class);
            startActivityForResult(intent, 1);
        });

        Button buttonProfileList = findViewById(R.id.buttonProfileList);
        buttonProfileList.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ProfilesActivity.class);
            startActivityForResult(intent, 1);
        });

        Button buttonStatisticLayer = findViewById(R.id.buttonGetStatistics);
        buttonStatisticLayer.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, StatisticsActivity.class);
            startActivity(intent);
        });
    }
}