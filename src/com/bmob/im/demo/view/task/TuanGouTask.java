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
import android.widget.TextView;
import android.widget.Toast;

import com.bmob.im.demo.R;
import com.bmob.im.demo.adapter.TuanGouAdapter;
import com.bmob.im.demo.util.Constant;
import com.bmob.im.demo.util.HttpUtil;
import com.bmob.im.demo.util.JSONUtil;
import com.bmob.im.demo.util.PixelUtil;
import com.bmob.im.demo.util.WindowUtil;
import com.bmob.im.newview.MainFoodActivity;
import com.bmob.im.newview.MainJiuDianActivity;
import com.bmob.im.newview.MainMeiRongActivity;
import com.bmob.im.newview.MainQiTaActivity;
import com.bmob.im.newview.MainSheYingActivity;

public class TuanGouTask extends AsyncTask<Void, Void, Void> {
	private ListView list;
	private ProgressDialog pDialog;
	private Activity activity;
	private String url;
	private TuanGouAdapter adapter;
	private View view, headerview;
	private ArrayList<HashMap<String, String>> contactList;
	private TextView jiazai;

	private int count = 1;

	public TuanGouTask(Activity activity, View view) {
		this.activity = activity;
		this.view = view;

	}

	public void setList(ListView list) {
		this.list = list;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setAdapter(TuanGouAdapter adapter) {
		this.adapter = adapter;
	}

	public void setContactList(ArrayList<HashMap<String, String>> contactList) {
		this.contactList = contactList;
	}

	public void setHeaderview(View headerview) {
		this.headerview = headerview;
	}

	@Override
	protected Void doInBackground(Void... params) {
		String jsonString = HttpUtil.httpGet(url);
		Log.i("cheng", "url" + url);
		if (jsonString != null) {
			ArrayList<HashMap<String, String>> map = JSONUtil
					.getTuanGou(jsonString);

			for (int i = 0; i < map.size(); i++) {
				HashMap<String, String> m = map.get(i);
				contactList.add(m);

			}

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
			list.setAdapter(adapter);
		} else {

			adapter.notifyDataSetChanged();
		}

		if (list.getFooterViewsCount() == 0) {

			list.addFooterView(view);
		}

		jiazai = (TextView) view.findViewById(R.id.tv_listview_foot);
		jiazai.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				count += 1;
				TuanGouTask task = new TuanGouTask(activity, view);
				task.setList(list);
				task.setUrl(Constant.TUANGOU_BASE_URL + "page=" + count
						+ "&rows=" + 10 + "&fenleiid=" + list.getTag());
				task.setContactList(contactList);
				task.setAdapter(adapter);
				task.execute();
			}
		});

		// list.setOnScrollListener(this);
		if (list.getCount() == 0) {
			Toast.makeText(activity, "没有该分类数据", Toast.LENGTH_SHORT).show();
		}
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int position,
					long id) {
				int type = (Integer) list.getTag();
				Intent intent = new Intent();
				switch (type) {
				case 1:
					intent.setClass(activity, MainFoodActivity.class);
					break;
				case 2:
					intent.setClass(activity, MainMeiRongActivity.class);
					break;
				case 3:
					intent.setClass(activity, MainMeiRongActivity.class);
					break;
				case 4:
					intent.setClass(activity, MainSheYingActivity.class);
					break;
				case 5:
					intent.setClass(activity, MainJiuDianActivity.class);
					break;
				case 7:
					intent.setClass(activity, MainQiTaActivity.class);
					break;

				default:
					break;
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
