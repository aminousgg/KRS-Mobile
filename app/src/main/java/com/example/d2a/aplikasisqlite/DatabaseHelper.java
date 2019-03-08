package com.example.d2a.aplikasisqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    //nama database
    public static final String DATABASE_NAME = "kuliah.db";
    //nama table
    public static final String TABLE_NAME = "table_krs";
    //versi database
    private static final int DATABASE_VERSION = 1;
    //table field
    public static final String COL_1 = "kode";
    public static final String COL_2 = "nama_matakuliah";
    public static final String COL_3 = "sks";
    public static final String COL_4 = "bu";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("create table table_krs(kode integer primary key autoincrement, " +
        db.execSQL("create table table_krs(kode integer primary key," +
                "nama_matakuliah text null," +
                "sks text null," +
                "bu integer null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //metode untuk tambah data
    public boolean insertData(String kode, String name_matakuliah, String
            sks, String bu) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, kode);
        contentValues.put(COL_2, name_matakuliah);
        contentValues.put(COL_3, sks);
        contentValues.put(COL_4, bu);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    //metode untuk mengambil data
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from table_krs", null);
        return res;
    }

    //metode untuk merubah data
    public boolean updateData(String kode, String nama_matakuliah, String
            sks, String bu) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, kode);
        contentValues.put(COL_2, nama_matakuliah);
        contentValues.put(COL_3, sks);
        contentValues.put(COL_4, bu);
        db.update(TABLE_NAME, contentValues, "kode = ?", new String[]{kode});
        return true;
    }

    //metode untuk menghapus data
    public int deleteData(String kode) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "kode = ?", new String[]{kode});
    }
}
