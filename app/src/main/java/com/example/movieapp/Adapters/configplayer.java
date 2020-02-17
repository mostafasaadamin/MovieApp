package com.example.movieapp.Adapters;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class configplayer {
    private static final String Api_Key = "AIzaSyBFdZwdrdeW2fNirSqJ919XG3aZedVW98U";
    YouTubePlayer.OnInitializedListener onInitializedListener;

    configplayer(final String video_key) {
        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(video_key);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
    }

    public void playVideo(YouTubePlayerView youTubePlayerView) {
        youTubePlayerView.initialize(Api_Key, onInitializedListener);
    }
}
