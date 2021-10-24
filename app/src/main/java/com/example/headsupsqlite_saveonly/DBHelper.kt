package com.example.headsupsqlite_saveonly

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(
    context: Context?,
    name: String?= "details.db",
    factory: SQLiteDatabase.CursorFactory?= null,
    version: Int= 1,
    private val tableName: String= "celebrities"
) : SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table $tableName (Name Text, Taboo1 Text, Taboo2 Text, Taboo3 Text)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {}

    fun saveNotes(name: String, taboo1: String, taboo2: String, taboo3: String): Long {
        val cv= ContentValues()
        cv.put("Name",name)
        cv.put("Taboo1",taboo1)
        cv.put("Taboo2",taboo2)
        cv.put("Taboo3",taboo3)
        return writableDatabase.insert(tableName,null,cv)
    }
}