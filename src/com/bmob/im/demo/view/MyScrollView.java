package com.bmob.im.demo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * @author Domm(����) E-mail:viplizhao@gmail.com
 * @version ����ʱ�䣺2014-9-1 ����10:22:29
 * �Զ���ScrollView���������в������е�onTouchEven 
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

	// �������������� true �Ļ�
	// ������ָ�ƶ�������һ�����µ���ָ���ƶ����ܱ�������ȥ��
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
