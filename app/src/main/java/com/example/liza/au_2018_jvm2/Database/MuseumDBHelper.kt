package com.example.liza.au_2018_jvm2.Database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.example.liza.au_2018_jvm2.Museum

class MuseumDBHelper(context: Context, name: String?,
                     factory: SQLiteDatabase.CursorFactory?) :
        SQLiteOpenHelper(context, DATABASE_NAME,
                factory, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_PRODUCTS_TABLE = ("CREATE TABLE " +
                TABLE_MUSEUMS + "("
                + COLUMN_NAME + " TEXT PRIMARY KEY," +
                COLUMN_DESCRIPTION
                + " TEXT," + COLUMN_URL + " TEXT" + ")")
        db.execSQL(CREATE_PRODUCTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int,
                           newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MUSEUMS)
        onCreate(db)
    }

    fun addMuseum(museum: Museum) {

        val values = ContentValues()
        values.put(COLUMN_NAME, museum.name)
        values.put(COLUMN_DESCRIPTION, museum.description)
        values.put(COLUMN_URL, museum.url)

        val db = this.writableDatabase

        db.insert(TABLE_MUSEUMS, null, values)
    }

    fun getAllMuseumsCursor (): Cursor {
        val query = "SELECT * FROM $TABLE_MUSEUMS"
        val db = this.writableDatabase
        val cursor = db.rawQuery(query, null)
        return cursor
    }

    companion object {

        private val DATABASE_VERSION = 3
        private val DATABASE_NAME = "museumDB.db"
        val TABLE_MUSEUMS = "museums"

        val COLUMN_NAME = BaseColumns._ID
        val COLUMN_DESCRIPTION = "description"
        val COLUMN_URL = "url"
    }
}
