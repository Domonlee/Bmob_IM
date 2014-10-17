package com.bmob.im.newview;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import android.widget.TextView;

import com.bmob.im.demo.R;
import com.bmob.im.demo.adapter.DaTiAdapter;

public class DaTiActivity extends Activity {
	private ListView list;
	private DaTiAdapter adapter;
	private TextView tijiao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_da_ti);
		init();
		addListener();
	}

	private void addListener() {
		tijiao.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	private void init() {
		list = (ListView) findViewById(R.id.lv_dati);
		adapter = new DaTiAdapter();
		adapter.setActivity(DaTiActivity.this);
		list.setAdapter(adapter);
		tijiao = (TextView) findViewById(R.id.tv_dati_tijiao);

		list.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				adapter.notifyDataSetChanged();
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.da_ti, menu);
		return true;
	}

}
