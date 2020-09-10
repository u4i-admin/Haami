package com.haami.haami;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.haami.haami.R;

public class BreathingFragment extends Fragment implements View.OnClickListener {

    Button advance_breathing_button;
    Button beginner_breathing_button;
    ImageView imageView;

    public BreathingFragment() {

    }

    public static BreathingFragment newInstance() {
        BreathingFragment fragment = new BreathingFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_breathing, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        advance_breathing_button = getView().findViewById(R.id.advance_breathing_button);
        beginner_breathing_button = getView().findViewById(R.id.beginner_breathing_button);
        imageView = getView().findViewById(R.id.imageView);

        Glide.with(this).load(R.drawable.breathing_a).into(imageView);

        advance_breathing_button.setOnClickListener(this);
        beginner_breathing_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.advance_breathing_button:
                advance_breathing_button.setBackground(getResources().getDrawable(R.drawable.breathing_active_button_background));
                beginner_breathing_button.setBackground(getResources().getDrawable(R.drawable.breathing_deactive_button_background));

                advance_breathing_button.setTextColor(getResources().getColor(R.color.colorBackground));
                beginner_breathing_button.setTextColor(getResources().getColor(R.color.colorRelaxationTitleText));

                Glide.with(this).load(R.drawable.breathing_b).into(imageView);
                break;
            case R.id.beginner_breathing_button:
                beginner_breathing_button.setBackground(getResources().getDrawable(R.drawable.breathing_active_button_background));
                advance_breathing_button.setBackground(getResources().getDrawable(R.drawable.breathing_deactive_button_background));

                beginner_breathing_button.setTextColor(getResources().getColor(R.color.colorBackground));
                advance_breathing_button.setTextColor(getResources().getColor(R.color.colorRelaxationTitleText));

                Glide.with(this).load(R.drawable.breathing_a).into(imageView);
                break;
        }
    }
}