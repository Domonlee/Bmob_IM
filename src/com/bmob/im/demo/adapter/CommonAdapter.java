package com.bmob.im.demo.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bmob.im.demo.R;
import com.bmob.im.demo.model.ItemCommon;

public class CommonAdapter extends BaseAdapter {

	private Context mContext;
	private List<ItemCommon> mLists;
	private LayoutInflater mLayoutInflater;

	private ImageView common_image_icon;
	private TextView common_text_item;

	public CommonAdapter(Context pContext, List<ItemCommon> pLists) {
		this.mContext = pContext;
		this.mLists = pLists;
		mLayoutInflater = LayoutInflater.from(mContext.getApplicationContext());
	}

	@Override
	public int getCount() {
		return mLists != null ? mLists.size() : 0;
	}

	@Override
	public Object getItem(int arg0) {
		return mLists.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int arg0, View view, ViewGroup parent) {
		if (view == null) {
			view = mLayoutInflater
					.inflate(R.layout.left_menu_common_item, null);
			common_image_icon = (ImageView)view.findViewById(R.id.left_iv_common_icon);
			common_text_item = (TextView)view.findViewById(R.id.left_tv_common_text);
		}
		common_image_icon.setImageResource(mLists.get(arg0).getId());
		common_text_item.setText(mLists.get(arg0).getName());
		return view;
	}

}
