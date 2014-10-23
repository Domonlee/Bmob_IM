package com.bmob.im.newview;

import com.bmob.im.demo.R;
import com.bmob.im.demo.R.id;
import com.bmob.im.demo.R.layout;
import com.bmob.im.demo.R.menu;
import com.bmob.im.demo.adapter.NewsAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ShequActivity extends Activity {
	private ListView list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shequ);
		init();

	}

	private void init() {
		list = (ListView) findViewById(R.id.lv_news);
		NewsAdapter adapter = new NewsAdapter();
		adapter.setActivity(ShequActivity.this);
		list.setAdapter(adapter);

		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int position,
					long id) {
				Intent intent = new Intent(ShequActivity.this,
						ShequInfoActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.shequ, menu);
		return true;
	}

}
