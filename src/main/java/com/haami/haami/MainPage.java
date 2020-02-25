package com.haami.haami;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;

public class MainPage extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        TextView h1 = (TextView) findViewById(R.id.haamiText1);
        TextView h2 = (TextView) findViewById(R.id.haamiText2);
        TextView jft = (TextView) findViewById(R.id.justfortodayText);
        TextView bb = (TextView) findViewById(R.id.basicbookText);
        TextView uc = (TextView) findViewById(R.id.usefulcontentText);
        TextView up = (TextView) findViewById(R.id.usefulplacesText);
        TextView u = (TextView) findViewById(R.id.useText);
        Typeface tft = Typeface.createFromAsset(getAssets(), "Bardiya.ttf");

        h1.setTypeface(tft);
        h2.setTypeface(tft);
        jft.setTypeface(tft);
        bb.setTypeface(tft);
        uc.setTypeface(tft);
        up.setTypeface(tft);
        u.setTypeface(tft);

    }

    @Override
    protected void onResume(){
        super.onResume();

        Bundle params = new Bundle();
        params.putString(FirebaseAnalytics.Param.ITEM_ID, "Main_Hit");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM, params);
    }

    public void justForToday(View view){
        Intent intentMainToJFT = new Intent(this, JustForToday.class);
        startActivity(intentMainToJFT);
    }

    public void toWelcome(View view) {
        Intent intentMainToWelcome = new Intent(this, Welcome.class);
        startActivity(intentMainToWelcome);
    }

    public void toBasicBook(View view) {
        Intent intentToBasicBook = new Intent(this, BasicBook.class);
        startActivity(intentToBasicBook);
    }

    public void toUse(View view) {
        Intent intentToUse = new Intent(this, Use.class);
        startActivity(intentToUse);
    }

    public void toUsefulContents(View view) {
        Intent intentToUsefulContent = new Intent(this, UsefulContents.class);
        startActivity(intentToUsefulContent);
    }

    public void toUsefulPlacesAndLinks(View view) {
        Intent intentToUsefulPlacesAndLinks = new Intent(this, UsefulPlacesAndLinks.class);
        startActivity(intentToUsefulPlacesAndLinks);
    }

    public void toAbout (View view) {
        Intent intentToAbout = new Intent(this, About.class);
        startActivity(intentToAbout);
    }

    public void toIntroduction (View view) {
        Intent intentToIntroduction = new Intent(this, Introduction.class);
        startActivity(intentToIntroduction);
    }
}
