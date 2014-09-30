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
import com.bmob.im.demo.util.MyHttp;

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
		goodsListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String name = ((TextView) view
						.findViewById(R.id.tv_exchange_item_goodsname))
						.getText().toString();
				String idinfo = ((TextView) view
						.findViewById(R.id.tv_exchange_item_info)).getText()
						.toString();
				String price = ((TextView) view
						.findViewById(R.id.tv_exchange_item_price)).getText()
						.toString();

				Toast.makeText(getApplicationContext(), "111", 1000).show();
				Intent getInfoDetailIntent = new Intent(
						ExchangeInfoActivity.this,
						SingleExchangeInfoActivity.class);
				getInfoDetailIntent.putExtra(TAG_NAME, name);
				getInfoDetailIntent.putExtra(TAG_ID, idinfo);
				getInfoDetailIntent.putExtra(TAG_EMAIL, price);
				startActivity(getInfoDetailIntent);
			}
		});
		new GetContacts().execute();
	}

	private void initView() {
		btnBack = (ImageView) findViewById(R.id.btn_top_back);
		goodsListView = (ListView) findViewById(R.id.listview_exchange);

	}

	private class GetContacts extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(ExchangeInfoActivity.this);
			pDialog.setMessage("数据加载中，请稍后...");
			pDialog.setCancelable(false);
			pDialog.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			MyHttp myHttp = new MyHttp();
			String jsonString = myHttp.httpGet(url);
			if (jsonString != null) {
				try {
					JSONObject jsonObject = new JSONObject(jsonString);
					contacts = jsonObject.getJSONArray(TAG_CONTACTS);

					for (int i = 0; i < contacts.length(); i++) {
						JSONObject c = contacts.getJSONObject(i);

						String id = c.getString(TAG_ID);
						String name = c.getString(TAG_NAME);
						String email = c.getString(TAG_EMAIL);

						HashMap<String, String> contactHashMap = new HashMap<String, String>();

						contactHashMap.put(TAG_ID, id);
						contactHashMap.put(TAG_NAME, name);
						contactHashMap.put(TAG_EMAIL, email);
						contactList.add(contactHashMap);
					}

				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {
				Log.e("ServiceHandler", "Couldn't get any data from the url");
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			if (pDialog.isShowing()) {
				pDialog.dismiss();
			}
			ListAdapter adapter = new SimpleAdapter(mContext, contactList,
					R.layout.listview_exchange_item, new String[] { TAG_ID,
							TAG_NAME, TAG_EMAIL }, new int[] {
							R.id.tv_exchange_item_info,
							R.id.tv_exchange_item_goodsname,
							R.id.tv_exchange_item_price });
			goodsListView.setAdapter(adapter);
		}
	}
}
