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



}
