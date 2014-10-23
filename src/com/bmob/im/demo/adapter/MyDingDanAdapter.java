package com.bmob.im.demo.adapter;

import java.util.ArrayList;

import com.bmob.im.demo.R;
import com.bmob.im.demo.bean.MyDianDan;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyDingDanAdapter extends BaseAdapter {
	private ArrayList<MyDianDan> list;
	private TextView biaoti;
	private TextView fbiaoti;
	private TextView price;
	private Activity activity;

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public void setList(ArrayList<MyDianDan> list) {
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
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

		v = LayoutInflater.from(activity).inflate(
				R.layout.listview_exchange_item, null);
		biaoti = (TextView) v.findViewById(R.id.tv_exchange_item_info);
		fbiaoti = (TextView) v.findViewById(R.id.tv_exchange_item_goodsname);
		price = (TextView) v.findViewById(R.id.tv_exchange_item_price);

		biaoti.setText(list.get(position).getTgbiaoti());
		fbiaoti.setText(list.get(position).getFubiaoti());
		price.setText(list.get(position).getTgprice());

		return v;
	}
}
