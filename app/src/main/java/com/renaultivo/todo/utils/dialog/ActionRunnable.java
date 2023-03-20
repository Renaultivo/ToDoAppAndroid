package com.renaultivo.todo.utils.dialog;

import com.renaultivo.todo.data.TaskItem;

public abstract class ActionRunnable implements Runnable {

    public TaskItem taskItem = null;

    public abstract void setTaskItem(TaskItem item);

}
