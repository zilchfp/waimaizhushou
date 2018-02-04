package com.amap.navi.demo.activity.CYC;

import com.amap.api.navi.model.NaviLatLng;

import java.util.Vector;

/**
 * Created by zilchfp on 17-11-25.
 */

public class RideRouteCalculation {
    private static Vector<NaviLatLng> locationList = new Vector<>();
    private static int distance[][];

    public void addLocation(NaviLatLng loca) {
        locationList.add(loca);
    }

    public void calLocationList() {
        int len = locationList.size();
        distance = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {

            }
        }
    }

}
