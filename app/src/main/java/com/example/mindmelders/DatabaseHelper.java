package com.example.mindmelders;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "login.db, signup.db";
    private static final String TABLE_NAME = "records_table";
    private static final String COL1 = "ID";
    private static final String COL2 = "NAME";
    private static final String COL3 = "PHONE_NUMBER";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user(ID INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, email TEXT, password TEXT)");
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " NAME TEXT, PHONE_NUMBER TEXT)";
        db.execSQL(createTable);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public boolean Insert(String username, String email, String password) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = sqLiteDatabase.insert("user", null, contentValues);
        return result != -1;
    }

    public Boolean CheckUsername(String username) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM user WHERE username=?", new String[]{username});
        return cursor.getCount() == 0;
    }

    public Boolean CheckLogin(String username, String password) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM user WHERE username=? AND password=?", new String[]{username, password});
        return cursor.getCount() > 0;
    }

    public boolean deleteUser(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete("user", "username=?", new String[]{username});
        return result > 0;
    }

    public boolean updateUser(String username, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email", email);
        values.put("password", password);
        int rowsAffected = db.update("user", values, "username = ?", new String[]{username});
        return rowsAffected > 0;
    }

    public boolean updateUsername(String oldUsername, String newUsername) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", newUsername);
        int rowsAffected = db.update("user", values, "username = ?", new String[]{oldUsername});
        return rowsAffected > 0;
    }
    public boolean addData(String name, String phoneNumber) {
        SQLiteDatabase db = this.getWritableDatabase();

        // First, check if the name already exists in the table
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL2 + "=?", new String[]{name});

        if (cursor.getCount() > 0) {
            // Name already exists, you can choose to update the existing entry or ignore the new entry
            // For simplicity, I'm ignoring the new entry here
            return false;
        } else {
            // Name doesn't exist, proceed to insert the new entry
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL2, name);
            contentValues.put(COL3, phoneNumber);

            long result = db.insert(TABLE_NAME, null, contentValues);

            // if data was inserted incorrectly it will return -1
            return result != -1;
        }
    }

    public Cursor getFriends() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME, null, null, null, null, null, null);
    }
    public boolean deleteFriend(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_NAME, "NAME=?", new String[]{name});
        return result > 0;
    }
}