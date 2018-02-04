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
import com.amap.api.navi.model.AMapNaviPath;
import com.amap.api.navi.model.NaviLatLng;
import com.amap.navi.demo.R;
import com.amap.navi.demo.activity.CYC.TimeBetweenTwo;
import com.amap.navi.demo.activity.search.AsynchronousGet;
import com.amap.navi.demo.activity.search.CalculateTImeBetweenTwo;
import com.amap.navi.demo.activity.search.PostExample;
import com.amap.navi.demo.activity.search.TestActivity;
import com.amap.navi.demo.noah.GA;

import java.io.IOException;
import java.util.Vector;


public class ShowRideRouteCalculateResultActivity extends BaseActivity {
    static int[][] timeMap;

    //列举测试的位置
    Vector<NaviLatLng> locationVector = new Vector<>();
    NaviLatLng n1 = new NaviLatLng(31.292404, 121.555749); //上海理工大学516号
    NaviLatLng n2 = new NaviLatLng(31.299038,121.545780); // 上海理工大学基础学院1100号
    NaviLatLng n3 = new NaviLatLng(31.294676,121.505600); //上海市杨浦区复旦步行街
    NaviLatLng n4 = new NaviLatLng(31.301048,121.513352); //上海五角场万达影城
    NaviLatLng n5 = new NaviLatLng(31.228364,121.475481); //上海博物馆
    NaviLatLng n6 = new NaviLatLng(31.282892,121.501410); //同济大学杨浦区同济大学四平路1239号

    NaviLatLng source = n1;
    NaviLatLng dest = n2;

    //创建视图
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_navi);
        mAMapNaviView = (AMapNaviView) findViewById(R.id.navi_view);
        mAMapNaviView.onCreate(savedInstanceState);
        mAMapNaviView.setAMapNaviViewListener(this);
    }

    //计算路线
    @Override
    public void onInitNaviSuccess() {
        super.onInitNaviSuccess();
        mAMapNavi.calculateRideRoute(source, dest);
    }

    //计算路线成功后的操作
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onCalculateRouteSuccess(int[] ids) {
        super.onCalculateRouteSuccess(ids);
        try {
            getTimeFromAMAP(source,dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Log.d("msg","start calLocationList");
        try {
            calLocationList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //所有位置收集在一个List中
    public void calLocationList() throws IOException {
        //Vector<NaviLatLng> locationVector = new Vector<NaviLatLng>();
        locationVector.add(n1);
        locationVector.add(n2);
        locationVector.add(n3);
        locationVector.add(n4);
        locationVector.add(n5);
        locationVector.add(n6);

        int len = locationVector.size();
        timeMap = calTimeMap(locationVector);
        checkTimeMap();
        GACal();

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void getTimeFromAMAP(NaviLatLng a, NaviLatLng b) throws IOException {
        //myOnCreate(nowInstanceState);
        Log.d("msg","Calculate succ...get time... ");
        mAMapNavi.startNavi(NaviType.GPS);
        AMapNaviPath pt = mAMapNavi.getNaviPath();
        int allTime = pt.getAllTime();
        String s = String.valueOf(allTime);
        Log.d("msg","Path Time: "+s);
    }

    public void checkTimeMap() {
        int l = timeMap.length;
        for (int i = 0; i < l; i++) {
            String s = "";
            for (int j = 0; j < l; j++) {
                s += Integer.valueOf(timeMap[i][j]) + " ";
            }
            Log.d("msg",s);
        }

    }

    public int[][] calTimeMap(Vector<NaviLatLng> locationList) {

        int len = locationList.size();
        int[][] tMap = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = i+1; j < len; j++) {
                //test
                TimeBetweenTwo temp = new TimeBetweenTwo();
                NaviLatLng ori = locationList.elementAt(i);
                NaviLatLng dest = locationList.elementAt(j);
                temp.r(ori, dest);
                int time = temp.getTime();
                tMap[i][j] = time;
                tMap[j][i] = time;
                //Log.d("msg","ori: "+Integer.toString(i)+" dest: "+Integer.toString(j)+"get Time: "+ Integer.toString(time));
            }
        }
        return tMap;
    }

    public void GACal() throws IOException {
        Log.d("msg","\"GA Begins\"");
        GA ga = new GA(30, timeMap, 1000, 0.8f, 0.9f);
        ga.init();
        ga.solve();
        int[] ans = ga.getBestTour();
        source = locationVector.elementAt(ans[0]);
        dest = locationVector.elementAt(ans[1]);
    }
}

