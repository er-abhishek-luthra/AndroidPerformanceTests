package com.example.perforamancetest.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PersonDao {
    @Query("SELECT * FROM Person")
    List<Person> getAll();

    @Query("SELECT * FROM Person WHERE uid IN (:userIds)")
    List<Person> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM Person WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    Person findByName(String first, String last);

    @Query("SELECT * FROM Person WHERE uid = :id")
    Person findById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)  // or OnConflictStrategy.IGNORE
    void insert(Person user);

    @Delete
    void delete(Person user);
}