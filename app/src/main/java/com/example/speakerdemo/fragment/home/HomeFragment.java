package com.example.speakerdemo.fragment.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import androidx.fragment.app.Fragment;

import com.example.speakerdemo.R;

public class HomeFragment extends Fragment {

    private View inflate;
    private androidx.appcompat.widget.Toolbar mHomeToolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.fragment_home, container, false);

        initView();
        return inflate;
    }

    private void initView() {

        mHomeToolbar = (androidx.appcompat.widget.Toolbar) inflate.findViewById(R.id.home_toolbar);
        hideToolbar();
    }

    /*
     * 显示toolbar
     * */
    public void showToolbar() {
        mHomeToolbar.setVisibility(inflate.VISIBLE);
    }

    /*
     * 隐藏toolbar
     * */
    public void hideToolbar() {
        mHomeToolbar.setVisibility(inflate.GONE);
    }

}