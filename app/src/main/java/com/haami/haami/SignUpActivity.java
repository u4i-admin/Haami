package com.haami.haami;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.haami.haami.R;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        TextView agreement_button = findViewById(R.id.agreement_button);
        Button create_account_button = findViewById(R.id.create_account_button);

        agreement_button.setOnClickListener(this);
        create_account_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.agreement_button:
                Intent agreementIntent = new Intent(SignUpActivity.this, AgreementActivity.class);
                startActivity(agreementIntent);
                break;
            case R.id.create_account_button:

                break;
        }
    }
}