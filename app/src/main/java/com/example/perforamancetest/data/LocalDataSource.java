package com.example.perforamancetest.data;

import android.content.Context;


import java.util.ArrayList;
import java.util.List;

public class LocalDataSource {


    public List<String> getMessages(Context context) {
        List<String> listOfStrings = new ArrayList<>();
        String localString = "Sample String ";
        for (int index = 0; index < 1000; index++) {
            listOfStrings.add(localString + " : " + index);
        }
        return listOfStrings;
    }
}
