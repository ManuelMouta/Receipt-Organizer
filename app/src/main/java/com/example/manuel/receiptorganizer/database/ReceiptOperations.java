package com.example.manuel.receiptorganizer.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.manuel.receiptorganizer.objects.ReceiptObject;

/**
 * Created by nb21910 on 04/06/16.
 */
public class ReceiptOperations {

    // Database fields
    private DataBaseWrapper dbHelper;
    private String[] RECEIPT_TABLE_COLUMNS = {
            DataBaseWrapper.RECEIPT_ID,
            DataBaseWrapper.RECEIPT_NAME,
            DataBaseWrapper.RECEIPT_CATEGORY,
            DataBaseWrapper.RECEIPT_PATH,
            DataBaseWrapper.RECEIPT_TOTAL,
            DataBaseWrapper.RECEIPT_INFO
    };
    private SQLiteDatabase database;

    public ReceiptOperations(Context context) {
        dbHelper = new DataBaseWrapper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public ReceiptObject addReceipt(String name,String category,String path,int total,String info) {

        ContentValues values = new ContentValues();

        values.put(DataBaseWrapper.RECEIPT_NAME, name);
        values.put(DataBaseWrapper.RECEIPT_CATEGORY, category);
        values.put(DataBaseWrapper.RECEIPT_PATH, path);
        values.put(DataBaseWrapper.RECEIPT_TOTAL, total);
        values.put(DataBaseWrapper.RECEIPT_INFO, info);

        long receiptId = database.insert(DataBaseWrapper.RECEIPTS, null, values);

        // now that the student is created return it ...
        Cursor cursor = database.query(DataBaseWrapper.RECEIPTS,
                RECEIPT_TABLE_COLUMNS, DataBaseWrapper.RECEIPT_ID + " = "
                        + receiptId, null, null, null, null);

        cursor.moveToFirst();

        ReceiptObject newComment = parseReceipt(cursor);
        cursor.close();
        return newComment;
    }

    public void deleteReceipt(ReceiptObject comment) {
        long id = comment.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(DataBaseWrapper.RECEIPTS, DataBaseWrapper.RECEIPT_ID
                + " = " + id, null);
    }

    public List getAllReceipts() {
        List receipts = new ArrayList();

        Cursor cursor = database.query(DataBaseWrapper.RECEIPTS,
                RECEIPT_TABLE_COLUMNS, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ReceiptObject receipt = parseReceipt(cursor);
            receipts.add(receipt);
            cursor.moveToNext();
        }

        cursor.close();
        return receipts;
    }

    private ReceiptObject parseReceipt(Cursor cursor) {
        ReceiptObject receipt = new ReceiptObject();
        receipt.setId((cursor.getInt(0)));
        receipt.setName(cursor.getString(1));
        receipt.setCategory(cursor.getString(2));
        receipt.setPath(cursor.getString(3));
        receipt.setTotal(cursor.getInt(4));
        receipt.setInfo(cursor.getString(5));

        return receipt;
    }
}
