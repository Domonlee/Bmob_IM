package com.bmob.im.demo.lock;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.bmob.im.demo.CustomApplcation;
import com.bmob.im.demo.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends Activity implements OnTouchListener {
	private TextView tvTouch;
	ScrollRelativeLayout scrollerLayout;
	LinearLayout toolsLayout;

	float x = 0;
	int dx = 0;
	float y1 = 0;
	float y2 = 0;
	String currentTimeString;
	String currentDateString;
	private static final int msgKey = 1;
	boolean visble = false;

	private TextView tvTime;
	private TextView tvDate;
	private Button share;
	private ImageView[] img =  new ImageView[4];
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


		// set no title
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		// set layout
		setContentView(R.layout.activity_home);

		tvTouch = (TextView) findViewById(R.id.Tv_scroll);
		tvTime = (TextView) findViewById(R.id.Tv_time);
		tvDate = (TextView) findViewById(R.id.Tv_date);
		share = (Button) findViewById(R.id.Btn_share);
		img[0] = (ImageView)findViewById(R.id.tv_test);
		img[1] = (ImageView)findViewById(R.id.tv_test2);
		img[2] = (ImageView)findViewById(R.id.tv_test3);
		img[3] = (ImageView)findViewById(R.id.tv_test4);
		
		share.setOnClickListener(listener);
		img[0].setOnClickListener(listener);
		img[1].setOnClickListener(listener);
		img[2].setOnClickListener(listener);
		img[3].setOnClickListener(listener);
		
		tvTouch.setOnTouchListener(this);

		scrollerLayout = (ScrollRelativeLayout) findViewById(R.id.layout_scroller);
		toolsLayout = (LinearLayout) findViewById(R.id.layout_tools);

		currentDateString = java.text.DateFormat.getDateInstance().format(
				new Date());
		currentTimeString = new SimpleDateFormat("HH:mm:ss").format(new Date());

		tvTime.setText(currentTimeString);
		tvDate.setText(currentDateString);

		new timeThread().start();

	}
	View.OnClickListener listener = new View.OnClickListener() {
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.Btn_share:
				CustomApplcation.mInstance.showShare();
				break;
			case R.id.tv_test:
				Intent intent = new Intent();
				intent.setClassName("com.android.calculator2", "com.android.calculator2.Calculator");
				startActivity(intent);
				break;
			case R.id.tv_test2:
				Intent intent2 = new Intent();
				intent2.setAction(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
				startActivity(intent2);
				break;
			case R.id.tv_test3:
				Uri telUri = Uri.parse("tel:");  
				startActivity(new Intent(Intent.ACTION_DIAL, telUri));
				break;
			case R.id.tv_test4:
				Uri smsUri = Uri.parse("tel:");  
				startActivity(new Intent(Intent.ACTION_VIEW, smsUri).setType("vnd.android-dir/mms-sms"));  
				break;
			default:
				break;
			}
		}
	};
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			x = event.getRawX();
			y1 = event.getY();
			break;
		case MotionEvent.ACTION_UP:
			int w = getResources().getDisplayMetrics().widthPixels;
			int initX = w / 2 - tvTouch.getWidth() / 2;
			y2 = event.getY();

			// get the right&left limt
			int rightLimt = new BigDecimal(w * 0.8 - initX).intValue();
			int leftLimt = rightLimt;

			// to the right
			if (scrollerLayout.getCurrX() < 0
					&& -scrollerLayout.getCurrX() > rightLimt) {
				scrollerLayout.autoScroll(scrollerLayout.getCurrX(), 0,
						-scrollerLayout.getCurrX(), 0, 11500);
				onScrollOut(true);
			}

			// to the left
			else if (scrollerLayout.getCurrX() > 0
					&& scrollerLayout.getCurrX() > leftLimt) {
				scrollerLayout.autoScroll(scrollerLayout.getCurrX(), 0,
						-scrollerLayout.getCurrX(), 0, 11500);
				onScrollOut(false);
			} else {
				// fix the distance
				scrollerLayout.autoScroll(scrollerLayout.getCurrX(),
						scrollerLayout.getCurrY(), -scrollerLayout.getCurrX(),
						-scrollerLayout.getCurrY(), 1000);
			}
			if ((y1 - y2) > 50) {
				Toast.makeText(getApplicationContext(), "up srcoll",
						Toast.LENGTH_LONG).show();
				if (!visble) {
					toolsLayout.setVisibility(View.VISIBLE);
					visble = true;
				} else {
					toolsLayout.setVisibility(View.GONE);
					visble = false;
				}
			}
			break;
		case MotionEvent.ACTION_MOVE:
			dx = new BigDecimal(x - event.getRawX() * 1.1).intValue();
			scrollerLayout.beginScroll(dx);
			break;
		}
		return true;
	}
	
	/**
	 * TODO   加积分接口
	 * @param flag  右true 左false 
	 */
	private void onScrollOut(boolean flag) {
		Toast.makeText(this, "积分+10", 0).show();
		if(flag){
			Intent mHomeIntent = new Intent(Intent.ACTION_MAIN);
			mHomeIntent.addCategory(Intent.CATEGORY_HOME);
			mHomeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
			startActivity(mHomeIntent);
		}
		else
			startActivity(new Intent(HomeActivity.this,WebActivity.class));
		finish();
	}

	public class timeThread extends Thread {
		@Override
		public void run() {
			do {
				try {
					Thread.sleep(1000);
					Message msg = new Message();
					msg.what = msgKey;
					mHandler.sendMessage(msg);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} while (true);
		}
	}

	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case msgKey:
				long systemTime = System.currentTimeMillis();
				CharSequence sysTimeStr = DateFormat.format("hh:mm:ss",
						systemTime);
				tvTime.setText(sysTimeStr);
				break;
			default:
				break;
			}
		};
	};

	@Override
	public void onBackPressed() {
		
	}

}