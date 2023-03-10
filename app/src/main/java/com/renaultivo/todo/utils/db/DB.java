package com.renaultivo.todo.utils.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.renaultivo.todo.data.TaskItem;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DB extends SQLiteOpenHelper {
    private SQLiteDatabase dataBase = null;
    public static final String BankName =  "BankTask";
    public static final int Version =  1;

    private static DB instance;

    public DB(Context context) {
        super(context,BankName,null,Version);
    }

    public static DB getInstance(Context context) {
        if(instance == null)
            instance = new DB(context);

        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TaskItem.CreateTable);
        dataBase = sqLiteDatabase;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(TaskItem.DropTable);
        onCreate( sqLiteDatabase);

    }


    public void CreateNewTask(TaskItem taskItem) {
        ContentValues values = ContentValuesTask(taskItem);
        getWritableDatabase().insert(TaskItem.tableName, null, values);
    }

    private ContentValues ContentValuesTask(TaskItem taskItem) {
        ContentValues values = new ContentValues();
        values.put(taskItem.titleColun, taskItem.getTitle());
        values.put(taskItem.descriptionColun, taskItem.getDescription());
        values.put(taskItem.checkedColun, taskItem.getChecked());
        values.put(taskItem.created_onColun, taskItem.getCreated_on().toString());
        return values;
    }

    private void checkTask()
    {

    }

}
