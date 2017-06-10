package com.renxiaoxiao.developtools.view.clipview;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.RelativeLayout;

/**
 * 最终xml界面调用的view
 */
public class ClipLayout extends RelativeLayout {

  private ClipImageView mImageView;
  private ClipBorderView mClipView;
  private Bitmap mBitmap;
  private int mHorizontalPadding = 10;

  public ClipLayout(Context context, AttributeSet attrs) {
    super(context, attrs);

    mImageView = new ClipImageView(context);
    mClipView = new ClipBorderView(context);

    android.view.ViewGroup.LayoutParams lp = new LayoutParams(
      android.view.ViewGroup.LayoutParams.MATCH_PARENT,
      android.view.ViewGroup.LayoutParams.MATCH_PARENT);

    this.addView(mImageView, lp);
    this.addView(mClipView, lp);

    mHorizontalPadding = (int) TypedValue.applyDimension(
      TypedValue.COMPLEX_UNIT_DIP, mHorizontalPadding, getResources()
        .getDisplayMetrics());
    mImageView.setHorizontalPadding(mHorizontalPadding);
    mClipView.setHorizontalPadding(mHorizontalPadding);
  }

  public void setHorizontalPadding(int mHorizontalPadding) {
    this.mHorizontalPadding = mHorizontalPadding;
    mImageView.setHorizontalPadding(mHorizontalPadding);
    mClipView.setHorizontalPadding(mHorizontalPadding);
  }

  public void setImageBackground(Bitmap bitmap) {
    this.mBitmap = bitmap;
    mImageView.setImageBitmap(mBitmap);
  }

  public void setClipBorderView(Context context) {
    mClipView = new ClipBorderView(context);
  }

  public Bitmap clip() {
    return mImageView.clip();
  }
}
