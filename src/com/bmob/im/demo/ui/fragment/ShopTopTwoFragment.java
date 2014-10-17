package com.bmob.im.demo.ui.fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.json.JSONArray;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.bmob.im.demo.R;
import com.bmob.im.demo.ui.FragmentBase;
import com.bmob.im.demo.ui.NewFriendActivity;
import com.bmob.im.demo.view.task.MyDiandanTask;

public class ShopTopTwoFragment extends FragmentBase {

	private TextView tv_time;
	int time = 0;// 时间差
	private Handler mHandler = new Handler();// 全局的Handler
	private String dateString;// 获取当前时间和截止时间的时间差
	private int mHour;
	public ListView list;
	

	

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_shop_limit, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initView();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.format(new Date());

		dateString = sdf.format(new Date()) + " 21:00:00";
		time = getTimeInterval(dateString);
		new Thread(new TimeCount()).start();

	}

	private int getTimeInterval(String dateString2) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		int interval = 0;
		try {
			Date curDate = new Date(System.currentTimeMillis());
			Date beginDate = simpleDateFormat.parse(dateString2);

			long b = beginDate.getTime();
			long c = curDate.getTime();
			interval = (int) ((b - c) / (1000));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return interval;
	}

	private void initView() {
		tv_time = (TextView) findViewById(R.id.tv_fragment_shop_limit_time);
		list = (ListView) findViewById(R.id.lv_xianshiqiang);
		
	}

	class TimeCount implements Runnable {
		@Override
		public void run() {
			while (time > 0)// 整个倒计时执行的循环
			{
				time--;
				mHandler.post(new Runnable() // 通过它在UI主线程中修改显示的剩余时间
				{
					public void run() {
						tv_time.setText(getInterval(time));// 显示剩余时间
					}
				});
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			// 下面是倒计时结束逻辑
			mHandler.post(new Runnable() {
				@Override
				public void run() {
					tv_time.setText("设定的时间到。");
				}
			});
		}
	}

	/**
	 * 设定显示文字
	 */
	public static String getInterval(int time) {
		String txt = null;
		if (time >= 0) {
			long day = time / (24 * 3600);// 天
			long hour = time % (24 * 3600) / 3600;// 小时
			long minute = time % 3600 / 60;// 分钟
			long second = time % 60;// 秒

			txt = "距离现在还有：" + hour + "小时" + minute + "分" + second + "秒";

		} else {
			txt = "已过期";
		}
		return txt;
	}
}
