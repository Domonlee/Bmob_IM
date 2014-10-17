package com.bmob.im.demo.ui.fragment;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.bmob.im.demo.CustomApplcation;
import com.bmob.im.demo.R;
import com.bmob.im.demo.CustomApplcation.MyLocationListener;
import com.bmob.im.demo.adapter.TuanGouAdapter;
import com.bmob.im.demo.ui.FragmentBase;
import com.bmob.im.demo.ui.ShopGroupFragmen;
import com.bmob.im.demo.util.Constant;
import com.bmob.im.demo.view.task.AllFenLeiTask;
import com.bmob.im.demo.view.task.FanWeiTuanGouTask;
import com.bmob.im.demo.view.task.TuanGouTask;

public class ShopTopOneFragment extends FragmentBase implements OnClickListener {

	private TextView[] lTabs;
	private ImageView food;
	private ImageView meirong;
	private ImageView yule;
	private ImageView hunsha;
	private ImageView lvyou;
	private ImageView qita;
	private TextView weizhi;
	private TextView paixu;
	private TextView fanwei;
	private RelativeLayout rl;
	private LinearLayout layout;
	private ShopGroupFragmen shopGroupFragmen;
	private FragmentTransaction transaction;
	private Activity activity;
	private ListView list;
	private View footview;
	private ImageButton back;
	private ImageButton totop;
	public ArrayList<HashMap<String, String>> qilist = new ArrayList<HashMap<String, String>>();
	// 排序按钮是否可点
	private boolean isxuanzefenlei;
	//
	private TuanGouAdapter adapter;

	private TuanGouZhuFragment zhuFragment;
	private TuanGouFuFragment fuFragment;
	private Fragment[] fragments;
	private boolean isshengxu;
	private View view;
	private String order;
	private double latitude;
	private double longtitude;
	private LocationClient mLocationClient;
	private MyLocationListener mMyLocationListener;

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
		if (zhuFragment == null && fuFragment == null) {

			zhuFragment = new TuanGouZhuFragment();
			fuFragment = new TuanGouFuFragment();
			fuFragment.setFragment(this);
			fuFragment.setZhuFragment(zhuFragment);
		}

		getChildFragmentManager().beginTransaction()
				.add(R.id.rl_shopgroup, fuFragment).hide(fuFragment)
				.add(R.id.rl_shopgroup, zhuFragment).show(zhuFragment).commit();
		footview = LayoutInflater.from(activity).inflate(
				R.layout.listview_footview, null);
		TextView tvfoot = (TextView) footview
				.findViewById(R.id.tv_listview_foot);

		layout = (LinearLayout) v.findViewById(R.id.shop_group_layout);
		rl = (RelativeLayout) v.findViewById(R.id.rl_shopgroup);
		food = (ImageView) v.findViewById(R.id.shop_group_type_food);
		meirong = (ImageView) v.findViewById(R.id.shop_group_type_beauty);
		yule = (ImageView) v.findViewById(R.id.shop_group_type_funny);
		hunsha = (ImageView) v.findViewById(R.id.shop_group_type_mv);
		lvyou = (ImageView) v.findViewById(R.id.shop_group_type_hotel);
		qita = (ImageView) v.findViewById(R.id.shop_group_type_other);

		weizhi = (TextView) v.findViewById(R.id.btn_shop_location_myloca);
		paixu = (TextView) v.findViewById(R.id.btn_shop_location_sort);
		fanwei = (TextView) v.findViewById(R.id.btn_shop_location_range);
		list = (ListView) v.findViewById(R.id.lv_shopgroup_list);
		back = (ImageButton) v.findViewById(R.id.ib_shopgroup);
		totop = (ImageButton) v.findViewById(R.id.ib_totop);
		// shopGroupFragmen = new ShopGroupFragmen();
		// transaction = getFragmentManager().beginTransaction();
		AllFenLeiTask task = new AllFenLeiTask(activity);
		task.setContactList(qilist);
		task.setList(zhuFragment.list);
		task.setFragment(this);
		task.setFuFragment(fuFragment);
		task.setZhuFragment(zhuFragment);
		task.setFootview(footview);
		task.setUrl(Constant.TUANGOU_BASE_URL + "page=" + 1 + "&rows=" + 10);
		task.execute();

