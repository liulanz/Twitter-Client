package com.codepath.apps.restclienttemplate.models;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class User {
    public String name;
    public String screenName;
    public String profileImageUrl;
    public int favorite;
    public int retweet ;

    public static User fromJson(JSONObject jsonObject) throws JSONException {
        User user = new User();
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
}
