package com.haami.haami;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.haami.haami.R;
import com.haami.haami.app.AppController;
import com.haami.haami.models.responses.UserResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static android.content.Context.MODE_PRIVATE;
import static com.haami.haami.Constants.getServerUrl;

public class TellYourStoryFragment extends Fragment implements View.OnClickListener {

    private ConstraintLayout back_dim_layout;
    private PopupWindow popupWindow;
    private View popupLayout;
    private EditText story_text_edittext;

    public TellYourStoryFragment() {
        // Required empty public constructor
    }

    public static TellYourStoryFragment newInstance(String param1, String param2) {
        TellYourStoryFragment fragment = new TellYourStoryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tell_your_story, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageButton return_button = getView().findViewById(R.id.return_button);
        Button cancel_button = getView().findViewById(R.id.cancel_button);
        Button send_button = getView().findViewById(R.id.send_button);
        story_text_edittext = getView().findViewById(R.id.story_text_edittext);
        back_dim_layout = getView().getRootView().findViewById(R.id.back_dim_layout);

        return_button.setOnClickListener(this);
        cancel_button.setOnClickListener(this);
        send_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.return_button:
            case R.id.cancel_button:
                getActivity().onBackPressed();
                break;
            case R.id.send_button:
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("myPrefs", MODE_PRIVATE);
                final String token = sharedPreferences.getString("token", null);

                if (token == null) {
                    LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);
                    popupLayout = layoutInflater.inflate(R.layout.popup_must_create_account, null);
                    popupWindow = new PopupWindow(popupLayout, 800, 800, true);

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
                    popupWindow.getContentView().findViewById(R.id.create_account_button).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popupWindow.dismiss();
                            Intent signInActivity = new Intent(getActivity(), LoginActivity.class);
                            startActivity(signInActivity);
                        }
                    });
                } else {
                    String url = getServerUrl() + "api/article/byUser";
                    String bodyText = story_text_edittext.getText().toString();

                    back_dim_layout.setVisibility(View.VISIBLE);

                    try {
                        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                                url, new JSONObject("{\"body\":\"" + bodyText + "\",\"categoryType\":\"4\"}"),
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        Log.d("TellYourStoryActivity", response.toString());
                                        back_dim_layout.setVisibility(View.GONE);
                                        back_dim_layout = getView().getRootView().findViewById(R.id.back_dim_layout);
                                        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);
                                        popupLayout = layoutInflater.inflate(R.layout.popup_story_sent, null);
                                        popupWindow = new PopupWindow(popupLayout, 800, 400, true);

                                        back_dim_layout.setVisibility(View.VISIBLE);
                                        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                                            @Override
                                            public void onDismiss() {
                                                back_dim_layout.setVisibility(View.GONE);
                                                getActivity().onBackPressed();
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
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d("TellYourStoryActivity", "Error: " + error.networkResponse.statusCode + "\n" + error.getMessage());
                                LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);
                                popupLayout = layoutInflater.inflate(R.layout.popup_error, null);
                                popupWindow = new PopupWindow(popupLayout, 800, 450, true);

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
                        }) {
                            @Override
                            public Map<String, String> getHeaders() throws AuthFailureError {
                                HashMap<String, String> headers = new HashMap<String, String>();
                                headers.put("Content-Type", "application/json");
                                headers.put("token", token);
                                return headers;
                            }
                        };
                        AppController.getInstance().addToRequestQueue(jsonObjReq, "post");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }
}