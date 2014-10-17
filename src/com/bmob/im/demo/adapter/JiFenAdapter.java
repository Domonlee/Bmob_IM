package com.bmob.im.demo.adapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import com.bmob.im.demo.R;
import com.bmob.im.demo.util.CollectionUtils;
import com.bmob.im.demo.util.Constant;
import com.bmob.im.demo.util.HeaderViewUtil;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class JiFenAdapter extends BaseAdapter {
	private ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
	private Activity activity;

	public void setList(ArrayList<HashMap<String, String>> list) {
		this.list = list;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	@Override
	public int getCount() {
		return list.size() + 1;
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
	public View getView(int position, View v, ViewGroup parent) {
		if (position == 0) {
			v = HeaderViewUtil.getHeaderView(activity);

		} else {

			v = LayoutInflater.from(activity).inflate(
					R.layout.listview_exchange_item, null);
			TextView biaoti = (TextView) v
					.findViewById(R.id.tv_exchange_item_info);
			TextView fbiaoti = (TextView) v
					.findViewById(R.id.tv_exchange_item_goodsname);
			TextView price = (TextView) v
					.findViewById(R.id.tv_exchange_item_price);

			biaoti.setText(list.get(position - 1).get(Constant.JIFEN_NAME));
			fbiaoti.setText(list.get(position - 1).get(Constant.JIFEN_SCORE));
			price.setText(list.get(position - 1).get(Constant.JIFEN_NUMBER));

		}

		return v;
	}
}
