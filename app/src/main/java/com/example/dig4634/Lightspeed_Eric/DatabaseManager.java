package com.example.dig4634.Lightspeed_Eric;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "leaderboard.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create table for leaderboard
        String createTableQuery = "CREATE TABLE leaderboard (id INTEGER PRIMARY KEY AUTOINCREMENT, player_name TEXT, score INTEGER)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrade if needed
        // For simplicity, in this example, we'll drop and recreate the table
        db.execSQL("DROP TABLE IF EXISTS leaderboard");
        onCreate(db);
    }
}

