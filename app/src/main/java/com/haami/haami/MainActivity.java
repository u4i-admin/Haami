package com.haami.haami;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button[] btn = new Button[4];
    private int[] btn_id = {R.id.home_button, R.id.library_button, R.id.relaxation_button, R.id.places_button};
    private static final String TAG = "MainActivity";
    private ConstraintLayout back_dim_layout;
    private PopupWindow popupWindow;
    private View popupLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        back_dim_layout = findViewById(R.id.back_dim_layout);
        ImageView loading_image = findViewById(R.id.loading_image);
        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        popupLayout = layoutInflater.inflate(R.layout.popup_exit, null);
        popupWindow = new PopupWindow(popupLayout, 800, 700, true);

        Glide.with(this).load(R.drawable.haami_loading).into(loading_image);

        for(int i = 0; i < btn.length; i++){
            btn[i] = findViewById(btn_id[i]);
            btn[i].setOnClickListener(this);
        }

        SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
        boolean isProUser = sharedPreferences.getBoolean("isProUser", false);

        if (isLoggedIn && isProUser) {
            FirebaseInstanceId.getInstance().getInstanceId()
                    .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                        @Override
                        public void onComplete(@NonNull Task<InstanceIdResult> task) {
                            if (!task.isSuccessful()) {
                                Log.w(TAG, "getInstanceId failed", task.getException());
                                return;
                            }
                            String token = task.getResult().getToken();
                            String msg = getString(R.string.msg_token_fmt, token);
                            Log.d(TAG, msg);
                            //Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                            try {
                                sendTokenToServer(token);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
        }

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_placeholder, new HomeFragment());
        // ft.add(R.id.frame_placeholder, new FooFragment());
        ft.commit();
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        switch (v.getId()) {
            case R.id.home_button :
                ft.replace(R.id.frame_placeholder, new HomeFragment());
                ft.commit();
                setUnFocus();
                btn[0].setTextColor(getResources().getColor(R.color.colorActiveText));
                btn[0].setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.home_icon_active, 0, 0);
                break;
            case R.id.library_button :
                if (checkNetworkExists()) {
                    ft.replace(R.id.frame_placeholder, new LibraryFragment());
                    ft.commit();
                    setUnFocus();
                    btn[1].setTextColor(getResources().getColor(R.color.colorActiveText));
                    btn[1].setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.library_icon_active, 0, 0);
                }
                break;
            case R.id.relaxation_button :
                ft.replace(R.id.frame_placeholder, new RelaxationFragment());
                ft.commit();
                setUnFocus();
                btn[2].setTextColor(getResources().getColor(R.color.colorActiveText));
                btn[2].setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.relaxation_icon_active, 0, 0);
                break;
            case R.id.places_button :
                if (checkNetworkExists()) {
                    ft.replace(R.id.frame_placeholder, new PlacesFragment());
                    ft.commit();
                    setUnFocus();
                    btn[3].setTextColor(getResources().getColor(R.color.colorActiveText));
                    btn[3].setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.places_icon_active, 0, 0);
                }
                break;
        }
    }

    private void setUnFocus(){
        btn[0].setTextColor(getResources().getColor(R.color.colorNotActiveText));
        btn[1].setTextColor(getResources().getColor(R.color.colorNotActiveText));
        btn[2].setTextColor(getResources().getColor(R.color.colorNotActiveText));
        btn[3].setTextColor(getResources().getColor(R.color.colorNotActiveText));
        btn[0].setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.home_icon_deactive, 0, 0);
        btn[1].setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.library_icon_deactive, 0, 0);
        btn[2].setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.relaxation_icon_deactive, 0, 0);
        btn[3].setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.places_icon_deactive, 0, 0);
    }

    public void goToHome(View view) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
        ft.replace(R.id.frame_placeholder, new TodayFragment());
        ft.addToBackStack(null);
        ft.commit();
        //Intent i = new Intent(this, HomeActivity.class);
        //startActivity(i);
    }

    public void replaceFragments(Class fragmentClass, @Nullable Bundle bundle) {
        Fragment fragment = null;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
            if (bundle != null)
                fragment.setArguments(bundle);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Insert the fragment by replacing any existing fragment
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_placeholder, fragment);
        ft.addToBackStack(null);
        ft.commit();
        if (fragmentClass == HomeFragment.class) {
            setUnFocus();
            btn[0].setTextColor(getResources().getColor(R.color.colorActiveText));
            btn[0].setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.home_icon_active, 0, 0);
        }
    }

    public void sendTokenToServer(String token) throws JSONException {
        try {
            String url = getServerUrl() + "api/user/setAppId";

            final JSONObject jsonBody = new JSONObject("{\"appId\":\"" + token + "\"}");

            JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                    url, jsonBody,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d(TAG, response.toString());

                            Gson gson = new Gson();
                            UserResponse apiResponse = gson.fromJson(response.toString(), UserResponse.class);
                            SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean("isAppIdRegistered", true);
                            editor.commit();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    try {
                        VolleyLog.d(TAG, "Error: " + error.networkResponse.statusCode);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Content-Type", "application/json");
                    SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", MODE_PRIVATE);
                    headers.put("token", sharedPreferences.getString("token", null));
                    return headers;
                }
            };
            AppController.getInstance().addToRequestQueue(jsonObjReq, "post");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            //super.onBackPressed();
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
            popupWindow.getContentView().findViewById(R.id.cancel_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                }
            });
            popupWindow.getContentView().findViewById(R.id.exit_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    public Boolean checkNetworkExists() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = null;
        if (connectivityManager != null) {
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        }
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            return true;
        } else {
            showNoConnectionPopup();
            return false;
        }
    }

    private void showNoConnectionPopup() {
        back_dim_layout = findViewById(R.id.back_dim_layout);
        LayoutInflater layoutInflater = (LayoutInflater) MainActivity.this.getSystemService(LAYOUT_INFLATER_SERVICE);
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
                back_dim_layout.setVisibility(View.GONE);
                popupWindow.dismiss();
            }
        });
    }
}
