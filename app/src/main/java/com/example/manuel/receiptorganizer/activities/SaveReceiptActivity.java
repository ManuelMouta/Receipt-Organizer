package com.example.manuel.receiptorganizer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.manuel.receiptorganizer.MainActivity;
import com.example.manuel.receiptorganizer.R;

/**
 * Created by nb21910 on 22/05/16.
 */
public class SaveReceiptActivity extends AppCompatActivity {
    private RelativeLayout btn1;
    private RelativeLayout btn2;
    private RelativeLayout btn3;
    private RelativeLayout btn4;
    private RelativeLayout btn5;
    private RelativeLayout btn6;
    private TextView btn1Label;
    private TextView btn2Label;
    private TextView btn3Label;
    private TextView btn4Label;
    private TextView btn5Label;
    private TextView btn6Label;


    private TextView showAllBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save_receipt);

        btn1 = (RelativeLayout) findViewById(R.id.type1);
        btn2 = (RelativeLayout) findViewById(R.id.type2);
        btn3 = (RelativeLayout) findViewById(R.id.type3);
        btn4 = (RelativeLayout) findViewById(R.id.type4);
        btn5 = (RelativeLayout) findViewById(R.id.type5);
        btn6 = (RelativeLayout) findViewById(R.id.type6);
        btn1Label = (TextView) findViewById(R.id.btn1_label);
        btn2Label = (TextView) findViewById(R.id.btn2_label);
        btn3Label = (TextView) findViewById(R.id.btn3_label);
        btn4Label = (TextView) findViewById(R.id.btn4_label);
        btn5Label = (TextView) findViewById(R.id.btn5_label);
        btn6Label = (TextView) findViewById(R.id.btn6_label);
        btn1Label.setText(MainActivity.Category1);
        btn2Label.setText(MainActivity.Category2);
        btn3Label.setText(MainActivity.Category3);
        btn4Label.setText(MainActivity.Category4);
        btn5Label.setText(MainActivity.Category5);
        btn6Label.setText(MainActivity.Category6);


        showAllBtn = (TextView) findViewById(R.id.showallbtn);
        showAllBtn.setVisibility(View.INVISIBLE);
        Intent intent = getIntent();
        final String receiptPath = intent.getStringExtra("receipPath");
        final String receiptDate = intent.getStringExtra("receipDate");

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SaveReceiptActivity.this,SaveReceiptDetailActivity.class);
                intent.putExtra("receiptPath",receiptPath);
                intent.putExtra("receiptDate",receiptDate);
                intent.putExtra("receiptCategory","1");
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SaveReceiptActivity.this,SaveReceiptDetailActivity.class);
                intent.putExtra("receiptPath",receiptPath);
                intent.putExtra("receiptDate",receiptDate);
                intent.putExtra("receiptCategory","2");
                startActivity(intent);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SaveReceiptActivity.this,SaveReceiptDetailActivity.class);
                intent.putExtra("receiptPath",receiptPath);
                intent.putExtra("receiptDate",receiptDate);
                intent.putExtra("receiptCategory","3");
                startActivity(intent);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SaveReceiptActivity.this,SaveReceiptDetailActivity.class);
                intent.putExtra("receiptPath",receiptPath);
                intent.putExtra("receiptDate",receiptDate);
                intent.putExtra("receiptCategory","4");
                startActivity(intent);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SaveReceiptActivity.this,SaveReceiptDetailActivity.class);
                intent.putExtra("receiptPath",receiptPath);
                intent.putExtra("receiptDate",receiptDate);
                intent.putExtra("receiptCategory","5");
                startActivity(intent);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SaveReceiptActivity.this,SaveReceiptDetailActivity.class);
                intent.putExtra("receiptPath",receiptPath);
                intent.putExtra("receiptDate",receiptDate);
                intent.putExtra("receiptCategory","6");
                startActivity(intent);
            }
        });
    }
}
