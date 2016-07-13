package com.example.manuel.receiptorganizer.chartdisplay;

import com.example.manuel.receiptorganizer.MainActivity;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;

public class MyValueFormatter implements ValueFormatter {

    private DecimalFormat mFormat;
    
    public MyValueFormatter() {
        mFormat = new DecimalFormat("###,###,###,##0.0");
    }

    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        String Unit = "";
        if(MainActivity.currency.equals("Euros"))
            Unit = " €";
        if(MainActivity.currency.equals("Dollars"))
            Unit = " $";
        if(MainActivity.currency.equals("Pounds"))
            Unit = " £";
        if(value>0)
            return mFormat.format(value) + Unit;
        else
            return "";
    }
}
