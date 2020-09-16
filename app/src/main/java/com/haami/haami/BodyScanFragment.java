package com.haami.haami;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;

import com.haami.haami.R;

import java.io.IOException;

import static com.haami.haami.Constants.getServerUrl;

public class BodyScanFragment extends Fragment implements View.OnClickListener {

    ImageButton play_button;
    SeekBar seekBar;
    MediaPlayer player;
    Boolean isPlaying = false;

    private Handler mHandler = new Handler();

    public BodyScanFragment() {

    }

    public static BodyScanFragment newInstance() {
        BodyScanFragment fragment = new BodyScanFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_body_scan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        play_button = getView().findViewById(R.id.play_button);
        seekBar = getView().findViewById(R.id.seekBar);
        play_button.setOnClickListener(this);

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
            case R.id.play_button:
                if(!isPlaying) {
                    if (player == null) {
                        final ConstraintLayout back_dim_layout = getView().getRootView().findViewById(R.id.back_dim_layout);
                        back_dim_layout.setVisibility(View.VISIBLE);

                        player = new MediaPlayer();
                        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        try {
                            player.setDataSource(getServerUrl() + "attachment/audio/BodyScan.mp3");
                            player.prepareAsync();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mp) {
                                seekBar.setMax(player.getDuration() / 1000);
                                player.start();
                                isPlaying = true;
                                play_button.setImageDrawable(getResources().getDrawable(R.drawable.relaxation_pause_icon));
                                back_dim_layout.setVisibility(View.GONE);
                            }
                        });
                        player.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                            @Override
                            public boolean onError(MediaPlayer mp, int what, int extra) {
                                back_dim_layout.setVisibility(View.GONE);
                                return false;
                            }
                        });
                        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                StopPlayer();
                            }
                        });
                    } else {
                        player.start();
                        isPlaying = true;
                        play_button.setImageDrawable(getResources().getDrawable(R.drawable.relaxation_pause_icon));
                    }
                } else {
                    if (player != null)
                        player.pause();

                    isPlaying = false;
                    play_button.setImageDrawable(getResources().getDrawable(R.drawable.relaxation_play_icon));
                }
                break;
        }
    }

    private void StopPlayer() {
        if (player != null) {
            player.release();
            player = null;
            isPlaying = false;
            play_button.setImageDrawable(getResources().getDrawable(R.drawable.relaxation_play_icon));
        }
    }
}