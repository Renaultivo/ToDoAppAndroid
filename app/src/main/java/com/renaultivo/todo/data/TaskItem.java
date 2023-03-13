package com.renaultivo.todo.data;

import android.content.ContentValues;

import java.util.Date;

public class TaskItem {

    public static final String tableName = "Task";
    public static final String idColun = "idTask";
    public static final String titleColun = "title";
    public static final String descriptionColun = "description";
    public static final String checkedColun = "checked";
    public static final String created_onColun = "created";

    public static final String CreateTable = "CREATE TABLE " + tableName + "("
            + idColun + " INTEGER PRIMARY KEY AUTOINCREMENT," + titleColun + " TEXT,"
            + descriptionColun + " TEXT," +
            checkedColun + " BOOLEAN," +
            created_onColun + " DATE" +")";

    public static final String DropTable = "DROP TABLE IF EXISTS " + tableName;

    public int id;
    public String title;
    public String description;
    public boolean checked;
    public Date created_on;

    public TaskItem(boolean checked, String title, String description, Date created_on) {
        this.checked = checked;
        this.title = title;
        this.description = description;
        this.created_on = created_on;

        //cursor.put("checked", new Integer(taskItem.getChecked()).toString())
        
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription()
    {
        this.description = description;
    }

    public boolean getChecked()
    {
        return checked;
    }

    public void setChecked()
    {
        this.checked = checked;
    }

    public Date getCreated_on()
    {
        return created_on;
    }

    public void setCreated_on()
    {
        this.created_on = created_on;
    }
}
