package com.bmob.im.demo.ui;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bmob.im.demo.R;
import com.bmob.im.demo.util.Constant;
import com.bmob.im.demo.view.task.MyTuanGouJuanTask;

public class GroupbyCodeInfoActivity extends LeftMenuInfoActivityBase {
	private ProgressDialog pDialog;

	private static String url = "http://api.androidhive.info/contacts/";

	// JSON Node names
	private static final String TAG_CONTACTS = "contacts";
	private static final String TAG_ID = "id";
	private static final String TAG_NAME = "name";
	private static final String TAG_GENDER = "gender";

	private ImageView btnBack;
	private ListView groupbyList;
	private Context mContext;
	JSONArray contacts = null;
	ArrayList<HashMap<String, String>> contactList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_groupby_info);

		initView();
		initValiData();
		btnBack.setOnClickListener(new BackOnClickListener());

		RelativeLayout layout = (RelativeLayout) findViewById(R.id.fragment_order_container);
		layout.setOnTouchListener(new SlidingBackListener());
	}

	private void initView() {
		btnBack = (ImageView) findViewById(R.id.btn_top_back);
		groupbyList = (ListView) findViewById(R.id.listview_groupby);
	}

	private void initValiData() {
		mContext = getApplicationContext();
		contactList = new ArrayList<HashMap<String, String>>();
		groupbyList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String name = ((TextView) view
						.findViewById(R.id.tv_exchange_item_goodsname))
						.getText().toString();
				String idinfo = ((TextView) view
						.findViewById(R.id.tv_exchange_item_info)).getText()
						.toString();
				String gender = ((TextView) view
						.findViewById(R.id.tv_exchange_item_price)).getText()
						.toString();

				Intent getInfoDetailIntent = new Intent(
						GroupbyCodeInfoActivity.this,
						SingleGroupbyInfoActivity.class);
				getInfoDetailIntent.putExtra(TAG_NAME, name);
				getInfoDetailIntent.putExtra(TAG_ID, idinfo);
				getInfoDetailIntent.putExtra(TAG_GENDER, gender);
				startActivity(getInfoDetailIntent);
			}
		});

		MyTuanGouJuanTask task = new MyTuanGouJuanTask(
				GroupbyCodeInfoActivity.this);
		task.setList(groupbyList);
		task.setUrl(Constant.MYTUANGOUJUAN_URL + "userid=" + 1);
		task.execute();
	}

}
