package com.bmob.im.demo.util;

import java.util.ArrayList;
import java.util.HashMap;

import com.bmob.im.demo.R;
import com.bmob.im.demo.ui.fragment.TuanGouFuFragment;
import com.bmob.im.demo.view.task.AllFenLeiTask;
import com.bmob.im.demo.view.task.CityTask;
import com.bmob.im.demo.view.task.TuanGouTask;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class DialogUtil {
	/**
	 * 显示范围选择
	 * 
	 * @param activity
	 * @param tv
	 * @param contactList
	 * @param fuFragment
	 * @param view
	 * @return
	 */

	public static View createMessageDialog(final Activity activity,
			TextView tv, final ArrayList<HashMap<String, String>> contactList,
			final TuanGouFuFragment fuFragment, final View view) {

		View v = LayoutInflater.from(activity).inflate(R.layout.fanwei_item,
				null);
		final PopupWindow window = new PopupWindow(v, tv.getWidth(),
				LayoutParams.WRAP_CONTENT);
		window.setOutsideTouchable(true);
		window.setBackgroundDrawable(new ColorDrawable());
		window.setFocusable(true);

		window.showAsDropDown(tv, 0, 0);
		RadioButton button1 = (RadioButton) v.findViewById(R.id.rb_fanwei_rb1);
		RadioButton button2 = (RadioButton) v.findViewById(R.id.rb_fanwei_rb2);
		RadioButton button3 = (RadioButton) v.findViewById(R.id.rb_fanwei_rb3);
		RadioButton button4 = (RadioButton) v.findViewById(R.id.rb_fanwei_rb4);

		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				window.dismiss();
				if (Constant.URL_TYPE == 0) {
					AllFenLeiTask task = new AllFenLeiTask(activity);
					task.setContactList(contactList);
					task.setList(fuFragment.list);
					task.setFuFragment(fuFragment);
					fuFragment.list.setTag(Constant.TUANGOU_FENLEI_TYPE);

					Constant.fanwei = 500;
					task.setUrl(Constant.FANWEI_BASEURL + "jingwendu="
							+ Constant.WEIDU + "," + Constant.JINGDU
							+ "&danstance=" + Constant.fanwei + "&page=" + 1
							+ "&rows=" + 10);

					task.execute();
				} else {
					TuanGouTask task = new TuanGouTask(activity, view);
					task.setContactList(contactList);
					task.setList(fuFragment.list);
					fuFragment.list.setTag(Constant.TUANGOU_FENLEI_TYPE);
					Constant.fanwei = 500;
					task.setUrl(Constant.FANWEI_BASEURL + "jingwendu="
							+ Constant.WEIDU + "," + Constant.JINGDU
							+ "&danstance=" + Constant.fanwei + "&page=" + 1
							+ "&rows=" + 10 + "&fenleiid="
							+ Constant.TUANGOU_FENLEI_TYPE);

					task.execute();
				}
			}
		});

		button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				window.dismiss();
				if (Constant.URL_TYPE == 0) {
					AllFenLeiTask task = new AllFenLeiTask(activity);
					task.setContactList(contactList);
					task.setList(fuFragment.list);
					task.setFuFragment(fuFragment);
					fuFragment.list.setTag(Constant.TUANGOU_FENLEI_TYPE);

					Constant.fanwei = 1000;
					task.setUrl(Constant.FANWEI_BASEURL + "jingwendu="
							+ Constant.WEIDU + "," + Constant.JINGDU
							+ "&danstance=" + Constant.fanwei + "&page=" + 1
							+ "&rows=" + 10);

					task.execute();
				} else {
					TuanGouTask task = new TuanGouTask(activity, view);
					task.setContactList(contactList);
					task.setList(fuFragment.list);
					fuFragment.list.setTag(Constant.TUANGOU_FENLEI_TYPE);
					Constant.fanwei = 1000;
					task.setUrl(Constant.FANWEI_BASEURL + "jingwendu="
							+ Constant.WEIDU + "," + Constant.JINGDU
							+ "&danstance=" + Constant.fanwei + "&page=" + 1
							+ "&rows=" + 10 + "&fenleiid="
							+ Constant.TUANGOU_FENLEI_TYPE);

					task.execute();
				}
			}
		});
		button3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				window.dismiss();
				if (Constant.URL_TYPE == 0) {
					AllFenLeiTask task = new AllFenLeiTask(activity);
					task.setContactList(contactList);
					task.setList(fuFragment.list);
					task.setFuFragment(fuFragment);
					fuFragment.list.setTag(Constant.TUANGOU_FENLEI_TYPE);

					Constant.fanwei = 2000;
					task.setUrl(Constant.FANWEI_BASEURL + "jingwendu="
							+ Constant.WEIDU + "," + Constant.JINGDU
							+ "&danstance=" + Constant.fanwei + "&page=" + 1
							+ "&rows=" + 10);

					task.execute();
				} else {
					TuanGouTask task = new TuanGouTask(activity, view);
					task.setContactList(contactList);
					task.setList(fuFragment.list);
					fuFragment.list.setTag(Constant.TUANGOU_FENLEI_TYPE);
					Constant.fanwei = 2000;
					task.setUrl(Constant.FANWEI_BASEURL + "jingwendu="
							+ Constant.WEIDU + "," + Constant.JINGDU
							+ "&danstance=" + Constant.fanwei + "&page=" + 1
							+ "&rows=" + 10 + "&fenleiid="
							+ Constant.TUANGOU_FENLEI_TYPE);

					task.execute();
				}
			}
		});
		button4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				window.dismiss();
				if (Constant.URL_TYPE == 0) {
					AllFenLeiTask task = new AllFenLeiTask(activity);
					task.setContactList(contactList);
					task.setList(fuFragment.list);
					task.setFuFragment(fuFragment);
					fuFragment.list.setTag(Constant.TUANGOU_FENLEI_TYPE);

					Constant.fanwei = 5000;
					task.setUrl(Constant.FANWEI_BASEURL + "jingwendu="
							+ Constant.WEIDU + "," + Constant.JINGDU
							+ "&danstance=" + Constant.fanwei + "&page=" + 1
							+ "&rows=" + 10);

					task.execute();
				} else {
					TuanGouTask task = new TuanGouTask(activity, view);
					task.setContactList(contactList);
					task.setList(fuFragment.list);
					fuFragment.list.setTag(Constant.TUANGOU_FENLEI_TYPE);
					Constant.fanwei = 5000;
					task.setUrl(Constant.FANWEI_BASEURL + "jingwendu="
							+ Constant.WEIDU + "," + Constant.JINGDU
							+ "&danstance=" + Constant.fanwei + "&page=" + 1
							+ "&rows=" + 10 + "&fenleiid="
							+ Constant.TUANGOU_FENLEI_TYPE);

					task.execute();
				}

			}
		});
		return v;
	}

	/**
	 * 展示区域选择
	 * 
	 * @param activity
	 * @param tv
	 * @param contactList
	 * @param view
	 * @param fuFragment
	 * @return
	 */
	public static View CreateWeiZhiDialog(final Activity activity, TextView tv,
			final ArrayList<HashMap<String, String>> contactList,
			final View view, final TuanGouFuFragment fuFragment) {
		View v = LayoutInflater.from(activity).inflate(R.layout.weizhi_item,
				null);
		final PopupWindow window = new PopupWindow(v, tv.getWidth(),
				LayoutParams.WRAP_CONTENT);
		window.setOutsideTouchable(true);
		window.setBackgroundDrawable(new ColorDrawable());
		window.setFocusable(true);

		window.showAsDropDown(tv, 0, 0);
		ListView list = (ListView) v.findViewById(R.id.lv_weizhi);
		// 启动任务
		CityTask task = new CityTask(activity);
		task.setUrl(Constant.CITY_URL + "cityid=1");
		task.setList(list);
		task.setFragment(fuFragment);
		task.setView(view);
		task.execute();
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int position,
					long id) {
				window.dismiss();
				TuanGouTask task = new TuanGouTask(activity, view);

				task.setList(fuFragment.list);

				task.setContactList(new ArrayList<HashMap<String, String>>());
				if (Constant.TUANGOU_FENLEI_TYPE == 0) {
					task.setUrl(Constant.CITY_BASE + "diqu=" + position
							+ "&rows=10" + "&page=1");
				} else {
					task.setUrl(Constant.CITY_BASE + "diqu=" + position
							+ "&rows=10" + "&page=1" + "&fenleiid="
							+ Constant.TUANGOU_FENLEI_TYPE);
				}
				task.execute();

			}
		});

		return v;
	}
}
