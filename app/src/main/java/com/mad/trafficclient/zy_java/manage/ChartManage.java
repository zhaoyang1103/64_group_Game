package com.mad.trafficclient.zy_java.manage;

import android.graphics.Color;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.text.DecimalFormat;
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
        pieChart.setUsePercentValues(true);

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
        int[] ints = new int[]{Color.BLUE, Color.RED};

        PieDataSet pieDataSet = new PieDataSet(entries, "");
        pieDataSet.setColors(ints);
        PieData pieData = new PieData(x, pieDataSet);
        pieDataSet.setValueFormatter(new PercentFormatter(new DecimalFormat("0.00")));
        pieChart.setData(pieData);
        pieChart.invalidate();
    }

    public void showHoriChart(ArrayList<String> x, ArrayList<Float> y) {
        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < y.size(); i++) {
            entries.add(new BarEntry(y.get(i), i));
        }
        int[] ints = new int[]{Color.GREEN, Color.BLUE, Color.RED};
        BarDataSet barDataSet = new BarDataSet(entries, "");
        barDataSet.setColors(ints);
        BarData barData = new BarData(x, barDataSet);
        horizontalBarChart.setData(barData);
        barDataSet.setValueFormatter(new PercentFormatter(new DecimalFormat("0.00")));

        horizontalBarChart.invalidate();
    }

    public void showBarchart6(ArrayList<String> x, ArrayList<Float> y) {
        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < y.size(); i++) {
            entries.add(new BarEntry(y.get(i), i));
        }
        int[] ints = new int[]{Color.GREEN, Color.BLUE, Color.RED};
        BarDataSet barDataSet = new BarDataSet(entries, "");
        barDataSet.setColors(ints);
        BarData barData = new BarData(x, barDataSet);
        barDataSet.setValueFormatter(new PercentFormatter(new DecimalFormat("0.00")));

        barChart.setData(barData);
        barChart.invalidate();
    }

    public void showDoubleChart(ArrayList<String> x, ArrayList<float[]> y) {
        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < y.size(); i++) {
            entries.add(new BarEntry(y.get(i), i));
        }
        int[] ints = new int[]{Color.GREEN, Color.parseColor("#FF9800")};
        BarDataSet barDataSet = new BarDataSet(entries, "");
        barDataSet.setColors(ints);
        BarData barData = new BarData(x, barDataSet);
        barDataSet.setValueFormatter(new PercentFormatter(new DecimalFormat("0.00")));
        barDataSet.setStackLabels(new String[]{"无违章","有违章"});
        barChart.setData(barData);
        barChart.invalidate();
    }

}
