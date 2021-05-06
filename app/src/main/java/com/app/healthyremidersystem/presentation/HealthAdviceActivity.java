package com.app.healthyremidersystem.presentation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.app.healthyremidersystem.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HealthAdviceActivity extends AppCompatActivity {

    @BindView(R.id.healthAdviceTextView)
    TextView healthAdviceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_advice);
        ButterKnife.bind(this);

        FirebaseDatabase.getInstance()
                .getReference("advice")
                .getRef()
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String advice = snapshot.getValue(String.class);
                        healthAdviceTextView.setText(advice);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(HealthAdviceActivity.this, "Error while loadin advice, please try again", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @OnClick(R.id.btnBack)
    public void onBackClicked() {
        onBackPressed();
    }
}