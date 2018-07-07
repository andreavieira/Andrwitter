package com.codepath.apps.restclienttemplate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class TweetDetailActivity extends AppCompatActivity {

    // the Tweet to display
    private Tweet tweet;
    // The view objects
    private ImageView ivProfileImage;
    private TextView tvUsername;
    private TextView tvBody;
    private TextView tvHandleName;
    private TextView tvTimestamp;
    private TextView tvRetweets;
    private TextView tvLikes;
   @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_tweet_detail);

       // perform findViewById lookups
       ivProfileImage = (ImageView) findViewById(R.id.ivProfileImage);
       tvUsername = (TextView) findViewById(R.id.tvUserName);
       tvBody = (TextView) findViewById(R.id.tvBody);
       tvHandleName = (TextView) findViewById(R.id.tvHandleName);
       tvTimestamp = (TextView) findViewById(R.id.tvTimestamp);
       tvRetweets = (TextView) findViewById(R.id.tvRetweets);
       tvLikes = (TextView) findViewById(R.id.tvLikes);

       // unwrap the tweet passed in via intent, using its simple name as a key
       tweet = (Tweet) Parcels.unwrap(getIntent().getParcelableExtra(Tweet.class.getSimpleName()));
       //  Log.d("TweetDetailsActivity", String.format("Showing details for '%s'", tweet.getTitle()));

       // set the text in the view object
       tvUsername.setText(tweet.getUser().getName());
       tvBody.setText(tweet.getBody());
       tvHandleName.setText("@" + tweet.getHandleName());
       tvTimestamp.setText(tweet.getCreateAt());
       tvRetweets.setText(tweet.retweet_count + " Retweets");
       tvLikes.setText(tweet.like_count + " Likes");
       GlideApp.with(this)
       .load(tweet.getUser().getProfileImageUrl())
               .transform(new RoundedCornersTransformation(75, 0))
               .into(ivProfileImage);
       }
}

