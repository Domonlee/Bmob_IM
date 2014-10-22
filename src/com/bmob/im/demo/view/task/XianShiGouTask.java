package com.bmob.im.demo.view.task;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.bmob.im.demo.R;
import com.bmob.im.demo.adapter.XianShiGouAdapter;
import com.bmob.im.demo.ui.fragment.ShopTopTwoFragment;
import com.bmob.im.demo.util.Constant;
import com.bmob.im.demo.util.HttpUtil;
import com.bmob.im.demo.util.JSONUtil;
import com.bmob.im.newview.XianShiItemActivity;

public class XianShiGouTask extends AsyncTask<Void, Void, Void> {
	private ListView list;
	private ProgressDialog pDialog;
	private Activity activity;
	private View view;
	private String url;
	private ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();
	private ShopTopTwoFragment twoFragment;
	private XianShiGouAdapter adapter;

	public void setAdapter(XianShiGouAdapter adapter) {
		this.adapter = adapter;
	}

	public XianShiGouTask(Activity activity) {
		this.activity = activity;
		view = LayoutInflater.from(activity).inflate(
				R.layout.listview_footview, null);

	}

	public void setView(View view) {
		this.view = view;
	}

	public void setList(ListView list) {
		this.list = list;
	}

	public void setTwoFragment(ShopTopTwoFragment twoFragment) {
		this.twoFragment = twoFragment;
	}

	public void setUrl(String url) {
		this.url = url;
		Constant.BEFORE_URL = url;
		Constant.URL_TYPE = 2;
	}

	@Override
	protected Void doInBackground(Void... params) {
		String jsonString = HttpUtil.httpGet(url);
		Log.i("cheng", "" + jsonString);
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
		if (adapter == null) {

			adapter = new XianShiGouAdapter();
			adapter.setActivity(activity);
			adapter.setList(contactList);
			if (list.getFooterViewsCount() == 0) {

				list.addFooterView(view);
			}
			list.setAdapter(adapter);
		} else {
			adapter.notifyDataSetChanged();
		}

		RadioButton tvfoot = (RadioButton) view
				.findViewById(R.id.tv_listview_foot);
		tvfoot.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				

				Constant.COUNT += 1;
				int n = Constant.COUNT - 1;
				XianShiGouTask task = new XianShiGouTask(activity);
				task.setList(list);
				task.setAdapter(adapter);
				task.setUrl(Constant.XIANSHIQIANGGOU.replace("page=" + n,
						"page=" + Constant.COUNT));
				task.execute();

			}
		});
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int position,
					long id) {
				Intent intent = new Intent(activity, XianShiItemActivity.class);
				intent.putExtra(Constant.XIANSHI_ID, contactList.get(position)
						.get(Constant.XIANSHI_ID));
				intent.putExtra(Constant.XIANSHI_BIAOTI,
						contactList.get(position).get(Constant.XIANSHI_BIAOTI));
				intent.putExtra(Constant.XIANSHI_FBIAOTI,
						contactList.get(position).get(Constant.XIANSHI_FBIAOTI));
				intent.putExtra(Constant.XIANSHI_PRICE,
						contactList.get(position).get(Constant.XIANSHI_PRICE));
				intent.putExtra(Constant.XIANSHI_PWD, contactList.get(position)
						.get(Constant.XIANSHI_PWD));
				intent.putExtra(
						Constant.XIANSHI_STARTTIME,
						contactList.get(position).get(
								Constant.XIANSHI_STARTTIME));
				intent.putExtra(Constant.XIANSHI_STOPTIME,
						contactList.get(position)
								.get(Constant.XIANSHI_STOPTIME));
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
