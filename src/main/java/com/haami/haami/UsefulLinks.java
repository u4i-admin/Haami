package com.haami.haami;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;

public class UsefulLinks extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_useful_links);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        TextView ulTitle = (TextView) findViewById(R.id.useful_links_title);
        TextView ulTitle1 = (TextView) findViewById(R.id.useful_links_title1);
        TextView ulTitle2 = (TextView) findViewById(R.id.useful_links_title2);
        TextView ulTitle3 = (TextView) findViewById(R.id.useful_links_title3);
        TextView ulTitle4 = (TextView) findViewById(R.id.useful_links_title4);
        TextView ulTitle5 = (TextView) findViewById(R.id.useful_links_title5);
        TextView ulTitle6 = (TextView) findViewById(R.id.useful_links_title6);

        Typeface tf_IranSans = Typeface.createFromAsset(getAssets(), "IRANSansMobile.ttf");
        Typeface tf_IranSans_Bold = Typeface.createFromAsset(getAssets(), "IRANSansMobile_Bold.ttf");
        Typeface tf_IranSans_Light = Typeface.createFromAsset(getAssets(), "IRANSansMobile_Light.ttf");
        Typeface tf_IranSans_Medium = Typeface.createFromAsset(getAssets(), "IRANSansMobile_Medium.ttf");
        Typeface tf_IranSans_UltraLight = Typeface.createFromAsset(getAssets(), "IRANSansMobile_UltraLight.ttf");

        ulTitle.setTypeface(tf_IranSans_Bold);
        ulTitle1.setTypeface(tf_IranSans_Medium);
        ulTitle2.setTypeface(tf_IranSans_Medium);
        ulTitle3.setTypeface(tf_IranSans_Medium);
        ulTitle4.setTypeface(tf_IranSans_Medium);
        ulTitle5.setTypeface(tf_IranSans_Medium);
        ulTitle6.setTypeface(tf_IranSans_Medium);

        LinearLayout icons_layout = (LinearLayout) findViewById(R.id.icons_layout);
        icons_layout.setGravity(Gravity.BOTTOM);
    }

    @Override
    protected void onResume(){
        super.onResume();

        Bundle params = new Bundle();
        params.putString(FirebaseAnalytics.Param.ITEM_ID, "UL_Hit");
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
