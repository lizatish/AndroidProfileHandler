package com.tishkovets.lab4.repository;

import com.tishkovets.lab4.domain.Branch;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BranchRepositoryImpl implements BranchRepository {
    private static BranchRepository instance;
    private final Map<Integer, Branch> branchMap = new LinkedHashMap<>();

    public BranchRepositoryImpl(String[] stringArray) {
        for (int i = 0; i < stringArray.length; i++) {
            branchMap.put(i, new Branch(i, stringArray[i]));
        }
    }

    @Override
    public List<Branch> getAll() {
        return new ArrayList<>(branchMap.values());
    }

    @Override
    public Branch getById(Long id) {
        return branchMap.get(id);
    }

    public static synchronized BranchRepository getInstance(String[] stringArray) {
        if (instance == null) {
            instance = new BranchRepositoryImpl(stringArray);
        }
        return instance;
    }
}
