package com.app.healthyremidersystem.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.app.healthyremidersystem.Constants;
import com.app.healthyremidersystem.R;
import com.app.healthyremidersystem.presentation.viewmodels.AddIllnessHistoryViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class MedicalHistoryActivity extends AppCompatActivity {

    @BindView(R.id.editIllnessName)
    TextInputEditText editIllnessName;
    @BindView(R.id.editIllnessYears)
    TextInputEditText editIllnessYears;
    @BindView(R.id.btnAdd)
    Button btnAdd;
    private AddIllnessHistoryViewModel addIllnessHistoryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_history);
        ButterKnife.bind(this);

        addIllnessHistoryViewModel = new ViewModelProvider(this).get(AddIllnessHistoryViewModel.class);
        addIllnessHistoryViewModel.getIllnessHistoryLiveData().observe(this, illnessHistory -> {
            if (illnessHistory != null) {
                Toast.makeText(this, "Illness history is added successfully", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Something wrong is happened, please try again later", Toast.LENGTH_SHORT).show();
            }
        });
        addIllnessHistoryViewModel.getYearsError().observe(this, error -> {
            editIllnessName.setError(error);
        });
        addIllnessHistoryViewModel.getNameError().observe(this, error -> {
            editIllnessYears.setError(error);
        });
    }

    @OnClick(R.id.btnAdd)
    public void onAddClicked() {
        String name = Objects.requireNonNull(editIllnessName.getText()).toString().trim();
        int years = editIllnessYears.getText().toString().isEmpty() ? 0 : Integer.parseInt(editIllnessYears.getText().toString());
        addIllnessHistoryViewModel.addNewIllnessHistory(Constants.getUserId(this), name, years);
    }

    @OnTextChanged(R.id.editIllnessName)
    public void removeDateError() {
        editIllnessName.setError(null);
    }

    @OnTextChanged(R.id.editIllnessYears)
    public void removeTimeError() {
        editIllnessYears.setError(null);
    }
}