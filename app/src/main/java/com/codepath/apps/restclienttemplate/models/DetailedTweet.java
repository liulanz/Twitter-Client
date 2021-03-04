package com.codepath.apps.restclienttemplate.models;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.databinding.ActivityDetailedTweetBinding;
import com.codepath.apps.restclienttemplate.databinding.ItemTweetBinding;

import org.parceler.Parcels;

public class DetailedTweet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityDetailedTweetBinding activityDetailedTweetBinding = DataBindingUtil.setContentView(this, R.layout.activity_detailed_tweet);
        final VideoView videoView = activityDetailedTweetBinding.videoView;
        Tweet tweet= Parcels.unwrap(getIntent().getParcelableExtra("tweet"));
        activityDetailedTweetBinding.setTweet(tweet);
        if (tweet.media.type.equals("video")) {
            Log.i("Video",tweet.media.media_url);
            MediaController mediaController = new MediaController(this);
            mediaController.setAnchorView(videoView);

            videoView.setMediaController(mediaController);
            videoView.setVideoURI(Uri.parse(tweet.media.media_url));
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                // Close the progress bar and play the video
                public void onPrepared(MediaPlayer mp) {
                    videoView.start();
                }
            });
        }

    }
}