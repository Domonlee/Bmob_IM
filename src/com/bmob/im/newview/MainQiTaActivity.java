package com.bmob.im.newview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bmob.im.demo.R;
import com.bmob.im.demo.bean.TuanGuangGao;
import com.bmob.im.demo.util.Constant;
import com.bmob.im.demo.util.ImageLoadOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class MainQiTaActivity extends Activity {
	private ImageView image;
	private TextView biaoti;
	private TextView fbiaoti;
	private TextView price;
	private Button ok;
	private TextView fprice;
	private TextView fcount;
	private TextView fname;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_qi_ta);
		init();
		setdata();
	}

	private void setdata() {
		Intent intent = getIntent();
		TuanGuangGao dan = (TuanGuangGao) intent
				.getSerializableExtra(Constant.KEY);

		biaoti.setText(intent.getStringExtra(Constant.TUANGOU_NMAE));
		fbiaoti.setText(intent.getStringExtra(Constant.TUANGOU_EPT));
		price.setText(intent.getStringExtra(Constant.TUANGOU_PJIGE));
		String t = null;
		if (dan != null) {

			t = dan.getTgfubiaoti();

			ImageLoader.getInstance().displayImage(
					Constant.IMAGE_BASE_URL + dan.getTgimg(), image,
					ImageLoadOptions.getOptions());
		} else {
			t = intent.getStringExtra(Constant.TUANGOU_PTEXT);
			ImageLoader.getInstance().displayImage(
					Constant.IMAGE_BASE_URL
							+ intent.getStringExtra(Constant.TUANGOU_IMG),
					image, ImageLoadOptions.getOptions());

		}
		if (t != null && t.contains("-")) {

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

	}

	private void init() {
		image = (ImageView) findViewById(R.id.iv_food_image);
		biaoti = (TextView) findViewById(R.id.tv_food_name);
		fbiaoti = (TextView) findViewById(R.id.tv_food_sname);
		price = (TextView) findViewById(R.id.tv_food_price);
		ok = (Button) findViewById(R.id.btn_logout);
		fname = (TextView) findViewById(R.id.tv_tugou_name);
		fcount = (TextView) findViewById(R.id.tv_tugou_count);
		fprice = (TextView) findViewById(R.id.tv_tugou_price);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_qi_ta, menu);
		return true;
	}

}
