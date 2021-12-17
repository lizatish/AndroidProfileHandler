package com.tishkovets.lab4.activity;

import static com.tishkovets.lab4.activity.ProfilesActivity.TWO_STRING_ARGS_PATTERN;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.tishkovets.lab4.R;
import com.tishkovets.lab4.domain.Profile;
import com.tishkovets.lab4.repository.ProfileRepository;
import com.tishkovets.lab4.repository.ProfileRepositoryImpl;
import com.tishkovets.lab4.service.StatisticService;
import com.tishkovets.lab4.service.StatisticServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticsActivity extends AppCompatActivity {

    private final StatisticService statisticService = new StatisticServiceImpl();
    private final ProfileRepository profileRepository = ProfileRepositoryImpl.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        String statistics = generateStatistics();
        TextView textViewStatistics = findViewById(R.id.textViewStatistic);
        textViewStatistics.setText(statistics);

        Button buttonStatisticLayer = findViewById(R.id.buttonStatisticBack);
        buttonStatisticLayer.setOnClickListener(v -> finish());

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String generateStatistics() {
        Resources resources = getResources();
        List<Profile> profiles = profileRepository.getAll();
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(TWO_STRING_ARGS_PATTERN, resources.getString(R.string.allProfiles), statisticService.getProfileCount(profiles)));
        sb.append(System.getProperty("line.separator"));
        sb.append(String.format(TWO_STRING_ARGS_PATTERN, resources.getString(R.string.popularEducation), statisticService.popularEducation(profiles)));
        sb.append(System.getProperty("line.separator"));
        sb.append(String.format(TWO_STRING_ARGS_PATTERN, resources.getString(R.string.popularBranch), statisticService.popularBranch(profiles)));
        sb.append(System.getProperty("line.separator"));
        sb.append(String.format(TWO_STRING_ARGS_PATTERN, resources.getString(R.string.popularExperience), statisticService.popularExperience(profiles)));
        sb.append(System.getProperty("line.separator"));
        sb.append(String.format(TWO_STRING_ARGS_PATTERN, resources.getString(R.string.averageSalary), statisticService.averageSalary(profiles)));
        sb.append(System.getProperty("line.separator"));
        String popularEmployment = getPopularEmployment(resources, profiles);
        sb.append(String.format(TWO_STRING_ARGS_PATTERN, resources.getString(R.string.popularEmployment), popularEmployment));
        sb.append(System.getProperty("line.separator"));


        return sb.toString();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @NonNull
    private String getPopularEmployment(Resources resources, List<Profile> profiles) {
        Map<String, Long> employmentCountMap = new HashMap<>();
        employmentCountMap.put(resources.getString(R.string.fullTime), statisticService.countFullTime(profiles));
        employmentCountMap.put(resources.getString(R.string.oneTime), statisticService.countOneTime(profiles));
        employmentCountMap.put(resources.getString(R.string.partTime), statisticService.countPartTime(profiles));
        employmentCountMap.put(resources.getString(R.string.staging), statisticService.countStaging(profiles));
        return employmentCountMap.entrySet().stream().max(Map.Entry.comparingByValue()).filter(entry -> entry.getValue() > 0).map(Map.Entry::getKey).orElse("");
    }
}
