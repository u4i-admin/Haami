package com.haami.haami;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;

public class UsefulPlaces extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_useful_places);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        TextView upTitle = (TextView) findViewById(R.id.useful_places_title);
        TextView upTitle1 = (TextView) findViewById(R.id.useful_places_title1);
        TextView upTitle2 = (TextView) findViewById(R.id.useful_places_title2);
        TextView upTitle3 = (TextView) findViewById(R.id.useful_places_title3);
        TextView upTitle4 = (TextView) findViewById(R.id.useful_places_title4);
        TextView upTitle5 = (TextView) findViewById(R.id.useful_places_title5);
        TextView upTitle6 = (TextView) findViewById(R.id.useful_places_title6);
        TextView upTitle7 = (TextView) findViewById(R.id.useful_places_title7);
        TextView upTitle8 = (TextView) findViewById(R.id.useful_places_title8);
        TextView upTitle9 = (TextView) findViewById(R.id.useful_places_title9);
        TextView upTitle10 = (TextView) findViewById(R.id.useful_places_title10);

        Typeface tf_IranSans = Typeface.createFromAsset(getAssets(), "IRANSansMobile.ttf");
        Typeface tf_IranSans_Bold = Typeface.createFromAsset(getAssets(), "IRANSansMobile_Bold.ttf");
        Typeface tf_IranSans_Light = Typeface.createFromAsset(getAssets(), "IRANSansMobile_Light.ttf");
        Typeface tf_IranSans_Medium = Typeface.createFromAsset(getAssets(), "IRANSansMobile_Medium.ttf");
        Typeface tf_IranSans_UltraLight = Typeface.createFromAsset(getAssets(), "IRANSansMobile_UltraLight.ttf");

        upTitle.setTypeface(tf_IranSans_Bold);
        upTitle1.setTypeface(tf_IranSans_Medium);
        upTitle2.setTypeface(tf_IranSans_Medium);
        upTitle3.setTypeface(tf_IranSans_Medium);
        upTitle4.setTypeface(tf_IranSans_Medium);
        upTitle5.setTypeface(tf_IranSans_Medium);
        upTitle6.setTypeface(tf_IranSans_Medium);
        upTitle7.setTypeface(tf_IranSans_Medium);
        upTitle8.setTypeface(tf_IranSans_Medium);
        upTitle9.setTypeface(tf_IranSans_Medium);
        upTitle10.setTypeface(tf_IranSans_Medium);
    }

    @Override
    protected void onResume(){
        super.onResume();

        Bundle params = new Bundle();
        params.putString(FirebaseAnalytics.Param.ITEM_ID, "UP_Hit");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM, params);
    }

    public void toBasicBook(View view) {
        Intent intentToBasicBook = new Intent(this, BasicBook.class);
        startActivity(intentToBasicBook);
    }

    public void toUsefulContents(View view) {
        Intent intentToUsefulContent = new Intent(this, UsefulContents.class);
        startActivity(intentToUsefulContent);
    }

    public void justForToday(View view){
        Intent intentMainToJFT = new Intent(this, JustForToday.class);
        startActivity(intentMainToJFT);
    }

    public void toUse(View view) {
        Intent intentToUse = new Intent(this, Use.class);
        startActivity(intentToUse);
    }

    public void toPlaces(View view) {
        String stringId = view.getResources().getResourceName(view.getId());
        Intent intentToContents = new Intent(this, Places.class);
        intentToContents.putExtra("text_id", stringId);
        startActivity(intentToContents);
    }

    public void toUsefulPlacesAndLinks(View view) {
        Intent intentToUsefulPlacesAndLinks = new Intent(this, UsefulPlacesAndLinks.class);
        startActivity(intentToUsefulPlacesAndLinks);
    }
    public void start(View view){
        Intent intentWelcometoMain = new Intent(this, MainPage.class);
        startActivity(intentWelcometoMain);
    }
}
