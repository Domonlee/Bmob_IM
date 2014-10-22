package com.bmob.im.newview;

import com.bmob.im.demo.R;
import com.bmob.im.demo.R.layout;
import com.bmob.im.demo.R.menu;
import com.bmob.im.demo.bean.MyDianDan;
import com.bmob.im.demo.util.Constant;
import com.bmob.im.demo.util.ImageLoadOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

public class OrderInfoItemActivity extends Activity {

	private ImageView image;
	private TextView biaoti;
	private TextView fbiaoti;
	private TextView youxiaoqi;
	private TextView pwd;
	private TextView zhuangtai;
	private TextView text;

	private TextView dnumber;
	private TextView dphone;
	private TextView dtime;
	private TextView dcount;
	private TextView dzongjia;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_info_item);
		init();
		setdata();
	}

	private void init() {
		image = (ImageView) findViewById(R.id.iv_exchange_item_pic);
		biaoti = (TextView) findViewById(R.id.tv_exchange_sname);
		fbiaoti = (TextView) findViewById(R.id.tv_exchange_item_info);
		youxiaoqi = (TextView) findViewById(R.id.tv_dingdan_youxiaoqi);
		pwd = (TextView) findViewById(R.id.tv_mima_num);
		zhuangtai = (TextView) findViewById(R.id.tv_order_zhuangtai);
		text = (TextView) findViewById(R.id.tv_order_price);
		dnumber = (TextView) findViewById(R.id.tv_order_dingdanhao);
		dphone = (TextView) findViewById(R.id.tv_order_phonenumber);
		dtime = (TextView) findViewById(R.id.tv_order_time);
		dcount = (TextView) findViewById(R.id.tv_order_count);
		dzongjia = (TextView) findViewById(R.id.tv_order_zongjia);

	}

	private void setdata() {
		Intent in = getIntent();
		MyDianDan dan = (MyDianDan) in.getSerializableExtra(Constant.KEY);
		biaoti.setText(dan.getTgbiaoti());
		fbiaoti.setText(dan.getFubiaoti());
		youxiaoqi.setText(dan.getValuetime());
		text.setText(dan.getRemark());
		dnumber.setText(dan.getSpnumber());
		dphone.setText(dan.getOrderphone());
		dtime.setText(dan.getOrdertime());
		dcount.setText(dan.getNumber3());
		dzongjia.setText(dan.getZongjia());
		ImageLoader.getInstance().displayImage(
				Constant.IMAGE_BASE_URL
						+ in.getStringExtra(Constant.DINGDAN_IMG), image,
				ImageLoadOptions.getOptions());

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.order_info_item, menu);
		return true;
	}

}
