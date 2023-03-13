package com.renaultivo.todo.utils.dialog;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;

import com.renaultivo.todo.R;

public class DialogModal extends Dialog {


    public DialogModal(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_add_task);

    }
}
