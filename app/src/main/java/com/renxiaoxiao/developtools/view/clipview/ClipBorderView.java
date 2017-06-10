package com.renxiaoxiao.developtools.view.clipview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Region;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * 圆形透明裁剪框
 */
public class ClipBorderView extends View {

  private int mHorizontalPadding;

  private int mBorderWidth = 1;

  private Paint mPaint;

  public ClipBorderView(Context context) {
    this(context, null);
  }

  public ClipBorderView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public ClipBorderView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);

    mBorderWidth = (int) TypedValue.applyDimension(
      TypedValue.COMPLEX_UNIT_DIP, mBorderWidth, getResources()
        .getDisplayMetrics());
    mPaint = new Paint();
    mPaint.setAntiAlias(true);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    Path path = new Path();
    Rect viewDrawingRect = new Rect();
    this.getDrawingRect(viewDrawingRect);

    float radius = (getWidth() - 2 * mHorizontalPadding) / 2;

    path.addCircle(getWidth() / 2, getHeight() / 2, radius, Path.Direction.CW);

    mPaint.setColor(Color.parseColor("#aa000000"));

    canvas.clipPath(path, Region.Op.DIFFERENCE);
    canvas.drawRect(viewDrawingRect, mPaint);
  }

  public void setHorizontalPadding(int mHorizontalPadding) {
    this.mHorizontalPadding = mHorizontalPadding;
  }

}
