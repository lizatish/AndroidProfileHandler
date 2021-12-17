package com.tishkovets.lab4.service;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.tishkovets.lab4.domain.Profile;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StatisticServiceImpl implements StatisticService {
    @Override
    public int getProfileCount(List<Profile> profiles) {
        return profiles.size();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public String popularEducation(List<Profile> profiles) {
        return profiles.stream()
                .map(profile -> profile.getEducationLevel().getName())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).orElse("");
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public String popularBranch(List<Profile> profiles) {
        return profiles.stream()
                .map(profile -> profile.getBranch().getName())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).orElse("");
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public String popularExperience(List<Profile> profiles) {
        return profiles.stream()
                .map(profile -> profile.getExperienceLevel().getName())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).orElse("");
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public double averageSalary(List<Profile> profiles) {
        return profiles.stream().mapToInt(Profile::getSalary).average().orElse(0d);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public long countPartTime(List<Profile> profiles) {
        return profiles.stream().filter(Profile::isPartTime).count();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public long countFullTime(List<Profile> profiles) {
        return profiles.stream().filter(Profile::isFullTime).count();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public long countOneTime(List<Profile> profiles) {
        return profiles.stream().filter(Profile::isOneTime).count();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public long countStaging(List<Profile> profiles) {
        return profiles.stream().filter(Profile::isStaging).count();
    }
}
