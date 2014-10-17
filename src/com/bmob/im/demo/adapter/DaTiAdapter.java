package com.bmob.im.demo.adapter;

import java.util.HashMap;

import com.bmob.im.demo.R;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;

public class DaTiAdapter extends BaseAdapter {
	private Activity activity;

	private RadioButton a;
	private RadioButton b;
	private RadioButton c;
	private RadioButton d;
	private HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	@Override
	public int getCount() {
		return 10;
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
		Log.i("cheng", map.toString());
		return v;
	}

}
