package com.mad.trafficclient.zy_java.manage;

import android.graphics.Color;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
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
    private LineChart lineChart;
    private XAxis xAxis;
    private YAxis yleft, yright;
    private RadarChart radarChart;

    public ChartManage(RadarChart radarChart) {
        this.radarChart = radarChart;
        radarChart.setDescription("");
        radarChart.getLegend().setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        radarChart.setDrawWeb(true);
        xAxis = radarChart.getXAxis();
        xAxis.setEnabled(false);


    }

    public void showReChart(ArrayList<String> x, ArrayList<Integer> y) {
        ArrayList<Entry> entries = new ArrayList<>();

        for (int i = 0; i < y.size(); i++) {
            entries.add(new Entry(y.get(i), i));
        }
        int[] ints = new int[]{Color.parseColor("#029ED9"), Color.parseColor("#34FF67")
                , Color.parseColor("#EF519D")
                , Color.parseColor("#FF0202")
                , Color.parseColor("#6702FF")};
        RadarDataSet radarDataSet = new RadarDataSet(entries, "");
        radarDataSet.setFillColor(Color.GREEN);
        radarDataSet.setColors(ints);
        radarDataSet.setDrawFilled(true);
        RadarData radarData = new RadarData(x, radarDataSet);
//        radarData
        radarData.setValueTextSize(1);
        radarChart.setData(radarData);
        radarChart.invalidate();

    }

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

    public ChartManage(LineChart lineChart) {
        this.lineChart = lineChart;
        xAxis = lineChart.getXAxis();
        yleft = lineChart.getAxisLeft();
        yright = lineChart.getAxisRight();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        yright.setEnabled(false);
        xAxis.setDrawGridLines(false);
        lineChart.setDescription("");
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
        barDataSet.setStackLabels(new String[]{"无违章", "有违章"});
        barChart.setData(barData);
        barChart.invalidate();
    }

    public void showLineChart(ArrayList<String> x, ArrayList<Integer> y) {
        ArrayList<Entry> entries = new ArrayList<>();
        for (int i = 0; i < y.size(); i++) {
            entries.add(new Entry(y.get(i), i));
        }
        int[] ints = new int[y.size()];
        for (int i = 0; i < y.size(); i++) {
            if (y.get(i) > 20) {
                ints[i] = Color.RED;
            } else {
                ints[i] = Color.GREEN;

            }
        }

        LineDataSet lineDataSet = new LineDataSet(entries, "");
        //lineDataSet;
        lineDataSet.setCircleColors(ints);
        lineDataSet.setColor(Color.BLACK);
        LineData lineData = new LineData(x, lineDataSet);
        lineChart.setData(lineData);
        lineChart.invalidate();
    }

    public void showMessagePieChart(ArrayList<String> x, ArrayList<Integer> y) {
        ArrayList<Entry> entries = new ArrayList<>();
        for (int i = 0; i < y.size(); i++) {
            entries.add(new Entry(y.get(i), i));
        }
        int[] ints = new int[]{Color.parseColor("#BFDD7C")
                , Color.parseColor("#E3DCA2"),
                Color.parseColor("#79EA5B"),
                Color.parseColor("#EE81B6"),
                Color.parseColor("#FF25B4")};

        PieDataSet pieDataSet = new PieDataSet(entries, "");
        pieDataSet.setColors(ints);
        pieChart.getLegend().setTextSize(25);
        PieData pieData = new PieData(x, pieDataSet);
        pieDataSet.setValueFormatter(new PercentFormatter(new DecimalFormat("0.00")));
        pieChart.getLegend().setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        pieData.setValueTextSize(0);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }

}
