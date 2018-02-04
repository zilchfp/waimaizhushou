package com.amap.navi.demo.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.amap.api.navi.AMapNaviView;
import com.amap.api.navi.enums.NaviType;
import com.amap.api.navi.model.NaviLatLng;
import com.amap.navi.demo.R;
import com.amap.api.navi.model.AMapNaviPath;
import com.amap.navi.demo.activity.search.AsynchronousGet;
import com.amap.navi.demo.activity.search.TestActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;


public class RideRouteCalculateActivity extends BaseActivity {
    static Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String val = data.getString("value");
            Log.d("msg","请求结果-->" + val);
        }
    };

    static Bundle nowInstanceState = null;

    Vector<NaviLatLng> locationVector = new Vector<>();
    NaviLatLng n1 = new NaviLatLng(31.292942, 121.557587);
    NaviLatLng n2 = new NaviLatLng(31.053154,121.750938);
    NaviLatLng n3 = new NaviLatLng(31.298877,121.506998); //复旦


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nowInstanceState = savedInstanceState;
        setContentView(R.layout.activity_basic_navi);
        mAMapNaviView = (AMapNaviView) findViewById(R.id.navi_view);
        mAMapNaviView.onCreate(savedInstanceState);
        mAMapNaviView.setAMapNaviViewListener(this);
    }

    protected void myOnCreate(Bundle savedInstanceState) {
        Log.d("msg","myOnCreate");
        super.onCreate(savedInstanceState);
        nowInstanceState = savedInstanceState;
    }



    @Override
    public void onInitNaviSuccess() {
        super.onInitNaviSuccess();
        mAMapNavi.calculateRideRoute(n1, n3);
    }

    public void onInitNaviSuccess(NaviLatLng a, NaviLatLng b) {
        super.onInitNaviSuccess();
        mAMapNavi.calculateRideRoute(a, b);
    }


    public void wrap(NaviLatLng a, NaviLatLng b) throws IOException {
        Log.d("msg","start cal");
        mAMapNavi.startNavi(NaviType.GPS);

        AMapNaviPath pt = mAMapNavi.getNaviPath();
        int allTime = pt.getAllTime();
        String s = String.valueOf(allTime);
        Log.d("msg",s);
        Intent i = new Intent(RideRouteCalculateActivity.this, TestActivity.class);
        i.putExtra("test",s);

    }


    @Override
    public void onCalculateRouteSuccess(int[] ids) {
        super.onCalculateRouteSuccess(ids);

        try {
            wrap(n1,n3);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Runnable runnable = new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void run() {
                // 通过地理编码API得到经纬度坐标
                String url = "http://restapi.amap.com/v3/geocode/geo?address=北京市朝阳区阜通东大街6号&key=f33513116a6e95b156fc389052ea4509";
                url = "http://restapi.amap.com/v3/geocode/geo?key=f33513116a6e95b156fc389052ea4509&address=上海理工大学&city=上海&output=JSON";
                try {
                    new AsynchronousGet().run(url);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                String res = "";

                Message msg = new Message();
                Bundle data = new Bundle();
                data.putString("value", res);
                msg.setData(data);
                handler.sendMessage(msg);
            }
                    };

        new Thread(runnable).start();
    }

}

