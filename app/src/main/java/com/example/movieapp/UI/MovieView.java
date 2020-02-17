package com.example.movieapp.UI;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.movieapp.POJO.Moviemodel;
import com.example.movieapp.POJO.Videos;
import com.example.movieapp.POJO.VideosModel;
import com.example.movieapp.Repository.NetworkState;
import com.example.movieapp.api.connection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import static com.example.movieapp.UI.MainActivity.TAG;
public class MovieView extends AndroidViewModel  {
    MutableLiveData<Moviemodel> movies=new MutableLiveData<>();
    MutableLiveData<Videos> movies_videos=new MutableLiveData<>();

    public MovieView(@NonNull Application application) {
    super(application);
    }

    public void getData()
    {
        if(NetworkState.checkInternetConnection(getApplication().getApplicationContext()))
        {
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
                    movies.setValue(moviemodel);

                }
                @Override
                public void onFailure(Call<Moviemodel> call, Throwable t) {
                    Log.i("asd", "onFailure: " + t.getMessage());
                    Toast.makeText(getApplication().getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });




        }
        else
            {

                movies.setValue(null);

                Log.i(TAG, "getData:Network error!!!!! ");
                Toast.makeText(getApplication().getApplicationContext(), "Netwok Error!!", Toast.LENGTH_SHORT).show();
            }

    }
public void getVideos(int id)
{
    if(NetworkState.checkInternetConnection(getApplication().getApplicationContext()))
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        connection conn = retrofit.create(connection.class);
        Call<Videos> getmovies = conn.getMovieVideos(id,"9d4a588f2ff2dd7d0c865e6b24bd1c2c");
        getmovies.enqueue(new Callback<Videos>() {
            @Override
            public void onResponse(Call<Videos> call, Response<Videos> response) {
                Videos moviemodel = response.body();
                 movies_videos.setValue(moviemodel);
                Log.i("weya", "onResponse: "+moviemodel.getResults().toString());

            }
            @Override
            public void onFailure(Call<Videos> call, Throwable t) {
                Log.i("asd", "onFailure: " + t.getMessage());
                Toast.makeText(getApplication().getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
    else
    {
        movies_videos.setValue(null);
        Log.i(TAG, "getData:Network error!!!!! ");
        Toast.makeText(getApplication().getApplicationContext(), "Netwok Error!!", Toast.LENGTH_SHORT).show();
    }
}
}
