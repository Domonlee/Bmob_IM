package com.bmob.im.demo.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bmob.im.demo.R;
import com.bmob.im.demo.ui.fragment.OrderTopOneFragment;
import com.bmob.im.demo.ui.fragment.OrderTopThreeFragment;
import com.bmob.im.demo.ui.fragment.OrderTopTwoFragment;

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

public class OrderInfoActivity extends ActivityBase {
	private OrderTopOneFragment oneFragment;
	private OrderTopTwoFragment twoFragment;
	private OrderTopThreeFragment threeFragment;
	private Fragment[] fragments;

	private ImageView btnBack;

	private TextView[] topTab;
	private int cIndex = 0;
	private int index;

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

	}

	private void initView() {
		oneFragment = new OrderTopOneFragment();
		twoFragment = new OrderTopTwoFragment();
		threeFragment = new OrderTopThreeFragment();

		fragments = new Fragment[] { oneFragment, twoFragment, threeFragment };

		topTab = new TextView[3];

		topTab[0] = (TextView) findViewById(R.id.tv_order_top1);
		topTab[1] = (TextView) findViewById(R.id.tv_order_top2);
		topTab[2] = (TextView) findViewById(R.id.tv_order_top3);

		topTab[0].setSelected(true);

		btnBack = (ImageView) findViewById(R.id.btn_top_back);

		getSupportFragmentManager().beginTransaction()
				.add(R.id.fragment_order_container, oneFragment)
				.add(R.id.fragment_order_container, twoFragment)
				.add(R.id.fragment_order_container, threeFragment)
				.hide(twoFragment).hide(threeFragment).show(oneFragment)
				.commit();
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
	 * 
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

}
