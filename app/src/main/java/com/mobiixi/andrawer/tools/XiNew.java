package com.mobiixi.andrawer.tools;

import android.content.Context;

import com.mobiixi.andrawer.R;
import com.mobiixi.andrawer.view.XiConfirmDialog;
import com.mobiixi.andrawer.view.XiConfirmDialogListener;
import com.mobiixi.andrawer.view.XiDrawingBoard;

/**
 * Created by xichen on 10/01/17.
 */

public class XiNew implements XiConfirmDialogListener {
    private XiConfirmDialog mDialog;
    private XiDrawingBoard mDrawingBoard;

    public XiNew(Context context) {
        mDialog = new XiConfirmDialog(context,
                                      R.layout.confirm_dialog,
                                      R.string.start_new_drawing,
                                      this);
    }

    public void popUpDialog(XiDrawingBoard drawingBoard) {
        mDrawingBoard = drawingBoard;
        mDialog.show();
    }

    @Override
    public void onDialogYesButtonClicked() {
        mDrawingBoard.startNewDrawing();
        mDialog.dismiss();
    }

    @Override
    public void onDialogNoButtonClicked() {
        mDialog.cancel();
    }
}
