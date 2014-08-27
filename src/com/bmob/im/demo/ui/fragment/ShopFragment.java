package com.bmob.im.demo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bmob.im.demo.R;
import com.bmob.im.demo.ui.FragmentBase;

public class ShopFragment extends FragmentBase {
	private TextView[] tTabs, lTabs;

	private int tindex, lindex;
	private int currentlTabIndex = 0;
	private int currenttTabIndex = 0;
	private ShopTopOneFragment shopTopOneFragment;
	private ShopTopTwoFragment shopTopTwoFragment;
	private ShopTopThreeragment shopTopThreeragment;
	private Fragment[] fragments;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_shop, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initView();
	}

	private void initView() {
		shopTopOneFragment = new ShopTopOneFragment();
		shopTopTwoFragment = new ShopTopTwoFragment();
		shopTopThreeragment = new ShopTopThreeragment();
		fragments = new Fragment[] { shopTopOneFragment, shopTopTwoFragment,
				shopTopThreeragment };

		getChildFragmentManager().beginTransaction()
				.add(R.id.shop_fragment_container, shopTopOneFragment)
				.add(R.id.shop_fragment_container, shopTopTwoFragment)
				.add(R.id.shop_fragment_container, shopTopThreeragment)
				.hide(shopTopTwoFragment).hide(shopTopThreeragment)
				.show(shopTopOneFragment).commit();

		tTabs = new TextView[3];
		tTabs[0] = (TextView) findViewById(R.id.tv_shop_top_1);
		tTabs[1] = (TextView) findViewById(R.id.tv_shop_top_2);
		tTabs[2] = (TextView) findViewById(R.id.tv_shop_top_3);

		lTabs = new TextView[3];
		lTabs[0] = (TextView) findViewById(R.id.btn_shop_location_myloca);
		lTabs[1] = (TextView) findViewById(R.id.btn_shop_location_sort);
		lTabs[2] = (TextView) findViewById(R.id.btn_shop_location_range);

		tTabs[0].setSelected(true);
		lTabs[0].setSelected(true);
	}

	public void ontTabSelect(View v) {

		switch (v.getId()) {
		case R.id.tv_shop_top_1:
			tindex = 0;
			break;
		case R.id.tv_shop_top_2:
			tindex = 1;
			break;
		case R.id.tv_shop_top_3:
			tindex = 2;
			break;
		}
		if (currenttTabIndex != tindex) {
			FragmentTransaction trx = getChildFragmentManager().beginTransaction();
			trx.hide(fragments[currenttTabIndex]);
			if (!fragments[tindex].isAdded()) {
				trx.add(R.id.shop_fragment_container, fragments[tindex]);
			}
			trx.show(fragments[tindex]).commit();
		}
		tTabs[currenttTabIndex].setSelected(false);
		tTabs[tindex].setSelected(true);
		currenttTabIndex = tindex;
		
	}

}
