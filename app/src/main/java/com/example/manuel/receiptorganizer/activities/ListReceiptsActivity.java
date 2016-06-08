package com.example.manuel.receiptorganizer.activities;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.manuel.receiptorganizer.R;
import com.example.manuel.receiptorganizer.utills.ReceiptsListAdapter;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by nb21910 on 20/05/16.
 */
public class ListReceiptsActivity extends AppCompatActivity{

    private RecyclerView receiptsList;
    private ArrayList<String> receipts;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Button categoryBtnClr;
    private Button categoryBtn1;
    private Button categoryBtn2;
    private Button categoryBtn3;
    private Button categoryBtn4;
    private Button categoryBtn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_receipts);

        receiptsList = (RecyclerView) findViewById(R.id.receipts_list);
        categoryBtnClr = (Button) findViewById(R.id.category);
        categoryBtn1 = (Button) findViewById(R.id.category1);
        categoryBtn2 = (Button) findViewById(R.id.category2);
        categoryBtn3 = (Button) findViewById(R.id.category3);
        categoryBtn4 = (Button) findViewById(R.id.category4);
        categoryBtn5 = (Button) findViewById(R.id.category5);

        mLayoutManager = new LinearLayoutManager(this);
        receiptsList.setLayoutManager(mLayoutManager);

        mAdapter = new ReceiptsListAdapter(ListReceiptsActivity.this,"0");
        receiptsList.setAdapter(mAdapter);

        categoryBtnClr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter = new ReceiptsListAdapter(ListReceiptsActivity.this,"0");
                receiptsList.setAdapter(mAdapter);
            }
        });
        categoryBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter = new ReceiptsListAdapter(ListReceiptsActivity.this,"1");
                receiptsList.setAdapter(mAdapter);
            }
        });
        categoryBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter = new ReceiptsListAdapter(ListReceiptsActivity.this,"2");
                receiptsList.setAdapter(mAdapter);
            }
        });
        categoryBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter = new ReceiptsListAdapter(ListReceiptsActivity.this,"3");
                receiptsList.setAdapter(mAdapter);
            }
        });
        categoryBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter = new ReceiptsListAdapter(ListReceiptsActivity.this,"4");
                receiptsList.setAdapter(mAdapter);
            }
        });
        categoryBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter = new ReceiptsListAdapter(ListReceiptsActivity.this,"5");
                receiptsList.setAdapter(mAdapter);
            }
        });

    }
}
