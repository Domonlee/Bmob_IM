package com.bmob.im.demo;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class DuiHuanActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dui_huan);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.dui_huan, menu);
		return true;
	}

}
