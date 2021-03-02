package com.codepath.apps.restclienttemplate.models;

import android.util.Log;

import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

@Parcel
public class Media {
    public String type;
    public String media_url;


    Media(){ }

    public static Media fromJson(JSONObject jsonObject) throws JSONException {
        Media media = new Media();
        if(jsonObject.has("type")) {
            media.type = jsonObject.getString("type");

        }
        else
            media.type = "";
        if(media.type.equals("photo")&& jsonObject.has("media_url_https")) {
            media.media_url = jsonObject.getString("media_url_https");
        }
        if(media.type.equals("video")){
           media.media_url = jsonObject.getJSONObject("video_info").getJSONArray("variants").getJSONObject(0).getString("url");

        }


        return media;
    }

}



