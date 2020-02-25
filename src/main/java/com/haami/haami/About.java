package com.haami.haami;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;

public class About extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        Typeface tf_IranSans = Typeface.createFromAsset(getAssets(), "IRANSansMobile.ttf");
        Typeface tf_IranSans_Bold = Typeface.createFromAsset(getAssets(), "IRANSansMobile_Bold.ttf");
        Typeface tf_IranSans_Light = Typeface.createFromAsset(getAssets(), "IRANSansMobile_Light.ttf");
        Typeface tf_IranSans_Medium = Typeface.createFromAsset(getAssets(), "IRANSansMobile_Medium.ttf");
        Typeface tf_IranSans_UltraLight = Typeface.createFromAsset(getAssets(), "IRANSansMobile_UltraLight.ttf");

        TextView theText = (TextView) findViewById(R.id.aboutText);
        theText.setTypeface(tf_IranSans_Light);

        TextView contents_top = (TextView) findViewById(R.id.contents_top);
        contents_top.setBackgroundResource(R.drawable.bottom_tab);
        contents_top.setTypeface(tf_IranSans_Bold);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            theText.setText(Html.fromHtml("<p style=\"direction: rtl;\">حامی، حامی شماست</p>\n" +
                    "<p style=\"direction: rtl;\">حامی از نخستین ساخته\u200Cهای پروژه \"ایران\u200Cکوباتور\" است که تلاش می\u200Cکند حامی معتادانی باشد که تصمیم گرفته\u200Cاند با تکیه بر آن نیروی برتری که مخصوص آنهاست، پاک باشند. حامی اعتیاد را همانند بسیاری از کارشناسان جهانی یک بیماری می\u200Cداند که با مراقبتی هر روزه باید با آن مقابله کرد و معتاد در مسیر بهبودی نیاز به حامی و پشتیبان دارد. حامی نخستین اپلیکیشن فارسی است که با ارائه بخش\u200Cهای مختلف و متنوع به معتادان پاک کمک می\u200Cکند از نوشته\u200Cهای هر روزه \"فقط برای امروز\" برای یک روز بیش\u200Cتر پاک ماندن استفاده کنند، کتاب \"متن پایه\" را مطالعه کنند که یکی از مهم\u200Cترین منابع مفید و مؤثر در زمینه بهبودی از اعتیاد فعال است، نوشته\u200Cهای مفیدی را بخوانند که به شناخت بهتر و کامل\u200Cتر از اعتیاد و نحوه مواجهه با آنچه برای خود معتاد و چه برای خانواده\u200Cهایی که فردی معتاد در خانه دارند کمک می\u200Cکنند، از آدرس مراکزی در کل کشور در زمینه حمایت از معتادان اطلاع پیدا کنند که بهترین و سالم\u200Cترین پشتیبانی\u200Cها و خدمات را به معتادان ارائه می\u200Cدهند، با وب\u200Cسایت\u200Cها و صفحات آنلاین مفید و معتبری آشنا شوند که به\u200Cطور مرتب اطلاعات مفیدی را به معتادان و خانواده\u200Cهای آنان ارائه می\u200Cدهند و در نهایت زمانی که عادت\u200Cهای نادرست اعتیاد به آنها فشار می\u200Cآورند و ممکن است رفتار نادرستی برخلاف میثاق پاک بودن از آنها سر بزند، می\u200Cتوانند با خواندن حرف\u200Cهای صادقانه معتادان دیگر که این حالت\u200Cها را تجربه کرده\u200Cاند، به تعهد خود برای پاک بودن وفادار بمانند. حامی نخستین اپلیکیشن فارسی برای معتادان است که تمام این خدمات را یکجا گردآوری کرده است.</p>\n" +
                    "<p style=\"direction: rtl;\">بدون شک، حامی با کمک\u200Cهای تمام کسانی که قلبشان برای بهبودی معتادان این مرز و بوم می\u200Cتپد کامل و کامل\u200Cتر خواهد شد تا روزی که تمام معتادانی که اراده کرده\u200Cاند پاک باشند در سراسر کشور، در کنار اتکا به نیروی برتری که مخصوص آنهاست و کمک صادقانه پشتیبان، حامی را در کنار خود داشته باشند.</p>\n" +
                    "<p style=\"direction: rtl;\">اپلیکیشن حامی توسط تیم طراحی حامی با سرپرستی حسین ترکاشوند طراحی و ساخته شده است. آقای ترکاشوند دارای مدرک کارشناسی ارشد مهندسی کامپیوتر از دانشگاه ایالتی نیویورک است که در ایران  نیز در رشته جامعه\u200Cشناسی دانشگاه تهران در مقطع کارشناسی ارشد مشغول به تحصیل بوده است.\n" +
                    "برای ارتباط با حامی، می\u200Cتوانید آن را  اینجا پیدا کنید:</p>\n" +
                    "<p style=\"direction: rtl;\">ایمیل: <a href=\"mailto:haamiApp@gmail.com\">haamiApp@gmail.com</a></p>\n" +
                    "<p style=\"direction: rtl;\">تلگرام: <a href=\"http://t.me/haamiApp\">t.me/haamiApp</a></p>\n" +
                    "<p style=\"direction: rtl;\">اینستاگرام: <a href=\"http://www.instagram.com/haamiApp\">www.instagram.com/haamiApp</a></p>\n" +
                    "<p style=\"direction: rtl;\">فیسبوک: <a href=\"http://www.facebook.com/haamiApp\">www.facebook.com/haamiApp</a></p>\n" +
                    "<p style=\"direction: rtl;\">توییتر: <a href=\"http://www.twitter.com/haamiApp\">www.twitter.com/haamiApp</a></p>\n" +
                    "<p style=\"direction: rtl;\">با مهر</p>", Html.FROM_HTML_MODE_LEGACY));
            theText.setMovementMethod(LinkMovementMethod.getInstance());
        } else {
            theText.setText(Html.fromHtml("<p style=\"direction: rtl;\">حامی، حامی شماست</p>\n" +
                    "<p style=\"direction: rtl;\">حامی از نخستین ساخته\u200Cهای پروژه \"ایران\u200Cکوباتور\" است که تلاش می\u200Cکند حامی معتادانی باشد که تصمیم گرفته\u200Cاند با تکیه بر آن نیروی برتری که مخصوص آنهاست، پاک باشند. حامی اعتیاد را همانند بسیاری از کارشناسان جهانی یک بیماری می\u200Cداند که با مراقبتی هر روزه باید با آن مقابله کرد و معتاد در مسیر بهبودی نیاز به حامی و پشتیبان دارد. حامی نخستین اپلیکیشن فارسی است که با ارائه بخش\u200Cهای مختلف و متنوع به معتادان پاک کمک می\u200Cکند از نوشته\u200Cهای هر روزه \"فقط برای امروز\" برای یک روز بیش\u200Cتر پاک ماندن استفاده کنند، کتاب \"متن پایه\" را مطالعه کنند که یکی از مهم\u200Cترین منابع مفید و مؤثر در زمینه بهبودی از اعتیاد فعال است، نوشته\u200Cهای مفیدی را بخوانند که به شناخت بهتر و کامل\u200Cتر از اعتیاد و نحوه مواجهه با آنچه برای خود معتاد و چه برای خانواده\u200Cهایی که فردی معتاد در خانه دارند کمک می\u200Cکنند، از آدرس مراکزی در کل کشور در زمینه حمایت از معتادان اطلاع پیدا کنند که بهترین و سالم\u200Cترین پشتیبانی\u200Cها و خدمات را به معتادان ارائه می\u200Cدهند، با وب\u200Cسایت\u200Cها و صفحات آنلاین مفید و معتبری آشنا شوند که به\u200Cطور مرتب اطلاعات مفیدی را به معتادان و خانواده\u200Cهای آنان ارائه می\u200Cدهند و در نهایت زمانی که عادت\u200Cهای نادرست اعتیاد به آنها فشار می\u200Cآورند و ممکن است رفتار نادرستی برخلاف میثاق پاک بودن از آنها سر بزند، می\u200Cتوانند با خواندن حرف\u200Cهای صادقانه معتادان دیگر که این حالت\u200Cها را تجربه کرده\u200Cاند، به تعهد خود برای پاک بودن وفادار بمانند. حامی نخستین اپلیکیشن فارسی برای معتادان است که تمام این خدمات را یکجا گردآوری کرده است.</p>\n" +
                    "<p style=\"direction: rtl;\">بدون شک، حامی با کمک\u200Cهای تمام کسانی که قلبشان برای بهبودی معتادان این مرز و بوم می\u200Cتپد کامل و کامل\u200Cتر خواهد شد تا روزی که تمام معتادانی که اراده کرده\u200Cاند پاک باشند در سراسر کشور، در کنار اتکا به نیروی برتری که مخصوص آنهاست و کمک صادقانه پشتیبان، حامی را در کنار خود داشته باشند.</p>\n" +
                    "<p style=\"direction: rtl;\">اپلیکیشن حامی توسط تیم طراحی حامی با سرپرستی حسین ترکاشوند طراحی و ساخته شده است. آقای ترکاشوند دارای مدرک کارشناسی ارشد مهندسی کامپیوتر از دانشگاه ایالتی نیویورک است که در ایران  نیز در رشته جامعه\u200Cشناسی دانشگاه تهران در مقطع کارشناسی ارشد مشغول به تحصیل بوده است.\n" +
                    "برای ارتباط با حامی، می\u200Cتوانید آن را  اینجا پیدا کنید:</p>\n" +
                    "<p style=\"direction: rtl;\">ایمیل: <a href=\"mailto:haamiApp@gmail.com\">haamiApp@gmail.com</a></p>\n" +
                    "<p style=\"direction: rtl;\">تلگرام: <a href=\"http://t.me/haamiApp\">t.me/haamiApp</a></p>\n" +
                    "<p style=\"direction: rtl;\">اینستاگرام: <a href=\"http://www.instagram.com/haamiApp\">www.instagram.com/haamiApp</a></p>\n" +
                    "<p style=\"direction: rtl;\">فیسبوک: <a href=\"http://www.facebook.com/haamiApp\">www.facebook.com/haamiApp</a></p>\n" +
                    "<p style=\"direction: rtl;\">توییتر: <a href=\"http://www.twitter.com/haamiApp\">www.twitter.com/haamiApp</a></p>\n" +
                    "<p style=\"direction: rtl;\">با مهر</p>"));
            theText.setMovementMethod(LinkMovementMethod.getInstance());
        }}

    @Override
    protected void onResume(){
        super.onResume();

        Bundle params = new Bundle();
        params.putString(FirebaseAnalytics.Param.ITEM_ID, "About_Hit");
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

    public void justForToday(View view) {
        Intent intentMainToJFT = new Intent(this, JustForToday.class);
        startActivity(intentMainToJFT);
    }

    public void toUsefulPlacesAndLinks(View view) {
        Intent intentToUsefulPlacesAndLinks = new Intent(this, UsefulPlacesAndLinks.class);
        startActivity(intentToUsefulPlacesAndLinks);
    }

    public void start(View view) {
        Intent intentWelcometoMain = new Intent(this, MainPage.class);
        startActivity(intentWelcometoMain);
    }
}
