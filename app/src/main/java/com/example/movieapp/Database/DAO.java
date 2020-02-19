package com.example.movieapp.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.movieapp.POJO.MovieData;

import java.util.List;

@Dao
public interface DAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovie(MovieData movie);

    @Query("select * from Movies")
    LiveData<List<MovieData>> get_All_Movies();

}
