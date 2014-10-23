package com.bmob.im.demo.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bmob.im.demo.R;
import com.bmob.im.demo.ui.SingleExchangeInfoActivity;
import com.bmob.im.demo.util.Constant;
import com.bmob.im.demo.util.HeaderViewUtil;
import com.bmob.im.demo.util.ImageLoadOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class JiFenAdapter extends BaseAdapter {
	private ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
	private Activity activity;
	private ArrayList<HashMap<String, String>> imagelist;
	private int i = 0;

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
							+ list.get(position - 1).get(Constant.JIFEN_IMAGE),
					image, ImageLoadOptions.getOptions());

			biaoti.setText(list.get(position - 1).get(Constant.JIFEN_NAME));
			fbiaoti.setText(list.get(position - 1).get(Constant.JIFEN_SCORE));
			price.setText(list.get(position - 1).get(Constant.JIFEN_NUMBER));

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
				Intent intent = new Intent(activity,
						SingleExchangeInfoActivity.class);
				intent.putExtra(Constant.JIFEN_NAME,
						imagelist.get(i).get(Constant.JIFEN_NAME));
				intent.putExtra(Constant.JIFEN_ID,
						imagelist.get(i).get(Constant.JIFEN_ID));
				intent.putExtra(Constant.JIFEN_IMAGE,
						imagelist.get(i).get(Constant.JIFEN_IMAGE));
				intent.putExtra(Constant.JIFEN_NAME,
						imagelist.get(i).get(Constant.JIFEN_NAME));
				intent.putExtra(Constant.JIFEN_NUMBER,
						imagelist.get(i).get(Constant.JIFEN_NUMBER));
				intent.putExtra(Constant.JIFEN_SCORE,
						imagelist.get(i).get(Constant.JIFEN_SCORE));

				activity.startActivity(intent);
			}
		});

	}

}
