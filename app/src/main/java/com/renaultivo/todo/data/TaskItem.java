package com.renaultivo.todo.data;

import java.util.Date;

public class TaskItem {

    public static final String tableName = "Task";
    public static final String idColun = "idTask";
    public static final String titleColun = "title";
    public static final String descriptionColun = "description";
    public static final String checkedColun = "checked";
    public static final String created_onColun = "created";

    public static final String CreateTable = "CREATE TABLE " + tableName + "("
            + idColun + " INTEGER PRIMARY KEY," + titleColun + " TEXT,"
            + descriptionColun + " TEXT," +
            checkedColun + "BOOLEAN," +
            created_onColun + " DATE" +")";

    public static final String DropTable = "DROP TABLE IF EXISTS " + tableName;

    public String title;
    public String description;
    public boolean checked;

    public Date created_on;

    public TaskItem(boolean checked, String title, String description, Date created_on) {
        this.checked = checked;
        this.title = title;
        this.description = description;
        this.created_on = created_on;
    }


}
