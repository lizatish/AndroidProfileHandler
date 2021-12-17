package com.tishkovets.lab4.repository;

import com.tishkovets.lab4.domain.Branch;
import com.tishkovets.lab4.domain.Education;
import com.tishkovets.lab4.domain.ExperienceLevel;
import com.tishkovets.lab4.domain.Profile;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ProfileRepositoryImpl implements ProfileRepository {

    private static ProfileRepository instance;
    public Map<Integer, Profile> profiles = new LinkedHashMap<>();

    @Override
    public List<Profile> getAll() {
        return new LinkedList<>(profiles.values());
    }

    @Override
    public Profile getById(int id) {
        return profiles.get(id);
    }

    @Override
    public void save(Profile profile) {
        profiles.put(profile.getId(), profile);
    }

    @Override
    public void delete(int id) {
        profiles.remove(id);
    }

    @Override
    public int getLastId() {
        return getAll().size() - 1;
    }

    public static synchronized ProfileRepository getInstance() {
        if (instance == null) {
            instance = new ProfileRepositoryImpl();
            instance.save(generateProfile());
        }
        return instance;
    }

    private static Profile generateProfile() {
        Profile profile = new Profile();
        profile.setId(0);
        profile.setName("Elizaveta");
        profile.setSecondName("Tishkovets");
        profile.setPatronymic("Vitalevna");
        profile.setEducationLevel(new Education(2131230983, "Неполное среднее"));
        profile.setBranch(new Branch(0, "Государственные организации"));
        profile.setExperienceLevel(new ExperienceLevel(0, "Нет опыта"));
        profile.setSalary(50000);
        return profile;
    }

}
