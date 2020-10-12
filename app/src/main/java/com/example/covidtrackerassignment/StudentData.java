package com.example.covidtrackerassignment;

import android.util.Log;

import java.util.Calendar;
import java.util.Stack;

public class StudentData {
    String name;
    int failedCounter;
    int state;
    Stack<Calendar> currentTime;

    public StudentData(String name, int state){
        this.name = name;
        this.state = state;
        currentTime = new Stack<>();
        currentTime.push(Calendar.getInstance());
        this.failedCounter = 0;
        Log.d("MyActivity", String.valueOf(currentTime));
    }

    public String getName() {
        return name;
    }


    public int getState() {
        return state;
    }

    public void setState(int state) {
       this.state = state;
    }

    public void newDate() {
        currentTime.push(Calendar.getInstance());
    }

    //This is the major use of stacks. I use it for the peek method, where you can look at the top entry, of course being the most recent date. You can then compare that to the date of any entry, and if
    //the date is the same, the form fails. If it succeeds,
    public boolean compareDate(Calendar instance){
        int firstDay = currentTime.peek().get(Calendar.DAY_OF_MONTH);
        int comparedDay = instance.get(Calendar.DAY_OF_MONTH);
        Log.d("MyActivity", firstDay + " " + comparedDay);
        if (firstDay == comparedDay){
            return true;
        } else {
            return false;
        }
    }

    public int getFailedCounter() {
        return failedCounter;
    }

    public void setFailedCounter(int failedCounter) {
         this.failedCounter = failedCounter;
    }

    public void decrementFailedCounter(){
        this.failedCounter--;
    }
}

