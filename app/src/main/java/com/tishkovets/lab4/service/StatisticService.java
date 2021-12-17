package com.tishkovets.lab4.service;

import com.tishkovets.lab4.domain.Profile;

import java.util.List;

public interface StatisticService {

    int getProfileCount(List<Profile> profiles);

    String popularEducation(List<Profile> profiles);

    String popularBranch(List<Profile> profiles);

    String popularExperience(List<Profile> profiles);

    double averageSalary(List<Profile> profiles);

    long countPartTime(List<Profile> profiles);

    long countFullTime(List<Profile> profiles);

    long countOneTime(List<Profile> profiles);

    long countStaging(List<Profile> profiles);

}
