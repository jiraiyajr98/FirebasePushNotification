package com.example.jiraiya.firebasepushnotification;


import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView send_notiTv;
    TextView receive_notiTv;
    ViewPager pager;

    ViewPagerAdapter pageradapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        send_notiTv = (TextView)findViewById(R.id.send_notiTv);
        receive_notiTv = (TextView)findViewById(R.id.received_notiTv);
        pager = (ViewPager) findViewById(R.id.view_pager);

        send_notiTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(0);
            }
        });

        receive_notiTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(1);
            }
        });

        pageradapter = new ViewPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pageradapter);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                changeLable(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void changeLable(int i) {

        if(i == 0){
            send_notiTv.setTextColor(Color.WHITE);
            send_notiTv.setTextSize(TypedValue.COMPLEX_UNIT_SP,24);

            receive_notiTv.setTextColor(getColor(R.color.colorInactive));
            receive_notiTv.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
        }
        if(i == 1){

            send_notiTv.setTextColor(getColor(R.color.colorInactive));
            send_notiTv.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);

            receive_notiTv.setTextColor(Color.WHITE);
            receive_notiTv.setTextSize(TypedValue.COMPLEX_UNIT_SP,24);
        }
    }
}
