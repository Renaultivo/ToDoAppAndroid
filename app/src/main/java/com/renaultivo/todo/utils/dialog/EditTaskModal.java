package com.renaultivo.todo.utils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.renaultivo.todo.R;
import com.renaultivo.todo.data.TaskItem;

public class EditTaskModal extends Dialog {


    public EditTaskModal(@NonNull Context context,  TaskItem taskItem, TaskModalAction actions) {
        super(context);
        setContentView(R.layout.edit_add_task);

        getWindow().setBackgroundDrawable(
                new ColorDrawable(
                        Color.TRANSPARENT
                )
        );

        getWindow().clearFlags(
                WindowManager.LayoutParams.FLAG_DIM_BEHIND
        );

        Button saveButton = findViewById(R.id.saveButton);
        Button cancelButton = findViewById(R.id.cancelButton);

        EditText title = findViewById(R.id.title);
        EditText description = findViewById(R.id.description);

        title.setText(taskItem.title);
        description.setText(taskItem.description);

        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                taskItem.title = title.getText().toString();
                taskItem.description = description.getText().toString();

                actions.save(taskItem);
                cancel();

            }

        });

        cancelButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cancel();
            }

        });

    }
}
