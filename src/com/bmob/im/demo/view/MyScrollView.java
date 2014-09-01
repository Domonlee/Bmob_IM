package com.bmob.im.demo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * @author Domm(李钊) E-mail:viplizhao@gmail.com
 * @version 创建时间：2014-9-1 上午10:22:29
 * 自定义ScrollView，用来进行捕获其中的onTouchEven 
 */
public class MyScrollView extends ScrollView {
	public MyScrollView(Context context) {
		super(context);

	}

	public MyScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);

	}

	public MyScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	// 这个方法如果返回 true 的话
	// 两个手指移动，启动一个按下的手指的移动不能被传播出去。
	@Override
	public boolean onInterceptTouchEvent(MotionEvent event) 
	{
		super.onInterceptTouchEvent(event);
		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		super.onTouchEvent(event);
		return false;
	}
}
