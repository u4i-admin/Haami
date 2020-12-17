package com.haami.haami;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.haami.haami.R;
import com.haami.haami.app.AppController;
import com.haami.haami.models.responses.UserResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import static com.haami.haami.Constants.getServerUrl;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "SignUpActivity";
    private ConstraintLayout back_dim_layout;
    private PopupWindow popupWindow;
    private View popupLayout;
    private EditText name_input;
    private EditText email_input;
    private EditText password_input;
    private CheckBox agreement_checkbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name_input = findViewById(R.id.name_input);
        email_input = findViewById(R.id.email_input);
        password_input = findViewById(R.id.password_input);
        agreement_checkbox = findViewById(R.id.agreement_checkbox);
        TextView agreement_button = findViewById(R.id.agreement_button);
        Button create_account_button = findViewById(R.id.create_account_button);
        back_dim_layout = findViewById(R.id.back_dim_layout);
        ImageView loading_image = findViewById(R.id.loading_image);
        Glide.with(this).load(R.drawable.haami_loading).into(loading_image);

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
                String name = name_input.getText().toString();
                String email = email_input.getText().toString();
                String password = password_input.getText().toString();
                if (!agreement_checkbox.isChecked()) {
                    agreement_checkbox.setTextColor(getResources().getColor(R.color.mdtp_red));
                }
                if (name.isEmpty()) {
                    name_input.setBackgroundColor(getResources().getColor(R.color.mdtp_red));
                }
                if (email.isEmpty()) {
                    email_input.setBackgroundColor(getResources().getColor(R.color.mdtp_red));
                }
                if (password.length() < 5) {
                    password_input.setBackgroundColor(getResources().getColor(R.color.mdtp_red));
                }
                if (agreement_checkbox.isChecked() && !name.isEmpty() && !email.isEmpty() && password.length() > 4) {
                    try {
                        InputMethodManager inputManager = (InputMethodManager) SignUpActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                        doSignUp(name, email, password);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    private void doSignUp(String name, String email, String password) throws JSONException {
        String url = getServerUrl() + "api/user/Signup";

        back_dim_layout.setVisibility(View.VISIBLE);

        final JSONObject jsonBody = new JSONObject("{\"name\": \"" + name + "\", \"email\":\"" + email + "\",\"password\":\"" + password + "\"}");

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        back_dim_layout.setVisibility(View.GONE);
                        Toast.makeText(SignUpActivity.this, R.string.signup_successful, Toast.LENGTH_LONG).show();
                        finish();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.networkResponse != null) {
                    VolleyLog.d(TAG, "Error: " + error.networkResponse.statusCode);
                    LayoutInflater layoutInflater = (LayoutInflater) SignUpActivity.this.getSystemService(LAYOUT_INFLATER_SERVICE);
                    popupLayout = layoutInflater.inflate(R.layout.popup_error, null);
                    popupWindow = new PopupWindow(popupLayout, 800, 320, true);
                    String errorText = null;
                    try {
                        errorText = new String(error.networkResponse.data, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    if (errorText != null && errorText.contains("Email")) {
                        TextView textView = popupWindow.getContentView().findViewById(R.id.textView);
                        textView.setText("این ایمیل قبلا ثبت شده است.\n لطفا از یک ایمیل دیگر جهت ثبت نام استفاده کنید");
                    }
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
                } else {
                    back_dim_layout = findViewById(R.id.back_dim_layout);
                    LayoutInflater layoutInflater = (LayoutInflater) SignUpActivity.this.getSystemService(LAYOUT_INFLATER_SERVICE);
                    popupLayout = layoutInflater.inflate(R.layout.popup_error, null);
                    popupWindow = new PopupWindow(popupLayout, 800, 320, true);

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
                }
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                //headers.put("token", "xxxxxxxxxxxxxxx");
                return headers;
            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjReq, "post");
    }
}