package com.bmob.im.demo.view.task;

import java.util.ArrayList;
import java.util.HashMap;

import com.bmob.im.demo.R;
import com.bmob.im.demo.adapter.DaTiAdapter;
import com.bmob.im.demo.util.Constant;
import com.bmob.im.demo.util.HttpUtil;
import com.bmob.im.demo.util.JSONUtil;
import com.bmob.im.newview.OrderInfoItemActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class DaTiTask extends AsyncTask<Void, Void, Void> {
	private ListView list;
	private ProgressDialog pDialog;
	private Activity activity;
	private ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();

	public DaTiTask(Activity activity) {
		this.activity = activity;
	}

	public void setList(ListView list) {
		this.list = list;
	}

	@Override
	protected Void doInBackground(Void... params) {
		String jsonString = HttpUtil.httpGet(Constant.DATI_URL);
		Log.i("cheng", "jsonString" + jsonString);
		if (jsonString != null) {
			contactList = JSONUtil.getDaTi(jsonString);
		}
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		if (pDialog.isShowing()) {
			pDialog.dismiss();
		}
		DaTiAdapter adapter = new DaTiAdapter();
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
