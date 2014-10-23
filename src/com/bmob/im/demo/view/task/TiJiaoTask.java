package com.bmob.im.demo.view.task;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.bmob.im.demo.util.Constant;
import com.bmob.im.demo.util.HttpUtil;
import com.bmob.im.demo.util.JSONUtil;
import com.bmob.im.newview.MainFoodActivity;
import com.bmob.im.newview.MainJiuDianActivity;
import com.bmob.im.newview.MainMeiRongActivity;
import com.bmob.im.newview.MainQiTaActivity;
import com.bmob.im.newview.MainSheYingActivity;

public class TiJiaoTask extends AsyncTask<Void, Void, Void> {
	private ListView list;
	private ProgressDialog pDialog;
	private Activity activity;
	private String url;
	private View view;
	private ArrayList<HashMap<String, String>> contactList;
	private ArrayList<HashMap<String, String>> imagelist;
	private TextView jiazai;

	public TiJiaoTask(Activity activity) {
		this.activity = activity;

	}

	public void setImagelist(ArrayList<HashMap<String, String>> imagelist) {
		this.imagelist = imagelist;
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
			contactList = JSONUtil.getTuanGou(jsonString);
			//imagelist = JSONUtil.getJiFenGuanggao(jsonString);
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
				intent.putExtra(Constant.TUANGOU_NMAE, contactList
						.get(position).get(Constant.TUANGOU_NMAE));
				intent.putExtra(Constant.TUANGOU_EPT, contactList.get(position)
						.get(Constant.TUANGOU_EPT));
				intent.putExtra(Constant.TUANGOU_PKC, contactList.get(position)
						.get(Constant.TUANGOU_PKC));
				intent.putExtra(Constant.TUANGOU_IMG, contactList.get(position)
						.get(Constant.TUANGOU_IMG));
				intent.putExtra(Constant.TUANGOU_PTEXT,
						contactList.get(position).get(Constant.TUANGOU_PTEXT));
				intent.putExtra(Constant.TUANGOU_PJIGE,
						contactList.get(position).get(Constant.TUANGOU_PJIGE));
				intent.putExtra(
						Constant.TUANGOU_XIAOLIANG,
						contactList.get(position).get(
								Constant.TUANGOU_XIAOLIANG));
				intent.putExtra(Constant.TUANGOU_CJTG, contactList
						.get(position).get(Constant.TUANGOU_CJTG));
				intent.putExtra(Constant.TUANGOU_CGQG, contactList
						.get(position).get(Constant.TUANGOU_CGQG));
				intent.putExtra(Constant.TUANGOU_TYPE, contactList
						.get(position).get(Constant.TUANGOU_TYPE));

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
