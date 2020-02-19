package com.example.movieapp.Database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.movieapp.POJO.MovieData;

@Database(entities = {MovieData.class}, version = 1)
public abstract class database  extends RoomDatabase
    {
        public abstract DAO dao ();
        private static database instance;
        public static synchronized database getDatabase (Context con)
        {
            if (instance == null) {
                instance = Room.databaseBuilder(con.getApplicationContext(),database.class,"Movies")
                        .fallbackToDestructiveMigration()
                        .build();
            }
            return instance;
        }

    }


