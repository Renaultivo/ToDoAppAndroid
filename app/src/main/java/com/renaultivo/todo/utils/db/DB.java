package com.renaultivo.todo.utils.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.renaultivo.todo.data.TaskItem;

public class DB extends SQLiteOpenHelper {

    public static final String BankName =  "BankTask";
    public static final int Version =  1;

    private static DB instance;

    private DB(Context context) {
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
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(TaskItem.DropTable);
        onCreate( sqLiteDatabase);

    }

    private SQLiteDatabase dataBase = null;
    public void CreateNewTask(Object obj) {
        TaskItem taskItem = (TaskItem) obj;   // casting
        ContentValues values = ContentValuesTask(taskItem);
        dataBase.insert(taskItem.tableName, null, values);
    }

    private ContentValues ContentValuesTask(TaskItem taskItem) {
        ContentValues values = new ContentValues();
        values.put(taskItem.title, taskItem.getTitle());
        values.put(taskItem.description, taskItem.getDescription());
        values.put(String.valueOf(taskItem.checked), new Integer(String.valueOf(taskItem.getChecked())));
        values.put(taskItem.created_on.toString(), taskItem.getCreated_on().toString());
        return values;
    }

    private void checkTask()
    {

    }
}
