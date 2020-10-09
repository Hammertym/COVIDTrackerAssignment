package com.example.covidtrackerassignment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

public class MainActivity extends AppCompatActivity {
    boolean globalTempCheck, globalTimeCheck;
    ChipGroup questions;
    Toast msg;
    Chip truth;
    TextView mTime;
    CountDownTimer CountDown;
    String givenName;
    EditText nameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        questions = findViewById(R.id.q_select);
        nameText = findViewById(R.id.name_box);
        msg = Toast.makeText(this, R.string.truth_msg, Toast.LENGTH_SHORT);
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

    public StudentData checkAndSend(String name) {
        int length = questions.getChildCount();
        StudentData student;
        for (int i = 0; i < length; i++) {
            Chip chip = (Chip) questions.getChildAt(i);
            if (chip.isChecked() && i != length - 1) {
                student = new StudentData(name, -1);
                return student;
            } else if (chip.isChecked() && i == length - 1) {
                student = new StudentData(name, 1);
                return student;
            }
        }
        return null;
    }

    public void checkChipGroup(View view) {
        StudentData student = checkAndSend(givenName);
        if (student != null){

        } else {
            msg.show();
        }
    }

    public void sendName(View view) {
        Editable tempText = nameText.getText();
        givenName = String.valueOf(tempText);
    }
}
//A Negative 1 means that the test failed, and a positive 1 means it succeeded (negative is checked first and is thus more prioritized). 0 means that the form was not filled out, as no chips were selected.