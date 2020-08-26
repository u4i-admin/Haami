package com.haami.haami;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.android.volley.toolbox.NetworkImageView;
import com.google.gson.Gson;

import com.haami.haami.R;

import com.haami.haami.app.AppController;
import com.haami.haami.models.responses.ArticleResponse;
import org.json.JSONObject;

import static com.haami.haami.Constants.getServerUrl;

public class PlaceSingleFragment extends Fragment {

    Long articleId;

    public PlaceSingleFragment() {

    }

    public static PlaceSingleFragment newInstance() {
        PlaceSingleFragment fragment = new PlaceSingleFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_place_single, container, false);

        Bundle bundle = this.getArguments();
        articleId = bundle.getLong("articleId");
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageButton return_button = getView().findViewById(R.id.return_button);
        final NetworkImageView image = getView().findViewById(R.id.image);
        final TextView place_name_textview = getView().findViewById(R.id.place_name_textview);
        final TextView category_textview = getView().findViewById(R.id.category_textview);
        final TextView address_textview = getView().findViewById(R.id.address_textview);
        final TextView description_textview = getView().findViewById(R.id.description_textview);

        return_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        String url = String.format("%sapi/article/%s", getServerUrl(), articleId);

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
                        image.setImageUrl(getServerUrl() + apiResponse.getPicUrl(), AppController.getInstance().getImageLoader());
                        place_name_textview.setText(apiResponse.getTitle());
                        category_textview.setText(apiResponse.getCategoryName());
                        address_textview.setText(apiResponse.getAddress());
                        description_textview.setText(apiResponse.getBody());
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