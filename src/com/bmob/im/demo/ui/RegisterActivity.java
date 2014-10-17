package com.bmob.im.demo.ui;

/**
 * δ��� �ֻ����ֶΣ��ֻ��ż��
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

		initTopBarForLeft("ע��");

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
			Toast.makeText(getApplicationContext(), "��������ֻ�����������������д",
					Toast.LENGTH_LONG).show();
			return;
		}

		if (!isPsw) {
			Toast.makeText(getApplicationContext(), "������������ʽ������������д",
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
		progress.setMessage("����ע��...");
		progress.setCanceledOnTouchOutside(false);
		progress.show();
		// ����ÿ��Ӧ�õ�ע����������϶���һ������IM sdkδ�ṩע�᷽�����û��ɰ���bmod SDK��ע�᷽ʽ����ע�ᡣ
		// ע���ʱ����Ҫע�����㣺1��User���а��豸id��type��2���豸���а�username�ֶ�
		final User bu = new User();
		bu.setUsername(name);
		bu.setPassword(password);
		bu.setUserTele(telenum);
		// ��user���豸id���а�
		bu.setDeviceType("android");
		bu.setInstallId(BmobInstallation.getInstallationId(this));
		bu.signUp(RegisterActivity.this, new SaveListener() {

			@Override
			public void onSuccess() {
				progress.dismiss();
				ShowToast("ע��ɹ�");
				// ���豸��username���а�
				userManager.bindInstallationForRegister(bu.getUsername());
				// ���µ���λ����Ϣ
				updateUserLocation();

				// ���㲥֪ͨ��½ҳ���˳�
				sendBroadcast(new Intent(
						BmobConstants.ACTION_REGISTER_SUCCESS_FINISH));
				// ������ҳ
				Intent intent = new Intent(RegisterActivity.this,
						MainInActivity.class);
				startActivity(intent);
				finish();

			}

			@Override
			public void onFailure(int arg0, String arg1) {
				BmobLog.i(arg1);
				ShowToast("ע��ʧ��:" + arg1);
				progress.dismiss();
			}
		});

	}

	/**
	 * @˵�� String �������ַ��� Int ���鶯�� 1:���� 2:�ֻ� 3:����
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

	/* ����һ������ʱ���ڲ��� */
	class MyCount extends CountDownTimer {
		public MyCount(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onFinish() {
			btn_sendkey.setText("������·���");
			btn_sendkey.setClickable(true);
		}

		@Override
		public void onTick(long millisUntilFinished) {
			btn_sendkey.setClickable(false);
			btn_sendkey.setText(millisUntilFinished / 1000 + "������·���");
		}
	}
}
