package com.example.covidtrackerassignment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.Object;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

public class MainActivity extends AppCompatActivity {
    boolean globalTempCheck, globalTimeCheck;
    ChipGroup questions;
    Chip truth;
    TextView mTime;
    CountDownTimer CountDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*= new CountDownTimer(600000, 1000) {
       @SuppressLint("SetTextI18n")
       @Override
       public void onTick(long l) {

           mTimer.setTextSize(48);
           mTimer.setText("time remaining:" + Math.round(l / 60000.0) + ":" + (Math.round(l / 1000.0)) % 60);
       }*/

       /*public void onFinish() {
           globalTimeCheck = false;
           mTimer.setTextSize(38);
           mTimer.setText("Enter the form once you have completed all of the questions.");*/

    public void checkChipGroup(View view) {
    }
}