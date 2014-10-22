package com.bmob.im.demo.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bmob.im.demo.R;
import com.bmob.im.demo.bean.TuanGuangGao;
import com.bmob.im.demo.util.Constant;
import com.bmob.im.demo.util.HeaderViewUtil;
import com.bmob.im.demo.util.ImageLoadOptions;
import com.bmob.im.newview.MainFoodActivity;
import com.bmob.im.newview.MainJiuDianActivity;
import com.bmob.im.newview.MainMeiRongActivity;
import com.bmob.im.newview.MainQiTaActivity;
import com.bmob.im.newview.MainSheYingActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

public class TuanGouAdapter extends BaseAdapter {
	private ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
	private Activity activity;
	private boolean ispaixu;
	private ArrayList<TuanGuangGao> imagelist;
	private int i;

	public void setImagelist(ArrayList<TuanGuangGao> imagelist) {
		this.imagelist = imagelist;
	}

	public void setList(ArrayList<HashMap<String, String>> list) {
		this.list = list;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	@Override
	public int getCount() {
		return list.size() + 1;
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
	public int getItemViewType(int position) {
		if (position == 0) {
			return Constant.ITEM_TYPE_HEADVIEW;
		}
		return Constant.ITEM_YYPE_PUTONGVIEW;

	}

	@Override
	public View getView(int position, View v, ViewGroup parent) {
		if (position == 0) {
			v = HeaderViewUtil.getHeaderView(activity);

			ImageView image = (ImageView) v.findViewById(R.id.iv_header_imge);
			TextView biaoti = (TextView) v.findViewById(R.id.tv_biaoti);
			TextView qianggou = (TextView) v.findViewById(R.id.tv_qiangou);

			TextView qianggou1 = (TextView) v.findViewById(R.id.tv_qiangou1);
			TextView qianggou2 = (TextView) v.findViewById(R.id.tv_qiangou2);
			TextView qianggou3 = (TextView) v.findViewById(R.id.tv_qiangou3);
			TextView qianggou4 = (TextView) v.findViewById(R.id.tv_qiangou4);

			TextView biaoti1 = (TextView) v.findViewById(R.id.tv_biaoti1);
			TextView biaoti2 = (TextView) v.findViewById(R.id.tv_biaoti2);
			TextView biaoti3 = (TextView) v.findViewById(R.id.tv_biaoti3);
			TextView biaoti4 = (TextView) v.findViewById(R.id.tv_biaoti4);

			ImageView image1 = (ImageView) v.findViewById(R.id.iv_header_imge1);
			ImageView image2 = (ImageView) v.findViewById(R.id.iv_header_imge2);
			ImageView image3 = (ImageView) v.findViewById(R.id.iv_header_imge3);
			ImageView image4 = (ImageView) v.findViewById(R.id.iv_header_imge4);
			if (imagelist != null && !imagelist.isEmpty()) {
				for (i = 0; i < imagelist.size(); i++) {

					if (imagelist.get(i).getPosationname().equals("5")) {
						ImageLoader.getInstance().displayImage(
								Constant.IMAGE_BASE_URL
										+ imagelist.get(i).getTgimg().trim(),
								image, ImageLoadOptions.getOptions());

						biaoti.setText(imagelist.get(i).getSpname());
						setListenr(qianggou, i);

					} else if (imagelist.get(i).getPosationname().equals("4")) {
						ImageLoader.getInstance().displayImage(
								Constant.IMAGE_BASE_URL
										+ imagelist.get(i).getTgimg().trim(),
								image4, ImageLoadOptions.getOptions());
						biaoti4.setText(imagelist.get(i).getSpname());
						setListenr(qianggou4, i);
						//
					} else if (imagelist.get(i).getPosationname().equals("3")) {
						ImageLoader.getInstance().displayImage(
								Constant.IMAGE_BASE_URL
										+ imagelist.get(i).getTgimg().trim(),
								image3, ImageLoadOptions.getOptions());
						biaoti3.setText(imagelist.get(i).getSpname());
						setListenr(qianggou3, i);

					} else if (imagelist.get(i).getPosationname().equals("2")) {
						ImageLoader.getInstance().displayImage(
								Constant.IMAGE_BASE_URL
										+ imagelist.get(i).getTgimg().trim(),
								image2, ImageLoadOptions.getOptions());
						biaoti2.setText(imagelist.get(i).getSpname());
						setListenr(qianggou2, i);

					} else if (imagelist.get(i).getPosationname().equals("1")) {
						ImageLoader.getInstance().displayImage(
								Constant.IMAGE_BASE_URL
										+ imagelist.get(i).getTgimg().trim(),
								image1, ImageLoadOptions.getOptions());
						biaoti1.setText(imagelist.get(i).getSpname());
						setListenr(qianggou1, i);

					}

				}
			}
		} else {

			v = LayoutInflater.from(activity).inflate(
					R.layout.listview_exchange_item, null);
			TextView biaoti = (TextView) v
					.findViewById(R.id.tv_exchange_item_info);
			TextView fbiaoti = (TextView) v
					.findViewById(R.id.tv_exchange_item_goodsname);
			TextView price = (TextView) v
					.findViewById(R.id.tv_exchange_item_price);
			ImageView image = (ImageView) v
					.findViewById(R.id.iv_exchange_item_pic);
			ImageLoader.getInstance().displayImage(
					Constant.IMAGE_BASE_URL
							+ list.get(position - 1).get(Constant.TUANGOU_IMG),
					image, ImageLoadOptions.getOptions());

			biaoti.setText(list.get(position - 1).get(Constant.TUANGOU_PTEXT));
			fbiaoti.setText(list.get(position - 1).get(Constant.TUANGOU_NMAE));

			price.setText(list.get(position - 1).get(Constant.TUANGOU_PJIGE));

		}

		return v;
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
