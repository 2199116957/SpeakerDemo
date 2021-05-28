package com.example.speakerdemo.fragment.news;

import android.Manifest;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.speakerdemo.R;
import com.example.speakerdemo.utils.PermissionsUtils;
import com.example.speakerdemo.utils.RxToast;

public class NewsFragment extends Fragment {

    private View inflate;
    private WebView mWebView;
    private String url = "file:///android_asset/a.html";
    private ValueCallback<Uri> mUploadMessage; //表单的数据信息
    private Toolbar mNewsToolbar;
    private WebView mWebview;
    private WebSettings webSettings;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_news, container, false);
        initView();
        return inflate;
    }

    private void initView() {
        mWebView = (WebView) inflate.findViewById(R.id.webview);
        mNewsToolbar = (Toolbar) inflate.findViewById(R.id.news_toolbar);

        hideToolbar();
        mWebView.addJavascriptInterface(new Js(), "jsObj");

        setWebViewParams(mWebView);
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("http:") || url.startsWith("https:")) {
                    mWebView.loadUrl(url);
                }
                return true;
            }


        });

        mWebView.loadUrl(url);
        mWebView.setWebChromeClient(new MyWebChromeClient());
//        mWebView.setWebViewClient(new MyWebViewClient(getActivity()));
        //在Js类里实现javascript想调用的方法(H5调用android)，"jsObj"就是这个接口的别名
    }

    private void setWebViewParams(WebView webView) {
        webSettings = webView.getSettings();
        //设置WebView是否允许执行JavaScript脚本，默认false，不允许。
        webSettings.setJavaScriptEnabled(true);
        //重写使用缓存的方式，默认值LOAD_DEFAULT。缓存的使用方式基于导航类型，正常的页面加载，检测缓存，需要时缓存内容复现。导航返回时，内容不会复现，只有内容会从缓存盘中恢复。该方法允许客户端通过指定LOAD_DEFAULT, LOAD_CACHE_ELSE_NETWORK, LOAD_NO_CACHE or LOAD_CACHE_ONLY的其中一项来重写其行为。
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        //WebView是否支持HTML的“viewport”标签或者使用wide viewport。设置值为true时，布局的宽度总是与WebView控件上的设备无关像素（device-dependent pixels）宽度一致。
        webSettings.setUseWideViewPort(true);
        //是否允许WebView度超出以概览的方式载入页面，默认false。
        webSettings.setLoadWithOverviewMode(true);
        //是否允许访问文件，默认允许。注意，这里只是允许或禁止对文件系统的访问，Assets 和 resources 文件使用file:///android_asset和file:///android_res仍是可访问的。
        webSettings.setAllowFileAccess(false);
        //设置布局，会引起WebView的重新布局
        webSettings.setLayoutAlgorithm(
                WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        //设置WebView是否支持多窗口。如果设置为true，主程序要实现onCreateWindow(WebView, boolean, boolean, Message)，默认false。
        webSettings.setSupportMultipleWindows(false);
        // webSetting.setLoadWithOverviewMode(true);
        //应用缓存API是否可用，默认值false, 结合setAppCachePath(String)使用。
        webSettings.setAppCacheEnabled(true);
        // webSetting.setDatabaseEnabled(true);
        //DOM存储API是否可用，默认false。
        webSettings.setDomStorageEnabled(true);
        //定位是否可用，默认为true。
        webSettings.setGeolocationEnabled(true);
        //已废弃。设置应用缓存内容的最大值。
//        webSettings.setAppCacheMaxSize(Long.MAX_VALUE);
        //在API18以上已废弃。未来将不支持插件，不要使用。
        webSettings.setPluginState(WebSettings.PluginState.ON_DEMAND);
        //获得WebView的用户代理字符串。
        String userAgentString = webSettings.getUserAgentString();
        //webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            webSettings.setMediaPlaybackRequiresUserGesture(false);
        }
    }

    private class MyWebChromeClient extends WebChromeClient {

        // For Android 3.0+
        public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
            if (mUploadMessage != null)
                return;
            mUploadMessage = uploadMsg;
        }

        // For Android < 3.0
        public void openFileChooser(ValueCallback<Uri> uploadMsg) {
            openFileChooser(uploadMsg, "");
        }

        // For Android > 4.1.1
        public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
            openFileChooser(uploadMsg, acceptType);
        }
    }

//    private class MyWebViewClient extends WebViewClient {
//        private Context mContext;
//
//        public MyWebViewClient(Context context) {
//            super();
//            mContext = context;
//        }
//
//        @Override
//        public void onPageStarted(WebView view, String url, Bitmap favicon) {
//            super.onPageStarted(view, url, favicon);
//        }
//
//        @Override
//        public void onPageFinished(WebView view, String url) {
//            super.onPageFinished(view, url);
//        }
//    }

    class Js {
        @JavascriptInterface
        public void checkIndicatePrivilege() {
            CheckAndroidPermission();//获取相机相册读写权限
        }

    }

    private void CheckAndroidPermission() {
        String[] permissions = new String[]{Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};
        PermissionsUtils.showSystemSetting = true;//是否支持显示系统设置权限设置窗口跳转
        //这里的this不是上下文，是Activity对象！
        PermissionsUtils.getInstance().chekPermissions(getActivity(), permissions, permissionsResult);
    }

    PermissionsUtils.IPermissionsResult permissionsResult = new PermissionsUtils.IPermissionsResult() {
        @Override
        public void passPermissons() {
            RxToast.normal("权限已开启", Toast.LENGTH_LONG);
        }

        @Override
        public void forbitPermissons() {
            Toast.makeText(getActivity(), "权限未开启，请开启权限!", Toast.LENGTH_SHORT).show();

        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionsUtils.getInstance().onRequestPermissionsResult(getActivity(), requestCode, permissions, grantResults);
    }

    /*
     * 显示toolbar
     * */
    public void showToolbar() {
        mNewsToolbar.setVisibility(inflate.VISIBLE);
    }

    /*
     * 隐藏toolbar
     * */
    public void hideToolbar() {
        mNewsToolbar.setVisibility(inflate.GONE);
    }


    @Override
    public void onPause() {
        super.onPause();
        if (mWebView != null) {
            mWebView.onPause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mWebView != null) {
            mWebView.onResume();
        }
    }
}