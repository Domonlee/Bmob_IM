package com.bmob.im.demo.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bmob.im.demo.R;
import com.bmob.im.demo.ui.FragmentBase;
import com.bmob.im.demo.view.task.MyDiandanTask;
import com.bmob.im.newview.DaTiActivity;
import com.bmob.im.newview.MoneyItemActivity;

public class MoneyFragment extends FragmentBase implements OnClickListener {
	private Activity activity;
	private TextView qiandao;
	private TextView dati;

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_money, null);
		init(view);
		return view;
	}

	private void init(View view) {
		qiandao = (TextView) view.findViewById(R.id.tv_money_qiandao);
		dati = (TextView) view.findViewById(R.id.tv_money_dati);
		qiandao.setOnClickListener(this);
		dati.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_money_qiandao:
			qiandao.setText("已签到");
			qiandao.setClickable(false);
			Toast.makeText(activity, "积分+20", Toast.LENGTH_SHORT).show();
			break;
		case R.id.tv_money_dati:
			Intent intent = new Intent(activity, DaTiActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}
}
