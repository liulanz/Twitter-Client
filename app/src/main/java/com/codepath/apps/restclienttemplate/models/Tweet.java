package com.codepath.apps.restclienttemplate.models;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.databinding.BindingAdapter;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.TimelineActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

@Parcel
//@Entity(foreignKeys = {@ForeignKey(entity=User.class, parentColumns="id", childColumns="userId"),
//        @ForeignKey(entity=Media.class, parentColumns="id", childColumns="mediaId")})
@Entity(foreignKeys = {@ForeignKey(entity=User.class, parentColumns="id", childColumns="userId"),
        @ForeignKey(entity=Media.class, parentColumns="id", childColumns="mediaId")})
public class Tweet {
    @PrimaryKey
    @ColumnInfo
    public long id;
    @ColumnInfo
    public String body;
    @ColumnInfo
    public String createdAt;
    @ColumnInfo
    public long mediaId;
    @ColumnInfo
    public long userId;
    @ColumnInfo
    public String timestamp = "" ;
    @Ignore
    public User user;

    @Ignore
    public Media media;
    private static final int SECOND_MILLIS = 1000;
    private static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
    private static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;
    private static final int DAY_MILLIS = 24 * HOUR_MILLIS;


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
//        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
//        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
//        sf.setLenient(true);
//        try{
//
//            long time = sf.parse(tweet.createdAt).getTime();
//            long now = System.currentTimeMillis();
//            final long diff = now-time;
//            if (diff < MINUTE_MILLIS) {
//                tweet.timestamp =  "just now";
//            } else if (diff < 2 * MINUTE_MILLIS) {
//                tweet.timestamp =  "a minute ago";
//            } else if (diff < 50 * MINUTE_MILLIS) {
//                tweet.timestamp =  diff / MINUTE_MILLIS + " m";
//            } else if (diff < 90 * MINUTE_MILLIS) {
//                tweet.timestamp =  "an hour ago";
//            } else if (diff < 24 * HOUR_MILLIS) {
//                tweet.timestamp =  diff / HOUR_MILLIS + " h";
//            } else if (diff < 48 * HOUR_MILLIS) {
//                tweet.timestamp =  "yesterday";
//            } else {
//                tweet.timestamp =  diff / DAY_MILLIS + " d";
//            }
//
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        tweet.user = User.fromJson(jsonObject.getJSONObject("user"));
        tweet.userId = tweet.user.id;
        if(jsonObject.has("extended_entities")&& jsonObject.getJSONObject("extended_entities").has("media"))
            tweet.media = Media.fromJson(jsonObject.getJSONObject("extended_entities").getJSONArray("media").getJSONObject(0));
        else {
            tweet.media = new Media();
            tweet.media.type = "";
            tweet.media.id= 0;
            tweet.media.media_url= "";
        }
        tweet.id = jsonObject.getLong("id");
        tweet.mediaId = tweet.media.id;
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

        if(media.type.equals("photo")){
            Glide.with(imageView)
                    .load(Uri.parse(media.media_url))
                    .into(imageView);
        }

    }
}
