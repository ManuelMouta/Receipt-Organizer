package com.example.manuel.receiptorganizer.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.manuel.receiptorganizer.R;
import com.example.manuel.receiptorganizer.chartdisplay.DemoBase;
import com.example.manuel.receiptorganizer.fragments.AnnualResumeFragment;
import com.example.manuel.receiptorganizer.fragments.MonthlyResumeFragment;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

/**
 * Created by nb21910 on 08/06/16.
 */
public class OverviewReceiptActivity extends DemoBase implements SeekBar.OnSeekBarChangeListener,
        OnChartValueSelectedListener {

    private TextView overviewBtn;

    private TextView annualResumeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fr = new MonthlyResumeFragment();
        fragmentTransaction.add(R.id.chart_fragment, fr);
        fragmentTransaction.commit();

        setContentView(R.layout.activity_piechart);

        overviewBtn = (TextView) findViewById(R.id.btn1);
        annualResumeBtn = (TextView) findViewById(R.id.btn2);

        overviewBtn.setBackgroundColor(getResources().getColor(R.color.darkblue));
        annualResumeBtn.setBackgroundColor(Color.WHITE);
        overviewBtn.setTextColor(Color.WHITE);
        annualResumeBtn.setTextColor(Color.BLACK);

        /*Fragment fr = new MonthlyResumeFragment();

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.chart_fragment, fr);
        fragmentTransaction.commit();*/

//      final ColorStateList oldColors =  overviewBtn.getTextColors();

        overviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                overviewBtn.setBackgroundColor(getResources().getColor(R.color.darkblue));
                annualResumeBtn.setBackgroundColor(Color.WHITE);
                overviewBtn.setTextColor(Color.WHITE);
                annualResumeBtn.setTextColor(Color.BLACK);

                android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment fr = new MonthlyResumeFragment();
                fragmentTransaction.replace(R.id.chart_fragment, fr);
                fragmentTransaction.commit();

            }
        });
        annualResumeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                annualResumeBtn.setBackgroundColor(getResources().getColor(R.color.darkblue));
                overviewBtn.setBackgroundColor(Color.WHITE);
                annualResumeBtn.setTextColor(Color.WHITE);
                overviewBtn.setTextColor(Color.BLACK);

                android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment fr = new AnnualResumeFragment();
                fragmentTransaction.replace(R.id.chart_fragment, fr);
                fragmentTransaction.commit();
            }
        });
    }


    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}

