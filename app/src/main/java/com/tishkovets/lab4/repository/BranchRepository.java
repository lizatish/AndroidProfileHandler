package com.tishkovets.lab4.repository;

import com.tishkovets.lab4.domain.Branch;

import java.util.Collection;
import java.util.List;

public interface BranchRepository {

    List<Branch> getAll();

    Branch getById(Long id);

}
