package com.example.speakerdemo.fragment.me;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.speakerdemo.R;
import com.example.speakerdemo.utils.FullScreenUtil;
import com.gyf.immersionbar.ImmersionBar;

public class MeFragment extends Fragment {


    private View inflate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment\

        inflate = inflater.inflate(R.layout.fragment_me, container, false);

        initView();
        return inflate;
    }

    private void initView() {

    }
    
}