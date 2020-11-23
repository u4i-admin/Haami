package com.haami.haami;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
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
import com.google.gson.GsonBuilder;
import com.haami.haami.R;
import com.haami.haami.app.AppController;
import com.haami.haami.models.responses.BadFeelingResponse;
import com.haami.haami.models.responses.UserResponse;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static android.content.Context.MODE_PRIVATE;
import static com.haami.haami.Constants.getServerUrl;

public class NotFeelingGoodFragment extends Fragment implements View.OnClickListener {

    private ConstraintLayout back_dim_layout;
    private PopupWindow popupWindow;
    private View popupLayout;
    private static final String TAG = "NotFeelingGoodFragment";
    private Long badFeelingIdRequest;
    private final Integer DELAY_TIME = 5000;
    private boolean keepTryingToCheck = false;

    public NotFeelingGoodFragment() {

    }

    public static NotFeelingGoodFragment newInstance(String param1, String param2) {
        NotFeelingGoodFragment fragment = new NotFeelingGoodFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_not_feeling_good, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ImageButton close_button = getView().findViewById(R.id.close_button);
        Button read_button = getView().findViewById(R.id.read_button);
        Button tell_your_story_button = getView().findViewById(R.id.tell_your_story_button);
        Button talk_to_constultant_button = getView().findViewById(R.id.talk_to_constultant_button);
        Button play_button = getView().findViewById(R.id.play_button);

        close_button.setOnClickListener(this);
        read_button.setOnClickListener(this);
        play_button.setOnClickListener(this);
        tell_your_story_button.setOnClickListener(this);
        talk_to_constultant_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.close_button:
                getActivity().onBackPressed();
                break;
            case R.id.read_button:
                ((MainActivity) getActivity()).replaceFragments(ReadFragment.class, null);
                break;
            case R.id.tell_your_story_button:
                ((MainActivity) getActivity()).replaceFragments(TellYourStoryFragment.class, null);
                break;
            case R.id.play_button:
                ((MainActivity) getActivity()).replaceFragments(GameFragment.class, null);
                break;
            case R.id.talk_to_constultant_button:
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("myPrefs", MODE_PRIVATE);
                final String token = sharedPreferences.getString("token", null);
                if (token == null) {
                    back_dim_layout = getView().getRootView().findViewById(R.id.back_dim_layout);
                    LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);
                    popupLayout = layoutInflater.inflate(R.layout.popup_must_create_account, null);
                    popupWindow = new PopupWindow(popupLayout, 800, 800, true);

                    back_dim_layout.setVisibility(View.VISIBLE);
                    TextView textView = popupWindow.getContentView().findViewById(R.id.textView);
                    textView.setText(R.string.you_must_create_account_for_consultant);
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
                    sendTalkingRequest();
                }
                break;
        }
    }

    public void sendTalkingRequest() {
        String url = getServerUrl() + "api/badFeeling";

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());

                        Gson gson = new Gson();
                        BadFeelingResponse apiResponse = gson.fromJson(response.toString(), BadFeelingResponse.class);
                        badFeelingIdRequest = apiResponse.getBadFeelingId();
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                keepTryingToCheck = true;
                                checkForAcceptance();
                            }
                        }, DELAY_TIME);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.networkResponse.statusCode);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
                headers.put("token", sharedPreferences.getString("token", null));
                return headers;
            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjReq, "post");

        back_dim_layout = getView().getRootView().findViewById(R.id.back_dim_layout);
        LayoutInflater layoutInflater = (LayoutInflater)getActivity().getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        popupLayout = layoutInflater.from(getActivity()).inflate(R.layout.popup_talking_to_guide, null);
        popupWindow = new PopupWindow(popupLayout, 800, 800, true);

        back_dim_layout.setVisibility(View.VISIBLE);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                keepTryingToCheck = false;
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
        ImageView loading_imageview = popupWindow.getContentView().findViewById(R.id.error_image);
        Glide.with(this).load(R.drawable.haami_loading).into(loading_imageview);
    }

    public void checkForAcceptance() {
        String url = getServerUrl() + "api/badFeeling/checkForGuideAcceptance/" + badFeelingIdRequest;

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());

                        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
                        BadFeelingResponse apiResponse = gson.fromJson(response.toString(), BadFeelingResponse.class);
                        Long guideId = apiResponse.getGuideId();
                        final String guidTelegramId = apiResponse.getGuideTelegramId();
                        if (guideId == null) {
                            if (keepTryingToCheck) {
                                new Timer().schedule(new TimerTask() {
                                    @Override
                                    public void run() {
                                        checkForAcceptance();
                                    }
                                }, DELAY_TIME);
                            }
                        } else {
                            popupWindow.dismiss();
                            LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);
                            popupLayout = layoutInflater.inflate(R.layout.popup_open_telegram, null);
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
                            popupWindow.getContentView().findViewById(R.id.no_button).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    popupWindow.dismiss();
                                }
                            });
                            popupWindow.getContentView().findViewById(R.id.yes_button).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    popupWindow.dismiss();
                                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/" + guidTelegramId));
                                    startActivity(browserIntent);
                                }
                            });
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.networkResponse.statusCode);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
                headers.put("token", sharedPreferences.getString("token", null));
                return headers;
            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjReq, "post");
    }
}