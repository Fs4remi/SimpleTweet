package com.codepath.apps.restclienttemplate.models;

import com.codepath.apps.restclienttemplate.TwitterClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Tweet {
    public String createdAt, body;
    public User user;

    //empty constructor
    public Tweet(){}

    public static Tweet fromJson(JSONObject jsonObj) throws JSONException {
        Tweet tweet = new Tweet();
        tweet.body = jsonObj.getString("text");
        tweet.createdAt = formatCreated_at(jsonObj.getString("created_at"));
        tweet.user = User.fromJson(jsonObj.getJSONObject("user"));
        return tweet;
    }
    private static String formatCreated_at(String jsonInfo) {
        String[] separated_careatedAt = jsonInfo.split(" ");
        String date = " " + separated_careatedAt[1] + " " + separated_careatedAt[2];


        return date;
    }
    public static List<Tweet> fromJsonArray(JSONArray jsonArr) throws JSONException {
        List<Tweet> tweets = new ArrayList<Tweet>();
        for(int i = 0; i <jsonArr.length(); i++){
            tweets.add(fromJson(jsonArr.getJSONObject(i)));
        }
        return tweets;
    }
}
