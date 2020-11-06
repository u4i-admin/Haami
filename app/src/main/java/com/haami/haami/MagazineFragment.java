package com.haami.haami;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
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
import com.google.gson.GsonBuilder;
import com.haami.haami.adapters.MagazineAdapter;
import com.haami.haami.app.AppController;
import com.haami.haami.models.apiResponse.BookApiResponse;
import com.haami.haami.models.responses.BookResponse;
import com.haami.haami.utils.RecyclerTouchListener;

import com.haami.haami.R;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MagazineFragment extends Fragment {
    private static final String TAG = "";
    List<BookResponse> magazineList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MagazineAdapter adapter;
    private int totalCount;
    private int loadedCount = 0;

    public MagazineFragment() {

    }

    public static MagazineFragment newInstance(String param1, String param2) {
        MagazineFragment fragment = new MagazineFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_magazine, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        recyclerView = getView().findViewById(R.id.magazine_recycler);
        adapter = new MagazineAdapter(magazineList);
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
                BookResponse article = magazineList.get(position);
                Bundle bundle = new Bundle();
                bundle.putLong("bookId", article.getBookId());
                bundle.putString("bookName", article.getBookName());
                bundle.putString("bookDescription", article.getDescription());
                bundle.putString("imageUrl", article.getImageUrl());
                ((MainActivity) getActivity()).replaceFragments(SingleMagazineFragment.class, bundle);
                //Toast.makeText(getActivity().getApplicationContext(), article.getBookName() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {
                //BookResponse article = magazineList.get(position);
                //Toast.makeText(getActivity().getApplicationContext(), article.getBookName() + " is long pressed", Toast.LENGTH_SHORT).show();
            }
        }));

        loadMore();
    }

    private void loadMore() {
        String url = "https://haamiapp.com/api/book/byType/2/" + loadedCount + "/10";

        final ConstraintLayout back_dim_layout = getView().getRootView().findViewById(R.id.back_dim_layout);
        back_dim_layout.setVisibility(View.VISIBLE);

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
                        BookApiResponse apiResponse = gson.fromJson(response.toString(), BookApiResponse.class);
                        List<BookResponse> data = apiResponse.getData();
                        totalCount = apiResponse.getCount();
                        loadedCount += data.size();
                        magazineList.addAll(data);
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
        AppController.getInstance().addToRequestQueue(jsonObjReq, "getMagazines");
    }
}