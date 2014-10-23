package com.bmob.im.demo.adapter;

import com.bmob.im.demo.R;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsAdapter extends BaseAdapter {
	private Activity activity;

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	@Override
	public int getCount() {
		return 4;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = LayoutInflater.from(activity).inflate(
				R.layout.activity_new_item, null);

		ImageView imageView = (ImageView) convertView
				.findViewById(R.id.iv_new_img);
		TextView textView = (TextView) convertView
				.findViewById(R.id.tv_new_text);
		return convertView;
	}

}
