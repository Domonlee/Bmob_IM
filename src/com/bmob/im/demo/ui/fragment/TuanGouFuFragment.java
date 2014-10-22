package com.bmob.im.demo.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import com.bmob.im.demo.R;
import com.bmob.im.demo.util.Constant;
import com.bmob.im.newview.MainFoodActivity;

public class TuanGouFuFragment extends Fragment {
	public ListView list;
	public ImageButton back;
	public ImageButton totop;
	private ShopTopOneFragment fragment;
	private Activity activity;
	

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public void setFragment(ShopTopOneFragment fragment) {
		this.fragment = fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.tuan_fu_fragment, null);
		init(v);
		return v;
	}

	private void init(View v) {
		list = (ListView) v.findViewById(R.id.lv_shopgroup_list);
		totop = (ImageButton) v.findViewById(R.id.ib_fu_totop);
		back = (ImageButton) v.findViewById(R.id.ib_shopgroup_back);
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				fragment.shouAll();
				Constant.COUNT = 1;
			}
		});

		totop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent id = new Intent(activity, MainFoodActivity.class);
				startActivity(id);
			}
		});

	}
}
