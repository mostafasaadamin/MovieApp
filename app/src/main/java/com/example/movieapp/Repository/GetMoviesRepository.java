package com.example.movieapp.Repository;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.movieapp.POJO.Moviemodel;
import com.example.movieapp.api.connection;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class GetMoviesRepository {

    public  void getAllMovies() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        connection conn = retrofit.create(connection.class);
        Call<Moviemodel> getmovies = conn.getAllMovies("9d4a588f2ff2dd7d0c865e6b24bd1c2c");
        getmovies.enqueue(new Callback<Moviemodel>() {
            @Override
            public void onResponse(Call<Moviemodel> call, Response<Moviemodel> response) {
                Moviemodel moviemodel = response.body();

            }
            @Override
            public void onFailure(Call<Moviemodel> call, Throwable t) {
                Log.i("asd", "onFailure: " + t.getMessage());
            }
        });

    }


}
