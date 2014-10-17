package com.bmob.im.demo.adapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import com.bmob.im.demo.R;
import com.bmob.im.demo.ui.fragment.ShopTopOneFragment;
import com.bmob.im.demo.ui.fragment.TuanGouFuFragment;
import com.bmob.im.demo.ui.fragment.TuanGouZhuFragment;
import com.bmob.im.demo.util.CollectionUtils;
import com.bmob.im.demo.util.Constant;
import com.bmob.im.demo.util.HeaderViewUtil;
import com.bmob.im.demo.view.task.TuanGouTask;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class AllFenLeiAdapter extends BaseAdapter implements OnClickListener {
	public ArrayList<HashMap<String, String>> qilist = new ArrayList<HashMap<String, String>>();
	private ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
	private Activity activity;
	private ImageView food;
	private ImageView meirong;
	private ImageView yule;
	private ImageView hunsha;
	private ImageView lvyou;
	private ImageView qita;
	private View view;
	// 排序按钮是否可点
	private boolean isxuanzefenlei;
	private ListView list;

	private ShopTopOneFragment fragment;
	private TuanGouZhuFragment zhuFragment;
	private TuanGouFuFragment fuFragment;
	
	public void setFragment(ShopTopOneFragment fragment) {
		this.fragment = fragment;
	}

	public void setZhuFragment(TuanGouZhuFragment zhuFragment) {
		this.zhuFragment = zhuFragment;
	}

	public void setFuFragment(TuanGouFuFragment fuFragment) {
		this.fuFragment = fuFragment;
	}

	public void setList(ListView list) {
		this.list = list;
	}

	public void setList(ArrayList<HashMap<String, String>> list) {
		this.data = list;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	@Override
	public int getCount() {
		return data.size() + 1;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View v, ViewGroup parent) {
		if (position == 0) {
			v = LayoutInflater.from(activity).inflate(
					R.layout.listview_header_item, null);
			food = (ImageView) v.findViewById(R.id.shop_group_type_food);
			meirong = (ImageView) v.findViewById(R.id.shop_group_type_beauty);
			yule = (ImageView) v.findViewById(R.id.shop_group_type_funny);
			hunsha = (ImageView) v.findViewById(R.id.shop_group_type_mv);
			lvyou = (ImageView) v.findViewById(R.id.shop_group_type_hotel);
			qita = (ImageView) v.findViewById(R.id.shop_group_type_other);
			food.setOnClickListener(this);
			meirong.setOnClickListener(this);
			yule.setOnClickListener(this);
			hunsha.setOnClickListener(this);
			lvyou.setOnClickListener(this);
			qita.setOnClickListener(this);
		} else if (position == 1) {
			v = HeaderViewUtil.getHeaderView(activity);

		} else {

			v = LayoutInflater.from(activity).inflate(
					R.layout.listview_exchange_item, null);
			TextView biaoti = (TextView) v
					.findViewById(R.id.tv_exchange_item_info);
			TextView fbiaoti = (TextView) v
					.findViewById(R.id.tv_exchange_item_goodsname);
			TextView price = (TextView) v
					.findViewById(R.id.tv_exchange_item_price);

			biaoti.setText(data.get(position - 1).get(Constant.TUANGOU_NMAE));
			fbiaoti.setText(data.get(position - 1).get(Constant.TUANGOU_PKC));
			price.setText(data.get(position - 1).get(Constant.TUANGOU_PJIGE));

		}

		return v;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// 餐饮美食
		case R.id.shop_group_type_food:
			Constant.paixu_type = Constant.TUANGOU_TYPE_FOOD;
			showFood();
			break;
		// 美容保健
		case R.id.shop_group_type_beauty:
			Constant.paixu_type = Constant.TUANGOU_TYPE_MEIRONG;
			showMeiRong();
			break;
		// 休闲娱乐
		case R.id.shop_group_type_funny:
			Constant.paixu_type = Constant.TUANGOU_TYPE_YULE;
			showYuLe();
			break;
		// 婚纱摄影
		case R.id.shop_group_type_mv:
			Constant.paixu_type = Constant.TUANGOU_TYPE_SHEYING;
			showSheYing();
			break;
		// 旅游酒店
		case R.id.shop_group_type_hotel:
			Constant.paixu_type = Constant.TUANGOU_TYPE_JIUDIAN;
			showJiuDian();
			break;
		// 其他
		case R.id.shop_group_type_other:
			Constant.paixu_type = Constant.TUANGOU_TYPE_QITA;
			showQiTa();
			break;
		}
	}

	/**
	 * 其他
	 */
	private void showQiTa() {
		showfuFragment();

		if (view == null)
			view = LayoutInflater.from(activity).inflate(
					R.layout.listview_footview, null);
		TuanGouTask task = new TuanGouTask(activity, view);
		task.setUrl(Constant.TUANGOU_URL + "page=" + 1 + "&rows=" + 1
				+ "&fenleiid=" + Constant.TUANGOU_TYPE_QITA);
		fuFragment.list.setTag(Constant.TUANGOU_TYPE_QITA);
		task.setList(fuFragment.list);
		task.setContactList(qilist);
		task.execute();
	}

	/**
	 * 旅游酒店
	 */
	private void showJiuDian() {
		showfuFragment();
		if (view == null)
			view = LayoutInflater.from(activity).inflate(
					R.layout.listview_footview, null);
		TuanGouTask task = new TuanGouTask(activity, view);
		task.setUrl(Constant.TUANGOU_URL + "page=" + 1 + "&rows=" + 1
				+ "&fenleiid=" + Constant.TUANGOU_TYPE_JIUDIAN);
		fuFragment.list.setTag(Constant.TUANGOU_TYPE_JIUDIAN);
		task.setList(fuFragment.list);
		task.setContactList(qilist);
		task.execute();
	}

	/**
	 * 婚纱摄影
	 */
	private void showSheYing() {
		showfuFragment();
		if (view == null)
			view = LayoutInflater.from(activity).inflate(
					R.layout.listview_footview, null);
		TuanGouTask task = new TuanGouTask(activity, view);
		task.setUrl(Constant.TUANGOU_URL + "page=" + 1 + "&rows=" + 1
				+ "&fenleiid=" + Constant.TUANGOU_TYPE_SHEYING);
		fuFragment.list.setTag(Constant.TUANGOU_TYPE_SHEYING);
		task.setList(fuFragment.list);
		task.setContactList(qilist);
		task.execute();
	}

	/**
	 * 休闲娱乐
	 */
	private void showYuLe() {
		showfuFragment();
		if (view == null)
			view = LayoutInflater.from(activity).inflate(
					R.layout.listview_footview, null);
		TuanGouTask task = new TuanGouTask(activity, view);
		task.setUrl(Constant.TUANGOU_URL + "page=" + 1 + "&rows=" + 1
				+ "&fenleiid=" + Constant.TUANGOU_TYPE_YULE);
		fuFragment.list.setTag(Constant.TUANGOU_TYPE_YULE);
		task.setList(fuFragment.list);
		task.setContactList(qilist);
		task.execute();
	}

	/**
	 * 美容保健
	 */
	private void showMeiRong() {
		showfuFragment();
		if (view == null)
			view = LayoutInflater.from(activity).inflate(
					R.layout.listview_footview, null);
		TuanGouTask task = new TuanGouTask(activity, view);
		task.setUrl(Constant.TUANGOU_URL + "page=" + 1 + "&rows=" + 1
				+ "&fenleiid=" + Constant.TUANGOU_TYPE_MEIRONG);
		fuFragment.list.setTag(Constant.TUANGOU_TYPE_MEIRONG);
		task.setList(fuFragment.list);
		task.setContactList(qilist);
		task.execute();
	}

	/**
	 * 餐饮美食
	 */
	private void showFood() {
		showfuFragment();
		// transaction.add(R.id.rl_shopgroup, shopGroupFragmen)
		// .show(shopGroupFragmen).commit();
		if (view == null)
			view = LayoutInflater.from(activity).inflate(
					R.layout.listview_footview, null);
		TuanGouTask task = new TuanGouTask(activity, view);
		task.setUrl(Constant.TUANGOU_URL + "page=" + 1 + "&rows=" + 10
				+ "&fenleiid=" + Constant.TUANGOU_TYPE_FOOD);
		fuFragment.list.setTag(Constant.TUANGOU_TYPE_FOOD);
		task.setList(fuFragment.list);
		task.setContactList(qilist);
		task.execute();
	}

	/**
	 * 显示分类fragment
	 */
	private void showfuFragment() {
		isxuanzefenlei = true;
		FragmentTransaction transaction = fragment.getFragmentManager()
				.beginTransaction();
		transaction.hide(zhuFragment);
		// transaction.add(R.id.rl_shopgroup, fuFragment);
		transaction.show(fuFragment).commit();
	}
}
