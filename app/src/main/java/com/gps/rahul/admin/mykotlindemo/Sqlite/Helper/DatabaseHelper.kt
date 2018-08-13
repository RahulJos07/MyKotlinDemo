package com.gps.rahul.admin.mykotlindemo.Sqlite.Helper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.gps.rahul.admin.mykotlindemo.Sqlite.Model.UserModel

class DatabaseHelper(val context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE = "CREATE TABLE ${TABLE_NAME} (" +
                ID + " INTEGER PRIMARY KEY," +
                NAME + " TEXT," + EMAIL_ID + " TEXT," + MOBILE_NO + " TEXT," +
                GENDER + " TEXT,"+ CLASS +" TEXT," + DATE_TIME + " TEXT);"
        db!!.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        val DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME
        db!!.execSQL(DROP_TABLE)
        onCreate(db)
    }
    fun onStoreData(userModel: UserModel) : Boolean {
        val db : SQLiteDatabase=this.writableDatabase
        val contentValues= ContentValues()
        contentValues.put(NAME,userModel.getName())
        contentValues.put(EMAIL_ID,userModel.getEmail_Id())
        contentValues.put(MOBILE_NO,userModel.getMobile_No())
        contentValues.put(GENDER,userModel.getGender())
        contentValues.put(CLASS,userModel.getClass())
        contentValues.put(DATE_TIME,userModel.getDateTime())
        val insert_data = db.insert(TABLE_NAME,null,contentValues)
        db.close()

        return !insert_data.equals(-1)
    }

    fun readAllUsers(): ArrayList<UserModel> {
        val users = ArrayList<UserModel>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("select * from " + TABLE_NAME, null)
        } catch (e: SQLiteException) {
            return ArrayList()
        }
        var userid: String
        var name: String
        var email: String
        var mobile: String
        var gender: String
        var cls: String
        var datetime: String

        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                userid = cursor.getString(cursor.getColumnIndex(ID))
                name = cursor.getString(cursor.getColumnIndex(NAME))
                email = cursor.getString(cursor.getColumnIndex(EMAIL_ID))
                mobile = cursor.getString(cursor.getColumnIndex(MOBILE_NO))
                gender = cursor.getString(cursor.getColumnIndex(GENDER))
                cls = cursor.getString(cursor.getColumnIndex(CLASS))
                datetime = cursor.getString(cursor.getColumnIndex(DATE_TIME))
                users.add(UserModel(userid, name, email, mobile, gender, cls, datetime))
                cursor.moveToNext()
            }
        }
        else
        {
            Toast.makeText(context,"Data Not Found",Toast.LENGTH_SHORT).show();
        }
        return users
    }

    fun onUpdateData(userModel: UserModel): Boolean {
        val database = this.writableDatabase
        val contentValues= ContentValues()
        val id= arrayOf(userModel.getId())
        contentValues.put(NAME,userModel.getName())
        contentValues.put(EMAIL_ID,userModel.getEmail_Id())
        contentValues.put(MOBILE_NO,userModel.getMobile_No())
        contentValues.put(GENDER,userModel.getGender())
        contentValues.put(CLASS,userModel.getClass())
        contentValues.put(DATE_TIME,userModel.getDateTime())
        val updatedata=database.update(TABLE_NAME, contentValues, ID + " =?", id)
        database.close()
        return !updatedata.equals(-1)
    }

    fun onDeleteData(_id: String):Boolean
    {
        val db = this.writableDatabase
        val _success = db.delete(TABLE_NAME, ID + "=?", arrayOf(_id.toString())).toLong()
        db.close()
        return Integer.parseInt("$_success") != -1
    }

    companion object {
        private val DB_VERSION=1
        private val DB_NAME="studentDemo"
        private val TABLE_NAME="student"
        private val ID="id"
        private val NAME="name"
        private val EMAIL_ID="email_id"
        private val MOBILE_NO="mobile_no"
        private val GENDER="gender"
        private val CLASS="class"
        private val DATE_TIME="record"
    }
}