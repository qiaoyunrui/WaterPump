package com.juhezi.waterpump.Widgets;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.RelativeSizeSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.juhezi.waterpump.DataStructure.LoopList;
import com.juhezi.waterpump.DataStructure.Node;
import com.juhezi.waterpump.Other.Config;

/**
 * LineView
 *
 * @author: 乔云瑞
 * @time: 2016/4/18 11:47
 * <p/>
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
    public static final int PERIOD = 1000;

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
        loopList = new LoopList<>(5);
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
            //绘制警戒线
            mPaint.setColor(Color.RED);
            mCanvas.drawLine(
                    0,
                    (float) (1 - (Config.WARN_VALUE / 100.0)) * (viewHeight - margin),
                    viewWidth,
                    (float) (1 - (Config.WARN_VALUE / 100.0)) * (viewHeight - margin),
                    mPaint);
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
        if (loopList != null) {
//            Log.i(TAG, loopList.toString());
            for (int i = 1; i < loopList.size(); i++) {
                if (loopList.size() == 1) {
                    break;
                }
                mPaint.setColor(Color.BLUE);
                mPaint.setPathEffect(new PathEffect());
                /**
                 * 绘制折线
                 */
                mCanvas.drawLine(
                        margin + (5 - loopList.size() + i - 1) * (viewWidth - 2 * margin) / 4,
                        (float) (1 - (loopList.get(i - 1).getValue() / MAX_VALUE)) * (viewHeight - margin),
                        margin + (5 - loopList.size() + i) * (viewWidth - 2 * margin) / 4,
                        (float) (1 - (loopList.get(i).getValue() / MAX_VALUE)) * (viewHeight - margin),
                        mPaint);
                if (loopList.get(i).isState()) {
                    mPaint.setColor(Color.BLUE);
                } else {
                    mPaint.setColor(Color.RED);
                }
                /**
                 * 绘制节点
                 */
                mCanvas.drawCircle(
                        margin + (5 - loopList.size() + i) * (viewWidth - 2 * margin) / 4,
                        (float) (1 - (loopList.get(i).getValue() / MAX_VALUE)) * (viewHeight - margin),
                        10,
                        mPaint);
                /**
                 * 绘制参数
                 */
                mPaint.setTextSize(30);
                mCanvas.drawText(
                        (int) loopList.get(i).getValue() + "",
                        margin + (5 - loopList.size() + i) * (viewWidth - 2 * margin) / 4 - margin / 2,
                        (float) (1 - (loopList.get(i).getValue() / MAX_VALUE)) * (viewHeight - margin) + 10,
                        mPaint);
                /**
                 * 绘制时间
                 */
                mPaint.setColor(Color.BLACK);
                mPaint.setTextSize(30);
                mCanvas.drawText(
                        loopList.get(i).getSecond() + "",
                        margin + (5 - loopList.size() + i) * (viewWidth - 2 * margin) / 4,
                        viewHeight - margin / 2,
                        mPaint);
            }
            /**
             * 设置节点的颜色
             */
            if (loopList.size() > 0) {
                //mPaint.setStyle(Paint.Style.STROKE);
                mPaint.setPathEffect(new PathEffect());

                if (loopList.get(0).isState()) {
                    mPaint.setColor(Color.BLUE);
                } else {
                    mPaint.setColor(Color.RED);
                }
                /**
                 * 绘制节点
                 */
                mCanvas.drawCircle(
                        margin + (5 - loopList.size()) * (viewWidth - 2 * margin) / 4,
                        (float) ((1 - (loopList.get(0).getValue() / MAX_VALUE)) * (viewHeight - margin)),
                        10,
                        mPaint);
                /**
                 * 绘制参数
                 */
                mPaint.setTextSize(30);
                mCanvas.drawText(
                        (int) loopList.get(0).getValue() + "",
                        margin + (5 - loopList.size() + 0) * (viewWidth - 2 * margin) / 4 - margin / 2,
                        (float) (1 - (loopList.get(0).getValue() / MAX_VALUE)) * (viewHeight - margin) + 10,
                        mPaint);
                /**
                 * 绘制时间
                 */
                mPaint.setColor(Color.BLACK);
                mPaint.setTextSize(30);
                mCanvas.drawText(
                        loopList.get(0).getSecond() + "",
                        margin + (5 - loopList.size() + 0) * (viewWidth - 2 * margin) / 4,
                        viewHeight - margin / 2,
                        mPaint);
            }
        }
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
