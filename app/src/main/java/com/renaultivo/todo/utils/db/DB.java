package com.renaultivo.todo.utils.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.renaultivo.todo.data.TaskItem;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import kotlinx.coroutines.scheduling.Task;

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

    public long EditTask(TaskItem taskItem)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(taskItem.titleColun, taskItem.getTitle());
        contentValues.put(taskItem.descriptionColun, taskItem.getDescription());
        contentValues.put(taskItem.checkedColun, taskItem.getChecked());
        contentValues.put(taskItem.created_onColun, taskItem.getCreated_on().toString());
        return getWritableDatabase().update(TaskItem.tableName, contentValues,"idTask="+taskItem.id,null);
    }

    public long DeleteTask(TaskItem taskItem)
    {
        return getWritableDatabase().delete(TaskItem.tableName, "idTask="+taskItem.id, null);
    }
    public ArrayList<String> listTask()
    {
        ArrayList<String> task = new ArrayList<>();
        Cursor cursor = dataBase.query(TaskItem.tableName, new String[]{TaskItem.titleColun,TaskItem.descriptionColun,
                        TaskItem.checkedColun, TaskItem.created_onColun}
                        ,null, null,null,null,null);
        while (cursor.moveToNext()){
            task.add(cursor.getString(0) +
                    cursor.getString(1)+
                    cursor.getString(2)+
                    cursor.getString(3));
        }
        return task;
    }

}
