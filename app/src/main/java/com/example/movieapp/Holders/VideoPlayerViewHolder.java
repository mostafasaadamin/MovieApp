package com.example.movieapp.Holders;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.example.movieapp.R;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.google.android.youtube.player.YouTubeThumbnailView;

import io.reactivex.annotations.NonNull;

public class VideoPlayerViewHolder extends RecyclerView.ViewHolder  {
    public RelativeLayout relativeLayoutOverYouTubeThumbnailView;
   public YouTubeThumbnailView youTubeThumbnailView;
    public CardView card;
Context ctx;
    public VideoPlayerViewHolder(View itemView,Context ctx) {
        super(itemView);
        relativeLayoutOverYouTubeThumbnailView =itemView.findViewById(R.id.relativeLayout_over_youtube_thumbnail);
        youTubeThumbnailView =itemView.findViewById(R.id.youtube_thumbnail);
this.ctx=ctx;
card=itemView.findViewById(R.id.card);
}

}