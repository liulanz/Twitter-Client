package com.codepath.apps.restclienttemplate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcel;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.codepath.apps.restclienttemplate.databinding.ActivityComposeBinding;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONException;
import org.parceler.Parcels;

import okhttp3.Headers;

public class ComposeActivity extends AppCompatActivity {
    ActivityComposeBinding activityComposeBinding;
    public static final String TAG = "ComposeActivity";
    public static final int MAX_TWEET_LENGTH = 280;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        final TwitterClient client;
        client = TwitterApplication.getRestClient(this);
        activityComposeBinding =  DataBindingUtil.setContentView(this, R.layout.activity_compose);
        activityComposeBinding.btnTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //error check
                String tweeContent = activityComposeBinding.etCompose.getText().toString();
                if (tweeContent.isEmpty()){
                    Toast.makeText(ComposeActivity.this, "Sorry, your tweet cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (tweeContent.length()>MAX_TWEET_LENGTH){
                    Toast.makeText(ComposeActivity.this, "Sorry, your tweet is too long", Toast.LENGTH_SHORT).show();
                    return;
                }
           //     Toast.makeText(ComposeActivity.this, tweeContent, Toast.LENGTH_LONG).show();
                client.publishTweet(tweeContent, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Headers headers, JSON json) {
                        Log.i(TAG, "onSuccess to publish tweet");
                        try {
                            Tweet tweet = Tweet.fromJson(json.jsonObject);
                            Intent intent = new Intent();
                            intent.putExtra("tweet", Parcels.wrap(tweet));
                            setResult(RESULT_OK, intent);
                            finish();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                        Log.i(TAG, "onFailure to publish tweet", throwable);
                    }
                });
            }
        });
        activityComposeBinding.etCompose.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Fires right as the text is being changed (even supplies the range of text)

                int tweetLength = activityComposeBinding.etCompose.getText().toString().length();

                if (activityComposeBinding.etCompose.length()>MAX_TWEET_LENGTH){
                    activityComposeBinding.etCompose.setTextColor(Color.RED);
                    activityComposeBinding.etValue.setTextColor(Color.RED);
                }
                else{
                    activityComposeBinding.etCompose.setTextColor(Color.BLACK);
                    activityComposeBinding.etValue.setTextColor(Color.BLACK);
                }
                String newText = Integer.toString(tweetLength)+"/280";
                activityComposeBinding.etValue.setText(newText);

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // Fires right after the text has changed


            }
        });
    }
}