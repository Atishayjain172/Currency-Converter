package com.example.currencyconvertor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="INFO.db";
public static final String Table_name="INFO";
public static final String col_1="Title";
public static final String col_2="Content";

    public database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE INFO(TITLE TEXT,CONTENT TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Table_name);
        onCreate(db);

    }

    public void insertdata(String Title,String Content)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(col_1,Title);
        contentValues.put(col_2,Content);
        long success=db.insert(Table_name,null,contentValues);

    }

    public Cursor getAllData()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+Table_name,null);
        return cursor;
    }
    public Cursor getspecData(String titlename)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String query="SELECT CONTENT FROM INFO WHERE TITLE='" + titlename+"'";
        Cursor  cursor = db.rawQuery(query,null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public boolean update_data(String title,String content)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(col_1,title);
        contentValues.put(col_2,content);
        db.update(Table_name,contentValues,"Title=?",new String[]{title});
        return true;
    }

    public Integer deletedata(String title)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(Table_name,"Title=?",new String[]{title});
    }
}
