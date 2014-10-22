package com.bmob.im.demo.ui.fragment;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.bmob.im.demo.R;
import com.bmob.im.demo.adapter.TuanGouAdapter;
import com.bmob.im.demo.ui.FragmentBase;
import com.bmob.im.demo.ui.ShopGroupFragmen;
import com.bmob.im.demo.util.Constant;
import com.bmob.im.demo.util.DialogUtil;
import com.bmob.im.demo.view.task.AllFenLeiTask;
import com.bmob.im.demo.view.task.CityTask;
import com.bmob.im.demo.view.task.JiFenTask;
import com.bmob.im.demo.view.task.TuanGouTask;
import com.bmob.im.demo.view.task.XianShiGouTask;
import com.bmob.im.newview.MainFoodActivity;

public class ShopTopOneFragment extends FragmentBase implements OnClickListener {

	private TextView[] lTabs;
	private TextView weizhi;
	private TextView paixu;
	private TextView fanwei;
	private ShopGroupFragmen shopGroupFragmen;
	private FragmentTransaction transaction;
	private Activity activity;
	private ListView list;
	private ImageButton back;
	private ImageButton totop;
	public ArrayList<HashMap<String, String>> qilist = new ArrayList<HashMap<String, String>>();
	//
	private TuanGouAdapter adapter;

