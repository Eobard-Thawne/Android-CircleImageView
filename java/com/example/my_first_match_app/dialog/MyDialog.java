package com.example.my_first_match_app.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.my_first_match_app.R;

public class MyDialog extends Dialog implements View.OnClickListener {
    private String title, ipHints, portHints, confirmBtnStr, cancelBtnStr;
    private CancelClickListener cancelListener;
    private ConfirmClickListener confirmClickListener;

    public MyDialog(@NonNull Context context) {
        super(context);
    }

    public MyDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    public MyDialog setIPHints(String hints) {
        this.ipHints = hints;
        return this;
    }

    public MyDialog setPortHints(String hints) {
        this.portHints = hints;
        return this;
    }

    public MyDialog setConfirmTitle(String str) {
        this.confirmBtnStr = str;
        return this;
    }

    public MyDialog setCancelTitle(String str) {
        this.cancelBtnStr = str;
        return this;
    }

    public MyDialog setConfirm(ConfirmClickListener confirmClickListener) {
        this.confirmClickListener = confirmClickListener;
        return this;
    }

    public MyDialog setCancel(CancelClickListener cancelListener) {
        this.cancelListener = cancelListener;
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_dialog_item);
        TextView confirm = findViewById(R.id.confirm_tv);
        TextView cancel = findViewById(R.id.cancel_tv);
        TextView textView = findViewById(R.id.title_tv);
        EditText ipEt = findViewById(R.id.ip_et);
        EditText portEt = findViewById(R.id.port_et);
        if (!TextUtils.isEmpty(title)) {
            textView.setText(title);
        }
        if (!TextUtils.isEmpty(ipHints)) {
            ipEt.setHint(ipHints);
        }
        if (!TextUtils.isEmpty(portHints)) {
            portEt.setHint(portHints);
        }
        if (!TextUtils.isEmpty(confirmBtnStr)) {
            confirm.setText(confirmBtnStr);
        }
        if (!TextUtils.isEmpty(cancelBtnStr)) {
            cancel.setText(cancelBtnStr);
        }
        //将背景设置为透明,为了防止出现伪圆角
        Window window = getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        confirm.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.confirm_tv) {
            if (this.confirmClickListener != null) {
                confirmClickListener.onConfirmClick(this);
            }
        } else if (v.getId() == R.id.cancel_tv) {
            if (this.cancelListener != null) {
                cancelListener.onCancelClick(this);
            }
        }
    }

    public interface ConfirmClickListener {
        void onConfirmClick(MyDialog dialog);
    }

    public interface CancelClickListener {
        void onCancelClick(MyDialog dialog);
    }
}
