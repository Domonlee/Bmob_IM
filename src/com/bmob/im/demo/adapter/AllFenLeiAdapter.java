package com.bmob.im.demo.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import com.bmob.im.demo.R;
import com.bmob.im.demo.bean.TuanGuangGao;
import com.bmob.im.demo.ui.fragment.TuanGouFuFragment;
import com.bmob.im.demo.util.Constant;
import com.bmob.im.demo.util.HeaderViewUtil;
import com.bmob.im.demo.util.ImageLoadOptions;
import com.bmob.im.demo.view.task.TuanGouTask;
import com.bmob.im.newview.MainFoodActivity;
import com.bmob.im.newview.MainJiuDianActivity;
import com.bmob.im.newview.MainMeiRongActivity;
import com.bmob.im.newview.MainQiTaActivity;
import com.bmob.im.newview.MainSheYingActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AllFenLeiAdapter extends BaseAdapter implements OnClickListener {
	public ArrayList<TuanGuangGao> imagelist;
	private ArrayList<HashMap<String, String>> data;
	private Activity activity;
	private ImageView food;
	private ImageView meirong;
	private ImageView yule;
	private ImageView hunsha;
	private ImageView lvyou;
	private ImageView qita;
	private View view;
	private int i;
	// 排序按钮是否可点

	private TuanGouFuFragment fuFragment;

	public void setImagelist(ArrayList<TuanGuangGao> imagelist) {
		this.imagelist = imagelist;
	}

	public void setFuFragment(TuanGouFuFragment fuFragment) {
		this.fuFragment = fuFragment;
	}

	public void setList(ArrayList<HashMap<String, String>> list) {
		this.data = list;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	@Override
	public int getCount() {
		return data.size() + 2;
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
			ImageView image = (ImageView) v.findViewById(R.id.iv_header_imge);
			TextView qianggou = (TextView) v.findViewById(R.id.tv_qiangou);

			TextView qianggou1 = (TextView) v.findViewById(R.id.tv_qiangou1);
			TextView qianggou2 = (TextView) v.findViewById(R.id.tv_qiangou2);
			TextView qianggou3 = (TextView) v.findViewById(R.id.tv_qiangou3);
			TextView qianggou4 = (TextView) v.findViewById(R.id.tv_qiangou4);

			ImageView image1 = (ImageView) v.findViewById(R.id.iv_header_imge1);
			ImageView image2 = (ImageView) v.findViewById(R.id.iv_header_imge2);
			ImageView image3 = (ImageView) v.findViewById(R.id.iv_header_imge3);
			ImageView image4 = (ImageView) v.findViewById(R.id.iv_header_imge4);
			if (imagelist != null && !imagelist.isEmpty()) {

				for (i = 0; i < imagelist.size(); i++) {

					if (imagelist.get(i).getPosationid().equals("5")) {
						ImageLoader.getInstance().displayImage(
								Constant.IMAGE_BASE_URL
										+ imagelist.get(i).getTgimg(), image,
								ImageLoadOptions.getOptions());
						setListenr(qianggou, i);

					} else if (imagelist.get(i).getPosationid().equals("4")) {
						ImageLoader.getInstance().displayImage(
								Constant.IMAGE_BASE_URL
										+ imagelist.get(i).getTgimg(), image4,
								ImageLoadOptions.getOptions());
						setListenr(qianggou4, i);
					} else if (imagelist.get(i).getPosationid().equals("3")) {
						ImageLoader.getInstance().displayImage(
								Constant.IMAGE_BASE_URL
										+ imagelist.get(i).getTgimg(), image3,
								ImageLoadOptions.getOptions());
						setListenr(qianggou3, i);

					} else if (imagelist.get(i).getPosationid().equals("2")) {
						ImageLoader.getInstance().displayImage(
								Constant.IMAGE_BASE_URL
										+ imagelist.get(i).getTgimg(), image2,
								ImageLoadOptions.getOptions());
						setListenr(qianggou2, i);

					} else if (imagelist.get(i).getPosationid().equals("1")) {
						ImageLoader.getInstance().displayImage(
								Constant.IMAGE_BASE_URL
										+ imagelist.get(i).getTgimg(), image1,
								ImageLoadOptions.getOptions());
						setListenr(qianggou1, i);

					}

				}
			}
		} else {

			v = LayoutInflater.from(activity).inflate(
					R.layout.listview_exchange_item, null);
			TextView fbiaoti = (TextView) v
					.findViewById(R.id.tv_exchange_item_info);
			TextView biaoti = (TextView) v
					.findViewById(R.id.tv_exchange_item_goodsname);
			TextView price = (TextView) v
					.findViewById(R.id.tv_exchange_item_price);
			ImageView image = (ImageView) v
					.findViewById(R.id.iv_exchange_item_pic);
			ImageLoader.getInstance().displayImage(
					Constant.IMAGE_BASE_URL
							+ data.get(position - 2).get(Constant.TUANGOU_IMG),
					image, ImageLoadOptions.getOptions());

			biaoti.setText(data.get(position - 2).get(Constant.TUANGOU_NMAE));
			fbiaoti.setText(data.get(position - 2).get(Constant.TUANGOU_PKC));
			price.setText(data.get(position - 2).get(Constant.TUANGOU_PJIGE));

		}

		return v;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// 餐饮美食
		case R.id.shop_group_type_food:
			Constant.TUANGOU_FENLEI_TYPE = Constant.TUANGOU_TYPE_FOOD;
			showFood();
			break;
		// 美容保健
		case R.id.shop_group_type_beauty:
			Constant.TUANGOU_FENLEI_TYPE = Constant.TUANGOU_TYPE_MEIRONG;
			showMeiRong();
			break;
		// 休闲娱乐
		case R.id.shop_group_type_funny:
			Constant.TUANGOU_FENLEI_TYPE = Constant.TUANGOU_TYPE_YULE;
			showYuLe();
			break;
		// 婚纱摄影
		case R.id.shop_group_type_mv:
			Constant.TUANGOU_FENLEI_TYPE = Constant.TUANGOU_TYPE_SHEYING;
			showSheYing();
			break;
		// 旅游酒店
		case R.id.shop_group_type_hotel:
			Constant.TUANGOU_FENLEI_TYPE = Constant.TUANGOU_TYPE_JIUDIAN;
			showJiuDian();
			break;
		// 其他
		case R.id.shop_group_type_other:
			Constant.TUANGOU_FENLEI_TYPE = Constant.TUANGOU_TYPE_QITA;
			showQiTa();
			break;
		}
	}

	/**
	 * 其他
	 */
	private void showQiTa() {

		if (view == null)
			view = LayoutInflater.from(activity).inflate(
					R.layout.listview_footview, null);
		TuanGouTask task = new TuanGouTask(activity, view);
		task.setUrl(Constant.TUANGOU_URL + "page=" + 1 + "&rows=" + 1
				+ "&fenleiid=" + Constant.TUANGOU_TYPE_QITA);
		fuFragment.list.setTag(Constant.TUANGOU_TYPE_QITA);
		task.setList(fuFragment.list);
		task.setContactList(data);
		task.execute();
	}

	/**
	 * 旅游酒店
	 */
	private void showJiuDian() {
		if (view == null)
			view = LayoutInflater.from(activity).inflate(
					R.layout.listview_footview, null);
		TuanGouTask task = new TuanGouTask(activity, view);
		task.setUrl(Constant.TUANGOU_URL + "page=" + 1 + "&rows=" + 1
				+ "&fenleiid=" + Constant.TUANGOU_TYPE_JIUDIAN);
		fuFragment.list.setTag(Constant.TUANGOU_TYPE_JIUDIAN);
		task.setList(fuFragment.list);
		task.setContactList(data);
		task.execute();
	}

	/**
	 * 婚纱摄影
	 */
	private void showSheYing() {
		if (view == null)
			view = LayoutInflater.from(activity).inflate(
					R.layout.listview_footview, null);
		TuanGouTask task = new TuanGouTask(activity, view);
		task.setUrl(Constant.TUANGOU_URL + "page=" + 1 + "&rows=" + 1
				+ "&fenleiid=" + Constant.TUANGOU_TYPE_SHEYING);
		fuFragment.list.setTag(Constant.TUANGOU_TYPE_SHEYING);
		task.setList(fuFragment.list);
		task.setContactList(data);
		task.execute();
	}

	/**
	 * 休闲娱乐
	 */
	private void showYuLe() {
		if (view == null)
			view = LayoutInflater.from(activity).inflate(
					R.layout.listview_footview, null);
		TuanGouTask task = new TuanGouTask(activity, view);
		task.setUrl(Constant.TUANGOU_URL + "page=" + 1 + "&rows=" + 1
				+ "&fenleiid=" + Constant.TUANGOU_TYPE_YULE);
		fuFragment.list.setTag(Constant.TUANGOU_TYPE_YULE);
		task.setList(fuFragment.list);
		task.setContactList(data);
		task.execute();
	}

	/**
	 * 美容保健
	 */
	private void showMeiRong() {
		if (view == null)
			view = LayoutInflater.from(activity).inflate(
					R.layout.listview_footview, null);
		TuanGouTask task = new TuanGouTask(activity, view);
		task.setUrl(Constant.TUANGOU_URL + "page=" + 1 + "&rows=" + 1
				+ "&fenleiid=" + Constant.TUANGOU_TYPE_MEIRONG);
		fuFragment.list.setTag(Constant.TUANGOU_TYPE_MEIRONG);
		task.setList(fuFragment.list);
		task.setContactList(data);
		task.execute();
	}

	/**
	 * 餐饮美食
	 */
	private void showFood() {
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
		task.setContactList(data);
		task.execute();
	}

	/**
	 * 添加点击事件
	 * 
	 * @param qianggou
	 * @param i2
	 */
	private void setListenr(TextView qianggou, final int i) {
		qianggou.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String type = imagelist.get(i).getFenlei();
				Intent intent = new Intent();
				if (type.equals("1")) {
					intent.setClass(activity, MainFoodActivity.class);
				} else if (type.equals("2")) {
					intent.setClass(activity, MainMeiRongActivity.class);
				} else if (type.equals("3")) {
					intent.setClass(activity, MainMeiRongActivity.class);
				} else if (type.equals("4")) {
					intent.setClass(activity, MainSheYingActivity.class);
				} else if (type.equals("5")) {
					intent.setClass(activity, MainJiuDianActivity.class);
				} else if (type.equals("7")) {
					intent.setClass(activity, MainQiTaActivity.class);
				}
				Bundle bundle = new Bundle();
				bundle.putSerializable(Constant.KEY, imagelist.get(i));
				intent.putExtras(bundle);
				activity.startActivity(intent);

			}
		});

	}
}
