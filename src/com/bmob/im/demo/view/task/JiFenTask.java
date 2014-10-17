package com.bmob.im.demo.view.task;

import java.util.ArrayList;
import java.util.HashMap;

import com.bmob.im.demo.R;
import com.bmob.im.demo.adapter.JiFenAdapter;
import com.bmob.im.demo.ui.SingleExchangeInfoActivity;
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

public class JiFenTask extends AsyncTask<Void, Void, Void> {
	private ListView list;
	private ProgressDialog pDialog;
	private Activity activity;
	private View view;
	private ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();

	public JiFenTask(Activity activity) {
		this.activity = activity;
		view = LayoutInflater.from(activity).inflate(
				R.layout.listview_footview, null);
	}

	public void setList(ListView list) {
		this.list = list;
	}

	@Override
	protected Void doInBackground(Void... params) {
		String jsonString = HttpUtil.httpGet(Constant.JIFENDUIHUAN_URL);
		if (jsonString != null) {
			contactList = JSONUtil.getJiFen(jsonString);
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
		// Constant.JIFEN_ID, Constant.JIFEN_NAME,
		// Constant.JIFEN_SCORE }, new int[] {
		// R.id.tv_exchange_item_info,
		// R.id.tv_exchange_item_goodsname,
		// R.id.tv_exchange_item_price });
		JiFenAdapter adapter = new JiFenAdapter();
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
				Intent intent = new Intent(activity,
						SingleExchangeInfoActivity.class);
				intent.putExtra(Constant.JIFEN_ID, contactList
						.get(position - 1).get(Constant.JIFEN_ID));
				intent.putExtra(Constant.JIFEN_IMAGE,
						contactList.get(position - 1).get(Constant.JIFEN_IMAGE));
				intent.putExtra(Constant.JIFEN_NAME,
						contactList.get(position - 1).get(Constant.JIFEN_NAME));
				intent.putExtra(Constant.JIFEN_NUMBER,
						contactList.get(position - 1)
								.get(Constant.JIFEN_NUMBER));
				intent.putExtra(Constant.JIFEN_SCORE,
						contactList.get(position - 1).get(Constant.JIFEN_SCORE));

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
