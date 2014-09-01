package com.bmob.im.demo.ui;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bmob.im.demo.R;
import com.bmob.im.demo.ui.LeftMenuInfoActivityBase.SlidingBackListener;

public class MyshareInfoActivity extends LeftMenuInfoActivityBase {

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
		
		RelativeLayout layout = (RelativeLayout) findViewById(R.id.fragment_order_container);
		layout.setOnTouchListener(new SlidingBackListener());
	}

	private void initView() {
		btnBack = (ImageView) findViewById(R.id.btn_top_back);
	}


	
}
