package com.mad.trafficclient.st_java.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.mad.trafficclient.R;

public class MapActivityST extends Activity {
    private MapView mapView = null;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_map_st);
        initView();
        mapView.onCreate(savedInstanceState);
        initData();
        initListener();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    private void initListener() {

    }

    private void initData() {
        //初始化地图控制器对象
        AMap aMap = null;
        if (aMap == null) {
            aMap = mapView.getMap();
        }
    }

    private void initView() {
        mapView = (MapView) findViewById(R.id.mapView);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        mapView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        mapView.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        mapView.onSaveInstanceState(outState);
    }
}
