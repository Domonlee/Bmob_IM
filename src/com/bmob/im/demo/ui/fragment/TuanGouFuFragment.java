package com.bmob.im.demo.ui.fragment;

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

public class TuanGouFuFragment extends Fragment {
	public ListView list;
	public ImageButton back;
	public ImageButton totop;
	private ShopTopOneFragment fragment;
	private TuanGouZhuFragment zhuFragment;

	public void setFragment(ShopTopOneFragment fragment) {
		this.fragment = fragment;
	}

	public void setZhuFragment(TuanGouZhuFragment zhuFragment) {
		this.zhuFragment = zhuFragment;
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
				Constant.paixu_type = 0;
				FragmentTransaction transaction = fragment.getFragmentManager()
						.beginTransaction();

				transaction.hide(TuanGouFuFragment.this);
				// transaction.remove(TuanGouFuFragment.this);
				// transaction.add(R.id.rl_shopgroup, zhuFragment);
				transaction.show(zhuFragment).commit();
			}
		});

	}
}
