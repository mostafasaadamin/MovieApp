package com.example.movieapp.api;

import com.example.movieapp.POJO.MovieData;
import com.example.movieapp.POJO.Moviemodel;
import com.example.movieapp.POJO.Videos;
import com.example.movieapp.POJO.VideosModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;
//http://api.themoviedb.org/3/movie/popular?api_key=9d4a588f2ff2dd7d0c865e6b24bd1c2c for all movies
// https://image.tmdb.org/t/p/w342/udDclJoHjfjb8Ekgsd4FDteOkCU.jpg for images


public interface connection {
    @GET("3/movie/popular")
    Call<Moviemodel>getAllMovies(@Query("api_key") String api_key);

    @GET("3/movie/{movie_id}/videos")
    Call<Videos> getMovieVideos(@Path("movie_id") int movie_id, @Query("api_key") String api_key);


    @GET("3/movie/popular")
    Call<Moviemodel>getAllMoviesPaged(@Query("api_key") String api_key,@Query("page") int page);

}
