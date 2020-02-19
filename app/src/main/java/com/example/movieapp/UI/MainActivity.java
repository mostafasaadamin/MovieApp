package com.example.movieapp.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;
import androidx.paging.PositionalDataSource;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movieapp.Adapters.IteemDataSource;
import com.example.movieapp.Adapters.MovieAdapters;
import com.example.movieapp.Adapters.PagedListAdapter;
import com.example.movieapp.POJO.MovieData;
import com.example.movieapp.R;
import com.example.movieapp.Repository.NetworkState;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import am.appwise.components.ni.NoInternetDialog;
import io.reactivex.annotations.Nullable;

public class MainActivity extends AppCompatActivity {
    static final String TAG = "MainActivity";
    NoInternetDialog noInternetDialog;
    RecyclerView Movie_Recycler;
    ProgressBar progressBar;
    TextView msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ItemViewModel itemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        final PagedListAdapter adapter = new PagedListAdapter(this);
        Movie_Recycler = findViewById(R.id.rv_movie_list);
        progressBar = findViewById(R.id.progress_bar_popular);
        //progressBar.setVisibility(View.VISIBLE);
        msg = findViewById(R.id.txt_error_popular);
        Movie_Recycler.setLayoutManager(new GridLayoutManager(this, 2));
        Movie_Recycler.setHasFixedSize(true);
        if (!NetworkState.checkInternetConnection(getApplication().getApplicationContext())) {
            itemViewModel.data.observe(this, new Observer<List<MovieData>>() {
                @Override
                public void onChanged(List<MovieData> movieData) {
                    if (movieData.size() > 0) {
                                       MovieAdapters adapters=new MovieAdapters(movieData,getApplicationContext());
                        Movie_Recycler.setAdapter(adapters);
                    }
                    else {
                        noInternetDialog = new NoInternetDialog.Builder(MainActivity.this).build();
                    //    noInternetDialog.show();

                    }

                }
            });
        }else
            {
                itemViewModel.ItemPagedList.observe(this, new Observer<PagedList<MovieData>>() {
                    @Override
                    public void onChanged(@Nullable PagedList<MovieData> items) {
                        progressBar.setVisibility(View.INVISIBLE);
                        if (items != null) {
                            // itemViewModel.Insert_movie(items);

                            adapter.submitList(items);


                            Movie_Recycler.setAdapter(adapter);


                        } else {
                            msg.setVisibility(View.VISIBLE);
                        }
                    }
                });

            }


        }


    private PagedList<MovieData> ConvertToPageList(List<MovieData> movieData) {
        PagedList<MovieData> data = null;
        data.addAll(movieData);
        return data;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(noInternetDialog!=null) {
            noInternetDialog.onDestroy();
        }
    }



}
