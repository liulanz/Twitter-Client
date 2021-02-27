package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.databinding.ItemTweetBinding;
import com.codepath.apps.restclienttemplate.models.Tweet;

import java.util.List;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder>{
    Context context;
    List<Tweet> tweets;
    public TweetsAdapter(Context context, List<Tweet> tweets){
        this.context = context;
        this.tweets = tweets;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemTweetBinding itemTweetBinding = ItemTweetBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(itemTweetBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tweet tweet = tweets.get(position);
        holder.itemTweetBinding.setTweet(tweet);
        holder.itemTweetBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
       return tweets.size();
    }
    public void clear(){
        tweets.clear();
        notifyDataSetChanged();
    }
    public void addAll(List<Tweet> tweetList){
        tweets.addAll(tweetList);
        notifyDataSetChanged();
    }
    // Pass in the context and list of tweets
    // For each row, inflate the layout
    // Bind values based on the position of the element
    // Define a viewholder
    public class ViewHolder extends RecyclerView.ViewHolder{
        ItemTweetBinding itemTweetBinding;

        public ViewHolder(@NonNull ItemTweetBinding itemTweetBinding) {

            super(itemTweetBinding.getRoot());
            this.itemTweetBinding = itemTweetBinding;

        }

    }
}
