package com.tishkovets.lab4.repository;

import com.tishkovets.lab4.domain.Profile;

import java.util.Collection;
import java.util.List;

public interface ProfileRepository {

    List<Profile> getAll();

    Profile getById(int id);

    void save(Profile profile);

    void delete(int id);

    int getLastId();
}
