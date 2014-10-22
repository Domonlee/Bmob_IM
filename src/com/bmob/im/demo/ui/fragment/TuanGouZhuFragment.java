package com.bmob.im.demo.ui.fragment;

import com.bmob.im.demo.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

public class TuanGouZhuFragment extends Fragment {
	public ListView list;
	public ImageButton totop;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.tuan_zhu_fragment, null);
		init(v);
		return v;
	}

	private void init(View v) {
		list = (ListView) v.findViewById(R.id.lv_shopgroup_list);
		totop = (ImageButton) v.findViewById(R.id.ib_totop);
	}

}
