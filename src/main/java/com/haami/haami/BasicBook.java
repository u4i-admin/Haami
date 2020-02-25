package com.haami.haami;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;

public class BasicBook extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_book);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        //Typeface tf_IranSans = Typeface.createFromAsset(getAssets(), "IRANSansMobile.ttf");
        Typeface tf_IranSans_Bold = Typeface.createFromAsset(getAssets(), "IRANSansMobile_Bold.ttf");
        //Typeface tf_IranSans_Light = Typeface.createFromAsset(getAssets(), "IRANSansMobile_Light.ttf");
        Typeface tf_IranSans_Medium = Typeface.createFromAsset(getAssets(), "IRANSansMobile_Medium.ttf");
        //Typeface tf_IranSans_UltraLight = Typeface.createFromAsset(getAssets(), "IRANSansMobile_UltraLight.ttf");

        TextView basic_book_title = (TextView) findViewById(R.id.basicbookTitle);
        basic_book_title.setTypeface(tf_IranSans_Bold);

        TextView bbPrePre = (TextView) findViewById(R.id.bbPrePre);
        bbPrePre.setTypeface(tf_IranSans_Bold);

        TextView bbPre = (TextView) findViewById(R.id.bbPre);
        bbPre.setTypeface(tf_IranSans_Medium);

        TextView bb1 = (TextView) findViewById(R.id.bb1);
        bb1.setTypeface(tf_IranSans_Medium);

        TextView bb2 = (TextView) findViewById(R.id.bb2);
        bb2.setTypeface(tf_IranSans_Medium);

        TextView bb3 = (TextView) findViewById(R.id.bb3);
        bb3.setTypeface(tf_IranSans_Medium);

        TextView bb41 = (TextView) findViewById(R.id.bb41);
        bb41.setTypeface(tf_IranSans_Medium);

        TextView bb42 = (TextView) findViewById(R.id.bb42);
        bb42.setTypeface(tf_IranSans_Medium);

        TextView bb5 = (TextView) findViewById(R.id.bb5);
        bb5.setTypeface(tf_IranSans_Medium);

        TextView bb6 = (TextView) findViewById(R.id.bb6);
        bb6.setTypeface(tf_IranSans_Medium);

        TextView bb7 = (TextView) findViewById(R.id.bb7);
        bb7.setTypeface(tf_IranSans_Medium);

        TextView bb8 = (TextView) findViewById(R.id.bb8);
        bb8.setTypeface(tf_IranSans_Medium);

        TextView bb9 = (TextView) findViewById(R.id.bb9);
        bb9.setTypeface(tf_IranSans_Medium);

        TextView bb10 = (TextView) findViewById(R.id.bb10);
        bb10.setTypeface(tf_IranSans_Medium);
    }

    @Override
    protected void onResume(){
        super.onResume();

        Bundle params = new Bundle();
        params.putString(FirebaseAnalytics.Param.ITEM_ID, "BB_Hit");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM, params);
    }

    public void toBasicBookChapter(View view) {
        String stringId = view.getResources().getResourceName(view.getId());
        Intent intentToBasicBookChapter = new Intent(this, BasicBookChapter.class);
        intentToBasicBookChapter.putExtra("text_id", stringId);
        startActivity(intentToBasicBookChapter);
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

    public void toUsefulPlacesAndLinks(View view) {
        Intent intentToUsefulPlacesAndLinks = new Intent(this, UsefulPlacesAndLinks.class);
        startActivity(intentToUsefulPlacesAndLinks);
    }
    public void start(View view){
        Intent intentWelcometoMain = new Intent(this, MainPage.class);
        startActivity(intentWelcometoMain);
    }
}
