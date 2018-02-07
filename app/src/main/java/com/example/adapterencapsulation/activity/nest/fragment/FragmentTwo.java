package com.example.adapterencapsulation.activity.nest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adapterencapsulation.R;

/**
 * Created by yuanpk on 2018/1/16.
 */

public class FragmentTwo extends Fragment {
    private static final String TAG = "FragmentTwo";

    public static FragmentTwo getFragmentTwo() {
        FragmentTwo fragmentTwo = new FragmentTwo();
       /* Bundle bundle=new Bundle();
        bundle.putString("userId","");
        fragmentOne.setArguments(bundle);*/
        return fragmentTwo;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragmentone, null);
        return view;
    }
}
