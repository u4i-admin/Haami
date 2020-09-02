package com.haami.haami;

import android.app.ProgressDialog;
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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.google.gson.Gson;
import com.haami.haami.adapters.BookAdapter;
import com.haami.haami.adapters.BookSectionAdapter;
import com.haami.haami.app.AppController;
import com.haami.haami.models.apiResponse.BookApiResponse;
import com.haami.haami.models.apiResponse.BookSectionApiResponse;
import com.haami.haami.models.responses.BookResponse;
import com.haami.haami.models.responses.BookSectionResponse;
import com.haami.haami.utils.RecyclerTouchListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.haami.haami.Constants.getServerUrl;

public class BookSectionsFragment extends Fragment {

    private static final String TAG = "";
    List<BookSectionResponse> bookSectionList = new ArrayList<>();
    private BookSectionAdapter adapter;
    private long bookId;
    private String bookName;
    private String bookDescription;
    private String imageUrl;
    private int totalCount;
    private int loadedCount = 0;

    public BookSectionsFragment() {
        // Required empty public constructor
    }

    public static BookSectionsFragment newInstance() {
        BookSectionsFragment fragment = new BookSectionsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_book_sections, container, false);

        Bundle bundle = this.getArguments();
        bookId = bundle.getLong("bookId");
        bookName = bundle.getString("bookName");
        bookDescription = bundle.getString("bookDescription");
        imageUrl = bundle.getString("imageUrl");
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView book_name_text = getView().findViewById(R.id.book_name_text);
        TextView book_description_text = getView().findViewById(R.id.book_description_text);
        NetworkImageView book_image = getView().findViewById(R.id.book_image);
        ImageButton return_button = getView().findViewById(R.id.return_button);
        return_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        book_name_text.setText(bookName);
        book_description_text.setText(bookDescription);
        book_image.setImageUrl(getServerUrl() + imageUrl, AppController.getInstance().getImageLoader());

        RecyclerView recyclerView = getView().findViewById(R.id.book_section_recycler);
        adapter = new BookSectionAdapter(bookSectionList);
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
                BookSectionResponse bookSection = bookSectionList.get(position);
                Bundle bundle = new Bundle();
                bundle.putLong("bookSectionId", bookSection.getBookSectionId());
                bundle.putString("bookName", bookSection.getBook().getBookName());
                ((MainActivity) getActivity()).replaceFragments(BookSectionFragment.class, bundle);
                //Toast.makeText(getActivity().getApplicationContext(), bookSection.getTitle() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        if (bookSectionList.isEmpty()) {
            loadMore();
        }
    }

    private void loadMore() {
        String url = getServerUrl() + "api/bookSection/byBook/" + bookId + "/" + loadedCount + "/10";

        final ConstraintLayout back_dim_layout = getView().getRootView().findViewById(R.id.back_dim_layout);
        back_dim_layout.setVisibility(View.VISIBLE);

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        Gson gson = new Gson();
                        BookSectionApiResponse apiResponse = gson.fromJson(response.toString(), BookSectionApiResponse.class);
                        List<BookSectionResponse> data = apiResponse.getData();
                        totalCount = apiResponse.getCount();
                        loadedCount += data.size();
                        bookSectionList.addAll(data);
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
        AppController.getInstance().addToRequestQueue(jsonObjReq, "getBookSections");
    }
}