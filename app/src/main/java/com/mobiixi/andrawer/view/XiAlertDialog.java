package com.mobiixi.andrawer.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.mobiixi.andrawer.R;


/**
 * Created by xichen on 08/01/17.
 */

public class XiAlertDialog extends Dialog {

    private int mMessageRes;
    private View.OnClickListener mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.alert_dialog);
        XiSketchyTextView contentView = (XiSketchyTextView) findViewById(R.id.alert_message);
        contentView.setText(mMessageRes);

        View okBtn = findViewById(R.id.dialog_ok);
        okBtn.setOnClickListener(mListener);
    }

    public XiAlertDialog(Context context, int messageRes, View.OnClickListener listener) {
        super(context);
        mMessageRes = messageRes;
        mListener = listener;
    }
}
