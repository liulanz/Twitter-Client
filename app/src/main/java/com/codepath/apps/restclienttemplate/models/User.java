package com.codepath.apps.restclienttemplate.models;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
@Parcel
@Entity
public class User {
    @ColumnInfo
    @PrimaryKey
    public long id;
    @ColumnInfo
    public String name;
    @ColumnInfo
    public String screenName;
    @ColumnInfo
    public String profileImageUrl;
    @ColumnInfo
    public int favorite;
    @ColumnInfo
    public int retweet ;
    @ColumnInfo
    public String username;
    public User (){}

    public static User fromJson(JSONObject jsonObject) throws JSONException {
        User user = new User();
        user.id = jsonObject.getLong("id");
        user.name = jsonObject.getString("name");
        user.screenName = jsonObject.getString("screen_name");
        user.profileImageUrl = jsonObject.getString("profile_image_url_https");
        if(jsonObject.has("favourites_count"))
            user.favorite = jsonObject.getInt("favourites_count");
        else
            user.favorite = 0;

        if(jsonObject.has("retweet_count"))
            user.retweet = jsonObject.getInt("retweet_count");
        else
            user.retweet= 0;
        if(jsonObject.has("username"))
            user.username = jsonObject.getString("username");
        return user;
    }
    @BindingAdapter("android:loadImage")
    public static void loadImage(ImageView imageView, String profileImageUrl){
        int radius = 30;
        int margin = 5;
        Glide.with(imageView)
                .load(profileImageUrl)
                .transform(new RoundedCornersTransformation(radius, margin))
                .into(imageView);
    }




    public static List<User> fromJsonUserArray(List<Tweet> tweetsFromNetwork) {
        List<User> users = new ArrayList<>();
        for(int i = 0; i < tweetsFromNetwork.size(); i++){
            users.add(tweetsFromNetwork.get(i).user);
        }
        return users;
    }
}
