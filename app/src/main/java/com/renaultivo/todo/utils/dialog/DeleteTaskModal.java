package com.renaultivo.todo.utils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.renaultivo.todo.R;
import com.renaultivo.todo.data.TaskItem;

import java.util.Date;

public class DeleteTaskModal extends Dialog {


    public DeleteTaskModal(@NonNull Context context, TaskModalAction actions) {
        super(context);
        setContentView(R.layout.delete_task_modal);

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

        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                actions.delete();
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
