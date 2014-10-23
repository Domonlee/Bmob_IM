package com.bmob.im.newview;

import com.bmob.im.demo.R;
import com.bmob.im.demo.R.layout;
import com.bmob.im.demo.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ShequInfoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shequ_info);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.shequ_info, menu);
		return true;
	}

}
