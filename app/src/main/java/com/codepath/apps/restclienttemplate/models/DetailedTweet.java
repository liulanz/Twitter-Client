package com.codepath.apps.restclienttemplate.models;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.databinding.ActivityDetailedTweetBinding;
import com.codepath.apps.restclienttemplate.databinding.ItemTweetBinding;

import org.parceler.Parcels;

public class DetailedTweet extends AppCompatActivity {
    TextView tvName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tvName = findViewById(R.id.tvName);
        setContentView(R.layout.activity_detailed_tweet);

        Tweet tweet= Parcels.unwrap(getIntent().getParcelableExtra("tweet"));
//        Tweet tweet =
//        LayoutInflater layoutInflater = LayoutInflater.from(this);
//
//        ActivityDetailedTweetBinding activityDetailedTweetBinding = ActivityDetailedTweetBinding.inflate(layoutInflater);
//        activityDetailedTweetBinding.setTweet(tweet);
//        activityDetailedTweetBinding.executePendingBindings();

    }
}