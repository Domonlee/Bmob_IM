package com.bmob.im.demo.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.bmob.im.demo.R;

public class SingleGroupbyInfoActivity extends Activity {

	// JSON node keys
	private static final String TAG_NAME = "name";
	private static final String TAG_ID = "id";
	private static final String TAG_GENDER = "gender";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_groupby_item_info);

		Intent in = getIntent();

		// Get JSON values from previous intent
		String name = in.getStringExtra(TAG_NAME);
		String gender = in.getStringExtra(TAG_GENDER);
		String id = in.getStringExtra(TAG_ID);

		// Displaying all values on the screen
		TextView goodsName = (TextView) findViewById(R.id.tv_exchange_item_goodsname);
		TextView info = (TextView) findViewById(R.id.tv_exchange_item_info);
		TextView price = (TextView) findViewById(R.id.exchange_item_price_tv);

		goodsName.setText(name);
		info.setText(id);
		price.setText(gender);
	}
}
