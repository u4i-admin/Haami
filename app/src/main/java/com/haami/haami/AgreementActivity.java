package com.haami.haami;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.haami.haami.R;
import com.haami.haami.app.AppController;
import com.haami.haami.models.responses.SettingResponse;

import org.json.JSONObject;

public class AgreementActivity extends AppCompatActivity {

    TextView privacy_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agreement);

        ImageButton close_button = findViewById(R.id.close_button);
        privacy_text = findViewById(R.id.privacy_text);

        close_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getPrivacy();
    }

    private void getPrivacy() {
        String url = String.format("%sapi/setting/byKey/Privacy", Constants.getServerUrl());
        final ConstraintLayout back_dim_layout = findViewById(R.id.back_dim_layout);
        back_dim_layout.setVisibility(View.VISIBLE);

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("TAG", response.toString());
                        Gson gson = new Gson();
                        SettingResponse apiResponse = gson.fromJson(response.toString(), SettingResponse.class);
                        privacy_text.setText(apiResponse.getDescription());
                        back_dim_layout.setVisibility(View.GONE);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
                back_dim_layout.setVisibility(View.GONE);
            }
        });
        AppController.getInstance().addToRequestQueue(jsonObjReq, "getPrivacy");
    }
}