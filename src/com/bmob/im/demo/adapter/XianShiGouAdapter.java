package com.bmob.im.demo.adapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import com.bmob.im.demo.R;
import com.bmob.im.demo.ui.SingleExchangeInfoActivity;
import com.bmob.im.demo.util.CollectionUtils;
import com.bmob.im.demo.util.Constant;
import com.bmob.im.demo.util.HeaderViewUtil;
import com.bmob.im.demo.util.ImageLoadOptions;
import com.bmob.im.newview.XianShiItemActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class XianShiGouAdapter extends BaseAdapter {
	private ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
	private Activity activity;
	private int i;
	private ArrayList<HashMap<String, String>> imagelist;

	public void setImagelist(ArrayList<HashMap<String, String>> imagelist) {
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
		return list.size();
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

					if (imagelist.get(i).get(Constant.JIFEN_POSITION)
							.equals("5")) {
						ImageLoader.getInstance().displayImage(
								Constant.IMAGE_BASE_URL
										+ imagelist.get(i).get(
												Constant.JIFEN_IMAGE), image,
								ImageLoadOptions.getOptions());
						setListenr(qianggou, i);

					} else if (imagelist.get(i).get(Constant.JIFEN_POSITION)
							.equals("4")) {
						ImageLoader.getInstance().displayImage(
								Constant.IMAGE_BASE_URL
										+ imagelist.get(i).get(
												Constant.JIFEN_IMAGE), image4,
								ImageLoadOptions.getOptions());
						setListenr(qianggou4, i);

					} else if (imagelist.get(i).get(Constant.JIFEN_POSITION)
							.equals("3")) {
						ImageLoader.getInstance().displayImage(
								Constant.IMAGE_BASE_URL
										+ imagelist.get(i).get(
												Constant.JIFEN_IMAGE), image3,
								ImageLoadOptions.getOptions());
						setListenr(qianggou3, i);

					} else if (imagelist.get(i).get(Constant.JIFEN_POSITION)
							.equals("2")) {
						ImageLoader.getInstance().displayImage(
								Constant.IMAGE_BASE_URL
										+ imagelist.get(i).get(
												Constant.JIFEN_IMAGE), image2,
								ImageLoadOptions.getOptions());
						setListenr(qianggou2, i);

					} else if (imagelist.get(i).get(Constant.JIFEN_POSITION)
							.equals("1")) {
						ImageLoader.getInstance().displayImage(
								Constant.IMAGE_BASE_URL
										+ imagelist.get(i).get(
												Constant.JIFEN_IMAGE), image1,
								ImageLoadOptions.getOptions());
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
							+ list.get(position).get(Constant.XIANSHI_IMG),
					image, ImageLoadOptions.getOptions());
			biaoti.setText(list.get(position).get(Constant.XIANSHI_BIAOTI));
			fbiaoti.setText(list.get(position).get(Constant.XIANSHI_FBIAOTI));
			price.setText(list.get(position).get(Constant.XIANSHI_PRICE));

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
				Intent intent = new Intent(activity, XianShiItemActivity.class);
				intent.putExtra(Constant.XIANSHI_ID,
						list.get(i).get(Constant.XIANSHI_ID));
				intent.putExtra(Constant.XIANSHI_BIAOTI,
						list.get(i).get(Constant.XIANSHI_BIAOTI));
				intent.putExtra(Constant.XIANSHI_FBIAOTI,
						list.get(i).get(Constant.XIANSHI_FBIAOTI));
				intent.putExtra(Constant.XIANSHI_PRICE,
						list.get(i).get(Constant.XIANSHI_PRICE));
				intent.putExtra(Constant.XIANSHI_PWD,
						list.get(i).get(Constant.XIANSHI_PWD));
				intent.putExtra(Constant.XIANSHI_STARTTIME,
						list.get(i).get(Constant.XIANSHI_STARTTIME));
				intent.putExtra(Constant.XIANSHI_STOPTIME,
						list.get(i).get(Constant.XIANSHI_STOPTIME));
				activity.startActivity(intent);
			}
		});

	}
}
