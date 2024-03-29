package com.renaultivo.todo;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.renaultivo.todo.data.TaskItem;
import com.renaultivo.todo.utils.db.DB;
import com.renaultivo.todo.utils.dialog.ActionRunnable;
import com.renaultivo.todo.utils.dialog.DeleteTaskModal;
import com.renaultivo.todo.utils.dialog.DialogModal;
import com.renaultivo.todo.utils.dialog.EditTaskModal;
import com.renaultivo.todo.utils.dialog.TaskModalAction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends Activity {

    DB db;

    private LinearLayout buildTaskUI_Item(TaskItem taskItem) {

        LinearLayout layout = new LinearLayout(this);
        CheckBox checkBox = new CheckBox(this);
        TextView textView = new TextView(this);

        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setBackgroundResource(R.drawable.task_item_background);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        layoutParams.setMargins(50, 20, 50, 20);

        layout.setLayoutParams(layoutParams);

        textView.setTextColor(Color.parseColor("#DDDDDD"));

        LinearLayout.LayoutParams checkBoxLayoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        checkBoxLayoutParams.setMargins(10, 10, 0, 10);

        checkBox.setLayoutParams(checkBoxLayoutParams);

        checkBox.setChecked(taskItem.checked);

        checkBox.setChecked(taskItem.checked);

        LinearLayout.LayoutParams textLayoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        textLayoutParams.setMargins(5, 10, 10, 10);

        textView.setLayoutParams(textLayoutParams);

        textView.setText(taskItem.title);

        textView.setPadding(10, 10, 10, 10);

        layout.addView(checkBox);
        layout.addView(textView);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                taskItem.checked = isChecked;

                db.editTask(taskItem);

            }

        });

        Context context = this;

        layout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                new EditTaskModal(context, taskItem, new TaskModalAction(
                        new ActionRunnable() {
                            @Override
                            public void setTaskItem(TaskItem item) {
                                this.taskItem = item;
                            }

                            @Override
                            public void run() {

                                db.editTask(this.taskItem);
                                textView.setText(this.taskItem.title);

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(context, "Task updated!", Toast.LENGTH_LONG).show();
                                    }
                                });

                            }
                        },
                        new ActionRunnable() {

                            @Override
                            public void setTaskItem(TaskItem item) {
                                this.taskItem = item;
                            }

                            @Override
                            public void run() {

                            }

                        }
                )).show();

            }

        });

        layout.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {

                new DeleteTaskModal(context, new TaskModalAction(new Runnable() {

                    @Override
                    public void run() {

                        db.deleteTask(taskItem);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                LinearLayout parent = (LinearLayout) layout.getParent();
                                parent.removeView(layout);

                                Toast.makeText(context, "Task \"" + taskItem.title + "\" was deleted", Toast.LENGTH_LONG).show();

                            }
                        });

                    }

                })).show();

                return false;

            }
        });

        return layout;

    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.actitivty_main);

        LinearLayout taskListLayout = findViewById(R.id.taskListLayout);
        Button addTaskButton = findViewById(R.id.addTaskButton);

        db = new DB(this);

        ArrayList<TaskItem> taskList = db.listTask();

        /*try {
            TaskItem taskItem1 = new TaskItem(true,"teste","descricaoTeste", new SimpleDateFormat("yyyy-MM-dd").parse("2023-02-02"));
            db = new DB(this);
            db.CreateNewTask(taskItem1);
            TaskItem taskItem2 = new TaskItem(false,"testeEdit","descricaoTesteEdit", new SimpleDateFormat("yyyy-MM-dd").parse("2022-07-03"));
            db.EditTask(taskItem2);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }*/

        /*try {
            taskList.add(new TaskItem(true, "Primeiro", "Primeiro description", new SimpleDateFormat("yyyy-MM-dd").parse("2023-02-02")));
            taskList.add(new TaskItem(true, "Segundo", "Segundo description", new SimpleDateFormat("yyyy-MM-dd").parse("2023-02-02")));
            taskList.add(new TaskItem(true, "Terceiro", "Terceiro description", new SimpleDateFormat("yyyy-MM-dd").parse("2023-02-02")));
            taskList.add(new TaskItem(true, "Quarto Quarto description Quarto description Quarto description Quarto description Quarto description Quarto description Quarto description Quarto description Quarto description Quarto description Quarto description Quarto description", "Quarto description Quarto description Quarto description Quarto description Quarto description Quarto description Quarto description Quarto description", new SimpleDateFormat("yyyy-MM-dd").parse("2023-02-02")));
            taskList.add(new TaskItem(true, "Quinto", "Quinto description", new SimpleDateFormat("yyyy-MM-dd").parse("2023-02-02")));
            taskList.add(new TaskItem(true, "Sexto", "Sexto description", new SimpleDateFormat("yyyy-MM-dd").parse("2023-02-02")));
            taskList.add(new TaskItem(true, "Setimo", "Setimo description", new SimpleDateFormat("yyyy-MM-dd").parse("2023-02-02")));
            taskList.add(new TaskItem(true, "Oitavo", "Oitavo description", new SimpleDateFormat("yyyy-MM-dd").parse("2023-02-02")));
            taskList.add(new TaskItem(true, "Nono", "Nono description", new SimpleDateFormat("yyyy-MM-dd").parse("2023-02-02")));
            taskList.add(new TaskItem(true, "Decimo", "Decimo description", new SimpleDateFormat("yyyy-MM-dd").parse("2023-02-02")));
            taskList.add(new TaskItem(true, "Decimo-primeiro", "Decimo-primeiro description", new SimpleDateFormat("yyyy-MM-dd").parse("2023-02-02")));
            taskList.add(new TaskItem(true, "Decimo-Segundo", "Decimo-Segundo description", new SimpleDateFormat("yyyy-MM-dd").parse("2023-02-02")));
            taskList.add(new TaskItem(true, "Decimo-Terceiro", "Decimo-Terceiro description", new SimpleDateFormat("yyyy-MM-dd").parse("2023-02-02")));
            taskList.add(new TaskItem(true, "Decimo-Quarto", "Decimo-Quarto description", new SimpleDateFormat("yyyy-MM-dd").parse("2023-02-02")));
            taskList.add(new TaskItem(true, "Decimo-Quinto", "Decimo-Quinto description", new SimpleDateFormat("yyyy-MM-dd").parse("2023-02-02")));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }*/

        for (TaskItem item : taskList) {
            System.out.println("---------------- START ITEM ------------------");
            System.out.println("ID:" + item.id);
            System.out.println("checked:" + item.checked);
            System.out.println("title:" + item.title);
            System.out.println("description:" + item.description);
            System.out.println("----------------- END ITEM -----------------");
            taskListLayout.addView(buildTaskUI_Item(item));
            //db.CreateNewTask(item);
        }

        Context context = this;

        TaskModalAction taskModalAction = new TaskModalAction(
                new ActionRunnable() {
                    @Override
                    public void setTaskItem(TaskItem item) {
                        this.taskItem = item;
                    }

                    @Override
                    public void run() {
                        this.taskItem = db.createNewTask(this.taskItem);
                        taskListLayout.addView(buildTaskUI_Item(this.taskItem));
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context, "Task created!", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                },
                new ActionRunnable() {

                    @Override
                    public void setTaskItem(TaskItem item) {
                        this.taskItem = item;
                    }

                    @Override
                    public void run() {

                    }

                }
        );

        addTaskButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                new DialogModal(context, taskModalAction).show();

            }

        });

        System.out.println(taskList);

    }

}
