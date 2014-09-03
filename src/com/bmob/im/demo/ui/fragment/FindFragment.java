package com.bmob.im.demo.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bmob.im.demo.R;
import com.bmob.im.demo.ui.AddFriendActivity;
import com.bmob.im.demo.ui.FragmentBase;

public class FindFragment extends FragmentBase implements OnClickListener {
	private TextView tv_friend;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_find, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initView();
		tv_friend.setOnClickListener(this);
	}

	private void initView() {
		tv_friend = (TextView) findViewById(R.id.tv_find_myfriends);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_find_myfriends:
			Intent intent = new Intent(getActivity(), AddFriendActivity.class);
			startActivity(intent);
			getActivity().overridePendingTransition(R.anim.push_left_in,
					R.anim.push_left_out);
			// getChildFragmentManager().beginTransaction().add(R.id.fragment_order_container
			// arg1)
			break;

		default:
			break;
		}

	}

}
