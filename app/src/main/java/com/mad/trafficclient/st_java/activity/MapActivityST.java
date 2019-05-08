package com.mad.trafficclient.st_java.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.mad.trafficclient.R;

public class MapActivityST extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_map_st);

    }
}
