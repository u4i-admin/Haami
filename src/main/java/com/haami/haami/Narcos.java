package com.haami.haami;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;

public class Narcos extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_narcos);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        Typeface tf_IranSans = Typeface.createFromAsset(getAssets(), "IRANSansMobile.ttf");
        Typeface tf_IranSans_Bold = Typeface.createFromAsset(getAssets(), "IRANSansMobile_Bold.ttf");
        Typeface tf_IranSans_Light = Typeface.createFromAsset(getAssets(), "IRANSansMobile_Light.ttf");
        Typeface tf_IranSans_Medium = Typeface.createFromAsset(getAssets(), "IRANSansMobile_Medium.ttf");
        Typeface tf_IranSans_UltraLight = Typeface.createFromAsset(getAssets(), "IRANSansMobile_UltraLight.ttf");

        TextView contents_top = (TextView) findViewById(R.id.useful_contents_top);
        contents_top.setBackgroundResource(R.drawable.bottom_tab);
        contents_top.setGravity(Gravity.CENTER | Gravity.BOTTOM);
        contents_top.setTypeface(tf_IranSans_Bold);

        TextView ucTitle41 = (TextView) findViewById(R.id.useful_content_title41);
        TextView ucTitle42 = (TextView) findViewById(R.id.useful_content_title42);
        TextView ucTitle43 = (TextView) findViewById(R.id.useful_content_title43);
        TextView ucTitle44 = (TextView) findViewById(R.id.useful_content_title44);
        TextView ucTitle45 = (TextView) findViewById(R.id.useful_content_title45);
        TextView ucTitle46 = (TextView) findViewById(R.id.useful_content_title46);
        TextView ucTitle47 = (TextView) findViewById(R.id.useful_content_title47);
        TextView ucTitle48 = (TextView) findViewById(R.id.useful_content_title48);
        TextView ucTitle49 = (TextView) findViewById(R.id.useful_content_title49);
        TextView ucTitle410 = (TextView) findViewById(R.id.useful_content_title410);
        TextView ucTitle411 = (TextView) findViewById(R.id.useful_content_title411);
        TextView ucTitle412 = (TextView) findViewById(R.id.useful_content_title412);

        ucTitle41.setTypeface(tf_IranSans_Medium);
        ucTitle42.setTypeface(tf_IranSans_Medium);
        ucTitle43.setTypeface(tf_IranSans_Medium);
        ucTitle44.setTypeface(tf_IranSans_Medium);
        ucTitle45.setTypeface(tf_IranSans_Medium);
        ucTitle46.setTypeface(tf_IranSans_Medium);
        ucTitle47.setTypeface(tf_IranSans_Medium);
        ucTitle48.setTypeface(tf_IranSans_Medium);
        ucTitle49.setTypeface(tf_IranSans_Medium);
        ucTitle410.setTypeface(tf_IranSans_Medium);
        ucTitle411.setTypeface(tf_IranSans_Medium);
        ucTitle412.setTypeface(tf_IranSans_Medium);
    }

    @Override
    protected void onResume(){
        super.onResume();

        Bundle params = new Bundle();
        params.putString(FirebaseAnalytics.Param.ITEM_ID, "Narcos_Hit");
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

    public void toUse(View view) {
        Intent intentToUse = new Intent(this, Use.class);
        startActivity(intentToUse);
    }

    public void justForToday(View view){
        Intent intentMainToJFT = new Intent(this, JustForToday.class);
        startActivity(intentMainToJFT);
    }

    public void toContents(View view) {
        String stringId = view.getResources().getResourceName(view.getId());
        Intent intentToContents = new Intent(this, Contents.class);
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
