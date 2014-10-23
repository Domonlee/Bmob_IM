package com.bmob.im.demo.lock;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.Scroller;

public class ScrollRelativeLayout extends RelativeLayout {

	private Scroller mScroller;

	public ScrollRelativeLayout(Context context) {
		// TODO Auto-generated constructor stub
		super(context);
		mScroller = new Scroller(context);
	}

	public ScrollRelativeLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		mScroller = new Scroller(context);
	}

	public ScrollRelativeLayout(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		mScroller = new Scroller(context);
	}

	/**
	 * 调用startScroll()是不会有滚动效果的，只有在computeScroll()获取滚动情况，做出滚动的响应
	 */
	@Override
	public void computeScroll() {
		if (mScroller.computeScrollOffset()) {
			scrollTo(mScroller.getCurrX(), 0);
			postInvalidate();
		}
	}

	/**
	 * 这个方法，当用户手指滑动的时候，更新位置
	 * 
	 * @param dx
	 */
	public void beginScroll(int dx) {
		mScroller.startScroll(0, 0, dx, 0, 0);
		invalidate();
	}

	/**
	 * 当用户松手的时候，进行修正位置
	 * 
	 * @param x
	 * @param dx
	 * @param y
	 * @param dy
	 * @param duration
	 */

	public void autoScroll(int x, int y, int dx, int dy, int duration) {
		mScroller.startScroll(x, y, dx, dy, duration);
		invalidate();
	}

	/**
	 * 返回当前的x
	 * 
	 * @return
	 */
	public int getCurrX() {
		return mScroller.getCurrX();
	}

	/**
	 * 返回当前的y
	 * 
	 * @return
	 */
	public int getCurrY() {
		return mScroller.getCurrY();
	}
}
