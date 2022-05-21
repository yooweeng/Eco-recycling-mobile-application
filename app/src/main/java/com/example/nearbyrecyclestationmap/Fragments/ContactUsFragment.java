package com.example.nearbyrecyclestationmap.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nearbyrecyclestationmap.R;
import com.example.nearbyrecyclestationmap.databinding.FragmentContactUsBinding;
import com.example.nearbyrecyclestationmap.databinding.FragmentSettingsBinding;

public class ContactUsFragment extends Fragment {

    private FragmentContactUsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentContactUsBinding.inflate(inflater, container, false);

        binding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = "ecogroup000@gmail.com";
                String subject = binding.edtSubject.getText().toString().trim();
                String message = binding.editMessage.getText().toString().trim();

                if(subject.isEmpty())
                {
                    Toast.makeText(requireContext(), "Please add Subject", Toast.LENGTH_SHORT).show();
                }
                else if(message.isEmpty())
                {
                    Toast.makeText(requireContext(), "Please add some Message", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String mail = "mailto:" + email +
                            "?&subject=" + Uri.encode(subject) +
                            "&body=" + Uri.encode(message);

                    Intent send = new Intent(Intent.ACTION_SENDTO);
                    send.setData(Uri.parse(mail));

                    try {
                        startActivity(send);
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(requireContext(), "Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Contact Us");
    }
}