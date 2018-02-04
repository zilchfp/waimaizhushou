package com.amap.navi.demo.activity.search;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amap.navi.demo.R;
import com.amap.navi.demo.activity.BaseActivity;

public class TestActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_test);

        Button b = (Button) findViewById(R.id.test_button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = getIntent();
                String s = getIntent().getStringExtra("test");

                TextView t = (TextView) findViewById(R.id.test_button);
                if (t == null) {
                    Log.d("msg","textview is null");
                }
                t.setText(s);
            }
        });


    }






}
