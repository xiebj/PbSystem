package com.example.administrator.pbsystem.ui.designView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.pbsystem.R;

/**
 * TODO: document your custom view class.
 */
public class MyView extends View {
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    //重写该方法绘制五角星花边图案
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(getResources().getColor(R.color.background));
        int viewWidth = this.getWidth();
        int viewHeight = this.getHeight();
        Paint paint = new Paint();
        //去锯齿
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        paint.setTextSize(48);
        canvas.drawText("请假", viewWidth / 2 - 48, viewHeight / 2 - 30, paint);
        canvas.drawRect(0, viewHeight / 2 + 20, viewWidth / 2 - 45, viewHeight / 2 + 28, paint);
        canvas.drawRect(viewWidth / 2 + 45, viewHeight / 2 + 20, viewWidth, viewHeight / 2 + 28, paint);
        Path path5 = new Path();
        path5.moveTo(viewWidth / 2 - 30, viewHeight / 2 + 24);
        path5.lineTo(viewWidth / 2 - 15, viewHeight / 2 + 24);
        path5.lineTo(viewWidth / 2, viewHeight / 2 + 12);
        path5.lineTo(viewWidth / 2 + 15, viewHeight / 2 + 24);
        path5.lineTo(viewWidth / 2 + 30, viewHeight / 2 + 24);
        path5.lineTo(viewWidth / 2 + 22, viewHeight / 2 + 35);
        path5.lineTo(viewWidth / 2 + 30, viewHeight / 2 + 50);
        path5.lineTo(viewWidth / 2, viewHeight / 2 + 35);
        path5.lineTo(viewWidth / 2 - 30, viewHeight / 2 + 50);
        path5.lineTo(viewWidth / 2 - 22, viewHeight / 2 + 35);
        path5.lineTo(viewWidth / 2 - 30, viewHeight / 2 + 24);
        path5.close();
        canvas.drawPath(path5, paint);
    }
}
