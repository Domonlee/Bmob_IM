package com.bmob.im.demo.ui;

import android.content.Intent;
import android.graphics.Canvas;
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
import android.widget.Toast;

import com.bmob.im.demo.R;
import com.bmob.im.demo.ui.fragment.FindFragment;
import com.bmob.im.demo.ui.fragment.LeftMenuBottomFragment;
import com.bmob.im.demo.ui.fragment.MoneyFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.CanvasTransformer;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainInActivity extends SlidingFragmentActivity {
	private Button[] bTabs;
	private Fragment[] fragments;
	private FindFragment findFragment;
	private MoneyFragment moneyFragment;
	private ShopGroupByActivity shopFragment;
	private int bindex;
	private int currentbTabIndex = 0;

	private CanvasTransformer mCanvasTransformer;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main_in);

		initView();
		initAnimation();
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
		shopFragment = new ShopGroupByActivity();
		fragments = new Fragment[] { shopFragment, moneyFragment, findFragment };
		// hide
		getSupportFragmentManager().beginTransaction()
				.add(R.id.fragment_container, findFragment)
				.add(R.id.fragment_container, moneyFragment)
				.add(R.id.fragment_container, shopFragment).hide(findFragment)
				.hide(moneyFragment).show(shopFragment).commit();

		bTabs = new Button[4];
		bTabs[0] = (Button) findViewById(R.id.btn_bm_shop);
		bTabs[1] = (Button) findViewById(R.id.btn_bm_money);
		bTabs[2] = (Button) findViewById(R.id.btn_bm_find);
		bTabs[3] = (Button) findViewById(R.id.btn_bm_group);

		// 把第一个tab设为选中状态
		bTabs[0].setSelected(true);
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
		// case R.id.btn_bm_group:
		// bindex = 3;
		// break;
		}
		System.out.println("MainIN" + bindex);
		if (currentbTabIndex != bindex) {
			FragmentTransaction trx = getSupportFragmentManager()
					.beginTransaction();
			trx.hide(fragments[currentbTabIndex]);
			if (!fragments[bindex].isAdded()) {
				trx.add(R.id.fragment_container, fragments[bindex]);
			}
			trx.show(fragments[bindex]).commit();
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
		 * 设置滑动阴影的宽度 设置滑动阴影的图像资源 设置滑动菜单视图的宽度 设置渐入渐出效果的值 设置触摸屏幕的模式
		 */
		sMenu.setShadowWidthRes(R.dimen.shadow_width);
		sMenu.setShadowDrawable(R.drawable.shadow);
		sMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sMenu.setFadeDegree(0.35f);
		sMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

		sMenu.setBehindScrollScale(0.0f);
		sMenu.setBehindCanvasTransformer(mCanvasTransformer);

		setSlidingActionBarEnabled(true);

	}

	/**
	 * 初始化动画效果
	 */
	private void initAnimation() {
		mCanvasTransformer = new CanvasTransformer() {
			@Override
			public void transformCanvas(Canvas canvas, float percentOpen) {
				float scale = (float) (percentOpen * 0.25 + 0.75);
				canvas.scale(scale, scale, canvas.getWidth() / 2,
						canvas.getHeight() / 2);
			}
		};
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
