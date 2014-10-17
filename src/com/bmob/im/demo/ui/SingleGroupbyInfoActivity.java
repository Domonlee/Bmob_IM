package com.bmob.im.demo.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bmob.im.demo.R;
import com.bmob.im.demo.util.Constant;

public class SingleGroupbyInfoActivity extends Activity {
	private TextView name;
	private TextView fbiaoti;
	private TextView time;
	private TextView pwd;
	private TextView status;
	private ImageView img;
	private TextView fname;
	private TextView fcount;
	private TextView fprice;

	// JSON node keys

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_groupby_item_info);
		init();
		setdata();

	}

	private void setdata() {
		Intent in = getIntent();
		name.setText(in.getStringExtra(Constant.MYTUANGOUJUAN_NAME));
		fbiaoti.setText(in.getStringExtra(Constant.MYTUANGOUJUAN_FBIAOTI));
		time.setText(in.getStringExtra(Constant.MYTUANGOUJUAN_TIME));
		pwd.setText(in.getStringExtra(Constant.MYTUANGOUJUAN_PWD));
		status.setText(in.getStringExtra(Constant.MYTUANGOUJUAN_STATUS));
		String t = in.getStringExtra(Constant.MYTUANGOUJUAN_TEXT);
		String[] s1 = t.split(";");
		String names = "";
		String counts = "";
		String prices = "";
		for (String str : s1) {
			String[] s2 = str.split("-");
			names = names + s2[0] + "\n";
			counts = counts + s2[1] + "\n";
			prices = prices + s2[2] + "\n";
		}
			fname.setText(names);
			fcount.setText(counts);
			fprice.setText(prices);

	}

	private void init() {
		name = (TextView) findViewById(R.id.tv_exchange_item_goodsname);
		fbiaoti = (TextView) findViewById(R.id.tv_exchange_item_info);
		time = (TextView) findViewById(R.id.tv_dingdan_youxiaoqi);
		pwd = (TextView) findViewById(R.id.tv_mima_num);
		status = (TextView) findViewById(R.id.tv_juan_zhuangtai);
		fname = (TextView) findViewById(R.id.tv_tugou_name);
		fcount = (TextView) findViewById(R.id.tv_tugou_count);
		fprice = (TextView) findViewById(R.id.tv_tugou_price);

	}
}
