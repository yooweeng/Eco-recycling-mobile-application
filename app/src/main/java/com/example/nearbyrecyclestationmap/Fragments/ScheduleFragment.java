package com.example.nearbyrecyclestationmap.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nearbyrecyclestationmap.R;
import com.example.nearbyrecyclestationmap.Utility.LoadingDialog;
import com.example.nearbyrecyclestationmap.databinding.FragmentScheduleBinding;
import com.example.nearbyrecyclestationmap.databinding.FragmentSettingsBinding;
import com.google.firebase.auth.FirebaseAuth;

public class ScheduleFragment extends Fragment {

    private FragmentScheduleBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentScheduleBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Schedule");
    }
}