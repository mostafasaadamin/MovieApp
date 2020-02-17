package com.example.movieapp.Adapters;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movieapp.Holders.MovieHolder;
import com.example.movieapp.POJO.MovieData;
import com.example.movieapp.POJO.Moviemodel;
import com.example.movieapp.R;
import com.example.movieapp.UI.movieOverview;

public class MovieAdapters extends RecyclerView.Adapter<MovieHolder> {
    Moviemodel MovList = new Moviemodel();
    Context context;
    public MovieAdapters(Moviemodel MovList, Context context) {
        this.MovList = MovList;
        this.context = context;
    }
    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View form = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card, parent, false);
        MovieHolder sesstion = new MovieHolder(form);
        return sesstion;
    }
    @Override
    public void onBindViewHolder(MovieHolder holder, final int position) {
        holder.Mov_name.setText(MovList.getResults().get(position).getTitle());
        String url="https://image.tmdb.org/t/p/w342/".concat(MovList.getResults().get(position).getPoster_path());
        Glide.with(context).load(url).into(holder.Mov_image);
        holder.items.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MovieData moviedata=MovList.getResults().get(position);
                Intent i=new Intent(context, movieOverview.class);
                i.putExtra("poster",moviedata.getBackdrop_path());
                i.putExtra("mov_name",moviedata.getTitle());
                i.putExtra("Release_date",moviedata.getRelease_date());
                i.putExtra("Rating",moviedata.getVote_average());
                i.putExtra("popularity",moviedata.getPopularity());
                i.putExtra("overview",moviedata.getOverview());
                i.putExtra("ID",moviedata.getId());
                Log.i("adapt", "onClick: "+moviedata.getTitle()+"overview:"+moviedata.getOverview()+"popul"+moviedata.getPopularity());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
    }
    @Override
    public int getItemCount() {
        return MovList.getResults().size();
    }

}