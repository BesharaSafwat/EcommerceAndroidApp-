package com.example.projectandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDBhelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "Users_DB";
    private static final int DB_VERSION = 1 ;
    private static final String TABLE_NAME = "User_table";
    private static final String  ID_OF_COL = "id" ;
    private static final String EMAIL = "email";
    private static final String PASSWORD ="password";
    private static final String NAME = "name";
    private static final String ROLE = "role";
    private static final String AVATAR ="avatar";


    public UserDBhelper( Context context) {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+TABLE_NAME+
                "("+ ID_OF_COL+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +EMAIL+" TEXT,"
                +PASSWORD+" TEXT,"
                +NAME+" TEXT,"
                +ROLE+" TEXT,"
                +AVATAR+" TEXT )";

        Log.d("lifecycledb","on create database");

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        Log.d("lifecycledb","on upgrade database");
        onCreate(db);
    }

    public void addUser(String email , String password, String name,
                        String role , String avatar){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put(EMAIL,email);
        values.put(PASSWORD,password);
        values.put(NAME,name);
        values.put(ROLE,role);
        values.put(AVATAR,avatar);
        db.insertWithOnConflict(TABLE_NAME,null,values,0);
//        Toast.makeText(contxt,"Product Data Saved",Toast.LENGTH_SHORT);
        Log.d("lifecycledb","add user");
        db.close();
    }
    public UsersModel getUserInfo(String searchValue , String searchCol){
        Log.d("lifecycle","get prod tag");
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,
                new String[] {ID_OF_COL,EMAIL,PASSWORD,NAME,ROLE,AVATAR},
                searchCol + " LIKE?",
                new String[]{"%"+searchValue+"%"},null,null,null);
        if (cursor != null) {
            cursor.moveToNext();
        }
        UsersModel user =new UsersModel(cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5));
        return user ;
    }


}
