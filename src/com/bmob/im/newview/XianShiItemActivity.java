package com.bmob.im.newview;

import com.bmob.im.demo.R;
import com.bmob.im.demo.R.id;
import com.bmob.im.demo.R.layout;
import com.bmob.im.demo.R.menu;
import com.bmob.im.demo.util.Constant;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class XianShiItemActivity extends Activity {
	private ImageView image;
	private TextView name;
	private TextView sname;
	private TextView starttime;
	private TextView stoptime;
	private TextView price;
	private TextView possword;
	private RadioButton next;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_xian_shi_item);
		init();
		addListener();
		setData();

	}

	private void setData() {
		Intent intent = getIntent();
		String biaoti = intent.getStringExtra(Constant.XIANSHI_BIAOTI);
		String fbiaoti = intent.getStringExtra(Constant.XIANSHI_FBIAOTI);
		String jiage = intent.getStringExtra(Constant.XIANSHI_PRICE);
		String pwd = intent.getStringExtra(Constant.XIANSHI_PWD);
		String stime = intent.getStringExtra(Constant.XIANSHI_STARTTIME);
		String endtime = intent.getStringExtra(Constant.XIANSHI_STOPTIME);
		name.setText(biaoti);
		sname.setText(fbiaoti);
		starttime.setText(stime);
		stoptime.setText(endtime);
		price.setText(jiage);
		possword.setText(pwd);
	}

	private void addListener() {
		next.setOnClickListener(new android.view.View.OnClickListener() {

			@Override
			public void onClick(View v) {
				View view = LayoutInflater.from(XianShiItemActivity.this)
						.inflate(R.layout.dialog_et, null);
				EditText editText = (EditText) view
						.findViewById(R.id.et_etdialog);

				final AlertDialog dialog = new AlertDialog.Builder(
						XianShiItemActivity.this)
						.setView(view)
						.setTitle("请输入数量")
						.setPositiveButton("确定", new OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								Toast.makeText(XianShiItemActivity.this,
										"已加入我的订单", Toast.LENGTH_SHORT).show();
							}

						})
						.setNegativeButton("取消",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										dialog.dismiss();
									}
								}).create();
				dialog.show();
				// 获得焦点，弹出软键盘
				editText.setOnFocusChangeListener(new OnFocusChangeListener() {

					@Override
					public void onFocusChange(View v, boolean hasFocus) {
						if (hasFocus) {
							dialog.getWindow()
									.setSoftInputMode(
											WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
						}
					}
				});

			}
		});
	}

	private void init() {
		image = (ImageView) findViewById(R.id.iv_xiannshi_image);
		name = (TextView) findViewById(R.id.tv_xianshi_name);
		sname = (TextView) findViewById(R.id.tv_xianshi_sname);
		starttime = (TextView) findViewById(R.id.tv_xianshi_starttime);
		stoptime = (TextView) findViewById(R.id.tv_xianshi_stoptime);
		price = (TextView) findViewById(R.id.tv_xianshi_price);
		possword = (TextView) findViewById(R.id.tv_xianshi_possword);
		next = (RadioButton) findViewById(R.id.rb_xianshi);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.xian_shi_item, menu);
		return true;
	}

}
