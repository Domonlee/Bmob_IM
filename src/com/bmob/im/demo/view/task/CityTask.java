package com.bmob.im.demo.view.task;

import java.util.ArrayList;
import java.util.HashMap;

import com.bmob.im.demo.adapter.CityAdapter;
import com.bmob.im.demo.ui.fragment.TuanGouFuFragment;
import com.bmob.im.demo.util.Constant;
import com.bmob.im.demo.util.HttpUtil;
import com.bmob.im.demo.util.JSONUtil;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class CityTask extends AsyncTask<Void, Void, Void> {
	private ProgressDialog pDialog;
	private Activity activity;
	private String url;
	private View view;
	private ListView list;
	private TuanGouFuFragment fragment;
	private ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();

	public void setFragment(TuanGouFuFragment fragment) {
		this.fragment = fragment;
	}

	public CityTask(Activity activity) {
		this.activity = activity;
	}

	public void setList(ListView list) {
		this.list = list;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setView(View view) {
		this.view = view;
	}

	@Override
	protected Void doInBackground(Void... params) {
		String json = HttpUtil.httpGet(url);
		Log.i("cheng", "urlcity" + url);
		if (json != null) {
			contactList = JSONUtil.getCity(json);
			Log.i("cheng", "contactList" + contactList.size());
		}
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		if (pDialog.isShowing()) {
			pDialog.dismiss();

		}
		CityAdapter adapter = new CityAdapter();
		adapter.setActivity(activity);
		adapter.setContactList(contactList);
		list.setAdapter(adapter);

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
