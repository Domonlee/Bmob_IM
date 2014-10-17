package com.bmob.im.newview;

import com.bmob.im.demo.R;
import com.bmob.im.demo.R.layout;
import com.bmob.im.demo.R.menu;
import com.bmob.im.demo.util.Constant;

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
	private TextView price;

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
		price = (TextView) findViewById(R.id.tv_order_price);

	}

	private void setdata() {
		Intent in = getIntent();
		biaoti.setText(in.getStringExtra(Constant.DINGDAN_BIANTI));
		fbiaoti.setText(in.getStringExtra(Constant.DINGDAN_FBIAOTI));
		pwd.setText(in.getStringExtra(Constant.DINGDAN_PWD));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.order_info_item, menu);
		return true;
	}

}
