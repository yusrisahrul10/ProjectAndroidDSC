package com.dscunikom.android.sma14bandung.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dscunikom.android.sma14bandung.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AwardsFragment extends Fragment {


    public AwardsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_awards, container, false);
    }

}
