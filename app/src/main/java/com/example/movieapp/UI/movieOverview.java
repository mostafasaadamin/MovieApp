package com.example.movieapp.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.movieapp.Adapters.VideosAdapter;
import com.example.movieapp.POJO.Videos;
import com.example.movieapp.R;
import com.example.movieapp.databinding.ActivityMovieOverviewBinding;

import java.util.Objects;


public class movieOverview extends AppCompatActivity {
   ItemViewModel view;

    VideosAdapter adapter;
    ActivityMovieOverviewBinding binding;
    int ID = -1;
    double popularity=2.0;
    String poster="null",mov_name="",Release_date="",overview="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_overview);
        binding.progressBar.setVisibility(View.VISIBLE);
        view = new ViewModelProvider(this).get(ItemViewModel.class);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        binding.recyclerView.setHasFixedSize(true);
            poster = getIntent().getExtras().getString("poster");
            mov_name = getIntent().getExtras().getString("mov_name");
            Release_date = getIntent().getExtras().getString("Release_date");
            ID = getIntent().getExtras().getInt("ID");
            popularity = getIntent().getExtras().getDouble("popularity");
            overview = getIntent().getExtras().getString("overview");
        binding.movieTitle.setText(mov_name);
        binding.movieReleaseDate.setText(Release_date);
        binding.moviePopularity.setText(Double.toString(popularity));
        binding.movieOverview.setText(overview);
        String url = "https://image.tmdb.org/t/p/w342/".concat(poster);
        Glide.with(movieOverview.this).load(url).placeholder(R.drawable.placeholder).into(binding.ivMoviePoster);
        if (ID != -1)
            view.getVideos(ID);
        binding.progressBar.setVisibility(View.GONE);
        view.movies_videos.observe(this, new Observer<Videos>() {
            @Override
            public void onChanged(Videos videosModel) {
                      if(videosModel!=null) {
                          if(videosModel.getResults().size()>0){
                          adapter = new VideosAdapter(videosModel, movieOverview.this);
                          binding.recyclerView.setAdapter(adapter);
                          Log.i("data", "onChanged: " + videosModel.getResults().toString());
                      }
                      }
                      }
        });
    }
}
