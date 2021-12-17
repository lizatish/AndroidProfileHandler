package com.tishkovets.lab4.repository;

import com.tishkovets.lab4.domain.Profile;

import java.util.Collection;

public interface ProfileRepository {

    Collection<Profile> getAll();

    Profile getById(Long id);

    void save(Profile profile);

    void delete(Long id);

}
