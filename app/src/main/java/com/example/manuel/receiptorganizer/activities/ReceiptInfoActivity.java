package com.example.manuel.receiptorganizer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.manuel.receiptorganizer.R;

/**
 * Created by nb21910 on 04/06/16.
 */
public class ReceiptInfoActivity extends AppCompatActivity {
    private TextView receiptInfoText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receipt_info);

        Intent intent = getIntent();
        String receiptInfo = intent.getStringExtra("receiptInfo");

        receiptInfoText = (TextView) findViewById(R.id.receipt_info);
        receiptInfoText.setText(receiptInfo);
    }
}
