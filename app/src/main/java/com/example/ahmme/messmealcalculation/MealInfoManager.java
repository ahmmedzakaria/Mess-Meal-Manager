package com.example.ahmme.messmealcalculation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Laptop Dream on 14-Jul-16.
 */
public class MealInfoManager {
    private DataBaseHelper helper;
    private SQLiteDatabase database;
    MealInfo mealInfo =new MealInfo();
    private Context context;

    public MealInfoManager(Context context) {
        this.context = context;
        helper = new DataBaseHelper(context);

    }

    private void open() {
        database = helper.getWritableDatabase();

    }

    private void close() {
        helper.close();
    }

    public boolean addMealInfo(MealInfo contact) {

        this.open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.MESS_MEMBER_NAME, contact.getName());
        contentValues.put(DataBaseHelper.COL_MEAL, contact.getMeal());
        contentValues.put(DataBaseHelper.COL_DEPOSIT, contact.getDeposit());

        long inserted = database.insert(DataBaseHelper.TABLE_MESS_MEAL, null, contentValues);
        this.close();

        database.close();

        if (inserted > 0) {
            return true;
        } else return false;

    }

    public ArrayList<MealInfo> getAllMealInfo() {

        this.open();

        ArrayList<MealInfo> contactList = new ArrayList<>();

        Cursor cursor = database.query(DataBaseHelper.TABLE_MESS_MEAL, null, null, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();

            for (int i = 0; i < cursor.getCount(); i++) {
                int id = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COL_ID));
                String name = cursor.getString(cursor.getColumnIndex(DataBaseHelper.MESS_MEMBER_NAME));
                double deposit = cursor.getDouble(cursor.getColumnIndex(DataBaseHelper.COL_DEPOSIT));
                double meal = cursor.getDouble(cursor.getColumnIndex(DataBaseHelper.COL_MEAL));

                MealInfo MealInfo = new MealInfo(id, name, deposit, meal);
                contactList.add(MealInfo);
                cursor.moveToNext();
            }
            this.close();

        }
        return contactList;
    }


    public MealInfo getMemberMealInfoById(int id) {

        this.open();

        Cursor cursor = database.query(DataBaseHelper.TABLE_MESS_MEAL, new String[]{DataBaseHelper.COL_ID,
                        DataBaseHelper.MESS_MEMBER_NAME, DataBaseHelper.COL_MEAL, DataBaseHelper.COL_DEPOSIT},
                DataBaseHelper.COL_ID + " = " + id, null, null, null, null);
        cursor.moveToFirst();

        int columId = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COL_ID));
        String name = cursor.getString(cursor.getColumnIndex(DataBaseHelper.MESS_MEMBER_NAME));
        double deposit = cursor.getDouble(cursor.getColumnIndex(DataBaseHelper.COL_DEPOSIT));
        double meal = cursor.getDouble(cursor.getColumnIndex(DataBaseHelper.COL_MEAL));

        MealInfo contact = new MealInfo(columId, name, meal, deposit);
        this.close();
        return contact;
    }
    public double getMemberMealByID(int id) {

        this.open();

        Cursor cursor = database.query(DataBaseHelper.TABLE_MESS_MEAL, new String[]{DataBaseHelper.COL_MEAL},
                DataBaseHelper.COL_ID + " = " + id, null, null, null, null);
        cursor.moveToFirst();
        double meal = cursor.getDouble(cursor.getColumnIndex(DataBaseHelper.COL_MEAL));

        this.close();
        return meal;
    }
    public double getMemberDepositByID(int id) {

        this.open();

        Cursor cursor = database.query(DataBaseHelper.TABLE_MESS_MEAL, new String[]{DataBaseHelper.COL_DEPOSIT},
                DataBaseHelper.COL_ID + " = " + id, null, null, null, null);
        cursor.moveToFirst();
        double deposit = cursor.getDouble(cursor.getColumnIndex(DataBaseHelper.COL_DEPOSIT));

        this.close();
        return deposit;
    }
    public int getTotalMessMember() {
        this.open();
        Cursor cursor = database.query(DataBaseHelper.TABLE_MESS_MEAL, new String[]{DataBaseHelper.COL_ID},
                null, null, null, null, null);
        cursor.moveToLast();
        int columId = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COL_ID));
        this.close();
        return columId;
    }

    public double getTotalMeal() {
        this.open();
        Cursor cursor = database.query(DataBaseHelper.TABLE_MESS_MEAL, new String[]{DataBaseHelper.COL_MEAL},
                null, null, null, null, null);
        cursor.moveToFirst();
        double totalMill=0.0;
        for (int i = 0; i < cursor.getCount(); i++) {
            double meal = cursor.getDouble(cursor.getColumnIndex(DataBaseHelper.COL_MEAL));
            totalMill+=meal;
            cursor.moveToNext();
        }
        this.close();
        return totalMill;
    }

    public boolean updateContact(int id, MealInfo contact) {
        this.open();
        ContentValues cv = new ContentValues();
        cv.put(DataBaseHelper.MESS_MEMBER_NAME, contact.getName());
        cv.put(DataBaseHelper.COL_DEPOSIT, contact.getDeposit());
        cv.put(DataBaseHelper.COL_MEAL, contact.getMeal());


        int updated = database.update(DataBaseHelper.TABLE_MESS_MEAL, cv, DataBaseHelper.COL_ID + " = " + id, null);
        this.close();
        if (updated > 0) {
            return true;
        } else
            return false;
    }

    public boolean deleteAllContact() {
        this.open();
        int deleted = database.delete(DataBaseHelper.TABLE_MESS_MEAL,null,null);
        this.close();
        if (deleted > 0) {
            return true;
        } else return false;
    }





    public double getMillRet(){
        double millRet= mealInfo.getTotalBazar()/getTotalMeal();
        return millRet;
    }

    public double getMillCost(int id){
        double millCost=getMemberMealByID(id)*getMillRet();
        return millCost;
    }
    public double getRestMony(int id){
        double restMony= getMemberDepositByID(id)-getMillCost(id);
        return restMony;
    }
}
