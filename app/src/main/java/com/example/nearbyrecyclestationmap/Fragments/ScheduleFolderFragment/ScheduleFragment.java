package com.example.nearbyrecyclestationmap.Fragments.ScheduleFolderFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.nearbyrecyclestationmap.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ScheduleFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.schedule_fragment_schedule,container,false);

        //link garbage time with the text view
        //1-7 represent Monday to Sunday respectively
        TextView gt1=view.findViewById(R.id.tvGarbageMon);
        TextView gt2=view.findViewById(R.id.tvGarbageTue);
        TextView gt3=view.findViewById(R.id.tvGarbageWed);
        TextView gt4=view.findViewById(R.id.tvGarbageThurs);
        TextView gt5=view.findViewById(R.id.tvGarbageFri);
        TextView gt6=view.findViewById(R.id.tvGarbageSat);
        TextView gt7=view.findViewById(R.id.tvGarbageSun);

        //link recycle time with the text view
        TextView rt1=view.findViewById(R.id.tvRecycleMon);
        TextView rt2=view.findViewById(R.id.tvRecycleTue);
        TextView rt3=view.findViewById(R.id.tvRecycleWed);
        TextView rt4=view.findViewById(R.id.tvRecycleThurs);
        TextView rt5=view.findViewById(R.id.tvRecycleFri);
        TextView rt6=view.findViewById(R.id.tvRecycleSat);
        TextView rt7=view.findViewById(R.id.tvRecycleSun);

        ImageView icon=view.findViewById(R.id.ivRefresh);
        ImageButton remainder=view.findViewById(R.id.imgbtnRemainder);
        TextView autoRefresh=view.findViewById(R.id.tvAutoRefresh);
        Button refresh=view.findViewById(R.id.btnRefresh);

        setScheduleFromDb(gt1,gt2,gt3,gt4,gt5,gt6,gt7,rt1,rt2,rt3,rt4,rt5,rt6,rt7,autoRefresh,icon);

        remainder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), remainderNotificationActivity.class);
                startActivity(intent);
            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setScheduleFromDb(gt1,gt2,gt3,gt4,gt5,gt6,gt7,rt1,rt2,rt3,rt4,rt5,rt6,rt7,autoRefresh,icon);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Schedule");
    }

    public void setScheduleFromDb(TextView gt1,TextView gt2,TextView gt3,TextView gt4,TextView gt5,TextView gt6,TextView gt7,TextView rt1,
                                  TextView rt2,TextView rt3,TextView rt4,TextView rt5,TextView rt6,TextView rt7,TextView aRefresh,ImageView i){

        FirebaseDatabase db=FirebaseDatabase.getInstance();
        DatabaseReference referenceForWorkDayGarbage=db.getReference().child("schedule").child("garbage");
        DatabaseReference referenceForRecycDayGarbage=db.getReference().child("schedule").child("recycle");

        referenceForWorkDayGarbage.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String mon=dataSnapshot.child("monday").getValue().toString();
                String tues=dataSnapshot.child("tuesday").getValue().toString();
                String wed=dataSnapshot.child("wednesday").getValue().toString();
                String thurs=dataSnapshot.child("thursday").getValue().toString();
                String fri=dataSnapshot.child("friday").getValue().toString();
                String sat=dataSnapshot.child("saturday").getValue().toString();
                String sun=dataSnapshot.child("sunday").getValue().toString();
                gt1.setText(mon);
                gt2.setText(tues);
                gt3.setText(wed);
                gt4.setText(thurs);
                gt5.setText(fri);
                gt6.setText(sat);
                gt7.setText(sun);

                setAnimations(aRefresh,i);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        referenceForRecycDayGarbage.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String mon=dataSnapshot.child("monday").getValue().toString();
                String tues=dataSnapshot.child("tuesday").getValue().toString();
                String wed=dataSnapshot.child("wednesday").getValue().toString();
                String thurs=dataSnapshot.child("thursday").getValue().toString();
                String fri=dataSnapshot.child("friday").getValue().toString();
                String sat=dataSnapshot.child("saturday").getValue().toString();
                String sun=dataSnapshot.child("sunday").getValue().toString();
                rt1.setText(mon);
                rt2.setText(tues);
                rt3.setText(wed);
                rt4.setText(thurs);
                rt5.setText(fri);
                rt6.setText(sat);
                rt7.setText(sun);

                setAnimations(aRefresh,i);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    void setAnimations(TextView aRefresh,ImageView i){
        //update loading animation
        AnimationSet animationSet1=new AnimationSet(true);
        AnimationSet animationSet2=new AnimationSet(true);
        Animation rotate=AnimationUtils.loadAnimation(getContext(),R.anim.rotate);
        Animation fadein=AnimationUtils.loadAnimation(getContext(),R.anim.fadein);
        Animation fadeout=AnimationUtils.loadAnimation(getContext(),R.anim.fadeout);

        animationSet1.addAnimation(rotate);
        animationSet1.addAnimation(fadein);
        animationSet1.addAnimation(fadeout);
        i.startAnimation(animationSet1);

        animationSet1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                i.setVisibility(View.VISIBLE);
                aRefresh.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                i.setVisibility(View.INVISIBLE);
                aRefresh.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animationSet2.addAnimation(fadein);
        animationSet2.addAnimation(fadeout);
        aRefresh.startAnimation(animationSet2);
    }
}
