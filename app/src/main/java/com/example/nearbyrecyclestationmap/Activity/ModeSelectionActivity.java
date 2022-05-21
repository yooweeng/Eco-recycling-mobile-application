package com.example.nearbyrecyclestationmap.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.nearbyrecyclestationmap.R;
import com.example.nearbyrecyclestationmap.databinding.ActivityLoginBinding;
import com.example.nearbyrecyclestationmap.databinding.ActivityModeSelectionBinding;

public class ModeSelectionActivity extends AppCompatActivity {

    private ActivityModeSelectionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityModeSelectionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnChild.setOnClickListener(view -> {
            startActivity(new Intent(ModeSelectionActivity.this, ChildMainActivity.class));
        });

        binding.btnAdult.setOnClickListener(view -> {
            startActivity(new Intent(ModeSelectionActivity.this, MainActivity.class));
        });
    }
}