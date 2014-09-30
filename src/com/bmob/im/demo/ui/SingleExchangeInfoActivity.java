package com.bmob.im.demo.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.bmob.im.demo.R;

public class SingleExchangeInfoActivity extends Activity {

	// JSON node keys
	private static final String TAG_NAME = "name";
	private static final String TAG_ID = "id";
	private static final String TAG_EMAIL = "email";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exchange_item_info);

		Intent in = getIntent();

		// Get JSON values from previous intent
		String name = in.getStringExtra(TAG_NAME);
		String email = in.getStringExtra(TAG_EMAIL);
		String id = in.getStringExtra(TAG_ID);

		// Displaying all values on the screen
		TextView goodsName = (TextView) findViewById(R.id.tv_exchange_item_goodsname);
		TextView info = (TextView) findViewById(R.id.tv_exchange_item_info);
		TextView price = (TextView) findViewById(R.id.exchange_item_price_tv);

		goodsName.setText(name);
		info.setText(id);
		price.setText(email);
	}
}
