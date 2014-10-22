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
import android.widget.TextView;
import android.widget.Toast;

import com.bmob.im.demo.R;
import com.bmob.im.demo.adapter.TuanGouAdapter;
import com.bmob.im.demo.bean.TuanGuangGao;
import com.bmob.im.demo.ui.fragment.TuanGouFuFragment;
import com.bmob.im.demo.util.Constant;
import com.bmob.im.demo.util.HttpUtil;
import com.bmob.im.demo.util.JSONUtil;
import com.bmob.im.newview.MainFoodActivity;
import com.bmob.im.newview.MainJiuDianActivity;
import com.bmob.im.newview.MainMeiRongActivity;
import com.bmob.im.newview.MainQiTaActivity;
import com.bmob.im.newview.MainSheYingActivity;
import com.bmob.im.newview.MainYuLeActivity;

public class TuanGouTask extends AsyncTask<Void, Void, Void> {
	private ListView list;
	private ProgressDialog pDialog;
	private Activity activity;
	private String url;
	private TuanGouAdapter adapter;
	private TuanGouFuFragment fragment;
	private View view;
	private ArrayList<HashMap<String, String>> contactList;
	public ArrayList<TuanGuangGao> imagelist;

	public TuanGouTask(Activity activity, View view) {
		this.activity = activity;
		this.view = LayoutInflater.from(activity).inflate(
				R.layout.listview_footview, null);

	}

	public void setFragment(TuanGouFuFragment fragment) {
		this.fragment = fragment;
	}

	public void setList(ListView list) {
		this.list = list;
	}

	public void setUrl(String url) {
		this.url = url;
		Constant.BEFORE_TUANGOU_URL = url;
		Constant.URL_TYPE = 1;
	}

	public void setAdapter(TuanGouAdapter adapter) {
		this.adapter = adapter;
	}

	public void setContactList(ArrayList<HashMap<String, String>> contactList) {
		this.contactList = contactList;
	}

	@Override
	protected Void doInBackground(Void... params) {
		String jsonString = HttpUtil.httpGet(url);
		Log.i("cheng", "tuanurl" + url);

		if (jsonString != null) {
			contactList = JSONUtil.getTuanGou(jsonString);

			imagelist = JSONUtil.getTuanGouGuanggao(jsonString);
			Log.i("cheng", contactList.size() + "contactList.size()");

		}
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		if (pDialog.isShowing()) {
			pDialog.dismiss();
		}
		// XXX

		if (adapter == null) {
			adapter = new TuanGouAdapter();
			adapter.setList(contactList);
			adapter.setActivity(activity);
			adapter.setImagelist(imagelist);
			if (list.getFooterViewsCount() == 0) {

				list.addFooterView(view);
			}
			list.setAdapter(adapter);
		} else {

			adapter.notifyDataSetChanged();
		}

		RadioButton textView = (RadioButton) view
				.findViewById(R.id.tv_listview_foot);
		textView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				Constant.COUNT += 1;
				int n = Constant.COUNT - 1;
				TuanGouTask task = new TuanGouTask(activity, view);
				task.setContactList(contactList);
				task.setList(fragment.list);
				task.setFragment(fragment);
				task.setAdapter(adapter);
				task.setUrl(Constant.BEFORE_TUANGOU_URL.replace("page=" + n,
						"page=" + Constant.COUNT));

				task.execute();
			}
		});
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int position,
					long id) {

				String type = contactList.get(position - 1).get(
						Constant.TUANGOU_TYPE);
				Intent intent = new Intent();
				if (type.equals("餐饮美食")) {
					intent.setClass(activity, MainFoodActivity.class);
				} else if (type.equals("美容保健")) {
					intent.setClass(activity, MainMeiRongActivity.class);
				} else if (type.equals("休闲娱乐")) {
					intent.setClass(activity, MainYuLeActivity.class);
				} else if (type.equals("婚纱摄影")) {
					intent.setClass(activity, MainSheYingActivity.class);
				} else if (type.equals("旅游酒店")) {
					intent.setClass(activity, MainJiuDianActivity.class);
				} else if (type.equals("其他分类")) {
					intent.setClass(activity, MainQiTaActivity.class);
				}

				// Intent intent = new Intent(activity,
				// SingleExchangeInfoActivity.class);
				intent.putExtra(Constant.TUANGOU_NMAE,
						contactList.get(position - 1)
								.get(Constant.TUANGOU_NMAE));
				intent.putExtra(Constant.TUANGOU_EPT,
						contactList.get(position - 1).get(Constant.TUANGOU_EPT));
				intent.putExtra(Constant.TUANGOU_PKC,
						contactList.get(position - 1).get(Constant.TUANGOU_PKC));
				intent.putExtra(Constant.TUANGOU_IMG,
						contactList.get(position - 1).get(Constant.TUANGOU_IMG));
				intent.putExtra(
						Constant.TUANGOU_PTEXT,
						contactList.get(position - 1).get(
								Constant.TUANGOU_PTEXT));
				intent.putExtra(
						Constant.TUANGOU_PJIGE,
						contactList.get(position - 1).get(
								Constant.TUANGOU_PJIGE));
				intent.putExtra(
						Constant.TUANGOU_XIAOLIANG,
						contactList.get(position - 1).get(
								Constant.TUANGOU_XIAOLIANG));

				intent.putExtra(Constant.TUANGOU_CJTG,
						contactList.get(position - 1)
								.get(Constant.TUANGOU_CJTG));
				intent.putExtra(Constant.TUANGOU_CGQG,
						contactList.get(position - 1)
								.get(Constant.TUANGOU_CGQG));
				intent.putExtra(Constant.TUANGOU_TYPE,
						contactList.get(position - 1)
								.get(Constant.TUANGOU_TYPE));

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

	@Override
	protected void onCancelled() {

		super.onCancelled();
	}
}
