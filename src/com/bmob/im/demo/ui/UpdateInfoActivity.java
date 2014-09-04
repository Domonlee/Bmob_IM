package com.bmob.im.demo.ui;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.listener.UpdateListener;

import com.bmob.im.demo.R;
import com.bmob.im.demo.bean.User;
import com.bmob.im.demo.view.HeaderLayout.onRightImageButtonClickListener;

/**
 * 设置基础属性
 * 
 * @Description: TODO
 * @author Domon
 */
public class UpdateInfoActivity extends ActivityBase {

	EditText edit_nick;
	TextView tv_name;
	private String[] selectStr = new String[] { "修改昵称", "修改常住地",
			"修改生日(格式为：20080808)", "修改收货地址", "修改电子邮箱", "修改密码" };
	private String[] infoStr = new String[] { "请填写昵称", "请填写常住地", "请填写生日",
			"请填写收货地址", "请填写电子邮箱", "密码不能为空" };
	private String[] nameStr = new String[] { "昵称", "常住地", "生日", "收货地址",
			"电子邮箱", "密码" };
	int i = 0;
	
	RegisterActivity registerActivity = new RegisterActivity();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_updateinfo);
		i = getIntent().getIntExtra("i", 0);
		initView();
	}

	private void initView() {
		// initTopBarForBoth("修改昵称",
		// R.drawable.base_action_bar_true_bg_selector,
		tv_name = (TextView) findViewById(R.id.tv_updateInfo_name);
		tv_name.setText(nameStr[i]);
		initTopBarForBoth(selectStr[i],
				R.drawable.base_action_bar_true_bg_selector,
				new onRightImageButtonClickListener() {

					@Override
					public void onClick() {
						String inputString = edit_nick.getText().toString();
						if (inputString.equals("")) {
							ShowToast(infoStr[i]);
							return;
						}
						updateInfo(inputString, i);
					}
				});
		edit_nick = (EditText) findViewById(R.id.edit_nick);
	}

	/**
	 * 修改资料 updateInfo
	 * 
	 * @Title: updateInfo
	 * @return void
	 * @throws
	 */
	private void updateInfo(String inputStr, int i) {
		final User user = userManager.getCurrentUser(User.class);
		switch (i) {
		case 0:
			user.setNick(inputStr);
			break;
		case 1:
			user.setUserAddr(inputStr);
			break;
		case 2:
			user.setUserBir(inputStr);
			break;
		case 3:
			user.setUserSendAddr(inputStr);
			break;
		case 4:
			if (!registerActivity.verifyString(inputStr, 1)) {
				Toast.makeText(getApplicationContext(), "您输入的邮箱格式有误，请重新填写",
						Toast.LENGTH_LONG).show();
				return;
			}
			user.setUserMail(inputStr);
			break;
		case 5:
			if (!registerActivity.verifyString(inputStr, 3)) {
				Toast.makeText(getApplicationContext(), "您输入的密码格式有误，请重新填写",
						Toast.LENGTH_LONG).show();
				return;
			}
			user.setPassword(inputStr);
			break;
		}
		user.update(this, new UpdateListener() {

			@Override
			public void onSuccess() {
				ShowToast("修改成功");
				finish();
				overridePendingTransition(R.anim.push_left_in,
						R.anim.push_right_out);
			}

			@Override
			public void onFailure(int arg0, String arg1) {
				ShowToast("onFailure:" + arg1);
			}
		});
	}
}
