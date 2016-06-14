package com.example.manuel.receiptorganizer.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.manuel.receiptorganizer.MainActivity;
import com.example.manuel.receiptorganizer.R;
import com.example.manuel.receiptorganizer.chartdisplay.MyValueFormatter;
import com.example.manuel.receiptorganizer.chartdisplay.MyYAxisValueFormatter;
import com.example.manuel.receiptorganizer.objects.ReceiptObject;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by nb21910 on 10/06/16.
 */
public class AnnualResumeFragment extends Fragment {

    private BarChart mBarChart;

    private View view;

    protected String[] mMonths = new String[] {
            "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Okt", "Nov", "Dec"
    };

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.barchartfragment, container, false);

        startBarChart();
        setData();

        return view;
    }

    private void startBarChart(){

        mBarChart = (BarChart) view.findViewById(R.id.barchart);
        //mBarChart.setOnChartValueSelectedListener(this);

        mBarChart.setDescription("");

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        mBarChart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        mBarChart.setPinchZoom(false);

        mBarChart.setDrawGridBackground(false);
        mBarChart.setDrawBarShadow(false);

        mBarChart.setDrawValueAboveBar(false);

        // change the position of the y-labels
        YAxis leftAxis = mBarChart.getAxisLeft();
        leftAxis.setValueFormatter(new MyYAxisValueFormatter());
        leftAxis.setAxisMinValue(0f); // this replaces setStartAtZero(true)
        mBarChart.getAxisRight().setEnabled(false);

        XAxis xLabels = mBarChart.getXAxis();
        xLabels.setPosition(XAxis.XAxisPosition.TOP);

        // mChart.setDrawXLabels(false);
        // mChart.setDrawYLabels(false);

        // setting data
        //mSeekBarX.setProgress(12);
        //mSeekBarY.setProgress(100);

        Legend l = mBarChart.getLegend();
        l.setPosition(Legend.LegendPosition.BELOW_CHART_RIGHT);
        l.setFormSize(8f);
        l.setFormToTextSpace(4f);
        l.setXEntrySpace(6f);

        //mBarChart.setData();
    }

    private void setData(){

        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i <12; i++) {
            xVals.add(mMonths[i % mMonths.length]);
        }

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

        for (int i = 0; i < 12; i++) {
            float mult = (1);
            float val1 = getCategoryValue(String.valueOf(i+1),"1");
            float val2 = getCategoryValue(String.valueOf(i+1),"2");
            float val3 = getCategoryValue(String.valueOf(i+1),"3");
            float val4 = getCategoryValue(String.valueOf(i+1),"4");
            float val5 = getCategoryValue(String.valueOf(i+1),"5");

            yVals1.add(new BarEntry(new float[] { val1, val2, val3, val4, val5 }, i));
        }

        BarDataSet set1;

        if (mBarChart.getData() != null &&
                mBarChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet)mBarChart.getData().getDataSetByIndex(0);
            set1.setYVals(yVals1);
            mBarChart.getData().setXVals(xVals);
            mBarChart.getData().notifyDataChanged();
            mBarChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "Categorias");
            set1.setColors(getColors());
            set1.setStackLabels(new String[]{"Casa", "Lola", "Roupa","Contas","Diversos"});

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);

            BarData data = new BarData(xVals, dataSets);
            data.setValueFormatter(new MyValueFormatter());

            mBarChart.setData(data);
        }

        mBarChart.invalidate();
    }

    private float getCategoryValue(String month,String category){
        if(month.length()==1)
            month = "0"+month;
        MainActivity.receiptDBoperation.open();
        List<ReceiptObject> receipts = MainActivity.receiptDBoperation.getAllReceipts();
        float total = 0;

        for(int i=0;i<receipts.size();i++){
            String date = receipts.get(i).getDate();
            String[] split_date = date.split("-");
            if(split_date[1].equals(month) && category.equals(receipts.get(i).getCategory()))
                total += receipts.get(i).getTotal();
        }
        return total;
    }

    private int[] getColors() {

        int stacksize = 5;

        // have as many colors as stack-values per entry
        int[] colors = new int[stacksize];

        for (int i = 0; i < stacksize; i++) {
            colors[i] = ColorTemplate.VORDIPLOM_COLORS[i];
        }

        return colors;
    }
}
