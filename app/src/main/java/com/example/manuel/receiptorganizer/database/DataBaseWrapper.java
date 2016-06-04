package com.example.manuel.receiptorganizer.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nb21910 on 04/06/16.
 */
public class DataBaseWrapper extends SQLiteOpenHelper {

    public static final String RECEIPTS = "Receipts";
    public static final String RECEIPT_ID = "_id";
    public static final String RECEIPT_NAME = "_name";
    public static final String RECEIPT_PATH = "_path";
    public static final String RECEIPT_CATEGORY = "_category";
    public static final String RECEIPT_TOTAL = "_total";
    public static final String RECEIPT_INFO = "_info";

    private static final String DATABASE_NAME = "Receipts.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "create table " + RECEIPTS
            + "(" + RECEIPT_ID + " integer primary key autoincrement, "
            + RECEIPT_NAME + " text not null,"
            + RECEIPT_PATH + " text not null,"
            + RECEIPT_CATEGORY + " text not null,"
            + RECEIPT_TOTAL + " integer,"
            + RECEIPT_INFO + " text);";

    public DataBaseWrapper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DataBaseWrapper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DataBaseWrapper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + RECEIPTS);
        onCreate(db);
    }
}
