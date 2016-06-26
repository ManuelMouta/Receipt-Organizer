package com.example.manuel.receiptorganizer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.manuel.receiptorganizer.activities.ListReceiptsActivity;
import com.example.manuel.receiptorganizer.activities.OverviewReceiptActivity;
import com.example.manuel.receiptorganizer.activities.SaveReceiptActivity;
import com.example.manuel.receiptorganizer.activities.SettingsActivity;
import com.example.manuel.receiptorganizer.database.CategoryOperations;
import com.example.manuel.receiptorganizer.database.ReceiptOperations;
import com.example.manuel.receiptorganizer.objects.CategoryObject;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout addReceiptBtn;

    private RelativeLayout receiptListBtn;

    private RelativeLayout overviewListBtn;

    private ImageView settingsBtn;

    private List<CategoryObject> categories;

    public static CategoryOperations categoryDBoperation;

    static final int REQUEST_TAKE_PHOTO = 0;

    String mCurrentPhotoPath;
    String mtimeStamp;

    public static ReceiptOperations receiptDBoperation;

    public static CategoryOperations categoryBDoperations;

    public static String Category1;

    public static String Category2;

    public static String Category3;

    public static String Category4;

    public static String Category5;

    public static String Category6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addReceiptBtn = (RelativeLayout) findViewById(R.id.new_receipt);
        receiptListBtn = (RelativeLayout) findViewById(R.id.list_receipts_btn);
        overviewListBtn = (RelativeLayout) findViewById(R.id.overview_receipts_btn);
        settingsBtn = (ImageView) findViewById(R.id.settings);

        addReceiptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        receiptListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ListReceiptsActivity.class);
                startActivity(intent);
            }
        });

        overviewListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,OverviewReceiptActivity.class);
                startActivity(intent);
            }
        });

        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SettingsActivity.class);
                startActivity(intent);
            }
        });

        categoryDBoperation = new CategoryOperations(this);
        receiptDBoperation = new ReceiptOperations(this);
        receiptDBoperation.open();
        categoryDBoperation.open();
        loadCategories();
    }

    @Override
    protected void onResume() {
        receiptDBoperation.open();
        categoryDBoperation.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        receiptDBoperation.close();
        categoryDBoperation.close();
        super.onPause();
    }

    @Override
    protected void onStop() {
        receiptDBoperation.close();
        categoryDBoperation.close();
        super.onStop();
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_TAKE_PHOTO && resultCode != RESULT_OK){
            try{
                File file = new File(mCurrentPhotoPath);
                if (file.exists()) {
                    file.delete();
                }
            }catch(Exception e){
                Log.e("ERROR: ",e.toString());
            }
        }
        else if(requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK){
            Intent intent = new Intent(MainActivity.this, SaveReceiptActivity.class);
            intent.putExtra("receipPath",mCurrentPhotoPath);
            intent.putExtra("receipDate",mtimeStamp);
            startActivity(intent);
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        mtimeStamp = new SimpleDateFormat("dd-MM-yy").format(new Date());
        String imageFileName = "Recibo   " + mtimeStamp + "   ";

        File storageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES) +
                File.separator + "Receipts");
        boolean success = true;
        if (!storageDir.exists()) {
            success = storageDir.mkdir();
        }
        if (success) {
            // Do something on success
        } else {
            // Do something else on failure
        }

        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void loadCategories(){
        boolean DBSet = false;
        categories = categoryDBoperation.getAllCategories();
        if(categories.isEmpty()){
            DBSet = true;
        }else{
            DBSet = false;
        }

        if(DBSet){
            Category1 = "Food";
            Category2 = "House";
            Category3 = "Bills";
            Category4 = "Health";
            Category5 = "Stuff";
            Category6 = "Other";
            categoryDBoperation.addCategory("1",Category1);
            categoryDBoperation.addCategory("2",Category2);
            categoryDBoperation.addCategory("3",Category3);
            categoryDBoperation.addCategory("4",Category4);
            categoryDBoperation.addCategory("5",Category5);
            categoryDBoperation.addCategory("6",Category6);
        }
        else{
            for(int i=0;i<categories.size();i++){
                if(categories.get(i).getCategoryValue().equals("1"))
                    Category1 = categories.get(i).getCategoryName();
                if(categories.get(i).getCategoryValue().equals("2"))
                    Category2 = categories.get(i).getCategoryName();
                if(categories.get(i).getCategoryValue().equals("3"))
                    Category3 = categories.get(i).getCategoryName();
                if(categories.get(i).getCategoryValue().equals("4"))
                    Category4 = categories.get(i).getCategoryName();
                if(categories.get(i).getCategoryValue().equals("5"))
                    Category5 = categories.get(i).getCategoryName();
                if(categories.get(i).getCategoryValue().equals("6"))
                    Category6 = categories.get(i).getCategoryName();
            }
        }
    }
}
