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
 * ���û�������
 * 
 * @Description: TODO
 * @author Domon
 */
public class UpdateInfoActivity extends ActivityBase {

	EditText edit_nick;
	TextView tv_name;
	private String[] selectStr = new String[] { "�޸��ǳ�", "�޸ĳ�ס��",
			"�޸�����(��ʽΪ��20080808)", "�޸��ջ���ַ", "�޸ĵ�������", "�޸�����" };
	private String[] infoStr = new String[] { "����д�ǳ�", "����д��ס��", "����д����",
			"����д�ջ���ַ", "����д��������", "���벻��Ϊ��" };
	private String[] nameStr = new String[] { "�ǳ�", "��ס��", "����", "�ջ���ַ",
			"��������", "����" };
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
		// initTopBarForBoth("�޸��ǳ�",
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
	 * �޸����� updateInfo
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
				Toast.makeText(getApplicationContext(), "������������ʽ������������д",
						Toast.LENGTH_LONG).show();
				return;
			}
			user.setUserMail(inputStr);
			break;
		case 5:
			if (!registerActivity.verifyString(inputStr, 3)) {
				Toast.makeText(getApplicationContext(), "������������ʽ������������д",
						Toast.LENGTH_LONG).show();
				return;
			}
			user.setPassword(inputStr);
			break;
		}
		user.update(this, new UpdateListener() {

			@Override
			public void onSuccess() {
				ShowToast("�޸ĳɹ�");
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
