package com.infosys.connected.h_carev2.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.infosys.connected.h_carev2.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment {




    public Fragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root= (ViewGroup) inflater.inflate(R.layout.fragment_fragment1, container, false);;
        return root;
    }
}