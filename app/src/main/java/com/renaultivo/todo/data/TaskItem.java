package com.renaultivo.todo.data;

import java.util.Date;

public class TaskItem {

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
