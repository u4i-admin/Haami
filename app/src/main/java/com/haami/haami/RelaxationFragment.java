package com.haami.haami;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.haami.haami.R;

public class RelaxationFragment extends Fragment implements View.OnClickListener {

    private Button[] btn = new Button[3];
    private int[] btn_id = {R.id.breathing_button, R.id.care_button, R.id.body_scan_button};

    public RelaxationFragment() {

    }

    public static RelaxationFragment newInstance(String param1, String param2) {
        RelaxationFragment fragment = new RelaxationFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_relaxation, container, false);
    }

    @Override
    public  void onViewCreated(View view, Bundle savedInstanceState) {
        ImageButton return_button = getView().findViewById(R.id.return_button);
        return_button.setOnClickListener(this);

        for(int i = 0; i < btn.length; i++){
            btn[i] = getView().findViewById(btn_id[i]);
            btn[i].setOnClickListener(this);
        }

        Fragment childFragment = new BreathingFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.relaxation_frame_placeholder, childFragment).commit();
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        switch (v.getId()){
            case R.id.breathing_button :
                ft.replace(R.id.relaxation_frame_placeholder, new BreathingFragment());
                ft.commit();
                setUnFocus();
                btn[0].setTextColor(getResources().getColor(R.color.colorBackground));
                btn[0].setBackground(getResources().getDrawable(R.drawable.relaxation_active_tab_background));
                break;
            case R.id.care_button :
                ft.replace(R.id.relaxation_frame_placeholder, new CareFragment());
                ft.commit();
                setUnFocus();
                btn[1].setTextColor(getResources().getColor(R.color.colorBackground));
                btn[1].setBackground(getResources().getDrawable(R.drawable.relaxation_active_tab_background));
                break;
            case R.id.body_scan_button :
                ft.replace(R.id.relaxation_frame_placeholder, new BodyScanFragment());
                ft.commit();
                setUnFocus();
                btn[2].setTextColor(getResources().getColor(R.color.colorBackground));
                btn[2].setBackground(getResources().getDrawable(R.drawable.relaxation_active_tab_background));
                break;
            case R.id.return_button:
                ((MainActivity) getActivity()).replaceFragments(HomeFragment.class, null);
                break;
        }
    }

    private void setUnFocus(){
        btn[0].setTextColor(getResources().getColor(R.color.colorRelaxationTitleText));
        btn[1].setTextColor(getResources().getColor(R.color.colorRelaxationTitleText));
        btn[2].setTextColor(getResources().getColor(R.color.colorRelaxationTitleText));
        btn[0].setBackground(getResources().getDrawable(R.drawable.relaxation_deactive_tab_background));
        btn[1].setBackground(getResources().getDrawable(R.drawable.relaxation_deactive_tab_background));
        btn[2].setBackground(getResources().getDrawable(R.drawable.relaxation_deactive_tab_background));
    }
}