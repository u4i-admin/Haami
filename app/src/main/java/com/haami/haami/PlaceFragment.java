package com.haami.haami;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import com.haami.haami.R;

import com.haami.haami.adapters.PlaceAdapter;
import com.haami.haami.app.AppController;
import com.haami.haami.models.apiResponse.ArticleApiResponse;
import com.haami.haami.models.responses.ArticleResponse;
import com.haami.haami.utils.RecyclerTouchListener;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PlaceFragment extends Fragment {

    private static final String TAG = "";
    List<ArticleResponse> placeList = new ArrayList<>();
    private RecyclerView recyclerView;
    private PlaceAdapter adapter;

    public PlaceFragment() {

    }

    public static PlaceFragment newInstance() {
        PlaceFragment fragment = new PlaceFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_place, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = getView().findViewById(R.id.place_recycler);
        adapter = new PlaceAdapter(placeList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity().getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                ArticleResponse place = placeList.get(position);
                Bundle bundle = new Bundle();
                bundle.putLong("articleId", place.getArticleId());
                ((MainActivity) getActivity()).replaceFragments(PlaceSingleFragment.class, bundle);
                //Toast.makeText(getActivity().getApplicationContext(), place.getTitle() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {
                //ArticleResponse place = placeList.get(position);
                //Toast.makeText(getActivity().getApplicationContext(), place.getTitle() + " is long pressed", Toast.LENGTH_SHORT).show();
            }
        }));

        String url = Constants.getServerUrl() + "api/article/byType/3/0/10";

        final ProgressDialog pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        Gson gson = new Gson();
                        ArticleApiResponse apiResponse = gson.fromJson(response.toString(), ArticleApiResponse.class);
                        List<ArticleResponse> data = apiResponse.getData();
                        placeList.addAll(data);
                        adapter.notifyDataSetChanged();
                        pDialog.hide();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                pDialog.hide();
            }
        });
        AppController.getInstance().addToRequestQueue(jsonObjReq, "getBooks");
    }
}