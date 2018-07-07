package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.models.Tweet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import org.parceler.Parcels;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.ViewHolder> {
    private List<Tweet> mTweets;
    Context context;

    // Pass in the Tweets array in the constructor
    public TweetAdapter(List<Tweet> tweets) {
        mTweets = tweets;
    }

    // For each row, inflate the layout and cache ref into ViewHolder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View tweetView = inflater.inflate(R.layout.item_tweet, parent, false);
        ViewHolder viewHolder = new ViewHolder(tweetView);
        return viewHolder;
    }

    // Bind the values based on the position of the element
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Get data according to position
        Tweet tweet = mTweets.get(position);

        // Populate the views according to this data
        holder.tvUsername.setText(tweet.user.name);
        holder.tvBody.setText(tweet.body);
        String date = getRelativeTimeAgo(tweet.createdAt);
        holder.tvTimestamp.setText(date);
        holder.tvRetweets.setText(tweet.retweet_count);
        holder.tvLikes.setText(tweet.like_count);

        GlideApp.with(context).load(tweet.user.getProfileImageUrl()).transform(new RoundedCornersTransformation(75, 0)).into(holder.ivProfileImage);
    }

    @Override
    public int getItemCount() {
        return mTweets.size();
    }
    // Create ViewHolder class

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView ivProfileImage;
        public TextView tvUsername;
        public TextView tvBody;
        public TextView tvTimestamp;
        public TextView tvRetweets;
        public TextView tvLikes;

        public ViewHolder(View itemView) {
            super(itemView);

            // Perform findViewById lookups
            ivProfileImage = (ImageView) itemView.findViewById(R.id.ivProfileImage);
            tvUsername = (TextView) itemView.findViewById(R.id.tvUserName);
            tvBody = (TextView) itemView.findViewById(R.id.tvBody);
            tvTimestamp = (TextView) itemView.findViewById(R.id.tvTimestamp);
            tvRetweets = (TextView) itemView.findViewById(R.id.tvRetweets);
            tvLikes = (TextView) itemView.findViewById(R.id.tvLikes);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(final View view) {
            // gets item position
            Log.d("TweetAdapter", String.format("Something was clicked"));
            int position = getAdapterPosition();
            // make sure the position is valid, i.e. actually exists in the view

            if (position != RecyclerView.NO_POSITION) {
                // get the tweet at the position, this won't work if the class is static
            }
            Tweet tweet = mTweets.get(position);
            Log.d("TweetAdapter", String.format("Got the tweet"));
            // create intent for the next activity
            Intent i = new Intent(context, TweetDetailActivity.class);
            // serialize the tweet using parceler, use its short name as a key
            i.putExtra(Tweet.class.getSimpleName(), Parcels.wrap(tweet));
            // show the activity
            context.startActivity(i);
        }
    }

    // Clean all elements of the recycler
    public void clear() {
        mTweets.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Tweet> list) {
        mTweets.addAll(list);
        notifyDataSetChanged();
    }

    public String getRelativeTimeAgo(String rawJsonDate) {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";
        try {
            long dateMillis = sf.parse(rawJsonDate).getTime();
            relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
            System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        return relativeDate;
    }

}

