package com.tishkovets.lab4.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tishkovets.lab4.R;
import com.tishkovets.lab4.domain.Branch;
import com.tishkovets.lab4.domain.Education;
import com.tishkovets.lab4.domain.ExperienceLevel;
import com.tishkovets.lab4.domain.Profile;
import com.tishkovets.lab4.repository.BranchRepository;
import com.tishkovets.lab4.repository.BranchRepositoryImpl;
import com.tishkovets.lab4.repository.ExperienceLevelRepository;
import com.tishkovets.lab4.repository.ExperienceLevelRepositoryImpl;
import com.tishkovets.lab4.repository.ProfileRepository;
import com.tishkovets.lab4.repository.ProfileRepositoryImpl;

public class ProfileEditActivity extends AppCompatActivity {

    public static final String EMPTY_STRING = "";

    private final ProfileRepository profileRepository = ProfileRepositoryImpl.getInstance();
    private BranchRepository branchRepository;
    private ExperienceLevelRepository experienceLevelRepository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);
        branchRepository = BranchRepositoryImpl.getInstance(getResources().getStringArray(R.array.branch));
        experienceLevelRepository = ExperienceLevelRepositoryImpl.getInstance(getResources().getStringArray(R.array.experience));

        Profile profile = getProfile();
        initFormData(profile);

        initButtonHandlers();

    }

    private void initButtonHandlers() {
        Button back = findViewById(R.id.buttonProfileEditBack);
        back.setOnClickListener(v -> finish());

        Button clear = findViewById(R.id.buttonClear);
        clear.setOnClickListener(v -> clearForm());

        Button save = getButtonAdd();
        save.setOnClickListener(v -> {
            saveForm();
            Profile profile = getProfile();
            if (profile != null) {
                Intent result = new Intent();
                result.putExtra("id", profile.getId());
                setResult(RESULT_OK, result);
            }
            finish();
        });

        SeekBar seekBar = getSeekBarSalaryLevel();
        TextView salary = getTextViewSalary();
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                salary.setText(String.format(getResources().getString(R.string.salaryPattern), Integer.valueOf(progress).toString()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void initFormData(Profile profile) {
        Spinner branch = getBranch();
        branch.setAdapter(new ArrayAdapter<Branch>(this, android.R.layout.simple_spinner_dropdown_item, branchRepository.getAll()));
        Spinner experience = getExperience();
        experience.setAdapter(new ArrayAdapter<ExperienceLevel>(this, android.R.layout.simple_spinner_dropdown_item, experienceLevelRepository.getAll()));
        isAgreetoDataProcessing().setOnClickListener(v -> {
            getButtonAdd().setEnabled(((Switch) v).isChecked());
        });

        clearForm();

        if (profile != null) {
            getName().setText(profile.getName());
            getSurname().setText(profile.getSecondName());
            getPatronymic().setText(profile.getPatronymic());
            getRadioGroupEducation().check(profile.getEducationLevel().getId());
//            ((RadioButton) getRadioGroupEducation().getChildAt(profile.getEducationLevel().getId())).setChecked(true);
            getBranch().setSelection(profile.getBranch().getId());
            getExperience().setSelection(profile.getExperienceLevel().getId());
            getSeekBarSalaryLevel().setProgress(profile.getSalary());
            getTextViewSalary().setText(String.format(getResources().getString(R.string.salaryPattern), Integer.valueOf(profile.getSalary()).toString()));
            isFullEmployment().setChecked(profile.isFullTime());
            isOneTimeEmployment().setChecked(profile.isOneTime());
            isPartEmployment().setChecked(profile.isPartTime());
            isInternshipEmployment().setChecked(profile.isStaging());
            isNoPhysicalBoundaries().setChecked(profile.isNoPhysicalRestriction());
            noCriminalRecprds().setChecked(profile.isNoCriminalRecords());
            getAdditionalText().setText(profile.getAdditionalInformation());
        }
    }

    private void saveForm() {
        Profile profile = getProfile();
        if (profile == null) {
            profile = new Profile();
            profile.setId(profileRepository.getLastId() + 1);
        }
        profile.setName(getName().getText().toString());
        profile.setSecondName(getSurname().getText().toString());
        profile.setPatronymic(getPatronymic().getText().toString());
        int checkedRadioButtonId = getRadioGroupEducation().getCheckedRadioButtonId();
        profile.setEducationLevel(new Education(checkedRadioButtonId, ((RadioButton) findViewById(checkedRadioButtonId)).getText().toString()));
        Branch branch = (Branch) getBranch().getSelectedItem();
        profile.setBranch(new Branch(branch.getId(), branch.getName()));
        ExperienceLevel experienceLevel = (ExperienceLevel) getExperience().getSelectedItem();
        profile.setExperienceLevel(experienceLevel);
        profile.setSalary(getSeekBarSalaryLevel().getProgress());
        profile.setFullTime(isFullEmployment().isChecked());
        profile.setPartTime(isPartEmployment().isChecked());
        profile.setOneTime(isOneTimeEmployment().isChecked());
        profile.setStaging(isInternshipEmployment().isChecked());
        profile.setNoPhysicalRestriction(isNoPhysicalBoundaries().isChecked());
        profile.setNoCriminalRecords(noCriminalRecprds().isChecked());
        profile.setAdditionalInformation(getAdditionalText().getText().toString());
        profileRepository.save(profile);
    }

    private void clearForm() {
        getName().setText(EMPTY_STRING);
        TextView surname = getSurname();
        surname.setText(EMPTY_STRING);
        TextView patronymic = getPatronymic();
        patronymic.setText(EMPTY_STRING);

        RadioGroup radioGroup = getRadioGroupEducation();
        radioGroup.clearCheck();

        Spinner branch = getBranch();
        branch.setSelection(0);
        Spinner experience = getExperience();
        experience.setSelection(0);
        SeekBar seekBarSalaryLevel = getSeekBarSalaryLevel();
        seekBarSalaryLevel.setProgress(0);
        getTextViewSalary().setText(String.format(getResources().getString(R.string.salaryPattern), "0"));

        CheckBox checkBoxIsFullEmployment = isFullEmployment();
        checkBoxIsFullEmployment.setChecked(false);
        CheckBox checkBoxIsOneTimeEmployment = isOneTimeEmployment();
        checkBoxIsOneTimeEmployment.setChecked(false);
        CheckBox checkBoxIsPartEmployment = isPartEmployment();
        checkBoxIsPartEmployment.setChecked(false);
        CheckBox checkBoxIsInternshipEmployment = isInternshipEmployment();
        checkBoxIsInternshipEmployment.setChecked(false);

        ToggleButton noPhysicalBoundaries = isNoPhysicalBoundaries();
        noPhysicalBoundaries.setChecked(false);
        ToggleButton noCriminalRecprds = noCriminalRecprds();
        noCriminalRecprds.setChecked(false);

        TextView additionalText = getAdditionalText();
        additionalText.setText(EMPTY_STRING);

        Switch agreement = isAgreetoDataProcessing();
        agreement.setChecked(false);
        getButtonAdd().setEnabled(false);
    }


    @Nullable
    private Profile getProfile() {
        Profile profile = null;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            long profileId = (long) extras.get("profileId");
            profile = profileRepository.getById((int) profileId);
        }
        return profile;
    }

    private TextView getTextViewSalary() {
        return findViewById(R.id.textViewSalary);
    }

    private SeekBar getSeekBarSalaryLevel() {
        return findViewById(R.id.seekBarSalaryLevel);
    }

    private Switch isAgreetoDataProcessing() {
        return findViewById(R.id.isAgreetoDataProcessing);
    }

    private TextView getAdditionalText() {
        return getTextViewById(R.id.editTextTextPersonName4);
    }

    private ToggleButton isNoPhysicalBoundaries() {
        return findViewById(R.id.isNoPhysicalBoundaries);
    }

    private ToggleButton noCriminalRecprds() {
        return findViewById(R.id.isNoCriminalRecprds);
    }

    private CheckBox isInternshipEmployment() {
        return findViewById(R.id.isInternshipEmployment);
    }

    private CheckBox isPartEmployment() {
        return findViewById(R.id.isPartEmployment);
    }

    private CheckBox isOneTimeEmployment() {
        return findViewById(R.id.isOneTimeEmployment);
    }

    private CheckBox isFullEmployment() {
        return findViewById(R.id.isFullEmployment);
    }

    private Spinner getExperience() {
        return findViewById(R.id.spinnerExperience);
    }

    private Spinner getBranch() {
        return findViewById(R.id.spinnerBranch);
    }

    private RadioGroup getRadioGroupEducation() {
        return findViewById(R.id.radioGroupEducation);
    }

    private TextView getPatronymic() {
        return getTextViewById(R.id.patronymic);
    }

    private TextView getSurname() {
        return getTextViewById(R.id.surname);
    }

    private TextView getName() {
        return getTextViewById(R.id.name);
    }

    private TextView getTextViewById(int p) {
        return findViewById(p);
    }

    private Button getButtonAdd() {
        return findViewById(R.id.buttonAdd);
    }


}
