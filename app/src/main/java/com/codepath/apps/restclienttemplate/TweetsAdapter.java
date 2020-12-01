package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.List;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder>{
    Context context;
    List<Tweet> tweets;

    public TweetsAdapter(Context con, List<Tweet> twts){
        context = con;
        tweets = twts;
    }
    //for each row, inflate the layout
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tweet,parent,false);
        return new ViewHolder(view);
    }
    //bind values based on the position of the element
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tweet tweet = tweets.get(position);
        holder.bind(tweet);
    }
    @Override
    public int getItemCount() {
        return tweets.size();
    }

    public void clear() {
        tweets.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Tweet> list) {
        tweets.addAll(list);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivProfile;
        TextView tvScreenName, tvBody, tvTimestamp;

        public ViewHolder(@NotNull View itemview){
            super(itemview);
            ivProfile = itemview.findViewById(R.id.ivProfile);
            tvScreenName = itemview.findViewById(R.id.tvScreenName);
            tvBody = itemview.findViewById(R.id.tvBody);
            tvTimestamp = itemview.findViewById(R.id.tvTimestamp);
        }

        public void bind(Tweet tweet){
            tvScreenName.setText(tweet.user.screenName);
            tvBody.setText(tweet.body);
            tvTimestamp.setText(tweet.createdAt);
            Glide.with(context).load(tweet.user.imgURL).into(ivProfile);
        }
    }
}
