package com.example.manuel.receiptorganizer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.manuel.receiptorganizer.R;

import java.io.File;

/**
 * Created by nb21910 on 27/05/16.
 */
public class SaveReceiptDetailActivity extends AppCompatActivity{
    private Button saveReceiptBtn;
    private EditText receiptName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receipt_detail);
        Intent intent = getIntent();
        final String receiptPath = intent.getStringExtra("receiptPath");
        receiptName = (EditText) findViewById(R.id.receipt_name_value);

        saveReceiptBtn = (Button) findViewById(R.id.save_receipt);

        saveReceiptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File from = new File(receiptPath);
                String Path = receiptPath.substring(0, receiptPath.lastIndexOf("/"));
                File to = new File(Path,receiptName.toString());
                from.renameTo(to);
                finish();
            }
        });
    }
}

