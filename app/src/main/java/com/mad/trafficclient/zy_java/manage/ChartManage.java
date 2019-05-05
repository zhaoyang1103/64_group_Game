package com.mad.trafficclient.zy_java.manage;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.util.ArrayList;

/**
 * Created by 昭阳 on 2019/5/5.
 */
public class ChartManage {
    private PieChart pieChart;
    private BarChart barChart;
    private HorizontalBarChart horizontalBarChart;
    private XAxis xAxis;
    private YAxis yleft, yright;

    public ChartManage(PieChart pieChart) {
        this.pieChart = pieChart;
        pieChart.animateXY(1000, 1000);
        pieChart.setDescription("");
        pieChart.setDrawHoleEnabled(false);
        pieChart.setTouchEnabled(false);
        pieChart.getLegend().setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);

    }

    public ChartManage(BarChart barChart) {
        this.barChart = barChart;
        barChart.animateXY(1000, 1000);
        barChart.getLegend().setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        xAxis = barChart.getXAxis();
        yleft = barChart.getAxisLeft();
        yright = barChart.getAxisRight();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        yright.setEnabled(false);
        xAxis.setDrawGridLines(false);
        xAxis.setLabelsToSkip(0);
        barChart.setTouchEnabled(false);


    }

    public ChartManage(HorizontalBarChart horizontalBarChart) {
        this.horizontalBarChart = horizontalBarChart;
        horizontalBarChart.animateXY(1000, 1000);
        horizontalBarChart.getLegend().setEnabled(false);
        xAxis = horizontalBarChart.getXAxis();
        yleft = horizontalBarChart.getAxisLeft();
        yright = horizontalBarChart.getAxisRight();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        yleft.setEnabled(false);
        horizontalBarChart.setTouchEnabled(false);

    }

    public void showPieChart(ArrayList<String> x, ArrayList<Float> y) {
        ArrayList<Entry> entries = new ArrayList<>();
        for (int i = 0; i < y.size(); i++) {
            entries.add(new Entry(y.get(i), i));
        }
        int[] ints = new int[]{};

        PieDataSet pieDataSet = new PieDataSet(entries, "");
        PieData pieData = new PieData(x, pieDataSet);
        pieChart.setData(pieData);


    }

}
