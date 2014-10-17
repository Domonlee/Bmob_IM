package com.bmob.im.newview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bmob.im.demo.R;
import com.bmob.im.demo.util.Constant;

public class MainQiTaActivity extends Activity {
	private ImageView image;
	private TextView biaoti;
	private TextView fbiaoti;
	private TextView price;
	private Button ok;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_qi_ta);
		init();
		setdata();
	}

	private void setdata() {
		Intent intent = getIntent();
		biaoti.setText(intent.getStringExtra(Constant.TUANGOU_NMAE));
		fbiaoti.setText(intent.getStringExtra(Constant.TUANGOU_PTEXT));
		price.setText(intent.getStringExtra(Constant.TUANGOU_PJIGE));

	}

	private void init() {
		image = (ImageView) findViewById(R.id.iv_food_image);
		biaoti = (TextView) findViewById(R.id.tv_food_name);
		fbiaoti = (TextView) findViewById(R.id.tv_food_sname);
		price = (TextView) findViewById(R.id.tv_food_price);
		ok = (Button) findViewById(R.id.btn_logout);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_qi_ta, menu);
		return true;
	}

}
