package com.mobiixi.andrawer.tools;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.provider.MediaStore;
import android.widget.Toast;

import com.mobiixi.andrawer.R;
import com.mobiixi.andrawer.view.XiConfirmDialog;
import com.mobiixi.andrawer.view.XiConfirmDialogListener;
import com.mobiixi.andrawer.view.XiDrawingBoard;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xichen on 06/01/17.
 */

public class XiDisk implements XiConfirmDialogListener {
    private XiConfirmDialog mDialog;
    private Context mContext;
    private XiDrawingBoard mDrawingBoard;
    public XiDisk(Context context) {
        mDialog = new XiConfirmDialog(context,
                                      R.layout.confirm_dialog,
                                      R.string.save_drawing,
                                      this);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mContext = context;
    }

    public void popupDialog(XiDrawingBoard drawingBoard) {
        mDrawingBoard = drawingBoard;
        mDialog.show();
    }

    @Override
    public void onDialogYesButtonClicked() {
        try {
            saveDrawing();
            showToastWithMsg(mContext.getResources().getString(R.string.drawing_save_suc));
        } catch (IOException e) {
            showToastWithMsg(mContext.getResources().getString(R.string.drawing_save_fail));
        } finally {
            mDrawingBoard.destroyDrawingCache();
            mDialog.dismiss();
        }
    }

    @Override
    public void onDialogNoButtonClicked() {
        mDialog.cancel();
    }

    private void saveDrawing() throws IOException {
        long milliSec = System.currentTimeMillis();
        Date now = new Date(milliSec);
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        String timeStr = formatter.format(now);
        String name = timeStr + "-andrawer.png";
        String imgUrl = MediaStore.Images.Media.insertImage(
                mContext.getContentResolver(), mDrawingBoard.getDrawingCache(),
                name, "Andrawer drawing");

        if (imgUrl == null) {
            throw new IOException("Failed to save drawing!");
        }
    }

    private void showToastWithMsg(String msg) {
        Toast toast = Toast.makeText(mContext,
                                          msg,
                                          Toast.LENGTH_SHORT);
        toast.show();
    }
}
