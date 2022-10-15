package com.pcx.capitalofapp.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "quiz.sqlite", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            "CREATE TABLE IF NOT EXISTS 'countries' (\n" +
                    "\t'id'\tINTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                    "\t'name'\tTEXT NOT NULL,\n" +
                    "\t'flag'\tTEXT NOT NULL,\n" +
                    "\t'capital'\tTEXT NOT NULL\n" +
                    ")"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS countries")
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
}