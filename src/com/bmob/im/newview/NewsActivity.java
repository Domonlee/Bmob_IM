package com.bmob.im.newview;

import com.bmob.im.demo.R;
import com.bmob.im.demo.adapter.NewsAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class NewsActivity extends Activity {
	private ListView list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);
		init();
	}

	private void init() {
		list = (ListView) findViewById(R.id.lv_news);
		NewsAdapter adapter = new NewsAdapter();
		adapter.setActivity(NewsActivity.this);
		list.setAdapter(adapter);

		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int position,
					long id) {

				Intent intent = new Intent(NewsActivity.this,
						NewsInfoActivity.class);
				startActivity(intent);

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.news, menu);
		return true;
	}

}
