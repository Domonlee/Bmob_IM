package com.bmob.im.demo.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import com.bmob.im.demo.R;
import com.bmob.im.demo.util.Constant;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

public class DaTiAdapter extends BaseAdapter {
	private Activity activity;
	private TextView wenti;
	private TextView daan1;
	private TextView daan2;
	private TextView daan3;
	private TextView daan4;

	private RadioButton a;
	private RadioButton b;
	private RadioButton c;
	private RadioButton d;
	private HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
	private ArrayList<HashMap<String, String>> contactList;

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public void setContactList(ArrayList<HashMap<String, String>> contactList) {
		this.contactList = contactList;
	}

	@Override
	public int getCount() {
		return contactList.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View v, ViewGroup parent) {
		v = LayoutInflater.from(activity).inflate(R.layout.dati_item, null);
		wenti = (TextView) v.findViewById(R.id.tv_dati_wenti);
		daan1 = (TextView) v.findViewById(R.id.tv_dati_daan1);
		daan2 = (TextView) v.findViewById(R.id.tv_dati_daan2);
		daan3 = (TextView) v.findViewById(R.id.tv_dati_daan3);
		daan4 = (TextView) v.findViewById(R.id.tv_dati_daan4);

		wenti.setText(contactList.get(position).get(Constant.DATI_QUESTION));
		daan1.setText(contactList.get(position).get(Constant.DATI_ANSWER1));
		daan2.setText(contactList.get(position).get(Constant.DATI_ANSWER2));
		daan3.setText(contactList.get(position).get(Constant.DATI_ANSWER3));
		daan4.setText(contactList.get(position).get(Constant.DATI_ANSWER4));

		a = (RadioButton) v.findViewById(R.id.rb_dati_1);
		b = (RadioButton) v.findViewById(R.id.rb_dati_2);
		c = (RadioButton) v.findViewById(R.id.rb_dati_3);
		d = (RadioButton) v.findViewById(R.id.rb_dati_4);
		if (map.containsKey(position)) {
			if (map.get(position) == 1) {
				a.setChecked(true);
			} else if (map.get(position) == 2) {
				b.setChecked(true);
			} else if (map.get(position) == 3) {
				c.setChecked(true);
			} else if (map.get(position) == 4) {
				d.setChecked(true);
			}

		}
		a.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (map.containsKey(position)) {
					map.remove(position);
				}

				map.put(position, 1);
			}
		});
		b.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (map.containsKey(position)) {
					map.remove(position);
				}
				map.put(position, 2);
			}
		});
		c.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (map.containsKey(position)) {
					map.remove(position);
				}
				map.put(position, 3);
			}
		});
		d.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (map.containsKey(position)) {
					map.remove(position);
				}
				map.put(position, 4);
			}
		});
		return v;
	}
}
