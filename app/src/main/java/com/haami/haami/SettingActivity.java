package com.haami.haami;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.haami.haami.R;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private ImageView account_image;
    private TextView user_name_textview;
    private TextView email_textview;
    private Button exit_button;
    private Button my_account_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        ImageButton return_button = findViewById(R.id.return_button);
        my_account_button = findViewById(R.id.create_account_button);
        Button clock_adjustment_button = findViewById(R.id.clock_adjustment_button);
        Button privacy_button = findViewById(R.id.privacy_button);
        Button about_haami_button = findViewById(R.id.about_haami_button);
        Button update_button = findViewById(R.id.update_button);
        exit_button = findViewById(R.id.exit_button);
        account_image = findViewById(R.id.account_image);
        user_name_textview = findViewById(R.id.user_name_textview);
        email_textview = findViewById(R.id.email_textview);

        return_button.setOnClickListener(this);
        my_account_button.setOnClickListener(this);
        clock_adjustment_button.setOnClickListener(this);
        privacy_button.setOnClickListener(this);
        about_haami_button.setOnClickListener(this);
        update_button.setOnClickListener(this);
        exit_button.setOnClickListener(this);
        checkAccount();
    }

    @Override
    protected void onResume() {
        super.onResume();

        checkAccount();
    }

    private void checkAccount() {
        SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", MODE_PRIVATE);
        String token = sharedPreferences.getString("token", null);
        if (token != null) {
            account_image.setVisibility(View.VISIBLE);
            user_name_textview.setVisibility(View.VISIBLE);
            email_textview.setVisibility(View.VISIBLE);
            exit_button.setVisibility(View.VISIBLE);
            my_account_button.setVisibility(View.GONE);
            user_name_textview.setText(sharedPreferences.getString("user_name", null));
            email_textview.setText(sharedPreferences.getString("email", null));
        }
    }

    @Override
    public void onClick(View v) {
        SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", MODE_PRIVATE);
        switch (v.getId()) {
            case R.id.return_button:
                finish();
                break;
            case R.id.create_account_button:
                Intent loginIntent = new Intent(this, LoginActivity.class);
                startActivity(loginIntent);
                break;
            case R.id.clock_adjustment_button:
                PersianCalendar persianCalendar = new PersianCalendar();
                int year = sharedPreferences.getInt("year", persianCalendar.getPersianYear());
                int month = sharedPreferences.getInt("month", persianCalendar.getPersianMonth());
                int day = sharedPreferences.getInt("day", persianCalendar.getPersianDay());
                DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
                        this,
                        year,
                        month,
                        day
                );
                datePickerDialog.show(getFragmentManager(), "DatePickerDialog");
                break;
            case R.id.your_comments_button:

                break;
            case R.id.privacy_button:
                Intent agreementActivity = new Intent(this, AgreementActivity.class);
                startActivity(agreementActivity);
                break;
            case R.id.about_haami_button:
                Intent aboutActivity = new Intent(SettingActivity.this, AboutActivity.class);
                startActivity(aboutActivity);
                break;
            case R.id.update_button:

                break;
            case R.id.exit_button:
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("token");
                editor.remove("isLoggedIn");
                editor.remove("isProUser");
                editor.remove("isAppIdRegistered");
                editor.remove("user_name");
                editor.remove("email");
                editor.apply();
                account_image.setVisibility(View.GONE);
                user_name_textview.setVisibility(View.GONE);
                email_textview.setVisibility(View.GONE);
                exit_button.setVisibility(View.GONE);
                my_account_button.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("year", year);
        editor.putInt("month", monthOfYear);
        editor.putInt("day", dayOfMonth);
        editor.commit();
    }
}