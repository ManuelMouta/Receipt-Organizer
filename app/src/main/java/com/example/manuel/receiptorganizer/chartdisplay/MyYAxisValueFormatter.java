package com.example.manuel.receiptorganizer.chartdisplay;

import com.example.manuel.receiptorganizer.MainActivity;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;

import java.text.DecimalFormat;

public class MyYAxisValueFormatter implements YAxisValueFormatter {

    private DecimalFormat mFormat;

    public MyYAxisValueFormatter() {
        mFormat = new DecimalFormat("###,###,###,##0.0");
    }

    @Override
    public String getFormattedValue(float value, YAxis yAxis) {
        String Unit = "";
        if(MainActivity.currency.equals("Euros"))
            Unit = " €";
        if(MainActivity.currency.equals("Dollars"))
            Unit = " $";
        if(MainActivity.currency.equals("Pounds"))
            Unit = " £";
        return mFormat.format(value) + Unit;
    }
}
