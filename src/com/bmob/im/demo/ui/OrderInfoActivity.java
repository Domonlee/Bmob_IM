package com.bmob.im.demo.ui;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
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

import com.bmob.im.demo.R;
import com.bmob.im.demo.ui.fragment.OrderTopOneFragment;
import com.bmob.im.demo.ui.fragment.OrderTopTwoFragment;
import com.bmob.im.demo.util.Constant;
import com.bmob.im.demo.util.MyHttp;
import com.bmob.im.demo.view.task.DingDanTask;
import com.bmob.im.demo.view.task.CityTask;
import com.bmob.im.newview.OrderInfoItemActivity;

//						_ooOoo_  
//					   o8888888o  
//					   88" . "88  
//					   (| -_- |)  
//						O\ = /O  
//					____/`---'\____  
//					.   ' \\| |// `.  
//					/ \\||| : |||// \  
//				  / _||||| -:- |||||- \  
//  				| | \\\ - /// | |  
//				  | \_| ''\---/'' | |  
//				   \ .-\__ `-` ___/-. /  
//				___`. .' /--.--\ `. . __  
//			 ."" '< `.___\_<|>_/___.' >'"".  
//			| | : `- \`.;`\ _ /`;.`/ - ` : | |  
//		      \ \ `-. \_ __\ /__ _/ .-` / /  
//	  ======`-.____`-.___\_____/___.-`____.-'======  
//						 `=---='  
//
//		.............................................  
//					佛祖保佑             永无BUG 
//佛曰:  
//写字楼里写字间，写字间里程序员；  
//程序人员写程序，又拿程序换酒钱。  
//酒醒只在网上坐，酒醉还来网下眠；  
//酒醉酒醒日复日，网上网下年复年。  
//但愿老死电脑间，不愿鞠躬老板前；  
//奔驰宝马贵者趣，公交自行程序员。  
//别人笑我忒疯癫，我笑自己命太贱；  
//不见满街漂亮妹，哪个归得程序员？  

/**
 * @author Domon
 * 
 */
public class OrderInfoActivity extends LeftMenuInfoActivityBase {
	private OrderTopOneFragment oneFragment;
	private OrderTopTwoFragment twoFragment;
	private Fragment[] fragments;
	private ListView list;

	private ImageView btnBack;

	private TextView[] topTab;
	private int cIndex = 0;
	private int index;

	private ProgressDialog pDialog;
	private JSONArray contacts;
	private ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_order_info);
		initView();

		topTab[0].setOnClickListener(new TopTabOnClickListener());
		topTab[1].setOnClickListener(new TopTabOnClickListener());
		topTab[2].setOnClickListener(new TopTabOnClickListener());

		btnBack.setOnClickListener(new BackOnClickListener());

		RelativeLayout layout = (RelativeLayout) findViewById(R.id.fragment_order_container);
		layout.setOnTouchListener(new SlidingBackListener());

	}

	private void initView() {
		oneFragment = new OrderTopOneFragment();
		twoFragment = new OrderTopTwoFragment();

		fragments = new Fragment[] { oneFragment, twoFragment };

		topTab = new TextView[3];

		topTab[0] = (TextView) findViewById(R.id.tv_order_top1);
		topTab[1] = (TextView) findViewById(R.id.tv_order_top2);
		topTab[2] = (TextView) findViewById(R.id.tv_order_top3);

		topTab[0].setSelected(true);

		list = (ListView) findViewById(R.id.lv_order);

		btnBack = (ImageView) findViewById(R.id.btn_top_back);

		getSupportFragmentManager().beginTransaction()
				.add(R.id.fragment_order_container, oneFragment)
				.add(R.id.fragment_order_container, twoFragment)
				.hide(twoFragment).show(oneFragment).commit();
		list.setOnItemClickListener(new OnItemClickListener() {
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

				Intent getInfoDetailIntent = new Intent(OrderInfoActivity.this,
						OrderInfoItemActivity.class);
				getInfoDetailIntent.putExtra(Constant.TAG_NAME, name);
				getInfoDetailIntent.putExtra(Constant.TAG_ID, idinfo);
				getInfoDetailIntent.putExtra(Constant.TAG_GENDER, gender);
				startActivity(getInfoDetailIntent);
			}
		});
		// 启动异步任务加载数据
		DingDanTask task = new DingDanTask(OrderInfoActivity.this);
		task.setList(list);
		task.execute();

	}

	class TopTabOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.tv_order_top1:
				setSelected(0, 1, 2);
				index = 0;
				selectFargment(index, cIndex);
				break;
			case R.id.tv_order_top2:
				setSelected(1, 0, 2);
				index = 1;
				selectFargment(index, cIndex);
				break;
			case R.id.tv_order_top3:
				setSelected(2, 1, 0);
				index = 2;
				selectFargment(index, cIndex);
				break;
			}
		}
	}

	/**
	 * 选择Top Bar上面的按钮
	 * 
	 * @param x
	 *            选中的菜单
	 * @param y
	 * @param z
	 */
	public void setSelected(int x, int y, int z) {
		topTab[x].setSelected(true);
		topTab[y].setSelected(false);
		topTab[z].setSelected(false);
	}

	/**
	 * @param t
	 * @param c
	 */
	public void selectFargment(int t, int c) {
		if (cIndex != index) {
			FragmentTransaction transaction = getSupportFragmentManager()
					.beginTransaction();
			transaction.hide(fragments[cIndex]);
			if (!fragments[index].isAdded()) {
				transaction.add(R.id.shop_fragment_container, fragments[index]);
			}
			transaction.show(fragments[index]).commit();
		}
		cIndex = index;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		return super.onTouchEvent(event);
	}

}
