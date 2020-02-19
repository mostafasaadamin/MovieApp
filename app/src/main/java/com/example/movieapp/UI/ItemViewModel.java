package com.example.movieapp.UI;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.example.movieapp.Adapters.ItemDataSourceFactory;
import com.example.movieapp.Database.DAO;
import com.example.movieapp.Database.database;
import com.example.movieapp.POJO.MovieData;
import com.example.movieapp.POJO.Videos;
import com.example.movieapp.api.connection;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ItemViewModel extends AndroidViewModel {
    public MutableLiveData<Videos> movies_videos = new MutableLiveData<>();
    public LiveData<PagedList<MovieData>> ItemPagedList;
    public LiveData<PageKeyedDataSource<Integer, MovieData>> liveDataSource;
    private DAO Movie_dao;
    private database Movie_database;
    public LiveData<List<MovieData>> data;


    public ItemViewModel(@NonNull Application application) {
        super(application);
        Movie_database = database.getDatabase(application);
        Movie_dao = Movie_database.dao();
        data=Movie_dao.get_All_Movies();
        ItemDataSourceFactory itemDataSourceFactory = new ItemDataSourceFactory();
        //getting the live data source from data source factory
        liveDataSource = itemDataSourceFactory.getItemLiveDataSource();
        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(500).build();
        ItemPagedList = (new LivePagedListBuilder(itemDataSourceFactory, pagedListConfig)).build();
    }

    public void getVideos(int id) {
               Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://api.themoviedb.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            connection conn = retrofit.create(connection.class);
            Call<Videos> getmovies = conn.getMovieVideos(id, "9d4a588f2ff2dd7d0c865e6b24bd1c2c");
            getmovies.enqueue(new Callback<Videos>() {
                @Override
                public void onResponse(Call<Videos> call, Response<Videos> response) {
                    Videos moviemodel = response.body();
                    movies_videos.setValue(moviemodel);
                    Log.i("weya", "onResponse: " + moviemodel.getResults().toString());

                }

                @Override
                public void onFailure(Call<Videos> call, Throwable t) {
                    Log.i("asd", "onFailure: " + t.getMessage());

                }
            });

        }


    }
