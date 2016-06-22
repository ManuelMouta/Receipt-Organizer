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

    /*private void startBarChart(){
        tvX = (TextView) findViewById(R.id.tvXMax);
        tvY = (TextView) findViewById(R.id.tvYMax);

        mSeekBarX = (SeekBar) findViewById(R.id.seekBar1);
        mSeekBarX.setOnSeekBarChangeListener(this);

        mSeekBarY = (SeekBar) findViewById(R.id.seekBar2);
        mSeekBarY.setOnSeekBarChangeListener(this);

        mBarChart = (BarChart) findViewById(R.id.chart2);
        mBarChart.setOnChartValueSelectedListener(this);

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
        mSeekBarX.setProgress(12);
        mSeekBarY.setProgress(100);

        Legend l = mBarChart.getLegend();
        l.setPosition(Legend.LegendPosition.BELOW_CHART_RIGHT);
        l.setFormSize(8f);
        l.setFormToTextSpace(4f);
        l.setXEntrySpace(6f);
    }*/

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

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pie, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(pieChartActive) {
            switch (item.getItemId()) {
                case R.id.actionToggleValues: {
                    for (IDataSet<?> set : mChart.getData().getDataSets())
                        set.setDrawValues(!set.isDrawValuesEnabled());

                    mChart.invalidate();
                    break;
                }
                case R.id.actionToggleHole: {
                    if (mChart.isDrawHoleEnabled())
                        mChart.setDrawHoleEnabled(false);
                    else
                        mChart.setDrawHoleEnabled(true);
                    mChart.invalidate();
                    break;
                }
                case R.id.actionDrawCenter: {
                    if (mChart.isDrawCenterTextEnabled())
                        mChart.setDrawCenterText(false);
                    else
                        mChart.setDrawCenterText(true);
                    mChart.invalidate();
                    break;
                }
                case R.id.actionToggleXVals: {

                    mChart.setDrawSliceText(!mChart.isDrawSliceTextEnabled());
                    mChart.invalidate();
                    break;
                }
                case R.id.actionSave: {
                    // mChart.saveToGallery("title"+System.currentTimeMillis());
                    mChart.saveToPath("title" + System.currentTimeMillis(), "");
                    break;
                }
                case R.id.actionTogglePercent:
                    mChart.setUsePercentValues(!mChart.isUsePercentValuesEnabled());
                    mChart.invalidate();
                    break;
                case R.id.animateX: {
                    mChart.animateX(1400);
                    break;
                }
                case R.id.animateY: {
                    mChart.animateY(1400);
                    break;
                }
                case R.id.animateXY: {
                    mChart.animateXY(1400, 1400);
                    break;
                }
                case R.id.actionToggleSpin: {
                    mChart.spin(1000, mChart.getRotationAngle(), mChart.getRotationAngle() + 360, Easing.EasingOption.EaseInCubic);
                    break;
                }
            }
        }else{
            switch (item.getItemId()) {
                case R.id.actionToggleValues: {
                    List<IBarDataSet> sets = mBarChart.getData()
                            .getDataSets();

                    for (IBarDataSet iSet : sets) {

                        BarDataSet set = (BarDataSet) iSet;
                        set.setDrawValues(!set.isDrawValuesEnabled());
                    }

                    mBarChart.invalidate();
                    break;
                }
                case R.id.actionToggleHighlight: {
                    if(mBarChart.getData() != null) {
                        mBarChart.getData().setHighlightEnabled(!mChart.getData().isHighlightEnabled());
                        mBarChart.invalidate();
                    }
                    break;
                }
                case R.id.actionTogglePinch: {
                    if (mBarChart.isPinchZoomEnabled())
                        mBarChart.setPinchZoom(false);
                    else
                        mBarChart.setPinchZoom(true);

                    mBarChart.invalidate();
                    break;
                }
                case R.id.actionToggleAutoScaleMinMax: {
                    mBarChart.setAutoScaleMinMaxEnabled(!mBarChart.isAutoScaleMinMaxEnabled());
                    mBarChart.notifyDataSetChanged();
                    break;
                }
                case R.id.actionToggleBarBorders: {
                    for (IBarDataSet set : mBarChart.getData().getDataSets())
                        ((BarDataSet)set).setBarBorderWidth(set.getBarBorderWidth() == 1.f ? 0.f : 1.f);

                    mChart.invalidate();
                    break;
                }
                case R.id.actionToggleHighlightArrow: {
                    if (mBarChart.isDrawHighlightArrowEnabled())
                        mBarChart.setDrawHighlightArrow(false);
                    else
                        mBarChart.setDrawHighlightArrow(true);
                    mBarChart.invalidate();
                    break;
                }
                case R.id.animateX: {
                    mBarChart.animateX(3000);
                    break;
                }
                case R.id.animateY: {
                    mBarChart.animateY(3000);
                    break;
                }
                case R.id.animateXY: {

                    mBarChart.animateXY(3000, 3000);
                    break;
                }
                case R.id.actionSave: {
                    if (mBarChart.saveToGallery("title" + System.currentTimeMillis(), 50)) {
                        Toast.makeText(getApplicationContext(), "Saving SUCCESSFUL!", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(getApplicationContext(), "Saving FAILED!", Toast.LENGTH_SHORT).show();
                    break;
                }
            }
        }
        return true;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if(pieChartActive) {
            tvX.setText("" + (mSeekBarX.getProgress() + 1));
            tvY.setText("" + (mSeekBarY.getProgress()));

            setData(mSeekBarX.getProgress(), mSeekBarY.getProgress());
        }else{
            tvX.setText("" + (mSeekBarX.getProgress() + 1));
            tvY.setText("" + (mSeekBarY.getProgress()));

            ArrayList<String> xVals = new ArrayList<String>();
            for (int i = 0; i < mSeekBarX.getProgress() + 1; i++) {
                xVals.add(mMonths[i % mMonths.length]);
            }

            ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

            for (int i = 0; i < mSeekBarX.getProgress() + 1; i++) {
                float mult = (mSeekBarY.getProgress() + 1);
                float val1 = (float) (Math.random() * mult) + mult / 3;
                float val2 = (float) (Math.random() * mult) + mult / 3;
                float val3 = (float) (Math.random() * mult) + mult / 3;

                yVals1.add(new BarEntry(new float[] { val1, val2, val3 }, i));
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
                set1 = new BarDataSet(yVals1, "Statistics Vienna 2014");
                set1.setColors(getColors());
                set1.setStackLabels(new String[]{"Births", "Divorces", "Marriages"});

                ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
                dataSets.add(set1);

                BarData data = new BarData(xVals, dataSets);
                data.setValueFormatter(new MyValueFormatter());

                mBarChart.setData(data);
            }

            mBarChart.invalidate();
        }
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

    private void setData(int count, float range) {

        float mult = range;
        count = 5;
        range = 100;
        ArrayList<Float> categoryValues = new ArrayList<Float>();
        categoryValues.add(getCategoryPercent("1"));
        categoryValues.add(getCategoryPercent("2"));
        categoryValues.add(getCategoryPercent("3"));
        categoryValues.add(getCategoryPercent("4"));
        categoryValues.add(getCategoryPercent("5"));

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

        PieDataSet dataSet = new PieDataSet(yVals1, "Categorias");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        data.setValueTypeface(tf);
        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);

        mChart.invalidate();
    }

    private SpannableString generateCenterSpannableText() {

        SpannableString s = new SpannableString("MPAndroidChart\ndeveloped by Philipp Jahoda");
        s.setSpan(new RelativeSizeSpan(1.7f), 0, 14, 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 14, s.length() - 15, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 14, s.length() - 15, 0);
        s.setSpan(new RelativeSizeSpan(.8f), 14, s.length() - 15, 0);
        s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 14, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 14, s.length(), 0);
        return s;
    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

        if (e == null)
            return;
        Log.i("VAL SELECTED",
                "Value: " + e.getVal() + ", xIndex: " + e.getXIndex()
                        + ", DataSet index: " + dataSetIndex);
    }

    @Override
    public void onNothingSelected() {
        Log.i("PieChart", "nothing selected");
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub

    }

    private int[] getColors() {

        int stacksize = 3;

        // have as many colors as stack-values per entry
        int[] colors = new int[stacksize];

        for (int i = 0; i < stacksize; i++) {
            colors[i] = ColorTemplate.VORDIPLOM_COLORS[i];
        }

        return colors;
    }*/
}

