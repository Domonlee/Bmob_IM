package com.bmob.im.demo.ui;

import com.bmob.im.demo.R;
import com.bmob.im.demo.ui.fragment.FindFragment;
import com.bmob.im.demo.ui.fragment.MoneyFragment;
import com.bmob.im.demo.ui.fragment.ShopFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainInActivity extends SlidingFragmentActivity {
	private Button[] bTabs;
	private TextView[] tTabs, lTabs;
	private Fragment[] fragments;
	private FindFragment findFragment;
	private MoneyFragment moneyFragment;
	private ShopFragment shopFragment;
	private int bindex, tindex, lindex;
	private int currentbTabIndex = 0;
	private int currentlTabIndex = 0;
	private int currenttTabIndex = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main_in);

		initView();
		initSlidingMenu(savedInstanceState);

		bTabs[3].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainInActivity.this,
						MainActivity.class);
				startActivity(intent);
			}
		});

	}

	private void initView() {
		findFragment = new FindFragment();
		moneyFragment = new MoneyFragment();
		shopFragment = new ShopFragment();
		fragments = new Fragment[] { shopFragment,moneyFragment,findFragment };
		//hide 
		getSupportFragmentManager().beginTransaction()
				.add(R.id.fragment_container, findFragment)
				.add(R.id.fragment_container, moneyFragment)
				.add(R.id.fragment_container, shopFragment)
				.hide(findFragment).hide(moneyFragment).show(shopFragment).commit();

		bTabs = new Button[4];
		bTabs[0] = (Button) findViewById(R.id.btn_bm_shop);
		bTabs[1] = (Button) findViewById(R.id.btn_bm_money);
		bTabs[2] = (Button) findViewById(R.id.btn_bm_find);
		bTabs[3] = (Button) findViewById(R.id.btn_bm_group);

		tTabs = new TextView[3];
		tTabs[0] = (TextView) findViewById(R.id.tv_shop_top_1);
		tTabs[1] = (TextView) findViewById(R.id.tv_shop_top_2);
		tTabs[2] = (TextView) findViewById(R.id.tv_shop_top_3);

		lTabs = new TextView[3];
		lTabs[0] = (TextView) findViewById(R.id.btn_shop_location_myloca);
		lTabs[1] = (TextView) findViewById(R.id.btn_shop_location_sort);
		lTabs[2] = (TextView) findViewById(R.id.btn_shop_location_range);

		// �ѵ�һ��tab��Ϊѡ��״̬
		bTabs[0].setSelected(true);
		tTabs[0].setSelected(true);
		lTabs[0].setSelected(true);
	}

	public void onTabSelect(View v) {
		switch (v.getId()) {
		case R.id.btn_bm_shop:
			bindex = 0;
			break;
		case R.id.btn_bm_money:
			bindex = 1;
			break;
		case R.id.btn_bm_find:
			bindex = 2;
			break;
//		case R.id.btn_bm_group:
//			bindex = 3;
//			break;
		}
		Toast.makeText(getApplicationContext(), bindex+ "", 1000).show();
		if (currentbTabIndex != bindex) {
			FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
			trx.hide(fragments[currentbTabIndex]);
			if (!fragments[bindex].isAdded()) {
				trx.add(R.id.fragment_container, fragments[bindex]);
			}
			trx.show(fragments[bindex]);
		}
		bTabs[currentbTabIndex].setSelected(false);
		bTabs[bindex].setSelected(true);
		currentbTabIndex = bindex;
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
		 * ���û�����Ӱ�Ŀ�� ���û�����Ӱ��ͼ����Դ ���û����˵���ͼ�Ŀ�� ���ý��뽥��Ч����ֵ ���ô�����Ļ��ģʽ
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
	 * ���������η��ؼ����˳�
	 */
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if (firstTime + 2000 > System.currentTimeMillis()) {
			super.onBackPressed();
		} else {
			Toast.makeText(getApplicationContext(), "���������η��ؼ����˳�", 1000).show();
		}
		firstTime = System.currentTimeMillis();
	}
}
