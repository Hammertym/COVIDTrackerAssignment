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
    boolean globalTempCheck = false, globalTimeCheck = false;
    ChipGroup questions = findViewById(R.id.q_select);
    Chip truth = findViewById(R.id.qfive);
    TextView mTimer = findViewById(R.id.timer);
    CountDownTimer CountDown = new CountDownTimer(600000, 1000) {
       @SuppressLint("SetTextI18n")
       @Override
       public void onTick(long l) {

           mTimer.setTextSize(48);
           mTimer.setText("time remaining:" + Math.round(l / 60000.0) + ":" + (Math.round(l / 1000.0)) % 60);
       }

       @SuppressLint("SetTextI18n")
       @Override
       public void onFinish() {
           globalTimeCheck = false;
           mTimer.setTextSize(38);
           mTimer.setText("Enter the form once you have completed all of the questions.");
       }
   };
    Toast msg1 = Toast.makeText(this, R.string.truth_msg, Toast.LENGTH_SHORT);
    Toast msg2 = Toast.makeText(this, R.string.unchecked_msg, Toast.LENGTH_SHORT);
    Toast msg3 = Toast.makeText(this, R.string.safe_msg, Toast.LENGTH_SHORT);
    Toast msg4 = Toast.makeText(this, R.string.timer_msg, Toast.LENGTH_SHORT);
    Toast msg5 = Toast.makeText(this, R.string.running_msg, Toast.LENGTH_SHORT);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void displayMsg(int i) {
        switch (i) {
            case 1:
                msg1.show();
            case 2:
                msg2.show();
            case 3:
                msg3.show();
            case 4:
                msg4.show();
            case 5:
                msg5.show();
        }
    }

    public void checkChipGroup(View view) {
        if (!globalTimeCheck) {
            boolean tempCheck = false;
            if (!(truth.isChecked())) {
                displayMsg(1);
            } else {
                for (int j = 0; j < questions.getChildCount() - 1; j++) {
                    Chip chip = (Chip) questions.getChildAt(j);
                    if (chip.isChecked())
                        if (j == 1) {
                            tempCheck = true;
                        } else {
                            displayMsg(2);
                            break;
                        }
                }
                if (tempCheck && globalTempCheck)
                    displayMsg(4);
                else
                    displayMsg(3);
            }
        }
        else
            displayMsg(5);
    }

    public void startTimer() {
        globalTempCheck = true;
        globalTimeCheck = true;
       CountDown.start();
    }
}