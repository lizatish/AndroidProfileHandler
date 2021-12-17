package com.tishkovets.lab4.repository;

import com.tishkovets.lab4.domain.Profile;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ProfileRepositoryImpl implements ProfileRepository {

    private static ProfileRepository instance;
    public Map<Long, Profile> profiles = new ConcurrentHashMap<>();

    @Override
    public Collection<Profile> getAll() {
        return profiles.values();
    }

    @Override
    public Profile getById(Long id) {
        return profiles.get(id);
    }

    @Override
    public void save(Profile profile) {

    }

    @Override
    public void delete(Long id) {

    }

    public static synchronized ProfileRepository getInstance() {
        if (instance == null) {
            instance = new ProfileRepositoryImpl();
        }
        return instance;
    }

}
