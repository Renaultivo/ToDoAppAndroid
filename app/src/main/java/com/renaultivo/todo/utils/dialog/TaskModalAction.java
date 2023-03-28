package com.renaultivo.todo.utils.dialog;

import com.renaultivo.todo.data.TaskItem;

public class TaskModalAction implements Runnable {

    public static final int CREATE = 0;
    public static final int EDIT = 1;
    public static final int DELETE = 2;

    private ActionRunnable onSave;
    private Runnable onDelete;
    private ActionRunnable onCancel;

    public TaskModalAction(ActionRunnable onSave, ActionRunnable onCancel) {

        this.onSave = onSave;
        this.onCancel = onCancel;

    }
    public TaskModalAction(Runnable onDelete) {

        this.onDelete = onDelete;

        this.onCancel = new ActionRunnable() {

            @Override
            public void setTaskItem(TaskItem item) {

            }

            @Override
            public void run() {

            }

        };

    }


    public void delete() {
        this.onDelete.run();
    }

    public void save(TaskItem taskItem) {
        this.onSave.setTaskItem(taskItem);
        this.onSave.run();
    }

    public void cancel(TaskItem taskItem) {
        this.onSave.setTaskItem(taskItem);
        this.onCancel.run();
    }

    @Override
    public void run() {

    }

}
