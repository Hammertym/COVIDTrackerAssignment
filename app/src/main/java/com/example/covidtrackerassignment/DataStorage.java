package com.example.covidtrackerassignment;

import java.util.ArrayList;

public class DataStorage {
    static ArrayList<StudentData> dataList = new ArrayList<>();
    static DataStorage instance;

    private DataStorage(){}

    static DataStorage getList(){
        if(instance == null){
            instance = new DataStorage();
        } return instance;
    }
}
//This class is used to save the state of the arraylist that holds all of the data between screens. Essentially, whenever
//Activity Second is called, the latest instance of the arraylist is loaded into the class.