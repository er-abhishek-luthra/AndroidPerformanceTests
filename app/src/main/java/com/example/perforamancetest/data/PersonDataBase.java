package com.example.perforamancetest.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;



@Database(entities = {Person.class}, version = 1)
public abstract class PersonDataBase extends RoomDatabase {
    public abstract PersonDao personDao();
}