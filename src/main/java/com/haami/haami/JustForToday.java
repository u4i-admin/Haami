package com.haami.haami;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.haami.haami.data.CalendarTool;

import java.util.Calendar;

import com.haami.haami.data.CalendarTool;

import static com.haami.haami.data.Text.body;
import static com.haami.haami.data.Text.end;
import static com.haami.haami.data.Text.subTitle;
import static com.haami.haami.data.Text.title;

public class JustForToday extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;
    private final Calendar c = Calendar.getInstance();
    private final int mYear = c.get(Calendar.YEAR);
    private final int mMonth = c.get(Calendar.MONTH) + 1;
    private int mDay = c.get(Calendar.DAY_OF_MONTH);
    private CalendarTool today = new CalendarTool();
    private int day;
    private int theDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_just_for_today);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        ImageView ra = (ImageView) findViewById(R.id.top_right_arrow);
        ImageView la = (ImageView) findViewById(R.id.top_left_arrow);

        final TextView tvDate = (TextView) findViewById(R.id.todayDate);
        LinearLayout tll = (LinearLayout) findViewById(R.id.top_linear_layout);

        Typeface tf_IranSans = Typeface.createFromAsset(getAssets(), "IRANSansMobile.ttf");
        Typeface tf_IranSans_Bold = Typeface.createFromAsset(getAssets(), "IRANSansMobile_Bold.ttf");
        Typeface tf_IranSans_Light = Typeface.createFromAsset(getAssets(), "IRANSansMobile_Light.ttf");
        Typeface tf_IranSans_Medium = Typeface.createFromAsset(getAssets(), "IRANSansMobile_Medium.ttf");
        Typeface tf_IranSans_UltraLight = Typeface.createFromAsset(getAssets(), "IRANSansMobile_UltraLight.ttf");
        Typeface tf_IranSansNum = Typeface.createFromAsset(getAssets(), "IRANSansMobile(FaNum)_Bold.ttf");

        tvDate.setTypeface(tf_IranSansNum);

        TextView tvTitle = (TextView) findViewById(R.id.title);
        tvTitle.setTypeface(tf_IranSans_Bold);

        TextView tvSubTitle = (TextView) findViewById(R.id.subtitle);
        tvSubTitle.setTypeface(tf_IranSans_UltraLight);


        TextView tvBody = (TextView) findViewById(R.id.body);
        tvBody.setTypeface(tf_IranSans_Light);


        TextView tvEnd = (TextView) findViewById(R.id.end);
        tvEnd.setTypeface(tf_IranSans_Medium);

        LinearLayout todayLayout = (LinearLayout) findViewById(R.id.today_layout);
        LinearLayout iconLayout = (LinearLayout) findViewById(R.id.icons_layout);
        tvDate.setGravity(Gravity.CENTER);
        tvTitle.setGravity(Gravity.CENTER | Gravity.TOP);
        tvSubTitle.setGravity(Gravity.CENTER | Gravity.TOP);
        iconLayout.setGravity(Gravity.CENTER | Gravity.BOTTOM);


        day = today.GDayOfYear();
        theDay = today.DayOfYear();

        today =  new CalendarTool(mYear, mMonth, mDay);

        setAll(day, mDay, tvBody, tvTitle, tvSubTitle, tvEnd, tvDate, todayLayout, tll);

        ra.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tvBody = (TextView) findViewById(R.id.body);
                TextView tvTitle = (TextView) findViewById(R.id.title);
                TextView tvSubTitle = (TextView) findViewById(R.id.subtitle);
                TextView tvEnd = (TextView) findViewById(R.id.end);
                LinearLayout todayLayout = (LinearLayout) findViewById(R.id.today_layout);
                LinearLayout tll = (LinearLayout) findViewById(R.id.top_linear_layout);
                day++;
                mDay++;
                setAll(day, mDay, tvBody, tvTitle, tvSubTitle, tvEnd, tvDate, todayLayout, tll);
            }
        });


        la.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tvBody = (TextView) findViewById(R.id.body);
                TextView tvTitle = (TextView) findViewById(R.id.title);
                TextView tvSubTitle = (TextView) findViewById(R.id.subtitle);
                TextView tvEnd = (TextView) findViewById(R.id.end);
                LinearLayout todayLayout = (LinearLayout) findViewById(R.id.today_layout);
                LinearLayout tll = (LinearLayout) findViewById(R.id.top_linear_layout);
                TextView tvDate = (TextView) findViewById(R.id.todayDate);
                day--;
                mDay--;
                setAll(day, mDay, tvBody, tvTitle, tvSubTitle, tvEnd, tvDate, todayLayout, tll);
            }
        });


    }


    public void setAll(int date1, int date2, TextView tv1, TextView tv2, TextView tv3, TextView tv4, TextView tv5,
                       LinearLayout ll1, LinearLayout ll2) {

        TextView tvBody = tv1;
        TextView tvTitle = tv2;
        TextView tvSubTitle = tv3;
        TextView tvEnd = tv4;
        TextView tvDate = tv5;

        LinearLayout todayLayout = ll1;
        LinearLayout tll = ll2;

        day = date1;

        today =  new CalendarTool(mYear, mMonth, date2);

        tvDate.setText(today.getIranianDate());

        if (today.DayOfYear() < 94) {
            todayLayout.setBackgroundResource(R.drawable.spring_back);
            tll.setBackgroundResource(R.drawable.spring_top_back);
        } else if (today.DayOfYear() > 93 && today.DayOfYear() < 187) {
            todayLayout.setBackgroundResource(R.drawable.summer_back);
            tll.setBackgroundResource(R.drawable.summer_top_back);
        } else if (today.DayOfYear() > 186 && today.DayOfYear() < 277) {
            todayLayout.setBackgroundResource(R.drawable.fall_back);
            tll.setBackgroundResource(R.drawable.fall_top_back);
        } else if (today.DayOfYear() > 276) {
            todayLayout.setBackgroundResource(R.drawable.winter_back);
            tll.setBackgroundResource(R.drawable.winter_top_back);
        }


        switch (day) {
            case 1:
                day = 1;
                tvBody.setText(body[0]);
                tvTitle.setText(title[0]);
                tvSubTitle.setText(subTitle[0]);
                tvEnd.setText(end[0]);
                break;
            case 2:
                day = 2;
                tvBody.setText(body[1]);
                tvTitle.setText(title[1]);
                tvSubTitle.setText(subTitle[1]);
                tvEnd.setText(end[1]);
                break;
            case 3:
                day = 3;
                tvBody.setText(body[2]);
                tvTitle.setText(title[2]);
                tvSubTitle.setText(subTitle[2]);
                tvEnd.setText(end[2]);
                break;
            case 4:
                day = 4;
                tvBody.setText(body[3]);
                tvTitle.setText(title[3]);
                tvSubTitle.setText(subTitle[3]);
                tvEnd.setText(end[3]);
                break;
            case 5:
                day = 5;
                tvBody.setText(body[4]);
                tvTitle.setText(title[4]);
                tvSubTitle.setText(subTitle[4]);
                tvEnd.setText(end[4]);
                break;
            case 6:
                day = 6;
                tvBody.setText(body[5]);
                tvTitle.setText(title[5]);
                tvSubTitle.setText(subTitle[5]);
                tvEnd.setText(end[5]);
                break;
            case 7:
                day = 7;
                tvBody.setText(body[6]);
                tvTitle.setText(title[6]);
                tvSubTitle.setText(subTitle[6]);
                tvEnd.setText(end[6]);
                break;
            case 8:
                day = 8;
                tvBody.setText(body[7]);
                tvTitle.setText(title[7]);
                tvSubTitle.setText(subTitle[7]);
                tvEnd.setText(end[7]);
                break;
            case 9:
                day = 9;
                tvBody.setText(body[8]);
                tvTitle.setText(title[8]);
                tvSubTitle.setText(subTitle[8]);
                tvEnd.setText(end[8]);
                break;
            case 10:
                day = 10;
                tvBody.setText(body[9]);
                tvTitle.setText(title[9]);
                tvSubTitle.setText(subTitle[9]);
                tvEnd.setText(end[9]);
                break;
            case 11:
                day = 11;
                tvBody.setText(body[10]);
                tvTitle.setText(title[10]);
                tvSubTitle.setText(subTitle[10]);
                tvEnd.setText(end[10]);
                break;
            case 12:
                day = 12;
                tvBody.setText(body[11]);
                tvTitle.setText(title[11]);
                tvSubTitle.setText(subTitle[11]);
                tvEnd.setText(end[11]);
                break;
            case 13:
                day = 13;
                tvBody.setText(body[12]);
                tvTitle.setText(title[12]);
                tvSubTitle.setText(subTitle[12]);
                tvEnd.setText(end[12]);
                break;
            case 14:
                day = 14;
                tvBody.setText(body[13]);
                tvTitle.setText(title[13]);
                tvSubTitle.setText(subTitle[13]);
                tvEnd.setText(end[13]);
                break;
            case 15:
                day = 15;
                tvBody.setText(body[14]);
                tvTitle.setText(title[14]);
                tvSubTitle.setText(subTitle[14]);
                tvEnd.setText(end[14]);
                break;
            case 16:
                day = 16;
                tvBody.setText(body[15]);
                tvTitle.setText(title[15]);
                tvSubTitle.setText(subTitle[15]);
                tvEnd.setText(end[15]);
                break;
            case 17:
                day = 17;
                tvBody.setText(body[16]);
                tvTitle.setText(title[16]);
                tvSubTitle.setText(subTitle[16]);
                tvEnd.setText(end[16]);
                break;
            case 18:
                day = 18;
                tvBody.setText(body[17]);
                tvTitle.setText(title[17]);
                tvSubTitle.setText(subTitle[17]);
                tvEnd.setText(end[17]);
                break;
            case 19:
                day = 19;
                tvBody.setText(body[18]);
                tvTitle.setText(title[18]);
                tvSubTitle.setText(subTitle[18]);
                tvEnd.setText(end[18]);
                break;
            case 20:
                day = 20;
                tvBody.setText(body[19]);
                tvTitle.setText(title[19]);
                tvSubTitle.setText(subTitle[19]);
                tvEnd.setText(end[19]);
                break;
            case 21:
                day = 21;
                tvBody.setText(body[20]);
                tvTitle.setText(title[20]);
                tvSubTitle.setText(subTitle[20]);
                tvEnd.setText(end[20]);
                break;
            case 22:
                day = 22;
                tvBody.setText(body[21]);
                tvTitle.setText(title[21]);
                tvSubTitle.setText(subTitle[21]);
                tvEnd.setText(end[21]);
                break;
            case 23:
                day = 23;
                tvBody.setText(body[22]);
                tvTitle.setText(title[22]);
                tvSubTitle.setText(subTitle[22]);
                tvEnd.setText(end[22]);
                break;
            case 24:
                day = 24;
                tvBody.setText(body[23]);
                tvTitle.setText(title[23]);
                tvSubTitle.setText(subTitle[23]);
                tvEnd.setText(end[23]);
                break;
            case 25:
                day = 25;
                tvBody.setText(body[24]);
                tvTitle.setText(title[24]);
                tvSubTitle.setText(subTitle[24]);
                tvEnd.setText(end[24]);
                break;
            case 26:
                day = 26;
                tvBody.setText(body[25]);
                tvTitle.setText(title[25]);
                tvSubTitle.setText(subTitle[25]);
                tvEnd.setText(end[25]);
                break;
            case 27:
                day = 27;
                tvBody.setText(body[26]);
                tvTitle.setText(title[26]);
                tvSubTitle.setText(subTitle[26]);
                tvEnd.setText(end[26]);
                break;
            case 28:
                day = 28;
                tvBody.setText(body[27]);
                tvTitle.setText(title[27]);
                tvSubTitle.setText(subTitle[27]);
                tvEnd.setText(end[27]);
                break;
            case 29:
                day = 29;
                tvBody.setText(body[28]);
                tvTitle.setText(title[28]);
                tvSubTitle.setText(subTitle[28]);
                tvEnd.setText(end[28]);
                break;
            case 30:
                day = 30;
                tvBody.setText(body[29]);
                tvTitle.setText(title[29]);
                tvSubTitle.setText(subTitle[29]);
                tvEnd.setText(end[29]);
                break;
            case 31:
                day = 31;
                tvBody.setText(body[30]);
                tvTitle.setText(title[30]);
                tvSubTitle.setText(subTitle[30]);
                tvEnd.setText(end[30]);
                break;
            case 32:
                day = 32;
                tvBody.setText(body[31]);
                tvTitle.setText(title[31]);
                tvSubTitle.setText(subTitle[31]);
                tvEnd.setText(end[31]);
                break;
            case 33:
                day = 33;
                tvBody.setText(body[32]);
                tvTitle.setText(title[32]);
                tvSubTitle.setText(subTitle[32]);
                tvEnd.setText(end[32]);
                break;
            case 34:
                day = 34;
                tvBody.setText(body[33]);
                tvTitle.setText(title[33]);
                tvSubTitle.setText(subTitle[33]);
                tvEnd.setText(end[33]);
                break;
            case 35:
                day = 35;
                tvBody.setText(body[34]);
                tvTitle.setText(title[34]);
                tvSubTitle.setText(subTitle[34]);
                tvEnd.setText(end[34]);
                break;
            case 36:
                day = 36;
                tvBody.setText(body[35]);
                tvTitle.setText(title[35]);
                tvSubTitle.setText(subTitle[35]);
                tvEnd.setText(end[35]);
                break;
            case 37:
                day = 37;
                tvBody.setText(body[36]);
                tvTitle.setText(title[36]);
                tvSubTitle.setText(subTitle[36]);
                tvEnd.setText(end[36]);
                break;
            case 38:
                day = 38;
                tvBody.setText(body[37]);
                tvTitle.setText(title[37]);
                tvSubTitle.setText(subTitle[37]);
                tvEnd.setText(end[37]);
                break;
            case 39:
                day = 39;
                tvBody.setText(body[38]);
                tvTitle.setText(title[38]);
                tvSubTitle.setText(subTitle[38]);
                tvEnd.setText(end[38]);
                break;
            case 40:
                day = 40;
                tvBody.setText(body[39]);
                tvTitle.setText(title[39]);
                tvSubTitle.setText(subTitle[39]);
                tvEnd.setText(end[39]);
                break;
            case 41:
                day = 41;
                tvBody.setText(body[40]);
                tvTitle.setText(title[40]);
                tvSubTitle.setText(subTitle[40]);
                tvEnd.setText(end[40]);
                break;
            case 42:
                day = 42;
                tvBody.setText(body[41]);
                tvTitle.setText(title[41]);
                tvSubTitle.setText(subTitle[41]);
                tvEnd.setText(end[41]);
                break;
            case 43:
                day = 43;
                tvBody.setText(body[42]);
                tvTitle.setText(title[42]);
                tvSubTitle.setText(subTitle[42]);
                tvEnd.setText(end[42]);
                break;
            case 44:
                day = 44;
                tvBody.setText(body[43]);
                tvTitle.setText(title[43]);
                tvSubTitle.setText(subTitle[43]);
                tvEnd.setText(end[43]);
                break;
            case 45:
                day = 45;
                tvBody.setText(body[44]);
                tvTitle.setText(title[44]);
                tvSubTitle.setText(subTitle[44]);
                tvEnd.setText(end[44]);
                break;
            case 46:
                day = 46;
                tvBody.setText(body[45]);
                tvTitle.setText(title[45]);
                tvSubTitle.setText(subTitle[45]);
                tvEnd.setText(end[45]);
                break;
            case 47:
                day = 47;
                tvBody.setText(body[46]);
                tvTitle.setText(title[46]);
                tvSubTitle.setText(subTitle[46]);
                tvEnd.setText(end[46]);
                break;
            case 48:
                day = 48;
                tvBody.setText(body[47]);
                tvTitle.setText(title[47]);
                tvSubTitle.setText(subTitle[47]);
                tvEnd.setText(end[47]);
                break;
            case 49:
                day = 49;
                tvBody.setText(body[48]);
                tvTitle.setText(title[48]);
                tvSubTitle.setText(subTitle[48]);
                tvEnd.setText(end[48]);
                break;
            case 50:
                day = 50;
                tvBody.setText(body[49]);
                tvTitle.setText(title[49]);
                tvSubTitle.setText(subTitle[49]);
                tvEnd.setText(end[49]);
                break;
            case 51:
                day = 51;
                tvBody.setText(body[50]);
                tvTitle.setText(title[50]);
                tvSubTitle.setText(subTitle[50]);
                tvEnd.setText(end[50]);
                break;
            case 52:
                day = 52;
                tvBody.setText(body[51]);
                tvTitle.setText(title[51]);
                tvSubTitle.setText(subTitle[51]);
                tvEnd.setText(end[51]);
                break;
            case 53:
                day = 53;
                tvBody.setText(body[52]);
                tvTitle.setText(title[52]);
                tvSubTitle.setText(subTitle[52]);
                tvEnd.setText(end[52]);
                break;
            case 54:
                day = 54;
                tvBody.setText(body[53]);
                tvTitle.setText(title[53]);
                tvSubTitle.setText(subTitle[53]);
                tvEnd.setText(end[53]);
                break;
            case 55:
                day = 55;
                tvBody.setText(body[54]);
                tvTitle.setText(title[54]);
                tvSubTitle.setText(subTitle[54]);
                tvEnd.setText(end[54]);
                break;
            case 56:
                day = 56;
                tvBody.setText(body[55]);
                tvTitle.setText(title[55]);
                tvSubTitle.setText(subTitle[55]);
                tvEnd.setText(end[55]);
                break;
            case 57:
                day = 57;
                tvBody.setText(body[56]);
                tvTitle.setText(title[56]);
                tvSubTitle.setText(subTitle[56]);
                tvEnd.setText(end[56]);
                break;
            case 58:
                day = 58;
                tvBody.setText(body[57]);
                tvTitle.setText(title[57]);
                tvSubTitle.setText(subTitle[57]);
                tvEnd.setText(end[57]);
                break;
            case 59:
                day = 59;
                tvBody.setText(body[58]);
                tvTitle.setText(title[58]);
                tvSubTitle.setText(subTitle[58]);
                tvEnd.setText(end[58]);
                break;
            case 60:
                day = 60;
                tvBody.setText(body[59]);
                tvTitle.setText(title[59]);
                tvSubTitle.setText(subTitle[59]);
                tvEnd.setText(end[59]);
                break;
            case 61:
                day = 61;
                tvBody.setText(body[60]);
                tvTitle.setText(title[60]);
                tvSubTitle.setText(subTitle[60]);
                tvEnd.setText(end[60]);
                break;
            case 62:
                day = 62;
                tvBody.setText(body[61]);
                tvTitle.setText(title[61]);
                tvSubTitle.setText(subTitle[61]);
                tvEnd.setText(end[61]);
                break;
            case 63:
                day = 63;
                tvBody.setText(body[62]);
                tvTitle.setText(title[62]);
                tvSubTitle.setText(subTitle[62]);
                tvEnd.setText(end[62]);
                break;
            case 64:
                day = 64;
                tvBody.setText(body[63]);
                tvTitle.setText(title[63]);
                tvSubTitle.setText(subTitle[63]);
                tvEnd.setText(end[63]);
                break;
            case 65:
                day = 65;
                tvBody.setText(body[64]);
                tvTitle.setText(title[64]);
                tvSubTitle.setText(subTitle[64]);
                tvEnd.setText(end[64]);
                break;
            case 66:
                day = 66;
                tvBody.setText(body[65]);
                tvTitle.setText(title[65]);
                tvSubTitle.setText(subTitle[65]);
                tvEnd.setText(end[65]);
                break;
            case 67:
                day = 67;
                tvBody.setText(body[66]);
                tvTitle.setText(title[66]);
                tvSubTitle.setText(subTitle[66]);
                tvEnd.setText(end[66]);
                break;
            case 68:
                day = 68;
                tvBody.setText(body[67]);
                tvTitle.setText(title[67]);
                tvSubTitle.setText(subTitle[67]);
                tvEnd.setText(end[67]);
                break;
            case 69:
                day = 69;
                tvBody.setText(body[68]);
                tvTitle.setText(title[68]);
                tvSubTitle.setText(subTitle[68]);
                tvEnd.setText(end[68]);
                break;
            case 70:
                day = 70;
                tvBody.setText(body[69]);
                tvTitle.setText(title[69]);
                tvSubTitle.setText(subTitle[69]);
                tvEnd.setText(end[69]);
                break;
            case 71:
                day = 71;
                tvBody.setText(body[70]);
                tvTitle.setText(title[70]);
                tvSubTitle.setText(subTitle[70]);
                tvEnd.setText(end[70]);
                break;
            case 72:
                day = 72;
                tvBody.setText(body[71]);
                tvTitle.setText(title[71]);
                tvSubTitle.setText(subTitle[71]);
                tvEnd.setText(end[71]);
                break;
            case 73:
                day = 73;
                tvBody.setText(body[72]);
                tvTitle.setText(title[72]);
                tvSubTitle.setText(subTitle[72]);
                tvEnd.setText(end[72]);
                break;
            case 74:
                day = 74;
                tvBody.setText(body[73]);
                tvTitle.setText(title[73]);
                tvSubTitle.setText(subTitle[73]);
                tvEnd.setText(end[73]);
                break;
            case 75:
                day = 75;
                tvBody.setText(body[74]);
                tvTitle.setText(title[74]);
                tvSubTitle.setText(subTitle[74]);
                tvEnd.setText(end[74]);
                break;
            case 76:
                day = 76;
                tvBody.setText(body[75]);
                tvTitle.setText(title[75]);
                tvSubTitle.setText(subTitle[75]);
                tvEnd.setText(end[75]);
                break;
            case 77:
                day = 77;
                tvBody.setText(body[76]);
                tvTitle.setText(title[76]);
                tvSubTitle.setText(subTitle[76]);
                tvEnd.setText(end[76]);
                break;
            case 78:
                day = 78;
                tvBody.setText(body[77]);
                tvTitle.setText(title[77]);
                tvSubTitle.setText(subTitle[77]);
                tvEnd.setText(end[77]);
                break;
            case 79:
                day = 79;
                tvBody.setText(body[78]);
                tvTitle.setText(title[78]);
                tvSubTitle.setText(subTitle[78]);
                tvEnd.setText(end[78]);
                break;
            case 80:
                day = 80;
                tvBody.setText(body[79]);
                tvTitle.setText(title[79]);
                tvSubTitle.setText(subTitle[79]);
                tvEnd.setText(end[79]);
                break;
            case 81:
                day = 81;
                tvBody.setText(body[80]);
                tvTitle.setText(title[80]);
                tvSubTitle.setText(subTitle[80]);
                tvEnd.setText(end[80]);
                break;
            case 82:
                day = 82;
                tvBody.setText(body[81]);
                tvTitle.setText(title[81]);
                tvSubTitle.setText(subTitle[81]);
                tvEnd.setText(end[81]);
                break;
            case 83:
                day = 83;
                tvBody.setText(body[82]);
                tvTitle.setText(title[82]);
                tvSubTitle.setText(subTitle[82]);
                tvEnd.setText(end[82]);
                break;
            case 84:
                day = 84;
                tvBody.setText(body[83]);
                tvTitle.setText(title[83]);
                tvSubTitle.setText(subTitle[83]);
                tvEnd.setText(end[83]);
                break;
            case 85:
                day = 85;
                tvBody.setText(body[84]);
                tvTitle.setText(title[84]);
                tvSubTitle.setText(subTitle[84]);
                tvEnd.setText(end[84]);
                break;
            case 86:
                day = 86;
                tvBody.setText(body[85]);
                tvTitle.setText(title[85]);
                tvSubTitle.setText(subTitle[85]);
                tvEnd.setText(end[85]);
                break;
            case 87:
                day = 87;
                tvBody.setText(body[86]);
                tvTitle.setText(title[86]);
                tvSubTitle.setText(subTitle[86]);
                tvEnd.setText(end[86]);
                break;
            case 88:
                day = 88;
                tvBody.setText(body[87]);
                tvTitle.setText(title[87]);
                tvSubTitle.setText(subTitle[87]);
                tvEnd.setText(end[87]);
                break;
            case 89:
                day = 89;
                tvBody.setText(body[88]);
                tvTitle.setText(title[88]);
                tvSubTitle.setText(subTitle[88]);
                tvEnd.setText(end[88]);
                break;
            case 90:
                day = 90;
                tvBody.setText(body[89]);
                tvTitle.setText(title[89]);
                tvSubTitle.setText(subTitle[89]);
                tvEnd.setText(end[89]);
                break;
            case 91:
                day = 91;
                tvBody.setText(body[90]);
                tvTitle.setText(title[90]);
                tvSubTitle.setText(subTitle[90]);
                tvEnd.setText(end[90]);
                break;
            case 92:
                day = 92;
                tvBody.setText(body[91]);
                tvTitle.setText(title[91]);
                tvSubTitle.setText(subTitle[9]);
                tvEnd.setText(end[91]);
                break;
            case 93:
                day = 93;
                tvBody.setText(body[92]);
                tvTitle.setText(title[92]);
                tvSubTitle.setText(subTitle[92]);
                tvEnd.setText(end[92]);
                break;
            case 94:
                day = 94;
                tvBody.setText(body[93]);
                tvTitle.setText(title[93]);
                tvSubTitle.setText(subTitle[93]);
                tvEnd.setText(end[93]);
                break;
            case 95:
                day = 95;
                tvBody.setText(body[94]);
                tvTitle.setText(title[94]);
                tvSubTitle.setText(subTitle[94]);
                tvEnd.setText(end[94]);
                break;
            case 96:
                day = 96;
                tvBody.setText(body[95]);
                tvTitle.setText(title[95]);
                tvSubTitle.setText(subTitle[95]);
                tvEnd.setText(end[95]);
                break;
            case 97:
                day = 97;
                tvBody.setText(body[96]);
                tvTitle.setText(title[96]);
                tvSubTitle.setText(subTitle[96]);
                tvEnd.setText(end[96]);
                break;
            case 98:
                day = 98;
                tvBody.setText(body[97]);
                tvTitle.setText(title[97]);
                tvSubTitle.setText(subTitle[97]);
                tvEnd.setText(end[97]);
                break;
            case 99:
                day = 99;
                tvBody.setText(body[98]);
                tvTitle.setText(title[98]);
                tvSubTitle.setText(subTitle[98]);
                tvEnd.setText(end[98]);
                break;
            case 100:
                day = 100;
                tvBody.setText(body[99]);
                tvTitle.setText(title[99]);
                tvSubTitle.setText(subTitle[99]);
                tvEnd.setText(end[99]);
                break;
            case 101:
                day = 101;
                tvBody.setText(body[100]);
                tvTitle.setText(title[100]);
                tvSubTitle.setText(subTitle[100]);
                tvEnd.setText(end[100]);
                break;
            case 102:
                day = 102;
                tvBody.setText(body[101]);
                tvTitle.setText(title[101]);
                tvSubTitle.setText(subTitle[101]);
                tvEnd.setText(end[101]);
                break;
            case 103:
                day = 103;
                tvBody.setText(body[102]);
                tvTitle.setText(title[102]);
                tvSubTitle.setText(subTitle[102]);
                tvEnd.setText(end[102]);
                break;
            case 104:
                day = 104;
                tvBody.setText(body[103]);
                tvTitle.setText(title[103]);
                tvSubTitle.setText(subTitle[103]);
                tvEnd.setText(end[103]);
                break;
            case 105:
                day = 105;
                tvBody.setText(body[104]);
                tvTitle.setText(title[104]);
                tvSubTitle.setText(subTitle[104]);
                tvEnd.setText(end[104]);
                break;
            case 106:
                day = 106;
                tvBody.setText(body[105]);
                tvTitle.setText(title[105]);
                tvSubTitle.setText(subTitle[105]);
                tvEnd.setText(end[105]);
                break;
            case 107:
                day = 107;
                tvBody.setText(body[106]);
                tvTitle.setText(title[106]);
                tvSubTitle.setText(subTitle[106]);
                tvEnd.setText(end[106]);
                break;
            case 108:
                day = 108;
                tvBody.setText(body[107]);
                tvTitle.setText(title[107]);
                tvSubTitle.setText(subTitle[107]);
                tvEnd.setText(end[107]);
                break;
            case 109:
                day = 109;
                tvBody.setText(body[108]);
                tvTitle.setText(title[108]);
                tvSubTitle.setText(subTitle[108]);
                tvEnd.setText(end[108]);
                break;
            case 110:
                day = 110;
                tvBody.setText(body[109]);
                tvTitle.setText(title[109]);
                tvSubTitle.setText(subTitle[109]);
                tvEnd.setText(end[109]);
                break;
            case 111:
                day = 111;
                tvBody.setText(body[110]);
                tvTitle.setText(title[110]);
                tvSubTitle.setText(subTitle[110]);
                tvEnd.setText(end[110]);
                break;
            case 112:
                day = 112;
                tvBody.setText(body[111]);
                tvTitle.setText(title[111]);
                tvSubTitle.setText(subTitle[111]);
                tvEnd.setText(end[111]);
                break;
            case 113:
                day = 113;
                tvBody.setText(body[112]);
                tvTitle.setText(title[112]);
                tvSubTitle.setText(subTitle[112]);
                tvEnd.setText(end[112]);
                break;
            case 114:
                day = 114;
                tvBody.setText(body[113]);
                tvTitle.setText(title[113]);
                tvSubTitle.setText(subTitle[113]);
                tvEnd.setText(end[113]);
                break;
            case 115:
                day = 115;
                tvBody.setText(body[114]);
                tvTitle.setText(title[114]);
                tvSubTitle.setText(subTitle[114]);
                tvEnd.setText(end[114]);
                break;
            case 116:
                day = 116;
                tvBody.setText(body[115]);
                tvTitle.setText(title[115]);
                tvSubTitle.setText(subTitle[115]);
                tvEnd.setText(end[115]);
                break;
            case 117:
                day = 117;
                tvBody.setText(body[116]);
                tvTitle.setText(title[116]);
                tvSubTitle.setText(subTitle[116]);
                tvEnd.setText(end[116]);
                break;
            case 118:
                day = 118;
                tvBody.setText(body[117]);
                tvTitle.setText(title[117]);
                tvSubTitle.setText(subTitle[117]);
                tvEnd.setText(end[117]);
                break;
            case 119:
                day = 119;
                tvBody.setText(body[118]);
                tvTitle.setText(title[118]);
                tvSubTitle.setText(subTitle[118]);
                tvEnd.setText(end[118]);
                break;
            case 120:
                day = 120;
                tvBody.setText(body[119]);
                tvTitle.setText(title[119]);
                tvSubTitle.setText(subTitle[119]);
                tvEnd.setText(end[119]);
                break;
            case 121:
                day = 121;
                tvBody.setText(body[120]);
                tvTitle.setText(title[120]);
                tvSubTitle.setText(subTitle[120]);
                tvEnd.setText(end[120]);
                break;
            case 122:
                day = 122;
                tvBody.setText(body[121]);
                tvTitle.setText(title[121]);
                tvSubTitle.setText(subTitle[121]);
                tvEnd.setText(end[121]);
                break;
            case 123:
                day = 123;
                tvBody.setText(body[122]);
                tvTitle.setText(title[122]);
                tvSubTitle.setText(subTitle[122]);
                tvEnd.setText(end[122]);
                break;
            case 124:
                day = 124;
                tvBody.setText(body[123]);
                tvTitle.setText(title[123]);
                tvSubTitle.setText(subTitle[123]);
                tvEnd.setText(end[123]);
                break;
            case 125:
                day = 125;
                tvBody.setText(body[124]);
                tvTitle.setText(title[124]);
                tvSubTitle.setText(subTitle[124]);
                tvEnd.setText(end[124]);
                break;
            case 126:
                day = 126;
                tvBody.setText(body[125]);
                tvTitle.setText(title[125]);
                tvSubTitle.setText(subTitle[125]);
                tvEnd.setText(end[125]);
                break;
            case 127:
                day = 127;
                tvBody.setText(body[126]);
                tvTitle.setText(title[126]);
                tvSubTitle.setText(subTitle[126]);
                tvEnd.setText(end[126]);
                break;
            case 128:
                day = 128;
                tvBody.setText(body[127]);
                tvTitle.setText(title[127]);
                tvSubTitle.setText(subTitle[127]);
                tvEnd.setText(end[127]);
                break;
            case 129:
                day = 129;
                tvBody.setText(body[128]);
                tvTitle.setText(title[128]);
                tvSubTitle.setText(subTitle[128]);
                tvEnd.setText(end[128]);
                break;
            case 130:
                day = 130;
                tvBody.setText(body[129]);
                tvTitle.setText(title[129]);
                tvSubTitle.setText(subTitle[129]);
                tvEnd.setText(end[129]);
                break;
            case 131:
                day = 131;
                tvBody.setText(body[130]);
                tvTitle.setText(title[130]);
                tvSubTitle.setText(subTitle[130]);
                tvEnd.setText(end[130]);
                break;
            case 132:
                day = 132;
                tvBody.setText(body[131]);
                tvTitle.setText(title[131]);
                tvSubTitle.setText(subTitle[131]);
                tvEnd.setText(end[131]);
                break;
            case 133:
                day = 133;
                tvBody.setText(body[132]);
                tvTitle.setText(title[132]);
                tvSubTitle.setText(subTitle[132]);
                tvEnd.setText(end[132]);
                break;
            case 134:
                day = 134;
                tvBody.setText(body[133]);
                tvTitle.setText(title[133]);
                tvSubTitle.setText(subTitle[133]);
                tvEnd.setText(end[133]);
                break;
            case 135:
                day = 135;
                tvBody.setText(body[134]);
                tvTitle.setText(title[134]);
                tvSubTitle.setText(subTitle[134]);
                tvEnd.setText(end[134]);
                break;
            case 136:
                day = 136;
                tvBody.setText(body[135]);
                tvTitle.setText(title[135]);
                tvSubTitle.setText(subTitle[135]);
                tvEnd.setText(end[135]);
                break;
            case 137:
                day = 137;
                tvBody.setText(body[136]);
                tvTitle.setText(title[136]);
                tvSubTitle.setText(subTitle[136]);
                tvEnd.setText(end[136]);
                break;
            case 138:
                day = 138;
                tvBody.setText(body[137]);
                tvTitle.setText(title[137]);
                tvSubTitle.setText(subTitle[137]);
                tvEnd.setText(end[137]);
                break;
            case 139:
                day = 139;
                tvBody.setText(body[138]);
                tvTitle.setText(title[138]);
                tvSubTitle.setText(subTitle[138]);
                tvEnd.setText(end[138]);
                break;
            case 140:
                day = 140;
                tvBody.setText(body[139]);
                tvTitle.setText(title[139]);
                tvSubTitle.setText(subTitle[139]);
                tvEnd.setText(end[139]);
                break;
            case 141:
                day = 141;
                tvBody.setText(body[140]);
                tvTitle.setText(title[140]);
                tvSubTitle.setText(subTitle[140]);
                tvEnd.setText(end[140]);
                break;
            case 142:
                day = 142;
                tvBody.setText(body[141]);
                tvTitle.setText(title[141]);
                tvSubTitle.setText(subTitle[141]);
                tvEnd.setText(end[141]);
                break;
            case 143:
                day = 143;
                tvBody.setText(body[142]);
                tvTitle.setText(title[142]);
                tvSubTitle.setText(subTitle[142]);
                tvEnd.setText(end[142]);
                break;
            case 144:
                day = 144;
                tvBody.setText(body[143]);
                tvTitle.setText(title[143]);
                tvSubTitle.setText(subTitle[143]);
                tvEnd.setText(end[143]);
                break;
            case 145:
                day = 145;
                tvBody.setText(body[144]);
                tvTitle.setText(title[144]);
                tvSubTitle.setText(subTitle[144]);
                tvEnd.setText(end[144]);
                break;
            case 146:
                day = 146;
                tvBody.setText(body[145]);
                tvTitle.setText(title[145]);
                tvSubTitle.setText(subTitle[145]);
                tvEnd.setText(end[145]);
                break;
            case 147:
                day = 147;
                tvBody.setText(body[146]);
                tvTitle.setText(title[146]);
                tvSubTitle.setText(subTitle[146]);
                tvEnd.setText(end[146]);
                break;
            case 148:
                day = 148;
                tvBody.setText(body[147]);
                tvTitle.setText(title[147]);
                tvSubTitle.setText(subTitle[147]);
                tvEnd.setText(end[14]);
                break;
            case 149:
                day = 149;
                tvBody.setText(body[148]);
                tvTitle.setText(title[148]);
                tvSubTitle.setText(subTitle[148]);
                tvEnd.setText(end[148]);
                break;
            case 150:
                day = 150;
                tvBody.setText(body[149]);
                tvTitle.setText(title[149]);
                tvSubTitle.setText(subTitle[149]);
                tvEnd.setText(end[149]);
                break;
            case 151:
                day = 151;
                tvBody.setText(body[150]);
                tvTitle.setText(title[150]);
                tvSubTitle.setText(subTitle[150]);
                tvEnd.setText(end[150]);
                break;
            case 152:
                day = 152;
                tvBody.setText(body[151]);
                tvTitle.setText(title[151]);
                tvSubTitle.setText(subTitle[151]);
                tvEnd.setText(end[151]);
                break;
            case 153:
                day = 153;
                tvBody.setText(body[152]);
                tvTitle.setText(title[152]);
                tvSubTitle.setText(subTitle[152]);
                tvEnd.setText(end[152]);
                break;
            case 154:
                day = 154;
                tvBody.setText(body[153]);
                tvTitle.setText(title[153]);
                tvSubTitle.setText(subTitle[153]);
                tvEnd.setText(end[153]);
                break;
            case 155:
                day = 155;
                tvBody.setText(body[154]);
                tvTitle.setText(title[154]);
                tvSubTitle.setText(subTitle[154]);
                tvEnd.setText(end[154]);
                break;
            case 156:
                day = 156;
                tvBody.setText(body[155]);
                tvTitle.setText(title[155]);
                tvSubTitle.setText(subTitle[155]);
                tvEnd.setText(end[155]);
                break;
            case 157:
                day = 157;
                tvBody.setText(body[156]);
                tvTitle.setText(title[156]);
                tvSubTitle.setText(subTitle[156]);
                tvEnd.setText(end[156]);
                break;
            case 158:
                day = 158;
                tvBody.setText(body[157]);
                tvTitle.setText(title[157]);
                tvSubTitle.setText(subTitle[157]);
                tvEnd.setText(end[157]);
                break;
            case 159:
                day = 159;
                tvBody.setText(body[158]);
                tvTitle.setText(title[158]);
                tvSubTitle.setText(subTitle[158]);
                tvEnd.setText(end[158]);
                break;
            case 160:
                day = 160;
                tvBody.setText(body[159]);
                tvTitle.setText(title[159]);
                tvSubTitle.setText(subTitle[159]);
                tvEnd.setText(end[159]);
                break;
            case 161:
                day = 161;
                tvBody.setText(body[160]);
                tvTitle.setText(title[160]);
                tvSubTitle.setText(subTitle[160]);
                tvEnd.setText(end[160]);
                break;
            case 162:
                day = 162;
                tvBody.setText(body[161]);
                tvTitle.setText(title[161]);
                tvSubTitle.setText(subTitle[161]);
                tvEnd.setText(end[161]);
                break;
            case 163:
                day = 163;
                tvBody.setText(body[162]);
                tvTitle.setText(title[162]);
                tvSubTitle.setText(subTitle[162]);
                tvEnd.setText(end[162]);
                break;
            case 164:
                day = 164;
                tvBody.setText(body[163]);
                tvTitle.setText(title[163]);
                tvSubTitle.setText(subTitle[163]);
                tvEnd.setText(end[163]);
                break;
            case 165:
                day = 165;
                tvBody.setText(body[164]);
                tvTitle.setText(title[164]);
                tvSubTitle.setText(subTitle[164]);
                tvEnd.setText(end[164]);
                break;
            case 166:
                day = 166;
                tvBody.setText(body[165]);
                tvTitle.setText(title[165]);
                tvSubTitle.setText(subTitle[165]);
                tvEnd.setText(end[165]);
                break;
            case 167:
                day = 167;
                tvBody.setText(body[166]);
                tvTitle.setText(title[166]);
                tvSubTitle.setText(subTitle[166]);
                tvEnd.setText(end[166]);
                break;
            case 168:
                day = 168;
                tvBody.setText(body[167]);
                tvTitle.setText(title[167]);
                tvSubTitle.setText(subTitle[167]);
                tvEnd.setText(end[167]);
                break;
            case 169:
                day = 169;
                tvBody.setText(body[168]);
                tvTitle.setText(title[168]);
                tvSubTitle.setText(subTitle[168]);
                tvEnd.setText(end[168]);
                break;
            case 170:
                day = 170;
                tvBody.setText(body[169]);
                tvTitle.setText(title[169]);
                tvSubTitle.setText(subTitle[169]);
                tvEnd.setText(end[169]);
                break;
            case 171:
                day = 171;
                tvBody.setText(body[170]);
                tvTitle.setText(title[170]);
                tvSubTitle.setText(subTitle[170]);
                tvEnd.setText(end[170]);
                break;
            case 172:
                day = 172;
                tvBody.setText(body[171]);
                tvTitle.setText(title[171]);
                tvSubTitle.setText(subTitle[171]);
                tvEnd.setText(end[171]);
                break;
            case 173:
                day = 173;
                tvBody.setText(body[172]);
                tvTitle.setText(title[172]);
                tvSubTitle.setText(subTitle[172]);
                tvEnd.setText(end[172]);
                break;
            case 174:
                day = 174;
                tvBody.setText(body[173]);
                tvTitle.setText(title[173]);
                tvSubTitle.setText(subTitle[173]);
                tvEnd.setText(end[173]);
                break;
            case 175:
                day = 175;
                tvBody.setText(body[174]);
                tvTitle.setText(title[174]);
                tvSubTitle.setText(subTitle[174]);
                tvEnd.setText(end[174]);
                break;
            case 176:
                day = 176;
                tvBody.setText(body[175]);
                tvTitle.setText(title[175]);
                tvSubTitle.setText(subTitle[175]);
                tvEnd.setText(end[175]);
                break;
            case 177:
                day = 177;
                tvBody.setText(body[176]);
                tvTitle.setText(title[176]);
                tvSubTitle.setText(subTitle[176]);
                tvEnd.setText(end[176]);
                break;
            case 178:
                day = 178;
                tvBody.setText(body[177]);
                tvTitle.setText(title[177]);
                tvSubTitle.setText(subTitle[177]);
                tvEnd.setText(end[177]);
                break;
            case 179:
                day = 179;
                tvBody.setText(body[178]);
                tvTitle.setText(title[178]);
                tvSubTitle.setText(subTitle[178]);
                tvEnd.setText(end[178]);
                break;
            case 180:
                day = 180;
                tvBody.setText(body[179]);
                tvTitle.setText(title[179]);
                tvSubTitle.setText(subTitle[179]);
                tvEnd.setText(end[179]);
                break;
            case 181:
                day = 181;
                tvBody.setText(body[180]);
                tvTitle.setText(title[180]);
                tvSubTitle.setText(subTitle[180]);
                tvEnd.setText(end[180]);
                break;
            case 182:
                day = 182;
                tvBody.setText(body[181]);
                tvTitle.setText(title[181]);
                tvSubTitle.setText(subTitle[181]);
                tvEnd.setText(end[181]);
                break;
            case 183:
                day = 183;
                tvBody.setText(body[182]);
                tvTitle.setText(title[182]);
                tvSubTitle.setText(subTitle[182]);
                tvEnd.setText(end[182]);
                break;
            case 184:
                day = 184;
                tvBody.setText(body[183]);
                tvTitle.setText(title[183]);
                tvSubTitle.setText(subTitle[183]);
                tvEnd.setText(end[183]);
                break;
            case 185:
                day = 185;
                tvBody.setText(body[184]);
                tvTitle.setText(title[184]);
                tvSubTitle.setText(subTitle[184]);
                tvEnd.setText(end[184]);
                break;
            case 186:
                day = 186;
                tvBody.setText(body[185]);
                tvTitle.setText(title[185]);
                tvSubTitle.setText(subTitle[185]);
                tvEnd.setText(end[185]);
                break;
            case 187:
                day = 187;
                tvBody.setText(body[186]);
                tvTitle.setText(title[186]);
                tvSubTitle.setText(subTitle[186]);
                tvEnd.setText(end[186]);
                break;
            case 188:
                day = 188;
                tvBody.setText(body[187]);
                tvTitle.setText(title[187]);
                tvSubTitle.setText(subTitle[187]);
                tvEnd.setText(end[187]);
                break;
            case 189:
                day = 189;
                tvBody.setText(body[188]);
                tvTitle.setText(title[188]);
                tvSubTitle.setText(subTitle[188]);
                tvEnd.setText(end[188]);
                break;
            case 190:
                day = 190;
                tvBody.setText(body[189]);
                tvTitle.setText(title[189]);
                tvSubTitle.setText(subTitle[189]);
                tvEnd.setText(end[189]);
                break;
            case 191:
                day = 191;
                tvBody.setText(body[190]);
                tvTitle.setText(title[190]);
                tvSubTitle.setText(subTitle[190]);
                tvEnd.setText(end[190]);
                break;
            case 192:
                day = 192;
                tvBody.setText(body[191]);
                tvTitle.setText(title[191]);
                tvSubTitle.setText(subTitle[191]);
                tvEnd.setText(end[191]);
                break;
            case 193:
                day = 193;
                tvBody.setText(body[192]);
                tvTitle.setText(title[192]);
                tvSubTitle.setText(subTitle[192]);
                tvEnd.setText(end[192]);
                break;
            case 194:
                day = 194;
                tvBody.setText(body[193]);
                tvTitle.setText(title[193]);
                tvSubTitle.setText(subTitle[193]);
                tvEnd.setText(end[193]);
                break;
            case 195:
                day = 195;
                tvBody.setText(body[194]);
                tvTitle.setText(title[194]);
                tvSubTitle.setText(subTitle[194]);
                tvEnd.setText(end[194]);
                break;
            case 196:
                day = 196;
                tvBody.setText(body[195]);
                tvTitle.setText(title[195]);
                tvSubTitle.setText(subTitle[195]);
                tvEnd.setText(end[195]);
                break;
            case 197:
                day = 197;
                tvBody.setText(body[196]);
                tvTitle.setText(title[196]);
                tvSubTitle.setText(subTitle[196]);
                tvEnd.setText(end[196]);
                break;
            case 198:
                day = 198;
                tvBody.setText(body[197]);
                tvTitle.setText(title[197]);
                tvSubTitle.setText(subTitle[197]);
                tvEnd.setText(end[197]);
                break;
            case 199:
                day = 199;
                tvBody.setText(body[198]);
                tvTitle.setText(title[198]);
                tvSubTitle.setText(subTitle[198]);
                tvEnd.setText(end[198]);
                break;
            case 200:
                day = 200;
                tvBody.setText(body[199]);
                tvTitle.setText(title[199]);
                tvSubTitle.setText(subTitle[199]);
                tvEnd.setText(end[199]);
                break;
            case 201:
                day = 201;
                tvBody.setText(body[200]);
                tvTitle.setText(title[200]);
                tvSubTitle.setText(subTitle[200]);
                tvEnd.setText(end[200]);
                break;
            case 202:
                day = 202;
                tvBody.setText(body[201]);
                tvTitle.setText(title[201]);
                tvSubTitle.setText(subTitle[201]);
                tvEnd.setText(end[201]);
                break;
            case 203:
                day = 203;
                tvBody.setText(body[202]);
                tvTitle.setText(title[202]);
                tvSubTitle.setText(subTitle[202]);
                tvEnd.setText(end[202]);
                break;
            case 204:
                day = 204;
                tvBody.setText(body[203]);
                tvTitle.setText(title[203]);
                tvSubTitle.setText(subTitle[203]);
                tvEnd.setText(end[203]);
                break;
            case 205:
                day = 205;
                tvBody.setText(body[204]);
                tvTitle.setText(title[204]);
                tvSubTitle.setText(subTitle[204]);
                tvEnd.setText(end[204]);
                break;
            case 206:
                day = 206;
                tvBody.setText(body[205]);
                tvTitle.setText(title[205]);
                tvSubTitle.setText(subTitle[205]);
                tvEnd.setText(end[205]);
                break;
            case 207:
                day = 207;
                tvBody.setText(body[206]);
                tvTitle.setText(title[206]);
                tvSubTitle.setText(subTitle[206]);
                tvEnd.setText(end[206]);
                break;
            case 208:
                day = 208;
                tvBody.setText(body[207]);
                tvTitle.setText(title[207]);
                tvSubTitle.setText(subTitle[207]);
                tvEnd.setText(end[207]);
                break;
            case 209:
                day = 209;
                tvBody.setText(body[208]);
                tvTitle.setText(title[208]);
                tvSubTitle.setText(subTitle[208]);
                tvEnd.setText(end[208]);
                break;
            case 210:
                day = 210;
                tvBody.setText(body[209]);
                tvTitle.setText(title[209]);
                tvSubTitle.setText(subTitle[209]);
                tvEnd.setText(end[209]);
                break;
            case 211:
                day = 211;
                tvBody.setText(body[210]);
                tvTitle.setText(title[210]);
                tvSubTitle.setText(subTitle[210]);
                tvEnd.setText(end[210]);
                break;
            case 212:
                day = 212;
                tvBody.setText(body[211]);
                tvTitle.setText(title[211]);
                tvSubTitle.setText(subTitle[211]);
                tvEnd.setText(end[211]);
                break;
            case 213:
                day = 213;
                tvBody.setText(body[212]);
                tvTitle.setText(title[212]);
                tvSubTitle.setText(subTitle[212]);
                tvEnd.setText(end[212]);
                break;
            case 214:
                day = 214;
                tvBody.setText(body[213]);
                tvTitle.setText(title[213]);
                tvSubTitle.setText(subTitle[213]);
                tvEnd.setText(end[213]);
                break;
            case 215:
                day = 215;
                tvBody.setText(body[214]);
                tvTitle.setText(title[214]);
                tvSubTitle.setText(subTitle[214]);
                tvEnd.setText(end[214]);
                break;
            case 216:
                day = 216;
                tvBody.setText(body[215]);
                tvTitle.setText(title[215]);
                tvSubTitle.setText(subTitle[215]);
                tvEnd.setText(end[215]);
                break;
            case 217:
                day = 217;
                tvBody.setText(body[216]);
                tvTitle.setText(title[216]);
                tvSubTitle.setText(subTitle[216]);
                tvEnd.setText(end[216]);
                break;
            case 218:
                day = 218;
                tvBody.setText(body[217]);
                tvTitle.setText(title[217]);
                tvSubTitle.setText(subTitle[217]);
                tvEnd.setText(end[217]);
                break;
            case 219:
                day = 219;
                tvBody.setText(body[218]);
                tvTitle.setText(title[218]);
                tvSubTitle.setText(subTitle[218]);
                tvEnd.setText(end[218]);
                break;
            case 220:
                day = 220;
                tvBody.setText(body[219]);
                tvTitle.setText(title[219]);
                tvSubTitle.setText(subTitle[219]);
                tvEnd.setText(end[219]);
                break;
            case 221:
                day = 221;
                tvBody.setText(body[220]);
                tvTitle.setText(title[220]);
                tvSubTitle.setText(subTitle[220]);
                tvEnd.setText(end[220]);
                break;
            case 222:
                day = 222;
                tvBody.setText(body[221]);
                tvTitle.setText(title[221]);
                tvSubTitle.setText(subTitle[221]);
                tvEnd.setText(end[221]);
                break;
            case 223:
                day = 223;
                tvBody.setText(body[222]);
                tvTitle.setText(title[222]);
                tvSubTitle.setText(subTitle[222]);
                tvEnd.setText(end[222]);
                break;
            case 224:
                day = 224;
                tvBody.setText(body[223]);
                tvTitle.setText(title[223]);
                tvSubTitle.setText(subTitle[223]);
                tvEnd.setText(end[223]);
                break;
            case 225:
                day = 225;
                tvBody.setText(body[224]);
                tvTitle.setText(title[224]);
                tvSubTitle.setText(subTitle[224]);
                tvEnd.setText(end[224]);
                break;
            case 226:
                day = 226;
                tvBody.setText(body[225]);
                tvTitle.setText(title[225]);
                tvSubTitle.setText(subTitle[225]);
                tvEnd.setText(end[225]);
                break;
            case 227:
                day = 227;
                tvBody.setText(body[226]);
                tvTitle.setText(title[226]);
                tvSubTitle.setText(subTitle[226]);
                tvEnd.setText(end[226]);
                break;
            case 228:
                day = 228;
                tvBody.setText(body[227]);
                tvTitle.setText(title[227]);
                tvSubTitle.setText(subTitle[227]);
                tvEnd.setText(end[227]);
                break;
            case 229:
                day = 229;
                tvBody.setText(body[228]);
                tvTitle.setText(title[228]);
                tvSubTitle.setText(subTitle[228]);
                tvEnd.setText(end[228]);
                break;
            case 230:
                day = 230;
                tvBody.setText(body[229]);
                tvTitle.setText(title[229]);
                tvSubTitle.setText(subTitle[229]);
                tvEnd.setText(end[229]);
                break;
            case 231:
                day = 231;
                tvBody.setText(body[230]);
                tvTitle.setText(title[230]);
                tvSubTitle.setText(subTitle[230]);
                tvEnd.setText(end[230]);
                break;
            case 232:
                day = 232;
                tvBody.setText(body[231]);
                tvTitle.setText(title[231]);
                tvSubTitle.setText(subTitle[231]);
                tvEnd.setText(end[231]);
                break;
            case 233:
                day = 233;
                tvBody.setText(body[232]);
                tvTitle.setText(title[232]);
                tvSubTitle.setText(subTitle[232]);
                tvEnd.setText(end[232]);
                break;
            case 234:
                day = 234;
                tvBody.setText(body[233]);
                tvTitle.setText(title[233]);
                tvSubTitle.setText(subTitle[233]);
                tvEnd.setText(end[233]);
                break;
            case 235:
                day = 235;
                tvBody.setText(body[234]);
                tvTitle.setText(title[234]);
                tvSubTitle.setText(subTitle[234]);
                tvEnd.setText(end[234]);
                break;
            case 236:
                day = 236;
                tvBody.setText(body[235]);
                tvTitle.setText(title[235]);
                tvSubTitle.setText(subTitle[235]);
                tvEnd.setText(end[235]);
                break;
            case 237:
                day = 237;
                tvBody.setText(body[236]);
                tvTitle.setText(title[236]);
                tvSubTitle.setText(subTitle[236]);
                tvEnd.setText(end[236]);
                break;
            case 238:
                day = 238;
                tvBody.setText(body[237]);
                tvTitle.setText(title[237]);
                tvSubTitle.setText(subTitle[237]);
                tvEnd.setText(end[237]);
                break;
            case 239:
                day = 239;
                tvBody.setText(body[238]);
                tvTitle.setText(title[238]);
                tvSubTitle.setText(subTitle[238]);
                tvEnd.setText(end[238]);
                break;
            case 240:
                day = 240;
                tvBody.setText(body[239]);
                tvTitle.setText(title[239]);
                tvSubTitle.setText(subTitle[239]);
                tvEnd.setText(end[239]);
                break;
            case 241:
                day = 241;
                tvBody.setText(body[240]);
                tvTitle.setText(title[240]);
                tvSubTitle.setText(subTitle[240]);
                tvEnd.setText(end[240]);
                break;
            case 242:
                day = 242;
                tvBody.setText(body[241]);
                tvTitle.setText(title[241]);
                tvSubTitle.setText(subTitle[241]);
                tvEnd.setText(end[241]);
                break;
            case 243:
                day = 243;
                tvBody.setText(body[242]);
                tvTitle.setText(title[242]);
                tvSubTitle.setText(subTitle[242]);
                tvEnd.setText(end[242]);
                break;
            case 244:
                day = 244;
                tvBody.setText(body[243]);
                tvTitle.setText(title[243]);
                tvSubTitle.setText(subTitle[243]);
                tvEnd.setText(end[243]);
                break;
            case 245:
                day = 245;
                tvBody.setText(body[244]);
                tvTitle.setText(title[244]);
                tvSubTitle.setText(subTitle[244]);
                tvEnd.setText(end[244]);
                break;
            case 246:
                day = 246;
                tvBody.setText(body[245]);
                tvTitle.setText(title[245]);
                tvSubTitle.setText(subTitle[245]);
                tvEnd.setText(end[245]);
                break;
            case 247:
                day = 247;
                tvBody.setText(body[246]);
                tvTitle.setText(title[246]);
                tvSubTitle.setText(subTitle[246]);
                tvEnd.setText(end[246]);
                break;
            case 248:
                day = 248;
                tvBody.setText(body[247]);
                tvTitle.setText(title[247]);
                tvSubTitle.setText(subTitle[247]);
                tvEnd.setText(end[247]);
                break;
            case 249:
                day = 249;
                tvBody.setText(body[248]);
                tvTitle.setText(title[248]);
                tvSubTitle.setText(subTitle[248]);
                tvEnd.setText(end[248]);
                break;
            case 250:
                day = 250;
                tvBody.setText(body[249]);
                tvTitle.setText(title[249]);
                tvSubTitle.setText(subTitle[249]);
                tvEnd.setText(end[249]);
                break;
            case 251:
                day = 251;
                tvBody.setText(body[250]);
                tvTitle.setText(title[250]);
                tvSubTitle.setText(subTitle[250]);
                tvEnd.setText(end[250]);
                break;
            case 252:
                day = 252;
                tvBody.setText(body[251]);
                tvTitle.setText(title[251]);
                tvSubTitle.setText(subTitle[251]);
                tvEnd.setText(end[251]);
                break;
            case 253:
                day = 253;
                tvBody.setText(body[252]);
                tvTitle.setText(title[252]);
                tvSubTitle.setText(subTitle[252]);
                tvEnd.setText(end[252]);
                break;
            case 254:
                day = 254;
                tvBody.setText(body[253]);
                tvTitle.setText(title[253]);
                tvSubTitle.setText(subTitle[253]);
                tvEnd.setText(end[253]);
                break;
            case 255:
                day = 255;
                tvBody.setText(body[254]);
                tvTitle.setText(title[254]);
                tvSubTitle.setText(subTitle[254]);
                tvEnd.setText(end[254]);
                break;
            case 256:
                day = 256;
                tvBody.setText(body[255]);
                tvTitle.setText(title[255]);
                tvSubTitle.setText(subTitle[255]);
                tvEnd.setText(end[255]);
                break;
            case 257:
                day = 257;
                tvBody.setText(body[256]);
                tvTitle.setText(title[256]);
                tvSubTitle.setText(subTitle[256]);
                tvEnd.setText(end[256]);
                break;
            case 258:
                day = 258;
                tvBody.setText(body[257]);
                tvTitle.setText(title[257]);
                tvSubTitle.setText(subTitle[257]);
                tvEnd.setText(end[257]);
                break;
            case 259:
                day = 259;
                tvBody.setText(body[258]);
                tvTitle.setText(title[258]);
                tvSubTitle.setText(subTitle[258]);
                tvEnd.setText(end[258]);
                break;
            case 260:
                day = 260;
                tvBody.setText(body[259]);
                tvTitle.setText(title[259]);
                tvSubTitle.setText(subTitle[259]);
                tvEnd.setText(end[259]);
                break;
            case 261:
                day = 261;
                tvBody.setText(body[260]);
                tvTitle.setText(title[260]);
                tvSubTitle.setText(subTitle[260]);
                tvEnd.setText(end[260]);
                break;
            case 262:
                day = 262;
                tvBody.setText(body[261]);
                tvTitle.setText(title[261]);
                tvSubTitle.setText(subTitle[261]);
                tvEnd.setText(end[261]);
                break;
            case 263:
                day = 263;
                tvBody.setText(body[262]);
                tvTitle.setText(title[262]);
                tvSubTitle.setText(subTitle[262]);
                tvEnd.setText(end[262]);
                break;
            case 264:
                day = 264;
                tvBody.setText(body[263]);
                tvTitle.setText(title[263]);
                tvSubTitle.setText(subTitle[263]);
                tvEnd.setText(end[263]);
                break;
            case 265:
                day = 265;
                tvBody.setText(body[264]);
                tvTitle.setText(title[264]);
                tvSubTitle.setText(subTitle[264]);
                tvEnd.setText(end[264]);
                break;
            case 266:
                day = 266;
                tvBody.setText(body[265]);
                tvTitle.setText(title[265]);
                tvSubTitle.setText(subTitle[265]);
                tvEnd.setText(end[265]);
                break;
            case 267:
                day = 267;
                tvBody.setText(body[266]);
                tvTitle.setText(title[266]);
                tvSubTitle.setText(subTitle[266]);
                tvEnd.setText(end[266]);
                break;
            case 268:
                day = 268;
                tvBody.setText(body[267]);
                tvTitle.setText(title[267]);
                tvSubTitle.setText(subTitle[267]);
                tvEnd.setText(end[267]);
                break;
            case 269:
                day = 269;
                tvBody.setText(body[268]);
                tvTitle.setText(title[268]);
                tvSubTitle.setText(subTitle[268]);
                tvEnd.setText(end[268]);
                break;
            case 270:
                day = 270;
                tvBody.setText(body[269]);
                tvTitle.setText(title[269]);
                tvSubTitle.setText(subTitle[269]);
                tvEnd.setText(end[269]);
                break;
            case 271:
                day = 271;
                tvBody.setText(body[270]);
                tvTitle.setText(title[270]);
                tvSubTitle.setText(subTitle[270]);
                tvEnd.setText(end[270]);
                break;
            case 272:
                day = 272;
                tvBody.setText(body[271]);
                tvTitle.setText(title[271]);
                tvSubTitle.setText(subTitle[271]);
                tvEnd.setText(end[271]);
                break;
            case 273:
                day = 273;
                tvBody.setText(body[272]);
                tvTitle.setText(title[272]);
                tvSubTitle.setText(subTitle[272]);
                tvEnd.setText(end[272]);
                break;
            case 274:
                day = 274;
                tvBody.setText(body[273]);
                tvTitle.setText(title[273]);
                tvSubTitle.setText(subTitle[273]);
                tvEnd.setText(end[273]);
                break;
            case 275:
                day = 275;
                tvBody.setText(body[274]);
                tvTitle.setText(title[274]);
                tvSubTitle.setText(subTitle[274]);
                tvEnd.setText(end[274]);
                break;
            case 276:
                day = 276;
                tvBody.setText(body[275]);
                tvTitle.setText(title[275]);
                tvSubTitle.setText(subTitle[275]);
                tvEnd.setText(end[275]);
                break;
            case 277:
                day = 277;
                tvBody.setText(body[276]);
                tvTitle.setText(title[276]);
                tvSubTitle.setText(subTitle[276]);
                tvEnd.setText(end[276]);
                break;
            case 278:
                day = 278;
                tvBody.setText(body[277]);
                tvTitle.setText(title[277]);
                tvSubTitle.setText(subTitle[277]);
                tvEnd.setText(end[277]);
                break;
            case 279:
                day = 279;
                tvBody.setText(body[278]);
                tvTitle.setText(title[278]);
                tvSubTitle.setText(subTitle[278]);
                tvEnd.setText(end[278]);
                break;
            case 280:
                day = 280;
                tvBody.setText(body[279]);
                tvTitle.setText(title[279]);
                tvSubTitle.setText(subTitle[279]);
                tvEnd.setText(end[279]);
                break;
            case 281:
                day = 281;
                tvBody.setText(body[280]);
                tvTitle.setText(title[280]);
                tvSubTitle.setText(subTitle[280]);
                tvEnd.setText(end[280]);
                break;
            case 282:
                day = 282;
                tvBody.setText(body[281]);
                tvTitle.setText(title[281]);
                tvSubTitle.setText(subTitle[281]);
                tvEnd.setText(end[281]);
                break;
            case 283:
                day = 283;
                tvBody.setText(body[282]);
                tvTitle.setText(title[282]);
                tvSubTitle.setText(subTitle[282]);
                tvEnd.setText(end[282]);
                break;
            case 284:
                day = 284;
                tvBody.setText(body[283]);
                tvTitle.setText(title[283]);
                tvSubTitle.setText(subTitle[283]);
                tvEnd.setText(end[283]);
                break;
            case 285:
                day = 285;
                tvBody.setText(body[284]);
                tvTitle.setText(title[284]);
                tvSubTitle.setText(subTitle[284]);
                tvEnd.setText(end[284]);
                break;
            case 286:
                day = 286;
                tvBody.setText(body[285]);
                tvTitle.setText(title[285]);
                tvSubTitle.setText(subTitle[285]);
                tvEnd.setText(end[285]);
                break;
            case 287:
                day = 287;
                tvBody.setText(body[286]);
                tvTitle.setText(title[286]);
                tvSubTitle.setText(subTitle[286]);
                tvEnd.setText(end[286]);
                break;
            case 288:
                day = 288;
                tvBody.setText(body[287]);
                tvTitle.setText(title[287]);
                tvSubTitle.setText(subTitle[287]);
                tvEnd.setText(end[287]);
                break;
            case 289:
                day = 289;
                tvBody.setText(body[288]);
                tvTitle.setText(title[288]);
                tvSubTitle.setText(subTitle[288]);
                tvEnd.setText(end[288]);
                break;
            case 290:
                day = 290;
                tvBody.setText(body[289]);
                tvTitle.setText(title[289]);
                tvSubTitle.setText(subTitle[289]);
                tvEnd.setText(end[289]);
                break;
            case 291:
                day = 291;
                tvBody.setText(body[290]);
                tvTitle.setText(title[290]);
                tvSubTitle.setText(subTitle[290]);
                tvEnd.setText(end[290]);
                break;
            case 292:
                day = 292;
                tvBody.setText(body[291]);
                tvTitle.setText(title[291]);
                tvSubTitle.setText(subTitle[291]);
                tvEnd.setText(end[291]);
                break;
            case 293:
                day = 293;
                tvBody.setText(body[292]);
                tvTitle.setText(title[292]);
                tvSubTitle.setText(subTitle[292]);
                tvEnd.setText(end[292]);
                break;
            case 294:
                day = 294;
                tvBody.setText(body[293]);
                tvTitle.setText(title[293]);
                tvSubTitle.setText(subTitle[293]);
                tvEnd.setText(end[293]);
                break;
            case 295:
                day = 295;
                tvBody.setText(body[294]);
                tvTitle.setText(title[294]);
                tvSubTitle.setText(subTitle[294]);
                tvEnd.setText(end[294]);
                break;
            case 296:
                day = 296;
                tvBody.setText(body[295]);
                tvTitle.setText(title[295]);
                tvSubTitle.setText(subTitle[295]);
                tvEnd.setText(end[295]);
                break;
            case 297:
                day = 297;
                tvBody.setText(body[296]);
                tvTitle.setText(title[296]);
                tvSubTitle.setText(subTitle[296]);
                tvEnd.setText(end[296]);
                break;
            case 298:
                day = 298;
                tvBody.setText(body[297]);
                tvTitle.setText(title[297]);
                tvSubTitle.setText(subTitle[297]);
                tvEnd.setText(end[297]);
                break;
            case 299:
                day = 299;
                tvBody.setText(body[298]);
                tvTitle.setText(title[298]);
                tvSubTitle.setText(subTitle[298]);
                tvEnd.setText(end[298]);
                break;
            case 300:
                day = 300;
                tvBody.setText(body[299]);
                tvTitle.setText(title[299]);
                tvSubTitle.setText(subTitle[299]);
                tvEnd.setText(end[299]);
                break;
            case 301:
                day = 301;
                tvBody.setText(body[300]);
                tvTitle.setText(title[300]);
                tvSubTitle.setText(subTitle[300]);
                tvEnd.setText(end[300]);
                break;
            case 302:
                day = 302;
                tvBody.setText(body[301]);
                tvTitle.setText(title[301]);
                tvSubTitle.setText(subTitle[301]);
                tvEnd.setText(end[301]);
                break;
            case 303:
                day = 303;
                tvBody.setText(body[302]);
                tvTitle.setText(title[302]);
                tvSubTitle.setText(subTitle[302]);
                tvEnd.setText(end[302]);
                break;
            case 304:
                day = 304;
                tvBody.setText(body[303]);
                tvTitle.setText(title[303]);
                tvSubTitle.setText(subTitle[303]);
                tvEnd.setText(end[303]);
                break;
            case 305:
                day = 305;
                tvBody.setText(body[304]);
                tvTitle.setText(title[304]);
                tvSubTitle.setText(subTitle[304]);
                tvEnd.setText(end[304]);
                break;
            case 306:
                day = 306;
                tvBody.setText(body[305]);
                tvTitle.setText(title[305]);
                tvSubTitle.setText(subTitle[305]);
                tvEnd.setText(end[305]);
                break;
            case 307:
                day = 307;
                tvBody.setText(body[306]);
                tvTitle.setText(title[306]);
                tvSubTitle.setText(subTitle[306]);
                tvEnd.setText(end[306]);
                break;
            case 308:
                day = 308;
                tvBody.setText(body[307]);
                tvTitle.setText(title[307]);
                tvSubTitle.setText(subTitle[307]);
                tvEnd.setText(end[307]);
                break;
            case 309:
                day = 309;
                tvBody.setText(body[308]);
                tvTitle.setText(title[308]);
                tvSubTitle.setText(subTitle[308]);
                tvEnd.setText(end[308]);
                break;
            case 310:
                day = 310;
                tvBody.setText(body[309]);
                tvTitle.setText(title[309]);
                tvSubTitle.setText(subTitle[309]);
                tvEnd.setText(end[309]);
                break;
            case 311:
                day = 311;
                tvBody.setText(body[310]);
                tvTitle.setText(title[310]);
                tvSubTitle.setText(subTitle[310]);
                tvEnd.setText(end[310]);
                break;
            case 312:
                day = 312;
                tvBody.setText(body[311]);
                tvTitle.setText(title[311]);
                tvSubTitle.setText(subTitle[311]);
                tvEnd.setText(end[311]);
                break;
            case 313:
                day = 313;
                tvBody.setText(body[312]);
                tvTitle.setText(title[312]);
                tvSubTitle.setText(subTitle[312]);
                tvEnd.setText(end[312]);
                break;
            case 314:
                day = 314;
                tvBody.setText(body[313]);
                tvTitle.setText(title[313]);
                tvSubTitle.setText(subTitle[313]);
                tvEnd.setText(end[313]);
                break;
            case 315:
                day = 315;
                tvBody.setText(body[314]);
                tvTitle.setText(title[314]);
                tvSubTitle.setText(subTitle[314]);
                tvEnd.setText(end[314]);
                break;
            case 316:
                day = 316;
                tvBody.setText(body[315]);
                tvTitle.setText(title[315]);
                tvSubTitle.setText(subTitle[315]);
                tvEnd.setText(end[315]);
                break;
            case 317:
                day = 317;
                tvBody.setText(body[316]);
                tvTitle.setText(title[316]);
                tvSubTitle.setText(subTitle[316]);
                tvEnd.setText(end[316]);
                break;
            case 318:
                day = 318;
                tvBody.setText(body[317]);
                tvTitle.setText(title[317]);
                tvSubTitle.setText(subTitle[317]);
                tvEnd.setText(end[317]);
                break;
            case 319:
                day = 319;
                tvBody.setText(body[318]);
                tvTitle.setText(title[318]);
                tvSubTitle.setText(subTitle[318]);
                tvEnd.setText(end[318]);
                break;
            case 320:
                day = 320;
                tvBody.setText(body[319]);
                tvTitle.setText(title[319]);
                tvSubTitle.setText(subTitle[319]);
                tvEnd.setText(end[319]);
                break;
            case 321:
                day = 321;
                tvBody.setText(body[320]);
                tvTitle.setText(title[320]);
                tvSubTitle.setText(subTitle[320]);
                tvEnd.setText(end[320]);
                break;
            case 322:
                day = 322;
                tvBody.setText(body[321]);
                tvTitle.setText(title[321]);
                tvSubTitle.setText(subTitle[321]);
                tvEnd.setText(end[321]);
                break;
            case 323:
                day = 323;
                tvBody.setText(body[322]);
                tvTitle.setText(title[322]);
                tvSubTitle.setText(subTitle[322]);
                tvEnd.setText(end[322]);
                break;
            case 324:
                day = 324;
                tvBody.setText(body[323]);
                tvTitle.setText(title[323]);
                tvSubTitle.setText(subTitle[323]);
                tvEnd.setText(end[323]);
                break;
            case 325:
                day = 325;
                tvBody.setText(body[324]);
                tvTitle.setText(title[324]);
                tvSubTitle.setText(subTitle[324]);
                tvEnd.setText(end[324]);
                break;
            case 326:
                day = 326;
                tvBody.setText(body[325]);
                tvTitle.setText(title[325]);
                tvSubTitle.setText(subTitle[325]);
                tvEnd.setText(end[325]);
                break;
            case 327:
                day = 327;
                tvBody.setText(body[326]);
                tvTitle.setText(title[326]);
                tvSubTitle.setText(subTitle[326]);
                tvEnd.setText(end[326]);
                break;
            case 328:
                day = 328;
                tvBody.setText(body[327]);
                tvTitle.setText(title[327]);
                tvSubTitle.setText(subTitle[327]);
                tvEnd.setText(end[327]);
                break;
            case 329:
                day = 329;
                tvBody.setText(body[328]);
                tvTitle.setText(title[328]);
                tvSubTitle.setText(subTitle[328]);
                tvEnd.setText(end[328]);
                break;
            case 330:
                day = 330;
                tvBody.setText(body[329]);
                tvTitle.setText(title[329]);
                tvSubTitle.setText(subTitle[329]);
                tvEnd.setText(end[329]);
                break;
            case 331:
                day = 331;
                tvBody.setText(body[330]);
                tvTitle.setText(title[330]);
                tvSubTitle.setText(subTitle[330]);
                tvEnd.setText(end[330]);
                break;
            case 332:
                day = 332;
                tvBody.setText(body[331]);
                tvTitle.setText(title[331]);
                tvSubTitle.setText(subTitle[331]);
                tvEnd.setText(end[331]);
                break;
            case 333:
                day = 333;
                tvBody.setText(body[332]);
                tvTitle.setText(title[332]);
                tvSubTitle.setText(subTitle[332]);
                tvEnd.setText(end[332]);
                break;
            case 334:
                day = 334;
                tvBody.setText(body[333]);
                tvTitle.setText(title[333]);
                tvSubTitle.setText(subTitle[333]);
                tvEnd.setText(end[333]);
                break;
            case 335:
                day = 335;
                tvBody.setText(body[334]);
                tvTitle.setText(title[334]);
                tvSubTitle.setText(subTitle[334]);
                tvEnd.setText(end[334]);
                break;
            case 336:
                day = 336;
                tvBody.setText(body[335]);
                tvTitle.setText(title[335]);
                tvSubTitle.setText(subTitle[335]);
                tvEnd.setText(end[335]);
                break;
            case 337:
                day = 337;
                tvBody.setText(body[336]);
                tvTitle.setText(title[336]);
                tvSubTitle.setText(subTitle[336]);
                tvEnd.setText(end[336]);
                break;
            case 338:
                day = 338;
                tvBody.setText(body[337]);
                tvTitle.setText(title[337]);
                tvSubTitle.setText(subTitle[337]);
                tvEnd.setText(end[337]);
                break;
            case 339:
                day = 339;
                tvBody.setText(body[338]);
                tvTitle.setText(title[338]);
                tvSubTitle.setText(subTitle[338]);
                tvEnd.setText(end[338]);
                break;
            case 340:
                day = 340;
                tvBody.setText(body[339]);
                tvTitle.setText(title[339]);
                tvSubTitle.setText(subTitle[339]);
                tvEnd.setText(end[339]);
                break;
            case 341:
                day = 341;
                tvBody.setText(body[340]);
                tvTitle.setText(title[340]);
                tvSubTitle.setText(subTitle[340]);
                tvEnd.setText(end[340]);
                break;
            case 342:
                day = 342;
                tvBody.setText(body[341]);
                tvTitle.setText(title[341]);
                tvSubTitle.setText(subTitle[341]);
                tvEnd.setText(end[341]);
                break;
            case 343:
                day = 343;
                tvBody.setText(body[342]);
                tvTitle.setText(title[342]);
                tvSubTitle.setText(subTitle[342]);
                tvEnd.setText(end[342]);
                break;
            case 344:
                day = 344;
                tvBody.setText(body[343]);
                tvTitle.setText(title[343]);
                tvSubTitle.setText(subTitle[343]);
                tvEnd.setText(end[343]);
                break;
            case 345:
                day = 345;
                tvBody.setText(body[344]);
                tvTitle.setText(title[344]);
                tvSubTitle.setText(subTitle[344]);
                tvEnd.setText(end[344]);
                break;
            case 346:
                day = 346;
                tvBody.setText(body[345]);
                tvTitle.setText(title[345]);
                tvSubTitle.setText(subTitle[345]);
                tvEnd.setText(end[345]);
                break;
            case 347:
                day = 347;
                tvBody.setText(body[346]);
                tvTitle.setText(title[346]);
                tvSubTitle.setText(subTitle[346]);
                tvEnd.setText(end[346]);
                break;
            case 348:
                day = 348;
                tvBody.setText(body[347]);
                tvTitle.setText(title[347]);
                tvSubTitle.setText(subTitle[347]);
                tvEnd.setText(end[347]);
                break;
            case 349:
                day = 349;
                tvBody.setText(body[348]);
                tvTitle.setText(title[348]);
                tvSubTitle.setText(subTitle[348]);
                tvEnd.setText(end[348]);
                break;
            case 350:
                day = 350;
                tvBody.setText(body[349]);
                tvTitle.setText(title[349]);
                tvSubTitle.setText(subTitle[349]);
                tvEnd.setText(end[349]);
                break;
            case 351:
                day = 351;
                tvBody.setText(body[350]);
                tvTitle.setText(title[350]);
                tvSubTitle.setText(subTitle[350]);
                tvEnd.setText(end[350]);
                break;
            case 352:
                day = 352;
                tvBody.setText(body[351]);
                tvTitle.setText(title[351]);
                tvSubTitle.setText(subTitle[351]);
                tvEnd.setText(end[351]);
                break;
            case 353:
                day = 353;
                tvBody.setText(body[352]);
                tvTitle.setText(title[352]);
                tvSubTitle.setText(subTitle[352]);
                tvEnd.setText(end[352]);
                break;
            case 354:
                day = 354;
                tvBody.setText(body[353]);
                tvTitle.setText(title[353]);
                tvSubTitle.setText(subTitle[353]);
                tvEnd.setText(end[353]);
                break;
            case 355:
                day = 355;
                tvBody.setText(body[354]);
                tvTitle.setText(title[354]);
                tvSubTitle.setText(subTitle[354]);
                tvEnd.setText(end[354]);
                break;
            case 356:
                day = 356;
                tvBody.setText(body[355]);
                tvTitle.setText(title[355]);
                tvSubTitle.setText(subTitle[355]);
                tvEnd.setText(end[355]);
                break;
            case 357:
                day = 357;
                tvBody.setText(body[356]);
                tvTitle.setText(title[356]);
                tvSubTitle.setText(subTitle[356]);
                tvEnd.setText(end[356]);
                break;
            case 358:
                day = 358;
                tvBody.setText(body[357]);
                tvTitle.setText(title[357]);
                tvSubTitle.setText(subTitle[357]);
                tvEnd.setText(end[357]);
                break;
            case 359:
                day = 359;
                tvBody.setText(body[358]);
                tvTitle.setText(title[358]);
                tvSubTitle.setText(subTitle[358]);
                tvEnd.setText(end[358]);
                break;
            case 360:
                day = 360;
                tvBody.setText(body[359]);
                tvTitle.setText(title[359]);
                tvSubTitle.setText(subTitle[359]);
                tvEnd.setText(end[359]);
                break;
            case 361:
                day = 361;
                tvBody.setText(body[360]);
                tvTitle.setText(title[360]);
                tvSubTitle.setText(subTitle[360]);
                tvEnd.setText(end[360]);
                break;
            case 362:
                day = 362;
                tvBody.setText(body[361]);
                tvTitle.setText(title[361]);
                tvSubTitle.setText(subTitle[361]);
                tvEnd.setText(end[361]);
                break;
            case 363:
                day = 363;
                tvBody.setText(body[362]);
                tvTitle.setText(title[362]);
                tvSubTitle.setText(subTitle[362]);
                tvEnd.setText(end[362]);
                break;
            case 364:
                day = 364;
                tvBody.setText(body[363]);
                tvTitle.setText(title[363]);
                tvSubTitle.setText(subTitle[363]);
                tvEnd.setText(end[363]);
                break;
            case 365:
                day = 365;
                tvBody.setText(body[364]);
                tvTitle.setText(title[364]);
                tvSubTitle.setText(subTitle[364]);
                tvEnd.setText(end[364]);
                break;
            case 366:
                day = 366;
                tvBody.setText(body[365]);
                tvTitle.setText(title[365]);
                tvSubTitle.setText(subTitle[365]);
                tvEnd.setText(end[365]);
                break;
        }
    }




    @Override
    protected void onResume(){
        super.onResume();

        Bundle params = new Bundle();
        params.putString(FirebaseAnalytics.Param.ITEM_ID, "JFT_Hit");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM, params);
    }

    public void justForToday(View view) {
        Intent intentMainToJFT = new Intent(this, JustForToday.class);
        startActivity(intentMainToJFT);
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

    public void toUsefulPlacesAndLinks(View view) {
        Intent intentToUsefulPlacesAndLinks = new Intent(this, UsefulPlacesAndLinks.class);
        startActivity(intentToUsefulPlacesAndLinks);
    }
    public void start(View view){
        Intent intentWelcometoMain = new Intent(this, MainPage.class);
        startActivity(intentWelcometoMain);
    }
}
