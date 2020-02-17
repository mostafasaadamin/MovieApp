package com.example.movieapp.UI;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.movieapp.Adapters.MovieAdapters;
import com.example.movieapp.POJO.Moviemodel;
import com.example.movieapp.R;
public class MainActivity extends AppCompatActivity {
    static final String TAG = "MainActivity";
    MovieView view;
    RecyclerView Movie_Recycler;
    MovieAdapters adapter;
    ProgressBar progressBar;
    TextView msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Movie_Recycler=findViewById(R.id.rv_movie_list);
        progressBar=findViewById(R.id.progress_bar_popular);
        progressBar.setVisibility(View.VISIBLE);
        msg=findViewById(R.id.txt_error_popular);
        Movie_Recycler.setLayoutManager(new GridLayoutManager(this,2));
        Movie_Recycler.setHasFixedSize(true);
       view= ViewModelProviders.of(this).get(MovieView.class);
        view.getData();
       view.movies.observe(this, new Observer<Moviemodel>() {
           @Override
           public void onChanged(Moviemodel moviemodel) {
               progressBar.setVisibility(View.GONE);
               if(moviemodel!=null) {
                   msg.setVisibility(View.INVISIBLE);
                   adapter = new MovieAdapters(moviemodel, MainActivity.this);
                   Movie_Recycler.setAdapter(adapter);
               }
               else
                   {
                    msg.setVisibility(View.VISIBLE);
                   }
               }
       });






    }

}
