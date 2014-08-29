package com.bmob.im.demo.ui;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bmob.im.demo.R;

public class MyshareInfoActivity extends ActivityBase {

	private ImageView btnBack;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_myshare_info);
		
		initView();
		btnBack.setOnClickListener(new BackOnClickListener());
	}

	private void initView() {
		btnBack = (ImageView) findViewById(R.id.btn_top_back);
	}


	
}
