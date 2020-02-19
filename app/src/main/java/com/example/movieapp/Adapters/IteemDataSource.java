package com.example.movieapp.Adapters;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.movieapp.POJO.MovieData;
import com.example.movieapp.POJO.Moviemodel;
import com.example.movieapp.Repository.NetworkState;
import com.example.movieapp.api.connection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class IteemDataSource extends PageKeyedDataSource<Integer, MovieData> {


    public static  int PAGE_SIZE = 2;

    //we will start from the first page which is 1
    private static  int FIRST_PAGE = 1;
//
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, MovieData> callback) {
        Log.i("sizyy", "loadInitial: "+PAGE_SIZE);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://api.themoviedb.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            connection conn = retrofit.create(connection.class);
            Call<Moviemodel> getmovies = conn.getAllMoviesPaged("9d4a588f2ff2dd7d0c865e6b24bd1c2c",FIRST_PAGE);
            getmovies.enqueue(new Callback<Moviemodel>() {
                @Override
                public void onResponse(Call<Moviemodel> call, Response<Moviemodel> response) {
                    if(response.body()!=null) {
                        Moviemodel moviemodel = response.body();
                    callback.onResult(moviemodel.getResults(),null,FIRST_PAGE+1);
                    }
                }
                @Override
                public void onFailure(Call<Moviemodel> call, Throwable t) {
                    Log.i("asd", "onFailure: " + t.getMessage());
                }
            });




        }



    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, MovieData> callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        connection conn = retrofit.create(connection.class);
        Call<Moviemodel> getmovies = conn.getAllMoviesPaged("9d4a588f2ff2dd7d0c865e6b24bd1c2c",params.key);
        getmovies.enqueue(new Callback<Moviemodel>() {
            @Override
            public void onResponse(Call<Moviemodel> call, Response<Moviemodel> response) {
                Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;
                if(response.body()!=null) {
                    Moviemodel moviemodel = response.body();
                    callback.onResult(moviemodel.getResults(),adjacentKey);
                }
            }
            @Override
            public void onFailure(Call<Moviemodel> call, Throwable t) {
                Log.i("asd", "onFailure: " + t.getMessage());
            }
        });

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, MovieData> callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        connection conn = retrofit.create(connection.class);
        Call<Moviemodel> getmovies = conn.getAllMoviesPaged("9d4a588f2ff2dd7d0c865e6b24bd1c2c",params.key);
        getmovies.enqueue(new Callback<Moviemodel>() {
            @Override
            public void onResponse(Call<Moviemodel> call, Response<Moviemodel> response) {
                if(response.body()!=null) {
                Integer adjacentKey = (params.key < response.body().getTotal_pages()) ? params.key + 1 : null;
                Log.i("LOL", "onResponse: "+response.body().getTotal_pages());

                    Moviemodel moviemodel = response.body();
                    callback.onResult(moviemodel.getResults(),adjacentKey);
                }
            }
            @Override
            public void onFailure(Call<Moviemodel> call, Throwable t) {
                Log.i("asd", "onFailure: " + t.getMessage());
            }
        });

    }
}
