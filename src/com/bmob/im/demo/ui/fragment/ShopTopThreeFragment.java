package com.bmob.im.demo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.bmob.im.demo.R;
import com.bmob.im.demo.ui.FragmentBase;

public class ShopTopThreeFragment extends FragmentBase {
	public ListView list;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragment_shop_exchange, container,
				false);

		init(v);
		return v;
	}

	private void init(View v) {
		list = (ListView) v.findViewById(R.id.lv_jifenduihuan);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initView();
	}

	private void initView() {
	}

}
