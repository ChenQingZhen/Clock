package com.cqz.clock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by chenqz on 2016/10/10.
 */
public class Clock extends View {
    private int mWidth;
    private int mHeight;
    //画圆的笔
    private Paint mCirclePaint;
    private int mCircleStrokeWidth = 5;
    //画刻度的笔
    private Paint mDegreePaint;

    private Paint mHourPaint;
    private Paint mMinutePaint;

    private Paint mCenterPaint;

    public Clock(Context context) {
        this(context, null);
    }

    public Clock(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        //画圆的笔
        mCirclePaint = new Paint();
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setStrokeWidth(mCircleStrokeWidth);

        mDegreePaint = new Paint();
        mDegreePaint.setAntiAlias(true);
        mDegreePaint.setTextSize(14);

        mHourPaint=new Paint();
        mHourPaint.setAntiAlias(true);


        mMinutePaint=new Paint();
        mMinutePaint.setAntiAlias(true);

        mCenterPaint=new Paint();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = measureWidth(widthMeasureSpec);
        mHeight = measureHeight(heightMeasureSpec);
        setMeasuredDimension(mWidth, mHeight);
    }

    private int measureWidth(int widthMeasureSpec) {
        int width;
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            width = specSize;
        } else {
            width = 80;
            if (specMode == MeasureSpec.AT_MOST) {
                width = Math.min(width, specSize);
            }
        }
        return width;
    }

    private int measureHeight(int heightMeasureSpec) {
        int height;
        int specMode = MeasureSpec.getMode(heightMeasureSpec);
        int specSize = MeasureSpec.getSize(heightMeasureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            height = specSize;
        } else {
            height = 80;
            if (specMode == MeasureSpec.AT_MOST) {
                height = Math.min(height, specSize);
            }
        }
        return height;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mWidth / 2, mHeight / 2, mWidth / 2 - mCircleStrokeWidth, mCirclePaint);
        canvas.save();
        canvas.translate(mWidth/2,0);
        for (int i = 0; i < 60; i++) {
            if (i % 5 == 0) {
                mDegreePaint.setStrokeWidth(5);
                canvas.drawLine(0,mCircleStrokeWidth,0,mCircleStrokeWidth+mHeight/10,mDegreePaint);
                String text=String.valueOf(i/5);
                canvas.drawText(text,0-mDegreePaint.measureText(text)/2,mCircleStrokeWidth+mHeight/10+15,mDegreePaint);
            } else {
                mDegreePaint.setStrokeWidth(3);
                canvas.drawLine(0,mCircleStrokeWidth,0,mCircleStrokeWidth+mHeight/20,mDegreePaint);
            }
            canvas.rotate(6,0,mHeight/2);
        }
        canvas.restore();

        mCenterPaint.setStrokeWidth(mHeight/15);
        canvas.drawPoint(mWidth/2,mHeight/2,mCenterPaint);
        mHourPaint.setStrokeWidth(mHeight/20);
        mMinutePaint.setStrokeWidth(mHeight/40);
        canvas.translate(mWidth/2,mHeight/2);
        canvas.drawLine(0,0,0,-mHeight/4,mHourPaint);
        canvas.drawLine(0,0,mHeight/3,0,mMinutePaint);
        canvas.restore();

    }
}
