/**
 *
 */
package com.mad.trafficclient.zy_java.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.mad.trafficclient.R;


public class MapViewFragment extends Fragment {

    private MapView mapview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.gaodefragment, container, false);


        initView(view);
        mapview.onCreate(savedInstanceState);
        return view;
    }

    private void initView(View view) {
        mapview = (MapView) view.findViewById(R.id.mapview);
        if (mapview != null) {
            AMap map = mapview.getMap();
        }

    }


    @Override
    public void onDestroy() {
        mapview.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onResume() {
        mapview.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        mapview.onPause();

        super.onPause();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        mapview.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }
}
