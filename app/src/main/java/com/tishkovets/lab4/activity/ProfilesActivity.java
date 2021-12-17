package com.tishkovets.lab4.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tishkovets.lab4.R;
import com.tishkovets.lab4.domain.Profile;
import com.tishkovets.lab4.repository.ProfileRepository;
import com.tishkovets.lab4.repository.ProfileRepositoryImpl;

public class ProfilesActivity extends AppCompatActivity {

    public static final String TWO_STRING_ARGS_PATTERN = "%s %s";
    private static final int PROFILE_ID_RESULT = 1;
    private final ProfileRepository profileRepository = ProfileRepositoryImpl.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiles);

        loadProfileList();
        initButtonHandlers();
        getListProfiles().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Profile profile = profileRepository.getById(position);
                String generateProfileInformation = generateProfileInformation(profile);
                TextView profileInformation = findViewById(R.id.profileText);
                profileInformation.setText(generateProfileInformation);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (PROFILE_ID_RESULT == requestCode && resultCode == RESULT_OK) {
            int id = data.getExtras().getInt("id");
            loadProfileList(id);
        }
    }

    private String generateProfileInformation(Profile profile) {
        Resources resources = getResources();
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s %s %s", profile.getName(), profile.getSecondName(), profile.getPatronymic()));
        sb.append(System.getProperty("line.separator"));
        sb.append(String.format(TWO_STRING_ARGS_PATTERN, resources.getString(R.string.textViewEducation), profile.getBranch().getName()));
        sb.append(System.getProperty("line.separator"));
        sb.append(String.format(TWO_STRING_ARGS_PATTERN, resources.getString(R.string.branchLike), profile.getExperienceLevel().getName()));
        sb.append(System.getProperty("line.separator"));
        sb.append(String.format(TWO_STRING_ARGS_PATTERN, resources.getString(R.string.experience), profile.getExperienceLevel().getName()));
        sb.append(System.getProperty("line.separator"));
        sb.append(String.format(TWO_STRING_ARGS_PATTERN, resources.getString(R.string.salary), profile.getExperienceLevel().getName()));
        if (profile.isFullTime() || profile.isPartTime() || profile.isOneTime() || profile.isStaging()) {
            sb.append(System.getProperty("line.separator"));
            sb.append(resources.getString(R.string.employment));
        }
        if (profile.isFullTime()) {
            sb.append(resources.getString(R.string.fullTime));
        }
        if (profile.isPartTime()) {
            sb.append(", ");
            sb.append(resources.getString(R.string.partTime));
        }
        if (profile.isOneTime()) {
            sb.append(", ");
            sb.append(resources.getString(R.string.oneTime));
        }
        if (profile.isStaging()) {
            sb.append(", ");
            sb.append(resources.getString(R.string.staging));
        }
        sb.append(System.getProperty("line.separator"));
        if (!profile.isNoCriminalRecords()) {
            sb.append(resources.getString(R.string.noCryminalRecords));
        } else {
            sb.append(resources.getString(R.string.yesCryminalRecords));
        }
        sb.append(System.getProperty("line.separator"));
        if (!profile.isNoPhysicalRestriction()) {
            sb.append(resources.getString(R.string.noPhysicalBoundaries));
        } else {
            sb.append(resources.getString(R.string.yesPhysicalBoundaries));
        }
        sb.append(System.getProperty("line.separator"));
        sb.append(String.format(TWO_STRING_ARGS_PATTERN, resources.getString(R.string.additionalInformation), profile.getAdditionalInformation()));
        return sb.toString();
    }

    private void loadProfileList() {
        Spinner profileList = getListProfiles();
        profileList.setAdapter(new ArrayAdapter<Profile>(this, android.R.layout.simple_spinner_dropdown_item, profileRepository.getAll()));
    }

    private void loadProfileList(int id) {
        Spinner profileList = getListProfiles();
        loadProfileList();
        profileList.setSelection(id);
    }

    private void initButtonHandlers() {
        Button back = findViewById(R.id.buttonProfilesBack);
        back.setOnClickListener(v -> finish());

        Button edit = findViewById(R.id.buttonProfilesEdit);
        edit.setOnClickListener(v -> {
            Spinner listProfiles = getListProfiles();
            long selectedItemId = listProfiles.getSelectedItemId();
            if (selectedItemId >= 0) {
                editProfile(selectedItemId);
            }
        });

        Button delete = findViewById(R.id.buttonProfilesRemove);
        delete.setOnClickListener(v -> {
            Spinner listProfiles = getListProfiles();
            long selectedItemId = listProfiles.getSelectedItemId();
            if (selectedItemId >= 0) {
                removeProfile(selectedItemId);
            }
        });
    }

    private void removeProfile(long selectedItemId) {
        profileRepository.delete((int) selectedItemId);
        if (!getListProfiles().isSelected()) {
            TextView profileInformation = findViewById(R.id.profileText);
            profileInformation.setText("");
        }
        loadProfileList();
    }

    private void editProfile(long profileId) {
        Intent intent = new Intent(ProfilesActivity.this, ProfileEditActivity.class);
        intent.putExtra("profileId", profileId);
        startActivityForResult(intent, PROFILE_ID_RESULT);
    }

    private Spinner getListProfiles() {
        return findViewById(R.id.listProfiles);
    }

}
