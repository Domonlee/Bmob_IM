package com.bmob.im.demo.ui;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bmob.im.demo.R;
import com.bmob.im.demo.util.Constant;
import com.bmob.im.demo.util.MyHttp;
import com.bmob.im.demo.view.task.JiFenTask;
import com.bmob.im.demo.view.task.MyDiandanTask;
import com.bmob.im.demo.view.task.MyJiFenTask;

public class ExchangeInfoActivity extends LeftMenuInfoActivityBase {
	// public class ExchangeInfoActivity extends Activity {

	private ProgressDialog pDialog;

	private static String url = "http://api.androidhive.info/contacts/";

	// JSON Node names
	private static final String TAG_CONTACTS = "contacts";
	private static final String TAG_ID = "id";
	private static final String TAG_NAME = "name";
	private static final String TAG_EMAIL = "email";

	private ImageView btnBack;
	private ListView goodsListView;

	private Context mContext;

	JSONArray contacts = null;
	ArrayList<HashMap<String, String>> contactList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_exchange_info);

		initView();
		initValiData();
		btnBack.setOnClickListener(new BackOnClickListener());

		RelativeLayout layout = (RelativeLayout) findViewById(R.id.fragment_order_container);
		layout.setOnTouchListener(new SlidingBackListener());
	}

	private void initValiData() {
		mContext = getApplicationContext();
		contactList = new ArrayList<HashMap<String, String>>();

		MyJiFenTask task = new MyJiFenTask(ExchangeInfoActivity.this);
		task.setList(goodsListView);
		task.setUrl(Constant.MYDUIHUAN_URL + "userid=" + 1);
		task.execute();
	}

	private void initView() {
		btnBack = (ImageView) findViewById(R.id.btn_top_back);
		goodsListView = (ListView) findViewById(R.id.listview_exchange);

	}

}
