package com.haami.haami;

import android.app.ProgressDialog;
import android.os.Bundle;

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
import com.haami.haami.adapters.ArticleAdapter;
import com.haami.haami.models.apiResponse.BookApiResponse;
import com.haami.haami.models.responses.BookResponse;
import com.haami.haami.utils.RecyclerTouchListener;

import com.haami.haami.R;

import com.haami.haami.app.AppController;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ArticleFragment extends Fragment {

    private static final String TAG = "";
    List<BookResponse> articleList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ArticleAdapter adapter;

    public ArticleFragment() {

    }

    public static ArticleFragment newInstance(String param1, String param2) {
        ArticleFragment fragment = new ArticleFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_article, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        recyclerView = getView().findViewById(R.id.article_recycler);
        adapter = new ArticleAdapter(articleList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity().getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                BookResponse article = articleList.get(position);
                Toast.makeText(getActivity().getApplicationContext(), article.getBookName() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {
                BookResponse article = articleList.get(position);
                Toast.makeText(getActivity().getApplicationContext(), article.getBookName() + " is long pressed", Toast.LENGTH_SHORT).show();
            }
        }));

        String url = Constants.getServerUrl() + "api/book/byType/3/0/10";

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
                        BookApiResponse apiResponse = gson.fromJson(response.toString(), BookApiResponse.class);
                        List<BookResponse> data = apiResponse.getData();
                        articleList.addAll(data);
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
        AppController.getInstance().addToRequestQueue(jsonObjReq, "getArticles");
    }
}