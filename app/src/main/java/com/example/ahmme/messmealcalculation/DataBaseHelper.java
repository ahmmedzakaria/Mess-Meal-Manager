package com.example.ahmme.messmealcalculation;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by BITM Trainer - 401 on 7/10/2016.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

   private static final String DATABASE_NAME = "mess_meal";
   private static final int DATABASE_VERSION = 1;

    public static final String TABLE_MESS_MEAL = "meal_info";
    public static final String COL_ID = "id";
    public static final String MESS_MEMBER_NAME = "mess_member_name";
    public static final String COL_DEPOSIT = "deposit";
    public static final String COL_MEAL = "meal";





    private static final String CREATE_CONTACT_TABLE = " CREATE TABLE " + TABLE_MESS_MEAL +
            "( " + COL_ID + " INTEGER PRIMARY KEY," + MESS_MEMBER_NAME + " TEXT, " + COL_DEPOSIT + " TEXT, " + COL_MEAL + " TEXT )";




    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_CONTACT_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


    }
}
