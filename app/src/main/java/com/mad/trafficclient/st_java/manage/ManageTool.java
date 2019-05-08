package com.mad.trafficclient.st_java.manage;

import android.graphics.Color;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class ManageTool {

    private BarChart barChart;
    private XAxis xAxis;
    private YAxis axisLeft;
    private YAxis axisRight;
    private LineChart lineChart;

    public ManageTool(BarChart barChart) {
        this.barChart = barChart;
        xAxis = barChart.getXAxis();
        axisLeft = barChart.getAxisLeft();
        axisRight = barChart.getAxisRight();
        initBarStyle();
    }

    private void initBarStyle() {

    }

    public ManageTool(LineChart lineChart) {
        this.lineChart = lineChart;
        xAxis = lineChart.getXAxis();
        axisLeft = lineChart.getAxisLeft();
        axisRight = lineChart.getAxisRight();
        initLineStyle();
    }

    private void initLineStyle() {

    }

    public void showBar(ArrayList<String> x, ArrayList<Float> y) {
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        for (int i = 0; i < y.size(); i++) {
            barEntries.add(new BarEntry(y.get(i), i));
        }
        BarDataSet barDataSet = new BarDataSet(barEntries, "");
        BarData barData = new BarData(x, barDataSet);
        barChart.setData(barData);
        barChart.invalidate();
    }

    public void showLine(ArrayList<String> x, ArrayList<Float> y) {
        ArrayList<Entry> entries = new ArrayList<>();
        for (int i = 0; i < y.size(); i++) {
            entries.add(new Entry(y.get(i), i));
        }
        LineDataSet lineDataSet = new LineDataSet(entries, "");
        int[] ints = new int[y.size()];
        for (int i = 0; i < y.size(); i++) {
            if (y.get(i) > 30) {
                ints[i] = Color.RED;
            } else {
                ints[i] = Color.BLUE;
            }
        }
        lineDataSet.setCircleColors(ints);
        LineData data = new LineData(x, lineDataSet);
        lineChart.setData(data);
        lineChart.invalidate();
    }
}
