package com.example.pizzamart

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DBHelper(context: Context): SQLiteOpenHelper(context,DBName,null,DATABASE_VERSION) {
    companion object {
        private const val DATABASE_VERSION = 1
        private const val DBName = "ShopDB"
        private const val TABLE_USER = "UserTB"
        private const val KEY_ID = "ID"
        private const val KEY_USER = "Username"
        private const val KEY_PASS = "Password"
    }
    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_USER_TABLE = ("CREATE TABLE " + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_USER + " TEXT,"
                + KEY_PASS + " TEXT" + ")")
        db.execSQL(CREATE_USER_TABLE)

    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USER")
        onCreate(db)
    }

    // INSERT DATA ON USERS TABLE
    fun insertUserTB(username: String, password:String): Boolean {
        val myDB: SQLiteDatabase = this.writableDatabase
        val cValues = ContentValues().apply {
            put(KEY_USER,username)
            put(KEY_PASS,password)
        }
        val result:Long = myDB.insert(TABLE_USER, null, cValues)
        return result != -1L
    }

    // CHECK IF THE USERNAME ALREADY EXIST
    fun checkUser(username: String):Boolean {
        val myDB: SQLiteDatabase = this.writableDatabase
        val cursor: Cursor = myDB.rawQuery("Select * from $TABLE_USER where $KEY_USER = ?", arrayOf(username))
        return cursor.count > 0
    }

    // CHECK IF INFO IS VALID
    fun checkUserPass(username: String, password: String):Boolean {
        val myDB: SQLiteDatabase = this.writableDatabase
        val cursor: Cursor = myDB.rawQuery("Select * from $TABLE_USER where $KEY_USER = ? and $KEY_PASS = ?", arrayOf(username, password))
        return cursor.count > 0
    }
}