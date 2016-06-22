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

import com.example.manuel.receiptorganizer.activities.ListReceiptsActivity;
import com.example.manuel.receiptorganizer.activities.OverviewReceiptActivity;
import com.example.manuel.receiptorganizer.activities.SaveReceiptActivity;
import com.example.manuel.receiptorganizer.activities.SettingsActivity;
import com.example.manuel.receiptorganizer.database.ReceiptOperations;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button addReceiptBtn;

    private Button receiptListBtn;

    private Button overviewListBtn;

    private Button settingsBtn;

    static final int REQUEST_TAKE_PHOTO = 0;

    String mCurrentPhotoPath;
    String mtimeStamp;

    public static ReceiptOperations receiptDBoperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addReceiptBtn = (Button) findViewById(R.id.new_receipt);
        receiptListBtn = (Button) findViewById(R.id.list_receipts_btn);
        overviewListBtn = (Button) findViewById(R.id.overview_receipts_btn);
        settingsBtn = (Button) findViewById(R.id.settings);

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

        receiptDBoperation = new ReceiptOperations(this);
        receiptDBoperation.open();
    }

    @Override
    protected void onResume() {
        receiptDBoperation.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        receiptDBoperation.close();
        super.onPause();
    }

    @Override
    protected void onStop() {
        receiptDBoperation.close();
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


}
