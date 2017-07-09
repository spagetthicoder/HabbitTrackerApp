package com.example.android.habbittrackerapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HabbitDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "HabbitTracker.db";

    public HabbitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the Nutrition table
        String SQL_CREATE_NUTRITION_TABLE = "CREATE TABLE " + HabbitContract.HabbitEntry.TABLE_NAME + " ("
                + HabbitContract.HabbitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabbitContract.HabbitEntry.COLUMN_HAD_BREAKFAST + " INTEGER NOT NULL DEFAULT 0, "
                + HabbitContract.HabbitEntry.COLUMN_BREAKFAST_MEAL + " TEXT, "
                + HabbitContract.HabbitEntry.COLUMN_HAD_LUNCH + " INTEGER NOT NULL DEFAULT 0, "
                + HabbitContract.HabbitEntry.COLUMN_LUNCH_MEAL + " TEXT, "
                + HabbitContract.HabbitEntry.COLUMN_HAD_SUPPER + " INTEGER NOT NULL DEFAULT 0, "
                + HabbitContract.HabbitEntry.COLUMN_SUPPER_MEAL + " TEXT, "
                + HabbitContract.HabbitEntry.COLUMN_ADDITIONAL_DATA + " TEXT, "
                + HabbitContract.HabbitEntry.COLUMN_DATETIME_STAMP + " TEXT);";

        db.execSQL(SQL_CREATE_NUTRITION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
