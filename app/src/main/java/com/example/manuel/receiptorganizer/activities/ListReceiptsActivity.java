package com.example.manuel.receiptorganizer.activities;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_receipts);

        receiptsList = (RecyclerView) findViewById(R.id.receipts_list);
        /*receipts = new ArrayList<String>();

        String path = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES).toString()+"/Receipts";
        File f = new File(path);
        File file[] = f.listFiles();
        for (int i=0; i < file.length; i++)
        {
            receipts.add(file[i].getName());
        }*/

        mLayoutManager = new LinearLayoutManager(this);
        receiptsList.setLayoutManager(mLayoutManager);

        mAdapter = new ReceiptsListAdapter();
        receiptsList.setAdapter(mAdapter);

    }
}
