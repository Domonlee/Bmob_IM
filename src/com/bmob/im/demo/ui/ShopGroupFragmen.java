package com.bmob.im.demo.ui;

import com.bmob.im.demo.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

public class ShopGroupFragmen extends Fragment {
	public ListView list;
	public ImageButton back;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_shopgroup, null);
		init(view);
		return view;
	}

	private void init(View view) {
		list = (ListView) view.findViewById(R.id.lv_shopgroup_list);
		back = (ImageButton) view.findViewById(R.id.ib_shopgroup);
	}

}
