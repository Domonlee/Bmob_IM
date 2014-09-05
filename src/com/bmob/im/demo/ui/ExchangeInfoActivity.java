package com.bmob.im.demo.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;

import com.bmob.im.demo.R;
import com.bmob.im.demo.model.ItemExchange;

public class ExchangeInfoActivity extends LeftMenuInfoActivityBase {

	private ProgressDialog pDialog;

	// URL to get contacts JSON
	private static String url = "http://api.androidhive.info/contacts/";

	// JSON Node names
	private static final String TAG_CONTACTS = "contacts";
	private static final String TAG_ID = "id";
	private static final String TAG_NAME = "name";

	private ImageView btnBack;
	private ListView goodsListView;

	private Context mContext ;

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
//		mContext = getParent();
		goodsListView = (ListView) findViewById(R.id.listview_exchange);

		contactList = new ArrayList<HashMap<String, String>>();

		new GetContacts().execute();

	}

	private void initView() {
		btnBack = (ImageView) findViewById(R.id.btn_top_back);
		goodsListView = (ListView) findViewById(R.id.listview_exchange);
	}

	/**
	 * @author Domon
	 * 
	 */
	public class MyHttp {
		public String httpGet(String url) {
			String response = null;
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(url);
			HttpResponse httpResponse;
			try {
				httpResponse = httpClient.execute(httpGet);
				if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					response = EntityUtils.toString(httpResponse.getEntity());
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return response;
		}
	}

	private class GetContacts extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
//			pDialog = new ProgressDialog();
//			pDialog.setMessage("please wait...");
//			pDialog.setCancelable(false);
//			pDialog.show();
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

						HashMap<String, String> contactHashMap = new HashMap<String, String>();

						contactHashMap.put(TAG_ID, id);
						contactHashMap.put(TAG_NAME, name);
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
							TAG_NAME }, new int[] { R.id.tv_exchange_item_info,
							R.id.tv_exchange_item_goodsname });
			goodsListView.setAdapter(adapter);
		}
	}
}
