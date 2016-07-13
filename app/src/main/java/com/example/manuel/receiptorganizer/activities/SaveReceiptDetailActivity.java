package com.example.manuel.receiptorganizer.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.manuel.receiptorganizer.MainActivity;
import com.example.manuel.receiptorganizer.R;
import com.example.manuel.receiptorganizer.objects.ReceiptObject;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.io.File;

/**
 * Created by nb21910 on 27/05/16.
 */
public class SaveReceiptDetailActivity extends AppCompatActivity{
    private Button saveReceiptBtn;
    private Button discardReceipt;
    private EditText receiptName;
    private EditText total;
    private EditText info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receipt_detail);
        Intent intent = getIntent();
        final String receiptPath = intent.getStringExtra("receiptPath");
        final String receiptDate = intent.getStringExtra("receiptDate");
        final String receiptCategory = intent.getStringExtra("receiptCategory");
        discardReceipt = (Button) findViewById(R.id.discard_receipt);
        receiptName = (EditText) findViewById(R.id.receipt_name_value);
        total = (EditText) findViewById(R.id.receipt_total);
        info = (EditText) findViewById(R.id.receipt_info);

        saveReceiptBtn = (Button) findViewById(R.id.save_receipt);

        discardReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        saveReceiptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    MainActivity.receiptDBoperation.open();
                    MainActivity.receiptDBoperation.addReceipt(
                            receiptName.getText().toString(),receiptCategory,receiptPath,Float.parseFloat((total.getText().toString())),info.getText().toString(),receiptDate);

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }catch (Exception e){
                    Toast.makeText(SaveReceiptDetailActivity.this,e.toString(),Toast.LENGTH_LONG).show();
                    Log.d("Save Error: ",e.toString());
                }
            }
        });
        new LongOperation().execute("");
    }

    private class LongOperation extends AsyncTask<String, Void, String> {
        protected AdRequest adRequest;
        @Override
        protected String doInBackground(String... params) {
            MobileAds.initialize(getApplicationContext(), "ca-app-pub-5964546814777835/4429291305");
            adRequest = new AdRequest.Builder().build();
            return "done";
        }

        @Override
        protected void onPostExecute(String result) {
            if(result.equals("done")) {
                AdView mAdView = (AdView) findViewById(R.id.adView);
                mAdView.loadAd(adRequest);
            }
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }
}


