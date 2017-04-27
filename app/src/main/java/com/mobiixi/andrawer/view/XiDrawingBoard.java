package com.mobiixi.andrawer.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.mobiixi.andrawer.R;

/**
 * Created by xichen on 24/12/16.
 *
 *  To draw something, you need 4 basic components:
 *  - a Bitmap to hold the pixels,
 *  - a Canvas to host the draw calls (writing into the bitmap),
 *  - a drawing primitive (e.g. Rect, Path, text, Bitmap),
 *  - a paint (to describe the colors and styles for the drawing).
 */

public class XiDrawingBoard extends View {

    //drawing path
    private Path mDrawPath;

    private Paint mDrawPaint;

    //initial color
    private int mPaintColor = 0xFF660000;
    //canvas
    private Canvas mCanvas;
    //bitmap of canvas
    private Bitmap mCanvasBitmap;
    // the paint used to draw the bitmap of canvas
    private Paint mCanvasBitmapPaint;

    private float mBrushSize;

    public XiDrawingBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
        mDrawPath = new Path();
        initDrawPaint();
        mCanvasBitmapPaint = new Paint(Paint.DITHER_FLAG);
    }

    public void startNewDrawing() {
        // clear canvas
        mCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        // update drawing board view
        invalidate();
    }

    public void setPathColor(String newColor){
        mPaintColor = Color.parseColor(newColor);
        mDrawPaint.setColor(mPaintColor);
        invalidate();
    }

    public void setBrushSize(float size) {
        setEraser(false);
        changePainterSize(size);
    }

    public void setEraserSize(float size) {
        setEraser(true);
        changePainterSize(size);
    }

    public void setEraser(boolean shouldErase) {
        if (shouldErase) {
            // this app uses white background,
            // Porter Duff Clears the erased part of the bitmap ended up
            // with transparent black pixels (value 0x00000000), so I don't use it,
            // instead, I just set color to white for erasing.
            //mDrawPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
            mDrawPaint.setColor(Color.WHITE);
        } else {
            mDrawPaint.setXfermode(null);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // start drawing path
                mDrawPath.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                // add path line along with touch
                mDrawPath.lineTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_UP:
                // finger lift up, we draw the path
                mCanvas.drawPath(mDrawPath, mDrawPaint);
                mDrawPath.reset();
                break;
            default:
                return false;
        }

        // trigger onDraw() callback by calling invalidate()
        invalidate();
        return true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mCanvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mCanvasBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float leftPos = 0;
        float topPos = 0;
        canvas.drawBitmap(mCanvasBitmap, leftPos, topPos, mCanvasBitmapPaint);
        canvas.drawPath(mDrawPath, mDrawPaint);
    }

    private void changePainterSize(float size) {
        float sizeInPixel = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                                                      size,
                                                      getResources().getDisplayMetrics());
        mBrushSize = sizeInPixel;
        mDrawPaint.setStrokeWidth(mBrushSize);
    }

    private void initDrawPaint() {
        mBrushSize = getResources().getInteger(R.integer.medium_size);
        mDrawPaint = new Paint();
        mDrawPaint.setColor(mPaintColor);
        mDrawPaint.setAntiAlias(true);
        mDrawPaint.setStrokeWidth(mBrushSize);
        mDrawPaint.setStyle(Paint.Style.STROKE);
        mDrawPaint.setStrokeJoin(Paint.Join.ROUND);
        mDrawPaint.setStrokeCap(Paint.Cap.ROUND);
        setEraser(false);
    }
}
