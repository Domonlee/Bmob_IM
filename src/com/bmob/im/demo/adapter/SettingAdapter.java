package com.bmob.im.demo.adapter;

import java.util.List;

import com.baidu.platform.comapi.map.n;
import com.bmob.im.demo.R;
import com.bmob.im.demo.model.ItemSetting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SettingAdapter extends BaseAdapter {

	private Context mContext;
	private List<ItemSetting> mLists;
	private LayoutInflater mLayoutInflater;

	private ImageView setting_image_icon;
	private TextView setting_text_item;

	public SettingAdapter(Context pContext, List<ItemSetting> pList) {
		this.mContext = pContext;
		this.mLists = pList;
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
			view = mLayoutInflater.inflate(
					R.layout.left_menu_setting_item, null);
			setting_image_icon = (ImageView) view
					.findViewById(R.id.left_iv_setting_icon);
			setting_text_item = (TextView) view
					.findViewById(R.id.left_tv_setting_text);
		}
		setting_image_icon.setImageResource(mLists.get(arg0).getId());
		setting_text_item.setText(mLists.get(arg0).getName());
		return view;
	}

}
