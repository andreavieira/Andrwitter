package com.codepath.apps.restclienttemplate.models;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

@Parcel
public class Tweet {

    // list attributes
    public String body;
    public long uid; //DB ID for the tweet
    public User user;
    public String createdAt;
    public String handleName;
    public String retweet_count;
    public String like_count;

    // deserialize the JSON
    public static Tweet fromJSON(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();

        // extract the values from JSON
        tweet.body = jsonObject.getString("text");
        tweet.uid = jsonObject.getLong("id");
        tweet.createdAt = jsonObject.getString("created_at");
        tweet.user = User.fromJSON(jsonObject.getJSONObject("user"));
        tweet.handleName = tweet.user.screenName;
        tweet.retweet_count  = jsonObject.getString("retweet_count");
        tweet.like_count  = jsonObject.getString("favorite_count");
        return tweet;
    }

    public String getBody() {
        return body;
    }

    public long getUid() {
        return uid;
    }

    public User getUser() {
        return user;
    }

    public String getCreateAt() {
        return createdAt;
    }

    public String getHandleName() {
        return handleName;
    }

    public String getRetweet_count() {
        return retweet_count;
    }

    public String getLike_count() {
        return like_count;
    }
}
