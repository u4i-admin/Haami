package com.haami.haami;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.haami.haami.models.responses.SettingResponse;

import com.haami.haami.R;

import com.haami.haami.app.AppController;

import org.json.JSONObject;

public class SplashActivity extends AppCompatActivity {

    private int SPLASH_DISPLAY_LENGTH = 1000;
    private RelativeLayout back_dim_layout;
    private PopupWindow popupWindow;
    private View popupLayout;
    private String downloadLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        back_dim_layout = findViewById(R.id.back_dim_layout);
        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        popupLayout = layoutInflater.inflate(R.layout.popup_no_connection, null);
        popupWindow = new PopupWindow(popupLayout, 800, 500, true);

        startupCheck();
    }

    public void startupCheck() {
        if (!checkNetworkExists()) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    showNoConnectionPopup();
                }
            }, 100);
        } else {
            checkVersion();
        }
    }

    private void checkVersion() {
        String url = String.format("%sapi/setting/byKey/Version", Constants.getServerUrl());
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("TAG", response.toString());
                        Gson gson = new Gson();
                        SettingResponse apiResponse = gson.fromJson(response.toString(), SettingResponse.class);
                        String latestVersion = apiResponse.getValue();
                        String currentVersion = getCurrentVersion();
                        if (!latestVersion.equals(currentVersion)) {
                            downloadLink = apiResponse.getDescription();
                            showUpdatePopup();
                        } else {
                            loadMainActivity();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
                loadMainActivity();
            }
        });
        AppController.getInstance().addToRequestQueue(jsonObjReq, "getVersion");
    }

    private void loadMainActivity() {
        //SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", MODE_PRIVATE);
        //String token = sharedPreferences.getString("token", null);

        final Intent mainIntent;
        mainIntent = new Intent(SplashActivity.this, MainActivity.class);//token == null ? LoginActivity.class : MainActivity.class);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(mainIntent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    private void showNoConnectionPopup() {
        SPLASH_DISPLAY_LENGTH = 1;
        back_dim_layout = findViewById(R.id.back_dim_layout);
        LayoutInflater layoutInflater = (LayoutInflater) SplashActivity.this.getSystemService(LAYOUT_INFLATER_SERVICE);
        popupLayout = layoutInflater.inflate(R.layout.popup_no_connection, null);
        popupWindow = new PopupWindow(popupLayout, 800, 420, true);

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
                finish();
            }
        });
        popupWindow.getContentView().findViewById(R.id.retry_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                startupCheck();
            }
        });
    }

    private void showUpdatePopup() {
        back_dim_layout = findViewById(R.id.back_dim_layout);
        LayoutInflater layoutInflater = (LayoutInflater) SplashActivity.this.getSystemService(LAYOUT_INFLATER_SERVICE);
        popupLayout = layoutInflater.inflate(R.layout.popup_new_update, null);
        popupWindow = new PopupWindow(popupLayout, 800, 420, true);

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
                finish();
            }
        });
        popupWindow.getContentView().findViewById(R.id.update_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Uri uri = Uri.parse("googlechrome://navigate?url=" + downloadLink);
                    Intent i = new Intent(Intent.ACTION_VIEW, uri);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                } catch (ActivityNotFoundException e) {

                }
            }
        });
    }

    private String getCurrentVersion(){
        PackageManager pm = this.getPackageManager();
        PackageInfo pInfo = null;
        try {
            pInfo = pm.getPackageInfo(this.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e1) {
            e1.printStackTrace();
        }

        String currentVersion = pInfo.versionName;
        return currentVersion;
    }

    private Boolean checkNetworkExists() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = null;
        if (connectivityManager != null) {
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        }
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}