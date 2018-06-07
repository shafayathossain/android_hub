package com.example.bs217.androidhub.main

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.widget.Toast
import com.example.bs217.androidhub.database_models.ModelSearch

class DatabaseQuery {

    private lateinit var context : Context

    fun insertSearch( search : ModelSearch) : Long{
        var id : Long = -1

        var databaseHelper : DatabaseHelper? = DatabaseHelper.getInstance(context)
        var sqliteDatabase : SQLiteDatabase = databaseHelper!!.writableDatabase

        var contentsValues : ContentValues = ContentValues()
        contentsValues.put("result", search.totalCount)
        try {
            id = sqliteDatabase.insertOrThrow("github", null, contentsValues)
        }catch (e : SQLiteException){
            Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show()
        }finally {
            sqliteDatabase.close()
        }
        return id
    }
}