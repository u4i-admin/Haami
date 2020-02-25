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

public class UsefulContents extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_useful_contents);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        TextView useful_contents_top = (TextView) findViewById(R.id.useful_contents_top);
        useful_contents_top.setBackgroundResource(R.drawable.useful_contents_top);

        LinearLayout iconsLayout = (LinearLayout) findViewById(R.id.icons_layout);
        iconsLayout.setGravity(Gravity.CENTER | Gravity.BOTTOM);

        Typeface tf_IranSans = Typeface.createFromAsset(getAssets(), "IRANSansMobile.ttf");
        Typeface tf_IranSans_Bold = Typeface.createFromAsset(getAssets(), "IRANSansMobile_Bold.ttf");
        Typeface tf_IranSans_Light = Typeface.createFromAsset(getAssets(), "IRANSansMobile_Light.ttf");
        Typeface tf_IranSans_Medium = Typeface.createFromAsset(getAssets(), "IRANSansMobile_Medium.ttf");
        Typeface tf_IranSans_UltraLight = Typeface.createFromAsset(getAssets(), "IRANSansMobile_UltraLight.ttf");

        TextView ucTitle = (TextView) findViewById(R.id.useful_content_title);
        ucTitle.setTypeface(tf_IranSans_Bold);

        TextView ucTitle1 = (TextView) findViewById(R.id.useful_content_title1);
        ucTitle1.setTypeface(tf_IranSans_Medium);

        TextView ucTitle2 = (TextView) findViewById(R.id.useful_content_title2);
        ucTitle2.setTypeface(tf_IranSans_Medium);

        TextView ucTitle3 = (TextView) findViewById(R.id.useful_content_title3);
        ucTitle3.setTypeface(tf_IranSans_Medium);

        TextView ucTitle4 = (TextView) findViewById(R.id.useful_content_title4);
        ucTitle4.setTypeface(tf_IranSans_Medium);
    }

    @Override
    protected void onResume(){
        super.onResume();

        Bundle params = new Bundle();
        params.putString(FirebaseAnalytics.Param.ITEM_ID, "UsefulContents_Hit");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM, params);
    }

    public void toRecoveryAndUs(View view) {
        Intent intentToRecoveryAndUs = new Intent(this, RecoveryAndUs.class);
        startActivity(intentToRecoveryAndUs);
    }

    public void toFamilies(View view) {
        Intent intentToFamilies = new Intent(this, Families.class);
        startActivity(intentToFamilies);
    }

    public void toLikeMe(View view) {
        Intent intentToLikeMe = new Intent(this, LikeMe.class);
        startActivity(intentToLikeMe);
    }

    public void toNarcotics(View view) {
        Intent intentToNarcotics = new Intent(this, Narcos.class);
        startActivity(intentToNarcotics);
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

    public void toUsefulPlacesAndLinks(View view) {
        Intent intentToUsefulPlacesAndLinks = new Intent(this, UsefulPlacesAndLinks.class);
        startActivity(intentToUsefulPlacesAndLinks);
    }
    public void start(View view){
        Intent intentWelcometoMain = new Intent(this, MainPage.class);
        startActivity(intentWelcometoMain);
    }
}
