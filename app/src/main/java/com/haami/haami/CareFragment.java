package com.haami.haami;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.haami.haami.R;

public class CareFragment extends Fragment implements View.OnClickListener {

    ImageButton play_button;
    SeekBar seekBar;
    MediaPlayer player;
    Boolean isPlaying = false;
    ImageView image;
    Button beginner;
    Button advance;

    private Handler mHandler = new Handler();

    public CareFragment() {

    }

    public static CareFragment newInstance() {
        CareFragment fragment = new CareFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_care, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        seekBar = getView().findViewById(R.id.seekBar);
        play_button = getView().findViewById(R.id.play_button);
        beginner = getView().findViewById(R.id.beginner_care_button);
        advance = getView().findViewById(R.id.advance_care_button);
        image = getView().findViewById(R.id.player_background_view);

        play_button.setOnClickListener(this);
        beginner.setOnClickListener(this);
        advance.setOnClickListener(this);

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
            case R.id.beginner_care_button:
                image.setImageDrawable(getResources().getDrawable(R.drawable.mindfulness_meditation_begginer));
                beginner.setBackground(getResources().getDrawable(R.drawable.breathing_active_button_background));
                advance.setBackground(getResources().getDrawable(R.drawable.breathing_deactive_button_background));
                beginner.setTextColor(getResources().getColor(R.color.colorRelaxationActiveTitle));
                advance.setTextColor(getResources().getColor(R.color.colorRelaxationTitleText));
                break;
            case R.id.advance_care_button:
                image.setImageDrawable(getResources().getDrawable(R.drawable.mindfulness_meditation_advance));
                beginner.setBackground(getResources().getDrawable(R.drawable.breathing_deactive_button_background));
                advance.setBackground(getResources().getDrawable(R.drawable.breathing_active_button_background));
                beginner.setTextColor(getResources().getColor(R.color.colorRelaxationTitleText));
                advance.setTextColor(getResources().getColor(R.color.colorRelaxationActiveTitle));
                break;
            case R.id.play_button:
                if(!isPlaying) {
                    if (player == null) {
                        player = MediaPlayer.create(getActivity(), R.raw.care_music);
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
                    play_button.setImageDrawable(getResources().getDrawable(R.drawable.relaxation_pause_icon));
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