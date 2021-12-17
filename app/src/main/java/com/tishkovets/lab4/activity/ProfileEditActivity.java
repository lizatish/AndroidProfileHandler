package com.tishkovets.lab4.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
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

        Profile profile = null;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            profile = (Profile) extras.get("profile");
        }
        initFormData(profile);

        initButtonHandlers();
    }

    private void initButtonHandlers() {
        Button back = findViewById(R.id.buttonProfileEditBack);
        back.setOnClickListener(v -> finish());

        Button clear = findViewById(R.id.buttonClear);
        clear.setOnClickListener(v -> clearForm());

        Button save = findViewById(R.id.buttonAdd);
        save.setOnClickListener(v -> saveForm());

        SeekBar seekBar = getSeekBarSalaryLevel();
        TextView salary = getTextViewSalary();
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                salary.setText(Integer.valueOf(progress).toString());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private TextView getTextViewSalary() {
        return findViewById(R.id.textViewSalary);
    }

    private SeekBar getSeekBarSalaryLevel() {
        return findViewById(R.id.seekBarSalaryLevel);
    }

    private void initFormData(Profile profile) {
        Spinner branch = getBranch();
        branch.setAdapter(new ArrayAdapter<Branch>(this, android.R.layout.simple_spinner_dropdown_item, branchRepository.getAll()));
        Spinner experience = getExperience();
        experience.setAdapter(new ArrayAdapter<ExperienceLevel>(this, android.R.layout.simple_spinner_dropdown_item, experienceLevelRepository.getAll()));
getRadioGroupEducation();

        clearForm();

        if (profile != null) {
            getName().setText(profile.getName());
            getSurname().setText(profile.getSecondName());
            getPatronymic().setText(profile.getPatronymic());
            getRadioGroupEducation().check(profile.getEducationLevel().getId());
            getBranch().setSelection(profile.getBranch().getId());
            getExperience().setSelection(profile.getExperienceLevel().getId());
            getSeekBarSalaryLevel().setProgress(profile.getSalary());
            getTextViewSalary().setText(Integer.valueOf(profile.getSalary()).toString());
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
        getTextViewSalary().setText("0");

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

}
