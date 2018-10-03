package com.dscunikom.android.sma14bandung.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.dscunikom.android.sma14bandung.slide.FragmentSlider;
import com.dscunikom.android.sma14bandung.R;
import com.dscunikom.android.sma14bandung.slide.SliderIndicator;
import com.dscunikom.android.sma14bandung.slide.SliderPagerAdapter;
import com.dscunikom.android.sma14bandung.slide.SliderView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private SliderPagerAdapter mAdapter;
    private SliderIndicator mIndicator;

    private SliderView sliderView;
    private LinearLayout mLinearLayout;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        sliderView = view.findViewById(R.id.sliderView);
        mLinearLayout = view.findViewById(R.id.pagesContainer);
        setupSlider();
        return view;
    }

    private void setupSlider() {
        sliderView.setDurationScroll(800);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(FragmentSlider.newInstance("https://image.tmdb.org/t/p/w250_and_h141_bestv2/zYFQM9G5j9cRsMNMuZAX64nmUMf.jpg"));
        fragments.add(FragmentSlider.newInstance("https://image.tmdb.org/t/p/w250_and_h141_bestv2/rXBB8F6XpHAwci2dihBCcixIHrK.jpg"));
        fragments.add(FragmentSlider.newInstance("https://image.tmdb.org/t/p/w250_and_h141_bestv2/biN2sqExViEh8IYSJrXlNKjpjxx.jpg"));
        fragments.add(FragmentSlider.newInstance("https://image.tmdb.org/t/p/w250_and_h141_bestv2/o9OKe3M06QMLOzTl3l6GStYtnE9.jpg"));

        mAdapter = new SliderPagerAdapter(getFragmentManager(), fragments);
        sliderView.setAdapter(mAdapter);
        mIndicator = new SliderIndicator(this.getActivity(), mLinearLayout, sliderView, R.drawable.indicator_circle);
        mIndicator.setPageCount(fragments.size());
        mIndicator.show();
    }

}
