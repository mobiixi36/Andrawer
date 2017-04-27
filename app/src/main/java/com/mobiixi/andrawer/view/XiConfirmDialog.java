package com.mobiixi.andrawer.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.mobiixi.andrawer.R;

/**
 * Created by xichen on 06/01/17.
 */

public class XiConfirmDialog extends Dialog implements View.OnClickListener {

    private View mYesBtn;
    private View mNoBtn;
    private XiSketchyTextView mContent;
    private int mMessageRes;
    private int mLayoutId;
    private XiConfirmDialogListener mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(mLayoutId);
        mContent = (XiSketchyTextView) findViewById(R.id.message_content);
        mYesBtn = findViewById(R.id.dialog_yes);
        mNoBtn =  findViewById(R.id.dialog_no);

        mContent.setText(mMessageRes);
        mYesBtn.setOnClickListener(this);
        mNoBtn.setOnClickListener(this);
    }

    public XiConfirmDialog(Context context,
                           int layoutId,
                           int msgRes,
                           XiConfirmDialogListener listener) {
        super(context);
        mMessageRes = msgRes;
        mLayoutId = layoutId;
        mListener = listener;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dialog_yes:
                mListener.onDialogYesButtonClicked();
                break;
            case R.id.dialog_no:
                mListener.onDialogNoButtonClicked();
                break;
            default:
                break;
        }
    }

}
