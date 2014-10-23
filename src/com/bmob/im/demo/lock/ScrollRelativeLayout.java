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
	 * ����startScroll()�ǲ����й���Ч���ģ�ֻ����computeScroll()��ȡ���������������������Ӧ
	 */
	@Override
	public void computeScroll() {
		if (mScroller.computeScrollOffset()) {
			scrollTo(mScroller.getCurrX(), 0);
			postInvalidate();
		}
	}

	/**
	 * ������������û���ָ������ʱ�򣬸���λ��
	 * 
	 * @param dx
	 */
	public void beginScroll(int dx) {
		mScroller.startScroll(0, 0, dx, 0, 0);
		invalidate();
	}

	/**
	 * ���û����ֵ�ʱ�򣬽�������λ��
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
	 * ���ص�ǰ��x
	 * 
	 * @return
	 */
	public int getCurrX() {
		return mScroller.getCurrX();
	}

	/**
	 * ���ص�ǰ��y
	 * 
	 * @return
	 */
	public int getCurrY() {
		return mScroller.getCurrY();
	}
}
