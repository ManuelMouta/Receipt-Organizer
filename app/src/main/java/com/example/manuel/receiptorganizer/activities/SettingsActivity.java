package com.example.manuel.receiptorganizer.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.manuel.receiptorganizer.MainActivity;
import com.example.manuel.receiptorganizer.R;
import com.example.manuel.receiptorganizer.database.CategoryOperations;
import com.example.manuel.receiptorganizer.objects.CategoryObject;

import java.util.List;

/**
 * Created by nb21910 on 21/06/16.
 */
public class SettingsActivity extends AppCompatActivity {

    private EditText category1Value;

    private EditText category2Value;

    private EditText category3Value;

    private EditText category4Value;

    private EditText category5Value;

    private EditText category6Value;

    private Spinner currency;

    private List<CategoryObject> categories;

    public static CategoryOperations categoryDBoperation;

    private Button saveBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        category1Value = (EditText) findViewById(R.id.category1);

        category2Value = (EditText) findViewById(R.id.category2);

        category3Value = (EditText) findViewById(R.id.category3);

        category4Value = (EditText) findViewById(R.id.category4);

        category5Value = (EditText) findViewById(R.id.category5);

        category6Value = (EditText) findViewById(R.id.category6);

        saveBtn = (Button) findViewById(R.id.save_btn);

        currency = (Spinner) findViewById(R.id.currency);

        setLabels();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryDBoperation = new CategoryOperations(SettingsActivity.this);
                categoryDBoperation.open();
                categories = categoryDBoperation.getAllCategories();

                for(int i=0;i<categories.size();i++){
                    if(i==0) {
                        categoryDBoperation.updateCategoryName(Integer.toString(i + 1), category1Value.getText().toString());
                        MainActivity.Category1 = category1Value.getText().toString();
                    }
                    if(i==1) {
                        categoryDBoperation.updateCategoryName(Integer.toString(i + 1), category2Value.getText().toString());
                        MainActivity.Category2 = category2Value.getText().toString();
                    }
                    if(i==2) {
                        categoryDBoperation.updateCategoryName(Integer.toString(i + 1), category3Value.getText().toString());
                        MainActivity.Category3 = category3Value.getText().toString();
                    }
                    if(i==3) {
                        categoryDBoperation.updateCategoryName(Integer.toString(i + 1), category4Value.getText().toString());
                        MainActivity.Category4 = category4Value.getText().toString();
                    }
                    if(i==4) {
                        categoryDBoperation.updateCategoryName(Integer.toString(i + 1), category5Value.getText().toString());
                        MainActivity.Category5 = category5Value.getText().toString();
                    }
                    if(i==5) {
                        categoryDBoperation.updateCategoryName(Integer.toString(i + 1), category6Value.getText().toString());
                        MainActivity.Category6 = category6Value.getText().toString();
                    }
                }

                SharedPreferences sharedPref = SettingsActivity.this.getSharedPreferences("currency",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("currency", currency.getSelectedItem().toString());
                editor.commit();
                MainActivity.currency = currency.getSelectedItem().toString();

                finish();
            }
        });

        if(MainActivity.currency.equals("Euros"))
            currency.setSelection(0);
        else if(MainActivity.currency.equals("Dollars"))
            currency.setSelection(1);
        else if(MainActivity.currency.equals("Pounds"))
            currency.setSelection(2);
    }

    private void setLabels(){
        category1Value.setText(MainActivity.Category1);
        category2Value.setText(MainActivity.Category2);
        category3Value.setText(MainActivity.Category3);
        category4Value.setText(MainActivity.Category4);
        category5Value.setText(MainActivity.Category5);
        category6Value.setText(MainActivity.Category6);
    }
}
