package com.bmob.im.demo.ui.fragment;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bmob.im.demo.R;
import com.bmob.im.demo.ui.FragmentBase;
import com.bmob.im.demo.util.Constant;
import com.bmob.im.demo.util.DialogUtil;
import com.bmob.im.demo.view.task.AllFenLeiTask;
import com.bmob.im.demo.view.task.TuanGouTask;

public class ShopTopOneFragment extends FragmentBase implements OnClickListener {

	private TextView weizhi;
	private TextView paixu;
	private TextView fanwei;
	private Activity activity;
	public ArrayList<HashMap<String, String>> qilist = new ArrayList<HashMap<String, String>>();
	//

	private TuanGouFuFragment fuFragment;
	private boolean isshengxu;
	private View view;
	private String order;
	private ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();

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

		weizhi = (TextView) v.findViewById(R.id.btn_shop_location_myloca);
		paixu = (TextView) v.findViewById(R.id.btn_shop_location_sort);
		fanwei = (TextView) v.findViewById(R.id.btn_shop_location_range);
		fanwei.setOnClickListener(this);
		weizhi.setOnClickListener(this);
		paixu.setOnClickListener(this);

		// ��ʾ������Ʒ
		shouAll();

	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		// �ҵ�λ��
		case R.id.btn_shop_location_myloca:
			showWeizhi();
			break;
		// ����
		case R.id.btn_shop_location_sort:
			paixu();
			break;
		// ��Χ
		case R.id.btn_shop_location_range:
			showFanWeiDialog();
			break;

		default:
			break;
		}

	}

	/**
	 * չʾ������Ʒ
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

	/**
	 * λ��ѡ��
	 */
	private void showWeizhi() {
		DialogUtil.CreateWeiZhiDialog(activity, weizhi, contactList, view,
				fuFragment);
	}

	/**
	 * ��Χѡ��
	 */
	private void showFanWeiDialog() {
		DialogUtil.createMessageDialog(activity, fanwei, contactList,
				fuFragment, view);

	}

	/**
	 * ����
	 */
	private void paixu() {
		isshengxu = !isshengxu;
		order = isshengxu ? "asc" : "desc";
		// ������������ֶ� ��ȥ��
		if (Constant.BEFORE_TUANGOU_URL.contains("&order=")) {
			int t = Constant.BEFORE_TUANGOU_URL.indexOf("&order=");
			Constant.BEFORE_TUANGOU_URL = Constant.BEFORE_TUANGOU_URL
					.substring(0, t);

		}

		if (!Constant.BEFORE_TUANGOU_URL.contains("fenleiid")) {
			// ȫ����Ʒ

			AllFenLeiTask task = new AllFenLeiTask(activity);
			Constant.TUANGOU_FENLEI_TYPE = 0;
			task.setContactList(qilist);
			task.setList(fuFragment.list);
			task.setFuFragment(fuFragment);
			task.setFootview(view);
			task.setUrl(Constant.BEFORE_TUANGOU_URL + "&order=" + order);
			task.execute();

		} else if (Constant.URL_TYPE == 1) {
			// ������Ʒ
			TuanGouTask task = new TuanGouTask(activity, view);
			task.setContactList(contactList);
			task.setList(fuFragment.list);
			task.setFragment(fuFragment);
			task.setUrl(Constant.BEFORE_TUANGOU_URL + "&order=" + order);
			task.execute();

		}

	}

}
