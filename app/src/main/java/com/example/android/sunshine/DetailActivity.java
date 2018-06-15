package com.example.android.sunshine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private static final String FORECAST_SHARE_HASHTAG = " #SunshineApp";

    private String mForecast;
    private TextView mWeatherDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mWeatherDisplay = (TextView) findViewById(R.id.tv_display_weather);

        Intent intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)) {
                mForecast = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);
                mWeatherDisplay.setText(mForecast);
            }
        }
    }


    // DONE (4) Display the menu and implement the forecast sharing functionality

    private Intent weatherIntent() {
        String mime = "text/plain";
        String concatenatedText = mForecast + FORECAST_SHARE_HASHTAG;
        Intent intent = ShareCompat.IntentBuilder.from(this).setType(mime).setText(concatenatedText).getIntent();
        return intent;
    }
    // DONE (3) Create a menu with an item with id of action_share
    @Override
    public boolean onCreateOptionsMenu(Menu share) {
        getMenuInflater().inflate(R.menu.share_details, share);
        MenuItem shareMenuItem = share.findItem(R.id.action_share);
        shareMenuItem.setIntent(weatherIntent());
        return true;
    }
}