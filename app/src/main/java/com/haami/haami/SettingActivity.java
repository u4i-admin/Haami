package com.haami.haami;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.haami.haami.R;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        ImageButton return_button = findViewById(R.id.return_button);
        Button my_account_button = findViewById(R.id.my_account_button);
        Button clock_adjustment_button = findViewById(R.id.clock_adjustment_button);
        Button privacy_button = findViewById(R.id.privacy_button);
        Button about_haami_button = findViewById(R.id.about_haami_button);
        Button update_button = findViewById(R.id.update_button);
        Button exit_button = findViewById(R.id.exit_button);

        return_button.setOnClickListener(this);
        my_account_button.setOnClickListener(this);
        clock_adjustment_button.setOnClickListener(this);
        privacy_button.setOnClickListener(this);
        about_haami_button.setOnClickListener(this);
        update_button.setOnClickListener(this);
        exit_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.return_button:
                finish();
                break;
            case R.id.my_account_button:

                break;
            case R.id.clock_adjustment_button:

                break;
            case R.id.your_comments_button:

                break;
            case R.id.privacy_button:

                break;
            case R.id.about_haami_button:
                Intent aboutActivity = new Intent(SettingActivity.this, AboutActivity.class);
                startActivity(aboutActivity);
                break;
            case R.id.update_button:

                break;
            case R.id.exit_button:

                break;
        }
    }
}