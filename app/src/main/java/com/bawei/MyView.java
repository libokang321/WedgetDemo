package com.bawei;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 *@Auther:libokang
 *@Date: 时间
 *@Description:功能
 * */
public class MyView extends View {

    private Paint dotPaint;
    private Paint rectPaint;
    private List<Point> list = new ArrayList<>();
    private Paint sdoPaint;

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        //圆点画笔
        dotPaint = new Paint();
        dotPaint.setColor(Color.RED);
        dotPaint.setAntiAlias(true);
        dotPaint.setStyle(Paint.Style.FILL);
        //方框画笔
        rectPaint = new Paint();
        rectPaint.setColor(Color.BLUE);
        rectPaint.setAntiAlias(true);
        rectPaint.setStrokeWidth(8);
        rectPaint.setStyle(Paint.Style.STROKE);
        //
        sdoPaint = new Paint();
        sdoPaint.setColor(Color.GRAY);
        sdoPaint.setAntiAlias(true);
        sdoPaint.setStyle(Paint.Style.STROKE);

    }

    private int x, y;
    private int destX, destY;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (Point point : list) {
            if (point.x > x && point.x < destX && point.y > y && point.y < destY) {
                canvas.drawCircle(point.x, point.y, 20, dotPaint);
            } else {
                canvas.drawCircle(point.x, point.y, 20, sdoPaint);
            }
        }
        canvas.drawRect(x, y, destX, destY, rectPaint);
    }

    /**
     * 添加小圆点
     */
    public void addDot() {
        int cx = new Random().nextInt(getScreenWidthOrHeight(true));
        int cy = new Random().nextInt(getScreenWidthOrHeight(false));
        Point point = new Point();
        point.x = cx;
        point.y = cy;
        list.add(point);
        invalidate();
    }

    /**
     * 删除小圆点
     */
    public void clearDot() {

        list.clear();
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = (int) event.getX();
                y = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                destX = (int) event.getX();
                destY = (int) event.getY();
                invalidate();
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return true;

    }

    public int getScreenWidthOrHeight(boolean width) {
        Resources resources = this.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        if (width) {
            return dm.widthPixels;
        } else {
            return dm.heightPixels;
        }

    }
}
