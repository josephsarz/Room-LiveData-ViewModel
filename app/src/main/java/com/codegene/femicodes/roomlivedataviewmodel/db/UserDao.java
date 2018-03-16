package com.codegene.femicodes.roomlivedataviewmodel.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by femicodes on 3/16/2018.
 */

@Dao
public interface UserDao {

    @Query("select * from User")
    LiveData<List<User>> getAllUsers();

    @Query("select * from User where id = :id")
    User getItembyId(String id);

    @Insert(onConflict = REPLACE)
    void addUser(User userModel);

    @Delete
    void deleteUser(User userModel);

}
