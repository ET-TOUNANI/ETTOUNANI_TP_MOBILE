package com.example.roomdatabasedemo;
import android.app.UiAutomation;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

 @Dao
public interface MainDao {

    // Insert query
    @Insert(onConflict =REPLACE)
    void insert(MainData mainData);
     // Delete query

     @Delete
     void reset(List<MainData> mainData);
     //to do

     // Update query
     @Query("UPDATE table_name SET text= :sText where ID=:sID")
     void update(int sID, String sText);

     // Get all data query
     @Query("SELECT * FROM table_name")
     List<MainData> getAll();


}
