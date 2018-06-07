package com.example.bs217.androidhub.main

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "github", null, 1){



    companion object {
        private var databaseHelper: DatabaseHelper? = null

        @Synchronized
        public fun getInstance(context: Context): DatabaseHelper? {
            if (this.databaseHelper == null) {
                databaseHelper = DatabaseHelper(context)
            }
            return databaseHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_SEARCH_TABLE : String = "CREATE TABLE search(" +
                "id INTEGER PRIMARY KEY, " +
                "result INTEGER)"
        val CREATE_ITEM_TABLE: String = "CREATE TABLE item(" +
                "id INTEGER PRIMARY KEY, " +
                "description TEXT NULL, " +
                "brancherUrl TEXT, " +
                "commentsUrl TEXT, " +
                "issuesUrl TEXT," +
                "createdAt TEXT, " +
                "updatedAt TEXT," +
                "size INTEGER," +
                "forksCount INTEGER," +
                "openIssuesCount INTEGER, " +
                "watchersCount INTEGER, " +
                "score INTEGER, " +
                "star INTEGER)"
        val CREATE_OWNER_TABLE: String = "CREATE TABLE owner(" +
                "id INTEGER PRIMARY KEY, " +
                "name TEXT, " +
                "imageOfOwner TEXT, " +
                "profileUrl TEXT, " +
                "followers TEXT, " +
                "followings TEXT, " +
                "reposOfOwner TEXT)"

        db?.execSQL(CREATE_SEARCH_TABLE)
        db?.execSQL(CREATE_ITEM_TABLE)
        db?.execSQL(CREATE_OWNER_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

}