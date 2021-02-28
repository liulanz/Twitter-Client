package com.codepath.apps.restclienttemplate.models;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        Tweet tweet= Parcels.unwrap(getIntent().getParcelableExtra("tweet"));



     ActivityDetailedTweetBinding activityDetailedTweetBinding = DataBindingUtil.setContentView(this, R.layout.activity_detailed_tweet);
     activityDetailedTweetBinding.setTweet(tweet);


    }
}