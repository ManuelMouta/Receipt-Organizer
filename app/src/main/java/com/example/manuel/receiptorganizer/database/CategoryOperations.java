package com.example.manuel.receiptorganizer.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.manuel.receiptorganizer.objects.CategoryObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nb21910 on 22/06/16.
 */
public class CategoryOperations {

    // Database fields
    private DataBaseWrapper dbHelper;
    private String[] CATEGORY_TABLE_COLUMNS = {
            DataBaseWrapper.CATEGORY_ID,
            DataBaseWrapper.CATEGORY_VALUE,
            DataBaseWrapper.CATEGORY_NAME
    };
    private SQLiteDatabase database;

    public CategoryOperations(Context context) {
        dbHelper = new DataBaseWrapper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public CategoryObject addCategory(String category_value, String category_name) {

        ContentValues values = new ContentValues();

        values.put(DataBaseWrapper.CATEGORY_VALUE, category_value);
        values.put(DataBaseWrapper.CATEGORY_NAME, category_name);


        long categorytId = database.insert(DataBaseWrapper.CATEGORIES, null, values);

        Cursor cursor = database.query(DataBaseWrapper.CATEGORIES,
                CATEGORY_TABLE_COLUMNS, DataBaseWrapper.CATEGORY_ID + " = "
                        + categorytId, null, null, null, null);

        cursor.moveToFirst();

        CategoryObject newComment = parseCategory(cursor);
        cursor.close();
        return newComment;
    }

    public void updateCategoryName(String categoryValue, String categoryName){
        ContentValues cv = new ContentValues();
        cv.put(DataBaseWrapper.CATEGORY_NAME,categoryName);

        database.update(DataBaseWrapper.CATEGORIES,cv,DataBaseWrapper.CATEGORY_VALUE + " = "
            + categoryValue,null);
    }

    public void deleteCategory(CategoryObject comment) {
        long id = comment.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(DataBaseWrapper.CATEGORIES, DataBaseWrapper.CATEGORY_ID
                + " = " + id, null);
    }

    public List getAllCategories() {
        List categories = new ArrayList();

        Cursor cursor = database.query(DataBaseWrapper.CATEGORIES,
                CATEGORY_TABLE_COLUMNS, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            CategoryObject categoriy = parseCategory(cursor);
            categories.add(categoriy);
            cursor.moveToNext();
        }

        cursor.close();
        return categories;
    }

    private CategoryObject parseCategory(Cursor cursor) {
        CategoryObject category = new CategoryObject();
        category.setId((cursor.getInt(0)));
        category.setCategoryValue(cursor.getString(1));
        category.setCategoryName(cursor.getString(2));

        return category;
    }

}