	private TuanGouFuFragment fuFragment;
	private boolean isshengxu;
	private View view;
	private String order;
	private ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();
	private String count;

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_shop_groupby, container,
				false);
		init(v);
		return v;
	}

	private void init(View v) {
		if (fuFragment == null) {

			fuFragment = new TuanGouFuFragment();
			fuFragment.setFragment(this);
			fuFragment.setActivity(activity);
		}
		view = LayoutInflater.from(activity).inflate(
				R.layout.listview_footview, null);
		getChildFragmentManager().beginTransaction()
				.add(R.id.rl_shopgroup, fuFragment).show(fuFragment).commit();

		// layout = (LinearLayout) v.findViewById(R.id.shop_group_layout);
		// rl = (RelativeLayout) v.findViewById(R.id.rl_shopgroup);
		// food = (ImageView) v.findViewById(R.id.shop_group_type_food);
		// meirong = (ImageView) v.findViewById(R.id.shop_group_type_beauty);
		// yule = (ImageView) v.findViewById(R.id.shop_group_type_funny);
		// hunsha = (ImageView) v.findViewById(R.id.shop_group_type_mv);
		// lvyou = (ImageView) v.findViewById(R.id.shop_group_type_hotel);
		// qita = (ImageView) v.findViewById(R.id.shop_group_type_other);
		weizhi = (TextView) v.findViewById(R.id.btn_shop_location_myloca);
		paixu = (TextView) v.findViewById(R.id.btn_shop_location_sort);
		fanwei = (TextView) v.findViewById(R.id.btn_shop_location_range);
		// totop = (ImageButton) v.findViewById(R.id.ib_totop);
		fanwei.setOnClickListener(this);
		weizhi.setOnClickListener(this);
		paixu.setOnClickListener(this);
		// totop.setOnClickListener(this);
		// RadioButton tvfoot = (RadioButton) view
		// .findViewById(R.id.tv_listview_foot);
		// tvfoot.setOnClickListener(this);
		// 显示所有商品
		shouAll();

	}

	/**
	 * 展示所有商品
	 */
	public void shouAll() {

		AllFenLeiTask task = new AllFenLeiTask(activity);
		Constant.TUANGOU_FENLEI_TYPE = 0;
		task.setContactList(qilist);
		task.setList(fuFragment.list);
		task.setFuFragment(fuFragment);
		task.setFootview(view);
		task.setUrl(Constant.TUANGOU_BASE_URL + "page=" + 1 + "&rows=" + 10);
		task.execute();
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		// 我的位置
		case R.id.btn_shop_location_myloca:
			showWeizhi();
			break;
		// 排序
		case R.id.btn_shop_location_sort:
			paixu();
			break;
		// 范围
		case R.id.btn_shop_location_range:
			showFanWeiDialog();
			break;
		// 返回
		case R.id.ib_shopgroup:
			shouAll();

			break;

		case R.id.ib_totop:
			// 返回顶部
			// fuFragment.list.requestFocusFromTouch();
			// fuFragment.list.setSelection(0);

			break;
		case R.id.tv_listview_foot:
			// showMore();
			break;

		default:
			break;
		}

	}

	/**
	 * 位置选择
	 */
	private void showWeizhi() {
		DialogUtil.CreateWeiZhiDialog(activity, weizhi, contactList, view,
				fuFragment);
	}

	/**
	 * 范围选择
	 */
	private void showFanWeiDialog() {
		DialogUtil.createMessageDialog(activity, fanwei, contactList,
				fuFragment, view);

	}

	/**
	 * 更多
	 */
	private void showMore() {

		if (Constant.URL_TYPE == 0) {
			// 全部商品
			Constant.COUNT += 1;
			int n = Constant.COUNT - 1;
			AllFenLeiTask task = new AllFenLeiTask(activity);
			Constant.TUANGOU_FENLEI_TYPE = 0;
			task.setContactList(qilist);
			task.setList(fuFragment.list);
			task.setFuFragment(fuFragment);
			fuFragment.list.setSelection(Constant.visibleitemCount);
			task.setFootview(view);
			task.setUrl(Constant.BEFORE_TUANGOU_URL.replace("page=" + n,
					"page=" + Constant.COUNT));
			task.execute();

		} else if (Constant.URL_TYPE == 1) {
			// 分类商品
			Constant.COUNT += 1;
			int n = Constant.COUNT - 1;
			TuanGouTask task = new TuanGouTask(activity, view);
			task.setContactList(contactList);
			task.setList(fuFragment.list);
			fuFragment.list.setSelection(Constant.visibleitemCount);
			task.setFragment(fuFragment);
			task.setUrl(Constant.BEFORE_TUANGOU_URL.replace("page=" + n,
					"page=" + Constant.COUNT));

			task.execute();

		} else if (Constant.URL_TYPE == 2) {
			// 限时购
			Constant.COUNT += 1;
			int n = Constant.COUNT - 1;
			XianShiGouTask task = new XianShiGouTask(activity);
			task.setList(fuFragment.list);
			task.setUrl(Constant.BEFORE_URL.replace("page=" + n, "page="
					+ Constant.COUNT));
			task.execute();
		} else if (Constant.URL_TYPE == 3) {
			// 积分兑换
			Constant.COUNT += 1;
			int n = Constant.COUNT - 1;
			JiFenTask task = new JiFenTask(activity);
			task.setList(fuFragment.list);
			task.setUrl(Constant.BEFORE_URL.replace("page=" + n, "page="
					+ Constant.COUNT));
			task.execute();
		}

	}

	/**
	 * 排序
	 */
	private void paixu() {
		isshengxu = !isshengxu;
		order = isshengxu ? "asc" : "desc";
		if (Constant.BEFORE_TUANGOU_URL.contains("&order=")) {
			int t = Constant.BEFORE_TUANGOU_URL.indexOf("&order=");
			Constant.BEFORE_TUANGOU_URL = Constant.BEFORE_TUANGOU_URL
					.substring(0, t);

		}

		if (!Constant.BEFORE_TUANGOU_URL.contains("fenleiid")) {
			// 全部商品

			AllFenLeiTask task = new AllFenLeiTask(activity);
			Constant.TUANGOU_FENLEI_TYPE = 0;
			task.setContactList(qilist);
			task.setList(fuFragment.list);
			task.setFuFragment(fuFragment);
			task.setFootview(view);
			task.setUrl(Constant.BEFORE_TUANGOU_URL + "&order=" + order);
			task.execute();

		} else if (Constant.URL_TYPE == 1) {
			// 分类商品
			TuanGouTask task = new TuanGouTask(activity, view);
			task.setContactList(contactList);
			task.setList(fuFragment.list);
			task.setFragment(fuFragment);
			task.setUrl(Constant.BEFORE_TUANGOU_URL + "&order=" + order);
			task.execute();

		}

	}

}
