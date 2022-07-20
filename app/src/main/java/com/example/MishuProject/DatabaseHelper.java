package com.example.MishuProject;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;

    private static final String DATABASE_NAME = "ProjectDatabaseTest";
    private static final int DATABASE_VERSION = 1;




    public DatabaseHelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context = context;
    }




    @Override
    public void onCreate(SQLiteDatabase db) {

        // SHOTS TABLE
        String queryTableShots = "CREATE TABLE " + DatabaseContract.ShotTABLE.TABLE_NAME + " (" +
                DatabaseContract.ShotTABLE.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DatabaseContract.ShotTABLE.COLUMN_OPERATOR + " TEXT, " +
                DatabaseContract.ShotTABLE.COLUMN_PROFILE + " TEXT, " +
                DatabaseContract.ShotTABLE.COLUMN_STARTTIME + " TEXT, " +

                // to remove as comment when LATITUDE AND LONGITUDE GETS IMPLEMENTED

                //DatabaseContract.ShotTABLE.COLUMN_LATITUDE + " TEXT, "+
                //DatabaseContract.ShotTABLE.COLUMN_LONGITUDE + " TEXT, "+
                DatabaseContract.ShotTABLE.COLUMN_SHOTNUMBER + " TEXT, "+
                DatabaseContract.ShotTABLE.COLUMN_TIME + " TEXT);";
        db.execSQL(queryTableShots);





        // FORMS TABLE
        String queryTableForms = "CREATE TABLE " +
                DatabaseContract.FormsTABLE.TABLE_NAME + " ("+
                DatabaseContract.FormsTABLE.COLUMN_OPERATOR + " TEXT, " +
                DatabaseContract.FormsTABLE.COLUMN_PROFILE + " TEXT, " +
                DatabaseContract.FormsTABLE.COLUMN_STARTTIME + " TEXT, " +

                // SOL TEREN

                DatabaseContract.FormsTABLE.COLUMN_FOLOSINTA_TEREN + " TEXT, "+
                DatabaseContract.FormsTABLE.COLUMN_FOLOSINTA_TEREN_OTHER + " TEXT, "+
                DatabaseContract.FormsTABLE.COLUMN_CULTURA + " TEXT, "+
                DatabaseContract.FormsTABLE.COLUMN_CULTURA_OTHER + " TEXT, "+
                DatabaseContract.FormsTABLE.COLUMN_TIP_SOL + " TEXT, "+
                DatabaseContract.FormsTABLE.COLUMN_SUPRAFATA + " TEXT, "+
                DatabaseContract.FormsTABLE.COLUMN_SUPRAFATA_OTHER + " TEXT, "+
                DatabaseContract.FormsTABLE.COLUMN_USCAT + " TEXT, "+
                DatabaseContract.FormsTABLE.COLUMN_PRIZA + " TEXT, "+

                        // ZGOMOT
                DatabaseContract.FormsTABLE.COLUMN_VANT + " TEXT, "+
                DatabaseContract.FormsTABLE.COLUMN_TRAFIC + " TEXT, "+
                        // FORAJ
                DatabaseContract.FormsTABLE.COLUMN_LOCALIZAT + " TEXT, "+
                DatabaseContract.FormsTABLE.COLUMN_PICHET + " TEXT, "+
                DatabaseContract.FormsTABLE.COLUMN_DISTANCE + " TEXT, "+
                        // OTHER
                DatabaseContract.FormsTABLE.COLUMN_OTHER_COMMENTS + " TEXT);";







        db.execSQL(queryTableForms);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ DatabaseContract.ShotTABLE.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ DatabaseContract.FormsTABLE.TABLE_NAME);
        onCreate(db);

    }
    //shotNumber string
    public int addShot(String operator, String profile, String shotNumber, String time,String profileST){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(DatabaseContract.ShotTABLE.COLUMN_OPERATOR,operator);
        contentValues.put(DatabaseContract.ShotTABLE.COLUMN_PROFILE,profile);
        contentValues.put(DatabaseContract.ShotTABLE.COLUMN_SHOTNUMBER,shotNumber);
        contentValues.put(DatabaseContract.ShotTABLE.COLUMN_TIME,time);
        contentValues.put(DatabaseContract.ShotTABLE.COLUMN_STARTTIME,profileST);

        //contentValues.put(DatabaseContract.ShotTABLE.COLUMN_LATITUDE,profileST);
        //contentValues.put(DatabaseContract.ShotTABLE.COLUMN_LONGITUDE,profileST);
        long result = db.insert(DatabaseContract.ShotTABLE.TABLE_NAME,null,contentValues);
        if(result == -1){
            Toast.makeText(context,"Failed to add to db", Toast.LENGTH_SHORT).show();
            return -1;
        }
        else{
            Toast.makeText(context,"added to db successfully", Toast.LENGTH_SHORT).show();
            return 1;
        }

    }

    public int addForms(String operator, String profile,String profileST,String folosintaTeren,String folosintaTerenOther, String cultura,String culturaOther, String tipSol, String suprafata,String suprafataOther, String uscat, String priza, String vant, String trafic, String localizat, String pichet, String distance, String otherComments){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(DatabaseContract.FormsTABLE.COLUMN_OPERATOR,operator);
        contentValues.put(DatabaseContract.FormsTABLE.COLUMN_PROFILE,profile);
        contentValues.put(DatabaseContract.FormsTABLE.COLUMN_STARTTIME,profileST);
        contentValues.put(DatabaseContract.FormsTABLE.COLUMN_FOLOSINTA_TEREN,folosintaTeren);
        contentValues.put(DatabaseContract.FormsTABLE.COLUMN_FOLOSINTA_TEREN_OTHER,folosintaTerenOther);
        contentValues.put(DatabaseContract.FormsTABLE.COLUMN_CULTURA,cultura);
        contentValues.put(DatabaseContract.FormsTABLE.COLUMN_CULTURA_OTHER,culturaOther);
        contentValues.put(DatabaseContract.FormsTABLE.COLUMN_SUPRAFATA,suprafata);
        contentValues.put(DatabaseContract.FormsTABLE.COLUMN_SUPRAFATA_OTHER,suprafataOther);
        contentValues.put(DatabaseContract.FormsTABLE.COLUMN_TIP_SOL,tipSol);
        contentValues.put(DatabaseContract.FormsTABLE.COLUMN_USCAT,uscat);
        contentValues.put(DatabaseContract.FormsTABLE.COLUMN_PRIZA,priza);
        contentValues.put(DatabaseContract.FormsTABLE.COLUMN_VANT,vant);
        contentValues.put(DatabaseContract.FormsTABLE.COLUMN_TRAFIC,trafic);
        contentValues.put(DatabaseContract.FormsTABLE.COLUMN_LOCALIZAT,localizat);
        contentValues.put(DatabaseContract.FormsTABLE.COLUMN_PICHET,pichet);
        contentValues.put(DatabaseContract.FormsTABLE.COLUMN_DISTANCE,distance);
        contentValues.put(DatabaseContract.FormsTABLE.COLUMN_OTHER_COMMENTS,otherComments);
        long result = db.insert(DatabaseContract.FormsTABLE.TABLE_NAME,null,contentValues);
        if(result == -1){
            Toast.makeText(context,"Failed to add to db", Toast.LENGTH_SHORT).show();
            return -1;
        }
        else{
            Toast.makeText(context,"added to db successfully", Toast.LENGTH_SHORT).show();
            return 1;
        }

    }

    public void deletelastEntry(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+DatabaseContract.ShotTABLE.TABLE_NAME+" WHERE _id = (SELECT MAX(_id) FROM "+DatabaseContract.ShotTABLE.TABLE_NAME+ ");");
    }


}