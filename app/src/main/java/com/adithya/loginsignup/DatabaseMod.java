package com.adithya.loginsignup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by RAdithya on 11/6/2016.
 */

public class DatabaseMod extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contacts.db";
    private static final String TABLE_NAME = "contacts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "names";
    private static final String COLUMN_EMAIL = "emails";
    private static final String COLUMN_PWORD = "pword";
    private static final String COLUMN_CONTNO = "contno";
    SQLiteDatabase db;

    private static final String TABLE_CREATE = "create table contacts (id integer primary key not null ," +
            "names text not null , emails text not null, pword text not null, contno text not null);";
    public DatabaseMod(Context context)

    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }
    public void insertContact(Contact c)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from contacts";
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();

        values.put(COLUMN_ID, count);
        values.put(COLUMN_NAME,c.getName());
        values.put(COLUMN_EMAIL,c.getEmail());
        values.put(COLUMN_PWORD,c.getPword());
        values.put(COLUMN_CONTNO,c.getContno());

        db.insert(TABLE_NAME,null,values);
        db.close();
    }
    public String[] searchPass(String email1)
    {
        db = this.getReadableDatabase();
        String query = " select emails,pword,names,contno from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        String a;
        String b[] = new String[3];
        b[0] = "not found";
        b[1] = "not found";
        b[2] = "not found";

        if (cursor.moveToFirst())
        {
            do{
                a = cursor.getString(0);
                if (a.equals(email1))
                {
                    b[0] = cursor.getString(1);
                    b[1] = cursor.getString(2);
                    b[2] = cursor.getString(3);
                    break;
                }
            }
            while(cursor.moveToNext());
        }
        return b;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = " DROP TABLE IF EXISTS "+ TABLE_NAME;
        db.execSQL (query);
        this.onCreate(db);
    }
}
