package com.codepath.apps.restclienttemplate.models;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TweetDao {
    @Query(
//           " SELECT User.*, Tweet.id AS tweet_id, Tweet.mediaId AS tweet_mediaId, Tweet.createdAt " +
//                   "AS tweet_createdAt, Tweet.body as tweet_body, Tweet.timestamp AS tweet_timestamp, " +
//                   " Tweet.userId AS tweet_userId FROM Tweet INNER JOIN User ON Tweet.userId = User.id")
            "SELECT t.*, Media.id AS media_id, Media.type AS media_type, Media.media_url AS media_media_url " +
                    "FROM(SELECT User.*, Tweet.id AS tweet_id, Tweet.mediaId AS tweet_mediaId, Tweet.createdAt AS tweet_createdAt, Tweet.body as tweet_body, Tweet.timestamp AS tweet_timestamp,"
            +"Tweet.userId AS tweet_userId "+
            " FROM Tweet INNER JOIN User ON Tweet.userId = User.id) AS t " +
                    "LEFT JOIN Media ON tweet_mediaId = Media.id"
                )
    List<TweetWithUser> recentItem();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertModel(Tweet... tweets); //get any number of tweet as an array
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertModel(User... users);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertModel(Media... medias);
}
