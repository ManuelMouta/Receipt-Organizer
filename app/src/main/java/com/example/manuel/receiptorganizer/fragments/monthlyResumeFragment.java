package com.example.manuel.receiptorganizer.fragments;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.manuel.receiptorganizer.MainActivity;
import com.example.manuel.receiptorganizer.R;
import com.example.manuel.receiptorganizer.objects.ReceiptObject;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nb21910 on 10/06/16.
 */
public class MonthlyResumeFragment extends Fragment {

    private PieChart mChart;

    private List<ReceiptObject> receipts;

    private View view;

    private Typeface tf;

    private String[] mParties = new String[] {
            MainActivity.Category1, MainActivity.Category2, MainActivity.Category3,
            MainActivity.Category4, MainActivity.Category5,MainActivity.Category6
    };

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.piechartfargment, container, false);

        startPieChart();

        return view;
    }

    private  void startPieChart(){
        MainActivity.receiptDBoperation.open();
        receipts = MainActivity.receiptDBoperation.getAllReceipts();


        mChart = (PieChart) view.findViewById(R.id.piechart);
        mChart.setUsePercentValues(true);
        mChart.setDescription("");
        mChart.setExtraOffsets(5, 10, 5, 5);

        mChart.setDragDecelerationFrictionCoef(0.95f);

        tf = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Regular.ttf");

        //mChart.setCenterTextTypeface(Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf"));
        //mChart.setCenterText(generateCenterSpannableText());

        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColor(Color.WHITE);

        mChart.setTransparentCircleColor(Color.WHITE);
        mChart.setTransparentCircleAlpha(110);

        mChart.setHoleRadius(58f);
        mChart.setTransparentCircleRadius(61f);

        mChart.setDrawCenterText(true);

        mChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        mChart.setRotationEnabled(true);
        mChart.setHighlightPerTapEnabled(true);

        // mChart.setUnit(" €");
        // mChart.setDrawUnitsInChart(true);

        // add a selection listener
        //mChart.setOnChartValueSelectedListener(MonthlyResumeFragment.this);

        setData(6, 1);

        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        // mChart.spin(2000, 0, 360);

        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);
    }

    private void setData(int count, float range) {

        float mult = range;
        range = 100;
        ArrayList<Integer> colors = new ArrayList<Integer>();
        ArrayList<Float> categoryValues = new ArrayList<Float>();
        for(int i=0;i<count;i++) {
            if(getCategoryPercent(Integer.toString(i+1))>0) {
                categoryValues.add(getCategoryPercent(Integer.toString(i + 1)));
                if(i==0)
                    colors.add(getResources().getColor(R.color.category1));
                if(i==1)
                    colors.add(getResources().getColor(R.color.category2));
                if(i==2)
                    colors.add(getResources().getColor(R.color.category3));
                if(i==3)
                    colors.add(getResources().getColor(R.color.category4));
                if(i==4)
                    colors.add(getResources().getColor(R.color.category5));
                if(i==5)
                    colors.add(getResources().getColor(R.color.category6));
            }
        }

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        // IMPORTANT: In a PieChart, no values (Entry) should have the same
        // xIndex (even if from different DataSets), since no values can be
        // drawn above each other.
        for (int i = 0; i < categoryValues.size(); i++) {
            yVals1.add(new Entry(categoryValues.get(i), i));
        }

        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0; i < count + 1; i++)
            xVals.add(mParties[i % mParties.length]);

        PieDataSet dataSet = new PieDataSet(yVals1, "Categories");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(14f);
        data.setValueTextColor(Color.BLACK);
        data.setValueTypeface(tf);
        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);

        mChart.invalidate();
    }

    private float getCategoryPercent(String Category){
        float total = 0;

        for(int i = 0;i<receipts.size();i++){
            if(receipts.get(i).getCategory().equals(Category))
                total+=receipts.get(i).getTotal();
        }
        return (total/getReceiptsTotal())*100;
    }

    private float getReceiptsTotal(){
        float total = 0;

        for(int i = 0;i<receipts.size();i++){
            total+=receipts.get(i).getTotal();
        }
        return total;
    }
}
