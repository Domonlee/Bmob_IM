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
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class JiFenTask extends AsyncTask<Void, Void, Void> {
	private ListView list;
	private ProgressDialog pDialog;
	private Activity activity;
	private View view;
	private String url;
	private ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();
	private ArrayList<HashMap<String, String>> imagelist;
	private JiFenAdapter adapter;

	public JiFenTask(Activity activity) {
		this.activity = activity;
		view = LayoutInflater.from(activity).inflate(
				R.layout.listview_footview, null);
	}

	public void setAdapter(JiFenAdapter adapter) {
		this.adapter = adapter;
	}

	public void setUrl(String url) {
		this.url = url;
		Constant.BEFORE_URL = url;
		Constant.URL_TYPE = 3;
	}

	public void setList(ListView list) {
		this.list = list;
	}

	@Override
	protected Void doInBackground(Void... params) {
		String jsonString = HttpUtil.httpGet(url);
		if (jsonString != null) {
			contactList = JSONUtil.getJiFen(jsonString);
			imagelist = JSONUtil.getJiFenGuanggao(jsonString);
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

			adapter = new JiFenAdapter();
			adapter.setActivity(activity);
			adapter.setList(contactList);
			adapter.setImagelist(imagelist);
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
				JiFenTask task = new JiFenTask(activity);
				task.setList(list);
				task.setAdapter(adapter);
				task.setUrl(url.replace("page=" + n, "page=" + Constant.COUNT));
				task.execute();

			}
		});
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
