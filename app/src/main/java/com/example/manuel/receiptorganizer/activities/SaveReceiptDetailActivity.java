package com.example.manuel.receiptorganizer.activities;

import android.content.Intent;
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

import java.io.File;

/**
 * Created by nb21910 on 27/05/16.
 */
public class SaveReceiptDetailActivity extends AppCompatActivity{
    private Button saveReceiptBtn;
    private EditText receiptName;
    private EditText total;
    private EditText info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receipt_detail);
        Intent intent = getIntent();
        final String receiptPath = intent.getStringExtra("receiptPath");
        receiptName = (EditText) findViewById(R.id.receipt_name_value);
        total = (EditText) findViewById(R.id.receipt_total);
        info = (EditText) findViewById(R.id.receipt_info);

        saveReceiptBtn = (Button) findViewById(R.id.save_receipt);

        saveReceiptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    /*String name_old_jpg = receiptPath.substring(receiptPath.lastIndexOf("/")+1);
                    String name_old = name_old_jpg.substring(0,name_old_jpg.lastIndexOf("."));
                    File from = new File(receiptPath, name_old);
                    File to = new File(Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_PICTURES) +
                            File.separator + "Receipts", receiptName.getText().toString());
                    from.renameTo(to);*/
                    //get file
                    MainActivity.receiptDBoperation.open();
                    MainActivity.receiptDBoperation.addReceipt(
                            receiptName.getText().toString(),"Compras",receiptPath,Integer.parseInt((total.getText().toString())),info.getText().toString());

                    finish();
                }catch (Exception e){
                    Toast.makeText(SaveReceiptDetailActivity.this,e.toString(),Toast.LENGTH_LONG).show();
                    Log.d("Save Error: ",e.toString());
                }
            }
        });
    }
}
