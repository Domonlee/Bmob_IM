package com.bmob.im.demo.util;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;

import com.bmob.im.demo.R;

public class HeaderViewUtil {
	public static View getHeaderView(Activity activity) {
		View view = LayoutInflater.from(activity).inflate(
				R.layout.activity_list_header_item, null);
		RelativeLayout rl = (RelativeLayout) view
				.findViewById(R.id.rl_header_rl);
		LinearLayout ll = (LinearLayout) view.findViewById(R.id.ll_header_ll);
		LinearLayout ll2 = (LinearLayout) view.findViewById(R.id.ll_header_ll2);
		int width = WindowUtil.getWidth(activity) - PixelUtil.dp2px(5);

		rl.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, width * 9 / 16));

		int swith = (width - PixelUtil.dp2px(15)) / 2;
		ll.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, swith * 9 / 16));
		ll.setPadding(0, PixelUtil.dp2px(5), 0, PixelUtil.dp2px(5));
		ll2.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, swith * 9 / 16));
		return view;
	}

}
