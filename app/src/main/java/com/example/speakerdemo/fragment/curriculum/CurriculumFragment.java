package com.example.speakerdemo.fragment.curriculum;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.speakerdemo.R;


public class CurriculumFragment extends Fragment {

    private View inflate;
    private Toolbar mCurriculmToolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_curriculum, container, false);
        initView();
        return inflate;
    }
    private void initView() {
        mCurriculmToolbar = (Toolbar) inflate.findViewById(R.id.curriculm_toolbar);
        hideToolbar();
    }
    /*
     * 显示toolbar
     * */
    public void showToolbar() {
        mCurriculmToolbar.setVisibility(inflate.VISIBLE);
    }

    /*
     * 隐藏toolbar
     * */
    public void hideToolbar() {
        mCurriculmToolbar.setVisibility(inflate.GONE);
    }


}