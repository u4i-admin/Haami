package com.haami.haami;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.haami.haami.R;

import static android.content.Context.MODE_PRIVATE;

public class LibraryFragment extends Fragment implements View.OnClickListener  {

    private Button[] btn = new Button[3];
    private int[] btn_id = {R.id.book_button, R.id.magazine_button, R.id.article_button};

    public LibraryFragment() {

    }

    public static LibraryFragment newInstance() {
        LibraryFragment fragment = new LibraryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_library, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ImageButton return_button = getView().findViewById(R.id.return_button);
        return_button.setOnClickListener(this);

        for (int i = 0; i < btn.length; i++) {
            btn[i] = getView().findViewById(btn_id[i]);
            btn[i].setOnClickListener(this);
        }

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("myPrefs", MODE_PRIVATE);
        int lastFragment = sharedPreferences.getInt("lastLibraryFragment", 0);

        Fragment childFragment;
        if (lastFragment == 3) {
            View v = getView().findViewById(R.id.article_button);
            onClick(v);
            //childFragment = new ArticleFragment();
        } else if (lastFragment == 2) {
            View v = getView().findViewById(R.id.magazine_button);
            onClick(v);
            //childFragment = new MagazineFragment();
        } else {
            childFragment = new BookFragment();
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.replace(R.id.library_frame_placeholder, childFragment).commit();
        }
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        switch (v.getId()){
            case R.id.book_button :
                ft.replace(R.id.library_frame_placeholder, new BookFragment());
                ft.commit();
                setUnFocus();
                btn[0].setTextColor(getResources().getColor(R.color.colorLibraryActiveTitle));
                btn[0].setBackground(getResources().getDrawable(R.drawable.library_active_tab_background));
                break;
            case R.id.magazine_button :
                ft.replace(R.id.library_frame_placeholder, new MagazineFragment());
                ft.commit();
                setUnFocus();
                btn[1].setTextColor(getResources().getColor(R.color.colorLibraryActiveTitle));
                btn[1].setBackground(getResources().getDrawable(R.drawable.library_active_tab_background));
                break;
            case R.id.article_button :
                ft.replace(R.id.library_frame_placeholder, new ArticleFragment());
                ft.commit();
                setUnFocus();
                btn[2].setTextColor(getResources().getColor(R.color.colorLibraryActiveTitle));
                btn[2].setBackground(getResources().getDrawable(R.drawable.library_active_tab_background));
                break;
            case R.id.return_button:
                ((MainActivity) getActivity()).replaceFragments(HomeFragment.class, null);
                break;
        }
    }

    private void setUnFocus(){
        btn[0].setTextColor(getResources().getColor(R.color.colorLibraryTitleText));
        btn[1].setTextColor(getResources().getColor(R.color.colorLibraryTitleText));
        btn[2].setTextColor(getResources().getColor(R.color.colorLibraryTitleText));
        btn[0].setBackground(getResources().getDrawable(R.drawable.library_deactive_tab_background));
        btn[1].setBackground(getResources().getDrawable(R.drawable.library_deactive_tab_background));
        btn[2].setBackground(getResources().getDrawable(R.drawable.library_deactive_tab_background));
    }
}