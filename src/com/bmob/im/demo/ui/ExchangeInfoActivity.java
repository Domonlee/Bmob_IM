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

import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;

import com.bmob.im.demo.R;
import com.bmob.im.demo.model.ItemExchange;

public class ExchangeInfoActivity extends LeftMenuInfoActivityBase {

	private ImageView btnBack;
	private ListView goodsListView;

	private Context mContext;
	private List<ItemExchange> exchangeModel;

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
		exchangeModel = new ArrayList<ItemExchange>();
		List<HashMap<String, Object>> ListData = getListData();
		SimpleAdapter simpleAdapter = new SimpleAdapter(mContext, ListData,
				R.layout.listview_exchange_item, new String[] { "prodname",
						"pinpai" }, new int[] {
						R.id.tv_exchange_item_goodsname,
						R.id.tv_exchange_item_info });

		goodsListView.setAdapter(simpleAdapter);
	}

	private List<HashMap<String, Object>> getListData() {
		List<HashMap<String, Object>> dataList = new ArrayList<HashMap<String, Object>>();
		String urlString = "http://www.ranlixu.com/api.asp?action=search&MidCode=703";
		MyHttp myHttp = new MyHttp();
		String retString = myHttp.httpGet(urlString);
		try {
			JSONArray jsonArray = new JSONArray(retString);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				{
					HashMap<String, Object> hashMap = new HashMap<String, Object>();
					// arrayList.`("prodname",
					// jsonObject.getString("ProdName"));
					hashMap.put("prodname", jsonObject.getString("ProdName"));
					hashMap.put("pinpai", jsonObject.getString("pinpai"));
					dataList.add(hashMap);
				}

			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return dataList;
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
				int statuscode = httpResponse.getStatusLine().getStatusCode();
				if (statuscode == HttpStatus.SC_OK) {
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

}
