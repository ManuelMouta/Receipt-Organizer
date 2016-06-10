package com.example.manuel.receiptorganizer.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.manuel.receiptorganizer.R;

/**
 * Created by nb21910 on 10/06/16.
 */
public class AnnualResumeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.barchartfragment, container, false);
    }
}
