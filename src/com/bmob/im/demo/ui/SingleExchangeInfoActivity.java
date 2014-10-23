package com.bmob.im.demo.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bmob.im.demo.DuiHuanActivity;
import com.bmob.im.demo.R;
import com.bmob.im.demo.util.Constant;
import com.bmob.im.demo.util.ImageLoadOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class SingleExchangeInfoActivity extends Activity {

	// JSON node keys

	private TextView jifen;
	private TextView jname;
	private ImageView jimg;
	private TextView jinfo;
	private Button duihuan;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exchange_item_info);
		init();

		Intent in = getIntent();
		// // Get JSON values from previous intent
		String name = in.getStringExtra(Constant.JIFEN_NAME);
		String number = in.getStringExtra(Constant.JIFEN_NUMBER);
		String score = in.getStringExtra(Constant.JIFEN_SCORE);
		jname.setText(name);

		jifen.setText(score);
		jinfo.setText(" £”‡" + number + "∑›");
		ImageLoader.getInstance().displayImage(
				Constant.IMAGE_BASE_URL
						+ in.getStringExtra(Constant.JIFEN_IMAGE), jimg,
				ImageLoadOptions.getOptions());
		duihuan.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SingleExchangeInfoActivity.this,
						DuiHuanActivity.class);
				startActivity(intent);
			}
		});

	}

	private void init() {
		jifen = (TextView) findViewById(R.id.tv_duihuanjifen);
		jimg = (ImageView) findViewById(R.id.iv_exchange_item_pic);
		jname = (TextView) findViewById(R.id.tv_exchange_item_info);
		jinfo = (TextView) findViewById(R.id.tv_exchange_info);
		duihuan = (Button) findViewById(R.id.exchange_item_price_sumbit_btn);

	}
}
