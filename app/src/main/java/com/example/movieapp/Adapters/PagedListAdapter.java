package com.example.movieapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.bumptech.glide.Glide;
import com.example.movieapp.Database.DAO;
import com.example.movieapp.Database.database;
import com.example.movieapp.Holders.MovieHolder;
import com.example.movieapp.POJO.MovieData;
import com.example.movieapp.POJO.Moviemodel;
import com.example.movieapp.R;
import com.example.movieapp.UI.ItemViewModel;
import com.example.movieapp.UI.movieOverview;

public class PagedListAdapter extends androidx.paging.PagedListAdapter<MovieData,MovieHolder> {
Context context;
    private DAO Movie_dao;
    private database Movie_database;
    public PagedListAdapter(Context context)
    {
        super(DIFF_CALLBACK);
        this.context=context;
        Movie_database = database.getDatabase(context);
        Movie_dao = Movie_database.dao();
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.movie_card,parent,false);
        MovieHolder movieHolder=new MovieHolder(view);
        return movieHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, final int position) {
        final MovieData moviemodel=getItem(position);
        assert moviemodel != null;
        holder.Mov_name.setText(moviemodel.getTitle());
        String url="https://image.tmdb.org/t/p/w342/".concat(moviemodel.getPoster_path());
        Glide.with(context).load(url).into(holder.Mov_image);
        Insert_movies task = new Insert_movies(Movie_dao);
        task.execute(moviemodel);
        holder.items.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context, movieOverview.class);
                i.putExtra("poster",moviemodel.getBackdrop_path());
                i.putExtra("mov_name",moviemodel.getTitle());
                i.putExtra("Release_date",moviemodel.getRelease_date());
                i.putExtra("Rating",moviemodel.getVote_average());
                i.putExtra("popularity",moviemodel.getPopularity());
                i.putExtra("overview",moviemodel.getOverview());
                i.putExtra("ID",moviemodel.getId());
                Log.i("adapt", "onClick: "+moviemodel.getTitle()+"overview:"+moviemodel.getOverview()+"popul"+moviemodel.getPopularity());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
    }
    private  static DiffUtil.ItemCallback<MovieData> DIFF_CALLBACK=new DiffUtil.ItemCallback<MovieData>() {
        @Override
        public boolean areItemsTheSame(@NonNull MovieData oldItem, @NonNull MovieData newItem) {

            return oldItem.getId()==newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull MovieData oldItem, @NonNull MovieData newItem) {

            return oldItem.equals(newItem);
        }
    };

    private static class Insert_movies extends AsyncTask<MovieData, Void, Void> {
        DAO c_dao;

        public Insert_movies(DAO c_dao) {
            this.c_dao = c_dao;
        }

        @Override
        protected void onPreExecute() {

            Log.i("darsh", "worked");
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(MovieData... movieEntity) {
            c_dao.insertMovie(movieEntity[0]);
            Log.i("go", "doInBackground: inserted");
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Log.i("goo", "inserted");
            //Toast.makeText(, "", Toast.LENGTH_SHORT).show();
            super.onPostExecute(aVoid);
        }
    }
}
