package com.example.manuel.receiptorganizer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.manuel.receiptorganizer.Activities.AddNewReceiptActivity;

public class MainActivity extends AppCompatActivity {
    private Button addReceiptBtn;
    private Button receipListtBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addReceiptBtn = (Button) findViewById(R.id.new_receipt);
        receipListtBtn = (Button) findViewById(R.id.list_receipts_btn);

        addReceiptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddNewReceiptActivity.class);
                startActivity(intent);
            }
        });
    }
}
