package com.example.manuel.receiptorganizer.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.manuel.receiptorganizer.MainActivity;
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
    private LinearLayout categoryBtn6;
    private LinearLayout categoryBtn1;
    private LinearLayout categoryBtn2;
    private LinearLayout categoryBtn3;
    private LinearLayout categoryBtn4;
    private LinearLayout categoryBtn5;
    private RelativeLayout header;
    private TextView headerTitle;
    private ImageView headerIcon;
    private TextView showAllBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_receipts);

        receiptsList = (RecyclerView) findViewById(R.id.receipts_list);
        categoryBtn6 = (LinearLayout) findViewById(R.id.category6);
        categoryBtn1 = (LinearLayout) findViewById(R.id.category1);
        categoryBtn2 = (LinearLayout) findViewById(R.id.category2);
        categoryBtn3 = (LinearLayout) findViewById(R.id.category3);
        categoryBtn4 = (LinearLayout) findViewById(R.id.category4);
        categoryBtn5 = (LinearLayout) findViewById(R.id.category5);

        headerIcon = (ImageView) findViewById(R.id.header_icon);
        headerTitle = (TextView) findViewById(R.id.receiptsListHeader);
        header = (RelativeLayout) findViewById(R.id.receiptslist_header);
        showAllBtn = (TextView) findViewById(R.id.showallbtn);

        headerTitle.setText("All");
        showAllBtn.setVisibility(View.INVISIBLE);
        header.setBackgroundColor(getResources().getColor(R.color.BurlyWood));
        headerIcon.setImageResource(R.drawable.file);

        mLayoutManager = new LinearLayoutManager(this);
        receiptsList.setLayoutManager(mLayoutManager);

        mAdapter = new ReceiptsListAdapter(ListReceiptsActivity.this,"0");
        receiptsList.setAdapter(mAdapter);

        showAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter = new ReceiptsListAdapter(ListReceiptsActivity.this,"0");
                receiptsList.setAdapter(mAdapter);
                changeHeader("0");
                showAllBtn.setVisibility(View.INVISIBLE);
                headerIcon.setImageResource(R.drawable.file);
            }
        });
        categoryBtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter = new ReceiptsListAdapter(ListReceiptsActivity.this,"6");
                receiptsList.setAdapter(mAdapter);
                changeHeader("6");
                showAllBtn.setVisibility(View.VISIBLE);
                headerIcon.setImageResource(R.drawable.file);
            }
        });
        categoryBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter = new ReceiptsListAdapter(ListReceiptsActivity.this,"1");
                receiptsList.setAdapter(mAdapter);
                changeHeader("1");
                showAllBtn.setVisibility(View.VISIBLE);
                headerIcon.setImageResource(R.drawable.food);
            }
        });
        categoryBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter = new ReceiptsListAdapter(ListReceiptsActivity.this,"2");
                receiptsList.setAdapter(mAdapter);
                changeHeader("2");
                showAllBtn.setVisibility(View.VISIBLE);
                headerIcon.setImageResource(R.drawable.house);
            }
        });
        categoryBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter = new ReceiptsListAdapter(ListReceiptsActivity.this,"3");
                receiptsList.setAdapter(mAdapter);
                changeHeader("3");
                showAllBtn.setVisibility(View.VISIBLE);
                headerIcon.setImageResource(R.drawable.bills);
            }
        });
        categoryBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter = new ReceiptsListAdapter(ListReceiptsActivity.this,"4");
                receiptsList.setAdapter(mAdapter);
                changeHeader("4");
                showAllBtn.setVisibility(View.VISIBLE);
                headerIcon.setImageResource(R.drawable.health);
            }
        });
        categoryBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter = new ReceiptsListAdapter(ListReceiptsActivity.this,"5");
                receiptsList.setAdapter(mAdapter);
                changeHeader("5");
                showAllBtn.setVisibility(View.VISIBLE);
                headerIcon.setImageResource(R.drawable.stuff);
            }
        });

    }

    public void changeHeader(String category){
        if(category.equals("0")){
            headerTitle.setText("All");
            header.setBackgroundColor(getResources().getColor(R.color.BurlyWood));
        }else if(category.equals("1")){
            headerTitle.setText(MainActivity.Category1);
            header.setBackgroundColor(getResources().getColor(R.color.category1));
        }else if(category.equals("2")){
            headerTitle.setText(MainActivity.Category2);
            header.setBackgroundColor(getResources().getColor(R.color.category2));
        }else if(category.equals("3")){
            headerTitle.setText(MainActivity.Category3);
            header.setBackgroundColor(getResources().getColor(R.color.category3));
        }else if(category.equals("4")){
            headerTitle.setText(MainActivity.Category4);
            header.setBackgroundColor(getResources().getColor(R.color.category4));
        }else if(category.equals("5")){
            headerTitle.setText(MainActivity.Category5);
            header.setBackgroundColor(getResources().getColor(R.color.category5));
        }else if(category.equals("6")){
            headerTitle.setText(MainActivity.Category5);
            header.setBackgroundColor(getResources().getColor(R.color.category6));
        }
    }
}
