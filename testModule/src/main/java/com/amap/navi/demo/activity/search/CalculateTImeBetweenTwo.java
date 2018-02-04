package com.amap.navi.demo.activity.search;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.amap.api.navi.model.NaviLatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


/*


 */

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class CalculateTImeBetweenTwo {
//
//    private static String ori = null;
//    private static String des = null;

    //latitude
    public int cal(String ori, String des) {
        PostExample example = new PostExample();
        String json = example.bowlingJson("Jesse", "Jake");
        String response = null;
        String duration = null;

        String url = "http://restapi.amap.com/v4/direction/bicycling?key=f33513116a6e95b156fc389052ea4509";
        ori = "&origin=" + ori;
        des = "&destination=" + des;
        url += ori + des;
//        Log.d("msg","url: "+url);
        try {
            response = example.post(url, json);
            //String response = example.post("http://www.roundsapp.com/post", json);

//            Log.d("msg","start parsing CalculateTImeBetweenTwo response");
            String json_string = response.toString();
            JSONObject root = new JSONObject(json_string);

            JSONObject data= root.getJSONObject("data");
            JSONArray paths = data.getJSONArray("paths");
            JSONObject zero = paths.getJSONObject(0);
            duration= zero.getString("duration");
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return (Integer.parseInt(duration));
    }
}
