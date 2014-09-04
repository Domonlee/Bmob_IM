package com.bmob.im.demo.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import cn.bmob.im.BmobUserManager;
import cn.bmob.v3.listener.UpdateListener;

import com.bmob.im.demo.R;
import com.bmob.im.demo.adapter.CommonAdapter;
import com.bmob.im.demo.adapter.SettingAdapter;
import com.bmob.im.demo.bean.User;
import com.bmob.im.demo.model.ItemCommon;
import com.bmob.im.demo.model.ItemSetting;
import com.bmob.im.demo.ui.ExchangeInfoActivity;
import com.bmob.im.demo.ui.GroupbyCodeInfoActivity;
import com.bmob.im.demo.ui.MessageInfoActivity;
import com.bmob.im.demo.ui.MyInviteInfoActivity;
import com.bmob.im.demo.ui.MyshareInfoActivity;
import com.bmob.im.demo.ui.OrderInfoActivity;
import com.bmob.im.demo.ui.SetMyInfoActivity;
import com.bmob.im.demo.ui.SettingInfoActivity;
import com.bmob.im.demo.util.ImageLoadOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * @author Domon
 * 
 */
public class LeftMenuBottomFragment extends Fragment {

	private View mView;
	private ListView lv_Common, lv_Setting;
	private Context mContext;
	private ImageView iv_set_avatar;
	private RelativeLayout topbarLayout;

	private List<ItemCommon> commonModels;
	private List<ItemSetting> settingsModels;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		if (null == mView) {
			mView = inflater.inflate(R.layout.left_menu_fragment, container,
					false);
			BmobUserManager userManager = new BmobUserManager();
			userManager = BmobUserManager.getInstance(mContext);
			User user = (User) userManager.getCurrentUser(User.class);
			initView();
			initValiData();
			bindData();
			refreshAvatar(user.getAvatar());

			lv_Common.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					Intent mIntent = null;
					switch (position) {
					case 0:
						mIntent = new Intent(getActivity(),
								OrderInfoActivity.class);
						break;
					case 1:
						mIntent = new Intent(getActivity(),
								GroupbyCodeInfoActivity.class);
						break;

					case 2:
						mIntent = new Intent(getActivity(),
								ExchangeInfoActivity.class);
						break;

					case 3:
						mIntent = new Intent(getActivity(),
								MyInviteInfoActivity.class);
						break;

					case 4:
						mIntent = new Intent(getActivity(),
								MyshareInfoActivity.class);
						break;
					}
					startActivity(mIntent);
					getActivity().overridePendingTransition(
							R.anim.push_left_in, R.anim.push_left_out);
				}
			});

			lv_Setting.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					Intent myIntent = null;
					switch (position) {
					case 0:
						myIntent = new Intent(getActivity(),
								MessageInfoActivity.class);
						break;
					case 1:
						myIntent = new Intent(getActivity(),
								SettingInfoActivity.class);
						break;
					}
					startActivity(myIntent);
					getActivity().overridePendingTransition(
							R.anim.push_left_in, R.anim.push_left_out);
				}
			});

		}
		return mView;
	}

	private void initView() {
		lv_Common = (ListView) mView.findViewById(R.id.left_listview_common);
		lv_Setting = (ListView) mView.findViewById(R.id.left_listview_setting);
		iv_set_avatar = (ImageView) mView.findViewById(R.id.iv_left_menu_head);

		// userManager = BmobUserManager.getInstance(mContext);
		topbarLayout = (RelativeLayout) mView
				.findViewById(R.id.layout_left_menu_topbar);
		topbarLayout.setOnClickListener(new ButtonListener());
	}

	public class ButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.layout_left_menu_topbar:
				Intent goUserInfoIntent = new Intent(getActivity(),
						SetMyInfoActivity.class);
				goUserInfoIntent.putExtra("from", "me");
				startActivity(goUserInfoIntent);
				break;
			}
		}
	}

	private void initValiData() {
		mContext = mView.getContext();
		commonModels = new ArrayList<ItemCommon>();
		settingsModels = new ArrayList<ItemSetting>();

		// 1. fill data in the commonlist
		Integer[] common_icon_id = new Integer[] {
				R.drawable.left_menu_icon_shoppinglist,
				R.drawable.left_menu_icon_groupbuy,
				R.drawable.left_menu_icon_exchange,
				R.drawable.left_menu_icon_invite,
				R.drawable.left_menu_icon_share };
		String[] common_arrays = mContext.getResources().getStringArray(
				R.array.left_arrays_common);
		for (int i = 0; i < common_icon_id.length; i++) {
			ItemCommon common = new ItemCommon(common_icon_id[i],
					common_arrays[i]);
			commonModels.add(common);
		}

		// 2. fill data in the settinglist
		Integer[] setting_icon_id = new Integer[] {
				R.drawable.left_menu_icon_message,
				R.drawable.left_menu_icon_setting };
		String[] setting_arrays = mContext.getResources().getStringArray(
				R.array.left_arrays_setting);
		for (int i = 0; i < setting_icon_id.length; i++) {
			ItemSetting setting = new ItemSetting(setting_icon_id[i],
					setting_arrays[i]);
			settingsModels.add(setting);
		}
	}

	/**
	 * Title: bindData Description: bind data in listview
	 * 
	 * @author Domon
	 */
	private void bindData() {
		lv_Common.setAdapter(new CommonAdapter(mContext, commonModels));
		lv_Setting.setAdapter(new SettingAdapter(mContext, settingsModels));
	}

	/**
	 * ¸üÐÂÍ·Ïñ refreshAvatar
	 * 
	 * @return void
	 * @throws
	 */
	private void refreshAvatar(String avatar) {
		if (avatar != null && !avatar.equals("")) {
			ImageLoader.getInstance().displayImage(avatar, iv_set_avatar,
					ImageLoadOptions.getOptions());
		} else {
			iv_set_avatar.setImageResource(R.drawable.default_head);
		}
	}
}
