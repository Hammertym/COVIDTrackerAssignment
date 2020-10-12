package com.example.covidtrackerassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Calendar;

import static com.example.covidtrackerassignment.DataStorage.dataList;
import static com.example.covidtrackerassignment.DataStorage.instance;

public class SecondActivity extends AppCompatActivity {
    Intent intent;
    TextView msgBox, counterBox;
    String nameMsg;
    int stateMsg;
    ArrayList<StudentData> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        intent = getIntent();
        nameMsg = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        stateMsg = Integer.parseInt(intent.getStringExtra(MainActivity.EXTRA_STATE));
        msgBox = findViewById(R.id.msgBox);
        counterBox = findViewById(R.id.counterBox);
        students = DataStorage.getList().dataList;
        Log.d("MyActivity", "name: " + nameMsg + " state: " + stateMsg);
        displayScreen(nameMsg, stateMsg);
    }

//The method is dependent on the time of day, as the user is only allowed to input a test once a day. HOWEVER: the app does not save data between sessions,
//so you will only be able to enter anyone's name once. Thus if you want to remove that aspect to better test the app, then comment out the sections below.
    public void displayScreen(String name, int state){
        StudentData usedStudent = null;
        boolean test = false;
            for (StudentData s : students){
                if (s.getName().equals(name)){
                    usedStudent = s;
                    usedStudent.setState(state);
                    test = true;
                    break;
                }
            }
            if (usedStudent == null){
            usedStudent = new StudentData(name, state);
            students.add(0, usedStudent);
        }
            //*Comment out from here
            if (test && usedStudent.compareDate(Calendar.getInstance())){
                usedStudent.newDate();
                msgBox.setText(usedStudent.getName() + getString(R.string.time_str));
           } else {
                usedStudent.newDate();
                //to here*/
                if (usedStudent.getState() == -1) {
                    msgBox.setText(usedStudent.getName() + getString(R.string.failed_msg));
                    usedStudent.setFailedCounter(9);
                } else if (usedStudent.getState() == 1 && usedStudent.getFailedCounter() == 0) {
                    msgBox.setText(usedStudent.getName() + getString(R.string.passed_string));
                } else if (usedStudent.getState() == 1 && usedStudent.getFailedCounter() != 0) {
                    msgBox.setText(usedStudent.getName() + getString(R.string.failed_msg));
                    counterBox.setText(usedStudent.getFailedCounter() + getString(R.string.counter_str));
                    usedStudent.decrementFailedCounter();
                }
                //*And here
            }
            //to here*/
        }

    public void returnActivity(View view) {
        finish();
    }
}
