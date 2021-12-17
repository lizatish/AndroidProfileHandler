package com.tishkovets.lab4.domain;

public class Profile {
    public Long id;
    private String name;
    private String secondName;
    private String patronymic;
    private Education educationLevel;
    private Branch branch;
    private ExperienceLevel experienceLevel;
    private int salary;
    private boolean isFullTime;
    private boolean isPartTime;
    private boolean isOneTime;
    private boolean isStaging;
    private boolean isNoPhysicalRestriction;
    private boolean isNoCriminalRecords;
    private String additionalInformation;
    private boolean isAgreement;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Education getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(Education educationLevel) {
        this.educationLevel = educationLevel;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public ExperienceLevel getExperienceLevel() {
        return experienceLevel;
    }

    public void setExperienceLevel(ExperienceLevel experienceLevel) {
        this.experienceLevel = experienceLevel;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public boolean isFullTime() {
        return isFullTime;
    }

    public void setFullTime(boolean fullTime) {
        isFullTime = fullTime;
    }

    public boolean isPartTime() {
        return isPartTime;
    }

    public void setPartTime(boolean partTime) {
        isPartTime = partTime;
    }

    public boolean isOneTime() {
        return isOneTime;
    }

    public void setOneTime(boolean oneTime) {
        isOneTime = oneTime;
    }

    public boolean isStaging() {
        return isStaging;
    }

    public void setStaging(boolean staging) {
        isStaging = staging;
    }

    public boolean isNoPhysicalRestriction() {
        return isNoPhysicalRestriction;
    }

    public void setNoPhysicalRestriction(boolean noPhysicalRestriction) {
        isNoPhysicalRestriction = noPhysicalRestriction;
    }

    public boolean isNoCriminalRecords() {
        return isNoCriminalRecords;
    }

    public void setNoCriminalRecords(boolean noCriminalRecords) {
        isNoCriminalRecords = noCriminalRecords;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public boolean isAgreement() {
        return isAgreement;
    }

    public void setAgreement(boolean agreement) {
        isAgreement = agreement;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", name, secondName, patronymic);
    }
}
