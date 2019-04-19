package com.act360_android_list_me.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.act360_android_list_me.pojo.ListPojo;

import java.util.ArrayList;
import java.util.List;


public class ListDatabase extends SQLiteOpenHelper {

    public ListDatabase(Context context) {
        super(context, Constants.DATABASE.DB_NAME, null, Constants.DATABASE.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(Constants.DATABASE.CREATE_TABLE_QUERY);
        }catch (SQLException ex){
            Log.d("Create SQL", ex.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(Constants.DATABASE.DROP_QUERY);
        this.onCreate(sqLiteDatabase);
    }

    public void addList(ListPojo listPojo){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.DATABASE.USER_ID, listPojo.getUserId());
        contentValues.put(Constants.DATABASE.ID, listPojo.getId());
        contentValues.put(Constants.DATABASE.TITLE, listPojo.getTitle());
        contentValues.put(Constants.DATABASE.BODY, listPojo.getBody());

        db.insert(Constants.DATABASE.TABLE_NAME, null, contentValues);
        db.close();
    }

    public List<ListPojo> getListPojo(){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(Constants.DATABASE.GET_LIST_QUERY, null);

        List<ListPojo> listPojoList = new ArrayList<>();

        if (cursor.getCount() > 0){

            if (cursor.moveToFirst()){
                do {

                    ListPojo listPojo = new ListPojo();

                    listPojo.setTitle(cursor.getColumnName(cursor.getColumnIndex(Constants.DATABASE.TITLE)));

                    listPojoList.add(listPojo);

                }while (cursor.moveToNext());
            }

        }

        return listPojoList;
    }



}
