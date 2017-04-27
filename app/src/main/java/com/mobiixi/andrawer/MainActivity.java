package com.mobiixi.andrawer;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;

import com.mobiixi.andrawer.tools.XiToolBar;
import com.mobiixi.andrawer.view.XiDrawingBoard;

public class MainActivity extends AppCompatActivity {
    private XiToolBar mToolBar;
    private XiDrawingBoard mDrawingBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        XiPermissionUtils.requestPermissionIfNecessary(XiPermissionUtils.XI_REQUEST_WRITE_EXTERNAL_STORAGE,
                                                       this);

        mDrawingBoard = (XiDrawingBoard) findViewById(R.id.drawingBoard);

        ImageButton newDrawingBtn = (ImageButton) findViewById(R.id.new_drawing_btn);
        ImageButton brushBtn = (ImageButton) findViewById(R.id.brush_btn);
        ImageButton paletteBtn = (ImageButton) findViewById(R.id.palette_btn);
        ImageButton eraserBtn = (ImageButton) findViewById(R.id.eraser_btn);
        ImageButton saveImgBtn = (ImageButton) findViewById(R.id.save_btn);

        mToolBar = new XiToolBar(this);
        mToolBar.setDrawingBoard(mDrawingBoard);

        mToolBar.addNewDrawing(newDrawingBtn);
        mToolBar.addBrush(brushBtn);
        mToolBar.addEraser(eraserBtn);
        mToolBar.addPalette(paletteBtn);
        mToolBar.addSaveTool(saveImgBtn);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        XiPermissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
