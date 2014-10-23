package com.bmob.im.demo.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bmob.im.demo.R;
import com.bmob.im.demo.util.Constant;

public class CityAdapter extends BaseAdapter {
	private ArrayList<HashMap<String, String>> contactList;
	private Activity activity;

	public void setContactList(ArrayList<HashMap<String, String>> contactList) {
		this.contactList = contactList;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	@Override
	public int getCount() {
		return contactList.size();
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
				R.layout.weizhi_item_item, null);
		TextView tv = (TextView) convertView.findViewById(R.id.rb_weizhi);
		tv.setText(contactList.get(position).get(Constant.CITY_QUYUNAME));

		return convertView;
	}

}
