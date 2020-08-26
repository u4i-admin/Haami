package com.haami.haami;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.haami.haami.models.responses.ArticleResponse;
import org.json.JSONObject;

import static com.haami.haami.Constants.getServerUrl;

public class ReadFragment extends Fragment implements View.OnClickListener {

    public ReadFragment() {

    }

    public static ReadFragment newInstance(String param1, String param2) {
        ReadFragment fragment = new ReadFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_read, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ImageButton return_button = getView().findViewById(R.id.return_button);
        Button feeling_better_button = getView().findViewById(R.id.feeling_better_button);
        Button next_article_button = getView().findViewById(R.id.next_article_button);

        return_button.setOnClickListener(this);
        feeling_better_button.setOnClickListener(this);
        next_article_button.setOnClickListener(this);

        LoadNewArticle();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.feeling_better_button:
            case R.id.return_button:
                getActivity().onBackPressed();
                break;
            case R.id.next_article_button:
                LoadNewArticle();
                break;
        }
    }

    public void LoadNewArticle() {
        final TextView read_text = getView().findViewById(R.id.read_text);

        String url = String.format("%sapi/article/GetRandomBadFeeling", getServerUrl());

        final ProgressDialog pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("TAG", response.toString());
                        Gson gson = new Gson();
                        ArticleResponse apiResponse = gson.fromJson(response.toString(), ArticleResponse.class);
                        read_text.setText(apiResponse.getBody());
                        pDialog.hide();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
                pDialog.hide();
            }
        });
        AppController.getInstance().addToRequestQueue(jsonObjReq, "getJustForToday");
    }
}