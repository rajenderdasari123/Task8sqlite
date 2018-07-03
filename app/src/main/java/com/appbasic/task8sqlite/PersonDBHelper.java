package com.appbasic.task8sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by 2159 on 17-01-2018.
 */

public class PersonDBHelper extends SQLiteOpenHelper {

    public static final String DATABASENAME = "person.db";
    public static final String TABLENAME = "persontable";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PERSON_NAME = "name";
    public static final String COLUMN_PERSON_AGE = "age";
    public static final String COLUMN_PERSON_OCCUPATION = "occupation";
    public static final String COLUMN_PERSON_IMAGE = "image";


    public PersonDBHelper(Context context) {
        super(context, DATABASENAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

 //db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,SURNAME TEXT,MARKS INTEGER)");
        sqLiteDatabase.execSQL("create table " + TABLENAME +" (id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,age TEXT,occupation TEXT,image TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + TABLENAME);
        onCreate(sqLiteDatabase);
    }

    public boolean saveNewPerson(String name, String age, String occupation) {

        SQLiteDatabase sqdn = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_PERSON_NAME, name);
        cv.put(COLUMN_PERSON_AGE, age);
        cv.put(COLUMN_PERSON_OCCUPATION, occupation);

        long result = sqdn.insert(TABLENAME, null, cv);
        Log.d("bbb", "nn" + cv);

        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean updatedata(String id, String name, String age, String occupation) {
        SQLiteDatabase sqdn = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ID, id);
        cv.put(COLUMN_PERSON_NAME, name);
        cv.put(COLUMN_PERSON_AGE, age);
        cv.put(COLUMN_PERSON_OCCUPATION, occupation);
        sqdn.update(TABLENAME, cv, "id=?", new String[]{id});
        Log.d("bbb", "nn111" + cv);
        return true;
    }

    public Integer delete(String id) {
        SQLiteDatabase sqdb = this.getWritableDatabase();
        return sqdb.delete(TABLENAME, "id=?", new String[]{id});
    }

    public Cursor getalldata() {
        SQLiteDatabase sqdb = this.getWritableDatabase();
        Cursor cur = sqdb.rawQuery("select * from " + TABLENAME, null);
        return cur;
    }
}
