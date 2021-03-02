package com.codepath.apps.restclienttemplate.models;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.TimelineActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

@Parcel
public class Tweet {
    public String body;
    public String createdAt;
    public User user;
    public long id;
    public Media media;

    public Tweet(){}

    public static Tweet fromJson(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();
        tweet.body = jsonObject.getString("text");
        tweet.createdAt = jsonObject.getString("created_at");
        for (int i = 0 ;  i < tweet.createdAt.length(); i++){
            if (tweet.createdAt.charAt(i) == '+'){
                tweet.createdAt = tweet.createdAt.substring(0, i);
                break;
            }
        }
        tweet.user = User.fromJson(jsonObject.getJSONObject("user"));
        if(jsonObject.has("extended_entities")&& jsonObject.getJSONObject("extended_entities").has("media"))
            tweet.media = Media.fromJson(jsonObject.getJSONObject("extended_entities").getJSONArray("media").getJSONObject(0));
        else {
            tweet.media = new Media();
            tweet.media.type = "";
            tweet.media.media_url= "";
        }
        tweet.id = jsonObject.getLong("id");
        return tweet;
    }

    public static List<Tweet> fromJsonArray(JSONArray jsonArray) throws JSONException {
        List<Tweet> tweets = new ArrayList<>();
        for(int i = 0; i <jsonArray.length();i++){
            tweets.add(fromJson(jsonArray.getJSONObject(i)));
        }
        return tweets;
    }
    @BindingAdapter("android:loadPoster")
    public static void loadPoster(ImageView imageView, Media media){
        Log.i("Media",media.type);
        Log.i("Media", media.media_url);
        if(media.type.equals("photo")){
            Glide.with(imageView)
                    .load(Uri.parse(media.media_url))
                    .into(imageView);
        }

    }
}
