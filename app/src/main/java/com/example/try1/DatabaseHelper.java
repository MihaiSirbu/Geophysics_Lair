package com.example.try1;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;

    private static final String DATABASE_NAME = "ProjectDatabaseTest";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "table1";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_OPERATOR = "operator";
    private static final String COLUMN_PROFILE = "profile";
    private static final String COLUMN_SHOTNUMBER = "shotnumber";
    private static final String COLUMN_TIME = "time";


    public DatabaseHelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context = context;
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_OPERATOR + " TEXT, " +
                COLUMN_PROFILE + " TEXT, " +
                COLUMN_SHOTNUMBER + " TEXT, " +
                COLUMN_TIME + " TEXT);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);

    }
    //shotNumber string
    public int addShot(String operator, String profile, String shotNumber, String time){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_OPERATOR,operator);
        contentValues.put(COLUMN_PROFILE,profile);
        contentValues.put(COLUMN_SHOTNUMBER,shotNumber);
        contentValues.put(COLUMN_TIME,time);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result == -1){
            Toast.makeText(context,"Failed to add to db", Toast.LENGTH_SHORT).show();
            return -1;
        }
        else{
            Toast.makeText(context,"added to db successfully", Toast.LENGTH_SHORT).show();
            return 1;
        }

    }


}