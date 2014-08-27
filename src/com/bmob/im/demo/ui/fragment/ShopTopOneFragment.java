package com.bmob.im.demo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bmob.im.demo.R;
import com.bmob.im.demo.ui.FragmentBase;

public class ShopTopOneFragment extends FragmentBase {

	private TextView[] lTabs ;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_shop_groupby,container,false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initView();
	}

	private void initView() {

		lTabs = new TextView[3];
		lTabs[0] = (TextView) findViewById(R.id.btn_shop_location_myloca);
		lTabs[1] = (TextView) findViewById(R.id.btn_shop_location_sort);
		lTabs[2] = (TextView) findViewById(R.id.btn_shop_location_range);
		
		lTabs[0].setSelected(true);
	}

	
}
