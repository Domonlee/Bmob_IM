package com.bmob.im.demo.view.task;

import java.util.ArrayList;
import java.util.HashMap;

import com.bmob.im.demo.R;
import com.bmob.im.demo.ui.SingleGroupbyInfoActivity;
import com.bmob.im.demo.util.Constant;
import com.bmob.im.demo.util.HttpUtil;
import com.bmob.im.demo.util.ImageLoadOptions;
import com.bmob.im.demo.util.JSONUtil;
import com.bmob.im.newview.XianShiItemActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

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

public class MyTuanGouJuanTask extends AsyncTask<Void, Void, Void> {
	private ListView list;
	private ProgressDialog pDialog;
	private Activity activity;
	private String url;
	private ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();

	public MyTuanGouJuanTask(Activity activity) {
		this.activity = activity;
	}

	public void setList(ListView list) {
		this.list = list;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	protected Void doInBackground(Void... params) {
		String jsonString = HttpUtil.httpGet(url);
		if (jsonString != null) {
			contactList = JSONUtil.getMYTuanGouJuan(jsonString);
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
						Constant.MYTUANGOUJUAN_NAME,
						Constant.MYTUANGOUJUAN_BIAOTI,
						Constant.MYTUANGOUJUAN_TGPRICE }, new int[] {
						R.id.tv_exchange_item_info,
						R.id.tv_exchange_item_goodsname,
						R.id.tv_exchange_item_price });
		
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int position,
					long id) {
				Intent intent = new Intent(activity,
						SingleGroupbyInfoActivity.class);
				intent.putExtra(Constant.MYTUANGOUJUAN_ID,
						contactList.get(position)
								.get(Constant.MYTUANGOUJUAN_ID));
				intent.putExtra(
						Constant.MYTUANGOUJUAN_NAME,
						contactList.get(position).get(
								Constant.MYTUANGOUJUAN_NAME));
				intent.putExtra(
						Constant.MYTUANGOUJUAN_BIAOTI,
						contactList.get(position).get(
								Constant.MYTUANGOUJUAN_BIAOTI));
				intent.putExtra(Constant.MYTUANGOUJUAN_FBIAOTI, contactList
						.get(position).get(Constant.MYTUANGOUJUAN_FBIAOTI));
				intent.putExtra(
						Constant.MYTUANGOUJUAN_PWD,
						contactList.get(position).get(
								Constant.MYTUANGOUJUAN_PWD));
				intent.putExtra(
						Constant.MYTUANGOUJUAN_TIME,
						contactList.get(position).get(
								Constant.MYTUANGOUJUAN_TIME));
				intent.putExtra(
						Constant.MYTUANGOUJUAN_IMG,
						contactList.get(position).get(
								Constant.MYTUANGOUJUAN_IMG));
				intent.putExtra(Constant.MYTUANGOUJUAN_TG,
						contactList.get(position)
								.get(Constant.MYTUANGOUJUAN_TG));
				intent.putExtra(Constant.MYTUANGOUJUAN_MOVEUSER, contactList
						.get(position).get(Constant.MYTUANGOUJUAN_MOVEUSER));

				intent.putExtra(
						Constant.MYTUANGOUJUAN_NUMBER,
						contactList.get(position).get(
								Constant.MYTUANGOUJUAN_NUMBER));
				intent.putExtra(Constant.MYTUANGOUJUAN_SPPRICE, contactList
						.get(position).get(Constant.MYTUANGOUJUAN_SPPRICE));
				intent.putExtra(Constant.MYTUANGOUJUAN_TGPRICE, contactList
						.get(position).get(Constant.MYTUANGOUJUAN_TGPRICE));
				intent.putExtra(
						Constant.MYTUANGOUJUAN_TEXT,
						contactList.get(position).get(
								Constant.MYTUANGOUJUAN_TEXT));
				intent.putExtra(
						Constant.MYTUANGOUJUAN_DIANPU,
						contactList.get(position).get(
								Constant.MYTUANGOUJUAN_DIANPU));
				intent.putExtra(
						Constant.MYTUANGOUJUAN_STATUS,
						contactList.get(position).get(
								Constant.MYTUANGOUJUAN_STATUS));
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
