package com.bmob.im.demo.ui;

/**
 * 未完成 手机号字段，手机号检测
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import cn.bmob.im.bean.BmobChatUser;
import cn.bmob.im.util.BmobLog;
import cn.bmob.v3.BmobInstallation;
import cn.bmob.v3.listener.SaveListener;

import com.bmob.im.demo.R;
import com.bmob.im.demo.bean.User;
import com.bmob.im.demo.config.BmobConstants;
import com.bmob.im.demo.util.CommonUtils;

public class RegisterActivity extends BaseActivity {

	Button btn_register, btn_sendkey;
	EditText et_username, et_password, et_pswagain, et_inputkey, et_invitecode,
			et_telenum;
	BmobChatUser currentUser;
	private MyCount mc;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		initTopBarForLeft("注册");

		et_username = (EditText) findViewById(R.id.et_username);
		et_password = (EditText) findViewById(R.id.et_password);
		et_pswagain = (EditText) findViewById(R.id.et_pswagain);
		et_inputkey = (EditText) findViewById(R.id.et_inputkey);
		et_invitecode = (EditText) findViewById(R.id.et_invitecode);
		et_telenum = (EditText) findViewById(R.id.et_telenum);

		btn_register = (Button) findViewById(R.id.btn_register);
		btn_sendkey = (Button) findViewById(R.id.btn_sendkey);

		btn_register.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				register();
			}
		});

		// send the tele code
		btn_sendkey.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				sendMessage();
				Toast.makeText(getApplicationContext(), "sending...",
						Toast.LENGTH_LONG).show();
			}
		});
	}

	private void register() {

		String name = et_username.getText().toString();
		String telenum = et_telenum.getText().toString();
		String password = et_password.getText().toString();
		String psw_again = et_pswagain.getText().toString();

		Boolean isTeteNum = verifyString(telenum, 2);
		Boolean isPsw = verifyString(password, 3);
		if (!isTeteNum) {
			Toast.makeText(getApplicationContext(), "您输入的手机号码有误，请重新填写",
					Toast.LENGTH_LONG).show();
			return;
		}

		if (!isPsw) {
			Toast.makeText(getApplicationContext(), "您输入的密码格式有误，请重新填写",
					Toast.LENGTH_LONG).show();
			return;
		}
		if (TextUtils.isEmpty(name)) {
			ShowToast(R.string.toast_error_username_null);
			return;
		}

		if (TextUtils.isEmpty(password)) {
			ShowToast(R.string.toast_error_password_null);
			return;
		}
		if (TextUtils.isEmpty(telenum)) {
			ShowToast(R.string.toast_error_password_null);
			return;
		}
		if (!psw_again.equals(password)) {
			ShowToast(R.string.toast_error_comfirm_password);
			return;
		}

		boolean isNetConnected = CommonUtils.isNetworkAvailable(this);
		if (!isNetConnected) {
			ShowToast(R.string.network_tips);
			return;
		}

		final ProgressDialog progress = new ProgressDialog(
				RegisterActivity.this);
		progress.setMessage("正在注册...");
		progress.setCanceledOnTouchOutside(false);
		progress.show();
		// 由于每个应用的注册所需的资料都不一样，故IM sdk未提供注册方法，用户可按照bmod SDK的注册方式进行注册。
		// 注册的时候需要注意两点：1、User表中绑定设备id和type，2、设备表中绑定username字段
		final User bu = new User();
		bu.setUsername(name);
		bu.setPassword(password);
		bu.setUserTele(telenum);
		// 将user和设备id进行绑定
		bu.setDeviceType("android");
		bu.setInstallId(BmobInstallation.getInstallationId(this));
		bu.signUp(RegisterActivity.this, new SaveListener() {

			@Override
			public void onSuccess() {
				progress.dismiss();
				ShowToast("注册成功");
				// 将设备与username进行绑定
				userManager.bindInstallationForRegister(bu.getUsername());
				// 更新地理位置信息
				updateUserLocation();

				// 发广播通知登陆页面退出
				sendBroadcast(new Intent(
						BmobConstants.ACTION_REGISTER_SUCCESS_FINISH));
				// 启动主页
				Intent intent = new Intent(RegisterActivity.this,
						MainInActivity.class);
				startActivity(intent);
				finish();

			}

			@Override
			public void onFailure(int arg0, String arg1) {
				BmobLog.i(arg1);
				ShowToast("注册失败:" + arg1);
				progress.dismiss();
			}
		});

	}

	/**
	 * @说明 String 待检验字符串 Int 检验动作 1:邮箱 2:手机 3:密码
	 * @return boolean
	 */
	public boolean verifyString(String str, int action) {
		boolean tag = true;
		final String pattern1 = "^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\\.([a-zA-Z0-9_-])+)+$";
		final String pattern2 = "^[1][3,7,5,8][0-9]{9}$";
		final String pattern3 = "^^[a-zA-Z0-9_.]{6,18}$";
		Pattern pattern = null;
		switch (action) {
		case 1:
			pattern = Pattern.compile(pattern1);
			break;
		case 2:
			pattern = Pattern.compile(pattern2);
			break;
		case 3:
			pattern = Pattern.compile(pattern3);
			break;
		}
		Matcher mat = pattern.matcher(str);
		if (!mat.matches()) {
			tag = false;
		}
		return tag;
	}

	private void sendMessage() {
		mc = new MyCount(60000, 1000);
		mc.start();
	}

	/* 定义一个倒计时的内部类 */
	class MyCount extends CountDownTimer {
		public MyCount(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onFinish() {
			btn_sendkey.setText("点击重新发送");
			btn_sendkey.setClickable(true);
		}

		@Override
		public void onTick(long millisUntilFinished) {
			btn_sendkey.setClickable(false);
			btn_sendkey.setText(millisUntilFinished / 1000 + "秒后重新发送");
		}
	}
}
