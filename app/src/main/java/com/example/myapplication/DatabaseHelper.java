package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;




public class DatabaseHelper extends SQLiteOpenHelper {


    public  final  static String DATABASE_NAME = "records.db";
    public final static String TABLE_NAME_INCOME ="incomes_table";
    public final static String  TABLE_NAME_EXPENDITURE="expenditures_table";

    //COLUMNS FOR INCOMES TABLE
    public  static final String COL_1 = "ID";
    public  static final String COL_2 = "ITEM_INCOME";
    public  static final String COL_3 = " INCOME";

    //COLUMNS FOR EXPENDITURE TABLE
    public  static final String COL_4 = "ID";
    public  static final String COL_5 = "ITEM_EXPENDITURE";
    public  static final String COL_6 = "EXPENDITURE";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME,null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME_INCOME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT ,ITEM_INCOME TEXT ,INCOME INTEGER)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_INCOME);
        onCreate(db);
    }

    public boolean insertIncomeDate(String incomeItemName,String income){
        SQLiteDatabase db  = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_2,incomeItemName);
        cv.put(COL_3,income);

        long result = db.insert(TABLE_NAME_INCOME,null,cv);
        return result != -1;

    }


    public Cursor getIncomeData(){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT *FROM "+TABLE_NAME_INCOME;
        Cursor cursor = db.rawQuery(query,null);

        return cursor;

    }


    public Integer deleteIncomeData(String id ){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME_INCOME,"ID = ?",new String[]{id});

    }



    public  boolean  updateIncomedata(String id,String incomeItemName,String income){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,incomeItemName);
        contentValues.put(COL_3,income);
        db.update(TABLE_NAME_INCOME,contentValues,"ID = ?", new String[]{id});
        return true;

    }


}
