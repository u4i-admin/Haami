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

public class PlacesFragment extends Fragment implements View.OnClickListener {

    private Button[] btn = new Button[2];
    private int[] btn_id = {R.id.place_button, R.id.link_button};

    public PlacesFragment() {

    }

    public static PlacesFragment newInstance(String param1, String param2) {
        PlacesFragment fragment = new PlacesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_places, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ImageButton return_button = getView().findViewById(R.id.return_button);
        return_button.setOnClickListener(this);

        for(int i = 0; i < btn.length; i++){
            btn[i] = getView().findViewById(btn_id[i]);
            btn[i].setOnClickListener(this);
        }

        Fragment childFragment = new PlaceFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.places_frame_placeholder, childFragment).commit();
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        switch (v.getId()){
            case R.id.place_button :
                ft.replace(R.id.places_frame_placeholder, new PlaceFragment());
                ft.commit();
                setUnFocus();
                btn[0].setTextColor(getResources().getColor(R.color.colorPlacesActiveTitle));
                btn[0].setBackground(getResources().getDrawable(R.drawable.relaxation_active_tab_background));
                break;
            case R.id.link_button :
                ft.replace(R.id.places_frame_placeholder, new LinkFragment());
                ft.commit();
                setUnFocus();
                btn[1].setTextColor(getResources().getColor(R.color.colorPlacesActiveTitle));
                btn[1].setBackground(getResources().getDrawable(R.drawable.relaxation_active_tab_background));
                break;
            case R.id.return_button:
                ((MainActivity) getActivity()).replaceFragments(HomeFragment.class, null);
                break;
        }
    }

    private void setUnFocus(){
        btn[0].setTextColor(getResources().getColor(R.color.colorPlacesTitleText));
        btn[1].setTextColor(getResources().getColor(R.color.colorPlacesTitleText));
        btn[0].setBackground(getResources().getDrawable(R.drawable.places_deactive_tab_background));
        btn[1].setBackground(getResources().getDrawable(R.drawable.places_deactive_tab_background));
    }
}