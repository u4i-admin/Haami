package com.haami.haami;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.haami.haami.R;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;

import java.util.Date;

import pl.pawelkleczkowski.customgauge.CustomGauge;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private RelativeLayout back_dim_layout;
    private PopupWindow popupWindow;
    private View popupLayout;
    private TextView day_text;
    private Button set_clock_button;
    private TextView days_count;
    private ImageView sign_not_choose_image;
    private CustomGauge gauge;
    private ImageButton sign_button;

    public HomeFragment() {

    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Button just_for_today_button = getView().findViewById(R.id.just_for_today_button);
        sign_button = getView().findViewById(R.id.sign_button);
        back_dim_layout = getView().getRootView().findViewById(R.id.back_dim_layout);
        Button not_feeling_good_button = getView().findViewById(R.id.not_feeling_good_button);
        ImageButton setting_button = getView().findViewById(R.id.setting_button);
        gauge = getView().findViewById(R.id.gauge);
        sign_not_choose_image = getView().findViewById(R.id.sign_not_choose_image);
        days_count = getView().findViewById(R.id.days_count);
        day_text = getView().findViewById(R.id.day_text);
        set_clock_button = getView().findViewById(R.id.set_clock_button);
        showClock();

        LayoutInflater layoutInflater = (LayoutInflater)getActivity().getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        popupLayout = layoutInflater.from(getActivity()).inflate(R.layout.popup_sign, null);
        popupWindow = new PopupWindow(popupLayout, 800, 1100, true);

        just_for_today_button.setOnClickListener(this);
        not_feeling_good_button.setOnClickListener(this);
        sign_button.setOnClickListener(this);
        setting_button.setOnClickListener(this);
        set_clock_button.setOnClickListener(this);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("year", year);
        editor.putInt("month", monthOfYear);
        editor.putInt("day", dayOfMonth);
        editor.commit();
        showClock();
    }

    public void showClock() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("myPrefs", MODE_PRIVATE);
        int year = sharedPreferences.getInt("year", 0);

        if (year == 0) {
            gauge.setPointEndColor(R.color.colorBorder);
            gauge.setPointStartColor(R.color.colorBorder);
            gauge.setPointSize(0);
            sign_not_choose_image.setVisibility(View.VISIBLE);
            set_clock_button.setVisibility(View.VISIBLE);
            sign_button.setVisibility(View.GONE);
            days_count.setVisibility(View.GONE);
            day_text.setVisibility(View.GONE);
        } else {
            int month = sharedPreferences.getInt("month", 0);
            int day = sharedPreferences.getInt("day", 0);
            int value = compareDates(year, month, day);
            gauge.setPointSize(value);
            sign_not_choose_image.setVisibility(View.GONE);
            set_clock_button.setVisibility(View.GONE);
            sign_button.setVisibility(View.VISIBLE);
            days_count.setVisibility(View.VISIBLE);
            day_text.setVisibility(View.VISIBLE);
            days_count.setText(String.valueOf(value));
        }
    }

    public int compareDates(int year, int month, int day) {
        PersianCalendar cal = new PersianCalendar();
        int currentYear = cal.getPersianYear();
        int currentMonth = cal.getPersianMonth();
        int currentDay = cal.getPersianDay();
        int exchange = 0;
        for (int i = year; i <= currentYear; i++) {
            int limitedMonth = i < currentYear ? 11 : currentMonth;
            boolean isLeapYear = cal.isLeapYear(i);
            for (int j = i > year ? 0 : month; j <= limitedMonth; j++) {
                if (i < currentYear || j < limitedMonth) {
                    exchange += j < 6 ? 31 : j < 11 ? 30 : isLeapYear ? 30 : 29;
                } else {
                    exchange += currentDay - day;
                }
            }
        }
        return exchange;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.just_for_today_button:
                ((MainActivity) getActivity()).replaceFragments(TodayFragment.class, null);
                break;
            case R.id.not_feeling_good_button:
                ((MainActivity) getActivity()).replaceFragments(NotFeelingGoodFragment.class, null);
                break;
            case R.id.sign_button:
                back_dim_layout.setVisibility(View.VISIBLE);
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        back_dim_layout.setVisibility(View.GONE);
                    }
                });
                popupWindow.showAtLocation(popupLayout, Gravity.CENTER, 0, 0);
                popupWindow.getContentView().findViewById(R.id.close_button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                break;
            case R.id.setting_button:
                Intent settingActivity = new Intent(getActivity(), SettingActivity.class);
                startActivity(settingActivity);
                break;
            case R.id.set_clock_button:
                PersianCalendar persianCalendar = new PersianCalendar();
                DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
                        this,
                        persianCalendar.getPersianYear(),
                        persianCalendar.getPersianMonth(),
                        persianCalendar.getPersianDay()
                );
                datePickerDialog.show(getActivity().getFragmentManager(), "DatePickerDialog");
                break;
        }
    }
}