package com.example.perforamancetest.overdraw_custom_view;

import androidx.room.Room;

import com.example.perforamancetest.overdraw_custom_view.data.PersonDataBase;

public class TestApplication extends android.app.Application {
    PersonDataBase personDataBase ;

    @Override
    public void onCreate() {
        super.onCreate();
        personDataBase = Room.databaseBuilder(getApplicationContext(),
                PersonDataBase.class, "personDatabase").allowMainThreadQueries().build();
    }

    public PersonDataBase getPersonDataBase(){
        return personDataBase;
    }
}
