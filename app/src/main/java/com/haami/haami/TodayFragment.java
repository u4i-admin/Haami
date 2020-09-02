package com.haami.haami;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class TodayFragment extends Fragment implements View.OnClickListener {

    private Integer selectedMonth = 0;
    private Integer selectedDay = 0;

    public TodayFragment() {

    }

    public static TodayFragment newInstance(String param1, String param2) {
        TodayFragment fragment = new TodayFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_today, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ImageButton close_button = getView().findViewById(R.id.close_button);
        ImageButton next_day_button = getView().findViewById(R.id.next_day_button);
        ImageButton previous_day_button = getView().findViewById(R.id.previous_day_button);

        close_button.setOnClickListener(this);
        next_day_button.setOnClickListener(this);
        previous_day_button.setOnClickListener(this);

        LoadData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.close_button:
                getActivity().onBackPressed();
                break;
            case R.id.next_day_button:
                NextDay();
                break;
            case R.id.previous_day_button:
                PreviousDay();
                break;
        }
    }

    private void LoadData() {
        final TextView month_text = getView().findViewById(R.id.month_text);
        final TextView day_text = getView().findViewById(R.id.day_text);
        final TextView body_text = getView().findViewById(R.id.body_text);

        String url = String.format("%sapi/article/justForToday/%d/%d", getServerUrl(), selectedMonth, selectedDay);

        final ConstraintLayout back_dim_layout = getView().getRootView().findViewById(R.id.back_dim_layout);
        back_dim_layout.setVisibility(View.VISIBLE);

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("TAG", response.toString());
                        Gson gson = new Gson();
                        ArticleResponse apiResponse = gson.fromJson(response.toString(), ArticleResponse.class);
                        String[] currentDate = apiResponse.getArticleDatePersian().split("/");
                        month_text.setText(GetMonthName(currentDate[1]));
                        day_text.setText(currentDate[2]);
                        body_text.setText(String.format("%s\n\n%s", apiResponse.getTitle(), apiResponse.getBody()));
                        selectedDay = Integer.parseInt(currentDate[2]);
                        selectedMonth = Integer.parseInt(currentDate[1]);
                        back_dim_layout.setVisibility(View.GONE);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
                back_dim_layout.setVisibility(View.GONE);
            }
        });
        AppController.getInstance().addToRequestQueue(jsonObjReq, "getJustForToday");
    }

    public String GetMonthName(String month) {
        switch (month) {
            case "1":
                return "فروردین";
            case "2":
                return "اردیبهشت";
            case "3":
                return "خرداد";
            case "4":
                return "تیر";
            case "5":
                return "مرداد";
            case "6":
                return "شهریور";
            case "7":
                return "مهر";
            case "8":
                return "آبان";
            case "9":
                return "آذر";
            case "10":
                return "دی";
            case "11":
                return "بهمن";
            case "12":
                return "اسفند";
            default:
                return "";
        }
    }

    public void NextDay() {
        if(selectedMonth < 7) {
            if (selectedDay < 31)
                selectedDay++;
            else {
                selectedDay = 1;
                selectedMonth++;
            }
        } else {
            if (selectedDay < 30)
                selectedDay++;
            else {
                selectedDay = 1;
                if (selectedMonth == 12)
                    selectedMonth = 1;
                else
                    selectedMonth++;
            }
        }

        LoadData();
    }

    public void PreviousDay() {
        if (selectedMonth > 7) {
            if (selectedDay > 1)
                selectedDay--;
            else {
                selectedDay = 30;
                selectedMonth--;
            }
        } else {
            if (selectedDay > 1)
                selectedDay--;
            else if (selectedMonth == 1) {
                selectedMonth = 12;
                selectedDay = 30;
            } else {
                selectedMonth--;
                selectedDay = 31;
            }
        }

        LoadData();
    }
}