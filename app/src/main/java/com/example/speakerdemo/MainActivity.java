package com.example.speakerdemo;

import android.Manifest;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.security.identity.NoAuthenticationKeyAvailableException;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.speakerdemo.fragment.curriculum.CurriculumFragment;
import com.example.speakerdemo.fragment.home.HomeFragment;
import com.example.speakerdemo.fragment.me.MeFragment;
import com.example.speakerdemo.fragment.more.MoreFragment;
import com.example.speakerdemo.fragment.news.NewsFragment;
import com.example.speakerdemo.utils.FullScreenUtil;
import com.example.speakerdemo.utils.PermissionsUtils;
import com.example.speakerdemo.utils.RxToast;
import com.google.android.material.tabs.TabLayout;
import com.gyf.immersionbar.ImmersionBar;

public class MainActivity extends AppCompatActivity {

    private FrameLayout fragmentMain;
    private TabLayout tablayoutMain;
    private FragmentManager supportFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RxToast.setContext(this);
        ImmersionBar.with(this).fitsSystemWindows(true).statusBarColor(R.color.white).autoStatusBarDarkModeEnable(true,0.2f).init();
        initView();
    }


    /*
    *设置页面切换
    * */
    private void initView() {
        //获取界面控件ID
        fragmentMain = (FrameLayout) findViewById(R.id.fragment_Main);
        tablayoutMain = (TabLayout) findViewById(R.id.tablayout_Main);

        //获取Fragment对象
        //首页fragment
        HomeFragment homeFragment = new HomeFragment();
        //课程fragment
        CurriculumFragment curriculumFragment = new CurriculumFragment();
        //更多fragment
        NewsFragment newsFragment = new NewsFragment();
        //消息fragment
        MoreFragment moreFragment = new MoreFragment();
        //我的fragment
        MeFragment meFragment = new MeFragment();
        //获取supportFragmentManager对象，把fragment对象添加到supportFragmentManager中
        supportFragmentManager = getSupportFragmentManager();
        supportFragmentManager.beginTransaction()
                .add(R.id.fragment_Main, homeFragment)
                .add(R.id.fragment_Main, curriculumFragment)
                .add(R.id.fragment_Main, newsFragment)
                .add(R.id.fragment_Main, moreFragment)
                .add(R.id.fragment_Main, meFragment)
                .show(homeFragment)
                .hide(curriculumFragment)
                .hide(moreFragment)
                .hide(newsFragment)
                .hide(meFragment)
                .commit();
        //添加TabLayout选择器和文字
        tablayoutMain.addTab(tablayoutMain.newTab().setText(R.string.main_app_Home).setIcon(R.drawable.main_tablayout_home_select));
        tablayoutMain.addTab(tablayoutMain.newTab().setText(R.string.main_app_Curriculum).setIcon(R.drawable.main_tablayout_curriculum_select));
        tablayoutMain.addTab(tablayoutMain.newTab().setIcon(R.drawable.main_tablayout_news_select));
        tablayoutMain.addTab(tablayoutMain.newTab().setText(R.string.main_app_more).setIcon(R.drawable.main_tablayout_more_select));
        tablayoutMain.addTab(tablayoutMain.newTab().setText(R.string.main_app_Me).setIcon(R.drawable.main_tablayout_me_select));
        tablayoutMain.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //根据TabLayout切换页面，show显示当前fragment，hide隐藏fragment
                switch (tab.getPosition()) {
                    case 0:
                        ImmersionBar.with(MainActivity.this).fitsSystemWindows(true)
                                .statusBarColor(R.color.white).autoStatusBarDarkModeEnable(true,0.2f).init();
                        supportFragmentManager.beginTransaction()
                                .show(homeFragment)
                                .hide(curriculumFragment)
                                .hide(moreFragment)
                                .hide(newsFragment)
                                .hide(meFragment)
                                .commit();
                        break;
                    case 1:
                        ImmersionBar.with(MainActivity.this).fitsSystemWindows(true)
                                .statusBarColor(R.color.Blue).autoStatusBarDarkModeEnable(true,0.2f).init();
                        supportFragmentManager.beginTransaction()
                                .show(curriculumFragment)
                                .hide(homeFragment)
                                .hide(moreFragment)
                                .hide(newsFragment)
                                .hide(meFragment)
                                .commit();
                        break;
                    case 2:
                        ImmersionBar.with(MainActivity.this).fitsSystemWindows(true)
                                .statusBarColor(R.color.Blue).autoStatusBarDarkModeEnable(true,0.2f).init();
                        supportFragmentManager.beginTransaction()
                                .show(newsFragment)
                                .hide(curriculumFragment)
                                .hide(homeFragment)
                                .hide(moreFragment)
                                .hide(meFragment)
                                .commit();
                        break;
                    case 3:
                        ImmersionBar.with(MainActivity.this).fitsSystemWindows(true)
                                .statusBarColor(R.color.Blue).autoStatusBarDarkModeEnable(true,0.2f).init();
                        supportFragmentManager.beginTransaction()
                                .show(moreFragment)
                                .hide(curriculumFragment)
                                .hide(newsFragment)
                                .hide(homeFragment)
                                .hide(meFragment)
                                .commit();
                        break;
                    case 4:
                        ImmersionBar.with(MainActivity.this).fitsSystemWindows(true)
                                .statusBarColor(R.color.Blue).autoStatusBarDarkModeEnable(true,0.2f).init();
                        supportFragmentManager.beginTransaction()
                                .show(meFragment)
                                .hide(curriculumFragment)
                                .hide(moreFragment)
                                .hide(newsFragment)
                                .hide(homeFragment)
                                .commit();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }


}