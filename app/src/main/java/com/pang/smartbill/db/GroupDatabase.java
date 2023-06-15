package com.pang.smartbill.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class GroupDatabase extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "SmartBill";
    private static final String TABLE_GROUPS = "grouptb";
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "grouptitle";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_CURRENCY = "currency";

    public GroupDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_GROUPS_TABLE = "CREATE TABLE " + TABLE_GROUPS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_TITLE + " varchar(20),"
                + KEY_DESCRIPTION + " varchar(80),"
                + KEY_CATEGORY + " varchar(20),"
                + KEY_CURRENCY + " varchar(20)"
                + ")";
        db.execSQL(CREATE_GROUPS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GROUPS);
        onCreate(db);
    }

    public long addGroup(GroupBean group) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, group.getGrouptitle());
        values.put(KEY_DESCRIPTION, group.getDescription());
        values.put(KEY_CATEGORY, group.getCategory());
        values.put(KEY_CURRENCY, group.getCurrency());
        long id = db.insert(TABLE_GROUPS, null, values);
        db.close();
        return id;
    }

    public void updateGroup(GroupBean group) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, group.getGrouptitle());
        values.put(KEY_DESCRIPTION, group.getDescription());
        values.put(KEY_CATEGORY, group.getCategory());
        values.put(KEY_CURRENCY, group.getCurrency());
        db.update(TABLE_GROUPS, values, KEY_ID + "=?", new String[]{String.valueOf(group.getId())});
        db.close();
    }

    public void deleteGroup(long groupId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_GROUPS, KEY_ID + "=?", new String[]{String.valueOf(groupId)});
        db.close();
    }

    public List<GroupBean> getAllGroups() {
        List<GroupBean> groupList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_GROUPS, null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") long id = cursor.getLong(cursor.getColumnIndex(KEY_ID));
                @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex(KEY_TITLE));
                @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION));
                @SuppressLint("Range") String category = cursor.getString(cursor.getColumnIndex(KEY_CATEGORY));
                @SuppressLint("Range") String currency = cursor.getString(cursor.getColumnIndex(KEY_CURRENCY));

                GroupBean group = new GroupBean(id, title, description, category, currency);
                group.setCurrency(currency);
                groupList.add(group);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return groupList;
    }
}
