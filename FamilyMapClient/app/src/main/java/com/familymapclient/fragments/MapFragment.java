package com.familymapclient.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.familymapclient.activities.MainActivity;
import com.familymapclient.R;

public class MapFragment extends Fragment {
    private MainActivity main_activity;
    private static final String TAG = "LOGIN_FRAGMENT";
    public static final String ARG_TITLE = "title";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.map_fragment, container, false);



        return view;
    }
}
