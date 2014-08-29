package com.bmob.im.demo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bmob.im.demo.R;
import com.bmob.im.demo.ui.fragment.ShopTopOneFragment;
import com.bmob.im.demo.ui.fragment.ShopTopThreeFragment;
import com.bmob.im.demo.ui.fragment.ShopTopTwoFragment;

public class ShopGroupByActivity extends FragmentBase {
	private TextView[] tTabs;

	private ShopTopThreeFragment shopTopThreeFragment;
	private ShopTopTwoFragment shopTopTwoFragment;
	private ShopTopOneFragment shopTopOneFragment;
	private Fragment[] fragments;
	private int tindex;
	private int currenttTabIndex = 0;
	private View view;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_shop, container, false);
		return view;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initView();

		tTabs[0].setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				setSelected(0, 1, 2);
				tindex = 0;
				selectFargment(tindex, currenttTabIndex);
			}
		});

		tTabs[1].setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				setSelected(1, 0, 2);
				tindex = 1;
				selectFargment(tindex, currenttTabIndex);
			}
		});

		tTabs[2].setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				setSelected(2, 0, 1);
				tindex = 2;
				selectFargment(tindex, currenttTabIndex);
			}
		});
	}

	private void initView() {

		shopTopOneFragment = new ShopTopOneFragment();
		shopTopTwoFragment = new ShopTopTwoFragment();
		shopTopThreeFragment = new ShopTopThreeFragment();
		fragments = new Fragment[] { shopTopOneFragment, shopTopTwoFragment,
				shopTopThreeFragment };

		tTabs = new TextView[3];
		tTabs[0] = (TextView) findViewById(R.id.tv_shop_top_1);
		tTabs[1] = (TextView) findViewById(R.id.tv_shop_top_2);
		tTabs[2] = (TextView) findViewById(R.id.tv_shop_top_3);

		tTabs[0].setSelected(true);

		getChildFragmentManager().beginTransaction()
				.add(R.id.shop_fragment_container, shopTopOneFragment)
				.add(R.id.shop_fragment_container, shopTopTwoFragment)
				.add(R.id.shop_fragment_container, shopTopThreeFragment)
				.hide(shopTopThreeFragment).hide(shopTopTwoFragment)
				.show(shopTopOneFragment).commit();
	}

	/**
	 * 选择Top Bar上面的按钮
	 * 
	 * @param x
	 *            选中的菜单
	 * @param y
	 * @param z
	 */
	public void setSelected(int x, int y, int z) {
		tTabs[x].setSelected(true);
		tTabs[y].setSelected(false);
		tTabs[z].setSelected(false);
	}

	/**
	 * 
	 */
	public void selectFargment(int t,int c) {
		if (currenttTabIndex != tindex) {
			FragmentTransaction transaction = getFragmentManager()
					.beginTransaction();
			transaction.hide(fragments[currenttTabIndex]);
			if (!fragments[tindex].isAdded()) {
				transaction
						.add(R.id.shop_fragment_container, fragments[tindex]);
			}
			transaction.show(fragments[tindex]).commit();
		}
		currenttTabIndex = tindex;
	}
}
