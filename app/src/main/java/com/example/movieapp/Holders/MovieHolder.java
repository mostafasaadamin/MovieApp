package com.example.movieapp.Holders;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.R;

public class MovieHolder extends RecyclerView.ViewHolder{
    public TextView Mov_name;
    public ImageView Mov_image;
    public CardView items;
    public MovieHolder(View itemView) {
        super(itemView);
        Mov_name=itemView.findViewById(R.id.cv_movie_title);
        Mov_image=itemView.findViewById(R.id.cv_iv_movie_poster);
        items=itemView.findViewById(R.id.card_view);
    }
}
