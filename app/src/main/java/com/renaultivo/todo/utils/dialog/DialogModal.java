package com.renaultivo.todo.utils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.renaultivo.todo.R;
import com.renaultivo.todo.data.TaskItem;

import java.util.Date;

public class DialogModal extends Dialog {


    public DialogModal(@NonNull Context context, TaskModalAction actions) {
        super(context);
        setContentView(R.layout.dialog_add_task);

        Button saveButton = findViewById(R.id.saveButton);
        Button cancelButton = findViewById(R.id.cancelButton);

        EditText title = findViewById(R.id.title);
        EditText description = findViewById(R.id.description);

        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                actions.save(new TaskItem(
                        false,
                        title.getText().toString(),
                        description.getText().toString(),
                        new Date()
                ));

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
