package com.example.ksc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "college.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_USERS = "users";
    public static final String COL_ID = "id";
    public static final String COL_STUDENT_ID = "student_id";
    public static final String COL_NAME = "name";
    public static final String COL_EMAIL = "email";
    public static final String COL_PHONE = "phone";
    public static final String COL_PASSWORD = "password";
    public static final String COL_CLASS = "student_class";
    public static final String COL_IMAGE_URI = "image_uri";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_STUDENT_ID + " TEXT,"
                + COL_NAME + " TEXT,"
                + COL_EMAIL + " TEXT,"
                + COL_PHONE + " TEXT,"
                + COL_PASSWORD + " TEXT,"
                + COL_CLASS + " TEXT,"
                + COL_IMAGE_URI + " TEXT"
                + ")";
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    // Insert user
    public boolean insertUser(String studentId, String name, String email, String phone,
                              String password, String studentClass, String imageUri) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_STUDENT_ID, studentId);
        values.put(COL_NAME, name);
        values.put(COL_EMAIL, email);
        values.put(COL_PHONE, phone);
        values.put(COL_PASSWORD, password);
        values.put(COL_CLASS, studentClass);
        values.put(COL_IMAGE_URI, imageUri);

        long result = db.insert(TABLE_USERS, null, values);
        return result != -1;
    }

    // Check if email exists
    public boolean isEmailExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_USERS,
                new String[]{COL_ID},
                COL_EMAIL + "=?",
                new String[]{email},
                null,
                null,
                null
        );
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    // Login check
    public boolean checkUserLogin(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_USERS,
                new String[]{COL_ID},
                COL_EMAIL + "=? AND " + COL_PASSWORD + "=?",
                new String[]{email, password},
                null,
                null,
                null
        );
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    // Update password
    public boolean updatePassword(String email, String newPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_PASSWORD, newPassword);

        int rowsAffected = db.update(
                TABLE_USERS,
                values,
                COL_EMAIL + "=?",
                new String[]{email}
        );

        return rowsAffected > 0;
    }

    // Get full user data by email (FOR DASHBOARD)
    public Cursor getUserByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(
                TABLE_USERS,
                null, // select all columns
                COL_EMAIL + "=?",
                new String[]{email},
                null,
                null,
                null
        );
    }
}
