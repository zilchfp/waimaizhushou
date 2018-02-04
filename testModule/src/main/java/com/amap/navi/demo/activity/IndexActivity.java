package com.amap.navi.demo.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.amap.api.navi.AMapNavi;
import com.amap.navi.demo.R;
import com.amap.navi.demo.activity.CYC.MainIndex;
import com.amap.navi.demo.activity.CYC.TimeBetweenTwo;
import com.amap.navi.demo.activity.CYC.RideRouteCalculation;
import com.amap.navi.demo.activity.custom.ComponentActivity;
import com.amap.navi.demo.activity.custom.CustomUiActivity;
import com.amap.navi.demo.activity.search.CalculateTImeBetweenTwo;
import com.amap.navi.demo.util.CheckPermissionsActivity;

import java.util.Arrays;

public class IndexActivity extends CheckPermissionsActivity {

    private AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (position == 0) {//组件
                startActivity(new Intent(IndexActivity.this, ComponentActivity.class));
            } else if (position == 1) {//骑行路线规划
                startActivity(new Intent(IndexActivity.this, RideRouteCalculateActivity.class));
            } else if (position == 2) {//实时导航
                startActivity(new Intent(IndexActivity.this, GPSNaviActivity.class));
            } else if (position == 3) {//模拟导航
                startActivity(new Intent(IndexActivity.this, EmulatorActivity.class));
            } else if (position == 4) {//导航UI在自定义
                startActivity(new Intent(IndexActivity.this, CustomUiActivity.class));
            } else if (position == 5) {//HUD导航
                startActivity(new Intent(IndexActivity.this, HudDisplayActivity.class));
            } else if (position == 6) {//展示导航路径详情
                startActivity(new Intent(IndexActivity.this, GetNaviStepsAndLinksActivity.class));
            }

        }
    };
    private String[] examples = new String[]
            {"导航组件<font color='red'>(新)<font>", "骑行路线规划", "实时导航", "模拟导航",
                    "导航UI自定义", "HUD导航", "展示导航路径详情"
            };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        initView();
    }

    private void initView() {
        ListView listView = (ListView) findViewById(R.id.list);
        IndexAdapter adapter = new IndexAdapter(this, Arrays.asList(examples));
        listView.setAdapter(adapter);
        setTitle("外卖员自助寻路系统 ");
        listView.setOnItemClickListener(mItemClickListener);
    }

    /**
     * 返回键处理事件
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            System.exit(0);// 退出程序
        }
        return super.onKeyDown(keyCode, event);
    }
}
