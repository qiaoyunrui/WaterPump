package com.juhezi.waterpump.Widgets;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.juhezi.waterpump.DataStructure.LoopList;
import com.juhezi.waterpump.DataStructure.Node;

/**
 * LineView
 *
 * @author: 乔云瑞
 * @time: 2016/4/18 11:47
 * <p>
 * 绘制折线图
 */
public class LineView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private static final String TAG = "LineView";

    private SurfaceHolder mHolder;
    private Canvas mCanvas;
    private boolean mIsDrawing;
    private Paint mPaint;

    private int viewHeight;
    private int viewWidth;
    private int margin;

    private LoopList<Node> loopList;

    public static final int MAX_VALUE = 100;
    public static final int PERIOD = 100;

    public LineView(Context context) {
        super(context);
        initView();
    }

    public LineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public LineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        viewHeight = getHeight();
        viewWidth = getWidth();
        margin = viewWidth / 7;
        mIsDrawing = true;
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mIsDrawing = false;
    }

    @Override
    public void run() {
        while (mIsDrawing) {
            long start = System.currentTimeMillis();
            draw();
            long end = System.currentTimeMillis();
            if (end - start < PERIOD) {
                try {
                    Thread.sleep(PERIOD - (end - start));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void initView() {
        mHolder = getHolder();
        mHolder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);
    }

    private void draw() {
        try {
            mCanvas = mHolder.lockCanvas();
            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            //绘制背景
            mCanvas.drawColor(Color.WHITE);
            //绘制横坐标
            mPaint.setColor(0xFF009394);
            mPaint.setStrokeWidth(5);
            mCanvas.drawLine(0, viewHeight - margin, viewWidth, viewHeight - margin, mPaint);

            //绘制纵向网格（虚线）
            PathEffect mPathEffect = new DashPathEffect(new float[]{20, 10, 5, 10}, 1);
            mPaint.setPathEffect(mPathEffect);
            mPaint.setStrokeWidth(2.5f);
            for (int i = 0; i < 5; i++) {
                mCanvas.drawLine(margin + i * (viewWidth - 2 * margin) / 4, viewHeight - margin, margin + i * (viewWidth - 2 * margin) / 4, 0, mPaint);
            }
            update(mCanvas, mPaint);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (mCanvas != null) {
                mHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }

    private void update(Canvas mCanvas, Paint mPaint) {

    }

    public void pushNode(Node node) {
        if (node != null) {
            loopList.push(node);
        }
    }

}

/**
 * 1.绘制横坐标
 * 2.绘制网格（用虚线）
 * 3.绘制曲线，使用drawline
 * 4.绘制节点（每一个节点上都要显示数据）
 * 5.循环移动画布
 */
