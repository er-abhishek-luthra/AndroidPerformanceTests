package com.example.perforamancetest;

import androidx.room.Room;

import com.example.perforamancetest.data.Person;
import com.example.perforamancetest.data.PersonDataBase;

public class TestApplication extends android.app.Application {
    PersonDataBase personDataBase ;
    public static String FIRST_NAME = "Abhishek";
    public static String LAST_NAME = "Luthra";

    @Override
    public void onCreate() {
        super.onCreate();
        personDataBase = Room.databaseBuilder(getApplicationContext(),
                PersonDataBase.class, "personDatabase").allowMainThreadQueries().build();
        addDataToDb();
    }

    private void addDataToDb(){
        for( int index =0; index<10000; index++){
            Person person = new Person();
            person.uid = index;
            if(index  == 9800){
                person.firstName = FIRST_NAME;
                person.lastName = LAST_NAME;
            }
            else{
                person.firstName = "Ram" + Math.random();
                person.lastName ="Gupta" + Math.random();
            }
            personDataBase.personDao().insert(person);
        }
    }

    public PersonDataBase getPersonDataBase(){
        return personDataBase;
    }
}
