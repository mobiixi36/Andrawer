package com.mobiixi.andrawer.tools;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.View;
import android.widget.ImageButton;

import com.mobiixi.andrawer.view.XiDrawingBoard;

/**
 * This class handles drawing tools on toolbar.
 *
 * Created by xichen on 02/01/17.
 */

public class XiToolBar implements XiToolBarListener {
    private Context mContext;

    private XiDrawingBoard mDrawingBoard;
    private XiNew mNew;
    private XiBrush mBrush;
    private XiEraser mEraser;
    private XiPalette mPalette;
    private XiDisk mDiskTool;

    public XiToolBar(Context context) {
        mContext = context;
    }

    public void setDrawingBoard(XiDrawingBoard board) {
        mDrawingBoard = board;
        mDrawingBoard.setDrawingCacheEnabled(true);
    }

    public void addNewDrawing(ImageButton btn) {
        mNew = new XiNew(mContext);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNew.popUpDialog(mDrawingBoard);
            }
        });
    }

    public void addBrush(final ImageButton btn) {
        mBrush = new XiBrush(mContext, this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBrush.popUpMenuOptions(btn);
            }
        });
    }

    public void addEraser(final ImageButton btn) {
        mEraser = new XiEraser(mContext, this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEraser.popUpMenuOptions(btn);
            }
        });
    }

    public void addPalette(final ImageButton btn) {
        mPalette = new XiPalette(mContext, this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPalette.popUpMenuOptions(btn);
            }
        });
    }

    public void addSaveTool(final ImageButton btn) {
        mDiskTool = new XiDisk(mContext);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDiskTool.popupDialog(mDrawingBoard);
            }
        });
    }

    @Override
    public void onBrushSizeChanged(float value) {
        mDrawingBoard.setBrushSize(value);
    }

    @Override
    public void onEraserSizeChanged(float value) {
        mDrawingBoard.setEraserSize(value);
    }

    @Override
    public void onColorSelected(String color) {
        mDrawingBoard.setPathColor(color);
    }

}
