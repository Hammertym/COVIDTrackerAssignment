package com.example.covidtrackerassignment;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

public class MainActivity extends AppCompatActivity {
    boolean globalTempCheck, globalTimeCheck;
    static final String EXTRA_MESSAGE = "com.example.android.covidtrackerassignment.extra.MESSAGE";
    static final String EXTRA_STATE = "com.example.android.covidtrackerassignment.extra.STATE";

    ChipGroup questions;
    Toast msg, nameMsg;
    Chip truth;
    TextView mTime;
    CountDownTimer CountDown;
    String givenName;
    private EditText nameText, sendText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        questions = findViewById(R.id.q_select);
        nameText = findViewById(R.id.editTextTextPersonName);
        msg = Toast.makeText(this, R.string.truth_msg, Toast.LENGTH_SHORT);
        nameMsg = Toast.makeText(this, R.string.no_name_msg, Toast.LENGTH_SHORT);
        givenName = null;
    }

    public StudentData checkAndSend(String name) {
        int length = questions.getChildCount();
        StudentData student;
        if (name != null) {
            for (int i = 0; i < length; i++) {
                Chip chip = (Chip) questions.getChildAt(i);
                if (chip.isChecked() && i != length - 1) {
                    student = new StudentData(name, -1);
                    return student;
                } else if (chip.isChecked() && i == length - 1) {
                    Log.d("MyActivity", "Success");
                    student = new StudentData(name, 1);
                    return student;
                }
            }
            student = new StudentData(name, 0);
            return student;
        }
        student = new StudentData(null, 0);
        return student;
    }

    public void checkChipGroup(View view) {
        StudentData student = checkAndSend(this.givenName);
        if (student.getName() == null) {
            nameMsg.show();
        } else if (student.getState() == 0){
            msg.show();
        } else if (student.getState() == 1){
            switchActivities(student.getName(), 1);
        } else {
            switchActivities(student.getName(), -1);
        }
    }

    public void switchActivities(String text, int state){
        Intent intent= new Intent(this, SecondActivity.class);
        intent.putExtra(EXTRA_MESSAGE, text);
        intent.putExtra(EXTRA_STATE, state + "");
        startActivity(intent);
    }

    public void sendName(View view) {
        Editable tempText = nameText.getText();
        this.givenName = String.valueOf(tempText);
        Log.d("MyActivity", "Given name: " + givenName);
    }
}
//A Negative 1 means that the test failed, and a positive 1 means it succeeded (negative is checked first and is thus more prioritized). 0 means that the form was not filled out, as no chips were selected
//or that there was no name inputted, which is tested for if name is null or not.