package com.example.movieapp.Adapters;
import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movieapp.Holders.MovieHolder;
import com.example.movieapp.Holders.VideoPlayerViewHolder;
import com.example.movieapp.POJO.MovieData;
import com.example.movieapp.POJO.Moviemodel;
import com.example.movieapp.POJO.Videos;
import com.example.movieapp.POJO.VideosModel;
import com.example.movieapp.R;
import com.example.movieapp.UI.movieOverview;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import org.jetbrains.annotations.NotNull;

public class VideosAdapter extends RecyclerView.Adapter<VideoPlayerViewHolder> {
    Videos videosList ;
    Context context;
    public VideosAdapter(Videos videosList, Context context) {
        this.videosList = videosList;
        this.context = context;
    }
    @Override
    public VideoPlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View form = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie, parent, false);
        VideoPlayerViewHolder sesstion = new VideoPlayerViewHolder(form,context);
        return sesstion;
    }
    @Override
    public void onBindViewHolder(@NotNull final VideoPlayerViewHolder holder, final int position) {
        final YouTubeThumbnailLoader.OnThumbnailLoadedListener  onThumbnailLoadedListener = new YouTubeThumbnailLoader.OnThumbnailLoadedListener(){
            @Override
            public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {

            }

            @Override
            public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                youTubeThumbnailView.setVisibility(View.VISIBLE);
            }
        };

        holder.youTubeThumbnailView.initialize("AIzaSyBFdZwdrdeW2fNirSqJ919XG3aZedVW98U", new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {
             String item = videosList.getResults().get(position).getKey();
                youTubeThumbnailLoader.setVideo(item);
                youTubeThumbnailLoader.setOnThumbnailLoadedListener(onThumbnailLoadedListener);
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
                //write something for failure
            }
        });
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item =  videosList.getResults().get(position).getKey();
                Intent intent = YouTubeStandalonePlayer.createVideoIntent((Activity) context, "AIzaSyBFdZwdrdeW2fNirSqJ919XG3aZedVW98U", item);
               context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return videosList.getResults().size();
    }

}