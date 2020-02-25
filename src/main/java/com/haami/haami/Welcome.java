package com.haami.haami;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;

public class Welcome extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


        String welcome = "اپلیکیشن حامی با ارائه بخش\u200Cهای متنوع درکنار شماست تا در  روند بهبودی و رهایی از اعتیاد فعال با تکیه بر آن نیروی برتری که مخصوص شماست، حامی همیشگی شما باشد.";

        Typeface tf_IranSans_UltraLight = Typeface.createFromAsset(getAssets(), "IRANSansMobile_UltraLight.ttf");
        Typeface tf_IranSans_Bold = Typeface.createFromAsset(getAssets(), "IRANSansMobile_Bold.ttf");
        TextView welcomeText = (TextView) findViewById(R.id.welcome);
        welcomeText.setText(welcome);
        welcomeText.setTypeface(tf_IranSans_UltraLight);
        TextView welcomeButton = (TextView) findViewById(R.id.start);
        welcomeButton.setText("خوش آمدید");
        welcomeButton.setTypeface(tf_IranSans_Bold);
    }

    @Override
    protected void onResume(){
        super.onResume();

        Bundle params = new Bundle();
        params.putString(FirebaseAnalytics.Param.ITEM_ID, "Welcome_Hit");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM, params);
    }

    public void start(View view){
        Intent intentWelcometoMain = new Intent(this, MainPage.class);
        startActivity(intentWelcometoMain);
    }
}
