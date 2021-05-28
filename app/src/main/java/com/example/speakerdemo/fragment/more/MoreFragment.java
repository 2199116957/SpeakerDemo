package com.example.speakerdemo.fragment.more;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.speakerdemo.R;

public class MoreFragment extends Fragment {


    private View inflate;
    private Toolbar mMoreToolbar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_more, container, false);
        initView();
        return inflate;
    }

    private void initView() {
        mMoreToolbar = (Toolbar) inflate.findViewById(R.id.more_toolbar);
        hideToolbar();
    }

    /*
     * 显示toolbar
     * */
    public void showToolbar() {
        mMoreToolbar.setVisibility(inflate.VISIBLE);
    }

    /*
     * 隐藏toolbar
     * */
    public void hideToolbar() {
        mMoreToolbar.setVisibility(inflate.GONE);
    }

}