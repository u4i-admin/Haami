package com.haami.haami;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;

public class Introduction extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        Typeface tf_IranSans = Typeface.createFromAsset(getAssets(), "IRANSansMobile.ttf");
        Typeface tf_IranSans_Bold = Typeface.createFromAsset(getAssets(), "IRANSansMobile_Bold.ttf");
        Typeface tf_IranSans_Light = Typeface.createFromAsset(getAssets(), "IRANSansMobile_Light.ttf");
        Typeface tf_IranSans_Medium = Typeface.createFromAsset(getAssets(), "IRANSansMobile_Medium.ttf");
        Typeface tf_IranSans_UltraLight = Typeface.createFromAsset(getAssets(), "IRANSansMobile_UltraLight.ttf");

        TextView welcomeText = (TextView) findViewById(R.id.welcomeText);
        TextView mainText = (TextView) findViewById(R.id.mainText);
        TextView bbText = (TextView) findViewById(R.id.bbText);
        TextView ucText = (TextView) findViewById(R.id.ucText);
        TextView uplText = (TextView) findViewById(R.id.uplText);
        TextView upText = (TextView) findViewById(R.id.upText);
        TextView ulText = (TextView) findViewById(R.id.ulText);
        TextView useText = (TextView) findViewById(R.id.useText);
        TextView jftText = (TextView) findViewById(R.id.jftText);
        TextView contents_top = (TextView) findViewById(R.id.contents_top);

        welcomeText.setTypeface(tf_IranSans_Light);
        mainText.setTypeface(tf_IranSans_Light);
        bbText.setTypeface(tf_IranSans_Light);
        ucText.setTypeface(tf_IranSans_Light);
        uplText.setTypeface(tf_IranSans_Light);
        upText.setTypeface(tf_IranSans_Light);
        ulText.setTypeface(tf_IranSans_Light);
        useText.setTypeface(tf_IranSans_Light);
        jftText.setTypeface(tf_IranSans_Light);
        contents_top.setTypeface(tf_IranSans_Bold);
        contents_top.setBackgroundResource(R.drawable.bottom_tab);

        welcomeText.setText("وقتی برای اولین بار اپلیکیشن حامی را اجرا کنید، با صفحه خوش\u200Cآمدگویی مواجه می\u200Cشوید. روی هر بخشی از این صفحه که بزنید، به صفحه اصلی اپلیکیشن منتقل خواهید شد.");
        mainText.setText("در صفحه اصلی اگر روی لوگو بزنید، به صفحه خوش\u200Cآمدگویی منتقل می\u200Cشوید. در میانه صفحه اصلی، پنج بخش اصلی اپلیکیشن حامی به نام\u200Cهای فقط برای امروز، کتاب پایه، مطالبی برای شما، جایی برای رفتن و حالم خوب نیست را می\u200Cبینید. روی هر بخش که بزنید به صفحه مربوط به آن خواهید رفت.");
        bbText.setText("کتاب پایه یکی از مهم\u200Cترین متون منبع برای معتادانی است که می\u200Cخواهند پاک باشند و قدم در راه بهبودی بگذارند. لطفا این کتاب را با عشق و علاقه بخوانید و بخش بخش آن را با تمام وجود درک کنید. این کتاب چراغ راه بهبودی است.");
        jftText.setText("در تمام صفحات دیگر اپلیکیشن حامی، منوی اصلی که شامل پنج بخش اصلی اپلیکیشن است در نوار پایین صفحه در اختیار شماست. هم\u200Cچنین دکمه خانه نیز در کنار پنج بخش اصلی در نوار پایین سمت چپ قرار دارد. در بخش فقط برای امروز، متن مربوط به هر روز سال برای شما نمایش داده خواهد شد. این متن بر اساس استاندارد جهانی معتادان گمنام است که با تقویم ایرانی هماهنگ شده است.");
        ucText.setText("در بخش \"مطالبی برای شما\" مطالبی را برای شما در چهار بخش اصلی به نام\u200Cهای \"ما و بهبودی\"، \"خانواده\u200Cها هم بخوانند\"، \"یک نفر مثل من\" و \"از مخدر چه می\u200Cدانی\" قرار داده\u200Cایم. در بخش \"ما و بهبودی\" مطالبی درباره شیوه\u200Cهای بهبودی به طور کل و نکات و مواردی که درباره آن باید بدانید گذاشته شده است. بخش \"خانواده\u200Cها هم بخوانند\" برای خانواده\u200Cهایی است که زندگی با یک معتاد را تجربه می\u200Cکنند. درک خانواده\u200Cها از اعتیاد و نحوه مواجهه با این بیماری پیچیده و دشوار، در بهبودی معتاد تاثیر بسیار بالایی دارد. بخش \"یک نفر مثل من\" روایت معتادانی است که از زندگی خود و اتفاقات آن می\u200Cگویند. خواندن حرف دل معتادان دیگری که در حال بهبودی هستند، از تحمل رنج بهبودی کاسته و به معتاد امید دوچندان می\u200Cبخشد. قسمت \"از مخدر چه می\u200Cدانی\" شما را با انواع مواد مخدر و تاثیرات مخرب هر یک از آنها آشنا می\u200Cکند. چه بسیار افرادی که ابتدا نادانسته و برای تفریح فریب استفاده از یک مواد مخدر را خورده\u200Cاند بی آنکه بدانند آن ماده چه تاثیراتی دارد و در خانواده مخدرها قرار می\u200Cگیرد.");
        uplText.setText("\"جایی برای رفتن\" نام بخشی است که مکان\u200Cهای مفید را به شما معرفی می\u200Cکند. این بخش دائما در حال به\u200Cروز شدن خواهد بود.");
        upText.setText("در بخش اول \"مکان\u200Cهای مفید\" را خواهید یافت. مراکز مفیدی در سراسر کشور که برای بهبودی و مشاوره می\u200Cتوانید به آنجا مراجعه کنید تا مورد حمایت و پشتیبانی قرار بگیرید. در توضیحات هر مرکز حتی\u200Cالامکان شماره تلفن و آدرس آن مرکز قرار داده شده است. اگر مکان مفیدی را می\u200Cشناسید که در این لیست قرار ندارد، حتما آن را با ما در میان بگذارید.");
        ulText.setText("در قسمت \"لینک\u200Cهای مفید\" نیز آدرس اینترنتی وب\u200Cسایت\u200Cها و صفحات مفید آنلاین را در اختیارتان گذاشته\u200Cایم تا با مراجعه به آنها از مطالب و خدمات آنها بهره\u200Cمند شوید. اگر وبسایت، صفحه، کانال یا هر مکان آنلاین دیگری را می\u200Cشناسید که مفید و موثر است اما در لیست حامی قرار ندارد، لطفا آن را با ما در میان بگذارید.");
        useText.setText("آخرین قسمت اپلیکیشن حامی یکی از مهم\u200Cترین بخش\u200Cهای آن است. در این قسمت که از نامش هم پیداست، برای مواقعی است که با خودتان می\u200Cگویید \"حالم خوب نیست\".  اگر احساس کردید عادت\u200Cهای بدترکیب اعتیاد به سراعتان آمده و ممکن است رفتاری خلاف مسیر بهبودی از شما سر بزند، روی بخش \"حالم خوب نیست\" بزنید. در این قسمت، حرف دل بسیاری از معتادانی چون شما را گذاشته\u200Cایم تا بخوانید و بدانید که شما تنها نیستید. در کنار آن نیروی برتری که مخصوص شماست و پشتیبان شما که صادقانه در کنار شماست، هیچ\u200Cکس به اندازه یک معتاد دیگر نمی\u200Cتواند حال شما را درک کند. اگر حال خوبی ندارید، حرف دل افرادی مانند خودتان را بخوانید و به توصیه\u200Cهایشان گوش دهید. اگر بعد از خواند متن اول هم\u200Cچنان حال خوبی ندارید، دوباره روی دکمه \"نه، هنوز حالم خوب نیست\" بالای صفحه بزنید تا حرف دل دیگری را بخوانید. این کار را آنقدر تکرار کنید تا حالتان بهتر شود. هر زمانی که حس کردید فشار از روی شما برداشته شده و احساس سبکی می\u200Cکنید، روی دکمه \"حالم خوب شد\" در پایین صفحه بزنید تا به صفحه اصلی منتقل شوید.");

    }

    @Override
    protected void onResume(){
        super.onResume();

        Bundle params = new Bundle();
        params.putString(FirebaseAnalytics.Param.ITEM_ID, "Intro_Hit");
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
