package com.haami.haami;

import android.app.ProgressDialog;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.google.gson.Gson;
import com.haami.haami.adapters.BookSectionAdapter;
import com.haami.haami.app.AppController;
import com.haami.haami.models.apiResponse.BookSectionApiResponse;
import com.haami.haami.models.responses.BookSectionResponse;
import com.haami.haami.utils.RecyclerTouchListener;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import static com.haami.haami.Constants.getServerUrl;

public class BookSectionFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "";
    private Long bookSectionId;
    private String bookName;
    private SeekBar seekBar;
    private ImageButton play_pause_button;
    private MediaPlayer player;
    private Boolean isPlaying = false;
    private Handler mHandler = new Handler();
    private String audio_file_url;

    public BookSectionFragment() {

    }

    public static BookSectionFragment newInstance() {
        BookSectionFragment fragment = new BookSectionFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_book_section, container, false);

        Bundle bundle = this.getArguments();
        bookSectionId = bundle.getLong("bookSectionId");
        bookName = bundle.getString("bookName");
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final ImageButton return_button = getView().findViewById(R.id.return_button);
        seekBar = getView().findViewById(R.id.seekBar);
        final TextView title_textview = getView().findViewById(R.id.title_textview);
        final TextView body_text = getView().findViewById(R.id.body_text);
        final NetworkImageView section_image = getView().findViewById(R.id.section_image);
        play_pause_button = getView().findViewById(R.id.play_pause_button);
        final TextView section_name_text = getView().findViewById(R.id.section_name_text);
        final ScrollView scrollView = getView().findViewById(R.id.scrollView);

        return_button.setOnClickListener(this);
        play_pause_button.setOnClickListener(this);

        title_textview.setText(bookName);

        String url = getServerUrl() + "api/bookSection/" + bookSectionId;

        final ConstraintLayout back_dim_layout = getView().getRootView().findViewById(R.id.back_dim_layout);
        back_dim_layout.setVisibility(View.VISIBLE);

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        Gson gson = new Gson();
                        BookSectionResponse apiResponse = gson.fromJson(response.toString(), BookSectionResponse.class);
                        body_text.setText(apiResponse.getBody());
                        if(apiResponse.getImageUrl().isEmpty()) {
                            section_image.setVisibility(View.GONE);
                            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) scrollView.getLayoutParams();
                            if(apiResponse.getAudioUrl().isEmpty()) {
                                params.topToBottom = section_name_text.getId();
                            } else {
                                params.topToBottom = play_pause_button.getId();
                            }
                            scrollView.requestLayout();
                        } else {
                            section_image.setImageUrl(getServerUrl() + apiResponse.getImageUrl(), AppController.getInstance().getImageLoader());
                        }
                        section_name_text.setText(apiResponse.getTitle());
                        if (!apiResponse.getAudioUrl().isEmpty()) {
                            seekBar.setVisibility(View.VISIBLE);
                            play_pause_button.setVisibility(View.VISIBLE);
                        }
                        audio_file_url = apiResponse.getAudioUrl();
                        back_dim_layout.setVisibility(View.GONE);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                back_dim_layout.setVisibility(View.GONE);
            }
        });
        AppController.getInstance().addToRequestQueue(jsonObjReq, "getBookSection");

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(player != null){
                    int mCurrentPosition = player.getCurrentPosition() / 1000;
                    seekBar.setProgress(mCurrentPosition);
                }
                mHandler.postDelayed(this, 1000);
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(player != null && fromUser) {
                    player.seekTo(progress * 1000);
                }
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        StopPlayer();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.return_button:
                getActivity().onBackPressed();
                break;
            case R.id.play_pause_button:
                if(!isPlaying) {
                    if (player == null) {
                        player = new MediaPlayer();
                        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        try {
                            player.setDataSource(getServerUrl() + audio_file_url);// = MediaPlayer.create(getActivity(), R.raw.care_music);
                            player.prepare();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        seekBar.setMax(player.getDuration() / 1000);
                        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                StopPlayer();
                            }
                        });
                    }
                    player.start();
                    isPlaying = true;
                    play_pause_button.setImageDrawable(getResources().getDrawable(R.drawable.relaxation_pause_icon));
                } else {
                    if (player != null)
                        player.pause();

                    isPlaying = false;
                    play_pause_button.setImageDrawable(getResources().getDrawable(R.drawable.play_audio_small_icon));
                }
                break;
        }
    }

    private void StopPlayer() {
        if (player != null) {
            player.release();
            player = null;
            isPlaying = false;
            play_pause_button.setImageDrawable(getResources().getDrawable(R.drawable.play_audio_small_icon));
        }
    }
}