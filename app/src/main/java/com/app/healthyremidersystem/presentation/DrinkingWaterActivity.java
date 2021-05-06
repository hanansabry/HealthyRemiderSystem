package com.app.healthyremidersystem.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.app.healthyremidersystem.Helper;
import com.app.healthyremidersystem.R;
import com.app.healthyremidersystem.domain.UserRepositoryImpl;
import com.app.healthyremidersystem.presentation.viewmodels.UserViewModel;

import java.util.Locale;

public class DrinkingWaterActivity extends AppCompatActivity {

    @BindView(R.id.litresTextView)
    TextView litresTextView;
    @BindView(R.id.loadingTextView)
    TextView loadingTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinking_water);
        ButterKnife.bind(this);

        litresTextView.setVisibility(View.INVISIBLE);
        loadingTextView.setVisibility(View.VISIBLE);
        UserViewModel userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.getUser(new Helper(this).getUserId());
        userViewModel.getUserMutableLiveData().observe(this, user -> {
            double weight = user.getWeight();
            double litres = weight * 0.033;
            litresTextView.setVisibility(View.VISIBLE);
            loadingTextView.setVisibility(View.INVISIBLE);
            litresTextView.setText(String.format(Locale.US, "%.1f L", litres));
        });
    }

    @OnClick(R.id.btnBack)
    public void onBackClicked() {
        onBackPressed();
    }
}