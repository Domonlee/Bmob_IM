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
		groupbyList = (ListView)findViewById(R.id.listview_groupby);
	}

	private void initValiData(){
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
		new GetContacts().execute();
	}
	
	private class GetContacts extends AsyncTask<Void, Void, Void>{

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(GroupbyCodeInfoActivity.this);
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
						String email = c.getString(TAG_GENDER);

						HashMap<String, String> contactHashMap = new HashMap<String, String>();

						contactHashMap.put(TAG_ID, id);
						contactHashMap.put(TAG_NAME, name);
						contactHashMap.put(TAG_GENDER, email);
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
							TAG_NAME, TAG_GENDER }, new int[] {
							R.id.tv_exchange_item_info,
							R.id.tv_exchange_item_goodsname,
							R.id.tv_exchange_item_price });
			groupbyList.setAdapter(adapter);
		}
	}
	
	

	
}
