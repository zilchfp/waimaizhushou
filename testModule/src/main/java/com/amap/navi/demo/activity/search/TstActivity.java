package com.amap.navi.demo.activity.search;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.amap.api.navi.AMapNavi;
import com.amap.navi.demo.R;
import com.amap.navi.demo.activity.BaseActivity;
import com.amap.navi.demo.activity.DriverListActivity;
import com.amap.navi.demo.activity.EmulatorActivity;
import com.amap.navi.demo.activity.GPSNaviActivity;
import com.amap.navi.demo.activity.GetNaviStepsAndLinksActivity;
import com.amap.navi.demo.activity.HudDisplayActivity;
import com.amap.navi.demo.activity.IndexActivity;
import com.amap.navi.demo.activity.IndexAdapter;
import com.amap.navi.demo.activity.IntelligentBroadcastActivity;
import com.amap.navi.demo.activity.RideRouteCalculateActivity;
import com.amap.navi.demo.activity.UseExtraGpsDataActivity;
import com.amap.navi.demo.activity.WalkRouteCalculateActivity;
import com.amap.navi.demo.activity.custom.ComponentActivity;
import com.amap.navi.demo.activity.custom.CustomUiActivity;
import com.amap.navi.demo.util.CheckPermissionsActivity;

import java.util.Arrays;

public class TstActivity extends BaseActivity {

    Intent i2 = getIntent();
    String s = getIntent().getStringExtra("test");




    private AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            if (position == 0) {//组件
                startActivity(new Intent(TstActivity.this, ComponentActivity.class));
            } else if (position == 1) {//驾车
                startActivity(new Intent(TstActivity.this, DriverListActivity.class));
            } else if (position == 2) {//步行路线规划
                startActivity(new Intent(TstActivity.this, WalkRouteCalculateActivity.class));
            } else if (position == 3) {//骑行路线规划
                startActivity(new Intent(TstActivity.this, RideRouteCalculateActivity.class));
            } else if (position == 4) {//实时导航
                startActivity(new Intent(TstActivity.this, GPSNaviActivity.class));
            } else if (position == 5) {//模拟导航
                startActivity(new Intent(TstActivity.this, EmulatorActivity.class));
            } else if (position == 6) {//只能巡航
                startActivity(new Intent(TstActivity.this, IntelligentBroadcastActivity.class));
            } else if (position == 7) {//使用设备外GPS数据导航
                startActivity(new Intent(TstActivity.this, UseExtraGpsDataActivity.class));
            } else if (position == 8) {//导航UI在自定义
                startActivity(new Intent(TstActivity.this, CustomUiActivity.class));
            } else if (position == 9) {//HUD导航
                startActivity(new Intent(TstActivity.this, HudDisplayActivity.class));
            } else if (position == 10) {//展示导航路径详情
                startActivity(new Intent(TstActivity.this, GetNaviStepsAndLinksActivity.class));
            }
        }
    };
    private String[] examples = new String[]

            {"导航组件<font color='red'>(新)<font>", "驾车路线规划2", "步行路线规划", "骑行路线规划", "实时导航", "模拟导航", "智能巡航", "传入GPS数据导航",
                    "导航UI自定义", "HUD导航", "展示导航路径详情"+s
            };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tst);
        initView();
    }

    private void initView() {
        //Log.d(TAG,"initView");
//        ListView listView = (ListView) findViewById(R.id.list);
//        if (listView == null) {
//            Log.d("msg","listView is null");
//        }
//        IndexAdapter adapter = new IndexAdapter(this, Arrays.asList(examples));
//        if (adapter == null) {
//            Log.d("msg","adapter");
//        }
//        listView.setAdapter(adapter);
//        setTitle("外卖员自助寻路系统 ");
//        listView.setOnItemClickListener(mItemClickListener);
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
