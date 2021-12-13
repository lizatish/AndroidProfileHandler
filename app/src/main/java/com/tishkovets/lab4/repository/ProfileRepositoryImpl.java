package com.tishkovets.lab4.repository;

import com.tishkovets.lab4.domain.Profile;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ProfileRepositoryImpl implements ProfileRepository {

    public Map<Long, Profile> profiles = new ConcurrentHashMap<>();

    @Override
    public Collection<Profile> getAll() {
        return profiles.values();
    }

    @Override
    public Profile getById(Long id) {
        return profiles.get(id);
    }


}
