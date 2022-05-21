package com.example.nearbyrecyclestationmap.Fragments.ScheduleFolderFragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nearbyrecyclestationmap.R;

import java.util.ArrayList;
import java.util.Calendar;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Schedule> list;

    private int notificationId=1;

    public MyAdapter(Context context, ArrayList<Schedule> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_recycleview_remaindernotification,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Schedule scheduleType=list.get(position);
        if(position==1){
            holder.tvRemainderNotiTitle.setText("Recycle");
        }
        holder.tvTime1.setText(scheduleType.getMonday());
        holder.tvTime2.setText(scheduleType.getTuesday());
        holder.tvTime3.setText(scheduleType.getWednesday());
        holder.tvTime4.setText(scheduleType.getThursday());
        holder.tvTime5.setText(scheduleType.getFriday());
        holder.tvTime6.setText(scheduleType.getSaturday());
        holder.tvTime7.setText(scheduleType.getSunday());

        holder.scheduleType=scheduleType;

        Intent intent=new Intent(context,AlarmNotification.class);
        intent.putExtra("notificationId",notificationId);
        intent.putExtra("message","Good day, preset schedule will be met in about 30 mins!");

        PendingIntent alarmIntent=PendingIntent.getBroadcast(
                context,0,intent,PendingIntent.FLAG_CANCEL_CURRENT
        );

        AlarmManager alarmManager=(AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        //to save switch status when activity is changed
        SharedPreferences switchesSetting=context.getSharedPreferences("save",0);
        //first item in the arraylist which is switch for the schedule of Garbage
        if(position==0){
            holder.sw1.setChecked(switchesSetting.getBoolean("sw10",false));
            holder.sw2.setChecked(switchesSetting.getBoolean("sw20",false));
            holder.sw3.setChecked(switchesSetting.getBoolean("sw30",false));
            holder.sw4.setChecked(switchesSetting.getBoolean("sw40",false));
            holder.sw5.setChecked(switchesSetting.getBoolean("sw50",false));
            holder.sw6.setChecked(switchesSetting.getBoolean("sw60",false));
            holder.sw7.setChecked(switchesSetting.getBoolean("sw70",false));
        }
        //second item in the arraylist which is switch for the schedule of Recycle
        if(position==1){
            holder.sw1.setChecked(switchesSetting.getBoolean("sw11",false));
            holder.sw2.setChecked(switchesSetting.getBoolean("sw21",false));
            holder.sw3.setChecked(switchesSetting.getBoolean("sw31",false));
            holder.sw4.setChecked(switchesSetting.getBoolean("sw41",false));
            holder.sw5.setChecked(switchesSetting.getBoolean("sw51",false));
            holder.sw6.setChecked(switchesSetting.getBoolean("sw61",false));
            holder.sw7.setChecked(switchesSetting.getBoolean("sw71",false));
        }

        holder.sw1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(b){
                    int time=Integer.parseInt(scheduleType.getMonday());
                    long alarmStartTime=setNotificationTime(time);

                    alarmManager.set(AlarmManager.RTC_WAKEUP,alarmStartTime,alarmIntent);
                    Toast.makeText(context, "Reminder set on Monday prior 30min before the schedule",Toast.LENGTH_SHORT).show();
                }
                else{
                    alarmManager.cancel(alarmIntent);
                    Toast.makeText(context, "Reminder cancel",Toast.LENGTH_SHORT).show();
                }

                SharedPreferences switchesSetting=context.getSharedPreferences("save",0);
                SharedPreferences.Editor editor=switchesSetting.edit();
                if(position==0){
                    editor.putBoolean("sw10", holder.sw1.isChecked());
                }
                if(position==1){
                    editor.putBoolean("sw11", holder.sw1.isChecked());
                }
                editor.commit();
            }
        });

        holder.sw2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(b){
                    int time=Integer.parseInt(scheduleType.getTuesday());
                    long alarmStartTime=setNotificationTime(time);

                    alarmManager.set(AlarmManager.RTC_WAKEUP,alarmStartTime,alarmIntent);
                    Toast.makeText(context, "Reminder set on Tuesday prior 30min before the schedule",Toast.LENGTH_SHORT).show();
                }
                else{
                    alarmManager.cancel(alarmIntent);
                    Toast.makeText(context, "Reminder cancel",Toast.LENGTH_SHORT).show();
                }

                SharedPreferences switchesSetting=context.getSharedPreferences("save",0);
                SharedPreferences.Editor editor=switchesSetting.edit();
                if(position==0){
                    editor.putBoolean("sw20", holder.sw2.isChecked());
                }
                if(position==1){
                    editor.putBoolean("sw21", holder.sw2.isChecked());
                }
                editor.commit();
            }
        });

        holder.sw3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(b){
                    int time=Integer.parseInt(scheduleType.getWednesday());
                    long alarmStartTime=setNotificationTime(time);

                    alarmManager.set(AlarmManager.RTC_WAKEUP,alarmStartTime,alarmIntent);
                    Toast.makeText(context, "Reminder set on Wednesday prior 30min before the schedule",Toast.LENGTH_SHORT).show();
                }
                else{
                    alarmManager.cancel(alarmIntent);
                    Toast.makeText(context, "Reminder cancel",Toast.LENGTH_SHORT).show();
                }

                SharedPreferences switchesSetting=context.getSharedPreferences("save",0);
                SharedPreferences.Editor editor=switchesSetting.edit();
                if(position==0){
                    editor.putBoolean("sw30", holder.sw3.isChecked());
                }
                if(position==1){
                    editor.putBoolean("sw31", holder.sw3.isChecked());
                }
                editor.commit();
            }
        });

        holder.sw4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(b){
                    int time=Integer.parseInt(scheduleType.getThursday());
                    long alarmStartTime=setNotificationTime(time);

                    alarmManager.set(AlarmManager.RTC_WAKEUP,alarmStartTime,alarmIntent);
                    Toast.makeText(context, "Reminder set on Thursday prior 30min before the schedule",Toast.LENGTH_SHORT).show();
                }
                else{
                    alarmManager.cancel(alarmIntent);
                    Toast.makeText(context, "Reminder cancel",Toast.LENGTH_SHORT).show();
                }

                SharedPreferences switchesSetting=context.getSharedPreferences("save",0);
                SharedPreferences.Editor editor=switchesSetting.edit();
                if(position==0){
                    editor.putBoolean("sw40", holder.sw4.isChecked());
                }
                if(position==1){
                    editor.putBoolean("sw41", holder.sw4.isChecked());
                }
                editor.commit();
            }
        });

        holder.sw5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(b){
                    int time=Integer.parseInt(scheduleType.getFriday());
                    long alarmStartTime=setNotificationTime(time);

                    alarmManager.set(AlarmManager.RTC_WAKEUP,alarmStartTime,alarmIntent);
                    Toast.makeText(context, "Reminder set on Friday prior 30min before the schedule",Toast.LENGTH_SHORT).show();
                }
                else{
                    alarmManager.cancel(alarmIntent);
                    Toast.makeText(context, "Reminder cancel",Toast.LENGTH_SHORT).show();
                }

                SharedPreferences switchesSetting=context.getSharedPreferences("save",0);
                SharedPreferences.Editor editor=switchesSetting.edit();
                if(position==0){
                    editor.putBoolean("sw50", holder.sw5.isChecked());
                }
                if(position==1){
                    editor.putBoolean("sw51", holder.sw5.isChecked());
                }
                editor.commit();
            }
        });

        holder.sw6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(b){
                    int time=Integer.parseInt(scheduleType.getSaturday());
                    long alarmStartTime=setNotificationTime(time);

                    alarmManager.set(AlarmManager.RTC_WAKEUP,alarmStartTime,alarmIntent);
                    Toast.makeText(context, "Reminder set on Saturday prior 30min before the schedule",Toast.LENGTH_SHORT).show();
                }
                else{
                    alarmManager.cancel(alarmIntent);
                    Toast.makeText(context, "Reminder cancel",Toast.LENGTH_SHORT).show();
                }

                SharedPreferences switchesSetting=context.getSharedPreferences("save",0);
                SharedPreferences.Editor editor=switchesSetting.edit();
                if(position==0){
                    editor.putBoolean("sw60", holder.sw6.isChecked());
                }
                if(position==1){
                    editor.putBoolean("sw61", holder.sw6.isChecked());
                }
                editor.commit();
            }
        });

        holder.sw7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(b){
                    int time=Integer.parseInt(scheduleType.getSunday());
                    long alarmStartTime=setNotificationTime(time);

                    alarmManager.set(AlarmManager.RTC_WAKEUP,alarmStartTime,alarmIntent);
                    Toast.makeText(context, "Reminder set on Sunday prior 30min before the schedule",Toast.LENGTH_SHORT).show();
                }
                else{
                    alarmManager.cancel(alarmIntent);
                    Toast.makeText(context, "Reminder cancel",Toast.LENGTH_SHORT).show();
                }

                SharedPreferences switchesSetting=context.getSharedPreferences("save",0);
                SharedPreferences.Editor editor=switchesSetting.edit();
                if(position==0){
                    editor.putBoolean("sw70", holder.sw7.isChecked());
                }
                if(position==1){
                    editor.putBoolean("sw71", holder.sw7.isChecked());
                }
                editor.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvRemainderNotiTitle;
        TextView tvTime1,tvTime2,tvTime3,tvTime4,tvTime5,tvTime6,tvTime7;
        Schedule scheduleType;
        Switch sw1,sw2,sw3,sw4,sw5,sw6,sw7;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvRemainderNotiTitle=itemView.findViewById(R.id.tvRemainderNotiTitle);
            tvTime1=itemView.findViewById(R.id.tvTime1);
            tvTime2=itemView.findViewById(R.id.tvTime2);
            tvTime3=itemView.findViewById(R.id.tvTime3);
            tvTime4=itemView.findViewById(R.id.tvTime4);
            tvTime5=itemView.findViewById(R.id.tvTime5);
            tvTime6=itemView.findViewById(R.id.tvTime6);
            tvTime7=itemView.findViewById(R.id.tvTime7);
            sw1=itemView.findViewById(R.id.switch1);
            sw2=itemView.findViewById(R.id.switch2);
            sw3=itemView.findViewById(R.id.switch3);
            sw4=itemView.findViewById(R.id.switch4);
            sw5=itemView.findViewById(R.id.switch5);
            sw6=itemView.findViewById(R.id.switch6);
            sw7=itemView.findViewById(R.id.switch7);
        }

    }

    //convert time from 24hrs based to hour and minute
    public long setNotificationTime(int time){  //time in 24hrs eg.1320 for 1.20pm
        int hour=time/100;  //convert to hour eg.13hr
        int min=time%100;   //convert to min eg.20min
        min=min-30;     //change the time to remain user 30min before the schedule met

        if(min<0){      //convert time to proper value if the time get deducted to negative value
            hour--;     //assume time 13hr 20min will become eg. 12hr
            min=min+60;     //will become eg. 50min
        }

        Calendar startTime=Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY,hour);   //24hrs system
        startTime.set(Calendar.MINUTE,min);
        startTime.set(Calendar.SECOND,0);
        long alarmStartTime=startTime.getTimeInMillis();

        return alarmStartTime;
    }
}
