package com.bmob.im.demo.view.task;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.bmob.im.demo.adapter.AllFenLeiAdapter;
import com.bmob.im.demo.adapter.TuanGouAdapter;
import com.bmob.im.demo.ui.SingleExchangeInfoActivity;
import com.bmob.im.demo.ui.fragment.ShopTopOneFragment;
import com.bmob.im.demo.ui.fragment.TuanGouFuFragment;
import com.bmob.im.demo.ui.fragment.TuanGouZhuFragment;
import com.bmob.im.demo.util.Constant;
import com.bmob.im.demo.util.HttpUtil;
import com.bmob.im.demo.util.JSONUtil;
import com.bmob.im.newview.MainFoodActivity;
import com.bmob.im.newview.MainJiuDianActivity;
import com.bmob.im.newview.MainMeiRongActivity;
import com.bmob.im.newview.MainQiTaActivity;
import com.bmob.im.newview.MainSheYingActivity;

public class AllFenLeiTask extends AsyncTask<Void, Void, Void> {
	private ListView list;
	private ProgressDialog pDialog;
	private Activity activity;
	private ArrayList<HashMap<String, String>> contactList;
	private ShopTopOneFragment fragment;
	private TuanGouZhuFragment zhuFragment;
	private TuanGouFuFragment fuFragment;
	private View footview;
	private String url;
	private AllFenLeiAdapter adapter;

	public void setContactList(ArrayList<HashMap<String, String>> contactList) {
		this.contactList = contactList;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setFootview(View footview) {
		this.footview = footview;
	}

	public void setFragment(ShopTopOneFragment fragment) {
		this.fragment = fragment;
	}

	public void setZhuFragment(TuanGouZhuFragment zhuFragment) {
		this.zhuFragment = zhuFragment;
	}

	public void setFuFragment(TuanGouFuFragment fuFragment) {
		this.fuFragment = fuFragment;
	}

	public AllFenLeiTask(Activity activity) {
		this.activity = activity;
	}

	public void setList(ListView list) {
		this.list = list;
	}

	@Override
	protected Void doInBackground(Void... params) {
		Log.i("cheng", "url" + url);
		String jsonString = HttpUtil.httpGet(url);
		if (jsonString != null) {

			contactList = JSONUtil.getTuanGou(jsonString);
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
		// Constant.DINGDAN_SP, Constant.DINGDAN_BIANTI,
		// Constant.DINGDAN_FBIAOTI }, new int[] {
		// R.id.tv_exchange_item_info,
		// R.id.tv_exchange_item_goodsname,
		// R.id.tv_exchange_item_price });
		if (adapter == null) {
			adapter = new AllFenLeiAdapter();
			adapter.setActivity(activity);
			adapter.setList(contactList);
			adapter.setList(zhuFragment.list);
			adapter.setFragment(fragment);
			adapter.setFuFragment(fuFragment);
			adapter.setZhuFragment(zhuFragment);
			zhuFragment.list.setAdapter(adapter);
		} else {

			adapter.notifyDataSetChanged();
		}

		zhuFragment.list.setAdapter(adapter);
		if (zhuFragment.list.getFooterViewsCount() == 0) {

			zhuFragment.list.addFooterView(footview);
		}

		zhuFragment.list.setOnItemClickListener(new OnItemClickListener() {

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
					intent.setClass(activity, MainMeiRongActivity.class);
				} else if (type.equals("婚纱摄影")) {
					intent.setClass(activity, MainSheYingActivity.class);
				} else if (type.equals("旅游酒店")) {
					intent.setClass(activity, MainJiuDianActivity.class);
				} else if (type.equals("其他分类")) {
					intent.setClass(activity, MainQiTaActivity.class);
				}

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

}
