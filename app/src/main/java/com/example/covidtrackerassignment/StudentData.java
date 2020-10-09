package com.example.covidtrackerassignment;

public class StudentData {
    String name;
    int state;

    public StudentData(String name, int state){
        this.name = name;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
