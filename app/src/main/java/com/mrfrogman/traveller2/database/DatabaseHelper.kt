package com.mrfrogman.traveller2.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DatabaseHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        val plansT =
            "CREATE TABLE plans(_id INTEGER PRIMARY KEY, title TEXT, detail TEXT, member INTEGER, amount INTEGER, date TEXT);"
        db.execSQL(plansT)
        val memberT =
            "CREATE TABLE member(_id INTEGER PRIMARY KEY, name TEXT, plan_id INTEGER, FOREIGN KEY (plan_id) REFERENCES plans(_id));"
        db.execSQL(memberT)
        val amountT =
            "CREATE TABLE amount(_id INTEGER PRIMARY KEY, title TEXT, detail TEXT, plan_id INTEGER, FOREIGN KEY (plan_id) REFERENCES plans(_id));"
        db.execSQL(amountT)
        val amountDetailT =
            "CREATE TABLE amount_detail(_id INTEGER PRIMARY KEY, title TEXT, detail TEXT, plan_id INTEGER, FOREIGN KEY (plan_id) REFERENCES plans(_id));"
        db.execSQL(amountDetailT)
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES)
        onCreate(sqLiteDatabase)
    }

    companion object {
        private const val DATABASE_NAME = "Traveller.db"
        private const val VERSION = 1
        private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS plans"
    }
}