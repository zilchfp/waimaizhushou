package com.amap.navi.demo.activity.search;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.amap.api.navi.model.NaviLatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;


public final class AsynchronousGet {
    private final OkHttpClient client = new OkHttpClient();

    //根据地理编码的结果返回NaviLatLng格式的经纬度坐标
    public NaviLatLng getNaviLatLng(String json_string) throws JSONException {
        //Log.d("msg","start parsing");
        JSONObject root = new JSONObject(json_string);

        JSONArray geocodes= root.getJSONArray("geocodes");
        JSONObject zero = geocodes.getJSONObject(0);
        String location= zero.getString("location");

        String[] parts = location.split(",");
        NaviLatLng l = new NaviLatLng(Double.valueOf(parts[1]), Double.valueOf(parts[0]));
//        Log.d("msg",parts[1]);
        return l;

    };


        public void run(String url) throws Exception {
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                    //获取返回结果
                    String bds = responseBody.string();
                    //Log.d("msg",bds);
                    try {
                        getNaviLatLng(bds);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
