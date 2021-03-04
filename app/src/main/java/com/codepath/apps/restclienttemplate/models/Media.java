package com.codepath.apps.restclienttemplate.models;

import android.util.Log;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
@Entity
public class Media {
    @ColumnInfo
    @PrimaryKey
    long id;
    @ColumnInfo
    public String type;
    @ColumnInfo
    public String media_url;


    Media(){ }


    public static Media fromJson(JSONObject jsonObject) throws JSONException {
        Media media = new Media();
        if(jsonObject.has("type")) {
            media.type = jsonObject.getString("type");

        }
        else
            media.type = "";
        media.id = jsonObject.getLong("id");
        if(media.type.equals("photo")&& jsonObject.has("media_url_https")) {
            media.media_url = jsonObject.getString("media_url_https");
        }
        if(media.type.equals("video")){
           media.media_url = jsonObject.getJSONObject("video_info").getJSONArray("variants").getJSONObject(0).getString("url");

        }


        return media;
    }


    public static List<Media> fromJsonMediaArray(List<Tweet> tweetsFromNetwork) {
        List<Media> medias = new ArrayList<>();
        for(int i = 0; i < tweetsFromNetwork.size(); i++){
            medias.add(tweetsFromNetwork.get(i).media);
            Log.i("TEST", Long.toString(tweetsFromNetwork.get(i).media.id));


        }
        return medias;
    }
}



