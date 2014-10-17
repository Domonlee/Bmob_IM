package com.bmob.im.demo.view.task;

import java.util.ArrayList;
import java.util.HashMap;

import com.bmob.im.demo.R;
import com.bmob.im.demo.adapter.TuanGouAdapter;
import com.bmob.im.demo.adapter.XianShiGouAdapter;
import com.bmob.im.demo.ui.SingleGroupbyInfoActivity;
import com.bmob.im.demo.util.Constant;
import com.bmob.im.demo.util.HttpUtil;
import com.bmob.im.demo.util.JSONUtil;
import com.bmob.im.newview.XianShiItemActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class XianShiGouTask extends AsyncTask<Void, Void, Void> {
	private ListView list;
	private ProgressDialog pDialog;
	private Activity activity;
	private View view;
	private ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();

	public XianShiGouTask(Activity activity) {
		this.activity = activity;
		view = LayoutInflater.from(activity).inflate(
				R.layout.listview_footview, null);
	}

	public void setList(ListView list) {
		this.list = list;
	}

	@Override
	protected Void doInBackground(Void... params) {
		String jsonString = HttpUtil.httpGet(Constant.XIANSHIQIANGGOU);
		if (jsonString != null) {
			contactList = JSONUtil.getXianShiGou(jsonString);
		}
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		if (pDialog.isShowing()) {
			pDialog.dismiss();
		}
		// ListAdapter adapter = new SimpleAdapter(activity, contactList,
		// R.layout.listview_exchange_item, new String[] {
		// Constant.XIANSHI_ID, Constant.XIANSHI_BIAOTI,
		// Constant.XIANSHI_PRICE }, new int[] {
		// R.id.tv_exchange_item_info,
		// R.id.tv_exchange_item_goodsname,
		// R.id.tv_exchange_item_price });

		XianShiGouAdapter adapter = new XianShiGouAdapter();
		adapter.setActivity(activity);
		adapter.setList(contactList);
		list.setAdapter(adapter);
		if (list.getFooterViewsCount() == 0) {

			list.addFooterView(view);
		}
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int position,
					long id) {
				Intent intent = new Intent(activity, XianShiItemActivity.class);
				intent.putExtra(Constant.XIANSHI_ID,
						contactList.get(position - 1).get(Constant.XIANSHI_ID));
				intent.putExtra(
						Constant.XIANSHI_BIAOTI,
						contactList.get(position - 1).get(
								Constant.XIANSHI_BIAOTI));
				intent.putExtra(
						Constant.XIANSHI_FBIAOTI,
						contactList.get(position - 1).get(
								Constant.XIANSHI_FBIAOTI));
				intent.putExtra(
						Constant.XIANSHI_PRICE,
						contactList.get(position - 1).get(
								Constant.XIANSHI_PRICE));
				intent.putExtra(Constant.XIANSHI_PWD,
						contactList.get(position - 1).get(Constant.XIANSHI_PWD));
				intent.putExtra(
						Constant.XIANSHI_STARTTIME,
						contactList.get(position - 1).get(
								Constant.XIANSHI_STARTTIME));
				intent.putExtra(
						Constant.XIANSHI_STOPTIME,
						contactList.get(position - 1).get(
								Constant.XIANSHI_STOPTIME));
				activity.startActivity(intent);
			}
		});

	}

	@Override
	protected void onPreExecute() {
		pDialog = new ProgressDialog(activity);
		pDialog.setMessage("数据加载中，请稍后...");
		pDialog.setCancelable(false);
		pDialog.show();
		super.onPreExecute();
	}

}
