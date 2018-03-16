package com.codegene.femicodes.roomlivedataviewmodel.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by femicodes on 3/16/2018.
 */
@Dao
@TypeConverters(DateConverter.class)
public interface BorrowDao {

    @Query("select * from Borrow")
    LiveData<List<Borrow>> getAllBorrowedItems();

    @Query("select * from Borrow where id = :id")
    Borrow getItembyId(String id);

    @Insert(onConflict = REPLACE)
    void addBorrow(Borrow borrowModel);

    @Delete
    void deleteBorrow(Borrow borrowModel);

}