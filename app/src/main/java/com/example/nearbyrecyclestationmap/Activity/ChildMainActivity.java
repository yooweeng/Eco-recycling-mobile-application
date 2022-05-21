package com.example.nearbyrecyclestationmap.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nearbyrecyclestationmap.R;
import com.example.nearbyrecyclestationmap.UserModel;
import com.example.nearbyrecyclestationmap.databinding.ActivityChildMainBinding;
import com.example.nearbyrecyclestationmap.databinding.ActivityMainBinding;
import com.example.nearbyrecyclestationmap.databinding.NavDrawerChildLayoutBinding;
import com.example.nearbyrecyclestationmap.databinding.NavDrawerLayoutBinding;
import com.example.nearbyrecyclestationmap.databinding.ToolbarLayoutBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChildMainActivity extends AppCompatActivity {

    private NavDrawerChildLayoutBinding navDrawerChildLayoutBinding;
    private ActivityChildMainBinding activityChildMainBinding;
    private ToolbarLayoutBinding toolbarLayoutBinding;
    private FirebaseAuth firebaseAuth;
    private CircleImageView imgHeader;
    private TextView txtName, txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        navDrawerChildLayoutBinding = NavDrawerChildLayoutBinding.inflate(getLayoutInflater());
        setContentView(navDrawerChildLayoutBinding.getRoot());
        activityChildMainBinding = navDrawerChildLayoutBinding.childMainActivity;
        toolbarLayoutBinding = activityChildMainBinding.toolbar;

        setSupportActionBar(toolbarLayoutBinding.toolbar);

        firebaseAuth = FirebaseAuth.getInstance();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                navDrawerChildLayoutBinding.navChildDrawer,
                toolbarLayoutBinding.toolbar,
                R.string.open_navigation_drawer,
                R.string.close_navigation_drawer
        );

        navDrawerChildLayoutBinding.navChildDrawer.addDrawerListener(toggle);
        toggle.syncState();

        NavController navController = Navigation.findNavController(this, R.id.fragmentContainerChild);
        NavigationUI.setupWithNavController(
                navDrawerChildLayoutBinding.navigationView,
                navController
        );

        View headerLayout = navDrawerChildLayoutBinding.navigationView.getHeaderView(0);

        imgHeader = headerLayout.findViewById(R.id.imgHeader);
        txtName = headerLayout.findViewById(R.id.txtHeaderName);
        txtEmail = headerLayout.findViewById(R.id.txtHeaderEmail);

        getUserData();
    }

    @Override
    public void onBackPressed() {
        if (navDrawerChildLayoutBinding.navChildDrawer.isDrawerOpen(GravityCompat.START))
            navDrawerChildLayoutBinding.navChildDrawer.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    private void getUserData() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users")
                .child(firebaseAuth.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()){

                    UserModel userModel = snapshot.getValue(UserModel.class);
                    Glide.with(ChildMainActivity.this).load(userModel.getImage()).into(imgHeader);
                    txtName.setText(userModel.getUsername());
                    txtEmail.setText(userModel.getEmail());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}