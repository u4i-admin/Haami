package com.haami.haami;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;

import static com.haami.haami.data.Text.randomBody;
import static com.haami.haami.data.Text.randomTitle;


public class Use extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        Typeface tf_IranSans = Typeface.createFromAsset(getAssets(), "IRANSansMobile.ttf");
        Typeface tf_IranSans_Bold = Typeface.createFromAsset(getAssets(), "IRANSansMobile_Bold.ttf");
        Typeface tf_IranSans_Light = Typeface.createFromAsset(getAssets(), "IRANSansMobile_Light.ttf");
        Typeface tf_IranSans_Medium = Typeface.createFromAsset(getAssets(), "IRANSansMobile_Medium.ttf");
        Typeface tf_IranSans_UltraLight = Typeface.createFromAsset(getAssets(), "IRANSansMobile_UltraLight.ttf");

        int random1 = (int) Math.floor(Math.random() * randomBody.length);
        int random2 = (int) Math.floor(Math.random() * randomTitle.length);
        TextView randomBodyText = (TextView) findViewById(R.id.randomBody);
        randomBodyText.setText(randomBody[random1]);
        randomBodyText.setGravity(Gravity.CENTER | Gravity.TOP);
        randomBodyText.setTypeface(tf_IranSans_Medium);

        TextView randomTitleText = (TextView) findViewById(R.id.randomTitle);
        randomTitleText.setText(randomTitle[random2]);
        randomTitleText.setGravity(Gravity.CENTER | Gravity.TOP);
        randomTitleText.setTypeface(tf_IranSans_Bold);

        TextView useText = (TextView) findViewById(R.id.I_want_to_use);
        useText.setGravity(Gravity.CENTER | Gravity.BOTTOM);
        useText.setTypeface(tf_IranSans_Medium);

        TextView notUseText = (TextView) findViewById(R.id.I_dont_want_to_use);
        notUseText.setGravity(Gravity.CENTER);
        notUseText.setTypeface(tf_IranSans_Medium);


        LinearLayout topLayout = (LinearLayout) findViewById(R.id.top_layout);
        topLayout.setBackgroundResource(R.drawable.use_top_back);

        LinearLayout bottomLayout = (LinearLayout) findViewById(R.id.bottom_layout);
        bottomLayout.setBackgroundResource(R.drawable.use_bottom_back);

        ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView);
        scrollView.setBackgroundResource(R.drawable.use_back);
    }

    @Override
    protected void onResume(){
        super.onResume();

        Bundle params = new Bundle();
        params.putString(FirebaseAnalytics.Param.ITEM_ID, "Use_Hit");
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

    public void start(View view){
        Intent intentWelcometoMain = new Intent(this, MainPage.class);
        startActivity(intentWelcometoMain);
    }

    public void toUsefulPlacesAndLinks(View view) {
        Intent intentToUsefulPlacesAndLinks = new Intent(this, UsefulPlacesAndLinks.class);
        startActivity(intentToUsefulPlacesAndLinks);
    }
}
