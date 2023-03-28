package com.renaultivo.todo.data;

import java.util.Date;

public class TaskItem {

    public static final String tableName = "Task";
    public static final String idColumn = "idTask";
    public static final String titleColumn = "title";
    public static final String descriptionColumn = "description";
    public static final String checkedColumn = "checked";
    public static final String created_onColumn = "created";

    public static final String createTable = "CREATE TABLE " + tableName + "("
            + idColumn + " INTEGER PRIMARY KEY AUTOINCREMENT," + titleColumn + " TEXT,"
            + descriptionColumn + " TEXT," +
            checkedColumn + " BOOLEAN," +
            created_onColumn + " DATE" +")";

    public static final String DropTable = "DROP TABLE IF EXISTS " + tableName;

    public int id;
    public String title;
    public String description;
    public boolean checked;
    public Date created_on;

    public TaskItem(int id, boolean checked, String title, String description, Date created_on) {
        this.id = id;
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
