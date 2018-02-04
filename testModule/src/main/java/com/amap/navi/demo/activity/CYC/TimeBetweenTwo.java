package com.amap.navi.demo.activity.CYC;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.amap.api.navi.model.NaviLatLng;
import com.amap.navi.demo.activity.search.AsynchronousGet;
import com.amap.navi.demo.activity.search.CalculateTImeBetweenTwo;

public class TimeBetweenTwo {
    static Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String val = data.getString("value");
//            Log.d("msg","DistanceBetweenTwo 请求结果-->" + val);
        }
    };

    static int time;

    public static int getTime() {
        return time;
    }

    public TimeBetweenTwo() {

    }

    private String getJingWei(NaviLatLng lo) {
      String JingWei;
      JingWei = Double.toString(lo.getLongitude()) + "," + Double.toString(lo.getLatitude());
      return JingWei;
    };


    public void r(NaviLatLng ori, NaviLatLng des) {

        final String oriJingWei = getJingWei(ori);
        final String desJingWei = getJingWei(des);

        //通过API  根据地址编码成经纬度坐标
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




        Runnable post = new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void run() {
                CalculateTImeBetweenTwo t = new CalculateTImeBetweenTwo();
                int duration = t.cal(oriJingWei,desJingWei);
                time = duration;
                //Log.d("msg","TimeBetweenTwo Distance Between Two Test:"+Integer.toString(duration));
            }
        };


        new Thread(runnable).start();
        Thread timeCalThread = new Thread(post);
        timeCalThread.start();
        try {
            timeCalThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
