package com.renaultivo.todo.utils.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.renaultivo.todo.data.TaskItem;

import java.util.ArrayList;
import java.util.Date;

public class DB extends SQLiteOpenHelper {
    private SQLiteDatabase dataBase = null;
    public static final String BankName =  "BankTask";
    public static final int Version =  1;

    private static DB instance;

    public DB(Context context) {
        super(context,BankName,null,Version);
        dataBase = getWritableDatabase();
    }

    public static DB getInstance(Context context) {
        if(instance == null)
            instance = new DB(context);

        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TaskItem.createTable);
        dataBase = sqLiteDatabase;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(TaskItem.DropTable);
        onCreate( sqLiteDatabase);

    }


    public TaskItem createNewTask(TaskItem taskItem) {
        ContentValues values = this.contentValuesTask(taskItem);
        taskItem.id = (int) getWritableDatabase().insert(TaskItem.tableName, null, values);

        return taskItem;

    }

    private ContentValues contentValuesTask(TaskItem taskItem) {
        ContentValues values = new ContentValues();
        values.put(taskItem.titleColumn, taskItem.getTitle());
        values.put(taskItem.descriptionColumn, taskItem.getDescription());
        values.put(taskItem.checkedColumn, taskItem.getChecked());
        values.put(taskItem.created_onColumn, new Long(taskItem.getCreated_on().getTime()).toString());
        return values;
    }

    public long editTask(TaskItem taskItem)
    {

        System.out.println("Updating task of id = " + taskItem.id);

        ContentValues contentValues = new ContentValues();
        contentValues.put(taskItem.titleColumn, taskItem.getTitle());
        contentValues.put(taskItem.descriptionColumn, taskItem.getDescription());
        contentValues.put(taskItem.checkedColumn, taskItem.getChecked());
        contentValues.put(taskItem.created_onColumn, taskItem.getCreated_on().toString());
        return getWritableDatabase().update(TaskItem.tableName, contentValues,"idTask="+taskItem.id,null);
    }

    public long deleteTask(TaskItem taskItem)
    {
        return getWritableDatabase().delete(TaskItem.tableName, "idTask="+taskItem.id, null);
    }
    public ArrayList<TaskItem> listTask()
    {
        ArrayList<TaskItem> taskList = new ArrayList<>();

        Cursor cursor = dataBase.query(TaskItem.tableName, new String[]{
                        TaskItem.idColumn,
                        TaskItem.titleColumn,TaskItem.descriptionColumn,
                        TaskItem.checkedColumn, TaskItem.created_onColumn
                },
                null, null,null,null,null);

        while (cursor.moveToNext()){

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

                TaskItem taskItem = null;

                taskItem = new TaskItem(
                        cursor.getInt(cursor.getColumnIndexOrThrow(TaskItem.idColumn)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(TaskItem.checkedColumn)) == 1,
                        cursor.getString(cursor.getColumnIndexOrThrow(TaskItem.titleColumn)),
                        cursor.getString(cursor.getColumnIndexOrThrow(TaskItem.descriptionColumn)),
                        new Date(cursor.getLong(cursor.getColumnIndexOrThrow(TaskItem.created_onColumn)))
                );

                taskList.add(taskItem);

            } else {

                TaskItem taskItem = new TaskItem(
                        cursor.getInt(cursor.getColumnIndexOrThrow(TaskItem.idColumn)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(TaskItem.checkedColumn)) == 1,
                        cursor.getString(cursor.getColumnIndexOrThrow(TaskItem.titleColumn)),
                        cursor.getString(cursor.getColumnIndexOrThrow(TaskItem.descriptionColumn)),
                        new Date(Date.parse(cursor.getString(cursor.getColumnIndexOrThrow(TaskItem.created_onColumn))))
                );

                taskList.add(taskItem);

            }


        }

        return taskList;
    }

}