		weizhi.setOnClickListener(this);
		paixu.setOnClickListener(this);
		fanwei.setOnClickListener(this);
		tvfoot.setOnClickListener(this);
		// food.setOnClickListener(this);
		// meirong.setOnClickListener(this);
		// yule.setOnClickListener(this);
		// hunsha.setOnClickListener(this);
		// lvyou.setOnClickListener(this);
		// qita.setOnClickListener(this);
		// back.setOnClickListener(this);
		// totop.setOnClickListener(this);
		// /adapter = new TuanGouAdapter();

	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initView();
	}

	private void initView() {

		lTabs = new TextView[3];
		lTabs[0] = (TextView) findViewById(R.id.btn_shop_location_myloca);
		lTabs[1] = (TextView) findViewById(R.id.btn_shop_location_sort);
		lTabs[2] = (TextView) findViewById(R.id.btn_shop_location_range);

		lTabs[0].setSelected(true);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		// 餐饮美食
		case R.id.shop_group_type_food:
			break;
		// 美容保健
		case R.id.shop_group_type_beauty:
			break;
		// 休闲娱乐
		case R.id.shop_group_type_funny:
			break;
		// 婚纱摄影
		case R.id.shop_group_type_mv:
			break;
		// 旅游酒店
		case R.id.shop_group_type_hotel:
			break;
		// 其他
		case R.id.shop_group_type_other:
			break;
		// 我的位置
		case R.id.btn_shop_location_myloca:
			break;
		// 排序
		case R.id.btn_shop_location_sort:
			paixu();
			break;
		// 范围
		case R.id.btn_shop_location_range:
			showFuJin();
			break;
		// 返回
		case R.id.ib_shopgroup:
			qilist.removeAll(qilist);
			isxuanzefenlei = false;

			// rl.setVisibility(View.GONE);
			// layout.setVisibility(View.VISIBLE);
			break;

		case R.id.ib_totop:
			// 返回顶部
			list.requestFocusFromTouch();
			list.setSelection(0);
			break;
		case R.id.tv_listview_foot:
			int count = 1;
			count += 1;
			AllFenLeiTask task = new AllFenLeiTask(activity);
			task.setContactList(qilist);
			task.setList(zhuFragment.list);
			task.setFragment(this);
			task.setFuFragment(fuFragment);
			task.setZhuFragment(zhuFragment);
			task.setFootview(footview);
			task.setUrl(Constant.TUANGOU_BASE_URL + "page=" + count + "&rows="
					+ 10);
			task.execute();
			break;

		default:
			break;
		}

	}

	/**
	 * 展示附近商品
	 */
	private void showFuJin() {
		if (view == null)
			view = LayoutInflater.from(activity).inflate(
					R.layout.listview_footview, null);
		FanWeiTuanGouTask task = new FanWeiTuanGouTask(activity, view);
		task.setUrl(Constant.FANWEI_BASEURL + "jingwendu=108.893938,34.269029"
				+ "danstance=10000" + "&page=" + 1 + "&rows=" + 10);
		fuFragment.list.setTag(Constant.TUANGOU_TYPE_QITA);
		task.setList(zhuFragment.list);
		task.setContactList(qilist);
		task.execute();

	}

	private void paixu() {
		switch (Constant.paixu_type) {
		case 0:
			isshengxu = !isshengxu;
			order = isshengxu ? "asc" : "desc";
			AllFenLeiTask task = new AllFenLeiTask(activity);
			task.setList(zhuFragment.list);
			task.setFragment(this);
			task.setFuFragment(fuFragment);
			task.setZhuFragment(zhuFragment);
			task.setFootview(footview);
			task.setUrl(Constant.TUANGOU_BASE_URL + "page=" + 1 + "&rows=" + 10
					+ "&order=" + order);
			task.execute();
			break;
		case Constant.TUANGOU_TYPE_FOOD:
			showFood();
			break;
		case Constant.TUANGOU_TYPE_MEIRONG:
			showMeiRong();
			break;
		case Constant.TUANGOU_TYPE_YULE:
			showYuLe();
			break;
		case Constant.TUANGOU_TYPE_SHEYING:
			showSheYing();
			break;
		case Constant.TUANGOU_TYPE_JIUDIAN:
			showJiuDian();
			break;
		case Constant.TUANGOU_TYPE_QITA:
			showQiTa();
			break;

		default:
			break;
		}
	}

	/**
	 * 餐饮美食
	 */
	private void showFood() {
		isshengxu = !isshengxu;
		order = isshengxu ? "asc" : "desc";
		// showfuFragment();
		// transaction.add(R.id.rl_shopgroup, shopGroupFragmen)
		// .show(shopGroupFragmen).commit();
		if (view == null)
			view = LayoutInflater.from(activity).inflate(
					R.layout.listview_footview, null);
		TuanGouTask task = new TuanGouTask(activity, view);
		task.setUrl(Constant.TUANGOU_BASE_URL + "page=" + 1 + "&rows=" + 10
				+ "&fenleiid=" + Constant.TUANGOU_TYPE_FOOD + "&order=" + order);
		fuFragment.list.setTag(Constant.TUANGOU_TYPE_FOOD);
		task.setList(fuFragment.list);
		task.setContactList(qilist);
		task.execute();
	}

	/**
	 * 其他
	 */
	private void showQiTa() {
		isshengxu = !isshengxu;
		order = isshengxu ? "asc" : "desc";
		// showfuFragment();

		if (view == null)
			view = LayoutInflater.from(activity).inflate(
					R.layout.listview_footview, null);
		TuanGouTask task = new TuanGouTask(activity, view);
		task.setUrl(Constant.TUANGOU_BASE_URL + "page=" + 1 + "&rows=" + 1
				+ "&fenleiid=" + Constant.TUANGOU_TYPE_QITA + "&order=" + order);
		fuFragment.list.setTag(Constant.TUANGOU_TYPE_QITA);
		task.setList(fuFragment.list);
		task.setContactList(qilist);
		task.execute();
	}

	/**
	 * 旅游酒店
	 */
	private void showJiuDian() {
		// showfuFragment();
		isshengxu = !isshengxu;
		order = isshengxu ? "asc" : "desc";
		if (view == null)
			view = LayoutInflater.from(activity).inflate(
					R.layout.listview_footview, null);
		TuanGouTask task = new TuanGouTask(activity, view);
		task.setUrl(Constant.TUANGOU_BASE_URL + "page=" + 1 + "&rows=" + 1
				+ "&fenleiid=" + Constant.TUANGOU_TYPE_JIUDIAN);
		fuFragment.list.setTag(Constant.TUANGOU_TYPE_JIUDIAN + "&order="
				+ order);
		task.setList(fuFragment.list);
		task.setContactList(qilist);
		task.execute();
	}

	/**
	 * 婚纱摄影
	 */
	private void showSheYing() {
		// showfuFragment();
		isshengxu = !isshengxu;
		order = isshengxu ? "asc" : "desc";
		if (view == null)
			view = LayoutInflater.from(activity).inflate(
					R.layout.listview_footview, null);
		TuanGouTask task = new TuanGouTask(activity, view);
		task.setUrl(Constant.TUANGOU_BASE_URL + "page=" + 1 + "&rows=" + 1
				+ "&fenleiid=" + Constant.TUANGOU_TYPE_SHEYING + "&order="
				+ order);
		fuFragment.list.setTag(Constant.TUANGOU_TYPE_SHEYING);
		task.setList(fuFragment.list);
		task.setContactList(qilist);
		task.execute();
	}

	/**
	 * 休闲娱乐
	 */
	private void showYuLe() {
		// showfuFragment();
		isshengxu = !isshengxu;
		order = isshengxu ? "asc" : "desc";
		if (view == null)
			view = LayoutInflater.from(activity).inflate(
					R.layout.listview_footview, null);
		TuanGouTask task = new TuanGouTask(activity, view);
		task.setUrl(Constant.TUANGOU_BASE_URL + "page=" + 1 + "&rows=" + 1
				+ "&fenleiid=" + Constant.TUANGOU_TYPE_YULE + "&order=" + order);
		fuFragment.list.setTag(Constant.TUANGOU_TYPE_YULE);
		task.setList(fuFragment.list);
		task.setContactList(qilist);
		task.execute();
	}

	/**
	 * 美容保健
	 */
	private void showMeiRong() {
		// showfuFragment();
		isshengxu = !isshengxu;
		order = isshengxu ? "asc" : "desc";
		if (view == null)
			view = LayoutInflater.from(activity).inflate(
					R.layout.listview_footview, null);
		TuanGouTask task = new TuanGouTask(activity, view);
		task.setUrl(Constant.TUANGOU_BASE_URL + "page=" + 1 + "&rows=" + 1
				+ "&fenleiid=" + Constant.TUANGOU_TYPE_MEIRONG + "&order="
				+ order);
		fuFragment.list.setTag(Constant.TUANGOU_TYPE_MEIRONG);
		task.setList(fuFragment.list);
		task.setContactList(qilist);
		task.execute();
	}

}
