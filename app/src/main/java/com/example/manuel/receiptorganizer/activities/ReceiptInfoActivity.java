package com.example.manuel.receiptorganizer.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.manuel.receiptorganizer.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.io.File;

/**
 * Created by nb21910 on 04/06/16.
 */
public class ReceiptInfoActivity extends AppCompatActivity {
    private TextView receiptInfoText;
    private Button send_email_btn;
    private TextView showAll;
    private TextView title;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receipt_info);

        Intent intent = getIntent();
        String receiptInfo = intent.getStringExtra("receiptInfo");
        final String path = intent.getStringExtra("path");

        send_email_btn = (Button) findViewById(R.id.send_email_btn);
        receiptInfoText = (TextView) findViewById(R.id.receipt_info);
        receiptInfoText.setText(receiptInfo);
        showAll = (TextView) findViewById(R.id.showallbtn);
        showAll.setVisibility(View.INVISIBLE);
        title = (TextView) findViewById(R.id.receiptsListHeader);
        title.setText("INFO");

        send_email_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "", null));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Recibo");
                intent.putExtra(Intent.EXTRA_TEXT, "Recibo em anexo.");
                File file = new File(path);
                Uri uri = Uri.fromFile(file);
                intent.putExtra(Intent.EXTRA_STREAM, uri);
                startActivity(Intent.createChooser(intent, "Choose an Email client :"));
            }
        });

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-5964546814777835/4429291305");
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
}
