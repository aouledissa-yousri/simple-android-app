package com.example.contactsmanager.datasources;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SQLiteDatasource extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "contacts.db";
    private static final int DATABASE_VERSION = 1;

    private final String TABLE_NAME;
    private final String[] COLUMNS;
    private final String CREATE_TABLE_QUERY;


    public SQLiteDatasource(Context context, String tableName, String[] columns, String createTableQuery) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        TABLE_NAME = tableName;
        COLUMNS = columns;
        CREATE_TABLE_QUERY = createTableQuery;

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long insert(ContentValues values) {
        SQLiteDatabase db = getWritableDatabase();
        return db.insert(TABLE_NAME, null, values);
    }

    public Cursor query(String selection, String[] selectionArgs) {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(TABLE_NAME, COLUMNS, selection, selectionArgs, null, null, null);
    }

    public Cursor getAllRecords() {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(TABLE_NAME, COLUMNS, null, null, null, null, null);
    }

    public void closeDB() {
        this.close();
    }
}
