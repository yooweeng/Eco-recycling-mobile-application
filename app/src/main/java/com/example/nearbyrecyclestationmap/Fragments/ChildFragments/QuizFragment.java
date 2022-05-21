package com.example.nearbyrecyclestationmap.Fragments.ChildFragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nearbyrecyclestationmap.Activity.QuizQuestionModel;
import com.example.nearbyrecyclestationmap.R;
import com.example.nearbyrecyclestationmap.databinding.FragmentHomeChildBinding;
import com.example.nearbyrecyclestationmap.databinding.FragmentQuizBinding;

import java.util.ArrayList;
import java.util.List;

public class QuizFragment extends Fragment {

    private FragmentQuizBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentQuizBinding.inflate(inflater, container, false);
        
        binding.btnStart.setOnClickListener(view -> {
            QuizFragmentDirections.ActionBtnQuizToQuestionFragment directions =
                    QuizFragmentDirections.actionBtnQuizToQuestionFragment();

            Navigation.findNavController(getView()).navigate(directions);
        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Quiz");
    }
}