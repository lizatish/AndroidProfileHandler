package com.tishkovets.lab4.repository;

import com.tishkovets.lab4.domain.ExperienceLevel;

import java.util.List;

public interface ExperienceLevelRepository {

    List<ExperienceLevel> getAll();

    ExperienceLevel getById(Long id);

}
