package com.bmob.im.demo.view.task;

import java.util.ArrayList;
import java.util.HashMap;

import com.bmob.im.demo.R;
import com.bmob.im.demo.ui.SingleExchangeInfoActivity;
import com.bmob.im.demo.ui.SingleGroupbyInfoActivity;
import com.bmob.im.demo.util.Constant;
import com.bmob.im.demo.util.HttpUtil;
import com.bmob.im.demo.util.JSONUtil;
import com.bmob.im.newview.OrderInfoItemActivity;
import com.bmob.im.newview.XianShiItemActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class DingDanTask extends AsyncTask<Void, Void, Void> {
	private ListView list;
	private ProgressDialog pDialog;
	private Activity activity;
	private ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();

	public DingDanTask(Activity activity) {
		this.activity = activity;
	}

	public void setList(ListView list) {
		this.list = list;
	}

	@Override
	protected Void doInBackground(Void... params) {
		String jsonString = HttpUtil.httpGet(Constant.DINGDAN_URL);
		if (jsonString != null) {
			contactList = JSONUtil.getMYDingDan(jsonString);
		}
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		if (pDialog.isShowing()) {
			pDialog.dismiss();
		}
		ListAdapter adapter = new SimpleAdapter(activity, contactList,
				R.layout.listview_exchange_item, new String[] {
						Constant.DINGDAN_SP, Constant.DINGDAN_BIANTI,
						Constant.DINGDAN_FBIAOTI }, new int[] {
						R.id.tv_exchange_item_info,
						R.id.tv_exchange_item_goodsname,
						R.id.tv_exchange_item_price });
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int position,
					long id) {
				Intent intent = new Intent(activity,
						OrderInfoItemActivity.class);
				intent.putExtra(Constant.DINGDAN_BIANTI,
						contactList.get(position).get(Constant.DINGDAN_BIANTI));
				intent.putExtra(Constant.DINGDAN_FBIAOTI,
						contactList.get(position).get(Constant.DINGDAN_FBIAOTI));
				intent.putExtra(Constant.DINGDAN_COUNT,
						contactList.get(position).get(Constant.DINGDAN_COUNT));
				intent.putExtra(Constant.DINGDAN_END, contactList.get(position)
						.get(Constant.DINGDAN_END));
				intent.putExtra(Constant.DINGDAN_START,
						contactList.get(position).get(Constant.DINGDAN_START));
				intent.putExtra(Constant.DINGDAN_PWD, contactList.get(position)
						.get(Constant.DINGDAN_PWD));
				intent.putExtra(Constant.DINGDAN_SP, contactList.get(position)
						.get(Constant.DINGDAN_SP));

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
