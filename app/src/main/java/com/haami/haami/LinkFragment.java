package com.haami.haami;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import com.haami.haami.R;

import com.haami.haami.adapters.LinkAdapter;
import com.haami.haami.app.AppController;
import com.haami.haami.models.apiResponse.ArticleApiResponse;
import com.haami.haami.models.responses.ArticleResponse;
import com.haami.haami.utils.RecyclerTouchListener;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LinkFragment extends Fragment {

    private static final String TAG = "";
    List<ArticleResponse> linkList = new ArrayList<>();
    private RecyclerView recyclerView;
    private LinkAdapter adapter;
    private int totalCount;
    private int loadedCount = 0;

    public LinkFragment() {

    }

    public static LinkFragment newInstance() {
        LinkFragment fragment = new LinkFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_link, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = getView().findViewById(R.id.link_recycler);
        adapter = new LinkAdapter(linkList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                //super.onScrolled(recyclerView, dx, dy);

                if (! recyclerView.canScrollVertically(1) && loadedCount < totalCount){ //1 for down
                    loadMore();
                }
            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity().getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                ArticleResponse link = linkList.get(position);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link.getBody()));
                startActivity(browserIntent);
                //Toast.makeText(getActivity().getApplicationContext(), link.getTitle() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {
                //ArticleResponse link = linkList.get(position);
                //Toast.makeText(getActivity().getApplicationContext(), link.getTitle() + " is long pressed", Toast.LENGTH_SHORT).show();
            }
        }));

        loadMore();
    }

    private void loadMore() {
        String url = "https://haamiapp.com/api/article/byType/5/" + loadedCount + "/10";

        final ConstraintLayout back_dim_layout = getView().getRootView().findViewById(R.id.back_dim_layout);
        back_dim_layout.setVisibility(View.VISIBLE);

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        Gson gson = new Gson();
                        ArticleApiResponse apiResponse = gson.fromJson(response.toString(), ArticleApiResponse.class);
                        List<ArticleResponse> data = apiResponse.getData();
                        totalCount = apiResponse.getCount();
                        loadedCount += data.size();
                        linkList.addAll(data);
                        adapter.notifyDataSetChanged();
                        back_dim_layout.setVisibility(View.GONE);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                back_dim_layout.setVisibility(View.GONE);
            }
        });
        AppController.getInstance().addToRequestQueue(jsonObjReq, "getBooks");
    }
}