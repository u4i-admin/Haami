package com.haami.haami;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ProgressDialog;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
import com.haami.haami.models.enums.UserTypeEnum;
import com.haami.haami.models.responses.UserResponse;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.haami.haami.Constants.getServerUrl;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "LoginActivity";
    private ConstraintLayout back_dim_layout;
    private PopupWindow popupWindow;
    private View popupLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ImageView loading_image = findViewById(R.id.loading_image);
        Button enter_button = findViewById(R.id.enter_button);
        TextView enter_as_guest_button = findViewById(R.id.enter_as_guest_button);
        TextView create_account_button = findViewById(R.id.create_account_button);
        back_dim_layout = findViewById(R.id.back_dim_layout);
        Glide.with(this).load(R.drawable.haami_loading).into(loading_image);
        enter_button.setOnClickListener(this);
        enter_as_guest_button.setOnClickListener(this);
        create_account_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.enter_button:
                try {
                    InputMethodManager inputManager = (InputMethodManager) LoginActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    doLogin();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.enter_as_guest_button:
                Intent mainActivity = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(mainActivity);
                finish();
                break;
            case R.id.create_account_button:
                Intent signUpActivity = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(signUpActivity);
                break;
        }
    }

    private void doLogin() throws JSONException {
        EditText email_input = findViewById(R.id.email_input);
        EditText password_input = findViewById(R.id.password_input);

        final String email = email_input.getText().toString();
        final String password = password_input.getText().toString();

        String url = getServerUrl() + "api/user/userLogin";

        back_dim_layout.setVisibility(View.VISIBLE);

        final JSONObject jsonBody = new JSONObject("{\"username\":\"" + email + "\",\"password\":\"" + password + "\"}");

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        back_dim_layout.setVisibility(View.GONE);

                        Gson gson = new Gson();
                        UserResponse apiResponse = gson.fromJson(response.toString(), UserResponse.class);

                        SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("token", apiResponse.getToken());
                        editor.putBoolean("isLoggedIn", true);
                        Integer currentUserType = apiResponse.getUserType();
                        editor.putBoolean("isProUser", currentUserType == 2);
                        editor.putString("user_name", apiResponse.getName());
                        editor.putString("email", apiResponse.getEmail());
                        editor.commit();

                        //Intent mainActivity = new Intent(LoginActivity.this, MainActivity.class);
                        //startActivity(mainActivity);
                        finish();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.networkResponse.statusCode);
                if (error.networkResponse.statusCode == 401) {
                    LayoutInflater layoutInflater = (LayoutInflater) LoginActivity.this.getSystemService(LAYOUT_INFLATER_SERVICE);
                    popupLayout = layoutInflater.inflate(R.layout.popup_wrong_password, null);
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
                } else {
                    back_dim_layout = findViewById(R.id.back_dim_layout);
                    LayoutInflater layoutInflater = (LayoutInflater) LoginActivity.this.getSystemService(LAYOUT_INFLATER_SERVICE);
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