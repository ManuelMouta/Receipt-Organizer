package com.example.manuel.receiptorganizer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.manuel.receiptorganizer.R;

/**
 * Created by nb21910 on 22/05/16.
 */
public class SaveReceiptActivity extends AppCompatActivity {
    private LinearLayout btn1;
    private LinearLayout btn2;
    private LinearLayout btn3;
    private LinearLayout btn4;
    private LinearLayout btn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save_receipt);

        btn1 = (LinearLayout) findViewById(R.id.type1);
        btn2 = (LinearLayout) findViewById(R.id.type2);
        btn3 = (LinearLayout) findViewById(R.id.type3);
        btn4 = (LinearLayout) findViewById(R.id.type4);
        btn5 = (LinearLayout) findViewById(R.id.type5);
        Intent intent = getIntent();
        final String receiptPath = intent.getStringExtra("receipPath");

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SaveReceiptActivity.this,SaveReceiptDetailActivity.class);
                intent.putExtra("receiptPath",receiptPath);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SaveReceiptActivity.this,SaveReceiptDetailActivity.class);
                intent.putExtra("receiptPath",receiptPath);
                startActivity(intent);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SaveReceiptActivity.this,SaveReceiptDetailActivity.class);
                intent.putExtra("receiptPath",receiptPath);
                startActivity(intent);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SaveReceiptActivity.this,SaveReceiptDetailActivity.class);
                intent.putExtra("receiptPath",receiptPath);
                startActivity(intent);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SaveReceiptActivity.this,SaveReceiptDetailActivity.class);
                intent.putExtra("receiptPath",receiptPath);
                startActivity(intent);
            }
        });
    }
}
