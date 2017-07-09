package com.example.android.habbittrackerapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.android.habbittrackerapp.data.HabbitContract;
import com.example.android.habbittrackerapp.data.HabbitDbHelper;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private SQLiteOpenHelper mDbHelper;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbHelper = new HabbitDbHelper(this);

        //inserting a sample Breakfast item into the nutrition table
        insertBreakfastHabbit();

        //getting all the data(all columns and rows) from the database and displaying in the log
        getDatabaseData();

    }

    private Cursor getCursorObject() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                HabbitContract.HabbitEntry._ID,
                HabbitContract.HabbitEntry.COLUMN_HAD_BREAKFAST,
                HabbitContract.HabbitEntry.COLUMN_BREAKFAST_MEAL,
                HabbitContract.HabbitEntry.COLUMN_HAD_LUNCH,
                HabbitContract.HabbitEntry.COLUMN_LUNCH_MEAL,
                HabbitContract.HabbitEntry.COLUMN_HAD_SUPPER,
                HabbitContract.HabbitEntry.COLUMN_SUPPER_MEAL,
                HabbitContract.HabbitEntry.COLUMN_ADDITIONAL_DATA,
                HabbitContract.HabbitEntry.COLUMN_DATETIME_STAMP};

        // Perform a query on the nutrition table
        Cursor cursor = db.query(
                HabbitContract.HabbitEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order

        return cursor;
    }

    private void getDatabaseData() {
        Cursor cursor = getCursorObject();

        try {
            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(HabbitContract.HabbitEntry._ID);
            int hadBreakfastColumnIndex = cursor.getColumnIndex(HabbitContract.HabbitEntry.COLUMN_HAD_BREAKFAST);
            int breakfastMealColumnIndex = cursor.getColumnIndex(HabbitContract.HabbitEntry.COLUMN_BREAKFAST_MEAL);
            int hadLunchColumnIndex = cursor.getColumnIndex(HabbitContract.HabbitEntry.COLUMN_HAD_LUNCH);
            int lunchMealColumnIndex = cursor.getColumnIndex(HabbitContract.HabbitEntry.COLUMN_LUNCH_MEAL);
            int hadSuppertColumnIndex = cursor.getColumnIndex(HabbitContract.HabbitEntry.COLUMN_HAD_SUPPER);
            int supperMealColumnIndex = cursor.getColumnIndex(HabbitContract.HabbitEntry.COLUMN_SUPPER_MEAL);
            int additionalDataColumnIndex = cursor.getColumnIndex(HabbitContract.HabbitEntry.COLUMN_ADDITIONAL_DATA);
            int datetimeStampColumnIndex = cursor.getColumnIndex(HabbitContract.HabbitEntry.COLUMN_DATETIME_STAMP);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the content
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                int currentHadBreakfast = cursor.getInt(hadBreakfastColumnIndex);
                String currentBreakfast = cursor.getString(breakfastMealColumnIndex);
                int currentHadLunch = cursor.getInt(hadLunchColumnIndex);
                String currentLunch = cursor.getString(lunchMealColumnIndex);
                int currentHadSupper = cursor.getInt(hadSuppertColumnIndex);
                String currentSupper = cursor.getString(supperMealColumnIndex);
                String currentAdditionalData = cursor.getString(additionalDataColumnIndex);
                String currentDatetime = cursor.getString(datetimeStampColumnIndex);

                //display data in the the Verbose Log
                Log.v("MainActivity", "ID: " + String.valueOf(currentID)
                        + "\n Had Breakfast: " + currentHadBreakfast
                        + "\n Breakfast: " + currentBreakfast
                        + "\n Had Lunch: " + currentHadLunch
                        + "\n Lunch: " + currentLunch
                        + "\n Had Supper: " + currentHadSupper
                        + "\n Supper: " + currentSupper
                        + "\n Additional Data: " + currentAdditionalData
                        + "\n Timestamp: " + currentDatetime);

            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void insertBreakfastHabbit() {
        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and Breakfast attributes are the values.
        ContentValues values = new ContentValues();
        values.put(HabbitContract.HabbitEntry.COLUMN_HAD_BREAKFAST, 1);
        values.put(HabbitContract.HabbitEntry.COLUMN_BREAKFAST_MEAL, "porridge with honey and banana");
        values.put(HabbitContract.HabbitEntry.COLUMN_ADDITIONAL_DATA, "300 calories meal");
        values.put(HabbitContract.HabbitEntry.COLUMN_DATETIME_STAMP, getDateTime());

        // Insert a new row for Breakfast in the database, returning the ID of that new row.
        // The first argument for db.insert() is the nutrition table name.
        // The second argument provides the name of a column in which the framework
        // can insert NULL in the event that the ContentValues is empty (if
        // this is set to "null", then the framework will not insert a row when
        // there are no values).
        // The third argument is the ContentValues object containing the info for Breakfast Entry.
        long newRowId = db.insert(HabbitContract.HabbitEntry.TABLE_NAME, null, values);

        Log.v("MainActivity", "New Rod ID: " + newRowId);
    }

    //Method for getting the Date and Time
    @RequiresApi(api = Build.VERSION_CODES.N)
    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
