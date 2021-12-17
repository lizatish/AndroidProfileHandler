package com.tishkovets.lab4.repository;

import com.tishkovets.lab4.domain.ExperienceLevel;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExperienceLevelRepositoryImpl implements ExperienceLevelRepository {

    private static ExperienceLevelRepository instance;
    private final Map<Integer, ExperienceLevel> experienceLevelMap = new LinkedHashMap<>();

    public ExperienceLevelRepositoryImpl(String[] stringArray) {
        for (int i = 0; i < stringArray.length; i++) {
            experienceLevelMap.put(i, new ExperienceLevel(i, stringArray[i]));
        }
    }

    @Override
    public List<ExperienceLevel> getAll() {
        return new ArrayList<>(experienceLevelMap.values());
    }

    @Override
    public ExperienceLevel getById(Long id) {
        return experienceLevelMap.get(id);
    }

    public static synchronized ExperienceLevelRepository getInstance(String[] stringArray) {
        if (instance == null) {
            instance = new ExperienceLevelRepositoryImpl(stringArray);
        }
        return instance;
    }
}
