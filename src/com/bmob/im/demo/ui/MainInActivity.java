package com.bmob.im.demo.ui;

import com.bmob.im.demo.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MainInActivity extends SlidingFragmentActivity {
	Button[] mTabs;

	private int index;
	private int currentTabIndex = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main_in);

		initView();
		initSlidingMenu(savedInstanceState);

		mTabs[3].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainInActivity.this,
						MainActivity.class);
				startActivity(intent);
			}
		});
	}

	private void initView() {
		mTabs = new Button[4];
		mTabs[0] = (Button) findViewById(R.id.btn_bm_shop);
		mTabs[1] = (Button) findViewById(R.id.btn_bm_find);
		mTabs[2] = (Button) findViewById(R.id.btn_bm_money);
		mTabs[3] = (Button) findViewById(R.id.btn_bm_group);
		// 把第一个tab设为选中状态
		mTabs[0].setSelected(true);
	}

	private void onTabSelect(View v) {
		switch (v.getId()) {
		case R.id.btn_bm_shop:
			index = 0;
			break;
		case R.id.btn_bm_money:
			index = 1;
			break;
		case R.id.btn_bm_find:
			index = 2;
			break;
		case R.id.btn_bm_group:
			index = 3;
			break;
		}
		mTabs[currentTabIndex].setSelected(false);
		mTabs[index].setSelected(true);
		currentTabIndex = index;

	}

	/**
	 * init the slidingmenu
	 */
	private void initSlidingMenu(Bundle saveInstanceState) {
		// setting slidingmenu view
		setBehindContentView(R.layout.menu_frame);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.menu_frame, new LeftMenuBottomFragment())
				.commit();

		SlidingMenu sMenu = getSlidingMenu();

		/*
		 * 设置滑动阴影的宽度 设置滑动阴影的图像资源 设置滑动菜单视图的宽度 设置渐入渐出效果的值 设置触摸屏幕的模式
		 */
		sMenu.setShadowWidthRes(R.dimen.shadow_width);
		sMenu.setShadowDrawable(R.drawable.shadow);
		sMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sMenu.setFadeDegree(0.35f);
		sMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_in, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private static long firstTime;

	/**
	 * 连续按两次返回键就退出
	 */
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if (firstTime + 2000 > System.currentTimeMillis()) {
			super.onBackPressed();
		} else {
			Toast.makeText(getApplicationContext(), "连续按两次返回键就退出", 1000).show();
		}
		firstTime = System.currentTimeMillis();
	}
}
