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

import java.util.ArrayList;
import java.util.List;

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

    private int numOfXPoints = 5;    //横坐标显示点的个数
    private int maxValue = 100;     //最大值
    private int warningValue = 80;  //警戒值
    private int period = 3000;  //循环周期

    private String tempString;

    private List<String> nameList = new ArrayList<>();

    private int[] colors = {Color.BLUE, Color.DKGRAY, Color.GRAY, Color.MAGENTA};

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

    public int getNumOfXPoints() {
        return numOfXPoints;
    }

    public void setNumOfXPoints(int numOfXPoints) {
        this.numOfXPoints = numOfXPoints;
        loopList.setLength(this.numOfXPoints);
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getWarningValue() {
        return warningValue;
    }

    public void setWarningValue(int warningValue) {
        this.warningValue = warningValue;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
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
            if (end - start < period) {
                try {
                    Thread.sleep(period - (end - start));
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
        loopList = new LoopList<>(numOfXPoints);
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
            for (int i = 0; i < numOfXPoints; i++) {
                mCanvas.drawLine(margin + i * (viewWidth - 2 * margin) / (numOfXPoints - 1), viewHeight - margin, margin + i * (viewWidth - 2 * margin) / (numOfXPoints - 1), 0, mPaint);
            }
            //绘制警戒线
            mPaint.setColor(Color.RED);
            mCanvas.drawLine(
                    0,
                    (float) (1 - (warningValue / 100.0)) * (viewHeight - margin),
                    viewWidth,
                    (float) (1 - (warningValue / 100.0)) * (viewHeight - margin),
                    mPaint);
            update(mCanvas, mPaint);
            mPaint.setTextSize(20);
            //绘制标识
            for (int i = 0; i < loopList.getNumOfValues(); i++) {
                mPaint.setColor(colors[i]);
                mCanvas.drawCircle((viewWidth - margin * 2) * i / (loopList.getNumOfValues() - 1) + margin / 3,
                        viewHeight - margin / 3,
                        10,
                        mPaint);
                if (nameList.get(i) == null) {
                    tempString = i + "";
                } else {
                    tempString = nameList.get(i);
                }
                mCanvas.drawText(
                        tempString,
                        (viewWidth - margin * 2) * i / (loopList.getNumOfValues() - 1) + 4 * margin / 9,
                        viewHeight - margin / 4,
                        mPaint
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (mCanvas != null) {
                mHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }

    /**
     * 更新折线
     * <p>
     * 1.绘制横坐标
     * 2.绘制网格（用虚线）
     * 3.绘制曲线，使用drawline
     * 4.绘制节点（每一个节点上都要显示数据）
     * 5.循环移动画布
     *
     * @param mCanvas
     * @param mPaint
     */
    private void update(Canvas mCanvas, Paint mPaint) {
        if (loopList != null) {
//            Log.i(TAG, loopList.toString());
            for (int i = 1; i < loopList.size(); i++) {
                if (loopList.size() == 1) {
                    break;
                }
                //4或者3
                for (int j = 0; j < loopList.getNumOfValues(); j++) {
                    //设置不同的画笔颜色
                    mPaint.setColor(colors[j]);
                    mPaint.setPathEffect(new PathEffect());

                    /**
                     * 绘制折线
                     */
                    mCanvas.drawLine(
                            margin + (numOfXPoints - loopList.size() + i - 1) * (viewWidth - 2 * margin) / (numOfXPoints - 1),
                            (float) (1 - (loopList.get(i - 1).getValues().get(j) / maxValue)) * (viewHeight - margin),
                            margin + (numOfXPoints - loopList.size() + i) * (viewWidth - 2 * margin) / (numOfXPoints - 1),
                            (float) (1 - (loopList.get(i).getValues().get(j) / maxValue)) * (viewHeight - margin),
                            mPaint);
                    if (loopList.get(i).getValues().get(j) < warningValue) {    //正常数值
                        mPaint.setColor(colors[j]);
                    } else {    //警戒数值
                        mPaint.setColor(Color.RED);
                    }

                    /**
                     * 绘制节点
                     */
                    mCanvas.drawCircle(
                            margin + (numOfXPoints - loopList.size() + i) * (viewWidth - 2 * margin) / (numOfXPoints - 1),
                            (float) (1 - (loopList.get(i).getValues().get(j) / maxValue)) * (viewHeight - margin),
                            10,
                            mPaint);

                    /**
                     * 绘制参数
                     */
                    mPaint.setTextSize(30);
                    mCanvas.drawText(
                            loopList.get(i).getValues().get(j).intValue() + "",
                            margin + (numOfXPoints - loopList.size() + i) * (viewWidth - 2 * margin) / (numOfXPoints - 1) - margin / 2,
                            (float) (1 - (loopList.get(i).getValues().get(j) / maxValue)) * (viewHeight - margin) + 10,
                            mPaint);
                }

                /**
                 * 绘制时间
                 */
                mPaint.setColor(Color.BLACK);
                mPaint.setTextSize(30);
                mCanvas.drawText(
                        loopList.get(i).getSecond() + "",
                        margin + (numOfXPoints - loopList.size() + i) * (viewWidth - 2 * margin) / (numOfXPoints - 1),
                        viewHeight - margin / 2,
                        mPaint);

            }
            /**
             * 绘制第一个节点
             */
            if (loopList.size() > 0) {

                for (int j = 0; j < loopList.getNumOfValues(); j++) {
                    mPaint.setPathEffect(new PathEffect());

                    if (loopList.get(0).getValues().get(j) < warningValue) {
                        mPaint.setColor(colors[j]);
                    } else {
                        mPaint.setColor(Color.RED);
                    }

                    /**
                     * 绘制节点
                     */
                    mCanvas.drawCircle(
                            margin + (numOfXPoints - loopList.size()) * (viewWidth - 2 * margin) / (numOfXPoints - 1),
                            (float) ((1 - (loopList.get(0).getValues().get(j) / maxValue)) * (viewHeight - margin)),
                            10,
                            mPaint);

                    /**
                     * 绘制参数
                     */
                    mPaint.setTextSize(30);
                    mCanvas.drawText(
                            loopList.get(0).getValues().get(j).intValue() + "",
                            margin + (numOfXPoints - loopList.size() + 0) * (viewWidth - 2 * margin) / (numOfXPoints - 1) - margin / 2,
                            (float) (1 - (loopList.get(0).getValues().get(j) / maxValue)) * (viewHeight - margin) + 10,
                            mPaint);


                }

                /**
                 * 绘制时间
                 */
                mPaint.setColor(Color.BLACK);
                mPaint.setTextSize(30);
                mCanvas.drawText(
                        loopList.get(0).getSecond() + "",
                        margin + (numOfXPoints - loopList.size() + 0) * (viewWidth - 2 * margin) / (numOfXPoints - 1),
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

    public void setLoopListNumOfValues(int num) {
        loopList.setNumOfValues(num);
    }

    public void pushName(String name) {
        nameList.add(name);
    }

    public void show(String tag) {
        Log.i(TAG, tag + " Start");
        Log.i(TAG, loopList.get(loopList.size()).toString());
        Log.i(TAG, "Over");
    }
}
