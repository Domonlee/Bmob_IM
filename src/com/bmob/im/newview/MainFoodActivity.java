package com.bmob.im.newview;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.bmob.im.demo.R;
import com.bmob.im.demo.adapter.MyPagerAdapter;
import com.bmob.im.demo.bean.TuanGuangGao;
import com.bmob.im.demo.util.Constant;
import com.bmob.im.demo.util.ImageLoadOptions;
import com.bmob.im.demo.view.task.TiJiaoTask;
import com.nostra13.universalimageloader.core.ImageLoader;

public class MainFoodActivity extends Activity implements OnPageChangeListener {
	private ImageView image;
	private TextView biaoti;
	private TextView fbiaoti;
	private TextView price;
	private Button ok;
	private TextView fname;
	private TextView fcount;
	private TextView fprice;
	private RelativeLayout layout;
	private ViewPager viewPager;
	private int currentIndex = 0;
	private TextView text;
	private ArrayList<View> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_food);
		init();
		setdata();

	}

	private void setdata() {
		Intent intent = getIntent();
		TuanGuangGao dan = (TuanGuangGao) intent
				.getSerializableExtra(Constant.KEY);

		biaoti.setText(intent.getStringExtra(Constant.TUANGOU_NMAE));
		fbiaoti.setText(intent.getStringExtra(Constant.TUANGOU_EPT));
		price.setText(intent.getStringExtra(Constant.TUANGOU_PJIGE));
		String t = null;
		if (dan != null) {

			t = dan.getTgfubiaoti();

			ImageLoader.getInstance().displayImage(
					Constant.IMAGE_BASE_URL + dan.getTgimg(), image,
					ImageLoadOptions.getOptions());
		} else {
			t = intent.getStringExtra(Constant.TUANGOU_PTEXT);
			ImageLoader.getInstance().displayImage(
					Constant.IMAGE_BASE_URL
							+ intent.getStringExtra(Constant.TUANGOU_IMG),
					image, ImageLoadOptions.getOptions());

		}
		if (t != null && t.contains("-")) {

			String[] s1 = t.split(";");
			String names = "";
			String counts = "";
			String prices = "";
			for (String str : s1) {
				String[] s2 = str.split("-");
				names = names + s2[0] + "\n";
				counts = counts + s2[1] + "\n";
				prices = prices + s2[2] + "\n";
			}
			fname.setText(names);
			fcount.setText(counts);
			fprice.setText(prices);
		}

	}

	private void init() {
		layout = (RelativeLayout) findViewById(R.id.rl_main);
		viewPager = (ViewPager) findViewById(R.id.main_viewpager);
		text = (TextView) findViewById(R.id.tv_main_text);
		viewPager.setOnPageChangeListener(this);
		list = new ArrayList<View>();
//		for (int i = 0; i < 4; i++) {
//			ImageView imageView = new ImageView(MainFoodActivity.this);
//			RelativeLayout.LayoutParams params = new LayoutParams(400, 500);
//			params.addRule(RelativeLayout.CENTER_IN_PARENT);
//			imageView.setLayoutParams(params);
//			list.add(imageView);
//
//		}
//		for (int i = 0; i < list.size(); i++) {
//
//			list.get(i).setBackgroundResource(R.drawable.ceshi1);
//		}

		 LayoutInflater inflater = getLayoutInflater();
		 list.add(inflater.inflate(R.layout.viewpager_item1, null));
		 list.add(inflater.inflate(R.layout.viewpager_item2, null));
		 list.add(inflater.inflate(R.layout.viewpager_item3, null));
		MyPagerAdapter adapter = new MyPagerAdapter();
		adapter.setList(list);
		adapter.setLayout(layout);
		viewPager.setAdapter(adapter);

		viewPager.setOnPageChangeListener(this);
		text.setText(1 + "/" + list.size());
		image = (ImageView) findViewById(R.id.iv_food_image);
		biaoti = (TextView) findViewById(R.id.tv_food_name);
		fbiaoti = (TextView) findViewById(R.id.tv_food_sname);
		price = (TextView) findViewById(R.id.tv_food_price);
		fname = (TextView) findViewById(R.id.tv_tugou_name);
		fcount = (TextView) findViewById(R.id.tv_tugou_count);
		fprice = (TextView) findViewById(R.id.tv_tugou_price);
		ok = (Button) findViewById(R.id.btn_logout);
		ok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TiJiaoTask task = new TiJiaoTask(MainFoodActivity.this);
			}
		});

		image.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				layout.setVisibility(View.VISIBLE);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_food, menu);
		return true;
	}

	@Override
	public void onPageScrollStateChanged(int position) {

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	@Override
	public void onPageSelected(int index) {
		currentIndex = index;
		text.setText((currentIndex + 1) + "/" + list.size());
	}

}
