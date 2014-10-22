package com.bmob.im.newview;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bmob.im.demo.R;
import com.bmob.im.demo.adapter.DaTiAdapter;
import com.bmob.im.demo.util.Constant;
import com.bmob.im.demo.util.HttpUtil;
import com.bmob.im.demo.view.task.DaTiTask;

public class DaTiActivity extends Activity {
	private ListView list;
	private DaTiAdapter adapter;
	private TextView tijiao;
	private String text;
	private JSONArray contacts;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_da_ti);
		init();
		setData();
		addListener();
	}

	private void setData() {
		DaTiTask task = new DaTiTask(DaTiActivity.this);
		task.setList(list);
		task.execute();
	}

	private void addListener() {
		tijiao.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(DaTiActivity.this, "已成功提交，感谢参与",
						Toast.LENGTH_SHORT).show();
				new Thread() {

					public void run() {
						try {
							String josn = HttpUtil
									.httpGet(Constant.DATI_TIJIAO_URL
											+ "userid=" + 1 + "&denshou=" + 10);
							JSONObject jsonObject = new JSONObject(josn);
							text = (String) jsonObject.get("markmassage");
						} catch (JSONException e) {
							e.printStackTrace();
						}

					};
				}.start();

				finish();
			}
		});
	}

	private void init() {
		list = (ListView) findViewById(R.id.lv_dati);

		tijiao = (TextView) findViewById(R.id.tv_dati_tijiao);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.da_ti, menu);
		return true;
	}

}
