package com.haami.haami;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
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
import com.haami.haami.app.AppController;
import com.haami.haami.models.responses.BookResponse;
import com.haami.haami.models.responses.BookSectionResponse;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import static android.content.Context.MODE_PRIVATE;
import static com.haami.haami.Constants.getServerUrl;

public class SingleMagazineFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "";
    private Long bookSectionId;
    private Long bookId;
    private String bookName;
    private SeekBar seekBar;
    private ImageButton play_pause_button;
    private MediaPlayer player;
    private Boolean isPlaying = false;
    private Handler mHandler = new Handler();
    private String audio_file_url;
    private WebView pdfView;

    public SingleMagazineFragment() {

    }

    public static SingleMagazineFragment newInstance() {
        SingleMagazineFragment fragment = new SingleMagazineFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("lastLibraryFragment", 2);
        editor.apply();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_single_magazine, container, false);

        Bundle bundle = this.getArguments();
        bookId = bundle.getLong("bookId");
        bookName = bundle.getString("bookName");
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final ImageButton return_button = getView().findViewById(R.id.return_button);
        seekBar = getView().findViewById(R.id.seekBar);
        final TextView title_textview = getView().findViewById(R.id.title_textview);
        pdfView = getView().findViewById(R.id.pdfView);
        final NetworkImageView section_image = getView().findViewById(R.id.section_image);
        play_pause_button = getView().findViewById(R.id.play_pause_button);
        final TextView section_name_text = getView().findViewById(R.id.section_name_text);
        final ScrollView scrollView = getView().findViewById(R.id.scrollView);

        return_button.setOnClickListener(this);
        play_pause_button.setOnClickListener(this);

        title_textview.setText(bookName);

        //String url = getServerUrl() + "api/bookSection/" + bookSectionId;
        String url = getServerUrl() + "api/book/" + bookId;

        final ConstraintLayout back_dim_layout = getView().getRootView().findViewById(R.id.back_dim_layout);
        back_dim_layout.setVisibility(View.VISIBLE);

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        Gson gson = new Gson();
                        final BookResponse apiResponse = gson.fromJson(response.toString(), BookResponse.class);
                        if (apiResponse.getSectionCount() > 0) {
                            final BookSectionResponse sectionResponse = apiResponse.getSections().get(0);
                            //showPdfFile(sectionResponse.getAudioUrl());
                            pdfView.getSettings().setJavaScriptEnabled(true);
                            pdfView.loadUrl("https://drive.google.com/viewerng/viewer?embedded=true&url=" + getServerUrl() + sectionResponse.getAudioUrl());
                            if (apiResponse.getImageUrl() == null || apiResponse.getImageUrl().isEmpty()) {
                                section_image.setVisibility(View.GONE);
                                //ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) scrollView.getLayoutParams();
                                //if(sectionResponse.getAudioUrl().isEmpty()) {
                                //params.topToBottom = section_name_text.getId();
                                //} else {
                                //    params.topToBottom = play_pause_button.getId();
                                //}
                                //scrollView.requestLayout();
                            } else {
                                section_image.setImageUrl(getServerUrl() + apiResponse.getImageUrl(), AppController.getInstance().getImageLoader());
                            }
                            section_name_text.setText(sectionResponse.getTitle());
                            //if (!sectionResponse.getAudioUrl().isEmpty()) {
                            //    seekBar.setVisibility(View.VISIBLE);
                            //    play_pause_button.setVisibility(View.VISIBLE);
                            //}
                            //audio_file_url = sectionResponse.getAudioUrl();
                        }
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

    private void showPdfFile(final String fileUrl) {
        new AsyncTask<Void, Void, Void>() {
            @SuppressLint("WrongThread")
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    InputStream input = new URL(getServerUrl() + fileUrl).openStream();
                    //pdfView.fromStream(input).load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
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