package com.bmob.im.newview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bmob.im.demo.R;
import com.bmob.im.demo.util.Constant;
import com.bmob.im.demo.view.task.TiJiaoTask;

public class MainFoodActivity extends Activity {
	private ImageView image;
	private TextView biaoti;
	private TextView fbiaoti;
	private TextView price;
	private Button ok;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_food);
		init();
		setdata();

	}

	private void setdata() {
		Intent intent = getIntent();
		String id = intent.getStringExtra(Constant.TUANGOU_ID);
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
		ok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				TiJiaoTask task = new TiJiaoTask(MainFoodActivity.this);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_food, menu);
		return true;
	}

}
