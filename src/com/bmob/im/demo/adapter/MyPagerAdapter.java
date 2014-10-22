package com.bmob.im.demo.adapter;

import java.util.ArrayList;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

public class MyPagerAdapter extends PagerAdapter {
	private ArrayList<View> list;
	private RelativeLayout layout;

	public void setLayout(RelativeLayout layout) {
		this.layout = layout;
	}

	public void setList(ArrayList<View> list) {
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == (arg1);
	}

	@Override
	public void destroyItem(View container, int position, Object object) {
		((ViewPager) container).removeView(list.get(position));
	}

	@Override
	public Object instantiateItem(View container, int position) {
		((ViewPager) container).addView(list.get(position));

		list.get(position).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				layout.setVisibility(View.GONE);
			}
		});
		return list.get(position);
	}
}
