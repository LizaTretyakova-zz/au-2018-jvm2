package com.example.liza.au2018jvm2.Database

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import com.example.liza.au2018jvm2.Museum
import org.jetbrains.anko.db.*

class MuseumDBHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "MyDatabase", null, 1) {
    companion object {

        private const val DATABASE_VERSION = 3
        private const val DATABASE_NAME = "museumDB.db"
        const val TABLE_MUSEUMS = "museums"

        const val COLUMN_NAME = BaseColumns._ID
        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_URL = "url"

    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables
        db.createTable(TABLE_MUSEUMS, true,
                COLUMN_NAME to TEXT + PRIMARY_KEY + UNIQUE,
                COLUMN_DESCRIPTION to TEXT,
                COLUMN_URL to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(TABLE_MUSEUMS, true)
    }

    fun addMuseum(museum: Museum) {
        this.use {
            insert(TABLE_MUSEUMS,
                    COLUMN_NAME to museum.name,
                    COLUMN_DESCRIPTION to museum.description,
                    COLUMN_URL to museum.url)
        }
    }

    fun getAllMuseumsCursor (): Cursor {
        val query = "SELECT * FROM ${MuseumDBHelper.TABLE_MUSEUMS}"
        val db = this.writableDatabase
        return db.rawQuery(query, null)
    }

    fun deleteMuseum(name: String) {
        this.use {
            delete(TABLE_MUSEUMS, "$COLUMN_NAME = {name}", "name" to name)
        }
    }
}
