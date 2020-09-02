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
import com.haami.haami.adapters.BookAdapter;
import com.haami.haami.app.AppController;
import com.haami.haami.models.apiResponse.BookApiResponse;
import com.haami.haami.models.responses.ArticleResponse;
import com.haami.haami.models.responses.BookResponse;
import com.haami.haami.utils.RecyclerTouchListener;

import com.haami.haami.R;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BookFragment extends Fragment {

    private static final String TAG = "";
    List<BookResponse> bookList = new ArrayList<>();
    private RecyclerView recyclerView;
    private BookAdapter adapter;
    private int totalCount;
    private int loadedCount = 0;

    public BookFragment() {

    }

    public static BookFragment newInstance(String param1, String param2) {
        BookFragment fragment = new BookFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_book, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        recyclerView = getView().findViewById(R.id.book_recycler);
        adapter = new BookAdapter(bookList);
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
                BookResponse book = bookList.get(position);
                Bundle bundle = new Bundle();
                bundle.putLong("bookId", book.getBookId());
                bundle.putString("bookName", book.getBookName());
                bundle.putString("bookDescription", book.getDescription());
                bundle.putString("imageUrl", book.getImageUrl());
                ((MainActivity) getActivity()).replaceFragments(BookSectionsFragment.class, bundle);
                //Toast.makeText(getActivity().getApplicationContext(), book.getBookName() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        loadMore();
    }

    private void loadMore() {
        String url = Constants.getServerUrl() + "api/book/byType/1/" + loadedCount + "/10";

        final ConstraintLayout back_dim_layout = getView().getRootView().findViewById(R.id.back_dim_layout);
        back_dim_layout.setVisibility(View.VISIBLE);

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        Gson gson = new Gson();
                        BookApiResponse apiResponse = gson.fromJson(response.toString(), BookApiResponse.class);
                        List<BookResponse> data = apiResponse.getData();
                        totalCount = apiResponse.getCount();
                        loadedCount += data.size();
                        bookList.addAll(data);
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