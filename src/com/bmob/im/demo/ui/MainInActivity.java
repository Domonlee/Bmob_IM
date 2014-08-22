package com.bmob.im.demo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import cn.bmob.im.bean.BmobInvitation;
import cn.bmob.im.bean.BmobMsg;
import cn.bmob.im.inteface.EventListener;

import com.bmob.im.demo.R;

public class MainInActivity extends ActivityBase implements EventListener{
	Button[] mTabs;

	private int index;
	private int currentTabIndex = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main_in);

		initView();

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

	@Override
	public void onAddUser(BmobInvitation arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMessage(BmobMsg arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNetChange(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onOffline() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReaded(String arg0, String arg1) {
		// TODO Auto-generated method stub
		
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
			ShowToast("再按一次退出程序");
		}
		firstTime = System.currentTimeMillis();
	}
}
